package mrn.ui.menubar;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import mrn.data.Graph;
import mrn.data.GraphLink;
import mrn.data.GraphNode;
import mrn.data.Model;
import mrn.ui.base.Controller;

import javax.xml.parsers.SAXParserFactory;
import java.beans.PropertyChangeEvent;
import java.io.FileWriter;
import java.io.IOException;

public class ControllerMenubar extends Controller<Model, ViewMenubar> {

    public ControllerMenubar(Model model, ViewMenubar view) {
        super(model, view);
    }

    @Override
    public void init(Model model, ViewMenubar view) {

        view.load.setOnAction(this::pressLoad);
        view.save.setOnAction(this::pressSave);
    }

    public void pressLoad(Event e) {
        this.model.loadGraph("default.xml");
        change.firePropertyChange("Graph", null, null);
        GraphNode<String> n = model.getGraph().getNodes().get(0);
        Graph<String> dfs = model.getGraph().depthFirstSearch(n);
    }

    public void pressSave(Event e) {
        Graph<?> graph = this.model.getGraph();

        FileWriter fw = null;
        try {
        fw = new FileWriter("save.xml");
        String s = "<graph weighted=\"" +
                String.valueOf(graph.isWeighted()) + "\" " +
                "directed=\"" + String.valueOf(graph.isDirected()) + "\" " +
                "mutligraph=\"" + String.valueOf(graph.isMultigraph() + "\" " +
                "type=\"String\" size=\"" + graph.getNumberNodes()) + "\">";
            fw.write(s);
            fw.append(System.getProperty("line.separator"));
            for(int i = 0; i < graph.getNumberNodes(); i++) {
                GraphNode n = graph.getNodes().get(i);

                fw.append("\t<node id=\"" + i + "\">");
                fw.append(System.getProperty("line.separator"));
                fw.append("\t\t<element>");
                fw.append(System.getProperty("line.separator"));
                fw.append("\t\t\t" + n.getElement().toString());
                fw.append(System.getProperty("line.separator"));
                fw.append("\t\t</element>");
                fw.append(System.getProperty("line.separator"));
                fw.append("\t</node>");
                fw.append(System.getProperty("line.separator"));
            }
            fw.append(System.getProperty("line.separator"));

            for(int i = 0; i < graph.getNumberNodes(); i++) {
                GraphNode<?> n = graph.getNodes().get(i);
                for(GraphLink l : n.getLinks()) {
                    fw.append("<link src=\"" + i);
                    GraphNode dest = l.getDest();
                    int j = 0;
                    for(j = 0; j < graph.getNumberNodes(); j++) {
                        if(graph.getNodes().get(j) == dest) {
                            break;
                        }
                    }
                    fw.append("\" dest=\"" + j + "\" w=\"0\"></link>");
                    fw.append(System.getProperty("line.separator"));
                }
            }

            fw.append("</graph>");
                // <link src="0" dest="3" w="0"></link>



            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }




    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
