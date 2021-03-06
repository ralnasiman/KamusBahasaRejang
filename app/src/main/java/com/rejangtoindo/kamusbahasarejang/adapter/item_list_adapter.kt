package com.rejangtoindo.kamusbahasarejang.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rejangtoindo.kamusbahasarejang.R
import com.rejangtoindo.kamusbahasarejang.model.kamus
import org.jetbrains.anko.AnkoLogger


class item_list_adapter(
    val mode: Int,
    val list: List<kamus>,
    private val cellClickListener: CellClickListener?
):RecyclerView.Adapter<item_list_adapter.ViewHolder>(), AnkoLogger {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

           val item= LayoutInflater.from(parent.context).inflate(R.layout.row_kamus, parent, false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind_item(list[position], mode)
        val data = list[position]
        holder.itemView.setOnClickListener {
            cellClickListener?.onCellClickListener(data)
        }


    }
    inner class ViewHolder(view:View): RecyclerView.ViewHolder(view) {
        private val indonesia: TextView = view.findViewById(R.id.tvText)
        private val rejang: TextView = view.findViewById(R.id.tvbhsRejang)
//        private val kelaskata: TextView = view.findViewById(R.id.tv_kelaskata)


        fun bind_item(kamus: kamus, mode : Int) {

            if (mode == 1) {
                indonesia.text = kamus.indo
                rejang.text = kamus.rejang
//                kelaskata.text = kamus.kelas_kata
            }
            else if(mode==2) {
                rejang.text = kamus.rejang
//                kelaskata.text = kamus.kelas_kata
                indonesia.text = kamus.indo
            }
        }

    }
    interface CellClickListener {
        fun onCellClickListener(data: kamus)
    }
}
