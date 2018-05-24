package mrn.ui.controlbar;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import mrn.data.Model;
import mrn.ui.base.View;

public class ViewControlBar extends View<Model> {
    public Button btnRun;
    public Button btnStep;
    public Button btnReset;

    private HBox layout;

    final double BTN_WIDTH = 75;

    public ViewControlBar(Model model) {
        this.setModel(model);
        this.ctrl = new ControllerControlBar(model, this);
    }

    @Override
    protected void init() {
        layout = new HBox();

        btnRun = new Button("Run");
        btnStep = new Button("Step");
        btnReset = new Button("Reset");
        btnRun.setMinWidth(BTN_WIDTH);
        btnStep.setMinWidth(BTN_WIDTH);
        btnReset.setMinWidth(BTN_WIDTH);

        layout.getChildren().addAll(btnRun, btnStep, btnReset);
        layout.setSpacing(10);
    }

    public HBox getRoot() {
        return  this.layout;
    }
}
