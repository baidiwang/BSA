package src;

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


//    public class GenericTeam<G> {
//        private G 0;
//        private String teamName;
//        public GenericTeam(G 0, String teamName) {
//            this.0 = 0;
//            this.teamName = teamName;
//        }
//        public G getObject() {
//            return 0;
//        }
//        public static void main(String[] args) {
//            GenericTeam<String> name = new GenericTeam<String>("BOS", "BOS");
//            GenericTeam<Integer> grades = new GenericTeam<Integer>(4.80 "four point eight zero");
//
//            System.out.println("name object = 4.80? => " + (name.getObject() == 4.80 ));
//            System.out.println("name object = BOS? => " + (grades.getObject().equals("BOS")));
//        }
//    }


    public static void main(String[] args) {
        String fileName = "NBA_data.txt";
        InitializeGameCase initializeGameCase = new InitializeGameCase();
        initializeGameCase.readTextFile(fileName);
        for (Team team: initializeGameCase.teams) {
            System.out.println(team.toString());
        }
    }
}
