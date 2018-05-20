package mrn.ui.controlbar;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import mrn.data.Model;
import mrn.ui.base.View;

public class ViewControlBar extends View<Model> {
    public Button btnStart;
    public Button btnStep;
    public ToggleButton btnSelectStartNode;

    private HBox layout;

    final double BTN_WIDTH = 75;

    public ViewControlBar(Model model) {
        this.setModel(model);
        this.ctrl = new ControllerControlBar(model, this);
    }

    @Override
    protected void init() {
        layout = new HBox();

        btnSelectStartNode = new ToggleButton("Select Start Node");
        btnStart = new Button("Start");
        btnStep = new Button("Step");
        btnStart.setMinWidth(BTN_WIDTH);
        btnStep.setMinWidth(BTN_WIDTH);

        layout.getChildren().addAll(btnSelectStartNode, btnStart, btnStep);
        layout.setSpacing(10);
    }

    public HBox getRoot() {
        return  this.layout;
    }
}
