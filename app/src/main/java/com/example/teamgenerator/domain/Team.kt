package com.example.teamgenerator.domain

data class Team(val players: MutableList<Player>) {
    val TEAMNAMES = listOf("Blind Hawks", "No motion Boys", "Wannamaker idols", "Athletic Useless")

    override fun toString(): String {
        return TEAMNAMES[(0..3).shuffled().first()].toString() + ":" + players + getValue()
    }

    public fun addPlayer(player: Player?) {
        if (player != null)
            players.add(player)
    }

    public fun getValue(): Int {
        var value = 0
        if (teamIsEmpty()) return value;
        for (player in players)
            value += player.value
        return value / players.size;
    }

    private fun teamIsEmpty(): Boolean {
        return players.size == 0;

    }
}