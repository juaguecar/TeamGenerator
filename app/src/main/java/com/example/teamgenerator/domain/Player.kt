package com.example.teamgenerator.domain

data class Player(val name: String, val value: Int){

    override fun toString(): String {
        return name
    }
}