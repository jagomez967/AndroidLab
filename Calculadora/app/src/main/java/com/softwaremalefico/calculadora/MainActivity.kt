package com.softwaremalefico.calculadora

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var numeroAcumulado: Int = 0
    var ultimaOperacion: Int = 0
    var borrarDisplay: Boolean = false

    lateinit var btnM1 : Button
    lateinit var btnM2 : Button
    lateinit var btnM3 : Button
    lateinit var btnM4 : Button

    lateinit var btn1 : Button
    lateinit var btn2 : Button
    lateinit var btn3 : Button
    lateinit var btn4 : Button
    lateinit var btn5 : Button
    lateinit var btn6 : Button
    lateinit var btn7 : Button
    lateinit var btn8 : Button
    lateinit var btn9 : Button
    lateinit var btn0 : Button
    lateinit var btnC : Button
    lateinit var btnCe : Button
    lateinit var btnMas : Button
    lateinit var btnMenos : Button
    lateinit var btnPor : Button
    lateinit var btnDiv : Button
    lateinit var btnIgual : Button
    lateinit var txtDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnM1 = findViewById(R.id.btnM1)
        btnM2 = findViewById(R.id.btnM2)
        btnM3 = findViewById(R.id.btnM3)
        btnM4 = findViewById(R.id.btnM4)


        var mBtns : Array<Button> = arrayOf(btnM1, btnM2, btnM3, btnM4)
        for(btn in mBtns){


            btn.setOnLongClickListener{ v ->
                var index = mBtns.indexOf(btn)
                var key = "posicion_" + index
                var sharedPref = getSharedPreferences("losBotonesM", Context.MODE_PRIVATE)
                var editor: SharedPreferences.Editor = sharedPref.edit()
                editor.putString(key, txtDisplay.text.toString())
                editor.commit()
                Toast.makeText(this, "Guardado en M " + (index+1), Toast.LENGTH_LONG).show()
                true
            }

            btn.setOnClickListener{ v -> run {
                var index = mBtns.indexOf(btn)
                var key = "posicion_" + index
                var sharedPref = getSharedPreferences("losBotonesM", Context.MODE_PRIVATE)
                txtDisplay.text = sharedPref.getString(key, txtDisplay.text.toString())
            }}


        }

        txtDisplay = findViewById(R.id.txtDisplay)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
        btn0 = findViewById(R.id.btn0)
        btnC = findViewById(R.id.btnC)
        btnCe = findViewById(R.id.btnCE)
        btnMas = findViewById(R.id.btnMas)
        btnMenos = findViewById(R.id.btnMenos)
        btnPor = findViewById(R.id.btnPor)
        btnDiv = findViewById(R.id.btnDiv)
        btnIgual = findViewById(R.id.btnIgual)

        var botonesNumericos : List<Button> = listOf(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0)
        for( botonNumerico in botonesNumericos){
            botonNumerico.setOnClickListener { v -> agregarDigito(v) }
        }

        var botonesOperadores : List<Button> = listOf(btnMas, btnMenos, btnPor, btnDiv)
        for( botonOperador in botonesOperadores){
            botonOperador.setOnClickListener { v -> nuevaOperacion(v) }
        }

        btnIgual.setOnClickListener{
            calcularResultado()
            ultimaOperacion = 0
            numeroAcumulado = 0
        }

        btnC.setOnClickListener {
            ultimaOperacion = 0
            numeroAcumulado = 0
            txtDisplay.text = "0"
        }

        btnCe.setOnClickListener { txtDisplay.text = "0" }

        if(savedInstanceState != null){
            txtDisplay.text = savedInstanceState.getString("display", "")
            numeroAcumulado = savedInstanceState.getInt("numeroAcumulado",0)
            ultimaOperacion = savedInstanceState.getInt("ultimaOperacion",0)
            borrarDisplay   = savedInstanceState.getBoolean("borrarDisplay",false)
        }

    }

    private fun calcularResultado() {
        var resultado = getNumeroActual()
        if(ultimaOperacion != 0) {

            when (ultimaOperacion) {
                R.id.btnMas -> resultado = numeroAcumulado + getNumeroActual()
                R.id.btnMenos -> resultado = numeroAcumulado - getNumeroActual()
                R.id.btnPor -> resultado = numeroAcumulado * getNumeroActual()
                R.id.btnDiv ->{
                    if(getNumeroActual() != 0)
                        resultado = numeroAcumulado / getNumeroActual()
                    else
                        resultado = 0
                }
            }
        }
        txtDisplay.text = resultado.toString()
    }

    private fun nuevaOperacion(v: View) {
        calcularResultado()
        numeroAcumulado = getNumeroActual()
        ultimaOperacion = v.id
        borrarDisplay = true
    }

    private fun getNumeroActual() : Int{
        return txtDisplay.text.toString().toInt()
    }

    private fun agregarDigito(v: View){
        if(borrarDisplay){
            txtDisplay.text = "0"
            borrarDisplay = false
        }
        var botonNumerico : Button = v as Button
        var nuevoDigito :Int = botonNumerico.text.toString().toInt()
        var numeroViejo :Int = getNumeroActual()
        var nuevoNumero = (numeroViejo * 10) + nuevoDigito
        txtDisplay.text = nuevoNumero.toString()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if(outState != null){
            outState.putString("display", txtDisplay.text.toString())
            outState.putInt("numeroAcumulado", numeroAcumulado)
            outState.putInt("ultimaOperacion", ultimaOperacion)
            outState.putBoolean("borrarDisplay", borrarDisplay)
        }
    }

}
