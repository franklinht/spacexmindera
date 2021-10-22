package com.frank.spacexmindera.ui.adapter

import com.frank.spacexmindera.data.model.LaunchInfo

interface ItemListener {
    fun onItemListener(launchInfo: LaunchInfo)
}