package mrn.data;

import java.util.ArrayList;
import java.util.Vector;

enum LinkStatus { Success, AlreadyLinked, FirstNotFound, LastNotFound, BothNotFound }

public final class Graph<T>  {

    private ArrayList<GraphNode<T>> nodes = new ArrayList<>();
    private boolean weighted = false;
    private boolean directed = false;
    private boolean multigraph = false;

    public Graph(boolean isWeighted, boolean isDirected, boolean isMultigraph) {
        this.weighted = isWeighted;
        this.directed = isDirected;
        this.multigraph = isMultigraph;
    }

    public Graph(Graph<T> copy) {
        this.weighted = copy.weighted;
        this.directed = copy.directed;
        this.nodes = copy.nodes;
    }

    public ArrayList<GraphNode<T>> getNodes() {
        return this.nodes;
    }

    public int getNumberNodes() {
        return nodes.size();
    }

    public int getNumberEdges() {
        int edgesCount = 0;
        for(GraphNode<T> n : this.nodes) {
            edgesCount += n.getLinks().size();
        }
        return edgesCount;
    }

    public boolean add(T e) {
        if(searchNode(e) == null) {
            GraphNode<T> n = new GraphNode<>();
            n.setElement(e);
            this.nodes.add(n);
            return true;
        }
        return false;
    }

    public boolean change(T oldVal, T newVal) {
        GraphNode<T> n = searchNode(oldVal);
        if(n != null) {
            n.setElement(newVal);
            return true;
        }
        return false;
    }

    private GraphNode<T> searchNode(T e) {
        for (GraphNode<T> n : this.nodes) {
            if (n.getElement() == e) {
                return n;
            }
        }
        return null;
    }

    private boolean remove(GraphNode<T> n) {
        return this.nodes.remove(n);
    }

    public boolean remove(T e) {
        GraphNode<T> n = searchNode(e);
        if(n != null) {
            this.nodes.remove(n);
            return true;
        }
        return false;
    }

    public LinkStatus link(final GraphNode<T> n1, final GraphNode<T> n2) {

        if (!nodes.contains(n1) && !nodes.contains(n2)) { return LinkStatus.BothNotFound; }
        if (!nodes.contains(n1)) { return LinkStatus.FirstNotFound; }
        if (!nodes.contains(n2)) { return LinkStatus.LastNotFound; }

        if(!multigraph) {
            if(n1.hasLink(n2)) {
                return LinkStatus.AlreadyLinked;
            }
        }
        GraphLink link = new GraphLink(n2, 0);
        n1.getLinks().add(link);

        if (!directed) { // if graph is undirected, set a link n2->n1 with the same link-ID
            GraphLink reverseLink = link.copy();
            reverseLink.setDest(n1);
            n2.getLinks().add(reverseLink);
        }
        return LinkStatus.Success;
    }

    public ArrayList<GraphLink> getNeighbors(T e) {
        GraphNode<T> n = searchNode(e);
        if (n != null) {
            return n.getLinks();
        }
        return null;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (GraphNode<T> n : this.nodes) {
            sb.append(n.getElement()).append(" -> {");
            for(GraphLink link : n.getLinks()) {
                sb.append(link.getDest().getElement()).append(",");
            }
            sb.append("}\n");
        }
        return sb.toString();
        }
    }

