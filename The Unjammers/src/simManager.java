
import java.io.File;
import java.util.Scanner;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.*;

public class simManager extends Application {
	/**
	 * Vehicle parameteres
	 */
	static int minAggression;
	static int maxAggression;
	static int numCars;
	static int numXs;
	static int numSUV;
	static int numTrucks;
	static int numIT;
	static int numVehicles = numCars + numXs + numSUV + numTrucks + numIT;
	static int timeStep;

	//Lengths 
	static int minCarLength;
	static int maxCarLength;
	static int avCarLength;
	static int minXLength;
	static int avXLength;
	static int maxXLength;
	static int minSUVLength;
	static int avSUVLength;
	static int maxSUVLength;
	static int minTruckLength;
	static int avTruckLength;
	static int maxTruckLength;
	static int minITLength;
	static int avITLength;
	static int maxITLength;

	//Braking Distances
	static int minCarBrake;
	static int maxCarBrake;
	static int avCarBrake;
	static int minXBrake;
	static int avXBrake;
	static int maxXBrake;
	static int minSUVBrake;
	static int avSUVBrake;
	static int maxSUVBrake;
	static int minTruckBrake;
	static int avTruckBrake;
	static int maxTruckBrake;
	static int minITBrake;
	static int avITBrake;
	static int maxITBrake;

	//Acceleration rates
	static int minCarAccl;
	static int maxCarAccl;
	static int avCarAccl;
	static int minXAccl;
	static int avXAccl;
	static int maxXAccl;
	static int minSUVAccl;
	static int avSUVAccl;
	static int maxSUVAccl;
	static int minTruckAccl;
	static int avTruckAccl;
	static int maxTruckAccl;
	static int minITAccl;
	static int avITAccl;
	static int maxITAccl;

	//Reaction times
	static int minReact;
	static int avReact;
	static int maxReact;

	//Lane Parameters
	static int numLanes;
	static int l1Loc;
	static int l2Loc;
	static int l3Loc;
	static int l4Loc;
	static int l5Loc;
	static int l6Loc;
	static int l1Pos;
	static int l2Pos;
	static int l3Pos;
	static int l4Pos;
	static int l5Pos;
	static int l6Pos;

	static int laneLength = 200;
	static int laneSpeedLim;

	//Circle Parameters
	static int cirDiameter;
	static int cirSpeedLim;

	public static void main (String args[]) {
		launch(args); 
	}

	public static void moveTrafficCircle(trafficLane lane1, trafficLane lane2, trafficLane lane3, trafficLane lane4, trafficLane lane5, trafficLane lane6, trafficLane exit1, trafficLane exit2, trafficLane exit3, trafficLane exit4, trafficLane exit5,  trafficLane exit6, trafficLane circle, int timeStep, int cirSpeedLim, int rad){


		if (lane1.head.v.position == 0 && lane1.size > 0){
			processEntry(lane1, circle, cirSpeedLim, timeStep);
		}
		if (lane2.head.v.position == 0 && lane2.size > 0){
			processEntry(lane2, circle, cirSpeedLim, timeStep);
		}
		if (lane3.head.v.position == 0 && lane3.size > 0){
			processEntry(lane3, circle, cirSpeedLim, timeStep);
		}
		if (lane4.head.v.position == 0 && lane4.size > 0){
			processEntry(lane4, circle, cirSpeedLim, timeStep);
		}
		if(lane5.size >0){
			if(lane5.head.v.position == 0){
				processEntry(lane5, circle, cirSpeedLim, timeStep);
			}
		}
		if(lane6.size > 0){
			if(lane6.head.v.position == 0){
				processEntry(lane6, circle, cirSpeedLim, timeStep);
			}
		}

  		processExit(exit1, exit2, exit3, exit4, exit5, exit6, circle, laneSpeedLim, timeStep);
		moveTrafficLane(circle, timeStep);
	}

