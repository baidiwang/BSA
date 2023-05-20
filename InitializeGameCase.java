package uk.ac.bham.BSA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InitializeGameCase {
    ArrayList<Game> games = new ArrayList<>();

    public void readTextFile(String fileName) {
        /*
         * PRECONDITION: Each line of local file is of the form:
         * <game date>, <game location>, <Team 1 Name>, <Play 1>, <Play 2>, <Play 3>, <Play 4>, <Play 5>, <Team 2 Name>, <Play 1>, <Play 2>, <Play 3>, <Play 4>, <Play 5>
         *
         * POSTCONDITION: The lines of input.txt are in sernEntityInstances as Game instances respectively
         */
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                // TODO Create Game with read parameters
                Game game = new Game();
                games.add(game);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String fileName = "input.text";
        InitializeGameCase initializeGameCase = new InitializeGameCase();
        initializeGameCase.readTextFile(fileName);
        for (Game game: initializeGameCase.games) {
            System.out.println(game.getStats()); //read all data
        }
    }
}
