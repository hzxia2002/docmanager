package com.comet.core.controller;

import com.comet.core.controller.BaseMultiActionController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 13-4-27
 * Time: 下午5:30
 * To change this template use File | Settings | File Templates.
 */
public class BaseMobileController extends BaseMultiActionController {
    private static final Logger log = LoggerFactory
            .getLogger(BaseMobileController.class);

    /**
     * 发送XML字符串
     *
     * @param response
     * @param xml
     */
    public void sendXml(HttpServletResponse response, CharSequence xml) {
        sendXml(response, xml);
    }

    /**
     * 发送XML字符串
     *
     * @param response
     * @param xml
     */
    public void sendXml(HttpServletResponse response, String xml) {
        response.setContentType(HTML_CONTENT_TYPE);
        try {
            response.getWriter().write(xml);
            log.debug("response output:" + xml);
        } catch (IOException e) {
            log.error("export JSON 出错：" + e.getMessage());
            if (log.isDebugEnabled()) {
                log.debug("", e);
            }
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
