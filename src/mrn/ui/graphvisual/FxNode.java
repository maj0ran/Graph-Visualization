package mrn.ui.graphvisual;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import mrn.data.GraphNode;

public class FxNode<T> {

    GraphNode<T> real;
    public double x;
    public double y;
    public double size;
    public FxNode(GraphNode<T> realNode, double x, double y, double size) {
        this.real = realNode;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.setFont(new Font(24));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);

        gc.strokeOval(x - size / 2, y - size / 2, size, size);
        gc.fillText(real.getElement().toString(), x, y );
    }
}
