
import java.util.Scanner;

public class simManager {
	/**
	 * Vehicle parameteres
	 */
	int minAggression;
	int maxAggression;
	int numCars;
	int numXs;
	int numTrucks;
	int numIT;

	//Lengths 
	float minCarLength;
	float maxCarLength;
	float avCarLength;
	float minXLength;
	float avXLength;
	float maxXLength;
	float minSUVLength;
	float avSUVLength;
	float maxSUVLength;
	float minTruckLength;
	float avTruckLength;
	float maxTruckLength;
	float minITLength;
	float avITLength;
	float maxITLength;

	//Braking Distances
	float minCarBrake;
	float maxCarBrake;
	float avCarBrake;
	float minXBrake;
	float avXBrake;
	float maxXBrake;
	float minSUVBrake;
	float avSUVBrake;
	float maxSUVBrake;
	float minTruckBrake;
	float avTruckBrake;
	float maxTruckBrake;
	float minITBrake;
	float avITBrake;
	float maxITBrake;

	//Acceleration rates
	float minCarAccl;
	float maxCarAccl;
	float avCarAccl;
	float minXAccl;
	float avXAccl;
	float maxXAccl;
	float minSUVAccl;
	float avSUVAccl;
	float maxSUVAccl;
	float minTruckAccl;
	float avTruckAccl;
	float maxTruckAccl;
	float minITAccl;
	float avITAccl;
	float maxITAccl;

	//Reaction times
	float minReact;
	float avReact;
	float maxReact;

	//Lane Parameters
	int numLanes;
	int l1Loc;
	int l2Loc;
	int l3Loc;
	int l4Loc;
	int l5Loc;
	int l6Loc;
	int laneLength = 1000;
	float laneSpeedLim;
	
	trafficLane lane1;
	trafficLane lane2;
	trafficLane lane3;
	trafficLane lane4;
	trafficLane lane5;
	trafficLane lane6;

	//Circle Parameters
	float cirDiameter;
	float cirSpeedLim;


	public void parseConfigFile(){
		String file = "config.txt";
		try {

			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				//String line = sc.nextLine();

				switch (sc.next()) {
				
				case "minAggression":
					minAggression = sc.nextInt();
					break;
				
					
				case "maxAggression":
					maxAggression = sc.nextInt();
					break;

				case "numCars":
					numCars = sc.nextInt();
					break;

				case "numXs":
					numXs = sc.nextInt();
					break;

				case "numTrucks":
					numTrucks = sc.nextInt();
					break;

				case "numIT":
					numIT = sc.nextInt();
					break;

				case "minCarLength":
					minCarLength = sc.nextFloat();
					break;

				case "maxCarLength" :
					maxCarLength= sc.nextFloat();
					break;

				case "avCarLength":
					avCarLength = sc.nextFloat();
					break;

				case "minXLength":
					minXLength = sc.nextFloat();
					break;

				case "avXLength":
					avXLength = sc.nextFloat();
					break;

				case "maxXLength": 
					maxXLength = sc.nextFloat();
					break;

				case "minSUVLength":
					minSUVLength = sc.nextFloat();
					break;

				case "avSUVLength":
					avSUVLength = sc.nextFloat();
					break;

				case "maxSUVLength":
					maxSUVLength = sc.nextFloat();
					break;

				case "minTruckLength":
					minTruckLength = sc.nextFloat();
					break;

				case "avTruckLength":
					avTruckLength = sc.nextFloat();
					break;

				case "maxTruckLength":
					maxTruckLength = sc.nextFloat();
					break;

				case "minITLength":
					minITLength = sc.nextFloat();
					break;

				case "avITLength":
					avITLength = sc.nextFloat();
					break;

				case "maxITLength": 
					maxITLength = sc.nextFloat();
					break;

				case "minCarBrake":
					minCarBrake = sc.nextFloat();
					break;

				case "maxCarBrake" :
					maxCarBrake= sc.nextFloat();
					break;

				case "avCarBrake":
					avCarBrake = sc.nextFloat();
					break;

				case "minXBrake":
					minXBrake = sc.nextFloat();
					break;

				case "avXBrake":
					avXBrake = sc.nextFloat();
					break;

				case "maxXBrake": 
					maxXBrake = sc.nextFloat();
					break;

				case "minSUVBrake":
					minSUVLength = sc.nextFloat();
					break;

				case "avSUVBrake":
					avSUVLength = sc.nextFloat();
					break;

				case "maxSUVBrake":
					maxSUVBrake = sc.nextFloat();
					break;

				case "minTruckBrake":
					minTruckBrake = sc.nextFloat();
					break;

				case "avTruckBrake":
					avTruckBrake = sc.nextFloat();
					break;

				case "maxTruckBrake":
					maxTruckBrake = sc.nextFloat();
					break;

				case "minITBrake":
					minITBrake = sc.nextFloat();
					break;

				case "avITBrake":
					avITBrake = sc.nextFloat();
					break;

				case "maxITBrake": 
					maxITBrake = sc.nextFloat();
					break;

				case "minCarAccl":
					minCarAccl = sc.nextFloat();
					break;

				case "maxCarAccl" :
					maxCarAccl= sc.nextFloat();
					break;

				case "avCarAccl":
					avCarAccl = sc.nextFloat();
					break;

				case "minXAccl":
					minXAccl = sc.nextFloat();
					break;

				case "avXAccl":
					avXAccl = sc.nextFloat();
					break;

				case "maxXAccl": 
					maxXAccl = sc.nextFloat();
					break;

				case "minSUVAccl":
					minSUVAccl = sc.nextFloat();
					break;

				case "avSUVAccl":
					avSUVAccl = sc.nextFloat();
					break;

				case "maxSUVAccl":
					maxSUVAccl = sc.nextFloat();
					break;

				case "minTruckAccl":
					minTruckAccl = sc.nextFloat();
					break;

				case "avTruckAccl":
					avTruckAccl = sc.nextFloat();
					break;

				case "maxTruckAccl":
					maxTruckAccl = sc.nextFloat();
					break;

				case "minITAccl":
					minITAccl = sc.nextFloat();
					break;

				case "avITAccl":
					avITAccl = sc.nextFloat();
					break;

				case "maxITAccl": 
					maxITAccl = sc.nextFloat();
					break;

				case "minReact":
					minReact = sc.nextFloat();

				case "numLanes":
					numLanes = sc.nextInt();
					break;

				case "l1Loc":
					l1Loc = sc.nextInt();
					break;

				case "l2Loc":
					l2Loc = sc.nextInt();
					break;

				case "l3Loc":
					l3Loc = sc.nextInt();
					break;

				case "l4Loc":
					l4Loc = sc.nextInt();
					break;

				case "l5Loc":
					l5Loc = sc.nextInt();
					break;

				case "l6Loc":
					l6Loc = sc.nextInt();
					break;

				case "laneSpeedLim":
					laneSpeedLim = sc.nextFloat();
					break;      			

				case "cirDiameter":
					cirDiameter = sc.nextFloat();
					break;

				case "cirSpeedLim":
					cirSpeedLim = sc.nextFloat();
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
