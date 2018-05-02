package mrn.data;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Model {

    private int value = 0;
    private Graph graph;
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

    public Graph getGraph() {
        return this.graph;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void incValue() {
        this.value++;
    }

    public void decValue() {
        this.value--;
    }
}
