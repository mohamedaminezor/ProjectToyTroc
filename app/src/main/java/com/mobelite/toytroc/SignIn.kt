package com.mobelite.toytroc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.mobelite.toytroc.SignUp.SignUp


class SignIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        val btn_click_me = findViewById(R.id.b1) as Button

        btn_click_me.setOnClickListener {
            Toast.makeText(this@SignIn, "You clicked me.", Toast.LENGTH_SHORT).show()
        }

        val btn2 = findViewById(R.id.b2) as Button
        btn2.setOnClickListener{
            val intent = Intent(this,  SignUp::class.java)
            startActivity(intent)
        }

    }
}