
import java.io.File;
import java.time.Duration;
import java.util.Scanner;

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
	static float timeStep;

	//Lengths 
	static float minCarLength;
	static float maxCarLength;
	static float avCarLength;
	static float minXLength;
	static float avXLength;
	static float maxXLength;
	static float minSUVLength;
	static float avSUVLength;
	static float maxSUVLength;
	static float minTruckLength;
	static float avTruckLength;
	static float maxTruckLength;
	static float minITLength;
	static float avITLength;
	static float maxITLength;

	//Braking Distances
	static float minCarBrake;
	static float maxCarBrake;
	static float avCarBrake;
	static float minXBrake;
	static float avXBrake;
	static float maxXBrake;
	static float minSUVBrake;
	static float avSUVBrake;
	static float maxSUVBrake;
	static float minTruckBrake;
	static float avTruckBrake;
	static float maxTruckBrake;
	static float minITBrake;
	static float avITBrake;
	static float maxITBrake;

	//Acceleration rates
	static float minCarAccl;
	static float maxCarAccl;
	static float avCarAccl;
	static float minXAccl;
	static float avXAccl;
	static float maxXAccl;
	static float minSUVAccl;
	static float avSUVAccl;
	static float maxSUVAccl;
	static float minTruckAccl;
	static float avTruckAccl;
	static float maxTruckAccl;
	static float minITAccl;
	static float avITAccl;
	static float maxITAccl;

	//Reaction times
	static float minReact;
	static float avReact;
	static float maxReact;

	//Lane Parameters
	static int numLanes;
	static int l1Loc;
	static int l2Loc;
	static int l3Loc;
	static int l4Loc;
	static int l5Loc;
	static int l6Loc;
	static float l1Pos;
	static float l2Pos;
	static float l3Pos;
	static float l4Pos;
	static float l5Pos;
	static float l6Pos;

	static int laneLength = 2000;
	static float laneSpeedLim;

	//Circle Parameters
	static float cirDiameter;
	static float cirSpeedLim;

	public static void main (String args[]) {
		launch(args); 
	}

	public static void moveTrafficCircle(trafficLane lane1, trafficLane lane2, trafficLane lane3, trafficLane lane4, trafficLane lane5, trafficLane lane6, trafficLane exit1, trafficLane exit2, trafficLane exit3, trafficLane exit4, trafficLane exit5,  trafficLane exit6, trafficLane circle, float timeStep, float cirSpeedLim, float rad){


		if (lane1.head.v.position == 0 && lane1.size > 0){
			processEntry(lane1, circle, cirSpeedLim, timeStep);
		}else if (lane2.head.v.position == 0 && lane2.size > 0){
			processEntry(lane2, circle, cirSpeedLim, timeStep);
		}else if (lane3.head.v.position == 0 && lane3.size > 0){
			processEntry(lane3, circle, cirSpeedLim, timeStep);
		}else if (lane4.head.v.position == 0 && lane4.size > 0){
			processEntry(lane4, circle, cirSpeedLim, timeStep);
		}else if(lane5.size >0){
			if(lane5.head.v.position == 0){
				processEntry(lane5, circle, cirSpeedLim, timeStep);
			}
		}else if(lane6.size > 0){
			if(lane6.head.v.position == 0){
				processEntry(lane6, circle, cirSpeedLim, timeStep);
			}
		}

		processExit(exit1, exit2, exit3, exit4, exit5, exit6, circle, laneSpeedLim, timeStep);
		moveTrafficLane(circle, timeStep);
	}

	public static void processEntry(trafficLane lane, trafficLane circle, float cirSpeedLim, float timeStep){
		vehicleNode merger = lane.head;
		vehicleNode check = circle.head;
		boolean clear = true;
		float mergePos = 0;
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
				if(check.v.position >= (mergePos - (  (Math.pow((cirSpeedLim/(merger.v.aggLvl/2)),2)) / (2 * merger.v.accel)  ) ) ){
					clear = false;
				}
				check = check.next;
			}
		}

		if(clear == true){

			if(circle.size > 0){
				check = circle.head;
				for(int j = 0; j < circle.size; j++){
					if(check.v.position < mergePos && mergePos < check.next.v.position){
						merger.prev = check;
						merger.next = check.next;
						check.next.prev = merger;
						check.next = merger;
						merger.v.position = 0.5 * merger.v.accel * Math.pow(timeStep, 2);
						merger.v.speed = merger.v.accel *timeStep;
					}
				}

			}
			lane.head.next.prev = null;
			lane.head = lane.head.next;
		}

	}

	public static void processExit(trafficLane exit1, trafficLane exit2, trafficLane exit3, trafficLane exit4, trafficLane exit5,  trafficLane exit6, trafficLane circle, float laneSpeedLim, float timeStep){
		vehicleNode exiter = circle.head;
		vehicleNode check = circle.head;
		trafficLane exit = null;
		float exitPos = 0;
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
				}
			}
		}
	}

	public static void moveTrafficExit(trafficLane exit, float timeStep, float laneSpeedLim, int laneLength){
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

	public static void moveTrafficLane(trafficLane lane, float timeStep){
		if (lane.size > 0){
			vehicleNode cur = lane.head;
			for(int i = 0; i < lane.size; i++){

				if(cur.v.position > cur.v.brakeDist  && cur == lane.head){
					cur.v.position = cur.v.position - (cur.v.speed * timeStep);

				}else if(cur.v.position > cur.v.brakeDist && cur.prev.v.speed > 0){
					cur.v.position = cur.v.position - (cur.v.speed * timeStep);
					if((cur.v.position - (cur.prev.v.position + cur.prev.v.length)) < (cur.v.brakeDist *(cur.v.aggLvl * 0.1))){
						cur.v.position = cur.prev.v.position + cur.prev.v.length + (cur.v.brakeDist *(cur.v.aggLvl * 0.1));
						cur.v.speed = cur.prev.v.speed;
					}

				}else if(cur.v.position < cur.v.brakeDist){

					if(cur == lane.head){
						cur.v.position = 0;
						cur.v.speed = 0;
					}else{
						cur.v.position = cur.prev.v.position + cur.prev.v.length + (cur.v.length * cur.v.aggLvl);
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
					minSUVBrake = sc.nextFloat();
					break;

				case "avSUVBrake":
					avSUVBrake = sc.nextFloat();
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
					break;

				case "avReact":
					avReact = sc.nextFloat();
					break;

				case "maxReact":
					maxReact = sc.nextFloat();
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

	public static void createTraffic( trafficLane lane1, trafficLane lane2,  trafficLane lane3, trafficLane lane4, trafficLane lane5, trafficLane lane6, trafficLane exit1, trafficLane exit2, trafficLane exit3, trafficLane exit4, trafficLane exit5, trafficLane exit6, trafficLane circle, int numLanes, float minReact, float avReact,	float maxReact, int minAggression,
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

	public static void setUpLane (vehicle car, float speedLim) {
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
		parseConfigFile();
		cirDiameter = cirDiameter *4;
		Circle trafficCircle = new Circle();
		int circleCenter = 750;
		trafficCircle.setCenterX(circleCenter);
		trafficCircle.setCenterY(circleCenter);
		float rad = cirDiameter /2;
		trafficCircle.setRadius(rad);

		float time = 0;
		l1Pos = (float) (rad * Math.toRadians(l1Loc));
		l2Pos = (float) (rad * Math.toRadians(l2Loc));
		l3Pos = (float) (rad * Math.toRadians(l3Loc));
		l4Pos = (float) (rad * Math.toRadians(l4Loc));
		if (numLanes == 5) {
			l5Pos = (float) (rad * Math.toRadians(l6Loc));
		} else if (numLanes == 6) {
			l5Pos = (float) (rad * Math.toRadians(l6Loc));
			l6Pos = (float) (rad * Math.toRadians(l6Loc));
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
		primaryStage.show();
		Group vehicles = new Group();
		Timeline TL = new Timeline();
		
		for(int i = 0; i < 20000; i++) {
			
			
			vehicles = null;
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
			
			vehicleNode cur;
			cur = lane1.head;
	        TL.getKeyFrames().addAll(new KeyFrame(Duration.ofMillis(500), 

			for(int j = 0; j < lane1.size; j++){
				Circle dot = new Circle();
				dot.setRadius(5);
				dot.setFill(Color.RED);
				dot.setCenterX((cur.v.position * Math.sin(Math.toRadians(l1Loc))) + rad * Math.sin(Math.toRadians(l1Loc)));
				dot.setCenterY((cur.v.position * Math.cos(Math.toRadians(l1Loc))) + rad * Math.cos(Math.toRadians(l1Loc)));
				new KeyValue (dot.getCenterX(), dot.getCenterY(), INTERPOLATOR);
				
				vehicles.getChildren().add(dot);
				cur = cur.next;
			}
			cur = lane2.head;
			for(int k = 0; k < lane2.size; k++){
				Circle dot = new Circle();
				dot.setRadius(5);
				dot.setFill(Color.BLUE);
				dot.setCenterX((cur.v.position * Math.sin(Math.toRadians(l2Loc))) + rad * Math.sin(Math.toRadians(l2Loc)));
				dot.setCenterY((cur.v.position * Math.cos(Math.toRadians(l2Loc))) + rad * Math.cos(Math.toRadians(l2Loc)));
				
				vehicles.getChildren().add(dot);
				cur = cur.next;
			}
			cur = lane3.head;
			for(int l = 0; l < lane3.size; l++){
				Circle dot = new Circle();
				dot.setRadius(5);
				dot.setFill(Color.GREEN);
				dot.setCenterX((int)((cur.v.position * Math.sin(Math.toRadians(l3Loc))) + rad * Math.sin(Math.toRadians(l3Loc))));
				dot.setCenterY((cur.v.position * Math.cos(Math.toRadians(l3Loc))) + rad * Math.cos(Math.toRadians(l3Loc)));
				
				vehicles.getChildren().add(dot);
				cur = cur.next;
			}
			cur = lane4.head;
			for(int m = 0; m < lane4.size; m++){
				Circle dot = new Circle();
				dot.setRadius(5);
				dot.setFill(Color.BLACK);
				dot.setCenterX((cur.v.position * Math.sin(Math.toRadians(l4Loc))) + rad * Math.sin(Math.toRadians(l4Loc)));
				dot.setCenterY((cur.v.position * Math.cos(Math.toRadians(l4Loc))) + rad * Math.cos(Math.toRadians(l4Loc)));
				
				vehicles.getChildren().add(dot);
				cur = cur.next;
			}
			cur = lane5.head;
			for(int n = 0; n < lane5.size; n++){
				Circle dot = new Circle();
				dot.setRadius(5);
				dot.setFill(Color.ORANGE);
				dot.setCenterX((cur.v.position * Math.sin(Math.toRadians(l5Loc))) + rad * Math.sin(Math.toRadians(l5Loc)));
				dot.setCenterY((cur.v.position * Math.cos(Math.toRadians(l5Loc))) + rad * Math.cos(Math.toRadians(l5Loc)));
				
				vehicles.getChildren().add(dot);
				cur = cur.next;
			}
			cur = lane6.head;
			for(int o = 0; o < lane6.size; o++){
				Circle dot = new Circle();
				dot.setRadius(5);
				dot.setFill(Color.YELLOW);
				dot.setCenterX((cur.v.position * Math.sin(Math.toRadians(l6Loc))) + rad * Math.sin(Math.toRadians(l6Loc)));
				dot.setCenterY((cur.v.position * Math.cos(Math.toRadians(l6Loc))) + rad * Math.cos(Math.toRadians(l6Loc)));
				
				vehicles.getChildren().add(dot);
				cur = cur.next;
			}
			
			vehicles.getChildren().add(root);

	                
			
			
			
			
			
		}

	}

}
