package com.ambow.net;


import android.util.ArrayMap;

import java.util.Iterator;
import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 */

public class RxApiManager {
    private ArrayMap<String, DisposableObserver> maps;

    public RxApiManager(){
        maps = new ArrayMap<>();
    }

    public void add(String tag, DisposableObserver subscription){
        cancel(tag);
        maps.put(tag,subscription);
    }
    public void remove(String tag){
        if (!maps.isEmpty()) {
            maps.remove(tag);
        }
    }

    public void removeAll() {
        if (!maps.isEmpty()) {
            maps.clear();
        }
    }

    public void cancel(String tag){
        if (maps.isEmpty()) {
            return;
        }
        if (maps.get(tag) == null) {
            return;
        }
        if (!maps.get(tag).isDisposed()) {
            maps.get(tag).dispose();
        }
    }

    public void cancelAll(){
        if (maps.isEmpty()) {
            return;
        }
        //迭代器删除
        Iterator<Map.Entry<String, DisposableObserver>> iterator = maps.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, DisposableObserver> next = iterator.next();
            cancel(next.getKey());
            iterator.remove();
        }
    }
}
