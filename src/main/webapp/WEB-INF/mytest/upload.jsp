<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/index/include/taglib.jsp"%>
<html>
<head>
<link rel="stylesheet" href="${ctxStatic}/webuploader/webuploader.css">
<link rel="stylesheet" href="${ctxStatic}/css/style.css">
<link rel="stylesheet" href="${ctxStatic}/trumbowyg-1.0/trumbowyg/design/css/trumbowyg.css">
<title>Insert title here</title>
<style type="text/css">
/* 	.wu-example{height: 150px;} */
/* 	.uploder_list{height: 100px;} */
/* 	#btns{height: 100px;} */
/* 	#attach{height: 30px;} */
/* 	#uploder{height: 30px;width: 50px;} */
.cl{
	width: 100px;
	height: 100px;
	cursor:url(${ctxStatic}/images/big.cur),auto;
}

.demo{width:580px;margin:30px auto;padding:10px 20px;border: 2px solid #ccc;}
.demo h3{line-height:40px; font-weight: bold;}
.file-item{float: left; position: relative; width: 110px;height: 110px; margin: 0 20px 20px 0; padding: 4px;}
.file-item .info{overflow: hidden;}
.uploader-list{width: 100%; overflow: hidden;border: 1px solid #cfcfcf;}
.buttions{width: 100%;height: 30px;box-sizing: border-box;}
.buttions .btns{line-height: 30px;color: #fff;}
.buttions .btns .begin{background: #00b7ee;    color: #fff;     border-color: transparent;padding: 0 18px;display: inline-block;border-radius: 3px;margin-left: 10px;cursor: pointer;font-size: 14px;float: left;}
.buttions .btns .cancel{background-color: #565656;    color: #fff;     border-color: transparent;padding: 0 18px;display: inline-block;border-radius: 3px;margin-left: 10px;cursor: pointer;font-size: 14px;float: left;}
</style>
</head>
<body>
	<div id="uploader" class="wu-example">
		<!-- 用来存放文件信息 -->
		<div class="demo">
			<div id="asdsadad">
				<div class="queueList">
					<div id="filePicker" class="placeholder">
						<p class="sead">添加一张图片</p>
					</div>
				</div>
				<div id="filePicker2" class="goon">
<!-- 					<span></span>继续添加 -->
				</div>
				
			</div>
		 	<div id="main" role="main">
				<textarea id="form-content" class="editor" cols="30" rows="10">
		        </textarea>
	        </div>
	        <div class="buttions">
	           <div class="btns">
		           <div id="filePicker2"></div><div class="uploadBtn begin">开始上传</div>
		       </div>
	           <div class="btns">
		           <div id="filePicker2"></div><div class="uploadBtn cancel">取消</div>
		       </div>
	        </div>
	       
		</div>

	</div>

</body>
<!-- <div class="progress">
	<span class="text">0%</span> <span class="percentage"></span>
</div>
<div class="info"></div>
<div class="btns">
	<div></div>
	<div class="uploadBtn">开始上传</div> 
</div>-->
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/webuploader/webuploader.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/upload.js"></script>

<script src="${ctxStatic}/trumbowyg-1.0/trumbowyg/trumbowyg.js"></script>
<script src="${ctxStatic}/trumbowyg-1.0/trumbowyg/langs/fr.js"></script>
<script src="${ctxStatic}/trumbowyg-1.0/trumbowyg/plugins/upload/trumbowyg.upload.js"></script>
<script src="${ctxStatic}/trumbowyg-1.0/trumbowyg/plugins/base64/trumbowyg.base64.js"></script>
<script type="text/javascript">
/* http://demo.qingcms.com/ */
/* 网站模板：http://www.qifeiye.com/ */
 /* http://news.ifeng.com/photo/yizhousaojietu/detail_2011_12/25/11555699_0.shtml */
 /* 插件集：http://www.cnblogs.com/lhb25/archive/2011/04/11/2005330.html
         大图插件：http://www.yyyweb.com/demo/fancybox/ 
 		 http://www.yyyweb.com/demo/lightbox/
 */
// 	$(function(){
// 		//上传图片
// 		 // 初始化Web Uploader
// 		var uploader = WebUploader.create({
// 		    // 选完文件后，是否自动上传。
// 		    auto: false,

// 		    // swf文件路径
// 		    swf: '${ctxStatic}/webuploader/Uploader.swf',
// 		    // 文件接收服务端。
// 		    server: '${ctx}/mytest/upload',

// 		    // 选择文件的按钮。可选。
// 		    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
// 		    pick: '#imgPicker',
// 		    chunked: true, //是否要分片处理大文件上传
// 		    chunkSize:2*1024*1024 //分片上传，每片2M，默认是5M
// 		    // 只允许选择图片文件。
// // 		    accept: {
// // 		        title: 'Images',
// // 		        extensions: 'gif,jpg,jpeg,bmp,png',
// // 		        mimeTypes: 'image/*'
// // 		    }
// 		});


// 	})


/***********************************富文本编辑区**********************************/
	  $('#simple-editor').trumbowyg();
      $.trumbowyg.btnsGrps.test = ['bold', 'link'];
      /* Add new words for customs btnsDef just below */

      $.extend(true, $.trumbowyg.langs, {
          fr: {
              align: 'Alignement',
              image: 'Image'
          }
      });

      $('#customized-buttonpane').trumbowyg({
          lang: 'fr',
          closable: true,
          fixedBtnPane: true,
          btnsDef: {
              // Customizables dropdowns
              align: {
                  dropdown: ['justifyLeft', 'justifyCenter', 'justifyRight', 'justifyFull'],
                  ico: 'justifyLeft'
              },
              image: {
                  dropdown: ['insertImage', 'upload', 'base64'],
                  ico: 'insertImage'
              }
          },
          btns: ['viewHTML',
              '|', 'formatting',
              '|', 'btnGrp-test',
              '|', 'align',
              '|', 'btnGrp-lists',
              '|', 'image']
      });

      /** Simple customization with current options **/
      $('#form-content').trumbowyg({
          lang: 'fr',
          closable: true,
          mobile: true,
          fixedBtnPane: true,
          fixedFullWidth: true,
          semantic: true,
          resetCss: true,
          autoAjustHeight: true,
          autogrow: true
      });

      $('.editor').on('dblclick', function(e){
          $(this).trumbowyg({
              lang: 'fr',
              closable: true,
              fixedBtnPane: true
          });
      });
            

</script>
</html>