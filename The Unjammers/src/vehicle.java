
public class vehicle {
	
	//class veriables
	public double position;
	public int speed;
	public int length;
	public int exitNum;
	public int aggLvl;
	public int maxAccl;
	public int minFolDist;
	public int vehicleClass;
	public int lane;
	public int vehicleID;
	
	//default constructor
	public vehicle(double position, int speed, int length, int exitNum, int aggLvl, int maxAccl, int minFolDist, int vehicleClass, int lane, int vehicleID){
		this.position = position;
		this.speed = speed;
		this.length = length;
		this.exitNum = exitNum;
		this.aggLvl = aggLvl;
		this.maxAccl = maxAccl;
		this.minFolDist = minFolDist;
		this.vehicleClass = vehicleClass;
		this.lane = lane;
		this.vehicleID = vehicleID;
	}
	//TODO figure out vehicle constructor based on user input
	public vehicle(){
		//stuff happens and random vehicles are generated

		
		
		
	}
	
	
	//Getters
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
	
	public int getMaxAccl(){
		return maxAccl;
	}
	
	public int getMinFolDist(){
		return minFolDist;
	}
	
	public int getVehicleClass(){
		return vehicleClass;
	}
	
	public int getLane(){
		return lane;
	}
	
	public int getVehicleID(){
		return vehicleID;
	}
}
