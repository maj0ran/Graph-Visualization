package mrn.ui.spantree;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import mrn.data.Model;
import mrn.ui.base.View;
import mrn.ui.graphvisual.FxGraph;
import mrn.ui.graphvisual.FxLink;
import mrn.ui.graphvisual.FxNode;

import java.util.HashMap;
import java.util.Stack;

public class ViewSpanTree extends View<Model> {
    public FxGraph<String> fxSpanTree;
    public Canvas canvas;
    public GraphicsContext gc;

    private int currNodeId = 0;
    HashMap<FxNode, Boolean> isDrawn = new HashMap<>();
    Stack<FxNode> stack = new Stack<>();
    public ViewSpanTree(Model m) {
        this.setModel(m);
        this.setController(new ControllerSpanTree(m, this));
    }

    @Override
    protected void init() {
        this.canvas = new Canvas(600, 600);
        this.gc = canvas.getGraphicsContext2D();
    }

    @Override
    public Canvas getRoot() {
        return canvas;
    }


    public void projectGraph() {

        final int nodeCount = fxSpanTree.fxnodes.size();

        final double areaWidth = canvas.getWidth();

        final double areaHeight = canvas.getHeight();

        final double nodeGap = 2*Math.PI / nodeCount;

        final Point2D c = new Point2D(areaWidth / 2, areaHeight / 2);

        final double u = Math.PI * Math.min(areaWidth, areaHeight);
        double r = (Math.min(areaWidth, areaHeight) / 2) - 50;

        final double nodeSize = 50;

        /* Nodes */
        for (int i = 0; i < nodeCount; i++) {
            fxSpanTree.fxnodes.get(i).x = c.getX() + Math.cos(nodeGap * i) * r;
            fxSpanTree.fxnodes.get(i).y = c.getY() + Math.sin(nodeGap * i) * r;
            fxSpanTree.fxnodes.get(i).size = nodeSize;
        }

        draw();
    }

    public void clear() {
        gc.clearRect(0, 0, canvas.getWidth() - 1, canvas.getHeight() - 1);
    }

    public void draw() {
        clear();

        for(var n : fxSpanTree.fxnodes) {
            n.draw(gc);
            for(FxLink l : n.links) {
                l.draw(gc);
            }
        }
    }
}
