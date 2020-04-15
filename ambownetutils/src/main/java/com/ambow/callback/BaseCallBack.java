package com.ambow.callback;


public interface BaseCallBack<T> {
    void networkerror();
    void onCompleted();
    void success(T data);
}
