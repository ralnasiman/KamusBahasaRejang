package com.rejangtoindo.kamusbahasarejang.ui.fragment.home

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.rejangtoindo.kamusbahasarejang.R
import com.rejangtoindo.kamusbahasarejang.adapter.item_list_adapter
import com.rejangtoindo.kamusbahasarejang.dao.database
import com.rejangtoindo.kamusbahasarejang.model.kamus
import kotlinx.android.synthetic.main.fragment_detail_indo.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.OkHttpClient
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.sdk27.coroutines.textChangedListener


class HomeFragment : Fragment(), item_list_adapter.CellClickListener {
    private var mode: Int = 1
    private lateinit var adp: item_list_adapter
    private val res: MutableList<kamus> = mutableListOf()

    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
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
        val v = inflater.inflate(R.layout.fragment_home, container, false)
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

            migrateDatabase()
        }
        rv_list.setHasFixedSize(true)
        rv_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adp = item_list_adapter(
            mode,
            res,
            this
        )
        rv_list.adapter = adp

        getKamus()
        etsearch.textChangedListener {
            afterTextChanged {
                updateAdp()

            }
        }


    }

    private fun updateAdp() {
        val temp: Collection<kamus>

        if (mode == 1)
            temp = res.filter {
                it.indo.decapitalize().contains(etsearch.text.toString().decapitalize())

            }
        else
            temp = res.filter {
                it.rejang.decapitalize().contains(etsearch.text.toString().decapitalize())
            }
//    else
//        temp = res.filter {
//            it.kelas_kata.decapitalize().contains(etsearch.text.toString().decapitalize())
//        }

        adp = item_list_adapter(mode, temp, this)

        rv_list.adapter = adp
    }

    private fun getKamus() {

        val res2 = requireActivity().database?.use {
            select(kamus.TABLE).parseList(classParser<kamus>())
        }
        res.clear()
        res.addAll(res2)
        adp.notifyDataSetChanged()
    }

    private fun migrateDatabase() {

        getActivity()?.getApplicationContext()?.assets?.open("kosakata_update.csv")
            ?.bufferedReader().use {
            val iterator = it?.lineSequence()?.iterator()
            if (iterator != null) {
                while (iterator.hasNext()) {
                    val line = iterator.next()
                    val data = line.split(",")
                    activity?.database?.use {
                        insert(
                            kamus.TABLE,
                            kamus.indo to data[1],
                            kamus.rejang to data[2],
                            kamus.kelas_kata to data[3]


                        )
                    }
                }
            }
        }

    }

    override fun onCellClickListener(data: kamus) {

        val pesan = LayoutInflater.from(activity).inflate(R.layout.fragment_detail_indo, null)

        //AlertDialogBuilder
        val messageBoxBuilder = AlertDialog.Builder(activity).setView(pesan)


        //setting text values
        pesan.tvJudul.text = "Detail Kata Indonesia"
        pesan.tvIndonesia.text = "Indonesia = "+data.indo
        pesan.tvbhsRejang.text = "Rejang = "+data.rejang
//        messageBoxView.tvLafal.text = data.lafal
        pesan.tvKelas.text = "Kelas kata = "+data.kelas_kata

        //show dialog
        val  messageBoxInstance = messageBoxBuilder.show()
//       messageBoxInstance.window?.setBackgroundDrawableResource(R.drawable.shape_background)
        //set Listener
        pesan.btn_oke.setOnClickListener(){
            //close dialog
            messageBoxInstance.dismiss()
        }
    }
}
