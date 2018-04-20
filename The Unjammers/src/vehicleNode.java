
public class vehicleNode {
	//variables
	vehicle v;
	vehicleNode next;
	vehicleNode prev;
	
	
	//Default constructor
	public vehicleNode(vehicle vehicle){
		v = vehicle;
		next = null;
		prev = null;
	}
	
	
	/**
	 * secondary constructor
	 * @param v - vehicle info
	 * @param n - next node
	 * @param p - previous node
	 */
	public vehicleNode(vehicle vehicle, vehicleNode n, vehicleNode p){
		v = vehicle;
		next = n;
		prev = p;
	}
}
