package com.ambow.mydemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.ambow.Bean.HttpResponse
import com.ambow.callback.BaseCallBack
import com.ambow.mydemo.video.PolyvPlayerActivity

import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity :Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        txt_home_click.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Log.i("Mainactivity=======","启动了")
                isHaveLikeApp()
            }
        })
        txt_login_click.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Log.i("Mainactivity=======","login启动了")
                var intent=Intent();
                intent.setClass(this@HomeActivity,LoginActivity::class.java)
                startActivity(intent)
            }
        })

        txt_live_click.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Log.i("Mainactivity=======","live启动了")
                var intent=Intent();
                intent.setClass(this@HomeActivity,PolyvPlayerActivity::class.java)
                startActivity(intent)
            }
        })
    }
    override fun onResume() {
        super.onResume()

    }
    fun isHaveLikeApp(){
        var loginModel: LoginModel= LoginModel();
        loginModel.haveAppLike(object : BaseCallBack<HttpResponse<IsAppLikeBean>> {
            override fun networkerror() {
                Log.i("Mainactivity=======","报错了")
            }
            override fun onCompleted() {
            }

            override fun success(data: HttpResponse<IsAppLikeBean>?) {
                Log.i("Mainactivity=======","成功了")
            }
        })
    }
}
