package com.ambow.mydemo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.ambow.loginlib.utils.LoginLoader
import com.ambow.loginlib.view.LoginSubmit
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity :Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    fun initView(){
        captch.setUserEdit(email)
        captch.setCaptchaListener(object:LoginLoader.CaptchaListener{
            override fun onPre() {
            }

            override fun onComplete(phoneOrEmail: String?) {
            }
        })

        login_fast_submit.setSubmitType(LoginLoader.SubmitType.LOGIN)
        setDataByType(LoginLoader.SubmitType.REGISTER, login_fast_submit);
        login_fast_submit.setSubmitListener(object :LoginLoader.SubmitListener{
            override fun onComplete(vararg strings: String?) {
               strings.forEach{
                   Log.d("LoginActivity", "submit onComplete: " + it.toString());
               }
            }
        })
    }

  fun setDataByType(submitType:LoginLoader.SubmitType,loginSubmit: LoginSubmit) {
      if (loginSubmit == null) return;
      if (submitType == null) return;
      loginSubmit.setSubmitType(submitType);
    when(submitType){
        LoginLoader.SubmitType.LOGIN-> loginSubmit.setData(email, password);
        LoginLoader.SubmitType.REGISTER-> loginSubmit.setData(email, code,password);

    }

    }
}
