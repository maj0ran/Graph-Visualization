package mrn.ui.graphvisual;

import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import mrn.data.Graph;
import mrn.data.GraphNode;
import mrn.data.Model;
import mrn.ui.base.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ViewGraphVisual extends View<Model> {

    private class FxNode {
        GraphNode node;
        double x;
        double y;
        double size;
        FxNode(GraphNode n, double x, double y, double size) {
            this.node = n; this.x = x; this.y = y; this.size = size;
        }

        private void draw(GraphicsContext gc) {
            gc.setFill(Color.BLACK);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(2);
            gc.setFont(new Font(24));
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setTextBaseline(VPos.CENTER);

            gc.strokeOval(x - size / 2, y - size / 2, size, size);
            gc.fillText(node.getElement().toString(), x, y );
        }
    }

    List<FxNode> fxnodes = new LinkedList<>();
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
        double r = Math.min(areaWidth, areaHeight) / 2;

        double nodeSize = Math.sqrt(
                            Math.pow(Math.sin(nodeGap),2) +
                            (Math.pow(1 - Math.cos(nodeGap),2))
                          ) * r / 1;
      //  double nodeSize = (1.0 / nodeCount) * 2 * r * Math.PI / 2;
         r -= nodeSize / 4;
         nodeSize /= 2;

        // DEBUG
        gc.strokeOval(c.getX() - r, c.getY() - r, r*2, r*2);
        gc.strokeRect(1, 1, canvas.getWidth() - 1, canvas.getHeight() - 1);

        var nodes = graph.getNodes();

        for (int i = 0; i < nodeCount; i++) {
            FxNode n = new FxNode(nodes.get(i),
                    c.getX() + Math.cos(nodeGap * i) * r, c.getY() + Math.sin(nodeGap * i) * r, nodeSize
                    );
            fxnodes.add(n);
        }
    }

    @Override
    public Canvas getRoot() {
        return canvas;
    }

    public void update() {
        projectGraph();
        for(FxNode n : this.fxnodes) {
            n.draw(gc);
        }
    }
}
