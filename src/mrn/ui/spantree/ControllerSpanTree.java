package mrn.ui.spantree;

import mrn.data.Graph;
import mrn.data.Model;
import mrn.ui.base.Controller;
import mrn.ui.graphvisual.FxGraph;
import mrn.ui.graphvisual.FxNode;

import java.beans.PropertyChangeEvent;

public class ControllerSpanTree extends Controller<Model, ViewSpanTree> {
    public ControllerSpanTree(Model m, ViewSpanTree v) {
        super(m,v);
    }

    @Override
    protected void init(Model model, ViewSpanTree view) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("spantree")) {
            Graph<String> spanTree = (Graph<String>)evt.getNewValue();
            view.fxSpanTree = new FxGraph<String>(spanTree);
            System.out.println(spanTree.toString());

            view.projectGraph();

        }
    }
}
