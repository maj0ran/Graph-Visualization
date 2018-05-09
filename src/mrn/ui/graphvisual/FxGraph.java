package mrn.ui.graphvisual;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import mrn.data.Graph;
import mrn.data.GraphLink;
import mrn.data.GraphNode;

import java.util.ArrayList;

public class FxGraph<T> {

    public Graph<T> realGraph;
    public ArrayList<FxNode<T>> fxnodes = new ArrayList<>();

    public FxGraph(Graph<T> realGraph) {
        this.realGraph = realGraph;

        for (GraphNode<T> n : realGraph.getNodes()) {
            this.fxnodes.add(new FxNode<>(n, 0, 0, 0));
        }

        for (GraphNode n : realGraph.getNodes()) {
            //    for(GraphLink l : n.getLinks()) {

            //    }
        }
    }

    public FxNode<T> getFromReal(GraphNode real) {
        for (FxNode<T> fxn : fxnodes) {
            if (fxn.real == real) {
                return fxn;
            }
        }
        return null;
    }

    public void draw() {
        for (FxNode fxn : this.fxnodes) {
          //  fxn.draw(gc);
        }
    }

}

