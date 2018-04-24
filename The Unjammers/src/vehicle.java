import java.util.Random;
import javafx.scene.shape.Circle;

public class vehicle {

	//class veriables
	public double position;
	public int speed;
	public int length;
	public int exitNum;
	public int aggLvl;
	public int lane;
	public int vehicleID;
	public int brakeDist;
	public int accel;
	public int react;
	Circle dot = new Circle();
	

	//default constructor
	public vehicle(double position, int speed, int length, int exitNum, int aggLvl, int maxAccl, int lane, int vehicleID){
		this.position = position;
		this.speed = speed;
		this.length = length;
		this.exitNum = exitNum;
		this.aggLvl = aggLvl;
		this.lane = lane;
		this.vehicleID = vehicleID;
		
	}
	//TODO figure out vehicle constructor based on user input
	public vehicle(	int numLanes, int minAggression, int maxAggression, int numCars,int minCarLength, int maxCarLength, int avCarLength, int minCarBrake, int maxCarBrake, int avCarBrake, int minCarAccl, int maxCarAccl, int avCarAccl, int minReact, int avReact, int maxReact){
		Random rand = new Random();
		position = 0;
		speed = 0;
		length = (int) ((Math.random() * ((maxCarLength - minCarLength) + 1 )) + minCarLength);//vehicleRandomizer(minCarLength, avCarLength, maxCarLength);
		brakeDist = (int) ((Math.random() * ((maxCarBrake - minCarBrake) + 1 )) + minCarBrake);//vehicleRandomizer(minCarBrake, avCarBrake, maxCarBrake);
		accel = (int) ((Math.random() * ((maxCarAccl - minCarAccl) + 1 )) + minCarAccl);//vehicleRandomizer(minCarAccl, avCarAccl, maxCarAccl);
		react = (int) ((Math.random() * ((maxReact - minReact) + 1 )) + minReact);//vehicleRandomizer(minReact, avReact, maxReact);
		exitNum = rand.nextInt(numLanes) + 1;
		aggLvl = (int)(Math.random() * ((maxAggression - minAggression) + 1 )) + minAggression;
		lane = rand.nextInt(numLanes) + 1;
		dot.setRadius(2);

	}

	//Getters and setter
	public double getPosition(){
		return position;
	}
	public int getSpeed(){
		return speed;
	}
	public int getLength(){
		return length;
	}
	public int getExitNum(){
		return exitNum;
	}
	public int getAggLvl(){
		return aggLvl;
	}
	public int getLane(){
		return lane;
	}
	public int getVehicleID(){
		return vehicleID;
	}
	public int getBrake(){
		return brakeDist;
	}
	public int getAccl(){
		return accel;
	}
	public int getReact(){
		return react;
	}
	public void setID (int num){
		vehicleID = num;
	}
	public void setPosition (int l) {
		position = l;
	}
	public void setSpeed (int s) {
		speed = s;
	}
	public void setDot( Circle circle){
		dot = circle;
	}

	public int vehicleRandomizer(int min, int av, int max){
		int M = (min*min);
		int A = (av * av); 
		int X = (max * max);
		int var = M + A + X;
		int std = (int) Math.sqrt(var);
		Random gen = new Random();
		int rand = (int)((gen.nextGaussian()) * std) + av;
		return rand;
	}
}