	public static void processEntry(trafficLane lane, trafficLane circle, int cirSpeedLim, int timeStep){
		vehicleNode merger = lane.head;
		vehicleNode check = circle.head;
		boolean clear = true;
		int mergePos = 0;
		switch(merger.v.lane){
		case 1:
			mergePos = l1Pos;
			break;
		case 2: 
			mergePos = l2Pos;
			break;
		case 3:
			mergePos = l3Pos;
			break;
		case 4: 
			mergePos = l4Pos;
			break;
		case 5:
			mergePos = l5Pos;
			break;
		case 6:
			mergePos = l6Pos;
			break;
		}

		if(circle.size > 0){
			for(int i = 0; i < circle.size; i++){
				if(check == null ||check.v.position >= (mergePos - (  (Math.pow((cirSpeedLim/(merger.v.aggLvl/2)),2)) / (2 * merger.v.accel)  ) ) ){
					clear = false;
					break;
				}
				check = check.next;
			}
		}

		if(clear == true){

			if(circle.size > 0){
				check = circle.head;
				for(int j = 0; j < circle.size; j++){
					if((check.next != null)&&(check.v.position < mergePos && mergePos < check.next.v.position)){
						merger.v.lane = 0;
						merger.prev = check;
						merger.next = check.next;
						check.next.prev = merger;
						check.next = merger;
						merger.v.position = cirSpeedLim * timeStep;
						merger.v.speed = cirSpeedLim;
						circle.size++;
					}else if(check.next == null){
						merger.v.lane = 0;
						circle.addVehicle(merger.v);
					}
				}

			}else{
				merger.v.lane = 0;
				circle.addVehicle(merger.v);
			}
			lane.head.next.prev = null;
			lane.head = lane.head.next;
			lane.size--;
		}

	}

	public static void processExit(trafficLane exit1, trafficLane exit2, trafficLane exit3, trafficLane exit4, trafficLane exit5,  trafficLane exit6, trafficLane circle, int laneSpeedLim, int timeStep){
		vehicleNode exiter = circle.head;
		vehicleNode check = circle.head;
		trafficLane exit = null;
		int exitPos = 0;
		if(circle.size > 0){
			switch(exiter.v.exitNum){
			case 1:
				exitPos = l1Pos;
				exit = exit1;
				break;
			case 2: 
				exitPos = l2Pos;
				exit = exit2;
				break;
			case 3:
				exitPos = l3Pos;
				exit = exit3;
				break;
			case 4: 
				exitPos = l4Pos;
				exit = exit4;
				break;
			case 5:
				exitPos = l5Pos;
				exit = exit5;
				break;
			case 6:
				exitPos = l6Pos;
				exit = exit6;
				break;
			}

			for(int i = 0; i < circle.size; i++){
				if((check.v.position + (check.v.speed *timeStep) > exitPos)){
					exiter = check;
					check.next.prev = check.prev;
					check.prev.next = check.next;
					exiter.prev = exit.tail;
					exiter.next = null;
					exit.tail.next = exiter;
					exiter.v.position = (exiter.v.position + ((exiter.v.speed * timeStep) + (0.5 * exiter.v.accel * Math.pow(timeStep, 2)))) - exitPos;
					exiter.v.speed = exiter.v.speed + (exiter.v.accel * timeStep);
					exiter.v.lane = exiter.v.exitNum;
					circle.size--;
					exit.size++;
				}
			}
		}
	}

	public static void moveTrafficExit(trafficLane exit, int timeStep, int laneSpeedLim, int laneLength){
		if(exit.size > 0){
			vehicleNode cur = exit.head;
			for(int i = 0; i < exit.size; i++){

				if(cur == exit.head){
					if(cur.v.speed == (laneSpeedLim * (cur.v.aggLvl * 0.05))){
						cur.v.position = cur.v.position + (cur.v.speed * timeStep);
					}else{
						cur.v.position = cur.v.speed * timeStep + 0.5 * cur.v.accel * (timeStep*timeStep);
						cur.v.speed = cur.v.speed + cur.v.accel * timeStep;
					}

				}else if(cur.v.position < laneLength ){

					if(cur.v.speed == (laneSpeedLim * (cur.v.aggLvl * 0.05))){
						cur.v.position = cur.v.position + (cur.v.speed * timeStep);
						if((cur.prev.v.position - cur.prev.v.length) < (cur.v.position + (cur.v.brakeDist * (cur.v.aggLvl * 0.1)))){
							cur.v.position = (cur.prev.v.position - cur.prev.v.length - (cur.v.brakeDist * (cur.v.aggLvl * 0.1)));
							cur.v.speed = cur.prev.v.speed;
						}
					}else{
						cur.v.position = cur.v.speed * timeStep + 0.5 * cur.v.accel * (timeStep*timeStep);
						cur.v.speed = cur.v.speed + cur.v.accel * timeStep;
						if((cur.prev.v.position - cur.prev.v.length) < (cur.v.position + (cur.v.brakeDist * (cur.v.aggLvl * 0.1)))){
							cur.v.position = (cur.prev.v.position - cur.prev.v.length - (cur.v.brakeDist * (cur.v.aggLvl * 0.1)));
							cur.v.speed = cur.prev.v.speed;
						}
					}
				}
			}
		}
	}

