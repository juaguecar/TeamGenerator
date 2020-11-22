package com.example.teamgenerator.domain

data class Team(val players: List<Player>) {
    val teamNames = listOf("Blind Hawks", "No motion Boys", "Wannamaker idols", "Athletic Useless")

    override fun toString(): String {
        return teamNames[(0..3).shuffled().first()].toString()+":"+players
    }

    public fun getValue(): Int {
        var value: Int = 0
        for (player in players) {
            value += player.value;
        }

        return value / players.size.toInt();
    }
}