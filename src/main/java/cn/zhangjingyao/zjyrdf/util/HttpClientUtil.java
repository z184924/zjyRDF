package cn.zhangjingyao.zjyrdf.util;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HttpClient工具类
 *
 * @author zjy
 */
public class HttpClientUtil {

    private static Logger logger = LogManager.getLogger(HttpClientUtil.class);

    public static final String ACCEPT = "accept";
    public static final String ACCEPT_CHARSET = "accept-charset";
    public static final String ACCEPT_ENCODING = "accept-encoding";
    public static final String ACCEPT_LANGUAGE = "accept-language";
    public static final String ACCEPT_RANGES = "accept-ranges";
    public static final String ACCEPT_PATCH = "accept-patch";
    public static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "access-control-allow-credentials";
    public static final String ACCESS_CONTROL_ALLOW_HEADERS = "access-control-allow-headers";
    public static final String ACCESS_CONTROL_ALLOW_METHODS = "access-control-allow-methods";
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "access-control-allow-origin";
    public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "access-control-expose-headers";
    public static final String ACCESS_CONTROL_MAX_AGE = "access-control-max-age";
    public static final String ACCESS_CONTROL_REQUEST_HEADERS = "access-control-request-headers";
    public static final String ACCESS_CONTROL_REQUEST_METHOD = "access-control-request-method";
    public static final String AGE = "age";
    public static final String ALLOW = "allow";
    public static final String AUTHORIZATION = "authorization";
    public static final String CACHE_CONTROL = "cache-control";
    public static final String CONNECTION = "connection";
    public static final String CONTENT_BASE = "content-base";
    public static final String CONTENT_ENCODING = "content-encoding";
    public static final String CONTENT_LANGUAGE = "content-language";
    public static final String CONTENT_LENGTH = "content-length";
    public static final String CONTENT_LOCATION = "content-location";
    public static final String CONTENT_TRANSFER_ENCODING = "content-transfer-encoding";
    public static final String CONTENT_DISPOSITION = "content-disposition";
    public static final String CONTENT_MD5 = "content-md5";
    public static final String CONTENT_RANGE = "content-range";
    public static final String CONTENT_SECURITY_POLICY = "content-security-policy";
    public static final String CONTENT_TYPE = "content-type";
    public static final String COOKIE = "cookie";
    public static final String DATE = "date";
    public static final String ETAG = "etag";
    public static final String EXPECT = "expect";
    public static final String EXPIRES = "expires";
    public static final String FROM = "from";
    public static final String HOST = "host";
    public static final String IF_MATCH = "if-match";
    public static final String IF_MODIFIED_SINCE = "if-modified-since";
    public static final String IF_NONE_MATCH = "if-none-match";
    public static final String IF_RANGE = "if-range";
    public static final String IF_UNMODIFIED_SINCE = "if-unmodified-since";
    /**
     * @deprecated
     */
    @Deprecated
    public static final String KEEP_ALIVE = "keep-alive";
    public static final String LAST_MODIFIED = "last-modified";
    public static final String LOCATION = "location";
    public static final String MAX_FORWARDS = "max-forwards";
    public static final String ORIGIN = "origin";
    public static final String PRAGMA = "pragma";
    public static final String PROXY_AUTHENTICATE = "proxy-authenticate";
    public static final String PROXY_AUTHORIZATION = "proxy-authorization";
    /**
     * @deprecated
     */
    @Deprecated
    public static final String PROXY_CONNECTION = "proxy-connection";
    public static final String RANGE = "range";
    public static final String REFERER = "referer";
    public static final String RETRY_AFTER = "retry-after";
    public static final String SEC_WEBSOCKET_KEY1 = "sec-websocket-key1";
    public static final String SEC_WEBSOCKET_KEY2 = "sec-websocket-key2";
    public static final String SEC_WEBSOCKET_LOCATION = "sec-websocket-location";
    public static final String SEC_WEBSOCKET_ORIGIN = "sec-websocket-origin";
    public static final String SEC_WEBSOCKET_PROTOCOL = "sec-websocket-protocol";
    public static final String SEC_WEBSOCKET_VERSION = "sec-websocket-version";
    public static final String SEC_WEBSOCKET_KEY = "sec-websocket-key";
    public static final String SEC_WEBSOCKET_ACCEPT = "sec-websocket-accept";
    public static final String SEC_WEBSOCKET_EXTENSIONS = "sec-websocket-extensions";
    public static final String SERVER = "server";
    public static final String SET_COOKIE = "set-cookie";
    public static final String SET_COOKIE2 = "set-cookie2";
    public static final String TE = "te";
    public static final String TRAILER = "trailer";
    public static final String TRANSFER_ENCODING = "transfer-encoding";
    public static final String UPGRADE = "upgrade";
    public static final String USER_AGENT = "user-agent";
    public static final String VARY = "vary";
    public static final String VIA = "via";
    public static final String WARNING = "warning";
    public static final String WEBSOCKET_LOCATION = "websocket-location";
    public static final String WEBSOCKET_ORIGIN = "websocket-origin";
    public static final String WEBSOCKET_PROTOCOL = "websocket-protocol";
    public static final String WWW_AUTHENTICATE = "www-authenticate";
    public static final String X_FRAME_OPTIONS = "x-frame-options";

