import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.json.*;

public interface Scoreboard { 
	
	String name = "chopper";
	String password = "totallysecretpassword";
	String path = "http://scoreboard.ddnss.ch/";
	
	default int[] push(String username, int score) {
		// Takes username and score as input and returns the rank of this score (-1 if server is not reachable) and the total amount of entries
		
		int[] output = new int[2];
		
		try{
			URL url = new URL(path+"push?name="+name+"&password="+password+"&username="+username+"&score="+score);
	        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			JSONObject result = new JSONObject(in.readLine());
			output[0] = (int) result.get("rank");
			output[1] = (int) result.get("total");
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return output;
		
	}
	
	default String[][] players() {
		// Returns a list of players and their scores, sorted by score
		
		String[][] data = null;
		
		try{
			URL url = new URL(path+"players?name="+name+"&password="+password);
	        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			JSONObject result = new JSONObject(in.readLine());
			JSONArray ranking = result.getJSONArray("result");
			data = new String[ranking.length()][2];
			for(int i = 0; i < data.length; i++) {
				JSONObject rank = ranking.getJSONObject(i);
				data[i][0] = rank.getString("username");
				data[i][1] = Integer.toString(rank.getInt("score"));
			}
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return data;
	}
	
}
