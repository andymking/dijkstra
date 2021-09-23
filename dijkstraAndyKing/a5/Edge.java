package a5;

public interface Edge {
    String getSource();
    String getDest();
    double getWeight();
    void setSource(String source);
    void setDest(String dest);
    void setWeight(double weight);
}
