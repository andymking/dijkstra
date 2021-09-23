package a5;

import java.util.HashMap;
import java.util.Map;

public interface Node {

     /**
      * @return the name of the node
      */
     String getName();
     void putEdge(Edge edge);
     void removeEdge(String dest);
     boolean hasEdge(String dest);
     boolean isAdjacent(String dest);
     int getInDegree();
     void subInDegree();
     void addInDegree();
     int numEdges();
     double getTotalDistTo();
     void addAdj(String name, Node node);
     void removeAdj(String name, Node node);
     boolean isKnown();
     void setKnown(boolean bool);
     Map<String, Node> getAdjs();
     void setTotalDistTo(double dist);
     void sumTotalDistTo(double dist);
     Edge getEdge(String dest);
     void setRecentNode(String recent);
     String getRecentNode();
}
