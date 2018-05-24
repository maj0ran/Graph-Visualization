package mrn.ui.graphvisual;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mrn.data.GraphLink;

public class FxLink {
        GraphLink real;

        public FxNode destNode;
        public FxNode srcNode;

        public double srcX;
        public double srcY;

        public double destX;
        public double destY;

        public Color color;

        // TODO: Should make a Factory at FxGraph for both Nodes and Links
        public FxLink(FxGraph parent, GraphLink realLink) {
            real = realLink;
            FxNode dest = parent.getFromReal(realLink.getDest());
            this.destNode = dest;
            this.color = Color.color(0, 0, 0);
        }

        public void draw(GraphicsContext gc) {
            srcX = this.srcNode.x;
            srcY = this.srcNode.y;
            destX = this.destNode.x;
            destY = this.destNode.y;

            double dx = srcX - destX;
            double dy = srcY - destY;
            double a = Math.atan2(dy, dx);

            srcX = srcX - Math.cos(a) * srcNode.size  / 2;
            srcY = srcY - Math.sin(a) * srcNode.size  / 2;

            destX = destX + Math.cos(a) * destNode.size / 2;
            destY = destY + Math.sin(a) * destNode.size / 2;

            gc.setStroke(color);
            gc.setFill(color);
            gc.strokeLine(srcX, srcY, destX, destY);


            double arrowHead_x[] = new double[] {
                    destX,
                    destX + Math.cos(a + Math.toRadians(30)) * 10,
                    destX + Math.cos(a - Math.toRadians(30)) * 10
            };

            double arrowHead_y[] = new double[] {
                    destY,
                    destY + Math.sin(a + Math.toRadians(30)) * 10,
                    destY + Math.sin(a - Math.toRadians(30)) * 10

            };

            gc.fillPolygon(arrowHead_x, arrowHead_y, 3);

        }
    }
