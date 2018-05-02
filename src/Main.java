import javafx.application.Application;
import javafx.stage.Stage;
import mrn.data.DataStructure;
import mrn.data.Graph;
import mrn.data.GraphXmlHandler;
import mrn.ui.JfxUi;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        JfxUi ui = new JfxUi(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
