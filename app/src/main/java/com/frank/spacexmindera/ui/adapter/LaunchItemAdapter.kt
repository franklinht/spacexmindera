package com.frank.spacexmindera.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frank.spacexmindera.R
import com.frank.spacexmindera.data.model.LaunchInfo
import com.frank.spacexmindera.utils.DateUtils
import com.squareup.picasso.Picasso

class LaunchItemAdapter internal constructor(private val data: ArrayList<LaunchInfo>, private val itemListener: ItemListener) : RecyclerView.Adapter<LaunchItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_launch_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItems(data[position])

    override fun getItemCount(): Int = data.size

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(launchInfo: LaunchInfo) {
            val statusIv =  itemView.findViewById<ImageView>(R.id.item_launch_status)
            val missionIv =  itemView.findViewById<ImageView>(R.id.item_launch_avatar)
            val missionNameTv =  itemView.findViewById<TextView>(R.id.item_launch_missionname_tv)
            val dateTimeTv =  itemView.findViewById<TextView>(R.id.item_launch_datetime_tv)
            val rocketModelTv =  itemView.findViewById<TextView>(R.id.item_launch_rocketmodel_tv)
            val sinceFromTv =  itemView.findViewById<TextView>(R.id.item_launch_sincefrom_tv)
            val whenDateTv =  itemView.findViewById<TextView>(R.id.item_launch_whendate_tv)

            missionNameTv.text = launchInfo.mission_name
            dateTimeTv.text = itemView.context.getString(
                R.string.home_launch_datetime_value,
                DateUtils.getDate(launchInfo.launch_date_utc),
                DateUtils.getTime(launchInfo.launch_date_utc))
            rocketModelTv.text = itemView.context.getString(
                R.string.home_launch_rocketmodel_value,
                launchInfo.rocket.rocket_name,
                launchInfo.rocket.rocket_type)
            whenDateTv.text = launchInfo.launch_date_unix.toString()

            if (launchInfo.launch_success == true) {
                statusIv.setImageResource(R.drawable.ic_check)
            } else {
                statusIv.setImageResource(R.drawable.ic_close)
            }

            Picasso.with(itemView.context).load(launchInfo.links?.mission_patch_small)
                .placeholder(R.mipmap.ic_launcher_round) // pic default
                .into(missionIv);

            itemView.setOnClickListener() {
                itemListener.onItemListener(launchInfo)
            }
        }
    }

    fun addLaunchItems(launchInfo: List<LaunchInfo>) {
        data.apply {
            clear()
            addAll(launchInfo)
        }
    }
}