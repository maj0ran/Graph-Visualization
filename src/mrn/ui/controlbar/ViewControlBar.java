package mrn.ui.controlbar;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import mrn.data.Graph;
import mrn.data.Model;
import mrn.ui.base.View;

public class ViewControlBar extends View<Model> {
    public Button start;
    public Button step;

    private HBox layout;

    final double BTN_WIDTH = 75;

    public ViewControlBar(Model model) {
        this.setModel(model);
        this.ctrl = new ControllerControlBar(model, this);
    }

    @Override
    protected void init() {
        layout = new HBox();

        start = new Button("Start");
        step = new Button("Step");
        start.setMinWidth(BTN_WIDTH);
        step.setMinWidth(BTN_WIDTH);

        layout.getChildren().addAll(start, step);
        layout.setSpacing(10);
    }

    public HBox getRoot() {
        return  this.layout;
    }
}
