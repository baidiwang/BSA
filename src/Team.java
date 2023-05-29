package src;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;

    List<Player> playerList = new ArrayList<>();

    public Team() {}

    public Team(List<Player> playerList) {
        this.playerList = playerList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public String toString() {
        String str = "Team Name: " + name + "\n";
        str += "Player List: \n";
        for (Player player: playerList) {
            str += player.toString();
        }
        return str;
    }
}