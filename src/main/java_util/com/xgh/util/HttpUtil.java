package com.xgh.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 模拟进行Http请求
 *
 * @author：段晓刚
 * @update：2014年6月12日 下午6:18:34
 * @Email：
 */
public final class HttpUtil {

    private static RequestConfig.Builder getRequestConfig() {

        RequestConfig.Builder config = RequestConfig.custom();
        //连接超时 5秒
        config.setConnectTimeout(3000);
        //请求超时 5秒
        config.setSocketTimeout(15000);
        return config;
    }

    /**
     * 模拟使用get请求
     * 多参数数据提交
     *
     * @param url
     * @param params Map中的键值作为键值使用
     * @return
     * @update：2014年7月24日 下午4:35:55
     */
    public static String doGet(String url, Map<String, Object> params) {
        // TODO Auto-generated method stub
        String paramstr = "";
        if (params != null && !params.isEmpty()) {
            Set<Map.Entry<String, Object>> set = params.entrySet();
            Iterator<Map.Entry<String, Object>> it = set.iterator();

            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                if (paramstr.equals("")) {
                    paramstr = entry.getKey() + "=" + entry.getValue();
                } else {
                    paramstr += "&" + entry.getKey() + "=" + entry.getValue();
                }
            }
        }
        // 新建HttpPost对象
        HttpGet httpGet = new HttpGet(paramstr.equals("") ? url : url + "?" + paramstr);
        // 获取HttpClient对象
        HttpClient httpClient = HttpClients.createDefault();//new DefaultHttpClient();

        httpGet.setConfig(getRequestConfig().build());

        // 获取HttpResponse实例
        HttpResponse httpResp = null;
        try {
            httpResp = httpClient.execute(httpGet);
        } catch (ClientProtocolException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return getResult(httpResp);
    }

    /**
     * 模拟使用post请求
     * 多参数数据提交
     *
     * @param url
     * @param params Map中的键值作为键值使用
     * @return
     * @update：2014年7月24日 下午4:36:30
     */
    public static String doPost(String url, Map<String, Object> params) {
        // TODO Auto-generated method stub

        // 新建HttpPost对象
        HttpPost httpPost = new HttpPost(url);

        List<NameValuePair> params2 = new ArrayList<NameValuePair>();
        if (params != null && !params.isEmpty()) {
            Set<Map.Entry<String, Object>> set = params.entrySet();
            Iterator<Map.Entry<String, Object>> it = set.iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                Object value = entry.getValue();
                params2.add(new BasicNameValuePair(entry.getKey(), JSONUtil.getJson(entry.getValue())));
            }
        }
        // 设置字符集
        HttpEntity entity = new UrlEncodedFormEntity(params2, Charset.forName("UTF-8"));

        // 设置参数实体
        httpPost.setEntity(entity);
        // 获取HttpClient对象
        HttpClient httpClient = HttpClients.createDefault();//new DefaultHttpClient();

        httpPost.setConfig(getRequestConfig().build());

        // 获取HttpResponse实例
        HttpResponse httpResp = null;
        try {
            httpResp = httpClient.execute(httpPost);
        } catch (ClientProtocolException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // 判断是够请求成功

        return getResult(httpResp);
    }


    /**
     * 模拟使用post请求
     * 多参数数据提交
     *
     * @param url
     * @param params Map中的键值作为键值使用
     * @return
     * @update：2014年7月24日 下午4:36:30
     */
    public static String doPost(String url, String params) {
        // TODO Auto-generated method stub
        // 新建HttpPost对象
        HttpPost httpPost = new HttpPost(url);

        httpPost.setEntity(new StringEntity(params, "utf-8"));

        // 获取HttpClient对象
        HttpClient httpClient = HttpClients.createDefault();//new DefaultHttpClient();

        httpPost.setConfig(getRequestConfig().build());

        // 获取HttpResponse实例
        HttpResponse httpResp = null;
        try {
            httpResp = httpClient.execute(httpPost);
        } catch (ClientProtocolException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        return getResult(httpResp);
    }

    private static String getResult(HttpResponse httpResp) {
        String serverResponse = "";
        if (httpResp != null) {
            // 判断是够请求成功
            if (httpResp.getStatusLine().getStatusCode() == 200) {
                // 获取返回的数据
                try {
                    serverResponse = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
            }

        }

        return serverResponse;
    }
}