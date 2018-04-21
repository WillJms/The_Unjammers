
import java.util.Scanner;

public class simManager {
	/**
	 * Vehicle parameteres
	 */
	int minAggression;
	int maxAggression;
	int numCars;
	int numXs;
	int numSUV;
	int numTrucks;
	int numIT;
	static float timeStep;

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
	static int numLanes;
	static int l1Loc;
	static int l2Loc;
	static int l3Loc;
	static int l4Loc;
	static int l5Loc;
	static int l6Loc;
	static float l1PosX;
	static float l2PosX;
	static float l3PosX;
	static float l4PosX;
	static float l5PosX;
	static float l6PosX;
	static float l1PosY;
	static float l2PosY;
	static float l3PosY;
	static float l4PosY;
	static float l5PosY;
	static float l6PosY;
	int laneLength = 2000;
	float laneSpeedLim;

	trafficLane lane1;
	trafficLane lane2;
	trafficLane lane3;
	trafficLane lane4;
	trafficLane lane5;
	trafficLane lane6;
	trafficLane exit1;
	trafficLane exit2;
	trafficLane exit3;
	trafficLane exit4;
	trafficLane exit5;
	trafficLane exit6;
	trafficLane circle;

	//Circle Parameters
	static float cirDiameter;
	float cirSpeedLim;
	
	public static void main (String args[]) {
		float time = 0;
		
		float rad = cirDiameter /2;
		float originX = 0;
		float originY = 0;
		int originLoc = 0;
		
		l1PosX = (float) (rad * Math.cos(Math.toRadians(l1Loc)));
		l1PosY = (float) (rad * Math.sin(Math.toRadians(l1Loc)));
		l2PosX = (float) (rad * Math.cos(Math.toRadians(l2Loc)));
		l2PosY = (float) (rad * Math.sin(Math.toRadians(l2Loc)));
		l3PosX = (float) (rad * Math.cos(Math.toRadians(l3Loc)));
		l3PosY = (float) (rad * Math.sin(Math.toRadians(l3Loc)));
		l4PosX = (float) (rad * Math.cos(Math.toRadians(l4Loc)));
		l4PosY = (float) (rad * Math.sin(Math.toRadians(l4Loc)));
		
		if (numLanes == 5) {
			l5PosX = (float) (rad * Math.cos(Math.toRadians(l6Loc)));
			l5PosY = (float) (rad * Math.sin(Math.toRadians(l5Loc)));
		} else if (numLanes == 6) {
			l5PosX = (float) (rad * Math.cos(Math.toRadians(l6Loc)));
			l5PosY = (float) (rad * Math.sin(Math.toRadians(l5Loc)));
			l6PosX = (float) (rad * Math.cos(Math.toRadians(l6Loc)));
			l6PosY = (float) (rad * Math.sin(Math.toRadians(l6Loc)));
		}
		
		
		for(int i = 0; i < 20000; i++) {
			time = i * timeStep;
			
			
			
		}
		
	}


