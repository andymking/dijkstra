package a5;

import java.util.HashMap;
import java.util.Map;

public class NodeImpl implements Node {

    private String name;
    private Map<String, Edge> edges;
    private int inDegree;
    private boolean known;
    private double totalDistTo; // Keeps track of total distance to node during algorithm execution.
    private String recentNode; // Keeps track of which node this was compared to most recently.
    private Map<String, Node> adjs;

    public NodeImpl(String name){
        this.name = name;
        this.edges = new HashMap<>();
        this.inDegree = 0;
        this.known = false;
        this.totalDistTo = 0.0;
        this.recentNode = "None";
        this.adjs = new HashMap<>();
    }

    public void putEdge(Edge edge) {
        this.edges.put(edge.getDest(), edge);
    }

    public void removeEdge(String dest) {
        this.edges.remove(dest);
    }

    public String getName() {
        return this.name;
    }

    public boolean hasEdge(String dest) {
        if (this.edges.containsKey(dest)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isAdjacent(String dest) {
        if (this.edges.containsKey(dest)) {
            return true;
        }
        else {
            return false;
        }
    }

    public int getInDegree() {
        return this.inDegree;
    }

    public void subInDegree() {
        this.inDegree--;
    }

    public void addInDegree() {
        this.inDegree++;
    }

    public void addAdj(String name, Node node) {
        this.adjs.put(name, node);
    }

    public void removeAdj(String name, Node node) {
        this.adjs.remove(name, node);
    }

    public int numEdges() {
        return this.edges.size();
    }

    public Edge getEdge(String dest) {
        return this.edges.get(dest);
    }

    public double getTotalDistTo() {
        return this.totalDistTo;
    }

    public void setTotalDistTo(double dist) {
        this.totalDistTo = dist;
    }

    public void sumTotalDistTo(double dist) {
        this.totalDistTo += dist;
    }

    public void setRecentNode(String recent) {
        this.recentNode = recent;
    }

    public String getRecentNode() {
        return this.recentNode;
    }

    public boolean isKnown() {
        if (this.known) {
            return true;
        }
        else {
            return false;
        }
    }

    public void setKnown(boolean bool) {
        this.known = bool;
    }

    public Map<String, Node> getAdjs() {
        return this.adjs;
    }
}
