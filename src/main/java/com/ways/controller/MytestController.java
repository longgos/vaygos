package com.ways.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.tool.base.BaseController;
import com.ways.constants.VayConst;
import com.ways.entity.UserBlogEntity;
import com.ways.facade.UserBlogFacade;

@Controller
@RequestMapping(value="/mytest")
public class MytestController extends BaseController{

	@Autowired
	private UserBlogFacade userBlogFacade;
	
	@RequestMapping(value ="/tomytest")
	public String tomytest(){
		return "mytest/upload";
	}
	
	/**
	 * 批量分片上传文件
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(method={RequestMethod.POST},value={"/upload"})
	@ResponseBody
	public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			String path = request.getParameter("path");
			path = path!=null? java.net.URLDecoder.decode(path, "utf-8") : "";
			// 判断enctype属性是否为multipart/form-data    
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart){
				//创建文件工厂
				FileItemFactory factory = new DiskFileItemFactory();
				/*ServletFileUpload 处理上传的文件的数据，优先保存在缓冲区，如果数据超过了缓冲区大小，
				则保存到硬盘上，存储在DiskFileItemFactory指定目录下的临时文件。数据都接收完后，
				它再在从临时文件中将数据写入到上传文件目录下的指定文件中，并删除临时文件。*/
				ServletFileUpload upload = new ServletFileUpload(factory);
				
				// 得到所有的表单域，它们目前都被当作FileItem 
				List<FileItem> fileForms =upload.parseRequest(request);
				String id="";
				String fileName = "";
				int chunks = 1;// 如果大于1说明是分片处理 
				int chunk = 0;//分片索引，下标从0开始
				FileItem tempFileItem = null;
				for (FileItem fileItem : fileForms) {
					if(fileItem.getFieldName().equals("id")){
						id = fileItem.getString();
					}else if(fileItem.getFieldName().equals("name")){
						fileName = new String((fileItem.getString()).getBytes("ISO-8859-1"),"UTF-8");
					}else if(fileItem.getFieldName().equals("chunks")){
						chunks = Integer.valueOf(fileItem.getString());
					}else if (fileItem.getFieldName().equals("chunk")) {  
                        chunk = Integer.valueOf(fileItem.getString());  
                    }else if(fileItem.getFieldName().equals("file")){
						tempFileItem = fileItem;
					}
				}
				System.out.println("id="+id+"  filename="+fileName+"  chunks="+chunks+" chunk="+chunk+"  size="+tempFileItem.getSize());
				 String filePath = "F:\\word";//文件上传路径 
				 String tempFileDir = filePath+File.separator+id;
						 //filePath;//+id;
				 //临时目录用来存放所有分片文件
				 File parentFileDir = new File(tempFileDir);
				 if(!parentFileDir.exists()){//如果文件夹不存在
					 parentFileDir.mkdirs();//建立多级文件夹
				 }
				 //分片处理时，前台会多次调用上传接口，每次都会上传文件的一部分到后台
				 File tempParatFile = new File(parentFileDir,fileName+"_"+chunk+".part");
				 FileUtils.copyInputStreamToFile(tempFileItem.getInputStream(), tempParatFile);
				 // 是否全部上传完成
				 // 所有分片都存在才说明整个文件上传完成
				 boolean uploadDone = true;
				 for (int i = 0; i < chunk; i++) {
					 //判断文件是否断片全部存在
					File partFile = new File(tempFileDir,fileName+"_"+chunk+".part");
					if(!partFile.exists()){
						uploadDone = false;
					}
				}
				//所有分片文件都上传完成,将所有分片文件合并到一个文件中 	
				 if(uploadDone){
					 // 得到 destTempFile 就是最终的文件 
					 File destTempFile = new File(filePath,fileName);
					 for (int i = 0; i < chunks; i++) {
						File pathFile = new File(parentFileDir,fileName + "_" + i + ".part");
						FileOutputStream destTempfos = new FileOutputStream(destTempFile,true);
						//遍历"所有分片文件"到"最终文件"中 
						FileUtils.copyFile(pathFile, destTempfos);
						destTempfos.close();
					}
					 String imgPath = destTempFile.getPath();
					 VayConst.filepath.add(imgPath);
					 System.out.println("文件路径:"+imgPath);
					 System.out.println("list:"+VayConst.filepath);
					 // 删除临时目录中的分片文件 
					 FileUtils.deleteDirectory(parentFileDir);
				 }else{
					// 临时文件创建失败 
					 if(chunk ==chunks-1){
						 FileUtils.deleteDirectory(parentFileDir); 
					 }
				 }
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	@RequestMapping(value="/saveContents")
	public String saveContents(String jsonobj,Model model){
		List<String> list = VayConst.filepath; 
		String [] path = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			path[i] = list.get(i);
		}
		Map<String, Object> map = JSON.parseObject(jsonobj);
		UserBlogEntity entity = new UserBlogEntity();
		entity.setUserId(1L);
		entity.setBlogTermId("1");
		entity.setBlogType(map.get("blogType").toString());
		entity.setFilePath(Arrays.toString(path));
		entity.setContents(map.get("contents").toString());
		entity.setIsUp(map.get("isUp").toString());
		entity.setZanCount(0L);
		entity.setCommentCount(0L);
		entity.setCreatedBy(VayConst.SYSTEM);
		entity.setCreatedDate(new Date());
		list = null;
		userBlogFacade.save(entity);
		return "mytest/upload";
	}
	
	private HttpServletRequest getRequest() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@RequestMapping(value="/msg")
	@ResponseBody
	public int messageProcess(){
		return 0;
	}
}
