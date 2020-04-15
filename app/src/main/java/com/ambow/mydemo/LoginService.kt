package com.ambow.mydemo

import com.ambow.Bean.HttpResponse
import io.reactivex.Observable
import retrofit2.http.GET


/**
 * Created by yjf
 * @description:
 * @date :2019/11/11
 */
interface LoginService {

    /**
     * 是否存在偏好
     */
    @GET("globalte/app/app-edurpApi/v1.0/getIsHaveAppLike")
    fun haveAppLike(): Observable<HttpResponse<IsAppLikeBean>>






}