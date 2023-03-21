package com.example.qytest

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val textView: TextView by lazy {
        findViewById<TextView>(R.id.chat_item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.buildText(
            "心爱的小摩托", gender = 1, level = 54, title = "长老", massage = "：当前文本是用户发的一段话，可以换行的一段话"
        )
    }
}