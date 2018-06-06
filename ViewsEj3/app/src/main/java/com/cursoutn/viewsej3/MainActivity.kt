package com.cursoutn.viewsej3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import android.R.attr.y
import android.R.attr.x
import android.graphics.Point
import android.view.Display
import android.widget.LinearLayout


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        barrita.max = 100

        barrita.setOnSeekBarChangeListener( object: SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                escalarImagenA(progress)

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        escalarImagenA(barrita.progress)
    }

    private fun escalarImagenA(progress: Int){
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        var pictureMaxWidth : Int = size.x
        var pictureMinWidth : Int = pictureMaxWidth / 4
        var pictureSize = Math.max( pictureMinWidth,  (pictureMaxWidth * progress) / 100)
        var newLayoutParams = LinearLayout.LayoutParams(pictureSize, pictureSize)

        picture.layoutParams = newLayoutParams
    }
}
