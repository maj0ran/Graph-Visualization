package mrn.ui.graphvisual;

import mrn.data.Model;
import mrn.ui.base.Controller;

import java.beans.PropertyChangeEvent;

public class ControllerGraphVisual extends Controller<Model, ViewGraphVisual> {

    public ControllerGraphVisual(Model m, ViewGraphVisual v) {
        super(m, v);
    }

    @Override
    protected void init(Model model, ViewGraphVisual view) {
        view.getRoot().setOnMouseClicked(e -> {
            System.out.println(e.getX() + " " + e.getY());

        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("Graph")) {
          view.drawGraph();
        }
    }
}
