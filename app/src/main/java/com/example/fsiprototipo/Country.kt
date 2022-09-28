package com.example.fsiprototipo

class Country(private var name: String, private var visa: Boolean, private var travelBySee: Travel,
              private var travelByGround: Travel, private var travelByAir: Travel,
              private var hotels: MutableList<Hotel>) {

    fun getTravelByGround(): Travel {
        return travelByGround
    }

    fun getTravelBySee(): Travel {
        return travelBySee
    }

    fun getTravelByAir(): Travel {
        return travelByAir
    }

    fun getPriceTravelByGround(discount: Discount): Int {
        return travelByGround.getFinalPrice(discount)
    }

    fun getPriceTravelBySee(discount: Discount): Int {
        return travelBySee.getFinalPrice(discount)
    }

    fun getPriceTravelByAir(discount: Discount): Int {
        return travelByAir.getFinalPrice(discount)
    }

    fun getName(): String {
        return name
    }

    fun getInformation(): String {
        return "País: $name.\n" +
                "Visa: ${getBoolean(visa)}.\n" +
                "Viaje por tierra: ${travelByGround.getTravelAble()}.\n" +
                "Viaje por oceano: ${travelBySee.getTravelAble()}.\n" +
                "Viaje por aire: ${travelByAir.getTravelAble()}."
    }

    override fun toString(): String {
        return name
    }

    private fun getBoolean(boolean: Boolean): String {
        return if(boolean) {
            "Si"
        } else {
            "No"
        }
    }
}