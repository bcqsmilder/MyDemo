package com.ambow.net;

import android.util.Log;

import com.ambow.callback.BaseCallBack;

import io.reactivex.observers.DisposableObserver;


public class ObserverImpl<T> extends DisposableObserver<T> {
    private BaseCallBack callBack;
    public ObserverImpl(BaseCallBack callBackData) {
        callBack = callBackData;
    }

    @Override
    public void onError(Throwable e) {
        Log.e("yyy","错误 "+e);
        callBack.networkerror();
    }
    @Override
    public void onComplete() {
        callBack.onCompleted();
    }
    @Override
    public void onNext(T t) {
        success(t);
    }
    /**
     * 将成功回调暴露出来 方便重写
     */
    public void success(T t){
     callBack.success(t);
    }

}
