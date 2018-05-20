package mrn.ui.graphvisual;

import javafx.scene.input.MouseEvent;
import mrn.data.Model;
import mrn.ui.base.Controller;

import java.beans.PropertyChangeEvent;

public class ControllerGraphVisual extends Controller<Model, ViewGraphVisual> {

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
            if(x > )
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("Graph")) {
          view.drawGraph();
        }
    }
}
