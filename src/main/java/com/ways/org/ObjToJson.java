package com.ways.org;

import com.alibaba.fastjson.JSON;
import com.ways.constants.VayConst;
import com.ways.param.UserBlogParam;

public class ObjToJson {
	
	public static String ToJson(){
		UserBlogParam param = new UserBlogParam();
		/**文章*/
		param.setType(VayConst.BLOG_TYPE_ESSAY);
		param.setTitle("我很忙，但对你随时有空");
		param.setMsg("有的人，你发了多少微信他都不回，转头却看见他在发朋友圈。他不是没时间，只是懒得为你消磨时间；\n\n有的人，你有丁点儿大的动静他就立刻注意到关心到。他不是特别闲，只是时时把你放心上。\n\n记住，真正在乎你的人不会总说我很忙。面对自己心心念念的人，知道关于她的一点风吹草动都很关切，怎么还会高冷地说自己很忙。那些总是说自己很忙没空理你的人，只是不愿意为你空出那几十秒而已，只是没有把你放在心上。\n\n在感情里，一个人把你放在心上，就会愿意在你身上花时间；如果他很忙，就会挤出时间来陪你。\n\n有的人会说，人要工作，要生计，哪来大把的时间陪你？可是这个世界，谁不忙呢？你不忙吗？\n\n时间，是金钱，多宝贵啊。但是正因为时间宝贵，所以才验证了人心。因为心里有你，不论什么时候，他都愿意在你身上付出时间：去赴一次约，去打一通电话，或者去发一条短信。如果你身边有这样一个人，请你一定要好好珍惜他。\n\n珍惜身边那个随时为你有空的人吧。其实他和别人一样，也会很忙，也会被工作压得喘不过气来；感谢他百忙中也总是抽出时间关心你。珍惜身边那个随时为你有空的人吧，不管是爱人、朋友还是家人。正是他们的陪伴，才让我们一直感到温暖。​​​​");
		String json = JSON.toJSONString(param);
		
		param.setType(VayConst.BLOG_TYPE_IMAGES);
		param.setMsg("我爱你似春风一般的暖阳~");
		String [] imgLink ={"120.77.176.118/images/woman.png"};
		param.setImgLink(imgLink);
		return json;
		
	}

//	public static Object Toobj(){
//		org.codehaus.jackson.map.ObjectMapper mapper = new ObjectMapper(); 
//		mapper.readValue(src, valueType)
//		return null;
//	}
	public static void main(String[] args) {
		ToJson();
	}
}
