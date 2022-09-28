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
import com.example.fsiprototipo.databinding.FragmentInternationalBinding
import com.example.fsiprototipo.databinding.FragmentNationalBinding
import java.util.*

/**
 * A fragment representing a list of Items.
 */
class NationalFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentNationalBinding? = null
    private val binding get() = _binding!!
    private var withoutDiscount: Discount = Discount("Sin descuento" , 0, true)

    private lateinit var cities: ArrayAdapter<City>
    private lateinit var hotelsIbague: MutableList<Hotel>
    private var hotelsCali: MutableList<Hotel> = LinkedList<Hotel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentNationalBinding.inflate(inflater, container, false)

        setUpHotels()
        setUpCities()

        binding.spinnerCitiesFrom.onItemSelectedListener = this
        binding.spinnerCitiesTo.onItemSelectedListener = this
        binding.spinnerCitiesFrom.adapter = cities
        binding.spinnerCitiesTo.adapter = cities

        return binding.root
    }

    private fun setUpCities() {
        cities = ArrayAdapter<City>(
            binding.root.context,
            R.layout.simple_spinner_dropdown_item)

        cities.add(City("Ibagué", hotelsIbague, Travel(0, false), Travel(0, false)))
        cities.add(City("Calí", hotelsIbague, Travel(0, false), Travel(0, false)))
    }

    private fun setUpHotels() {
        initialHotelIbague(Hotel("Hotel Sonesto", 320000, 4))
        hotelsIbague.add(Hotel("Hotel F25", 200000, 10))

        hotelsCali.add(Hotel("NH Cali Royal", 217400, 15))
        hotelsCali.add(Hotel("Hotel Sonesta", 314500, 2))
    }

    private fun initialHotelIbague(hotel: Hotel) {
        if(!::hotelsIbague.isInitialized) {
            hotelsIbague = LinkedList<Hotel>()
        }

        hotelsIbague.add(hotel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        binding.informationHotels.textSize = 26F
        binding.informationHotels.setTextColor(Color.BLACK)

        if(binding.spinnerCitiesFrom.selectedItem.toString() == binding.spinnerCitiesTo.selectedItem.toString()) {
            binding.informationHotels.text = "\nSon el mismo lugar.\n"
        } else if(binding.spinnerCitiesTo.selectedItem.toString() == "Calí" && binding.spinnerCitiesFrom.selectedItem.toString() == "Ibagué") {
            binding.informationHotels.text = "Precios Bus:\nEconómico: 38000\nPrimera Clase: 88000"
        } else if(binding.spinnerCitiesFrom.selectedItem.toString() == "Calí" && binding.spinnerCitiesTo.selectedItem.toString() == "Ibagué") {
            binding.informationHotels.text = "Precios Bus:\nEconómico: 62000\nPrimera Clase: 80000"
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}