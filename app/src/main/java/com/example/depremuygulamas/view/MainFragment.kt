package com.example.depremuygulamas.view

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.depremuygulamas.R
import com.example.depremuygulamas.adapter.adapter
import com.example.depremuygulamas.databinding.FragmentMainBinding
import com.example.depremuygulamas.model.SubModel
import com.example.depremuygulamas.viewmodel.mainviewmodel
import java.util.*
import kotlin.collections.ArrayList


class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding:FragmentMainBinding?=null
    private val binding get() = _binding!!
    private lateinit var modelview:mainviewmodel
    val dizi= mutableListOf<SubModel>()
    var Adapt=adapter()
    val tempdizi=ArrayList<SubModel>()
    val temp2=ArrayList<SubModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     _binding= FragmentMainBinding.inflate(inflater,container,false)
        modelview=ViewModelProvider(this).get(mainviewmodel::class.java)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        observeData()

        return binding.root
    }
    private fun observeData() {
        binding.recyclerView.layoutManager=LinearLayoutManager(requireContext())
        modelview.getDatas()
        modelview.data.observe(viewLifecycleOwner){
            dizi.addAll(it.get(0).result)
            temp2.addAll(it.get(0).result)
            tempdizi.addAll(it.get(0).result)
            Adapt.refreshData(dizi)
            binding.recyclerView.adapter=Adapt
        }
        modelview.is_it_loading.observe(viewLifecycleOwner){
            if(it==true){
                binding.progressBar.visibility=View.VISIBLE
                binding.recyclerView.visibility=View.INVISIBLE
            }else if(it==false){
                binding.progressBar.visibility=View.INVISIBLE
                binding.recyclerView.visibility=View.VISIBLE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_layout,menu)
        binding.toolbar.setTitle(R.string.son_depremler)
        val itemView = menu.findItem(R.id.search_view)
        val searchView= (itemView.actionView) as SearchView

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText=newText!!.toLowerCase(Locale.getDefault())
                if(searchText.isNotEmpty()){
                    for(i in 0 until tempdizi.size){
                       if(tempdizi.get(i).lokasyon.toLowerCase(Locale.getDefault()).contains(searchText)){
                            dizi.clear()
                           dizi.add(temp2.get(i))
                           binding.recyclerView.adapter?.notifyDataSetChanged()
                       }
                    }
                }else{
                    dizi.clear()
                    dizi.addAll(temp2)
                    Adapt.refreshData(dizi)
                    binding.recyclerView.adapter?.notifyDataSetChanged()
                }
                return false
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


}