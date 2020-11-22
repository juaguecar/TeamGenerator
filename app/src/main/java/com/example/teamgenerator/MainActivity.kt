package com.example.teamgenerator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.teamgenerator.databinding.ActivityMainBinding
import com.example.teamgenerator.domain.Player
import com.example.teamgenerator.domain.Team

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTeams() }
    }

    private fun calculateTeams() {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val playerListString = stringInTextField.split(",").toMutableList()
        val playerList = mapPlayers(playerListString);
        playerList.shuffle()
        drawTeams(mapTeams(playerList))
    }

    private fun drawTeams(teams: MutableList<Team>) {
        binding.team1PlayerList.text = teams[0].toString()
        binding.team2PlayerList.text = teams[1].toString()
    }

    private fun mapTeams(playerList: MutableList<Player>): MutableList<Team> {
        val mid = (playerList.size / 2).toInt()
        val team1 = Team(playerList.subList(0, mid));
        val team2 = Team(playerList.subList(mid, playerList.size));
        return mutableListOf(team1, team2)
    }

    private fun mapPlayers(playerListString: MutableList<String>): MutableList<Player> {
        val listOfPlayer = mutableListOf<Player>()
        for (playerString in playerListString) {
            listOfPlayer.add(Player(playerString))
        }
        return listOfPlayer;
    }
}