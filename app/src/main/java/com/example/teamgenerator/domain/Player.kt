package com.example.teamgenerator.domain

data class Player(val name: String){
    var value: Int = 0

    override fun toString(): String {
        return name
    }
}