    /**
     * 发送request
     *
     * @param httpClient httpClient
     * @param request    request
     * @return String Response
     */
    private static String executeRequest(HttpClient httpClient, HttpRequestBase request) {
        try {
            HttpResponse response = httpClient.execute(request);
            //请求发送成功，并得到响应
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                //读取服务器返回过来的json字符串数据
                return EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            } else {
                logger.error("请求返回:" + statusCode + "(" + request.getURI().toString() + ")");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get请求
     *
     * @param url     url
     * @param params  params
     * @param headers headers
     * @return String Response
     */
    public static String doGet(String url, Map<String, String> params, Map<String, String> headers) {
        // 定义HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //发送get请求
        HttpGet request = new HttpGet(url);
        request.setHeader(CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        if (headers != null) {
            headers.forEach(request::setHeader);
        }
        if (params != null) {
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            params.forEach((key, value) -> {
                nameValuePairs.add(new BasicNameValuePair(key, value));
            });
            String paramString = URLEncodedUtils.format(nameValuePairs, Consts.UTF_8);
            request.setURI(URI.create(url + "?" + paramString));
        }
        return executeRequest(httpClient, request);
    }

    /**
     * get请求
     *
     * @param url url
     * @return String Response
     */
    public static String doGet(String url) {
        return HttpClientUtil.doGet(url, null, null);
    }

    /**
     * post请求
     *
     * @param url     url
     * @param entity  request entity
     * @param headers headers
     * @return String Response
     */
    public static String doPost(String url, HttpEntity entity, Map<String, String> headers) {
        // 定义HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 实例化HTTP方法
        HttpPost request = new HttpPost(url);
        request.setHeader(CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        if (headers != null) {
            headers.forEach(request::setHeader);
        }
        //设置参数
        request.setEntity(entity);
        return executeRequest(httpClient, request);
    }

    /**
     * post请求(用于UrlEncodedForm表单)
     *
     * @param url     url
     * @param params  params
     * @param headers headers
     * @return String Response
     */
    public static String doPost(String url, Map<String, String> params, Map<String, String> headers) {
        //设置参数
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        params.forEach((key, value) -> {
            nameValuePairs.add(new BasicNameValuePair(key, value));
        });
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs, StandardCharsets.UTF_8);
        if (headers == null) {
            headers = new HashMap<>(1);
        }
        headers.put(CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.withCharset(Consts.UTF_8).toString());
        return HttpClientUtil.doPost(url, urlEncodedFormEntity, headers);
    }

    /**
     * post请求(用于UrlEncodedForm表单)
     *
     * @param url     url
     * @param params  params
     * @param headers headers
     * @return String Response
     */
    public static String doPost(String url, Map<String, String> params, Map<String, File> fileParams, Map<String, String> headers) {
        //设置参数
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        params.forEach(multipartEntityBuilder::addTextBody);
        fileParams.forEach(multipartEntityBuilder::addBinaryBody);
        HttpEntity httpEntity = multipartEntityBuilder.build();
        if (headers == null) {
            headers = new HashMap<>(1);
        }
        headers.put(CONTENT_TYPE, ContentType.MULTIPART_FORM_DATA.withCharset(Consts.UTF_8).toString());
        return HttpClientUtil.doPost(url, httpEntity, headers);
    }

    /**
     * post请求携带自定义Header（用于请求json格式的参数）
     *
     * @param url     url
     * @param params  params
     * @param headers headers
     * @return String Response
     */
    public static String doPost(String url, String params, Map<String, String> headers) {
        StringEntity entity = new StringEntity(params, Consts.UTF_8);
        return HttpClientUtil.doPost(url, entity, headers);
    }

}
