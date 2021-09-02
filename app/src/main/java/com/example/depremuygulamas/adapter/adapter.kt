package com.example.depremuygulamas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.depremuygulamas.databinding.RecyclerRowBinding
import com.example.depremuygulamas.model.ParcelableClass
import com.example.depremuygulamas.model.SubModel
import com.example.depremuygulamas.view.MainFragmentDirections

class adapter:RecyclerView.Adapter<adapter.TutucuVH>() {
    var emptyList=emptyList<SubModel>()

    class TutucuVH(val binding:RecyclerRowBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutucuVH {
        return TutucuVH(RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TutucuVH, position: Int) {
                holder.binding.textYerinAdi.text=emptyList.get(position).lokasyon
                holder.binding.textBuyukluk.text=emptyList.get(position).mag
                holder.itemView.setOnClickListener {

                    val dataclass=ParcelableClass(emptyList.get(position))
                    val action= MainFragmentDirections.actionMainFragmentToSecondFragment(dataclass)
                    Navigation.findNavController(it).navigate(action)

                }
    }

    override fun getItemCount(): Int {
        return emptyList.size
    }
    fun refreshData(new_data:List<SubModel>){
        this.emptyList=new_data
        notifyDataSetChanged()
    }

}