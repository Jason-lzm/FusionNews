package com.lzm.fusionnews.net;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by lzm on 2017-9-21.
 */

public class RequestManager {
    private OkHttpClient mOkHttpClient;
    private static RequestManager mInstance;

    /**
     * construction a RequestManager
     * init the OkhttpClient
     */
    RequestManager() {
        mOkHttpClient =new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    /**
     * return a instance of the RequestManager
     *
     * @return
     */
    public static RequestManager getInstance() {
        if (mInstance == null) {
            synchronized (RequestManager.class) {
                if (mInstance == null) {
                    mInstance = new RequestManager();
                }
            }
        }
        return mInstance;
    }

    public Response getSync(RequestParams params) throws IOException{
        if (!validateParams(params)) {
            return null;
        }

        return mOkHttpClient.newCall(buildGetRequest(params)).execute();
    }

    public void getAsync(RequestParams params, final okhttp3.Callback listener) {
        if (!validateParams(params)) {
            Timber.e("invalid params");
            return;
        }

        Timber.d("getAsync url:" + params.getUrl());
        addRequest(buildGetRequest(params), listener);
    }

    /**
     * Perform a Post request immediately, and blocks until the response can be processed or is in error
     *
     */
    public Response postSync(RequestParams params) throws IOException {
        if (!validateParams(params)) {
            return null;
        }

        return mOkHttpClient.newCall(buildPostRequest(params)).execute();
    }

    public void postAsync(RequestParams params, final okhttp3.Callback listener) {
        if (!validateParams(params)) {
            Timber.e("invalid params");
            return;
        }

        addRequest(buildPostRequest(params), listener);
    }

    private boolean validateParams(RequestParams params) {
        if (params == null) {
            throw new IllegalArgumentException("params can't be empty,at last has a url");
        }

        if (params.getUrl() == null || params.getUrl().length() <= 0) {
            throw new IllegalArgumentException("request url can't be empty");
        }
        return true;
    }

    /**
     * add a request to the httpclient
     *
     * @param request
     * @param callback
     */
    void addRequest(Request request, okhttp3.Callback callback) {
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    public Request buildGetRequest(RequestParams params) {
        Request.Builder builder = new Request.Builder();
        if (TextUtils.isEmpty(params.getUrl())) {
            throw new IllegalArgumentException("request url can't be empty or null!");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(params.getUrl());
        if (params.getParams() != null) {
            sb.append("?");

            HashMap<String, String> mapParams = (HashMap<String, String>) params.getParams();
            Iterator<Map.Entry<String, String>> iterator = mapParams.entrySet().iterator();

            int len = mapParams.size();
            int i = 1;
            while (iterator.hasNext()) {
                Map.Entry<String, String> item = iterator.next();
                sb.append(item.getKey() + "=" + item.getValue());
                if (i < len) {
                    sb.append("&");
                }
                i++;
            }
        }
        builder.url(sb.toString());
        Timber.d( "get url:" + sb.toString());
        if (params.getTag() != null) {
            builder.tag(params.getTag());
        }
        return builder.build();
    }

    private Request buildPostRequest(RequestParams params){
        if (TextUtils.isEmpty(params.getUrl())) {
            throw new IllegalArgumentException("request url can't be empty or null!");
        }
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        RequestBody requestBody = null;
        if (params.getParams() != null) {
            HashMap<String, String> mapParams = (HashMap<String, String>) params.getParams();
            Iterator<Map.Entry<String, String>> iterator = mapParams.entrySet().iterator();
            Map.Entry<String, String> item;
            while (iterator.hasNext()) {
                item = iterator.next();
                if (!TextUtils.isEmpty(item.getKey())) {
                    formBodyBuilder.add(item.getKey(), item.getValue());
                }
            }
            requestBody = formBodyBuilder.build();
        }


        Request.Builder requestBuilder = new Request.Builder();
        if (params.getHeaders() != null) {
            Iterator iterator = params.getHeaders().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry item = (Map.Entry)iterator.next();
                requestBuilder.addHeader((String)item.getKey(), (String)item.getValue());
            }
        }

        requestBuilder.url(params.getUrl()).post(requestBody);
        if (params.getTag() != null) {
            requestBuilder.tag(params.getTag());
        }
        return requestBuilder.build();
    }
}
