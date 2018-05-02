package mrn.ui.menubar;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import mrn.data.Model;
import mrn.ui.base.Controller;

import java.beans.PropertyChangeEvent;

public class ControllerMenubar extends Controller<Model, ViewMenubar> {

    public ControllerMenubar(Model model, ViewMenubar view) {
        super(model, view);
    }

    @Override
    public void init(Model model, ViewMenubar view) {

        view.load.setOnAction(this::pressLoad);
    }

    public void pressLoad(Event e) {
        this.model.loadGraph("D:\\Code\\Java\\Graph\\default.xml");
        change.firePropertyChange("Graph", null, null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
