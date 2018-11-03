package com.cjwx.titan.server.handler;

import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.engine.web.annotation.RestHandler;
import com.cjwx.titan.engine.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: qian li
 * @Date: 2018年08月01日 16:12
 */
@RestHandler("微信公众号接口")
@RequestMapping(value = "/system/weixin/", method = RequestMethod.POST)
public class WeixinHandler {

    @Resource
    protected WxMpConfigStorage wxMpConfigStorage;
    @Resource
    protected WxMpService wxMpService;
    @Resource
    protected WxMpMessageRouter wxMpMessageRouter;

    @ApiOperation(value = "路由", notes = "路由", httpMethod = "POST")
    @RequestMapping("route")
    public String route(HttpServletRequest request, @RequestBody Model model) {
        WxMenu wxMenu = new WxMenu();
        try {
            wxMpService.menuCreate(wxMenu);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        WxMpMessageHandler handler1 = (WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) ->
                WxMpXmlOutMessage.TEXT().content("哈哈哈").fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();

        WxMpMessageHandler handler2 = (WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) ->
                WxMpXmlOutMessage.TEXT().content("呵呵呵").fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();

        wxMpMessageRouter.rule().async(false).content("哈哈").handler(handler1).next();
        wxMpMessageRouter.rule().async(false).content("呵呵").handler(handler2).end();

        try {
            String signature = model.getString("signature");
            String nonce = model.getString("nonce");
            String timestamp = model.getString("timestamp");

            // 验证消息签名合法性
            if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
                return "非法请求";
            }

            String echostr = model.get("echostr").toString();

            if (StringUtils.isNotEmpty(echostr)) {
                return echostr;
            }

            String encryptType = StringUtils.isEmpty(model.getString("encrypt_type")) ? "raw" : model.getString("encrypt_type");
            if ("raw".equals(encryptType)) {
                // 明文传输的消息
                WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
                WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
                return outMessage.toXml();
            } else if ("aes".equals(encryptType)) {
                // 是aes加密的消息
                String msgSignature = model.getString("msg_signature");
                WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), wxMpConfigStorage, timestamp, nonce, msgSignature);
                WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
                return outMessage.toEncryptedXml(wxMpConfigStorage);
            } else {
                wxMpService.oauth2buildAuthorizationUrl(WxConsts.OAUTH2_SCOPE_USER_INFO, null);
                return "不可识别的加密类型";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello";
    }

}
