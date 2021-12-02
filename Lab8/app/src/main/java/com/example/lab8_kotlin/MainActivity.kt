package com.example.lab8_kotlin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MyAdapter
    private val contacts = ArrayList<Contact>()
    //接收回傳資料
    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.extras?.let{
            if (requestCode == 1 && resultCode == Activity.RESULT_OK){
                val name = it.getString("name") ?:return@let
                val photo = it.getString("phone") ?:return@let
                //新增聯絡人資料
                contacts.add(Contact(name, photo))
                //新增列表
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //將變數與XML元件綁定
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val btn_add = findViewById<Button>(R.id.btn_add)
        //建立LinearLayoutManager物件，設定垂直排列
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        //建立MyAdapter並連結recyclerView
        adapter = MyAdapter(contacts)
        recyclerView.adapter = adapter
        //設定按鈕監聽器，使用startActivityForResult()啟動SecActivity
        btn_add.setOnClickListener{
            startActivityForResult(Intent(this, SecActivity::class.java),1)
        }
    }
}

data class Contact(
    val name: String, //姓名
    val phone: String //電話
)