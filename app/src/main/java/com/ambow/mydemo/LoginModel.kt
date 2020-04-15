package com.ambow.mydemo

import com.ambow.Bean.BaseModel
import com.ambow.Bean.HttpResponse
import com.ambow.callback.BaseCallBack
import com.ambow.net.ObserverImpl
import com.ambow.net.RetrofitFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by yjf
 * @description:登录 model
 * @date :2019/11/11
 */
class LoginModel: BaseModel() {

    private val loginService: LoginService by lazy {
        RetrofitFactory.getHostRetrofit().create(LoginService::class.java)
    }




    /**
     * 判断是否存在偏好
     */
    fun haveAppLike(baseCallBack: BaseCallBack<HttpResponse<IsAppLikeBean>>) {
        val objectObserver = ObserverImpl<HttpResponse<IsAppLikeBean>>(baseCallBack)
        rxApiManager.add("haveAppLike", objectObserver)
        loginService.haveAppLike()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(objectObserver)
    }



}