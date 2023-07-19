package com.steven.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
public class HttpUtil {
    private static final int SocketTimeout = 10000;// 10秒
    private static final int ConnectTimeout = 10000;// 10秒
    private static final int RequestTimeout = 10000;// 10秒

    private static CloseableHttpClient createSSLInsecureClient() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (TrustStrategy) (arg0, arg1) -> true).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        } catch (KeyStoreException e) {
            log.error(e.getMessage(), e);
        }
        return HttpClients.createDefault();
    }

    private static List<NameValuePair> buildParam(Map<String, String> params) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (params != null && params.keySet().size() > 0) {
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        return nvps;
    }

    public static String get(String url, Map<String, String> params, Map<String, String> header) {
        return get(url, params, header, SocketTimeout);
    }

    public static String get(String url, Map<String, String> params, Map<String, String> header, int socketTimeout) {
        String result = "";
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient;
        if (url != null && url.toLowerCase().startsWith("https")) {
            closeableHttpClient = createSSLInsecureClient();
        } else {
            closeableHttpClient = httpClientBuilder.build();
        }
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(ConnectTimeout).build();

        try {
            if (!url.contains("?")) {
                url = url + "?";
            }
            if (url.contains("&") && !url.endsWith("&")) {
                url = url + "&";
            }
            String address = url + URLEncodedUtils.format(buildParam(params), "UTF-8");

            HttpGet httpGet = new HttpGet(address);

            httpGet.setConfig(requestConfig);
            if (header == null || header.size() < 1) {
                httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
            } else {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }
            }
            HttpResponse httpResponse = closeableHttpClient.execute(httpGet);

            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
                EntityUtils.consume(entity);
            }
            closeableHttpClient.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public static String post(String url, Object params, Map<String, String> header) throws Exception {
        return post(url, params, header, SocketTimeout);
    }

    public static String post(String url, Object params, Map<String, String> header, int socketTimeout) throws Exception {
        String result = "";
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient;
        if (url != null && url.toLowerCase().startsWith("https")) {
            closeableHttpClient = createSSLInsecureClient();
        } else {
            closeableHttpClient = httpClientBuilder.build();
        }
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(ConnectTimeout).build();
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity;
        if (params instanceof String) {
            stringEntity = new StringEntity((String) params, "utf-8");
        } else {
            stringEntity = new StringEntity(JsonUtil.getInstance()
                    .write(params), "utf-8");
        }
        httpPost.setEntity(stringEntity);
        if (header == null || header.size() < 1) {
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        } else {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        httpPost.setConfig(requestConfig);
        HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
        StatusLine statusLine = httpResponse.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        if (statusCode == HttpStatus.SC_OK
                || statusCode == HttpStatus.SC_PARTIAL_CONTENT) {
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
                EntityUtils.consume(entity);
            }
            closeableHttpClient.close();
        } else {
            throw new Exception("bad request:statusCode=" + statusCode);
        }
        return result;
    }

    public static String sendPostForm(String url, Map<String, String> params, Map<String, String> header ) throws Exception {
        String result = "";
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient;
        if (url != null && url.toLowerCase().startsWith("https")) {
            closeableHttpClient = createSSLInsecureClient();
        } else {
            closeableHttpClient = httpClientBuilder.build();
        }
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(SocketTimeout)
                .setConnectTimeout(ConnectTimeout).build();
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity;

        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (Objects.nonNull(params) && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (log.isDebugEnabled()) {
                    log.debug(entry.getKey() + ":" + entry.getValue());
                }
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, Charset.forName("UTF-8")));
        //httpPost.setEntity(stringEntity);
        if (header == null || header.size() < 1) {
            httpPost.addHeader("Content-type", "application/x-www-form-urlencoded");
        } else {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        httpPost.setConfig(requestConfig);
        HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
        StatusLine statusLine = httpResponse.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        if (statusCode == HttpStatus.SC_OK
                || statusCode == HttpStatus.SC_PARTIAL_CONTENT) {
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
                EntityUtils.consume(entity);
            }
            closeableHttpClient.close();
        } else {
            throw new Exception("bad request:statusCode=" + statusCode);
        }
        return result;
    }


}
