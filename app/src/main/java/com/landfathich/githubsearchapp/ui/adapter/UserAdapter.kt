package com.landfathich.githubsearchapp.ui.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.landfathich.githubsearchapp.data.model.Item

class UserAdapter : ListDelegationAdapter<List<Item>>() {

    init {
        delegatesManager.addDelegate(UserDelegateAdapter())
        delegatesManager.addDelegate(RepoDelegateAdapter())
    }

    override fun setItems(items: List<Item>?) {
        super.setItems(items)
        notifyDataSetChanged()
    }
}