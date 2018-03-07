/**
 * Checks URLs in the CSV file, gets HTTP response codes for each one. Prints number of broken links and response code.
 * 
 * @bengibaykal
 * @07.03.2018
 */

 import java.io.IOException;
 import java.net.URL;
 import java.net.HttpURLConnection;
 import edu.duke.*;
 import org.apache.commons.csv.*;

public class ResponseCodeCheckFromFile {
    public static void main (String args[]) throws Exception
    {   
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = null;
        int counter = 0;
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
            counter++;
            }  
        }
        
        if (counter !=0 ){
         System.out.println("Number of broken links in this file : " + counter );
        }
    }
}
