import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class TeamQuery extends Action implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Team> teams;
    private String name;

    private Team team;

    public TeamQuery(ArrayList<Team> teams, String name) {
        this.teams = teams;
        this.name = name;
    }

    @Override
    public Object getQuery() {
        return team;
    }

    @Override
    public void run() {
        Optional<Team> optionalTeam = teams.stream().filter(team -> team.getName().equals(name)).findFirst();
        team = optionalTeam.orElse(null);

        try {
            Thread.sleep(1000); // Pause for 1 seconds
        } catch (InterruptedException e) {
            System.out.println("Team Query thread interrupted");
        }
    }

//    @Override
//    public Team performAction() {
//        Optional<Team> optionalTeam = teams.stream().filter(team -> team.getName().equals(name)).findFirst();
//        return optionalTeam.orElse(null);
//    }

    @Override
    public String toString() {
        return "Team query request with target name " + name;
    }
}
