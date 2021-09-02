package com.example.depremuygulamas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.depremuygulamas.model.Earthquake
import com.example.depremuygulamas.util.RetroiftInstance.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class mainviewmodel:ViewModel() {
    val data=MutableLiveData<List<Earthquake>>()
    val is_it_loading=MutableLiveData<Boolean>()

    fun getDatas(){
        viewModelScope.launch(Dispatchers.Main) {
           is_it_loading.value=true
            val respond=api.getData()
            if(respond.isSuccessful){
                respond.body()?.let {
                    data.value= listOf(it)
                    is_it_loading.value=false
                }
            }


        }


    }

}