	public static void moveTrafficLane(trafficLane lane, int timeStep){
		if (lane.size > 0){
			vehicleNode cur = lane.head;
			for(int i = 0; i < lane.size; i++){

				if((cur.v.position > cur.v.brakeDist  && cur.v.vehicleID == lane.head.v.vehicleID) || (cur.v.lane == 0 &&  cur.v.vehicleID == lane.head.v.vehicleID)){
					cur.v.position = cur.v.position - (cur.v.speed * timeStep);

				}else if(cur.v.position > cur.v.brakeDist && cur.prev.v.speed > 0){
					cur.v.position = cur.v.position - (cur.v.speed * timeStep);
					if((cur.v.position - (cur.prev.v.position + cur.prev.v.length)) < (cur.v.brakeDist *(cur.v.aggLvl * 0.1))){
						cur.v.position = cur.prev.v.position + cur.prev.v.length + (cur.v.brakeDist *(cur.v.aggLvl * 0.1));
						cur.v.speed = cur.prev.v.speed;
					}

				}else if(cur.v.position < cur.v.brakeDist && cur.v.lane != 0){

					if(cur.v.vehicleID == lane.head.v.vehicleID){
						cur.v.position = 0;
						cur.v.speed = 0;
					}else{
						cur.v.position = cur.prev.v.position + cur.prev.v.length + (cur.v.length * (int)(cur.v.aggLvl*0.1));
						cur.v.speed = cur.prev.v.speed;
					}
				}
			}
		}
	}

