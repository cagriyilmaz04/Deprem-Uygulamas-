package com.example.depremuygulamas.view

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.depremuygulamas.R
import com.example.depremuygulamas.databinding.FragmentSecondBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class SecondFragment : Fragment(R.layout.fragment_second),OnMapReadyCallback {

    lateinit var map:GoogleMap
    private val args by navArgs<SecondFragmentArgs>()
    private var _binding:FragmentSecondBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentSecondBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        map= p0
        map.uiSettings.apply {
            isZoomControlsEnabled=true
            isScrollGesturesEnabled=true
        }
        val deprem_yeri = LatLng(args.sendclass.main_data.lat, args.sendclass.main_data.lng)
        map.addMarker(
            MarkerOptions()
                .position(deprem_yeri)
                .title("Depremin Gerçekleştiği Yer"))

        binding.textView.text="Depremin Gerçekleştiği Yer : ${args.sendclass.main_data.lokasyon}\nDepremin Büyüklüğü :${args.sendclass.main_data.mag}\nGerçekleştiği Zaman: ${args.sendclass.main_data.date}\nDerinlik :${args.sendclass.main_data.depth} KM\n"

        map.moveCamera(CameraUpdateFactory.newLatLng(deprem_yeri))

    }

}