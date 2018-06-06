package com.cursoutn.viewsej2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        langOtro.setOnCheckedChangeListener { buttonView, isChecked -> otherLang.isEnabled = isChecked }
        otherLang.isEnabled = false
        btnGenerar.setOnClickListener {
            var cv = "Sos un programador "
            if(radioJunior.isChecked)
                cv += "Junior"
            else if(radioSemi.isChecked)
                cv+= "Semi Senior"
            else if(radioSenior.isChecked)
                cv += "Senior"
            else {
                Toast.makeText(this, "Indique su seniority", Toast.LENGTH_LONG ).show()
                Log.d("Ej2", "El flaco se olvido de seleccionar un seniority")
                return@setOnClickListener
            }

            var langs = mutableListOf(langBasic, langCSharp, langJava, langKotlin, langPascal, langPhp, langProlog, langPython, langRubi)
            var selectedLanguages : MutableList<String> = langs.filter { v -> v.isChecked }.map { v -> v.text.toString() }.toMutableList()

            var otroLang = otherLang.text.toString()
            if(langOtro.isChecked && otroLang.length > 0){
                selectedLanguages.add(otroLang)
            }

            if(selectedLanguages.size > 0){
                cv += " y se programar en " + selectedLanguages.joinToString(", ")
                txtCV.text = cv
            }else{
                Toast.makeText(this, "Si sos un programador, deci en que sabes programar...", Toast.LENGTH_LONG ).show()
                Log.d("Ej2", "El flaco se olvido de seleccionar los lenguajes")
            }


        };

    }
}
