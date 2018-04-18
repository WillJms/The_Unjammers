import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class simManager {

	//TODO parameters for vehicle randomization
	int numVehicles;
	int minCarLength;
	int maxLength;
	int avLength;
	int minXLength;
	int avXLength;
	int maxXLength;
	int minSUVLength;
	int avSUVLength;
	int maxSUVLength;
	int minTruckLength;
	int avTruckLength;
	int maxTruckLength;
	int minITLength;
	int avITLength;
	int maxITLength;

	
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
	        	
	        		case "numVehicles":
	        			numVehicles = sc.nextInt();
	        			break;
	        			
	        		case "minCarLength":
	        			minCarLength = sc.nextInt();
	        			break;
	        		
	        		case "maxCarLength" :
	        			maxLength= sc.nextInt();
	        			break;
	        		
	        		case "avCarLength":
	        			avLength = sc.nextInt();
	        			break;
	        			
	        		case "minXLength":
	        			minXLength = sc.nextInt();
	        			break;
	        		
	        		case "avXLength":
	        			avXLength = sc.nextInt();
	        			break;
	        		
	        		case "maxXLength": 
	        			maxXLength = sc.nextInt();
	        			break;
	        		
	        		case "minSUVLength":
	        			minSUVLength = sc.nextInt();
	        			break;
	        			
	        		case "avSUVLength":
	        			avSUVLength = sc.nextInt();
	        			break;
	        			
	        		case "maxSUVLength":
	        			maxSUVLength = sc.nextInt();
	        			break;
	        			
	        		case "minTruckLength":
	        			minTruckLength = sc.nextInt();
	        			break;
	        			
	        		case "avTruckLength":
	        			avTruckLength = sc.nextInt();
	        			break;
	        			
	        		case "maxTruckLength":
	        			maxTruckLength = sc.nextInt();
	        			break;
	        			
	        		case "minITLength":
	        			minITLength = sc.nextInt();
	        			break;
	        			
	        		case "avITLength":
	        			avITLength = sc.nextInt();
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
