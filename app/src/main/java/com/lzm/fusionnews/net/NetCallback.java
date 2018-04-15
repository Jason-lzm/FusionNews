package com.lzm.fusionnews.net;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.badoo.mobile.util.WeakHandler;
import com.lzm.fusionnews.utils.JsonUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by lzm on 2018/4/15.
 */

public abstract class NetCallback<T> implements Callback {

    private final static int MSG_CALLBACK_SUCCESSFUL = 0x01;
    private final static int MSG_CALLBACK_FAILED = 0x02;

    private WeakHandler mHandler = new WeakHandler(Looper.getMainLooper(), new UICallback<T>(this));

    private Class<T> mClass;
    public NetCallback(Class<T> clazz) {
        mClass = clazz;
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if(response.isSuccessful()){
            String resbody = response.body().string();
            Timber.i("request success, response:" + resbody);
            T result = JsonUtil.fromJson(resbody, mClass);
            Message msg = Message.obtain();
            msg.what = MSG_CALLBACK_SUCCESSFUL;
            msg.obj = result;
            mHandler.sendMessage(msg);
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Message msg = Message.obtain();
        msg.what = MSG_CALLBACK_FAILED;
        msg.obj = e;
        mHandler.sendMessage(msg);
    }

    public abstract void onSuccess(T result);
    public abstract void onFailed(IOException e);

    class UICallback<T> implements Handler.Callback{

        private NetCallback mNetCallback;

        public UICallback(NetCallback callback){
            mNetCallback = callback;
        }

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case MSG_CALLBACK_SUCCESSFUL:
                    T result = (T)msg.obj;
                    mNetCallback.onSuccess(result);
                    break;
                case MSG_CALLBACK_FAILED:
                    IOException e = (IOException) msg.obj;
                    mNetCallback.onFailed(e);
                    default:
            }
            return false;
        }
    }
}
