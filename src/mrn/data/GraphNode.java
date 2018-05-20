package mrn.data;

import java.util.ArrayList;

public class GraphNode<T> {

        private T element;

        private ArrayList<GraphLink> links = new ArrayList<>();

        public GraphNode() {}

        public GraphNode(T elem) {
            this.element = elem;
        }

        public GraphNode(GraphNode<T> copy) {
            this.element = copy.element;
            this.links = new ArrayList<>();
            for(GraphLink l : copy.getLinks()) {
             //   this.links.add()
            }
        }

        public void setElement(T e) {
            this.element = e;
        }

        public T getElement() {
            return element;
        }

        public ArrayList<GraphLink> getLinks() {
            return this.links;
        }

        public GraphLink getLink(GraphNode dest) {
            for(GraphLink l : links) {
                if(l.getDest() == dest) {
                    return l;
                }
            }
            return null;
        }

    }

