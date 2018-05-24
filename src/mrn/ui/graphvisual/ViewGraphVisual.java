package mrn.ui.graphvisual;

import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import mrn.data.Graph;
import mrn.data.GraphLink;
import mrn.data.GraphNode;
import mrn.data.Model;
import mrn.ui.base.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ViewGraphVisual extends View<Model> {



    private FxGraph<String> fxGraph;
    public Canvas canvas;
    public GraphicsContext gc;

    public ViewGraphVisual(Model model) {
        this.model = model;
        this.ctrl = new ControllerGraphVisual(model, this);
    }

    @Override
    protected void init() {
        this.canvas = new Canvas(600, 600);
        gc = canvas.getGraphicsContext2D();

    }

    public void initGraph(Graph<String> g) {
        fxGraph = new FxGraph<>(g);
    }


    private void projectGraph() {

        final int nodeCount = model.getGraph().getNumberNodes();

        final double areaWidth = canvas.getWidth();

        final double areaHeight = canvas.getHeight();

        final double nodeGap = 2*Math.PI / nodeCount;

        final Point2D c = new Point2D(areaWidth / 2, areaHeight / 2);

        final double u = Math.PI * Math.min(areaWidth, areaHeight);
        double r = (Math.min(areaWidth, areaHeight) / 2) - 50;

        final double nodeSize = 50;

        /* Nodes */
        for (int i = 0; i < nodeCount; i++) {
            fxGraph.fxnodes.get(i).x = c.getX() + Math.cos(nodeGap * i) * r;
            fxGraph.fxnodes.get(i).y = c.getY() + Math.sin(nodeGap * i) * r;
            fxGraph.fxnodes.get(i).size = nodeSize;
        }
    }

    @Override
    public Canvas getRoot() {
        return canvas;
    }

    public void clear() {
        gc.clearRect(0, 0, canvas.getWidth() - 1, canvas.getHeight() - 1);
    }

    public void drawGraph() {

        clear();

        projectGraph();
        for(var n : fxGraph.fxnodes) {
            n.draw(gc);
            for(FxLink l : n.links) {
                l.draw(gc);
            }
        }
    }

    public FxGraph<String> getFxGraph() {
        return fxGraph;
    }
}
