package uk.ac.bham.BSA;

import java.util.ArrayList;
import java.util.List;

public class Team {
    List<Player> playerList = new ArrayList<>();

    public Team(List<Player> playerList) {
        this.playerList = playerList;
    }
}
