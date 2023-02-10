package dijkstrafx;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Region;

class Gui {
private static final String RESULT_STRING = "Sequence of nodes discovered in the shortest path: \n";

    int prevNodes = -1;
    int prevEdges = -1;
    RandomGraph graphwithnodes = new RandomGraph(0,0);

    GridPane createUI(){

    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.TOP_LEFT);
    gridPane.setPadding(new Insets(20,20,20,20));
    gridPane.setHgap(20);
    gridPane.setVgap(20);

    return gridPane;
}

    void addNodeEdgeControls(GridPane gridPane) {

        Label vertexLabel = new Label(" Number of vertices : ");
        gridPane.add(vertexLabel, 0,2);
        TextField nodesTxt = new TextField();
        nodesTxt.setPrefWidth(70);
        gridPane.add(nodesTxt, 1,2);

        Label edgeLabel = new Label(" Number of edges : ");
        gridPane.add(edgeLabel, 0,3);
        TextField edgesTxt = new TextField();
        edgesTxt.setPrefWidth(70);
        gridPane.add(edgesTxt, 1,3);

        Label sourceLabel = new Label(" Source (number) : ");
        gridPane.add(sourceLabel, 0,4);
        TextField sourceTxt = new TextField();
        sourceTxt.setPrefWidth(70);
        gridPane.add(sourceTxt, 1,4);

        //bottone crea grafo
        Button addGraphButton = new Button("Set graph & source");
        addGraphButton.setDefaultButton(true);
        addGraphButton.setPrefHeight(40);
        addGraphButton.setPrefWidth(130);
        gridPane.add(addGraphButton, 0, 5, 4, 2); //Node child, int columnIndex, int rowIndex, int colspan, int rowspan
        GridPane.setValignment(addGraphButton, VPos.CENTER);
        GridPane.setHalignment(addGraphButton, HPos.CENTER);


        addGraphButton.setOnAction(event -> {
            if(nodesTxt.getText()==null || edgesTxt.getText()==null || nodesTxt.getText().isEmpty() || edgesTxt.getText().isEmpty() ) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!", "Please enter number");
            }
            else{
                int nodes;
                int edges;
                int source;
                try{
                    nodes = Integer.parseInt(nodesTxt.getText());
                    edges = Integer.parseInt(edgesTxt.getText());
                    source = Integer.parseInt(sourceTxt.getText());
                }
                catch(NumberFormatException e){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!", "Invalid Input");
                    return;
                }
                double maxEdges = (double) nodes*(nodes-1);
                if(edges>maxEdges){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Warning!", "The number of edges exceeds the maximum value N * (N - 1)");
                    return;
                }
                if(source>nodes-1){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Warning!", "Invalid source");
                    return;
                }
                if (nodes != prevNodes || edges != prevEdges) {
                   final int finalNodes = nodes;
                    final int finalEdges = edges;
                    final int finalSource = source;
                    graphwithnodes.setNumNodes(finalNodes,finalSource);
                    graphwithnodes.setSource(source);
                    graphwithnodes.setNumEdges(finalEdges);

                    refresh(nodes,edges);
                    showAlert(Alert.AlertType.INFORMATION, gridPane.getScene().getWindow(), "Addition Successful!", "New random graph");
                } else {
                    graphwithnodes.setSource(source);
                    showAlert(Alert.AlertType.INFORMATION, gridPane.getScene().getWindow(), "Addition Successful!", "New source provided");
                }

                computePathGui(gridPane, graphwithnodes);
                sourceTxt.setText(null);
            }
        });
    }

    private void refresh(int nodes, int edges) {
        prevNodes = nodes;
        prevEdges = edges;
    }

    void computePathGui(GridPane gridPane, RandomGraph graph){
        Button PathButton = new Button("Get path");
        PathButton.setPrefHeight(40);
        PathButton.setDefaultButton(true);
        PathButton.setPrefWidth(130);
        gridPane.add(PathButton, 2, 5, 5, 2);
        GridPane.setValignment(PathButton, VPos.CENTER);
        GridPane.setHalignment(PathButton, HPos.CENTER);

        PathButton.setOnMouseClicked(event -> {
            String path = RESULT_STRING + graph.printPath();
            showAlert(Alert.AlertType.INFORMATION, gridPane.getScene().getWindow(), " Path ", path);
        });

    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setResizable(true);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText(null);
        if (message.length() > 1000) {
            TextArea textArea = new TextArea(message);
            textArea.setEditable(false);
            textArea.setWrapText(true);
            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            alert.getDialogPane().setExpandableContent(textArea);
        } else {
            alert.setContentText(message);
        }
        alert.initOwner(owner);
        alert.show();
    }

}
