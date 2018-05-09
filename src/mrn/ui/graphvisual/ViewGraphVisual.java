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
    private Canvas canvas;
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


    private void projectGraph() {
        Graph<String> graph = model.getGraph();

        final int nodeCount = graph.getNumberNodes();

        final double areaWidth = canvas.getWidth();

        final double areaHeight = canvas.getHeight();

        final double nodeGap = 2*Math.PI / nodeCount;

        final Point2D c = new Point2D(areaWidth / 2, areaHeight / 2);

        final double u = Math.PI * Math.min(areaWidth, areaHeight);
        double r = (Math.min(areaWidth, areaHeight) / 2) - 50;

        final double nodeSize = 50;

        // DEBUG
        gc.strokeRect(1, 1, canvas.getWidth() - 1, canvas.getHeight() - 1);

        fxGraph = new FxGraph<>(graph);

        /* Nodes */
        for (int i = 0; i < nodeCount; i++) {
            fxGraph.fxnodes.get(i).x = c.getX() + Math.cos(nodeGap * i) * r;
            fxGraph.fxnodes.get(i).y = c.getY() + Math.sin(nodeGap * i) * r;
            fxGraph.fxnodes.get(i).size = nodeSize;
        }

        /* Edges */
        for(FxNode<String> n : fxGraph.fxnodes) {
            for(GraphLink l : n.real.getLinks()) {

                FxNode dest = fxGraph.getFromReal(l.getDest());
                double dx = n.x - dest.x;
                double dy = n.y - dest.y;
                double a = Math.atan2(dy, dx);

                double src_x = n.x - Math.cos(a) * nodeSize  / 2;
                double src_y = n.y - Math.sin(a) * nodeSize  / 2;
                double dst_x = dest.x + Math.cos(a) * nodeSize / 2;
                double dst_y = dest.y + Math.sin(a) * nodeSize / 2;
                gc.strokeLine(src_x, src_y, dst_x, dst_y);
                double arrowHead_x[] = new double[] {
                        dst_x,
                        dst_x + Math.cos(a + Math.toRadians(30)) * 10,
                        dst_x + Math.cos(a - Math.toRadians(30)) * 10
                };

                double arrowHead_y[] = new double[] {
                        dst_y,
                        dst_y + Math.sin(a + Math.toRadians(30)) * 10,
                        dst_y + Math.sin(a - Math.toRadians(30)) * 10

                };
                gc.fillPolygon(arrowHead_x, arrowHead_y, 3);

            }
        }
    }

    @Override
    public Canvas getRoot() {
        return canvas;
    }

    public void drawGraph() {
        projectGraph();
        for(var n : fxGraph.fxnodes) {
            n.draw(gc);
        }
    }

    public void selectNode() {

    }
}
