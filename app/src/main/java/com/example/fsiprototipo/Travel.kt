package com.example.fsiprototipo

import java.security.PrivateKey

class Travel(private var price: Int, private var able: Boolean) {

    fun getFinalPrice(discount: Discount): Int {
        return (price - (price * discount.getPercentage()/100))
    }

    fun getAble(): Boolean {
        return able
    }

    fun getTravelAble(): String {
        return if (able) {
            "Si"
        } else {
            "No"
        }
    }
}