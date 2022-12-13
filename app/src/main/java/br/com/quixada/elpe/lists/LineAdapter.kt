package br.com.quixada.elpe.lists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LineAdapter (var titulos: ArrayList<String>,
                   var descripton: ArrayList<String>,
                   var ids: ArrayList<String>): RecyclerView.Adapter<LineAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewNumber: TextView
        val textViewName: TextView
        val textViewUuid: TextView
        init {
            textViewName = view.findViewById<TextView>(R.id.nomeText)
            textViewUuid = view.findViewById<TextView>(R.id.uuid)
            textViewNumber = view.findViewById<TextView>(R.id.number)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.line_recycler, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewName.text = titulos[position]
        holder.textViewNumber.text = descripton[position]
        holder.textViewUuid.text = ("ID #"+ids[position])
    }

    override fun getItemCount(): Int {
        return titulos.size
    }

}