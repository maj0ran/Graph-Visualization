package mrn.ui.controlbar;

import javafx.event.EventType;
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
        this.view.start.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            System.out.println("start");
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
