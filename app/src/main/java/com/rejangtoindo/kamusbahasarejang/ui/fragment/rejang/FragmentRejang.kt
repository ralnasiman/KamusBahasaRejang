package com.rejangtoindo.kamusbahasarejang.ui.fragment.rejang

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.rejangtoindo.kamusbahasarejang.R
import com.rejangtoindo.kamusbahasarejang.adapter.item_list_adapter2
import com.rejangtoindo.kamusbahasarejang.dao.database
import com.rejangtoindo.kamusbahasarejang.model.kamus
import kotlinx.android.synthetic.main.fragment_detail_indo.view.btn_oke
import kotlinx.android.synthetic.main.fragment_detail_indo.view.tvIndonesia
import kotlinx.android.synthetic.main.fragment_detail_indo.view.tvJudul
import kotlinx.android.synthetic.main.fragment_detail_indo.view.tvKelas
import kotlinx.android.synthetic.main.fragment_detail_rejang.view.*
import kotlinx.android.synthetic.main.fragment_rejang.*
import okhttp3.OkHttpClient
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.sdk27.coroutines.textChangedListener

class FragmentRejang : Fragment(), item_list_adapter2.CellClickListener {
    private var mode: Int = 1
    private lateinit var adp: item_list_adapter2
    private val res: MutableList<kamus> = mutableListOf()

    companion object {

        @JvmStatic
        fun newInstance() =
            FragmentRejang().apply {
                arguments = Bundle().apply {
                    // putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        arguments?.let {

        }
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {


        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_rejang, container, false)
        return v
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val isFirstTime =
            activity?.getPreferences(Context.MODE_PRIVATE)?.getBoolean("isFirstRun", true)
        super.onActivityCreated(savedInstanceState)
//        showSelectedPopuler()
        Stetho.initializeWithDefaults(context)

        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()
        if (isFirstTime!!) {
            activity?.getPreferences(Context.MODE_PRIVATE)?.edit()?.putBoolean("isFirstRun", false)
                ?.apply()
            migrasiDatabase()
        }
        rv_list_rejang.setHasFixedSize(true)
        rv_list_rejang.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adp = item_list_adapter2(
            mode,
            res,
            this)
        rv_list_rejang.adapter = adp

        getKamusRejang()
        etCari.textChangedListener {
            afterTextChanged {
                updateAdp()

            }
        }
    }

    private fun updateAdp(){
        val temp: Collection<kamus>

        if (mode == 1) {
            temp = res.filter {
                it.rejang.decapitalize().contains(etCari.text.toString().decapitalize())

            }
            adp = item_list_adapter2(mode, temp, this)
            rv_list_rejang.adapter = adp
        }
        else if(mode==2) {
            temp = res.filter {
                it.indo.decapitalize().contains(etCari.text.toString().decapitalize())
            }
            adp = item_list_adapter2(mode, temp, this)

            rv_list_rejang.adapter = adp
        }


    }

    private fun getKamusRejang(){

        val res2 = requireActivity().database?.use {
            select(kamus.TABLE ).orderBy(kamus.rejang).parseList(classParser<kamus>())
        }
        res.clear()
        res.addAll(res2)
        adp.notifyDataSetChanged()
    }

    private fun migrasiDatabase(){

        getActivity()?.getApplicationContext()?.assets?.open("kosakata.csv")?.bufferedReader().use {
            val iterator = it?.lineSequence()?.iterator()
            if (iterator != null) {
                while(iterator.hasNext()) {
                    val line = iterator.next()
                    val data = line.split(",")
                    activity?.database?.use {
                        insert(
                            kamus.TABLE,
                            kamus.rejang to data[1],
                            kamus.indo to data[2],
                            kamus.kelas_kata to data[3]


                        )
                    }
                }
            }
        }

    }

    override fun onCellClickListener(data: kamus) {
        val pesan = LayoutInflater.from(activity).inflate(R.layout.fragment_detail_rejang, null)

        //AlertDialogBuilder
        val messageBoxBuilder = AlertDialog.Builder(activity).setView(pesan)

        //setting text values
        pesan.tvJudul.text = "Detail Kata Rejang"
        pesan.tvbhsRejang.text = "Rejang = "+data.rejang
        pesan.tvIndo.text = "Indonesia = "+data.indo
//        messageBoxView.tvLafal.text = data.lafal
        pesan.tvKelasKata.text = "Kelas kata = "+data.kelas_kata

        //show dialog
        val  messageBoxInstance = messageBoxBuilder.show()

        //set Listener
        pesan.btn_oke.setOnClickListener(){
            //close dialog
            messageBoxInstance.dismiss()
        }
    }

}