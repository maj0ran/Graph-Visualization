package mrn.data;

import java.util.ArrayList;

public class GraphNode<T> {

        private T element;

        private ArrayList<GraphLink> links = new ArrayList<>();

        public GraphNode() {}

        public void setElement(T e) {
            this.element = e;
        }

        public T getElement() {
            return element;
        }

        public ArrayList<GraphLink> getLinks() {
            return this.links;
        }

        public boolean hasLink(GraphNode dest) {
            for(GraphLink l : links) {
                if(l.getDest() == dest) {
                    return true;
                }
            }
            return false;
        }

    }

