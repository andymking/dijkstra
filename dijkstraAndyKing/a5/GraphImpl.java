package a5;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class GraphImpl implements Graph {
    Map<String, Node> nodes;

    public GraphImpl() {
        nodes = new HashMap<>();
    }

    @Override
    public boolean addNode(String name) {
        if (nodes.containsKey(name)) {
            return false;
        }
        else {
            Node newNode = new NodeImpl(name);
            nodes.put(name, newNode);

            return true;
        }
    }

    @Override
    public boolean addEdge(String src, String dest, double weight) {
        if (weight < 0.0) {
            return false;
        }
        if (nodes.containsKey(src) == false) {
            return false;
        }
        if (nodes.containsKey(dest) == false) {
            return false;
        }
        if (nodes.get(src).hasEdge(dest)) {
            return false;
        }
        Edge newEdge = new EdgeImpl(src, dest, weight);
        nodes.get(src).putEdge(newEdge);
        nodes.get(src).addAdj(dest, nodes.get(dest));
        nodes.get(dest).addInDegree();
        return true;
    }

    @Override
    public boolean deleteNode(String name) {
        if (nodes.containsKey(name) == false) {
            return false;
        }
        else {
            removeInEdges(name);
            nodes.remove(name);
            return true;
        }
    }

    public void removeInEdges(String name) {
        for (Node node : nodes.values()) {
            deleteEdge(node.getName(), name);
        }
    }

    @Override
    public boolean deleteEdge(String src, String dest) {
        if (nodes.containsKey(src) == false) {
            return false;
        }

        if (nodes.containsKey(dest) == false) {
            return false;
        }

        if (nodes.get(src).hasEdge(dest) == false) {
            return false;
        }

        else {
            nodes.get(src).removeEdge(dest);
            nodes.get(src).removeAdj(dest, nodes.get(dest));
            nodes.get(dest).subInDegree();
            return true;
        }
    }

    @Override
    public int numNodes() {
        return nodes.size();
    }

    @Override
    public int numEdges() {
        int size = 0;
        for (Node node : nodes.values()) {
            size += node.numEdges();
        }
        return size;
    }

    @Override
    public Map<String, Double> dijkstra(String start) {
        Map<String, Double> completedGraph = new HashMap<>(); // Completed graph to be returned.
        PriorityQueue<Node> prq = new PriorityQueue<>(Comparator.comparing(Node::getTotalDistTo)); // Priority Queue used to run the algorithm.
        prq.add(nodes.get(start)); // Add starting node to Priority Queue.
        double pLen = 0; // Keeps track of distance to each node.
        if (nodes.get(start).numEdges() == 0) {
            // Edge case in which there is only one node.
            completedGraph.put(start, 0.0);
            return completedGraph;
        }
        while (prq.size() > 0) {
            // Main algorithm loop.
            if (prq.peek().isKnown()) {
                // Tests if node has been visited or not, and if so, to remove it.
                prq.poll();
                if (prq.size() == 0) {
                    break;
                }
            }
            Node obj = prq.poll(); // Takes off highest priority node from queue, starting the algorithm.
            String name = obj.getName(); // Keeps the name of the node in a string for reference.
            obj.setKnown(true); // Sets known to true, letting the program know that the node has been visited.
            for (Node adjacent : obj.getAdjs().values()) {
                // For loop looking at adjacent nodes to the start node.
                if (!adjacent.isKnown()) {
                    pLen = obj.getEdge(adjacent.getName()).getWeight() + obj.getTotalDistTo();
                    if (pLen < adjacent.getTotalDistTo() ^ adjacent.getTotalDistTo() == 0) {
                        // If total distance to current node plus adjacent edge weight is less than the adjacent total distance, update and add to queue.
                        adjacent.setTotalDistTo(pLen);
                        adjacent.setRecentNode(name);
                        prq.add(adjacent);
                    }
                }
            }

        }
        organize(completedGraph); // Put node names and distances into a HashMap for return.
        return completedGraph;
    }

    public void organize(Map<String, Double> graph) {
        for (Node node : nodes.values()) {
            graph.put(node.getName(), node.getTotalDistTo());
        }
    }
}
