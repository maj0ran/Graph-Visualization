package mrn.ui.menubar;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import mrn.data.Graph;
import mrn.data.GraphBuilder;
import mrn.data.Model;
import mrn.ui.base.View;
import mrn.ui.base.Window;


public class ViewMenubar extends View<Model> {

    Window parent;
    public MenuBar menu; // root
    Menu menuFile;
    MenuItem save;
    MenuItem load;
    MenuItem quit;

    Label loadLabel = new Label("load");

    public ViewMenubar(Model model) {
        this.setModel(model);
        this.setController(new ControllerMenubar(model, this));
    }

    @Override
    protected void init() {

        menu = new MenuBar();
        menuFile = new Menu("File");
        save = new MenuItem("Save");

        loadLabel = new Label("load");
        load = new CustomMenuItem(loadLabel);
        quit = new MenuItem("Quit");

        menuFile.getItems().addAll(save, load, quit);
        menu.getMenus().addAll(menuFile);



    }
    public void update() {
        System.out.println("load");
    }

    public MenuBar getRoot() {
        return this.menu;
    }
}
