package mrn.data;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class GraphBuilder {


    public GraphBuilder() {

    }

    public <T> Graph<T>newGraph(boolean weighted, boolean directed, boolean multigraph) {
        return new Graph<>(weighted, directed, multigraph);
    }

    public Graph<String> load(String path) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        GraphXmlHandler handler = new GraphXmlHandler();

        parser.parse(path, handler);
        return handler.getGraph();
    }


}
