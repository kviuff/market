package com.wechat;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.market.maicheng.common.utils.DateUtils;
import com.market.maicheng.model.Merchant;
import com.wechat.common.WeChatConstat;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

public class WeChatPush {
	private static WxMpService wxMpService;
	
	/**
     * 微信模板消息--给商户的订单提醒
     * @param map   map
     * @return  String
     * @throws WxErrorException
     */
    public static String deliverTemplateSendToStoreForOrder(Map<String, Object> map) throws WxErrorException {
        String result = "";
        if(map.get("openId") != null){
            WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
            config.setAppId(WeChatConstat.APP_ID); //设置微信公众号的appId
            config.setSecret(WeChatConstat.APP_SECRET); //设置微信公众号的appSecret
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(config);
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(map.get("openId").toString())
                    .templateId("_IjA2-8gHu8anxeC5CcVummejx2ITptxeP8ejSBL9dw")
                    .build();
            templateMessage.addData(new WxMpTemplateData("first", map.get("first").toString()));
            templateMessage.addData(new WxMpTemplateData("keyword1", map.get("ordersn").toString()));      // 订单编号
            templateMessage.addData(new WxMpTemplateData("keyword2", map.get("ordergoods").toString()));   // 订单商品
            templateMessage.addData(new WxMpTemplateData("keyword3", map.get("orderamount").toString()));  // 订单金额
            //templateMessage.addData(new WxMpTemplateData("keyword4", map.get("paymenttype").toString()));  // 交易方式
            templateMessage.addData(new WxMpTemplateData("keyword5", map.get("memberinfo").toString()));   // 会员信息
            templateMessage.addData(new WxMpTemplateData("remark", map.get("remark").toString()));
            result =  wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            System.out.println("微信推送发送返回信息:" + result);
        } else {
        		System.out.println("微信推送参数有错!");
        }
        return result;
    }
    
    /**
     * 微信模板消息--商户入驻申请通知
     * @param map   map
     * @return  String
     * @throws WxErrorException
     */
    public static String deliverTemplateSendToForApplyStore(Map<String, Object> map) throws WxErrorException {
        String result = "";
        if(map.get("openId") != null){
            WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
            config.setAppId(WeChatConstat.APP_ID); //设置微信公众号的appId
            config.setSecret(WeChatConstat.APP_SECRET); //设置微信公众号的appSecret
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(config);
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(map.get("openId").toString())
                    .templateId("OuSMp2ZiZcJ8tXAOCuw9V-FPbPDdX0KW6kGhUPJImz8")
                    .build();
            templateMessage.addData(new WxMpTemplateData("first", map.get("first").toString()));
            templateMessage.addData(new WxMpTemplateData("keyword1", map.get("storename").toString())); //商户名称
            templateMessage.addData(new WxMpTemplateData("keyword2", map.get("area").toString())); //所属区域
            templateMessage.addData(new WxMpTemplateData("keyword3", map.get("applytime").toString())); //申请时间
            templateMessage.addData(new WxMpTemplateData("keyword4", map.get("applyinfo").toString())); //申请信息
            templateMessage.addData(new WxMpTemplateData("remark", map.get("remark").toString()));
            result =  wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            System.out.println("微信推送发送返回信息:" + result);
        } else {
        		System.out.println("微信推送参数有错!");
        }
        return result;
    }
    
    /**
     * 微信模板消息--商户入驻审核通过
     * @param map   map
     * @return  String
     * @throws WxErrorException
     */
    public static String deliverTemplateSendToForApplyStoreForSuccess(Map<String, Object> map) throws WxErrorException {
        String result = "";
        if(map.get("openId") != null){
            WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
            config.setAppId(WeChatConstat.APP_ID); //设置微信公众号的appId
            config.setSecret(WeChatConstat.APP_SECRET); //设置微信公众号的appSecret
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(config);
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(map.get("openId").toString())
                    .templateId("Doeszfh08GFecKU9OVemh5Y-oU0SfuCufAkmZZlaZCs")
                    .build();
            templateMessage.addData(new WxMpTemplateData("first", map.get("first").toString()));
            templateMessage.addData(new WxMpTemplateData("keyword1", map.get("storename").toString())); //企业名称
            templateMessage.addData(new WxMpTemplateData("keyword2", map.get("info").toString())); //入驻状态
            templateMessage.addData(new WxMpTemplateData("remark", map.get("remark").toString()));
            result =  wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            System.out.println("微信推送发送返回信息:" + result);
        } else {
        		System.out.println("微信推送参数有错!");
        }
        return result;
    }
    
