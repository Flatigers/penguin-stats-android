package org.penguin_stats.android.ui.detailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.penguin_stats.android.R

class DetailReportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_report, container, false)
    }

    companion object {
        @JvmStatic
        fun new() = DetailReportFragment()
    }
}