package com.example.fsiprototipo

class Discount(private var name: String, private var percentage: Int, private var active: Boolean) {
    fun getName(): String {
        return name
    }

    fun getPercentage(): Int {
        return percentage
    }

    fun isActive(): Boolean {
        return active
    }

    fun isActiveToString(): String {
        return if(active) {
            "Activado"
        } else {
            "Desactivado"
        }
    }

    fun setActive(active: Boolean) {
        this.active = active
    }
}