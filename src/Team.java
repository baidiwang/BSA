import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Team implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public List<Player> getPlayerList() { return playerList; }

    public String toString() {
        String str = "Team Name: " + name + "\n";
        str += "Player List: \n";
        for (Player player: playerList) {
            str += player.toString();
        }
        return str;
    }
}
