package ca.uqam.info.ace.mermaid.gui;

import ca.uqam.info.ace.mermaid.mermaid.MermaidRegistry;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class StartVisualizer extends Application {

    @Override
    public void start(Stage InfoStage) {
        VBox root = new VBox();
        Scene infoscene = new Scene(root, 500, 400);
        Text initial_text = new Text("Les mermaid sont prêt à recevoir les données à cette adresse :");
        Text text = new Text("Ouvrir la fenetre du mermaide correspondant :");
        Hyperlink link = new Hyperlink("http://localhost:8080/mermaid/");
        link.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    URI uri = new URI("http://localhost:8080/mermaid/");
                    Desktop.getDesktop().browse(uri);
                } catch (URISyntaxException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
        root.getChildren().add(initial_text);
        root.getChildren().add(link);
        root.getChildren().add(text);
        for (int i = 1; i <= MermaidRegistry.GLOBAL_REGISTRY.size(); i++) {
            Stage stage = new Stage();
            MermaidVisualizer mermaidVisualizer = new MermaidVisualizer(i, stage);
            mermaidVisualizer.attach(MermaidRegistry.GLOBAL_REGISTRY.fetch(i));
            Button btn = new Button(MermaidRegistry.GLOBAL_REGISTRY.fetch(i).getName().get());
            btn.setOnAction((ActionEvent event) -> {
                stage.show();
            });
            root.getChildren().add(btn);
        }
        root.setSpacing(10);
        InfoStage.setScene(infoscene);
        InfoStage.setTitle("Info");
        InfoStage.show();

    }

    public static void Refresh (MermaidVisualizer v){
        v.refresh();
    }


}



