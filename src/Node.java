import java.util.*;

class Node extends PriorityQueue implements Comparable<Node> {
	static Node startNode;
	static Node endNode;
    private String name;
    private double lat;
    private double lon;
    private ArrayList<Node> neighbours = new ArrayList<>();
    private Node previousNode;
    static Node currentNode;
    private double totalG;
    private double totalF;

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
    void setTotalG(double totalG) {
        this.totalG = totalG;
    }
    void setTotalF(double totalF) {
        this.totalF = totalF;
    }
    static void setCurrentNode(Node currentNode) {
        Node.currentNode = currentNode;
    }
	// SETTERS end
	//------------------------------
	
	//------------------------------
	// GETTERS start
    String getName(){return name;}
    private double getLon(){return lon;}
    private double getLat(){return lat;}
    ArrayList<Node> getNeighbours(){return neighbours;}
    Node getPreviousNode() {return previousNode;}
    double getTotalG() {
        return this.totalG;
    }
    double getTotalF() {
        return this.totalF;
    }
	// GETTERS end
	//------------------------------
	
	
	
	// Returns the distance between two nodes in KM
    double getDistanceTo(Node destination)
    {
        double toRad = Math.PI/180.0;
            /*System.out.println(aye++ + ":");
            System.out.println(source.getName());
            System.out.println(source.getLon());
            System.out.println(destination.getName());
            System.out.println(destination.getLon());
*/
        double lon1 = this.getLon()  * toRad;
        double lat1 = this.getLat() * toRad;
        double lon2 = destination.getLon() * toRad;
        double lat2 = destination.getLat() * toRad;

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat/2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon/2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        // 6367 is Earth's radius, return value is KM
        return 6367 * c;
    }

    double calculateH(){
        return getDistanceTo(endNode);
        
    }


    @Override
    public int compareTo(Node o) {
        return 0;
    }
}

