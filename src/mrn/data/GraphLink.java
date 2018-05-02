package mrn.data;

public class GraphLink {

    static int id_counter = 0;

    private int id;
    private GraphNode dest;
    private double weight;

    public GraphLink(GraphNode dest, double weight) {
        this.dest = dest;
        this.weight = weight;
        this.id = id_counter++;
    }

    public GraphLink copy() {
        GraphLink copyLink = new GraphLink(this.dest, this.weight);
        copyLink.id = this.id;
        return copyLink;
    }

    public void setDest(GraphNode newDest) {
        this.dest = newDest;
    }

    public GraphNode getDest() {
        return this.dest;
    }

}
