/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package dijkstrafx;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/*import model.Algorithm;
import model.Node;*/

/**
 *
 * @author milar
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Gui gui = new Gui();
        Pane root = new Pane();

        MenuBar mb = new MenuBar();
        mb.setPrefWidth(2160);

        VBox vb = new VBox(mb);
        GridPane NodeUI = gui.createUI();
        gui.addNodeEdgeControls( NodeUI);

        Group newRoot = new Group();
        newRoot.getChildren().addAll(NodeUI,vb);
        primaryStage.setTitle("GRAPH");
        primaryStage.setScene(new Scene(root, 500, 300));
        root.getChildren().addAll(NodeUI,vb);

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);

    }
    
}
