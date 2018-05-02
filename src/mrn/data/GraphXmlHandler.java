package mrn.data;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class GraphXmlHandler extends DefaultHandler {

    private Graph<String> g;
    private GraphNode<String> currNode;

    boolean belem;

    @Override
    public void startDocument() {
        System.out.println("Document Start");
    }

    @Override
    public void endDocument() {
        System.out.println(g.toString());
        System.out.println("Document end");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
        if (qName.equalsIgnoreCase("graph")) {
            boolean isWeighted = Boolean.valueOf(atts.getValue("weighted"));
            boolean isDirected = Boolean.valueOf(atts.getValue("directed"));
            boolean isMultigraph = Boolean.valueOf(atts.getValue("multigraph"));
            int size = Integer.valueOf(atts.getValue("size"));

            this.g = new Graph<String>(isWeighted, isDirected, isMultigraph);
        }


        if (qName.equalsIgnoreCase("node")) {
            int idx = Integer.valueOf(atts.getValue("id"));
            currNode = new GraphNode<>();
        }

        if (qName.equalsIgnoreCase("element")) {
            belem = true;

        }
        if (qName.equalsIgnoreCase("link")) {
            int src = Integer.valueOf(atts.getValue("src"));
            int dest = Integer.valueOf(atts.getValue("dest"));
            GraphNode srcNode = g.getNodes().get(src);
            GraphNode destNode = g.getNodes().get(dest);
            g.link(srcNode, destNode);
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) {
        if (qName.equalsIgnoreCase("node")) {
            g.getNodes().add(currNode);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if(belem) {
            String s = new String(ch, start, length);
            s = s.trim();
            currNode.setElement(s);
            belem = false;
        }
    }


    public Graph<String> getGraph() {
        return this.g;
    }
}

