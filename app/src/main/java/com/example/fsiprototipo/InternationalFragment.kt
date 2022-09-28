package com.example.fsiprototipo

import android.R
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.marginLeft
import com.example.fsiprototipo.databinding.FragmentInternationalBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import java.util.*

class InternationalFragment : Fragment(), AdapterView.OnItemSelectedListener, OnMapReadyCallback {

    private var _binding: FragmentInternationalBinding? = null
    private lateinit var countries: ArrayAdapter<Country>
    private lateinit var mMap: GoogleMap
    private lateinit var hotelsUSA: MutableList<Hotel>
    private lateinit var hotelsMEX: MutableList<Hotel>
    private var withoutDiscount: Discount = Discount("Sin descuento" , 0, true)
    private var discount: Discount = Discount("Vuela a USA", 10, true)
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentInternationalBinding.inflate(inflater, container, false)

        countries = ArrayAdapter<Country>(
            binding.root.context,
            R.layout.simple_spinner_dropdown_item)

        initialTravelUSA(Hotel("Santiago's Hotel", 200000, 10))
        hotelsUSA.add(Hotel("German's Hotel", 150000, 5))

        countries.add(Country("Estados Unidos", true, Travel(400000, true),
            Travel(0, false), Travel(2800000, true), hotelsUSA))

        initialTravelMEX(Hotel("Chachati's Hotel", 100000, 20))
        hotelsMEX.add(Hotel("Barco's Hotel", 300000, 15))

        countries.add(Country("México", false, Travel(400000, true),
            Travel(500000, true), Travel(0, false), hotelsMEX))

        binding.spinnerCountries.onItemSelectedListener = this
        binding.spinnerCountries.adapter = countries

        return binding.root
    }

    private fun initialTravelUSA(hotel: Hotel) {
        if(!::hotelsUSA.isInitialized) {
            hotelsUSA = LinkedList<Hotel>()
        }

        hotelsUSA.add(hotel)
    }

    private fun initialTravelMEX(hotel: Hotel) {
        if(!::hotelsMEX.isInitialized) {
            hotelsMEX = LinkedList<Hotel>()
        }

        hotelsMEX.add(hotel)
    }

    private fun setUpTable(hotels: MutableList<Hotel>) {
        binding.tableLayout.removeAllViews()
        val tableTitle = TableRow(requireContext())

        val textName = TextView(requireContext())
        textName.text = "   Nombre   "
        textName.setTextColor(Color.BLACK)
        textName.textSize = 19F
        tableTitle.addView(textName)

        val textPercentage = TextView(requireContext())
        textPercentage.text = "   Precio   "
        textPercentage.setTextColor(Color.BLACK)
        textPercentage.textSize = 19F
        tableTitle.addView(textPercentage)

        val textActive = TextView(requireContext())
        textActive.text = "   Disponibilidad   "
        textActive.setTextColor(Color.BLACK)
        textActive.textSize = 19F
        tableTitle.addView(textActive)

        binding.tableLayout.addView(tableTitle)

        for (i in 0 until hotels.size) {

            val tableData = TableRow(requireContext())
            val textData = TextView(requireContext())
            textData.text = hotels[i].getName() + ":"
            textData.textSize = 14F
            textData.id = View.generateViewId()
            tableData.addView(textData)

            val textData2 = TextView(requireContext())
            textData2.text = hotels[i].getPrice().toString() + " COP"
            textData2.gravity = Gravity.CENTER
            textData2.textSize = 14F
            textData2.marginLeft
            tableData.addView(textData2)

            val textData3 = TextView(requireContext())
            textData3.text = "Habitaciones: " + hotels[i].getRoomsAble().toString()
            textData3.gravity = Gravity.CENTER
            textData3.textSize = 14F
            tableData.addView(textData3)

            binding.tableLayout.addView(tableData)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        binding.documentsrequire.text = countries.getItem(p2)!!.getInformation()

        if(p2 == 0) {
            setUpTable(hotelsUSA)
        } else if(p2 == 1) {
            setUpTable(hotelsMEX)
        }

        binding.priceGround.setOnClickListener {
            if(countries.getItem(p2)!!.getTravelByGround().getAble()) {
                binding.price.text = countries.getItem(p2)!!.getPriceTravelByGround(withoutDiscount).toString() + " COP"
            } else {
                binding.price.text = "No disponible."
            }
        }

        binding.priceSee.setOnClickListener {
            if(countries.getItem(p2)!!.getTravelBySee().getAble()) {
                binding.price.text = countries.getItem(p2)!!.getPriceTravelBySee(withoutDiscount).toString() + " COP"
            } else {
                binding.price.text = "No disponible."
            }
        }

        binding.priceAir.setOnClickListener {
            if(countries.getItem(p2)!!.getTravelByAir().getAble() &&
                countries.getItem(p2)!!.getName() == "Estados Unidos") {
                binding.price.text = countries.getItem(p2)!!.getPriceTravelByAir(discount).toString() + " COP"
            } else if (countries.getItem(p2)!!.getTravelByAir().getAble()) {
                binding.price.text = countries.getItem(p2)!!.getPriceTravelByAir(withoutDiscount).toString() + " COP"
            } else {
                binding.price.text = "No disponible."
            }

        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }
}