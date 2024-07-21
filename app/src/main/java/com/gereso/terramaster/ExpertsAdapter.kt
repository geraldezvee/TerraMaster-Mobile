package com.gereso.terramaster

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class ExpertsAdapter(context: Context, experts: List<FetchAll>) :
    ArrayAdapter<FetchAll>(context, R.layout.activity_home, experts) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val holder: ViewHolder

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_home, parent, false)
            holder = ViewHolder()
            holder.fullnameTextView = view.findViewById(R.id.fullname)
            holder.introductionTextView = view.findViewById(R.id.introduction)
            holder.locationTextView = view.findViewById(R.id.location)
            holder.pricerangeTextView = view.findViewById(R.id.pricerange)
            holder.proposalsdoneTextView = view.findViewById(R.id.proposalsdone)
            holder.specialtyTextView = view.findViewById(R.id.specialty)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val expert = getItem(position)
        holder.fullnameTextView.text = expert?.fullname
        holder.introductionTextView.text = expert?.introduction
        holder.locationTextView.text = expert?.location
        holder.pricerangeTextView.text = expert?.pricerange
        holder.proposalsdoneTextView.text = expert?.proposalsdone.toString()
        holder.specialtyTextView.text = expert?.specialty

        return view!!
    }

    private class ViewHolder {
        lateinit var fullnameTextView: TextView
        lateinit var introductionTextView: TextView
        lateinit var locationTextView: TextView
        lateinit var pricerangeTextView: TextView
        lateinit var proposalsdoneTextView: TextView
        lateinit var specialtyTextView: TextView
    }
}
