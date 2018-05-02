package mrn.ui.graphstatuspanel;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import mrn.data.Model;
import mrn.ui.base.View;

public class ViewGraphStatus extends View<Model> {

    private GridPane layout;
    public Label nodesCount;
    public Label edgesCount;
    public Label nodesCountNum;
    public Label edgesCountNum;


    public ViewGraphStatus(Model model) {
        this.model = model;
        this.ctrl = new ControllerGraphStatus(model, this);
    }

    @Override
    protected void init() {
        nodesCount = new Label("No. of Nodes: ");
        edgesCount = new Label("No. of Edges: ");
        nodesCountNum = new Label("");
        edgesCountNum = new Label("");

        this.layout = new GridPane();
        this.layout.add(nodesCount, 0, 0);
        this.layout.add(nodesCountNum, 1, 0);
        this.layout.add(edgesCount, 0, 1);
        this.layout.add(edgesCountNum, 1, 1);
    }

    @Override
    public GridPane getRoot() {
        return layout;
    }

    public void update() {
        this.nodesCountNum.setText(String.valueOf(model.getGraph().getNumberNodes()));
        this.edgesCountNum.setText(String.valueOf(model.getGraph().getNumberEdges()));
    }
}
