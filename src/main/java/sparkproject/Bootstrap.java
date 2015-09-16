package sparkproject;
import wordgame.*;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;
import java.io.InputStream;
import java.lang.StringBuffer;
import java.lang.Exception;

public class Bootstrap {

  public static void main(String[] args) throws Exception{
    staticFileLocation("/public"); 
    get("/", (req, res) -> {
        //return "Your puzzle: " + PuzzleGen.create();

        String index = "";
        try{
          index = retrieveIndexFile();
        }catch(Exception e){
          e.printStackTrace();
        }

        return index;
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
