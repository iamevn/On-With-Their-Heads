package sparkproject;
import wordgame.*;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;
import java.io.PrintWriter;
import java.io.InputStream;
import java.lang.StringBuffer;
import java.lang.StringBuffer;
import java.lang.Exception;

public class Bootstrap {

  public static void main(String[] args) throws Exception{
    staticFileLocation("/public"); 
    get("/", (req, res) -> {
        String index = "";
        String jsfile = "";
        String index_file = "public/index.html";
        String js_file = "public/js/onwiththeirheads.js";
        try{
          index = retrieveIndexFile(index_file);
          js_file = retrieveIndexFile(js_file);
        }catch(Exception e){
          e.printStackTrace();
        }

        PuzzleObj puzzle = new PuzzleObj();
       /* 
        index.replaceAll("\\b<p class='hide-text clues'>\\b", "$1\n"+ "ok" + puzzle.getClues() + "\n");
        index.replaceAll("\\b<p class='hide-text answers'>\\b", "$1\n" + "work" +  puzzle.toString() + "\n");
        index.replaceAll("\\bHAPPYNOW\\b", "<p>" + puzzle.getClues() + "\n" + puzzle.toString()+ "</p>" + "\n");
        */
        //index.replaceAll("<p class=\"hide-text clues\">", "$1\n"+ "ok" + puzzle.getClues() + "\n");
        //index.replaceAll("<p class=\"hide-text answers'\">", "$1\n" + "work" +  puzzle.toString() + "\n");
        // index.replaceAll("HAPPYNOW", "<p>" + puzzle.getClues() + "\n" + puzzle.toString()+ "</p>" + "\n");

        //String data = "<p>" + puzzle.getClues() + " ::::::::::::::::::: " + puzzle.toString()+ "</p>" + "\n";
        String number;
        for (String clue : puzzle.getClues()){
          index = index.replaceFirst("(_*)insertclue(_*)", clue);
        }
        /*
        String[] clues = raw_clues.split(",");
        String clue1 = clues[0].replace("[","");
        String clue2 = clues[1];
        String clue3 = clues[2].replace("]","");
        index.replaceAll("insertclue1", clue1);
        index.replaceAll("insertclue2", clue2);
        index.replaceAll("insertclue3", clue3);
        */
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
