import java.util.*;
import java.util.function.BiConsumer;

public class Pathfinder {

    public static void main(String[] args) {
		TextHandler menu = new TextHandler();
		HashMap<String, Node> nodeHashMap = createGraph();
        //ArrayList<Node> nodes = createGraph();
		menu.displayMenu();
		int choice;
		choice = menu.userInt();
            switch(choice){
                case 1:
                    printNodesAndNeighbours(nodeHashMap);
                    break;
                case 2:
                    System.out.println("Choose a start node:");
                    String startNode = menu.chooseCity(nodeHashMap);

                    System.out.println("Choose an end node:");
                    String endNode = menu.chooseCity(nodeHashMap);

                    setStartEndNodes(startNode, endNode, nodeHashMap);
                    runPathfinder();
                    break;
            }

	
    }

    private static void runPathfinder() {
        double tentativeG = 0;

        ArrayList<Node> closedNodes = new ArrayList<>();
        PriorityQueue<Node> openNodesQueue = new PriorityQueue<Node>( new NodeComparator());

        Node.startNode.setTotalG(0);
        Node.startNode.setTotalF(getDistanceBetween(Node.startNode, Node.endNode));
        openNodesQueue.add(Node.startNode);
        while (!openNodesQueue.isEmpty()){
            // Updates currentNode to be the one with the lowest F in the queue
            Node.currentNode = openNodesQueue.poll();
            System.out.println(Node.currentNode.getName() + " is current");
            if (Node.currentNode == Node.endNode){
                printBestPath(Node.currentNode);
                return;
            }
            closedNodes.add(Node.currentNode);

            // Enter each neighbour
           for (Node neighbour : Node.currentNode.getNeighbours()){
               // skip if it's a closed node
                if (closedNodes.contains(neighbour)){ continue; }

                tentativeG = Node.currentNode.getTotalG() + getDistanceBetween(Node.currentNode, neighbour);
                // add to queue if it's not already there
                if (!openNodesQueue.contains(neighbour)){
                    neighbour.setTotalG(tentativeG);
                    neighbour.setTotalF(tentativeG + getDistanceBetween(neighbour, Node.endNode));
                    openNodesQueue.add(neighbour);
                } else
                    // skip the node if it already has a better G value
                    if (tentativeG >= neighbour.getTotalG()){ continue; }

                    neighbour.setPreviousNode(Node.currentNode);
                    neighbour.setTotalG(tentativeG);
                    neighbour.setTotalF(tentativeG + getDistanceBetween(neighbour, Node.endNode));
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

    private static void printBestPath(Node currentNode) {
        System.out.println("The best path is: ");
        ArrayList<Node> nodeList = new ArrayList<>();

        while (currentNode != Node.startNode){
            nodeList.add(currentNode);
            currentNode = currentNode.getPreviousNode();
        }
        nodeList.add(currentNode);
        int j = 1;
        for (int i = nodeList.size() - 1; i >= 0; i--){
            System.out.println(j++ + " " + nodeList.get(i).getName());
        }
    }

    private static void setStartEndNodes(String start, String end, HashMap<String, Node> nodeHashMap) {
        Node.startNode = nodeHashMap.get(start);
        Node.endNode = nodeHashMap.get(end);
    }

    private static void printNodesAndNeighbours(HashMap<String, Node> nodeHashMap) {
        for (Node node : nodeHashMap.values()){
            System.out.println(node.getName());
            for (Node neighbour : node.getNeighbours()){
                System.out.println("     > " + neighbour.getName());
            }
        }
    }

    private static HashMap<String, Node> createGraph()
    {
        //Skapar en nod för varje tågstation
        Node hki = new Node("Helsinki", 60.1640504, 24.7600896);//1
        Node tpe = new Node("Tampere", 61.6277369, 23.5501169);
        Node tku = new Node("Turku", 60.4327477, 22.0853171);
        Node jyv = new Node("Jyväskylä", 62.1373432, 25.0954598);
        Node kpo = new Node("Kuopio", 62.9950487, 26.556762);
        Node lhi = new Node("Lahtis", 60.9948736, 25.5747703);
        Node kar = new Node("Karjaa", 60.0714, 23.6619);
        Node kou = new Node("Kouvola", 60.8679, 26.7042);
        Node por = new Node("Pori", 61.4851, 21.7974);
        Node joe = new Node("Joensuu", 62.6010, 29.7636);//10
        Node sei = new Node("Seinäjoki", 62.7877, 22.8504);
        Node vsa = new Node("Vasa", 63.0951, 21.6165);
        Node kaj = new Node("Kajaani", 64.2222, 27.7278);
        Node oul = new Node("Oulu", 65.0121, 25.4651);
        Node rva = new Node("Rovaniemi", 66.5039, 25.7294);
        Node par = new Node("Parikkala",61.5502, 29.5024 );
        Node yli = new Node("Ylivieska", 64.0723, 24.5336);//17

        //Förbindelser från Helsingfors tågstation
        hki.addNeighbour(tpe); //Tammerfors
        hki.addNeighbour(kar); //Karis
        hki.addNeighbour(lhi); //Lahtis
        hki.addNeighbour(kou); //kouvola


        //Förbindelser från Tammerfors tågstation
        tpe.addNeighbour(hki); //Helsingfors
        tpe.addNeighbour(tku); //Åbo
        tpe.addNeighbour(jyv); //Jyväskylä
        tpe.addNeighbour(lhi); //Lahtis
        tpe.addNeighbour(por); //Pori
        tpe.addNeighbour(sei); //Seinäjoki

        //Förbindelser från Åbo tågstation
        tku.addNeighbour(kar); //Karjaa
        tku.addNeighbour(tpe); //Tammerfors

        //Förbindelser från Jyväskylä tågstation
        jyv.addNeighbour(tpe);//Tammerfors
        jyv.addNeighbour(sei);//seinäjoki
        jyv.addNeighbour(kpo);//kuopio
        jyv.addNeighbour(joe);//joensuu
        jyv.addNeighbour(kou);//kouvola

        //Förbindelser från Kuopio tågstation
        kpo.addNeighbour(lhi); //Lahtis
        kpo.addNeighbour(kaj);//kajaani
        kpo.addNeighbour(joe);//joensuu

        //Förbindelser från Lahtis tågstation
        lhi.addNeighbour(hki); //Helsingors
        lhi.addNeighbour(tpe); //Tammerfors
        lhi.addNeighbour(kou); //Kouvola

        //TODO: PROI VASA OULU ROVA YLIVIESKA KAJAANI JOENSUU PARIKKALA KOUVOLA
        //förbindelser från Pori
        por.addNeighbour(tpe);//tampere

        // vasa
        vsa.addNeighbour(sei);//seinäjoki

        //seinäjoki
        sei.addNeighbour(vsa);
        sei.addNeighbour(yli);
        sei.addNeighbour(jyv);
        sei.addNeighbour(tpe);

        //ylivieska
        yli.addNeighbour(oul);
        yli.addNeighbour(sei);

        //oulu
        oul.addNeighbour(rva);
        oul.addNeighbour(yli);
        oul.addNeighbour(kaj);

        //rova
        rva.addNeighbour(oul);

        //kajaani
        kaj.addNeighbour(oul);
        kaj.addNeighbour(kpo);

        //joensuu
        joe.addNeighbour(kpo);
        joe.addNeighbour(jyv);
        joe.addNeighbour(par);

        //parikkala
        par.addNeighbour(joe);
        par.addNeighbour(kou);

        //kouvola
        kou.addNeighbour(par);
        kou.addNeighbour(jyv);
        kou.addNeighbour(lhi);
        kou.addNeighbour(hki);

        //karjaa
        kar.addNeighbour(hki);
        kar.addNeighbour(tku);

        //Skapar en lista för grafen och sätter in alla noder
        ArrayList<Node> graph = new ArrayList<>();
        HashMap<String, Node> nodeHash = new HashMap<>();
        graph.add(hki);
        graph.add(tpe);
        graph.add(tku);
        graph.add(jyv);
        graph.add(kpo);
        graph.add(lhi);
        graph.add(kar);
        graph.add(vsa);
        graph.add(oul);
        graph.add(rva);
        graph.add(kaj);
        graph.add(joe);
        graph.add(sei);
        graph.add(par);
        graph.add(yli);
        graph.add(kou);
        graph.add(kar);
        graph.add(por);
        for (Node node : graph){
            nodeHash.put(node.getName(), node);
        }

        return nodeHash;
    }
}

class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node node1, Node node2) {
        if (node1.getTotalF() > node2.getTotalF()){
            return 1;
        } else if (node1.getTotalF() < node2.getTotalF()){
            return -1;
        }
        return 0;
    }
}

