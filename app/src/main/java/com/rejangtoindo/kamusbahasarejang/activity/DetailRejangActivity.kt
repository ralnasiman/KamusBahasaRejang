package com.rejangtoindo.kamusbahasarejang.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rejangtoindo.kamusbahasarejang.R
import kotlinx.android.synthetic.main.activity_detail_rejang.*

class DetailRejangActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_rejang)

        val indo = intent.getStringExtra("EXTRA_REJANG")
        tctbhsRejang.text= indo
        val rejang = intent.getStringExtra("EXTRA_INDO")
        txtBhsIndo.text= rejang
        val kelas = intent.getStringExtra("EXTRA_KK")
        tv_kelas.text= kelas
        getActionBar()?.setTitle("Detail Kata Rejang");
        getSupportActionBar()?.setTitle("Detail Kata Rejang");}

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {

        // super.onBackPressed(); // Comment this super call to avoid calling finish() or fragmentmanager's backstack pop operation.
    }
    }