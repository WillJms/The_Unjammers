
public class trafficLane {

	public vehicleNode head;
	public vehicleNode tail;
	public int size;
	
	//default constructor
	public trafficLane(){
		head = tail = null;
		size = 0;
	}
	
	//adds vehicle based on position
	public void addVehicle(vehicle v) {
		//Empty
		if (head == null){
			head = tail = new vehicleNode(v);
			size++;
		}else{
			vehicleNode cur = head;
			while (v.getPosition() > cur.v.getPosition()){
				cur = cur.next;
			}
			(cur.prev).next = 	new vehicleNode(v, cur.prev, cur.next);
			cur.prev = (cur.prev).next;
			if (cur.next == null){
				tail = cur;
			}
			size++;
		}
	}
	
	public void deleteVehicle(int vehicleID){
		vehicleNode cur = head;
		while (vehicleID != cur.v.getVehicleID()){
			cur = cur.next;
		}
		(cur.prev).next = cur.next;
		(cur.next).prev = cur.prev;
	}

}
