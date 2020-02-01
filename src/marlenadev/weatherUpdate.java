package marlenadev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class weatherUpdate {
	private final String rssFeed = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2643123";
	int getRssFeed() {
	        try{
	            URL rssUrl = new URL (rssFeed);
	            BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
	            String ourLine = "";
	            String line;

	            while((line=in.readLine())!=null){
	            	if ( line.contains("<description>Minimum Temperature:") ) {
	            		ourLine = line;
	            	}
	            }
	            
	            System.out.println(ourLine);
	            int Farenheit = getFarenhite(ourLine);
	            
	            //TODO: Convert to Celsius
	            
	            return Farenheit;
	        } catch (IOException ue) {
				System.out.println("No internet connection");
	        }
		return 0;
	}
	
	int getFarenhite(String line) {
		int startPosition = line.indexOf("(")+1;
		int endPosition = line.indexOf(")")-3;
		
		String far = line.substring(startPosition, endPosition);
		
		return Integer.valueOf(far);
	}

}
