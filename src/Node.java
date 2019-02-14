import java.lang.reflect.Array;
import java.util.ArrayList;

class Node {
	static Node startNode;
	static Node endNode;
    private String name;
    private double lat;
    private double lon;
    private ArrayList<Node> neighbours = new ArrayList<>();
    private Node previousNode;
    static Node currentNode;
    private double totalG;

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
    void setPreviousNode(Node previousNode) {this.previousNode = previousNode;}
    private void setTotalG(double totalG) {
        this.totalG = totalG;
    }
	// SETTERS end
	//------------------------------
	
	//------------------------------
	// GETTERS start
    String getName(){return name;}
    private double getLon(){return lon;}
    private double getLat(){return lat;}
    ArrayList<Node> getNeighbours(){return neighbours;}
    public Node getPreviousNode() {return previousNode;}
    private double getTotalG() {
        return totalG;
    }
	// GETTERS end
	//------------------------------
	
	
	
	// Returns the distance between two nodes in KM
    private double getDistance(Node source, Node destination)
    {
        double toRad = Math.PI/180.0;
        double lon1 = source.getLon() * toRad;
        double lat1 = source.getLat()* toRad;
        double lon2 = destination.getLon()* toRad;
        double lat2 = destination.getLat()* toRad;

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat/2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon/2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        // 6367 is Earth's radius, return value is KM
        return 6367 * c;
    }
	

    // The distance from the previous to the current node
    double calcG(){
        if(this == startNode){
            this.setTotalG(0);
            return 0;
        } else{
            this.setTotalG(getDistance(this, previousNode) + previousNode.getTotalG());
            return this.getTotalG();
        }

    }

    double calcH(){
        return getDistance(this, endNode);
        
    }
}

