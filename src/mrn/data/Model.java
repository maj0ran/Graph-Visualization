package mrn.data;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Model {

    private Graph<String> graph;
    private GraphBuilder graphFactory;

    public Model() {
        this.graphFactory = new GraphBuilder();
    }

    public void loadGraph(String path) {
        try {
            graph = graphFactory.load(path);
        }
        catch (SAXException e) {
            System.out.println("bla");
        }
        catch(IOException e) {
            System.out.println("bla2");
        }
        catch(ParserConfigurationException e) {
            System.out.println("bla3");
        }
    }

    public Graph<String> getGraph() {
        return this.graph;
    }
}