	@SuppressWarnings("null")
	public void parseConfigFile(){
		String file = "config.txt";
		try {

			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				//String line = sc.nextLine();

				switch (sc.next()) {
				
				case "timeStep":
					timeStep = sc.nextFloat();
					break;

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
					if(l1Loc == 0) {
						l1Loc = (Integer) null;
					}
					break;

				case "l2Loc":
					l2Loc = sc.nextInt();
					if(l2Loc == 0) {
						l2Loc = (Integer) null;
					}
					break;

				case "l3Loc":
					l3Loc = sc.nextInt();
					if(l3Loc == 0) {
						l3Loc = (Integer) null;
					}
					break;

				case "l4Loc":
					l4Loc = sc.nextInt();
					if(l4Loc == 0) {
						l4Loc = (Integer) null;
					}
					break;

				case "l5Loc":
					l5Loc = sc.nextInt();
					if(l5Loc == 0) {
						l5Loc = (Integer) null;
					}
					break;

				case "l6Loc":
					l6Loc = sc.nextInt();
					if(l6Loc == 0) {
						l6Loc = (Integer) null;
					}
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

	public void createTraffic(int numLanes, float minReact, float avReact,	float maxReact, int minAggression,
			int maxAggression, int numCars,	int numXs, int numSUV,	int numTrucks, int numIT, float minCarLength, float maxCarLength,
			float avCarLength, float minXLength, float avXLength, float maxXLength, float minSUVLength, float avSUVLength, float maxSUVLength,
			float minTruckLength, float avTruckLength, float maxTruckLength, float minITLength, float avITLength, float maxITLength,
			float minCarBrake, float maxCarBrake, float avCarBrake, float minXBrake, float avXBrake, float maxXBrake, float minSUVBrake, 
			float avSUVBrake, float maxSUVBrake, float minTruckBrake, float avTruckBrake, float maxTruckBrake, float minITBrake, float avITBrake, 
			float maxITBrake,float minCarAccl, float maxCarAccl, float avCarAccl, float minXAccl, float avXAccl, float maxXAccl, float minSUVAccl, 
			float avSUVAccl, float maxSUVAccl, float minTruckAccl, float avTruckAccl, float maxTruckAccl, float minITAccl, float avITAccl, 
			float maxITAccl, float laneSpeedLim){


		for(int i = 0; i < (numCars + numXs + numTrucks + numIT); i++){
			if (i <= numCars){
				vehicle car = new vehicle(numLanes,  minAggression,  maxAggression,  numCars, minCarLength, maxCarLength, avCarLength,
						minCarBrake,  maxCarBrake,  avCarBrake,  minCarAccl,  maxCarAccl,  avCarAccl,  minReact,  avReact,  maxReact);
				car.setID(i);
				setUpLane(car, laneSpeedLim);
				addToLane(car);
			}

			if(i <= numXs){
				vehicle x = new vehicle(numLanes, minAggression, maxAggression, numXs, minXLength, maxXLength, avXLength, minXBrake, 
						avXBrake, maxXBrake, minXAccl, avXAccl, maxXAccl, minReact,  avReact,  maxReact);
				x.setID((i + numCars));
				setUpLane(x, laneSpeedLim);
				addToLane(x);
			}

			if (i <= numSUV){
				vehicle SUV = new vehicle(numLanes, minAggression, maxAggression, numSUV, minSUVLength, maxSUVLength, avSUVLength,
						minSUVBrake, avSUVBrake, maxSUVBrake, minSUVAccl, avSUVAccl, maxSUVAccl, minReact,  avReact,  maxReact);
				SUV.setID(i + numCars + numXs);
				setUpLane(SUV, laneSpeedLim);
				addToLane(SUV);
			}

			if (i <= numTrucks){
				vehicle truck = new vehicle(numLanes, minAggression, maxAggression, numTrucks, minTruckLength, maxTruckLength,
						avTruckLength, minTruckBrake, avTruckBrake, maxTruckBrake, minTruckAccl, avTruckAccl, maxTruckAccl, minReact,  avReact,  maxReact);
				truck.setID(i + numCars + numXs + numSUV);
				setUpLane(truck, laneSpeedLim);
				addToLane(truck);
			}

			if (i <= numIT){
				vehicle IT = new vehicle(numLanes, minAggression, maxAggression, numIT, minITLength, maxITLength, avITLength,
						minITBrake, avITBrake, maxITBrake, minITAccl, avITAccl, maxITAccl, minReact,  avReact,  maxReact);
				IT.setID(i + numCars + numXs + numSUV + numTrucks);
				setUpLane(IT, laneSpeedLim);
				addToLane(IT);
			}
		}
	}

	public void setUpLane (vehicle car, float speedLim) {
		float lane1Length = 500;
		float lane2Length = 500;
		float lane3Length = 500;
		float lane4Length = 500;
		float lane5Length = 500;
		float lane6Length = 500;

		switch (car.getLane()) {
		case 1:
			lane1Length = (float) (lane1Length + (car.getBrake() *(car.getAggLvl() * .1)) + car.getLength());
			car.setPosition(lane1Length);
			car.setSpeed((float) (speedLim * (car.getAggLvl() * 0.05)));
			break;

		case 2:
			lane2Length = (float) (lane2Length + (car.getBrake() *(car.getAggLvl() * .1)) + car.getLength());
			car.setPosition(lane2Length);
			car.setSpeed((float) (speedLim * (car.getAggLvl() * 0.05)));
			break;

		case 3: 
			lane3Length = (float) (lane3Length + (car.getBrake() *(car.getAggLvl() * .1)) + car.getLength());
			car.setPosition(lane3Length);
			car.setSpeed((float) (speedLim * (car.getAggLvl() * 0.05)));
			break;

		case 4:
			lane4Length = (float) (lane4Length + (car.getBrake() *(car.getAggLvl() * .1)) + car.getLength());
			car.setPosition(lane4Length);
			car.setSpeed((float) (speedLim * (car.getAggLvl() * 0.05)));
			break;

		case 5:
			lane5Length = (float) (lane5Length + (car.getBrake() *(car.getAggLvl() * .1)) + car.getLength());
			car.setPosition(lane5Length);
			car.setSpeed((float) (speedLim * (car.getAggLvl() * 0.05)));
			break;

		case 6:
			lane6Length = (float) (lane6Length + (car.getBrake() *(car.getAggLvl() * .1)) + car.getLength());
			car.setPosition(lane6Length);
			car.setSpeed((float) (speedLim * (car.getAggLvl() * 0.05)));
			break;
		}


	}

	public void addToLane(vehicle car){
		switch(car.getLane()){
		case 1:
			lane1.addVehicle(car);
			break;

		case 2:
			lane2.addVehicle(car);
			break;

		case 3:
			lane3.addVehicle(car);
			break;

		case 4:
			lane4.addVehicle(car);
			break;

		case 5:
			lane5.addVehicle(car);
			break;

		case 6:
			lane6.addVehicle(car);
			break;
		}
	}

}
