package com.cursoutn.viesej4

import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        barrita.max = 100

        barrita.setOnSeekBarChangeListener( object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val display = windowManager.defaultDisplay
                val size = Point()
                display.getSize(size)
                var maximumMargin = size.x - picture.width
                var newMargin = (maximumMargin * progress) / 100

                var marginParams = ViewGroup.MarginLayoutParams(picture.layoutParams)
                marginParams.leftMargin = newMargin
                marginParams.rightMargin = 0
                marginParams.topMargin = 0
                marginParams.bottomMargin = 0

                var newLayoutParams = LinearLayout.LayoutParams(marginParams)
                picture.layoutParams = newLayoutParams


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }


        })
    }
}
