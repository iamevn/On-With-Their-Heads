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
          index = retrieveFile("index.html");
        }catch(Exception e){
          e.printStackTrace();
        }

        return index;
     });
  }

  private static String retrieveFile(String file) throws Exception{
    InputStream in = null;
    StringBuffer resource = new StringBuffer();
    int i;
    char c;
    file = "public/" + file;
    try{
      in = Bootstrap.class.getClassLoader().getResourceAsStream(file);

      while((i=in.read())!=-1 ){
        c=(char)i;
        resource.append(c);
      }
    }catch(Exception e){
      e.printStackTrace();

    }finally{
      if(in != null)
        in.close();

    }

   return resource.toString();
  }

}
