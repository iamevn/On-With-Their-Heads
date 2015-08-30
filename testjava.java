import wordgame.*;

class testjava {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            /* Puzzle p = new Puzzle(); */
            /* String puzzle = p.myToString(); */
            /* System.out.println(puzzle); */
            System.out.println(Puzzle.generatePuzzle());
        }
    }
}

