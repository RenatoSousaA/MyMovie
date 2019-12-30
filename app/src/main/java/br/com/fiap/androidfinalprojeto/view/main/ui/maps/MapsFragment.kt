package br.com.fiap.androidfinalprojeto.view.main.ui.maps

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.fiap.androidfinalprojeto.R
import br.com.fiap.androidfinalprojeto.view.main.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_maps.*
import java.net.URI
import java.util.*
import java.util.jar.Manifest

class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapsViewModel: MapsViewModel
    private var mMap: GoogleMap? = null

    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mapsViewModel =
            ViewModelProviders.of(this).get(MapsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_maps, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        return root
    }

    override fun onResume() {
        super.onResume()
        btnMaps.setOnClickListener {
            val key = "cinema"
            val geo = "geo:0,0?q=$key&z=12"
            val geoURI = Uri.parse(geo)
            val intent = Intent( Intent.ACTION_VIEW, geoURI)
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val geocoder = Geocoder(activity?.applicationContext, Locale.getDefault())

        locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        initLocationListener()
        requestLocationUpdates()

        val long = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).longitude
        val lati = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).latitude

        val UCA = LatLng(lati, long)

        mMap?.addMarker(MarkerOptions().position(UCA).title("Você está aqui!"))?.showInfoWindow()
        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(UCA, 15f))
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
    }

    private fun initLocationListener() {
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {

            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

            }

            override fun onProviderEnabled(provider: String?) {

            }

            override fun onProviderDisabled(provider: String?) {

            }
        }
    }
}