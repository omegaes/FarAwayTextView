package com.mega4tech.farawaytextviewdemo

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.strateq.farawaytextview.FarAwayObserver
import com.strateq.farawaytextview.FarAwayTextView
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    companion object {
        val CALLBACK_NUMBER = 1001
    }

    lateinit var farAwayTv: FarAwayTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        farAwayTv = findViewById(R.id.far_away_tv)

        findViewById<Button>(R.id.perm_btn).setOnClickListener {
            TedPermission.with(this)
                    .setPermissionListener(object : PermissionListener {
                        override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {


                        }

                        override fun onPermissionGranted() {
                            FarAwayObserver.getInstance(applicationContext).LocationPermissionGranted(applicationContext)
                        }

                    })
                    .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                    .check()
        }

        farAwayTv.setDestination( 3.101258,101.640195)
    }
}
