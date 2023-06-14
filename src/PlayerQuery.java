import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class PlayerQuery extends Action implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Team> teams;
    private String name;

    private Player player;

    public PlayerQuery(ArrayList<Team> teams, String name) {
        this.teams = teams;
        this.name = name;
    }

    @Override
    public void run() {
        Optional<Team> optionalTeam = teams.stream().filter(team -> team.getPlayerList().stream().filter(player -> player.getName().equals(name)).count() > 0).findFirst();
        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();
            Optional<Player> optionalPlayer = team.getPlayerList().stream().filter(player -> player.getName().equals(name)).findFirst();
            player = optionalPlayer.orElse(null);

            try {
                Thread.sleep(1000); // Pause for 1 seconds
            } catch (InterruptedException e) {
                System.out.println("Player Query thread interrupted");
            }
        }
    }

    @Override
    public Player getQuery() {
        return player;
    }

//    @Override
//    public Player performAction() {
//        Optional<Team> optionalTeam = teams.stream().filter(team -> team.getPlayerList().stream().filter(player -> player.getName().equals(name)).count() > 0).findFirst();
//        if (optionalTeam.isPresent()) {
//            Team team = optionalTeam.get();
//            Optional<Player> optionalPlayer = team.getPlayerList().stream().filter(player -> player.getName().equals(name)).findFirst();
//            return optionalPlayer.orElse(null);
//        }
//        return null;
//    }

    @Override
    public String toString() {
        return "Player query request with target name " + name;
    }
}
