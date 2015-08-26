/* import wordgame.core.*; */

class testjava {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            String puzzle = wordgame.core.genPuzz();
            System.out.println(puzzle);
        }
    }
}