    /**
     * 微信模板消息--商户入驻审核失败
     * @param map   map
     * @return  String
     * @throws WxErrorException
     */
    public static String deliverTemplateSendToForApplyStoreForError(Map<String, Object> map) throws WxErrorException {
        String result = "";
        if(map.get("openId") != null){
            WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
            config.setAppId(WeChatConstat.APP_ID); //设置微信公众号的appId
            config.setSecret(WeChatConstat.APP_SECRET); //设置微信公众号的appSecret
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(config);
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(map.get("openId").toString())
                    .templateId("RLk7_-IneCKv4rwR_b42g8SVyQvIRLl0C4Z-IomhTsA")
                    .build();
            templateMessage.addData(new WxMpTemplateData("first", map.get("first").toString()));
            templateMessage.addData(new WxMpTemplateData("keyword1", map.get("storename").toString())); //账号
            templateMessage.addData(new WxMpTemplateData("keyword2", map.get("info").toString())); //内容
            templateMessage.addData(new WxMpTemplateData("keyword3", map.get("applytime").toString())); //时间
            templateMessage.addData(new WxMpTemplateData("remark", map.get("remark").toString()));
            result =  wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            System.out.println("微信推送发送返回信息:" + result);
        } else {
        		System.out.println("微信推送参数有错!");
        }
        return result;
    }
    
    /**
     * 微信模板消息--申请价格
     * @param map   map
     * @return  String
     * @throws WxErrorException
     */
    public static String deliverTemplateSendToStoreForApplyPrice(Map<String, Object> map) throws WxErrorException {
        String result = "";
        if(map.get("openId") != null){
            WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
            config.setAppId(WeChatConstat.APP_ID); //设置微信公众号的appId
            config.setSecret(WeChatConstat.APP_SECRET); //设置微信公众号的appSecret
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(config);
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(map.get("openId").toString())
                    .templateId("wtes9DrruMVhNKbcJ3avndmFvd_lPn8LmSZ329iculU")
                    .build();
            templateMessage.addData(new WxMpTemplateData("first", map.get("first").toString()));
            templateMessage.addData(new WxMpTemplateData("keyword1", map.get("name").toString())); // 查看人
            templateMessage.addData(new WxMpTemplateData("keyword2", map.get("accountnumber").toString()));// 查看账号
            templateMessage.addData(new WxMpTemplateData("keyword3", map.get("applytime").toString())); // 申请时间
            templateMessage.addData(new WxMpTemplateData("remark", map.get("remark").toString()));
            result =  wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            System.out.println("微信推送发送返回信息:" + result);
        } else {
        		System.out.println("微信推送参数有错!");
        }
        return result;
    }
    
    /**
     * 微信模板消息--申请价格授权状态通知
     * @param map   map
     * @return  String
     * @throws WxErrorException
     */
    public static String deliverTemplateSendToStoreForApplyPriceForResult(Map<String, Object> map) throws WxErrorException {
        String result = "";
        if(map.get("openId") != null){
            WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
            config.setAppId(WeChatConstat.APP_ID); //设置微信公众号的appId
            config.setSecret(WeChatConstat.APP_SECRET); //设置微信公众号的appSecret
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(config);
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(map.get("openId").toString())
                    .templateId("K1q198kY__c56lZmga9O1Y7GI8xWorcsaF9Zf88QHRg")
                    .build();
            templateMessage.addData(new WxMpTemplateData("first", map.get("first").toString()));
            templateMessage.addData(new WxMpTemplateData("keyword1", map.get("name").toString())); // 店铺名称
            templateMessage.addData(new WxMpTemplateData("keyword2", map.get("authorizetime").toString()));// 授权时间
            templateMessage.addData(new WxMpTemplateData("keyword3", map.get("authorizestatus").toString())); // 授权状态
            templateMessage.addData(new WxMpTemplateData("remark", map.get("remark").toString()));
            result =  wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            System.out.println("微信推送发送返回信息:" + result);
        } else {
        		System.out.println("微信推送参数有错!");
        }
        return result;
    }
    
