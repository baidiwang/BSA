import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InitializeGameCase {
    ArrayList<Team> teams = new ArrayList<>();

    public void readTextFile(String fileName) {
        /*
         * PRECONDITION: Each line of local file is of the form:
         * <Player ID>, <Player Name>, <Team Name>, <Offense>, <Defense>, <Total>
         *
         * POSTCONDITION: The lines of input.txt are in sernEntityInstances as Player instances respectively
         */
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    // Validate parts
                    validateLineParts(parts);
                    // Create Player with read parameters
                    Player player = new Player();
                    player.setId(parts[0]);
                    player.setName(parts[1]);
                    player.setOffense(Double.valueOf(parts[3]));
                    player.setDefense(Double.valueOf(parts[4]));
                    player.setTotal(Double.valueOf(parts[5]));

                    String teamName = parts[2];

                    // Check if the team exists
                    Team existsTeam = null;
                    for (Team team : teams) {
                        if (team.getName().equals(teamName)) {
                            existsTeam = team;
                            break;
                        }
                    }

                    // Add players if the team exists
                    if (existsTeam != null) {
                        existsTeam.addPlayer(player);
                    } else {
                        // Create a team if it does not exist
                        Team team = new Team();
                        team.setName(teamName);
                        team.addPlayer(player);
                        teams.add(team);
                    }
                } catch (InvalidLineStructureException e) {
                    e.printMessage();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validateLineParts(String[] someLineParts)
            throws InvalidLineStructureException {

        if (someLineParts[0].isEmpty()) {
            throw new InvalidLineStructureException("Blank player ID found.");
        }

        if (someLineParts[1].isEmpty()) {
            throw new InvalidLineStructureException("Blank player name found.");
        }

        if (someLineParts[2].isEmpty()) {
            throw new InvalidLineStructureException("Blank team name found.");
        }

        try {
            Double.parseDouble(someLineParts[3]);
        } catch(NumberFormatException e) {
            throw new InvalidLineStructureException("Offense should be decimal.");
        }

        try {
            Double.parseDouble(someLineParts[4]);
        } catch(NumberFormatException e) {
            throw new InvalidLineStructureException("Defense should be decimal.");
        }

        try {
            Double.parseDouble(someLineParts[5]);
        } catch(NumberFormatException e) {
            throw new InvalidLineStructureException("Total should be decimal.");
        }
    }

    public static void main(String[] args) {
        String fileName = "NBA_data.txt";
        InitializeGameCase initializeGameCase = new InitializeGameCase();
        initializeGameCase.readTextFile(fileName);
        for (Team team: initializeGameCase.teams) {
            System.out.println(team.toString());
        }

        TeamQuery teamQuery = new TeamQuery(initializeGameCase.teams, "MIN");
        Request<TeamQuery> teamQueryRequest = new Request<>(teamQuery);

        PlayerQuery playerQuery = new PlayerQuery(initializeGameCase.teams, "Anthony Davis");
        Request<PlayerQuery> playerQueryRequest = new Request<>(playerQuery);

        RequestRecord requestRecord = new RequestRecord();

        System.out.println("===================== Query Team Result ===================== ");
        Object queryTeam = requestRecord.makeRequest(teamQueryRequest);
        System.out.println(queryTeam.toString());

        System.out.println("=====================  Query Player Result ===================== ");
        Object queryPlayer = requestRecord.makeRequest(playerQueryRequest);
        System.out.println(queryPlayer.toString());

        System.out.println("=====================  Read Request Result ===================== ");
        requestRecord.readRequests();
    }
}
