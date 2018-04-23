import java.util.Random;
import javafx.scene.shape.Circle;

public class vehicle {

	//class veriables
	public double position;
	public float speed;
	public float length;
	public int exitNum;
	public int aggLvl;
	public int lane;
	public int vehicleID;
	public float brakeDist;
	public float accel;
	public float react;
	

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
	public vehicle(	int numLanes, int minAggression, int maxAggression, int numCars,float minCarLength, float maxCarLength, float avCarLength, float minCarBrake, float maxCarBrake, float avCarBrake, float minCarAccl, float maxCarAccl, float avCarAccl, float minReact, float avReact, float maxReact){
		Random rand = new Random();
		position = 0;
		speed = 0;
		length = vehicleRandomizer(minCarLength, avCarLength, maxCarLength);
		brakeDist = vehicleRandomizer(minCarBrake, avCarBrake, maxCarBrake);
		accel = vehicleRandomizer(minCarAccl, avCarAccl, maxCarAccl);
		react = vehicleRandomizer(minReact, avReact, maxReact);
		exitNum = rand.nextInt(numLanes) + 1;
		aggLvl = (int)(Math.random() * ((maxAggression - minAggression) + 1 )) + minAggression;
		lane = rand.nextInt(numLanes) + 1;

	}

	//Getters and setter
	public double getPosition(){
		return position;
	}
	public float getSpeed(){
		return speed;
	}
	public float getLength(){
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
	public float getBrake(){
		return brakeDist;
	}
	public float getAccl(){
		return accel;
	}
	public float getReact(){
		return react;
	}
	public void setID (int num){
		vehicleID = num;
	}
	public void setPosition (float l) {
		position = l;
	}
	public void setSpeed (float s) {
		speed = s;
	}

	public float vehicleRandomizer(float min, float av, float max){
		float M = (min*min);
		float A = (av * av); 
		float X = (max * max);
		float var = M + A + X;
		float std = (float) Math.sqrt(var);
		Random gen = new Random();
		float rand = (float)((gen.nextGaussian()) * std) + av;
		return rand;
	}
}
