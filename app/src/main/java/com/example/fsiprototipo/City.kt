package com.example.fsiprototipo

class City(private var name: String, private var hotels: MutableList<Hotel>,
           private var travelByGround: Travel,private var travelByAir: Travel) {

    fun getTravelByGround(): Travel {
        return travelByGround
    }

    fun getTravelByAir(): Travel {
        return travelByAir
    }

    fun getPriceTravelByGround(discount: Discount): Int {
        return travelByGround.getFinalPrice(discount)
    }

    fun getPriceTravelByAir(discount: Discount): Int {
        return travelByAir.getFinalPrice(discount)
    }

    fun getName(): String {
        return name
    }

    override fun toString(): String {
        return name
    }
}