package com.ambow.Bean;


import com.ambow.net.RxApiManager;

public class BaseModel {
    protected RxApiManager rxApiManager;

    public BaseModel() {
        rxApiManager = new RxApiManager();
    }
    public void cancelAllRequest(){
        if(null != rxApiManager){
            rxApiManager.cancelAll();
        }
    }
}