	public static void parseConfigFile(){
		File file =new File( "config.txt");
		try {

			Scanner sc = new Scanner(file);
			while (sc.hasNext()) {
				//String line = sc.nextLine();

				switch (sc.next()) {

				case "timeStep":
					timeStep = (int)sc.nextFloat();
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

				case "numSUV":
					numSUV = sc.nextInt();
					break;

				case "numTrucks":
					numTrucks = sc.nextInt();
					break;

				case "numIT":
					numIT = sc.nextInt();
					break;

				case "minCarLength":
					minCarLength = (int)sc.nextFloat();
					break;

				case "maxCarLength" :
					maxCarLength= (int)sc.nextFloat();
					break;

				case "avCarLength":
					avCarLength =(int) sc.nextFloat();
					break;

				case "minXLength":
					minXLength = (int)sc.nextFloat();
					break;

				case "avXLength":
					avXLength = (int)sc.nextFloat();
					break;

				case "maxXLength": 
					maxXLength = (int)sc.nextFloat();
					break;

				case "minSUVLength":
					minSUVLength =(int) sc.nextFloat();
					break;

				case "avSUVLength":
					avSUVLength = (int)sc.nextFloat();
					break;

				case "maxSUVLength":
					maxSUVLength =(int) sc.nextFloat();
					break;

				case "minTruckLength":
					minTruckLength =(int) sc.nextFloat();
					break;

				case "avTruckLength":
					avTruckLength = (int)sc.nextFloat();
					break;

				case "maxTruckLength":
					maxTruckLength =(int) sc.nextFloat();
					break;

				case "minITLength":
					minITLength =(int) sc.nextFloat();
					break;

				case "avITLength":
					avITLength =(int) sc.nextFloat();
					break;

				case "maxITLength": 
					maxITLength =(int) sc.nextFloat();
					break;

				case "minCarBrake":
					minCarBrake = (int)sc.nextFloat();
					break;

				case "maxCarBrake" :
					maxCarBrake=(int) sc.nextFloat();
					break;

				case "avCarBrake":
					avCarBrake =(int) sc.nextFloat();
					break;

				case "minXBrake":
					minXBrake =(int) sc.nextFloat();
					break;

				case "avXBrake":
					avXBrake =(int) sc.nextFloat();
					break;

				case "maxXBrake": 
					maxXBrake =(int) sc.nextFloat();
					break;

				case "minSUVBrake":
					minSUVBrake =(int) sc.nextFloat();
					break;

				case "avSUVBrake":
					avSUVBrake =(int) sc.nextFloat();
					break;

				case "maxSUVBrake":
					maxSUVBrake =(int) sc.nextFloat();
					break;

				case "minTruckBrake":
					minTruckBrake =(int) sc.nextFloat();
					break;

				case "avTruckBrake":
					avTruckBrake =(int) sc.nextFloat();
					break;

				case "maxTruckBrake":
					maxTruckBrake = (int)sc.nextFloat();
					break;

				case "minITBrake":
					minITBrake = (int)sc.nextFloat();
					break;

				case "avITBrake":
					avITBrake = (int)sc.nextFloat();
					break;

				case "maxITBrake": 
					maxITBrake =(int) sc.nextFloat();
					break;

				case "minCarAccl":
					minCarAccl =(int) sc.nextFloat();
					break;

				case "maxCarAccl" :
					maxCarAccl=(int) sc.nextFloat();
					break;

				case "avCarAccl":
					avCarAccl = (int)sc.nextFloat();
					break;

				case "minXAccl":
					minXAccl = (int)sc.nextFloat();
					break;

				case "avXAccl":
					avXAccl =(int) sc.nextFloat();
					break;

				case "maxXAccl": 
					maxXAccl =(int) sc.nextFloat();
					break;

				case "minSUVAccl":
					minSUVAccl = (int)sc.nextFloat();
					break;

				case "avSUVAccl":
					avSUVAccl = (int)sc.nextFloat();
					break;

				case "maxSUVAccl":
					maxSUVAccl =(int) sc.nextFloat();
					break;

				case "minTruckAccl":
					minTruckAccl =(int) sc.nextFloat();
					break;

				case "avTruckAccl":
					avTruckAccl = (int)sc.nextFloat();
					break;

				case "maxTruckAccl":
					maxTruckAccl =(int) sc.nextFloat();
					break;

				case "minITAccl":
					minITAccl =(int) sc.nextFloat();
					break;

				case "avITAccl":
					avITAccl =(int) sc.nextFloat();
					break;

				case "maxITAccl": 
					maxITAccl =(int) sc.nextFloat();
					break;

				case "minReact":
					minReact =(int) sc.nextFloat();
					break;

				case "avReact":
					avReact =(int) sc.nextFloat();
					break;

				case "maxReact":
					maxReact = (int)sc.nextFloat();
					break;

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
					laneSpeedLim =(int) sc.nextFloat();
					break;      			

				case "cirDiameter":
					cirDiameter = (int)sc.nextFloat();
					break;

				case "cirSpeedLim":
					cirSpeedLim =(int) sc.nextFloat();
					break;

				}

			}
			sc.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createTraffic( trafficLane lane1, trafficLane lane2,  trafficLane lane3, trafficLane lane4, trafficLane lane5, trafficLane lane6, trafficLane exit1, trafficLane exit2, trafficLane exit3, trafficLane exit4, trafficLane exit5, trafficLane exit6, trafficLane circle, int numLanes, int minReact, int avReact,	int maxReact, int minAggression,
			int maxAggression, int numCars,	int numXs, int numSUV,	int numTrucks, int numIT, int minCarLength, int maxCarLength,
			int avCarLength, int minXLength, int avXLength, int maxXLength, int minSUVLength, int avSUVLength, int maxSUVLength,
			int minTruckLength, int avTruckLength, int maxTruckLength, int minITLength, int avITLength, int maxITLength,
			int minCarBrake, int maxCarBrake, int avCarBrake, int minXBrake, int avXBrake, int maxXBrake, int minSUVBrake, 
			int avSUVBrake, int maxSUVBrake, int minTruckBrake, int avTruckBrake, int maxTruckBrake, int minITBrake, int avITBrake, 
			int maxITBrake,int minCarAccl, int maxCarAccl, int avCarAccl, int minXAccl, int avXAccl, int maxXAccl, int minSUVAccl, 
			int avSUVAccl, int maxSUVAccl, int minTruckAccl, int avTruckAccl, int maxTruckAccl, int minITAccl, int avITAccl, 
			int maxITAccl, int laneSpeedLim){


		for(int i = 0; i < (numCars + numXs + numTrucks + numIT); i++){
			if (i <= numCars){
				vehicle car = new vehicle(numLanes,  minAggression,  maxAggression,  numCars, minCarLength, maxCarLength, avCarLength,
						minCarBrake,  maxCarBrake,  avCarBrake,  minCarAccl,  maxCarAccl,  avCarAccl,  minReact,  avReact,  maxReact);
				car.setID(i);
				setUpLane( car, laneSpeedLim);
				addToLane( lane1,  lane2,   lane3,  lane4,  lane5,  lane6,  exit1,  exit2,  exit3,  exit4,  exit5,  exit6,  circle, car);
			}

			if(i <= numXs){
				vehicle x = new vehicle(numLanes, minAggression, maxAggression, numXs, minXLength, maxXLength, avXLength, minXBrake, 
						avXBrake, maxXBrake, minXAccl, avXAccl, maxXAccl, minReact,  avReact,  maxReact);
				x.setID((i + numCars));
				setUpLane( x, laneSpeedLim);
				addToLane(lane1,  lane2,   lane3,  lane4,  lane5,  lane6,  exit1,  exit2,  exit3,  exit4,  exit5,  exit6,  circle,x);
			}

			if (i <= numSUV){
				vehicle SUV = new vehicle(numLanes, minAggression, maxAggression, numSUV, minSUVLength, maxSUVLength, avSUVLength,
						minSUVBrake, avSUVBrake, maxSUVBrake, minSUVAccl, avSUVAccl, maxSUVAccl, minReact,  avReact,  maxReact);
				SUV.setID(i + numCars + numXs);
				setUpLane(SUV, laneSpeedLim);
				addToLane(lane1,  lane2,   lane3,  lane4,  lane5,  lane6,  exit1,  exit2,  exit3,  exit4,  exit5,  exit6,  circle,SUV);
			}

			if (i <= numTrucks){
				vehicle truck = new vehicle(numLanes, minAggression, maxAggression, numTrucks, minTruckLength, maxTruckLength,
						avTruckLength, minTruckBrake, avTruckBrake, maxTruckBrake, minTruckAccl, avTruckAccl, maxTruckAccl, minReact,  avReact,  maxReact);
				truck.setID(i + numCars + numXs + numSUV);
				setUpLane(truck, laneSpeedLim);
				addToLane(lane1,  lane2,   lane3,  lane4,  lane5,  lane6,  exit1,  exit2,  exit3,  exit4,  exit5,  exit6,  circle,truck);
			}

			if (i <= numIT){
				vehicle IT = new vehicle(numLanes, minAggression, maxAggression, numIT, minITLength, maxITLength, avITLength,
						minITBrake, avITBrake, maxITBrake, minITAccl, avITAccl, maxITAccl, minReact,  avReact,  maxReact);
				IT.setID(i + numCars + numXs + numSUV + numTrucks);
				setUpLane(IT, laneSpeedLim);
				addToLane(lane1,  lane2,   lane3,  lane4,  lane5,  lane6,  exit1,  exit2,  exit3,  exit4,  exit5,  exit6,  circle,IT);
			}
		}
	}

	public static void setUpLane (vehicle car, int speedLim) {
		int lane1Length = 50;
		int lane2Length = 50;
		int lane3Length = 50;
		int lane4Length = 50;
		int lane5Length = 50;
		int lane6Length = 50;

		switch (car.getLane()) {
		case 1:
			lane1Length = (int) (lane1Length + (car.getBrake() *(car.getAggLvl() * .1)) + car.getLength());
			car.setPosition(lane1Length);
			car.setSpeed((int) (speedLim * (car.getAggLvl() * 0.5)));
			break;

		case 2:
			lane2Length = (int) (lane2Length + (car.getBrake() *(car.getAggLvl() * .1)) + car.getLength());
			car.setPosition(lane2Length);
			car.setSpeed((int) (speedLim * (car.getAggLvl() * 0.5)));
			break;

		case 3: 
			lane3Length = (int) (lane3Length + (car.getBrake() *(car.getAggLvl() * .1)) + car.getLength());
			car.setPosition(lane3Length);
			car.setSpeed((int) (speedLim * (car.getAggLvl() * 0.5)));
			break;

		case 4:
			lane4Length = (int) (lane4Length + (car.getBrake() *(car.getAggLvl() * .1)) + car.getLength());
			car.setPosition(lane4Length);
			car.setSpeed((int) (speedLim * (car.getAggLvl() * 0.5)));
			break;

		case 5:
			lane5Length = (int) (lane5Length + (car.getBrake() *(car.getAggLvl() * .1)) + car.getLength());
			car.setPosition(lane5Length);
			car.setSpeed((int) (speedLim * (car.getAggLvl() * 0.5)));
			break;

		case 6:
			lane6Length = (int) (lane6Length + (car.getBrake() *(car.getAggLvl() * .1)) + car.getLength());
			car.setPosition(lane6Length);
			car.setSpeed((int) (speedLim * (car.getAggLvl() * 0.5)));
			break;
		}


	}

	public static void addToLane( trafficLane lane1, trafficLane lane2,  trafficLane lane3, trafficLane lane4, trafficLane lane5, trafficLane lane6, trafficLane exit1, trafficLane exit2, trafficLane exit3, trafficLane exit4, trafficLane exit5, trafficLane exit6, trafficLane circle, vehicle car){

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


	@Override
	public void start(Stage primaryStage) throws Exception {

		Timeline TL = new Timeline();


		parseConfigFile();
		int numVehicles = numCars + numXs + numSUV + numTrucks + numIT;
		cirDiameter = cirDiameter *4;
		Circle trafficCircle = new Circle();
		int circleCenter = 500;
		trafficCircle.setCenterX(circleCenter);
		trafficCircle.setCenterY(circleCenter);
		int rad = cirDiameter /2;
		trafficCircle.setRadius(rad/2);


		int time = 0;
		l1Pos = (int) (rad * Math.toRadians(l1Loc));
		l2Pos = (int) (rad * Math.toRadians(l2Loc));
		l3Pos = (int) (rad * Math.toRadians(l3Loc));
		l4Pos = (int) (rad * Math.toRadians(l4Loc));
		if (numLanes == 5) {
			l5Pos = (int) (rad * Math.toRadians(l6Loc));
		} else if (numLanes == 6) {
			l5Pos = (int) (rad * Math.toRadians(l6Loc));
			l6Pos = (int) (rad * Math.toRadians(l6Loc));
		}

		Line line1 = new Line();
		line1.setStartX((rad * Math.sin(Math.toRadians(l1Loc))) + circleCenter);
		line1.setStartY((rad * Math.cos(Math.toRadians(l1Loc))) + circleCenter);
		line1.setEndX((laneLength * Math.sin(Math.toRadians(l1Loc))) + circleCenter);
		line1.setEndY((laneLength * Math.cos(Math.toRadians(l1Loc))) + circleCenter);

		Line line2 = new Line();
		line2.setStartX((rad * Math.sin(Math.toRadians(l2Loc))) + circleCenter);
		line2.setStartY((rad * Math.cos(Math.toRadians(l2Loc))) + circleCenter);
		line2.setEndX((laneLength * Math.sin(Math.toRadians(l2Loc))) + circleCenter);
		line2.setEndY((laneLength * Math.cos(Math.toRadians(l2Loc))) + circleCenter);

		Line line3 = new Line();
		line3.setStartX((rad * Math.sin(Math.toRadians(l3Loc))) + circleCenter);
		line3.setStartY((rad * Math.cos(Math.toRadians(l3Loc))) + circleCenter);
		line3.setEndX((laneLength * Math.sin(Math.toRadians(l3Loc))) + circleCenter);
		line3.setEndY((laneLength * Math.cos(Math.toRadians(l3Loc))) + circleCenter);

		Line line4 = new Line();
		line4.setStartX((rad * Math.sin(Math.toRadians(l4Loc))) + circleCenter);
		line4.setStartY((rad * Math.cos(Math.toRadians(l4Loc))) + circleCenter);
		line4.setEndX((laneLength * Math.sin(Math.toRadians(l4Loc))) + circleCenter);
		line4.setEndY((laneLength * Math.cos(Math.toRadians(l4Loc))) + circleCenter);
		Group root = new Group(trafficCircle, line1, line2, line3, line4);

		if(numLanes == 5){
			Line line5 = new Line();
			line5.setStartX((rad * Math.sin(Math.toRadians(l5Loc))) + circleCenter);
			line5.setStartY((rad * Math.cos(Math.toRadians(l5Loc))) + circleCenter);
			line5.setEndX((laneLength * Math.sin(Math.toRadians(l5Loc))) + circleCenter);
			line5.setEndY((laneLength * Math.cos(Math.toRadians(l5Loc))) + circleCenter);
			root = new Group(trafficCircle, line1, line2, line3, line4, line5);
		}else if(numLanes == 6){
			Line line5 = new Line();
			line5.setStartX((rad * Math.sin(Math.toRadians(l5Loc))) + circleCenter);
			line5.setStartY((rad * Math.cos(Math.toRadians(l5Loc))) + circleCenter);
			line5.setEndX((laneLength * Math.sin(Math.toRadians(l5Loc))) + circleCenter);
			line5.setEndY((laneLength * Math.cos(Math.toRadians(l5Loc))) + circleCenter);

			Line line6 = new Line();
			line6.setStartX((rad * Math.sin(Math.toRadians(l6Loc))) + circleCenter);
			line6.setStartY((rad * Math.cos(Math.toRadians(l6Loc))) + circleCenter);
			line6.setEndX((laneLength * Math.sin(Math.toRadians(l6Loc))) + circleCenter);
			line6.setEndY((laneLength * Math.cos(Math.toRadians(l6Loc))) + circleCenter);
			root = new Group(trafficCircle, line1, line2, line3, line4, line5, line6);
		}

		trafficLane lane1 = new trafficLane();
		trafficLane lane2 = new trafficLane();
		trafficLane lane3 = new trafficLane();
		trafficLane lane4 = new trafficLane();
		trafficLane lane5 = new trafficLane();
		trafficLane lane6 = new trafficLane();
		trafficLane exit1 = new trafficLane();
		trafficLane exit2 = new trafficLane();
		trafficLane exit3 = new trafficLane();
		trafficLane exit4 = new trafficLane();
		trafficLane exit5 = new trafficLane();
		trafficLane exit6 = new trafficLane();
		trafficLane circle = new trafficLane();

		createTraffic(lane1,  lane2,   lane3,  lane4,  lane5,  lane6,  exit1,  exit2,  exit3,  exit4,  exit5,  exit6,  circle, numLanes, minReact, avReact,	 maxReact, minAggression,
				maxAggression,  numCars,	 numXs,  numSUV, numTrucks,  numIT,  minCarLength,  maxCarLength,
				avCarLength,  minXLength,  avXLength,  maxXLength,  minSUVLength,  avSUVLength,  maxSUVLength,
				minTruckLength,  avTruckLength,  maxTruckLength,  minITLength,  avITLength,  maxITLength,
				minCarBrake,  maxCarBrake,  avCarBrake,  minXBrake,  avXBrake,  maxXBrake,  minSUVBrake, 
				avSUVBrake,  maxSUVBrake,  minTruckBrake,  avTruckBrake,  maxTruckBrake,  minITBrake,  avITBrake, 
				maxITBrake, minCarAccl,  maxCarAccl,  avCarAccl,  minXAccl,  avXAccl,  maxXAccl,  minSUVAccl, 
				avSUVAccl,  maxSUVAccl,  minTruckAccl,  avTruckAccl,  maxTruckAccl,  minITAccl,  avITAccl, 
				maxITAccl,  laneSpeedLim);



		Scene map = new Scene(root, 1000, 1000);
		primaryStage.setTitle("Traffic Circle SImulation");
		primaryStage.setScene(map);		

		Group vehicles = new Group();


		for(int i = 0; i < 200; i++) {


			time = i * timeStep;
			moveTrafficLane(lane1, timeStep);
			moveTrafficExit(exit1, timeStep, laneSpeedLim, laneLength);
			moveTrafficLane(lane2, timeStep);
			moveTrafficExit(exit2, timeStep, laneSpeedLim, laneLength);
			moveTrafficLane(lane3, timeStep);
			moveTrafficExit(exit3, timeStep, laneSpeedLim, laneLength);
			moveTrafficLane(lane4, timeStep);
			moveTrafficExit(exit4, timeStep, laneSpeedLim, laneLength);
			if(numLanes ==5){
				moveTrafficLane(lane5, timeStep);
				moveTrafficExit(exit5, timeStep, laneSpeedLim, laneLength);
			}else if(numLanes ==6){
				moveTrafficLane(lane5, timeStep);
				moveTrafficExit(exit5, timeStep, laneSpeedLim, laneLength);
				moveTrafficLane(lane6, timeStep);
				moveTrafficExit(exit6, timeStep, laneSpeedLim, laneLength);
			}
			moveTrafficCircle( lane1,  lane2,  lane3,  lane4,  lane5,  lane6,  exit1,  exit2,  exit3,  exit4,  exit5,   exit6,  circle,  timeStep,  cirSpeedLim,  rad);



			vehicleNode cur1 = lane1.head;
			vehicleNode cur2 = lane2.head;
			vehicleNode cur3 = lane3.head;
			vehicleNode cur4 = lane4.head;
			vehicleNode cur5 = lane5.head;
			vehicleNode cur6 = lane6.head;



			for(int j = 0; j < lane1.size; j++){
				if( lane1.size != 0 && cur1 != null){
					if (i == 0){
						cur1.v.dot.setFill(Color.RED);	
						cur1.v.dot.setCenterX((cur1.v.position * Math.cos(Math.toRadians(l1Loc))) + circleCenter);
						cur1.v.dot.setCenterY((cur1.v.position * Math.sin(Math.toRadians(l1Loc))) + circleCenter);	
						vehicles.getChildren().add(cur1.v.dot);
					}
					TL.getKeyFrames().addAll(new KeyFrame(Duration.millis(1000), 
							new KeyValue(cur1.v.dot.centerXProperty(),(cur1.v.position * Math.cos(Math.toRadians(l1Loc))) + circleCenter),	
							new KeyValue(cur1.v.dot.centerYProperty(), ((cur1.v.position * Math.sin(Math.toRadians(l1Loc)))) + circleCenter)));
					cur1 = cur1.next;
				}
				if( 0 != lane2.size && cur2 != null){
					if (i == 0){
						cur2.v.dot.setCenterX((cur2.v.position * Math.cos(Math.toRadians(l2Loc))) + circleCenter);
						cur2.v.dot.setCenterY((cur2.v.position * Math.sin(Math.toRadians(l2Loc))) + circleCenter);
						cur2.v.dot.setFill(Color.BLUE);
						vehicles.getChildren().add(cur2.v.dot);
					}
					TL.getKeyFrames().addAll(new KeyFrame(Duration.millis(1000), 
							new KeyValue(cur2.v.dot.centerXProperty(),(cur2.v.position * Math.cos(Math.toRadians(l2Loc))) + circleCenter),	
							new KeyValue(cur2.v.dot.centerYProperty(), ((cur2.v.position * Math.sin(Math.toRadians(l2Loc)))) + circleCenter)));
					cur2 = cur2.next;
				}

				if( 0!= lane3.size && cur3 != null){
				
					if (i == 0){
						cur3.v.dot.setCenterX((cur3.v.position * Math.cos(Math.toRadians(l3Loc))) + circleCenter);
						cur3.v.dot.setCenterY((cur3.v.position * Math.sin(Math.toRadians(l3Loc))) + circleCenter);
						cur3.v.dot.setFill(Color.GREEN);
						vehicles.getChildren().add(cur3.v.dot);
					}
					TL.getKeyFrames().addAll(new KeyFrame(Duration.millis(1000), 
							new KeyValue(cur3.v.dot.centerXProperty(),(cur3.v.position * Math.cos(Math.toRadians(l3Loc))) + circleCenter),	
							new KeyValue(cur3.v.dot.centerYProperty(), ((cur3.v.position * Math.sin(Math.toRadians(l3Loc)))) + circleCenter)));
					cur3 = cur3.next;

				}

				if(0 !=lane4.size && cur4 != null){
					if (i == 0){
						cur4.v.dot.setCenterX((cur4.v.position * Math.cos(Math.toRadians(l4Loc))) + circleCenter);
						cur4.v.dot.setCenterY((cur4.v.position * Math.sin(Math.toRadians(l4Loc))) + circleCenter);
						cur4.v.dot.setFill(Color.BLACK);
						vehicles.getChildren().add(cur4.v.dot);
					}
					TL.getKeyFrames().addAll(new KeyFrame(Duration.millis(1000), 
							new KeyValue(cur4.v.dot.centerXProperty(),(cur4.v.position * Math.cos(Math.toRadians(l4Loc))) + circleCenter),	
							new KeyValue(cur4.v.dot.centerYProperty(), ((cur4.v.position * Math.sin(Math.toRadians(l4Loc)))) + circleCenter)));
					cur4 = cur4.next;
				}

				if(0 != lane5.size && cur5 != null){
					if (i == 0){
						cur5.v.dot.setCenterX((cur5.v.position * Math.cos(Math.toRadians(l5Loc))) + circleCenter);
						cur5.v.dot.setCenterY((cur5.v.position * Math.sin(Math.toRadians(l5Loc))) + circleCenter);
						cur5.v.dot.setFill(Color.ORANGE);
						vehicles.getChildren().add(cur5.v.dot);
					}
					TL.getKeyFrames().addAll(new KeyFrame(Duration.millis(1000), 
							new KeyValue(cur5.v.dot.centerXProperty(),(cur5.v.position * Math.cos(Math.toRadians(l5Loc))) + circleCenter),	
							new KeyValue(cur5.v.dot.centerYProperty(), ((cur5.v.position * Math.sin(Math.toRadians(l5Loc)))) + circleCenter)));
					cur5 = cur5.next;
				}

				if(0 !=lane6.size && cur6 != null){
					
					if (i == 0){
						cur6.v.dot.setCenterX((cur6.v.position * Math.cos(Math.toRadians(l6Loc))) + circleCenter);
						cur6.v.dot.setCenterY((cur6.v.position * Math.sin(Math.toRadians(l6Loc))) + circleCenter);
						cur6.v.dot.setFill(Color.YELLOW);
						vehicles.getChildren().add(cur6.v.dot);
					}
					TL.getKeyFrames().addAll(new KeyFrame(Duration.millis(1000), 
							new KeyValue(cur6.v.dot.centerXProperty(),(cur6.v.position * Math.cos(Math.toRadians(l6Loc))) + circleCenter),	
							new KeyValue(cur6.v.dot.centerYProperty(), ((cur6.v.position * Math.sin(Math.toRadians(l6Loc)))) + circleCenter)));
					cur6 = cur6.next;
				}


			}
			//map.setRoot(vehicles);
			//primaryStage.setScene(map);
			//primaryStage.sizeToScene(); 

			System.out.print(time); 
		}
		//TL goes here
		root.getChildren().add(vehicles);
		TL.play();
		primaryStage.show();
	}

}

