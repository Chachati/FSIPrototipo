package com.example.fsiprototipo

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.marginLeft
import androidx.fragment.app.Fragment
import com.example.fsiprototipo.databinding.FragmentDiscountsBinding
import java.util.LinkedList


class DiscountsFragment : Fragment() {
    private var _binding: FragmentDiscountsBinding? = null
    private lateinit var discounts: MutableList<Discount>
    private lateinit var discount: Discount
    private lateinit var tableData: TableRow
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentDiscountsBinding.inflate(inflater, container, false)

        initialDiscounts(Discount("Verano", 20, false))
        initialDiscounts(Discount("Invierno", 10, false))
        initialDiscounts(Discount("Vuela a USA", 10, true))

        setUpTable()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initialDiscounts(discount: Discount) {
        if(!::discounts.isInitialized) {
            discounts = LinkedList<Discount>()
        }

        discounts.add(discount)
    }

    private fun setUpTable() {
        val tableTitle = TableRow(requireContext())

        val textName = TextView(requireContext())
        textName.text = "  Nombre  "
        textName.setTextColor(Color.BLACK)
        textName.textSize = 18F
        tableTitle.addView(textName)

        val textPercentage = TextView(requireContext())
        textPercentage.text = "  Porcentaje  "
        textPercentage.setTextColor(Color.BLACK)
        textPercentage.textSize = 18F
        tableTitle.addView(textPercentage)

        val textActive = TextView(requireContext())
        textActive.text = "  Disponibilidad  "
        textActive.setTextColor(Color.BLACK)
        textActive.textSize = 18F
        tableTitle.addView(textActive)

        binding.tableLayout.addView(tableTitle)

        for (i in 0 until discounts.size) {

            tableData = TableRow(requireContext())
            val textData = TextView(requireContext())
            textData.text = discounts[i].getName()
            textData.textSize = 22F
            textData.id = View.generateViewId()
            tableData.addView(textData)

            val textData2 = TextView(requireContext())
            textData2.text = discounts[i].getPercentage().toString() + "%"
            textData2.gravity = Gravity.CENTER
            textData2.textSize = 22F
            textData2.marginLeft
            tableData.addView(textData2)

            val textData3 = TextView(requireContext())
            textData3.text = discounts[i].isActiveToString()
            textData3.gravity = Gravity.CENTER
            textData3.textSize = 22F
            tableData.addView(textData3)

            binding.tableLayout.addView(tableData)
        }
    }

    fun existsDiscountActive(): Boolean {
        var result: Boolean = false
        var discountsActive: Int = 0

        for(i in 0 until discounts.size) {
            discountsActive += 1
        }
        if(discountsActive != 0) {
            result = true
        }

        return result
    }

    fun discountActive(): Discount {
        return discount
    }

    fun View?.removeSelf() {
        this ?: return
        val parentView = parent as? ViewGroup ?: return
        parentView.removeView(this)
    }
}