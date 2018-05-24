package mrn.ui.controlbar;

import javafx.scene.input.MouseEvent;
import mrn.data.GraphNode;
import mrn.data.Model;
import mrn.ui.base.Controller;
import mrn.ui.graphvisual.FxNode;

import java.beans.PropertyChangeEvent;

public class ControllerControlBar extends Controller<Model, ViewControlBar> {

    public ControllerControlBar(Model m, ViewControlBar v) {
        super(m, v);
    }

    private GraphNode startNode;
    @Override
    public void init(Model model, ViewControlBar view) {
        this.view.btnRun.addEventHandler(MouseEvent.MOUSE_CLICKED, this::startButton);
        this.view.btnStep.addEventHandler(MouseEvent.MOUSE_CLICKED, this::stepButton);
        this.view.btnReset.addEventHandler(MouseEvent.MOUSE_CLICKED, this::resetButton);
    }

    public void startButton(MouseEvent e) {
        if(startNode != null) {
            change.firePropertyChange("dfs", null, startNode);
        }
    }

    public void stepButton(MouseEvent e) {
        if(startNode != null) {
            change.firePropertyChange("step", null, startNode);
        }
    }

    public void resetButton(MouseEvent e) {
        change.firePropertyChange("reset", null, null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("select")) {
            FxNode n = (FxNode)evt.getNewValue();
            startNode = n.real;
        }
    }


}
