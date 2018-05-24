package mrn.data;

import javafx.util.Pair;

import java.util.*;

import static mrn.data.NodeColor.BLACK;
import static mrn.data.NodeColor.GRAY;
import static mrn.data.NodeColor.WHITE;

enum LinkStatus { Success, AlreadyLinked, FirstNotFound, LastNotFound, BothNotFound }

public final class Graph<T>  {

    public LinkedHashMap<GraphNode<T>, GraphNode<T>> dfsLog;


    private ArrayList<GraphNode<T>> nodes = new ArrayList<>();
    private boolean weighted = false;
    private boolean directed = false;
    private boolean multigraph = false;


    public boolean isWeighted() {
        return weighted;
    }

    public boolean isDirected() {
        return directed;
    }

    public boolean isMultigraph() {
        return multigraph;
    }

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

    public GraphNode<T> add(T e) {
        if(searchNode(e) == null) {
            GraphNode<T> n = new GraphNode<>();
            n.setElement(e);
            this.nodes.add(n);
            return n;
        }
        return null;
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
            if(n1.getLink(n2) != null) {
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

    /**
     * ---WIP---
     * Depth-First-Search on the graph which returns the spanning tree from the selected node
     * @param src The starting node where the algorithm begins
     */
    public Graph<T> depthFirstSearch(GraphNode<T> src) {
        if(!this.nodes.contains(src)) {
            return null;
        }

        dfsLog = new LinkedHashMap<>();

        //init
        Map<GraphNode<T>, NodeColor> color = new HashMap<>();
        Map<GraphNode<T>, GraphNode<T>> parent = new HashMap<>();

        for(GraphNode<T> n : this.nodes) {
            color.put(n, WHITE);
        }
        Stack<GraphNode<T>> stack = new Stack<>();
        GraphNode<T> curr;

        // Actual DFS
        stack.push(src);
        color.put(src, GRAY);

        while(!stack.isEmpty()) {
            curr = stack.pop();

            if(parent.get(curr) == null) {
                dfsLog.put(curr, null);
            }
            else {
                dfsLog.put(curr, parent.get(curr));
            }
            color.put(curr, BLACK);

            for(GraphLink l : curr.getLinks()) {
                if(color.get(l.getDest()) == WHITE) {
                    GraphNode<T> next = l.getDest();
                    parent.put(next, curr);
                    color.put(next, GRAY);
                    stack.push(next);
                }
            }
        }

        // Now Construct the new Tree from the parent-table
        Graph<T> spanningTree = new Graph<>(this.weighted, this.directed, this.multigraph);
        LinkedHashMap<GraphNode<T>, GraphNode<T>> spanningTreeNodes = new LinkedHashMap<>();


        LinkedHashMap<GraphNode<T>, GraphNode<T>> nodeRef = new LinkedHashMap<>();         // n -> n'
        for(GraphNode<T> originalChild : dfsLog.keySet()) {
            GraphNode<T> spanTreeChild = new GraphNode<>(originalChild.getElement());
            nodeRef.put(originalChild, spanTreeChild);

            GraphNode<T> originalParent = dfsLog.get(originalChild);         // p -> p'
            GraphNode<T> spanTreeParent = null;
            if(originalParent != null) {
                    spanTreeParent = nodeRef.get(originalParent);
            }

            spanningTreeNodes.put(spanTreeChild, spanTreeParent);
        }

        for(GraphNode<T> n : spanningTreeNodes.keySet()) {
            spanningTree.getNodes().add(n);
            if(spanningTreeNodes.get(n) != null) {
                 spanningTree.link(spanningTreeNodes.get(n), n);
            }
        }

        for(GraphNode<T> p : spanningTreeNodes.values()) {

        }

        return spanningTree;
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

