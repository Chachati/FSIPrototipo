package com.example.fsiprototipo

class Hotel(private var name: String, private var price: Int, private var roomsAble: Int) {
    fun getName(): String {
        return name
    }

    fun getPrice(): Int {
        return price
    }

    fun getRoomsAble(): Int {
        return roomsAble
    }
}