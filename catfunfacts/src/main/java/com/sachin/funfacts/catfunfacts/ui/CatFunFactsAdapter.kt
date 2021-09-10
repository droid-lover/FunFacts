package com.sachin.funfacts.catfunfacts.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sachin.central.datasource.pojos.CatFactsResModel
import com.sachin.central.datasource.pojos.DogFactsResModel
import com.sachin.funfacts.catfunfacts.R
import kotlinx.android.synthetic.main.item_cats_fact_layout.view.*

/**
 * created by Sachin Rajput
 * https://droid-lover.medium.com/
 */
class CatFunFactsAdapter(private val facts: List<CatFactsResModel>) : RecyclerView.Adapter<CatFunFactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cats_fact_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCatFunFact.text = facts[position]?.fact?:""
    }

    override fun getItemCount(): Int {
        return facts.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvCatFunFact: TextView = itemView.tvCatFunFact
    }
}