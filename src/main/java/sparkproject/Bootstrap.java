package sparkproject;
import wordgame.*;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;
import java.io.PrintWriter;
import java.io.InputStream;
import java.lang.StringBuffer;
import java.lang.Exception;

public class Bootstrap {

  public static void main(String[] args) throws Exception{
    staticFileLocation("/public"); 
    get("/", (req, res) -> {
        String index = "";
        try{
          index = retrieveIndexFile();
        }catch(Exception e){
          e.printStackTrace();
        }

        PuzzleObj puzzle = new PuzzleObj();
        index.replaceAll("<p class=\"hide-text clues\">", "$1\n" + puzzle.getClues() + "\n");
        index.replaceAll("<p class=\"hide-text answers\">", "$1\n" + puzzle.toString() + "\n");

        return index;
     });
    get("/clues", (req, res) -> {
        //return "Your puzzle: " + PuzzleGen.create();
        PuzzleObj puzzle = new PuzzleObj();
        return puzzle.getClues();
     });
  }

  public static String retrieveIndexFile() throws Exception{
    StringBuffer index = new StringBuffer();
    InputStream in = null;
    int i;
    char c;
    String index_file = "public/index.html";
    try{
      in = Bootstrap.class.getClassLoader().getResourceAsStream(index_file);

      while((i=in.read())!=-1 ){
        c=(char)i;
        index.append(c);
      }
    }catch(Exception e){
      e.printStackTrace();

    }finally{
      if(in != null)
        in.close();
    }
   return index.toString();
  }
}
