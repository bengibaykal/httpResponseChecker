 /**
 * Receives URLs in the choosen CSV file, gets HTTP response code for each one. Saves number the broken links with response code in a new CSV file named "brokenLinks.csv".
 * 
 * @bengibaykal
 * @07.03.2018
 */



 import java.io.IOException;
 import java.net.URL;
 import java.net.HttpURLConnection;
 import edu.duke.*;
 import org.apache.commons.csv.*;
 import java.io.PrintWriter;
 import java.io.File;


public class ResponseCodeCheckFromFile {
    public static void main (String args[]) throws Exception
    {   
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = null;
        int counter = 0;
        
        PrintWriter pw = new PrintWriter(new File("brokenLinks.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("brokenLinks");
        sb.append(',');
        sb.append("responseCode");
        sb.append('\n');
        
        for(CSVRecord currentRow : parser){
           
           //System.out.println(currentRow);
            URL url = new URL((currentRow.get("url")).toString());
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int code = connection.getResponseCode();
        
            //System.out.println("Response code of the " + url + " is : " +code);
            if (code != 200)
            {
              System.out.println("Reponse for the URL " + url  +":  NOK with the code " + code );
            
              sb.append(url);
              sb.append(',');
              sb.append(code);
              sb.append('\n');

              counter++;
            }  
        }
        
        if (counter !=0 ){
         System.out.println("Number of broken links in this file : " + counter );
        }
         pw.write(sb.toString());
         pw.close();
    }
}
