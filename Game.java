package uk.ac.bham.BSA;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Team> teamList = new ArrayList<>();

    public Game() {}

    public Game(List<Team> teamList) {
        this.teamList = teamList;
    }

    public Stats getStats() {
        return null;
    };

    public List<Team> getTeams() {
        return null;
    }
}
