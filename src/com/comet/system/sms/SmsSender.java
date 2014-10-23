package com.comet.system.sms;

import com.comet.core.utils.SpringUtils;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.http.Header;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 12-12-2
 * Time: 下午8:38
 * To change this template use File | Settings | File Templates.
 */
public class SmsSender {
    private static Log log = LogFactory.getLog(SmsSender.class);

    private String url;
    private String userId;
    private String password;

    HttpClient httpClient = null;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static SmsSender getInstance() {
        SmsSender sender = (SmsSender) SpringUtils.getBean("smsSender");

        if(sender != null) {
            return sender;
        }

        return null;
    }

    /**
     * 发送消息
     *
     * @param msg 消息内容，不同的参数之间通过|分隔
     * @param reciever 接收者，通过逗号分隔
     * @param templateId 模板ID
     * @return
     * @throws Exception
     */
    public SmsResult send(String msg, String reciever, String templateId) throws Exception {
        HttpParams params = new BasicHttpParams();
        httpClient = new DefaultHttpClient(params);

        String md5pwd = hexMD5(getPassword());//密码MD5
        StringBuffer s = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        s.append("<request><userId>");
        s.append(getUserId());//用户账号
        s.append("</userId><password>");
        s.append(md5pwd); //MD5后的密码
        s.append("</password><templateId>");
        s.append(templateId);//模版ID
        s.append("</templateId><phone>");
        s.append(reciever);//手机号码
        s.append("</phone><port>");
        s.append("");//扩展端口--我们这里不需要填写
        s.append("</port><data><![CDATA[");
        s.append(msg);//变量数据根据模板设置的参数
        s.append("]]></data><signature>");
        s.append("");//签名内容--我们这里不需要填写
        s.append("</signature></request>");

        String ret = post(getUrl(), s.toString(), md5pwd);

        return parseResult(ret);
    }

    public String hexMD5(String src) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bs = md.digest(src.getBytes("UTF-8"));
        String r = new String(Hex.encodeHex(bs));

        return r.toUpperCase();
    }

    /**
     * 发送数据
     *
     * @param url
     * @param xml
     * @param md5Pwd
     * @return
     * @throws Exception
     */
    public String post(String url, String xml, String md5Pwd)
            throws Exception {
        String ts = String.valueOf(System.currentTimeMillis() / 1000);
        String token = hexMD5(xml + ts + md5Pwd);
        String xmlLen = String.valueOf(xml.length());

        try {
            HttpPost httpPost = new HttpPost(url);
            InputStream in = new ByteArrayInputStream(xml.getBytes());
            InputStreamEntity entity = new InputStreamEntity(in, in.available());
            httpPost.setEntity(entity);
            httpPost.setHeader(HTTP.CONTENT_TYPE, "text/xml;charset=GB2312");
            httpPost.setHeader("Cmd", "mt");
            httpPost.setHeader("TS", ts);
            httpPost.setHeader("Authorization", token);
//            httpPost.setHeader(HTTP.CONTENT_LEN, xmlLen);

            Header[] headers = httpPost.getAllHeaders();

            for(Header head : headers) {
                System.out.println(head.getName() + "|" + head.getValue() + "|" + xmlLen);
                if(head.getName().equals(HTTP.CONTENT_LEN)) {
                    httpPost.removeHeader(head);
                    httpPost.setHeader(HTTP.CONTENT_LEN, xmlLen);
                }
            }

            HttpResponse response = httpClient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new Exception(response.getStatusLine().getReasonPhrase());
            }
            String result = EntityUtils.toString(response.getEntity());

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * 解析发送结果
     *
     * @param xml
     * @return
     */
    public SmsResult parseResult(String xml) {
        XStream xstream = new XStream();
        xstream.alias("response", SmsResult.class);

        SmsResult obj = (SmsResult)xstream.fromXML(xml);

        return obj;
    }
}
