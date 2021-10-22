package com.frank.spacexmindera.ui.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.frank.spacexmindera.R
import com.frank.spacexmindera.data.api.RFBuilder
import com.frank.spacexmindera.data.api.RFHelper
import com.frank.spacexmindera.data.api.StatusResponse
import com.frank.spacexmindera.data.model.CompanyInfo
import com.frank.spacexmindera.data.model.LaunchInfo
import com.frank.spacexmindera.ui.adapter.ItemListener
import com.frank.spacexmindera.ui.adapter.LaunchItemAdapter
import com.frank.spacexmindera.ui.viewmodel.HomeViewModel
import com.frank.spacexmindera.ui.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import android.content.Intent
import android.net.Uri
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


class HomeFragment : Fragment(), ItemListener {
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter : LaunchItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        initObservers()
    }

    private fun initView() {
        adapter = LaunchItemAdapter(arrayListOf(), this)
        home_launch_rv.layoutManager = LinearLayoutManager(context)
        home_launch_rv.adapter = adapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RFHelper(RFBuilder.api))
        ).get(HomeViewModel::class.java)
    }

    private fun initObservers() = setupObservers();

    private fun setupObservers() {
        // Company Description
        viewModel.getCompanyInfo().observe(viewLifecycleOwner, Observer {
            it?.let { response ->
                when (response.statusReponse) {
                    StatusResponse.SUCCESS -> {
                        home_description_tv.visibility = View.VISIBLE
                        activity?.progressBar?.visibility = View.GONE
                        response.data?.let {
                                companyInfo -> fillCompanyDescription(companyInfo)
                        }
                    }
                    StatusResponse.ERROR -> {
                        Toast.makeText(context, getString(R.string.home_company_info_error), Toast.LENGTH_LONG).show()

                        home_description_tv.visibility = View.VISIBLE
                        activity?.progressBar?.visibility = View.GONE
                    }
                    StatusResponse.LOADING -> {
                        home_description_tv.visibility = View.GONE
                        activity?.progressBar?.visibility = View.VISIBLE
                    }
                }
            }
        })

        //Launch history
        viewModel.getLaunchHistory().observe(viewLifecycleOwner, Observer {
            it?.let { response ->
                when (response.statusReponse) {
                    StatusResponse.SUCCESS -> {
                        home_launch_rv.visibility = View.VISIBLE
                        activity?.progressBar?.visibility = View.GONE
                        response.data?.let {
                                launchList -> setupLaunchList(launchList)
                        }
                    }
                    StatusResponse.ERROR -> {
                        Toast.makeText(context, getString(R.string.home_launch_history_error), Toast.LENGTH_LONG).show()

                        home_launch_rv.visibility = View.GONE
                        activity?.progressBar?.visibility = View.GONE
                    }
                    StatusResponse.LOADING -> {
                        home_description_tv.visibility = View.GONE
                        activity?.progressBar?.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun setupLaunchList(launchList: List<LaunchInfo>) {
        adapter.apply {
            addLaunchItems(launchList)
            notifyDataSetChanged()
        }
    }

    private fun fillCompanyDescription(companyInfo: CompanyInfo) {
        val formatter: NumberFormat = DecimalFormat("#,###")
        val valuation = formatter.format(companyInfo.valuation)

        home_description_tv.text = getString(R.string.home_company_info,
            companyInfo.name,
            companyInfo.founder,
            companyInfo.founded.toString(),
            companyInfo.employees.toString(),
            companyInfo.launchSites.toString(),
            valuation
        )
    }

    override fun onItemListener(launchInfo: LaunchInfo) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(launchInfo.mission_name)
        builder.setMessage(getString(R.string.home_launch_options_message))
        builder.setPositiveButton(getString(R.string.home_launch_open_wikipedia)) { _,_ ->
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(launchInfo.links.wikipedia))
            startActivity(browserIntent)
        }
        builder.setNegativeButton(getString(R.string.home_launch_open_article)) { _,_ ->
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(launchInfo.links.article_link))
            startActivity(browserIntent)
        }
        builder.setNeutralButton(getString(R.string.home_launch_open_youtube)) { _,_ ->
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(launchInfo.links.video_link))
            startActivity(browserIntent)
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}