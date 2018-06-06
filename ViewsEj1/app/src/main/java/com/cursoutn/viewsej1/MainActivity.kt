package com.cursoutn.viewsej1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
//import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        buttonMas.setOnClickListener { txtContador.text = (txtContador.text.toString().toInt() + 1).toString() }
        buttonMenos.setOnClickListener { txtContador.text = (txtContador.text.toString().toInt() - 1).toString() }


    }
}
