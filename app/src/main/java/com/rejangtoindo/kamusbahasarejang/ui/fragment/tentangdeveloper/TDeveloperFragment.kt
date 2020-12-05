package com.rejangtoindo.kamusbahasarejang.ui.tentangbahasa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rejangtoindo.kamusbahasarejang.R

class TDeveloperFragment : Fragment() {

    private lateinit var tDeveloperFragment: TDeveloperViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        tDeveloperFragment =
                ViewModelProviders.of(this).get(TDeveloperViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_developer, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        tDeveloperFragment.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}