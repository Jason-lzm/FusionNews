package com.lzm.fusionnews.net;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lzm on 2017-9-21.
 * request params
 */
public class RequestParams<T> {
    private String tag;
    private String url;
    private HashMap<String, String> params;
    private HashMap<String, String> headers;
    private String contentType;

    public final HashMap<String, String> getHeaders() {
        return this.headers;
    }
    public final void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public final String getContentType() {
        return this.contentType;
    }
    public final void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public RequestParams(String tag, String url, HashMap<String, String> headers, HashMap<String, String> params, String contentType) {
        this.tag = tag;
        this.url = url;
        this.headers = headers;
        this.params = params;
        this.contentType = contentType;
    }

    public Object getTag() {
        return tag;
    }

    public String getUrl() {
        return url;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("url:" + getUrl() + "\n tag:" + getTag() + "\n");
        if (headers != null) {
            sb.append("headers:\n");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                sb.append(entry.getKey() + "->" + entry.getValue() + "\n");
            }
        }

        if (getParams() != null) {
            sb.append("bodys:\n");
            for (Map.Entry<String, String> entry :
                    getParams().entrySet()) {
                sb.append(entry.getKey() + "->" + entry.getValue() + "\n");
            }
        }
        return sb.toString();
    }

    public static class Builder {
        private String tag;
        private String url;
        private HashMap<String, String> params;

        private HashMap<String, String> headers;
        private String contentType;

        public final Builder setContentType(String contentType){
            this.contentType = contentType;
            return this;
        }
        public final Builder setHeaders(HashMap<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setParams(HashMap<String, String> params) {
            this.params = params;
            return this;
        }

        public RequestParams build() {
            return new RequestParams(this.tag, this.url, this.headers, this.params, this.contentType);
        }
    }
}
