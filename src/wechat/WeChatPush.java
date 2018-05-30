package wechat;

import java.util.HashMap;
import java.util.Map;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

public class WeChatPush {
	private static WxMpService wxMpService;
	private static final String APP_ID = "wx00859ea61ad39301";
	private static final String APP_SECRET = "621f7264072c8b6bd83a3fcd6170f2a8";
	
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
            config.setAppId(APP_ID); //设置微信公众号的appId
            config.setSecret(APP_SECRET); //设置微信公众号的appSecret
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(config);
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(map.get("openId").toString())
                    .templateId("jJz3VWhqs17M1f6hz1Kt2MlAscacmy2FWW8xiMVSbC0")
                    .build();
            templateMessage.addData(new WxMpTemplateData("first", map.get("first").toString()));
            templateMessage.addData(new WxMpTemplateData("orderType", map.get("orderType").toString())); //订单编号
            templateMessage.addData(new WxMpTemplateData("customerInfo", map.get("customerInfo").toString()));
            templateMessage.addData(new WxMpTemplateData("orderItemName", map.get("orderItemName").toString()));
            templateMessage.addData(new WxMpTemplateData("orderItemData", map.get("orderItemData").toString()));
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
            config.setAppId(APP_ID); //设置微信公众号的appId
            config.setSecret(APP_SECRET); //设置微信公众号的appSecret
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(config);
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(map.get("openId").toString())
                    .templateId("jJz3VWhqs17M1f6hz1Kt2MlAscacmy2FWW8xiMVSbC0")
                    .build();
            templateMessage.addData(new WxMpTemplateData("first", map.get("first").toString()));
            templateMessage.addData(new WxMpTemplateData("orderType", map.get("orderType").toString())); //订单编号
            templateMessage.addData(new WxMpTemplateData("customerInfo", map.get("customerInfo").toString()));
            templateMessage.addData(new WxMpTemplateData("orderItemName", map.get("orderItemName").toString()));
            templateMessage.addData(new WxMpTemplateData("orderItemData", map.get("orderItemData").toString()));
            templateMessage.addData(new WxMpTemplateData("remark", map.get("remark").toString()));
            result =  wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            System.out.println("微信推送发送返回信息:" + result);
        } else {
        		System.out.println("微信推送参数有错!");
        }
        return result;
    }
    
    /**
     * 微信模板消息--商户入驻
     * @param map   map
     * @return  String
     * @throws WxErrorException
     */
    public static String deliverTemplateSendToForApplyStore(Map<String, Object> map) throws WxErrorException {
        String result = "";
        if(map.get("openId") != null){
            WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
            config.setAppId(APP_ID); //设置微信公众号的appId
            config.setSecret(APP_SECRET); //设置微信公众号的appSecret
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(config);
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(map.get("openId").toString())
                    .templateId("-j0Wf7r6hKdwb2kJ6pbeL4cenRzb5oE_lg_51srzY2o")
                    .build();
            templateMessage.addData(new WxMpTemplateData("first", map.get("first").toString()));
            templateMessage.addData(new WxMpTemplateData("keyword1", map.get("applyname").toString())); //订单编号
            templateMessage.addData(new WxMpTemplateData("keyword2", map.get("applytime").toString()));
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
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("AppId", "wx00859ea61ad39301");
            map.put("AppSecret", "621f7264072c8b6bd83a3fcd6170f2a8");
            //map.put("openId", "o5rpjxJYBIbns5x1xC3nAhPuzoA0");
            map.put("openId", "o5rpjxNlot7xA6r63rvVUNjBoTB4");
            map.put("templateId", "jJz3VWhqs17M1f6hz1Kt2MlAscacmy2FWW8xiMVSbC0");
            map.put("first", "您有新订单，请查收");
            map.put("orderType", "新订单");
            map.put("customerInfo", "张三");
            map.put("orderItemName", "商品名称");
            map.put("orderItemData", "手机");
            map.put("remark", "请尽快发货");
            //deliverTemplateSend(map);
        } catch (Exception we) {
            we.printStackTrace();
        }
    }
}
