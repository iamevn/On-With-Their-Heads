import wordgame.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

/* renegotiable : renegading, egotist, tiaras, bleeping : -egading, -tist, -ras, -eping */
public class PuzzleObj {
    private String rawPuzz;
    private String solution;
    private List<String> clueWords;
    private List<String> clues;

    public PuzzleObj () {
        this.rawPuzz = Puzzle.generatePuzzle();
        this.solution = extractSolution(rawPuzz);
        this.clueWords = extractClueWords(rawPuzz);
        this.clues = extractClues(rawPuzz);
    }

    public List<String> getClues() {
        return this.clues;
    }

    public Boolean check(String guess) {
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

        return lst;
    }
    private List<String> removePrefix(int n, List<String>words) {
        List<String> lst = new LinkedList<String>(words);

        /* TODO: do it */

        return lst;
    }
}

