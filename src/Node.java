import java.util.*;

class Node extends PriorityQueue {

    // Constructor variables
    private String name;
    private double lat;
    private double lon;
    private ArrayList<Node> neighbours = new ArrayList<>();

    // Variables used for A*
    private double totalG = Double.POSITIVE_INFINITY;
    private double totalF = Double.POSITIVE_INFINITY;
    private Node previousNode;

    // Static variables
    static Node startNode;
    static Node endNode;
    static Node currentNode;

    Node(String name, double lat, double lon){
        setName(name);
        setLat(lat);
        setLon(lon);
    }

	
	//------------------------------
	// SETTERS
    private void setName(String name){this.name = name;}
    private void setLat(double lat){this.lat = lat;}
    private void setLon(double lon){this.lon = lon;}
    void addNeighbour(Node neighbours){this.neighbours.add(neighbours);}

    // A* sets
    void setPreviousNode(Node previousNode) {this.previousNode = previousNode;}
    void setTotalG(double totalG){ this.totalG = totalG; }
    void setTotalF(double totalF){ this.totalF = totalF; }
    static void setStartNode(Node start){startNode = start;}
    static void setEndNode(Node end){endNode = end;}
	// SETTERS end
	//------------------------------
	
	//------------------------------
	// GETTERS start
    String getName(){return name;}
    double getLon(){return lon;}
    double getLat(){return lat;}
    ArrayList<Node> getNeighbours(){return neighbours;}

    // A* gets
    Node getPreviousNode() {return previousNode;}
    double getTotalG() {return totalG;}
    double getTotalF() {return totalF;}
    static Node getEndNode(){return endNode;}
    static Node getStartNode(){return startNode; }
	// GETTERS end
	//------------------------------
	
	
	

}

