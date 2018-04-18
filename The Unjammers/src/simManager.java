import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class simManager {

	//TODO parameters for vehicle randomization
	int numVehicles;
	//TODO parameters for lane locations
	//TODO parameters for sings
	//TODO parameters for circle
	String file = "config.txt";
	
	public void parseConfigFile(String file){
		
	    try {

	        Scanner sc = new Scanner(file);
	        while (sc.hasNextLine()) {
	        	//String line = sc.nextLine();
	        	
	        	switch (sc.next()) {
	        	
	        		case "vehicles":
	        			numVehicles = sc.nextInt();
	        			break;
	        		
	        		
	        			
	        	}
	        	
	        }
	        sc.close();
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	 }

	
}
