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
    index = retrieveFile("head.txt", index); 
    index = retrieveFile("onwiththeirheads.js", index);
    index = retrieveFile("tags.txt", index);
    index = retrieveFile("style.css", index);
    index = retrieveFile("body.txt", index);
    return index.toString();
  }

  private static StringBuffer retrieveFile(String file, StringBuffer page) throws Exception{
    InputStream in = null;
    int i;
    char c;
    file = "public/" + file;
    try{
      in = Bootstrap.class.getClassLoader().getResourceAsStream(file);

      while((i=in.read())!=-1 ){
        c=(char)i;
        page.append(c);
      }
    }catch(Exception e){
      e.printStackTrace();

    }finally{
      if(in != null)
        in.close();

    }

   return page;
  }

}
