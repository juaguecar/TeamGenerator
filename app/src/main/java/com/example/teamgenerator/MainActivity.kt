package com.example.teamgenerator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.teamgenerator.databinding.ActivityMainBinding
import com.example.teamgenerator.domain.Player
import com.example.teamgenerator.domain.Team
import kotlin.random.Random

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
        drawTeams(mapTeamEquals(playerList))
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

    private fun mapTeamEquals(playerList: MutableList<Player>): MutableList<Team> {
        playerList.sortBy { it.value }
        val team1 = Team(mutableListOf())
        val team2 = Team(mutableListOf())

        val cloneIterableList = playerList.toMutableList()
        for (player: Player in cloneIterableList) {
            if (team1IsBetter(team1, team2)) {
                val bestPlayer = playerList.maxByOrNull { it.value }
                team2.addPlayer(bestPlayer)
                playerList.remove(bestPlayer)
                val worstPlayer = playerList.minByOrNull { it.value }
                team1.addPlayer(worstPlayer)
                playerList.remove(worstPlayer)
            } else {
                val bestPlayer = playerList.maxByOrNull { it.value }
                team1.addPlayer(bestPlayer)
                playerList.remove(bestPlayer)
                val worstPlayer = playerList.minByOrNull { it.value }
                team2.addPlayer(worstPlayer)
                playerList.remove(worstPlayer)
            }


        }
        getEqualTeams(team1, team2);
        val difference = team1.getValue() - team2.getValue()

        return mutableListOf(team1, team2)
    }

    private fun getEqualTeams(team1: Team, team2: Team) {
        var playerList = mutableListOf<Player>()
        

    }


    private fun team1IsBetter(team1: Team, team2: Team): Boolean {
        return team1.getValue() >= team2.getValue()
    }

    private fun mapPlayers(playerListString: MutableList<String>): MutableList<Player> {
        val listOfPlayer = mutableListOf<Player>()
        for (playerString in playerListString) {
            listOfPlayer.add(Player(playerString, Random.nextInt(0, 10)))
        }
        return listOfPlayer;
    }
}