package mrn.ui.graphvisual;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import mrn.data.GraphLink;
import mrn.data.GraphNode;

import java.util.ArrayList;

public class FxNode<T> {

    public GraphNode<T> real;

    public ArrayList<FxLink> links = new ArrayList<>();
    public double x;
    public double y;
    public double size;
    public Color borderColor;


    public FxNode(GraphNode<T> realNode, double x, double y, double size) {

        this.real = realNode;
        this.x = x;
        this.y = y;
        this.size = size;
        this.borderColor = Color.color(0, 0, 0);
    }

    public void addLink(FxLink l) {
        l.srcNode = this;
        this.links.add(l);
    }

    public void draw(GraphicsContext gc) {
        gc.setStroke(borderColor);
        gc.setFill(Color.color(0,0,0));
        gc.setLineWidth(2);
        gc.setFont(new Font(24));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.strokeOval(x - size / 2, y - size / 2, size, size);
        gc.fillText(real.getElement().toString(), x, y );
    }

    public FxLink getLinkFromReal(GraphLink real) {
        for(FxLink l : links) {
            if(l.real == real) {
                return l;
            }
        }
        return null;
    }

}
