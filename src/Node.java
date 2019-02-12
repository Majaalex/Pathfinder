import java.lang.reflect.Array;
import java.util.ArrayList;

class Node {
    private String name;
    private double lat;
    private double lon;
    private ArrayList<Node> neighbours = new ArrayList<>();
    boolean visited;

    Node(String name, double lat, double lon){
        setName(name);
        setLat(lat);
        setLon(lon);
    }

    private void setName(String name){
        this.name = name;
    }
    private void setLat(double lat){
        this.lat = lat;
    }
    private void setLon(double lon){
        this.lon = lon;
    }
    void addNeighbour(Node neighbours){
        this.neighbours.add(neighbours);
    }

    String getName(){
        return name;
    }

    double getLon(){
        return lon;
    }
    double getLat(){
        return lat;
    }

    ArrayList<Node> getNeighbours(){
        return neighbours;
    }

    void setVisited(boolean visited){
        this.visited = visited;
    }

    boolean getVisited(){
        return visited;
    }

    private double getDistance(Node source, Node destination)
    {
        double toRad = Math.PI/180.0;
        double lon1 = source.getLon() * toRad;
        double lat1 = source.getLat()* toRad;
        double lon2 = destination.getLon()* toRad;
        double lat2 = destination.getLat()* toRad;
        /*
        lon1 = lon1*Math.PI/180.0;
        lat1 = lat1*Math.PI/180.0;
        lon2 = lon2*Math.PI/180.0;
        lat2 = lat2*Math.PI/180.0;*/


        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat/2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon/2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        // 6367 is Earth's radius, return value is KM
        return 6367 * c;
    }

    // The distance from the start to the current node
    double calculateH(Node destination){
        if(this.getNeighbours().contains(destination)){
            return getDistance(this,destination);
        } else {
            System.out.println("These cities are not connected.");
            return 0;
        }
    }

    double calculateG(Node destination){
        return 0;
        
    }
}
