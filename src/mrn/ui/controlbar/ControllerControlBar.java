package mrn.ui.controlbar;

import javafx.scene.input.MouseEvent;
import mrn.data.Model;
import mrn.ui.base.Controller;

import java.beans.PropertyChangeEvent;

public class ControllerControlBar extends Controller<Model, ViewControlBar> {

    public ControllerControlBar(Model m, ViewControlBar v) {
        super(m, v);
    }

    @Override
    public void init(Model model, ViewControlBar view) {
        this.view.btnStart.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            model.getGraph().depthFirstSearch(null);
        });

        this.view.btnSelectStartNode.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            change.firePropertyChange("start_select", !this.view.btnSelectStartNode.isSelected(), this.view.btnSelectStartNode.isSelected());
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
