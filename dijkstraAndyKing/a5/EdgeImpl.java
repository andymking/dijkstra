package a5;

public class EdgeImpl implements Edge {

    private String source;
    private String dest;
    private double weight;

    public EdgeImpl(String source, String dest, double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    public String getSource() {
        return this.source;
    }

    public String getDest() {
        return this.dest;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
