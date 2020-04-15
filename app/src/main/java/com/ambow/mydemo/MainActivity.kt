package com.ambow.mydemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt_click.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var  intent= Intent();
                intent.setClass(this@MainActivity,HomeActivity::class.java)
                startActivity(intent)
            }
        })
    }


}
