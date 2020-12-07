package com.rejangtoindo.kamusbahasarejang.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rejangtoindo.kamusbahasarejang.R
import kotlinx.android.synthetic.main.activity_detail_kamus.*


class DetailKamusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kamus)

        val indo = intent.getStringExtra("EXTRA_INDO")
        tv_indo.text= indo
        val rejang = intent.getStringExtra("EXTRA_REJANG")
        tv_rejang.text= rejang
        val kelas = intent.getStringExtra("EXTRA_KK")
        tv_kelaskata.text= kelas
        getActionBar()?.setTitle("Detail Kata Indonesia");
        getSupportActionBar()?.setTitle("Detail Kata Indonesia");
//        val lafal = intent.getStringExtra("EXTRA_LAFAL")
//        tv_pelafalan.text= lafal
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {

        // super.onBackPressed(); // Comment this super call to avoid calling finish() or fragmentmanager's backstack pop operation.
    }
}