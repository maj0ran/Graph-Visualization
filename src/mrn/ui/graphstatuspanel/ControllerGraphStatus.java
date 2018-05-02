package mrn.ui.graphstatuspanel;

import mrn.data.Graph;
import mrn.data.Model;
import mrn.ui.base.Controller;

import java.beans.PropertyChangeEvent;

public class ControllerGraphStatus extends Controller<Model, ViewGraphStatus> {

    ControllerGraphStatus(Model model, ViewGraphStatus view) {
        super(model, view);
    }
    @Override
    public void init(Model model, ViewGraphStatus view) {
        this.view.nodesCountNum.setText("0");
        this.view.edgesCountNum.setText("0");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("Graph")) {
            view.update();
        }
    }
}
