package mrn.ui.graphvisual;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import mrn.data.Graph;
import mrn.data.GraphLink;
import mrn.data.GraphNode;
import mrn.data.Model;
import mrn.ui.base.Controller;

import java.beans.PropertyChangeEvent;
import java.util.LinkedHashMap;

public class ControllerGraphVisual extends Controller<Model, ViewGraphVisual> {

    LinkedHashMap<GraphNode<String>, GraphNode<String>> dfsLog;
    Graph<String> spanTree = null;
    int stepCount = 0;

    public ControllerGraphVisual(Model m, ViewGraphVisual v) {
        super(m, v);
    }

    @Override
    protected void init(Model model, ViewGraphVisual view) {
        view.getRoot().setOnMouseClicked(this::selectNode);

    }

    private void selectNode(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        for( FxNode<String>  node: view.getFxGraph().fxnodes) {
            if(x > node.x - node.size / 2 && x < node.x + node.size / 2 && y > node.y - node.size / 2 && y < node.y + node.size / 2) {
                node.borderColor = Color.color(1, 0, 0);
                System.out.println(node.real.getElement());
                change.firePropertyChange("select", null, node);
            } else {
                node.borderColor = Color.color(0, 0, 0);
            }
            view.drawGraph();
//            view.gc.clearRect(0, 0, view.canvas.getWidth() - 1, view.canvas.getHeight() - 1);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("Graph")) {
            view.initGraph(model.getGraph());
            view.drawGraph();
        }

        if(evt.getPropertyName().equals("reset")) {
            view.initGraph(model.getGraph());
            view.drawGraph();
            stepCount = 0;
        }

        if(evt.getPropertyName().equals("dfs")) {
            // reset colors
            for(FxNode<?> n : view.getFxGraph().fxnodes) {
                for(FxLink l : n.links) {
                    l.color = Color.BLACK;
                }
                n.borderColor = Color.BLACK;
            }


            Graph<String> spanTree = model.getGraph().depthFirstSearch((GraphNode)evt.getNewValue());
             dfsLog = model.getGraph().dfsLog;

            for(GraphNode n : dfsLog.keySet()) {
                    FxNode fxn = view.getFxGraph().getFromReal(n);
                    fxn.borderColor = Color.color(0,1,0);;
                    if(dfsLog.get(n) != null) {
                        GraphLink l = dfsLog.get(n).getLink(n);
                        FxLink fxl = view.getFxGraph().getFromReal(dfsLog.get(n)).getLinkFromReal(l);
                        fxl.color = Color.color(0,1,0);;
                    }
            }

            view.drawGraph();
            change.firePropertyChange("spantree", null, spanTree); // to ControllerSpanTree
        }

        if(evt.getPropertyName().equals("step")) {

            if(stepCount == 0) {
                spanTree = model.getGraph().depthFirstSearch((GraphNode)evt.getNewValue());
                this.dfsLog = model.getGraph().dfsLog;
            }

            if(stepCount < this.dfsLog.keySet().size()) {
                GraphNode<String> n = (GraphNode) dfsLog.keySet().toArray()[stepCount];
                FxNode<?> fxn = view.getFxGraph().getFromReal(n);
                fxn.borderColor = Color.color(0,1,0);
                if(dfsLog.get(n) != null) {
                    GraphLink l = dfsLog.get(n).getLink(n);
                    FxLink fxl = view.getFxGraph().getFromReal(dfsLog.get(n)).getLinkFromReal(l);
                    fxl.color = Color.color(0,1,0);;
                }
                view.drawGraph();
                stepCount++;

            }
            else {
                System.out.println("Finished");
                change.firePropertyChange("spantree", null, spanTree);
            }
        }
    }
}
