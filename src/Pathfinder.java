import java.util.*;

public class Pathfinder {

    public static void main(String[] args) {
		TextHandler menu = new TextHandler();
        ArrayList<Node> nodes = createGraph();
		menu.displayMenu();
		int choice = menu.userInt();

		switch(choice){
			case 1:
				printNodesAndNeighbours(nodes);
				break;
			case 2:
			    System.out.println("Choose a start node:");
                int startNode = menu.chooseCity();
                System.out.println("Choose an end node:");
                int endNode = menu.chooseCity();
                setStartEndNodes(startNode, endNode, nodes);
                runPathfinder();
                break;
		}
        
	
    }

    private static void runPathfinder() {
        double tentativeG = 0;

        ArrayList<Node> closedNodes = new ArrayList<>();
        PriorityQueue<Node> openNodesQueue = new PriorityQueue<Node>( new NodeComparator());

        Node.startNode.setTotalG(0);
        Node.startNode.setTotalF(getDistanceBetween(Node.getStartNode(), Node.getEndNode()));
        openNodesQueue.add(Node.startNode);
        while (!openNodesQueue.isEmpty()){
            // Updates currentNode to be the one with the lowest F in the queue
            Node.currentNode = openNodesQueue.poll();
            if (Node.currentNode == Node.endNode){
                printBestPath(Node.currentNode);
                return;
            }

            closedNodes.add(Node.currentNode);

            for (Node neighbour : Node.currentNode.getNeighbours()){
                if (closedNodes.contains(neighbour)){ continue; }

                tentativeG = Node.currentNode.getTotalG() + getDistanceBetween(Node.currentNode, neighbour);
                if (!openNodesQueue.contains(neighbour)){
                    if (neighbour.getTotalG() == 0){
                        neighbour.setTotalG(tentativeG);
                        neighbour.setTotalF(tentativeG + getDistanceBetween(neighbour, Node.endNode));
                    }
                    openNodesQueue.add(neighbour);
                } else if (tentativeG >= neighbour.getTotalG()){ continue; }

                neighbour.setPreviousNode(Node.currentNode);
                neighbour.setTotalG(tentativeG);
                neighbour.setTotalF(neighbour.getTotalG() + getDistanceBetween(neighbour, Node.endNode));
            }
        }
    }

    // Returns the distance between two nodes in KM
    static private double getDistanceBetween(Node origin, Node destination)
    {
        double toRad = Math.PI/180.0;
        double lon1 = origin.getLon()  * toRad;
        double lat1 = origin.getLat() * toRad;
        double lon2 = destination.getLon() * toRad;
        double lat2 = destination.getLat() * toRad;

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat/2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon/2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        // 6367 is Earth's radius, return value is KM
        return 6367 * c;
    }

    private double calculateH(Node origin){
        return getDistanceBetween(origin, Node.getEndNode());

    }

    private static void printBestPath(Node currentNode) {
        System.out.println("The best path is: ");
        String path = "";
        while (currentNode != Node.startNode){
            path = path.concat(currentNode.getName() + "  ");
            currentNode = currentNode.getPreviousNode();
        }

        path = path.concat(Node.startNode.getName() + "  ");
        System.out.println(path);
    }

    private static void setStartEndNodes(int start, int end, ArrayList<Node> nodes) {
        switch (start){
            // HKI
            case 1:
                Node.startNode = nodes.get(start - 1);
                break;
                // Tampere
            case 2:
                Node.startNode = nodes.get(start - 1);
                break;
                // Turku
            case 3:
                Node.startNode = nodes.get(start - 1);
                break;
                //Jyväskylä
            case 4:
                Node.startNode = nodes.get(start - 1);
                break;
                //Kuopio
            case 5:
                Node.startNode = nodes.get(start - 1);
                break;
                // Lahtis
            case 6:
                Node.startNode = nodes.get(start - 1);
                break;
        }
        switch (end){
            // HKI
            case 1:
                Node.endNode = nodes.get(end - 1);
                break;
                // Tampere
            case 2:
                Node.endNode = nodes.get(end - 1);
                break;
                // Turku
            case 3:
                Node.endNode = nodes.get(end - 1);
                break;
                //Jyväskylä
            case 4:
                Node.endNode = nodes.get(end - 1);
                break;
                //Kuopio
            case 5:
                Node.endNode = nodes.get(end - 1);
                break;
                // Lahtis
            case 6:
                Node.endNode = nodes.get(end - 1);
                break;
        }
    }

    private static void printNodesAndNeighbours(ArrayList<Node> nodes) {
        for (Node node : nodes) {
            System.out.println(node.getName() + " is connected to ");
            for (Node neighbour : node.getNeighbours()){
                System.out.println("     " + neighbour.getName());
            }
            System.out.println();
        }
    }

    private static ArrayList<Node> createGraph()
    {
        //Skapar en nod för varje tågstation
        Node hki = new Node("Helsingfors", 60.1640504, 24.7600896);
        Node tpe = new Node("Tammerfors", 61.6277369, 23.5501169);
        Node tku = new Node("Abo", 60.4327477, 22.0853171);
        Node jyv = new Node("Jyväskylä", 62.1373432, 25.0954598);
        Node kpo = new Node("Kuopio", 62.9950487, 26.556762);
        Node lhi = new Node("Lahtis", 60.9948736, 25.5747703);

        //Förbindelser från Helsingfors tågstation
        hki.addNeighbour(tpe); //Tammerfors
        hki.addNeighbour(tku); //Åbo
        hki.addNeighbour(lhi); //Lahtis

        //Förbindelser från Tammerfors tågstation
        tpe.addNeighbour(hki); //Helsingfors
        tpe.addNeighbour(tku); //Åbo
        tpe.addNeighbour(jyv); //Jyväskylä
        tpe.addNeighbour(lhi); //Lahtis

        //Förbindelser från Åbo tågstation
        tku.addNeighbour(hki); //Helsingfors
        tku.addNeighbour(tpe); //Tammerfors

        //Förbindelser från Jyväskylä tågstation
        jyv.addNeighbour(tpe); //Tammerfors

        //Förbindelser från Kuopio tågstation
        kpo.addNeighbour(lhi); //Lahtis

        //Förbindelser från Lahtis tågstation
        lhi.addNeighbour(hki); //Helsingors
        lhi.addNeighbour(tpe); //Tammerfors
        lhi.addNeighbour(kpo); //Kuopio

        //Skapar en lista för grafen och sätter in alla noder
        ArrayList<Node> graph = new ArrayList<>();
        graph.add(hki);
        graph.add(tpe);
        graph.add(tku);
        graph.add(jyv);
        graph.add(kpo);
        graph.add(lhi);

        return graph;
    }
}

class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node node1, Node node2) {
        if (node1.getTotalF() < node2.getTotalF()){
            return 1;
        } else if (node1.getTotalF() > node2.getTotalF()){
            return -1;
        }
        return 0;
    }
}

