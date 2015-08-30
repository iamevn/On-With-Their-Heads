package sparkproject;
import wordgame.*;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;
public class Bootstrap {

    public static void main(String[] args) {
        get("/", (req, res) -> {
            return "Your puzzle: " + PuzzleGen.create();
        });
    }

}
