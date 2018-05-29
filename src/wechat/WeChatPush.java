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
	
	/**
     * 微信模板消息
     * @param map   map
     * @return  String
     * @throws WxErrorException
     */
    public static String deliverTemplateSend(Map<String, Object> map) throws WxErrorException {
        String result = "";
        if(map.get("AppId") != null && map.get("AppSecret") != null && map.get("openId") != null && map.get("templateId") != null){
            WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
            config.setAppId(map.get("AppId").toString()); //设置微信公众号的appId
            config.setSecret(map.get("AppSecret").toString()); //设置微信公众号的appSecret
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(config);
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(map.get("openId").toString())
                    .templateId(map.get("templateId").toString())
                    .url(map.get("url").toString()) //设置点击消息跳转的url
                    .build();
            templateMessage.addData(new WxMpTemplateData("first", map.get("first").toString(),"#8B1A1A"));
            templateMessage.addData(new WxMpTemplateData("keyword1", map.get("keyword1").toString(), "#949494")); //订单编号
            if (map.get("keyword2") != null) {
                templateMessage.addData(new WxMpTemplateData("keyword2", map.get("keyword2").toString(), "#949494")); //物流公司
            }
            if (map.get("keyword3") != null) {
                templateMessage.addData(new WxMpTemplateData("keyword3", map.get("keyword3").toString(), "#949494")); //物流单号
            }
            if (map.get("keyword4") != null) {
                templateMessage.addData(new WxMpTemplateData("keyword4", map.get("keyword4").toString(), "#949494")); //物流单号
            }
            if (map.get("keyword5") != null) {
                templateMessage.addData(new WxMpTemplateData("keyword5", map.get("keyword5").toString(), "#949494")); //物流单号
            }
            templateMessage.addData(new WxMpTemplateData("remark", map.get("remark").toString(), "#A020F0"));
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
            map.put("openId", "oS8vSwHZZIUzerlKwL2NRjBMlcV0");
            map.put("url", "http://h5.leimingtech.com/dist/home.html#/orderDetail/" + "87de447dc285494f8f210e18064294f7");
            map.put("message", "亲，宝贝已经启程了，好想快点来到你身边吖");
            map.put("orderSn", "orderSn");
            map.put("expressSn", "expressSn");
            map.put("expressName", "顺丰快递");
            deliverTemplateSend(map);
        } catch (Exception we) {
            we.printStackTrace();
        }
    }
}
