package com.example.lab7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ListView
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val gridView = findViewById<GridView>(R.id.gridView)
        val listView = findViewById<ListView>(R.id.listView)

        val count = ArrayList<String>()
        val item = ArrayList<Item>()
        val priceRange = IntRange(10,100)
        val array = resources.obtainTypedArray(R.array.image_list)
        for(i in 0 until array.length()){
            val photo = array.getResourceId(i,0)
            val name = "水果${i+1}"
            val price = priceRange.random()
            count.add("${i+1}個")
            item.add(Item(photo, name,price))
        }

        array.recycle()
        //建立ArrayAdapter物件，並傳入字串與simple_list_item_1.xml
        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,count)
        //建立橫向顯示列數
        gridView.numColumns = 3
        //建立Myadapter物件，並傳入adapter_vertical作為畫面
        gridView.adapter = MyAdapter(this, item,R.layout.adapter_vertical)
        //建立Myadapter物件，並傳入adapter_horizontal作為畫面
        listView.adapter = MyAdapter(this, item,R.layout.adapter_horizontal)
    }
}

//設定新的類別定義水果的資料結構
data class Item(
    val photo: Int,         //圖片
    val name: String,       //名稱
    val price: Int          //價格
)