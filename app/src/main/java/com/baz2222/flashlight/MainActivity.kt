package com.baz2222.flashlight

import android.content.Context
import android.hardware.camera2.CameraManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var cameraManager:CameraManager
    private lateinit var powerButton:ImageButton
    private lateinit var player:MediaPlayer
    var isPressed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        powerButton = findViewById(R.id.sw)
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        powerButton.setOnClickListener{ flashLightButtonPressed(it)}
        player = MediaPlayer.create(this, R.raw.click)
    }

    private fun flashLightButtonPressed(view: View?) {
        player.start()
        if(!isPressed){
            val cameraListID = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraListID, true)
            isPressed = true
            powerButton.setImageResource(R.drawable.ic_power_on)
            textMessage(getString(R.string.on_message), this)
        }else{
            val cameraListID = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraListID, false)
            isPressed = false
            powerButton.setImageResource(R.drawable.ic_power_off)
            textMessage(getString(R.string.off_message), this)
        }//else
    }

    private fun textMessage(msg: String, context: Context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}