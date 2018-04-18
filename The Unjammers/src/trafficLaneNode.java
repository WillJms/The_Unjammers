
public class trafficLaneNode {

	trafficLane Lane;
	trafficLaneNode next;
	trafficLaneNode prev;
	
	
	//Default Constructor
	public trafficLaneNode(trafficLane lane) {
		Lane = lane;
		next = prev = null;
	}
	
	/**
	 * secondary constructor
	 * @param lane - traffic lane (circle included)
	 * @param n - next Lane
	 * @param p - previous Lane
	 */
	public trafficLaneNode(trafficLane lane, trafficLaneNode n, trafficLaneNode p) {
		Lane = lane;
		next = n;
		prev = p;
	}
}
