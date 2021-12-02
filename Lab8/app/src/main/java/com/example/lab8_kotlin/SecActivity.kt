package com.example.lab8_kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecActivity:AppCompatActivity() {
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec)
        //將變數與XML元件綁定
        val btn_send = findViewById<Button>(R.id.btn_send)
        val ed_name = findViewById<EditText>(R.id.ed_name)
        val ed_phone  = findViewById<EditText>(R.id.ed_phone)

        //設定按鈕監聽器
        btn_send.setOnClickListener{
            //判斷是否輸入資料
            when{
                ed_name.length()<1 -> showToast("請輸入姓名")
                ed_phone.length()<1 -> showToast("請輸入電話")
                else->{
                    val b = Bundle()
                    b.putString("name",ed_name.text.toString())
                    b.putString("phone",ed_phone.text.toString())
                    //使用setResult()回傳聯絡人資料
                    setResult(Activity.RESULT_OK, Intent().putExtras(b))
                    finish()
                }
            }
        }
    }
    //建立showToast方法顯示Toast訊息
    private fun showToast(msg:String)=
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()



}