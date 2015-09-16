import wordgame.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

/* 
 * PuzzleObj is an object representing a puzzle.
 * It has a no-argument constructor used like so: PuzzleObj puzzle = new PuzzleObj();
 *
 * puzzle.getClues();
 *   returns a list of (String) clues for the puzzle, sorted alphabetically.
 *   -> {"egeading", "eping", "ras", "tist"}
 * 
 * puzzle.check(String guess);
 *   returns true if guess is the solution to the puzzle (ignores case)
 *   returns false otherwise
 *
 * puzzle.toString();
 *   returns the raw string representation of the puzzle (not intended for use outside of a debug situation)
 *   -> "renegotiable : renegading, egotist, tiaras, bleeping : -egading, -tist, -ras, -eping"
 *
 */
public class PuzzleObj {
    private String rawPuzz;
    private String solution;
    private List<String> clueWords;
    private List<String> clues;

    public PuzzleObj () {
        boolean done = false;
        while (!done) {
            this.rawPuzz = Puzzle.generatePuzzle();
            this.solution = extractSolution(rawPuzz);
            if (this.solution.length() == 9) {
                this.clueWords = extractClueWords(rawPuzz);
                this.clues = extractClues(rawPuzz);
                done = true;
            }
        }
    }

    public List<String> getClues() {
        return this.clues;
    }

    public boolean check(String guess) {
        return this.solution.equalsIgnoreCase(guess);
    }

    @Override
    public String toString() {
        return this.rawPuzz;
    }


    /* helpers for constructor */
    private String extractSolution(String rawPuzz) {
        return rawPuzz.substring(0, rawPuzz.indexOf(" "));
    }

    private List<String> extractClueWords(String rawPuzz) {
        String s = rawPuzz.substring(rawPuzz.indexOf(":") + 2, rawPuzz.lastIndexOf(":") - 1).replace(",","");
        List<String> lst = new LinkedList<String>(Arrays.asList(s.split(" ")));

        return lst;
    }

    private List<String> extractClues(String rawPuzz) {
        String s = rawPuzz.substring(rawPuzz.lastIndexOf(":") + 2, rawPuzz.length()).replace(",","").replace("-","");
        List<String> lst = new LinkedList<String>(Arrays.asList(s.split(" ")));

        /* TODO: play around with comperators and stuff */
        java.util.Collections.sort(lst);

        return lst;
    }
    private List<String> removePrefix(int n, List<String>words) {
        List<String> lst = new LinkedList<String>(words);

        /* TODO: do it */

        return lst;
    }
}