    public static void main(String[] args){
        try {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("AppId", "wx00859ea61ad39301");
//            map.put("AppSecret", "621f7264072c8b6bd83a3fcd6170f2a8");
//            //map.put("openId", "o5rpjxJYBIbns5x1xC3nAhPuzoA0");
//            map.put("openId", "o5rpjxNlot7xA6r63rvVUNjBoTB4");
//            map.put("first", "您有新订单，请查收");
//            map.put("ordersn", "新订单");
//            map.put("ordergoods", "张三");
//            map.put("orderamount", "商品名称");
//            map.put("memberinfo", "手机");
//            map.put("remark", "请尽快发货");
//            deliverTemplateSendToStoreForOrder(map);
        	
//        		Map<String, Object> weixinmap = new HashMap<String, Object>();
//			weixinmap.put("openId", "o5rpjxNlot7xA6r63rvVUNjBoTB4");
//			weixinmap.put("first", "您有一个新的商户入驻申请");
//			weixinmap.put("storename", "111111");
//			weixinmap.put("area", "地址");
//			weixinmap.put("applytime", DateUtils.formatLongToStr(System.currentTimeMillis(), ""));
//			weixinmap.put("applyinfo", "手机号");
//			weixinmap.put("remark", "有新商家申请入驻平台，请您及时审核！");
//			deliverTemplateSendToForApplyStore(weixinmap);
        	
//        		Map<String, Object> weixinmap = new HashMap<String, Object>();
//			weixinmap.put("openId", "o5rpjxNlot7xA6r63rvVUNjBoTB4");
//			weixinmap.put("first", "您好，您的入驻申请已经审核通过。");
//			weixinmap.put("storename", "名称");
//			weixinmap.put("info", "入驻成功");
//			weixinmap.put("remark", "您好，您的入驻申请已经审核通过。");
//			WeChatPush.deliverTemplateSendToForApplyStoreForSuccess(weixinmap);
        	
//        		Map<String, Object> weixinmap = new HashMap<String, Object>();
//			weixinmap.put("openId", "o5rpjxNlot7xA6r63rvVUNjBoTB4");
//			weixinmap.put("first", "抱歉审核失败");
//			weixinmap.put("storename", "storename");
//			weixinmap.put("info", "抱歉，您的帐号审核失败，请您重新申请");
//			weixinmap.put("applytime", DateUtils.formatLongToStr(System.currentTimeMillis(), ""));
//			weixinmap.put("remark", "您的帐号审核未通过，请您重新提交。");
//			WeChatPush.deliverTemplateSendToForApplyStoreForError(weixinmap);
        	
        	
			Map<String, Object> weixinmap = new HashMap<String, Object>();
			weixinmap.put("openId", "o5rpjxNlot7xA6r63rvVUNjBoTB4");
			weixinmap.put("first", "申请查看价格通知");
			weixinmap.put("name", "name");
			weixinmap.put("authorizetime", DateUtils.formatLongToStr(System.currentTimeMillis(), ""));
			weixinmap.put("authorizestatus", "授权成功");
			weixinmap.put("remark", "您向供货商storename申请查看价格已通过商家审核！");
			WeChatPush.deliverTemplateSendToStoreForApplyPriceForResult(weixinmap);
        	
        	
        } catch (Exception we) {
            we.printStackTrace();
        }
    	
//    		String str = "{\"access_token\":\"10_XjDpRuSz9PNDeXkXpN_6BdkYdGFio2MczFvPaXZBmM5Me89Nzn9Kouinl1yshadwmxFyV4qr311yGS1EFKVdEyIs727nC8erxMtQ2I6M__qmDXwvy69rQpdyydfhAfO5J_B84L-5bWiAtXJUEQBiAJAPBM\",\"expires_in\":7200}";
//    		JSONObject jsonObject = JSON.parseObject(str);
//    		System.out.println(jsonObject.getString("access_token"));
    }
}
