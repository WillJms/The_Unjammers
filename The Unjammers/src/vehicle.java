import java.util.Random;
public class vehicle {
	
	//class veriables
	public double position;
	public int speed;
	public float length;
	public int exitNum;
	public int aggLvl;
	public int lane;
	public int vehicleID;
	
	//default constructor
	public vehicle(double position, int speed, int length, int exitNum, int aggLvl, int maxAccl, int minFolDist, int vehicleClass, int lane, int vehicleID){
		this.position = position;
		this.speed = speed;
		this.length = length;
		this.exitNum = exitNum;
		this.aggLvl = aggLvl;
		this.lane = lane;
		this.vehicleID = vehicleID;
	}
	//TODO figure out vehicle constructor based on user input
	public vehicle(	int minAggression, int maxAggression, int numCars, int numXs, int numTrucks, int numIT, int numLanes, float minCarLength, float maxCarLength, float avCarLength, float minXLength, float avXLength, float maxXLength, float minSUVLength, float avSUVLength, float maxSUVLength, float minTruckLength, float avTruckLength, float maxTruckLength, float minITLength, float avITLength, float maxITLength, float minCarBrake, float maxCarBrake, float avCarBrake, float minXBrake, float avXBrake, float maxXBrake, float minSUVBrake, float avSUVBrake, float maxSUVBrake, float minTruckBrake, float avTruckBrake, float maxTruckBrake, float minITBrake, float avITBrake, float maxITBrake, float minCarAccl, float maxCarAccl, float avCarAccl, float minXAccl, float avXAccl, float maxXAccl, float minSUVAccl, float avSUVAccl, float maxSUVAccl, float minTruckAccl, float avTruckAccl, float maxTruckAccl, float minITAccl, float avITAccl, float maxITAccl, float minReact, float avReact, float maxReact){
		//stuff happens and random vehicles are generated
		for(int i =0; i < numCars; i++){
			Random rand = new Random();
			aggLvl = (int)(Math.random() * ((maxAggression - minAggression) + 1 )) + minAggression;
			speed = 0;
			length = vehicleRandomizer(minCarLength, avCarLength, maxCarLength);
			exitNum = rand.nextInt(numLanes) + 1;
			position = 0;
		}
	}
	
	//Getters
	public double getPosition(){
		return position;
	}
	
	public int getSpeed(){
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
