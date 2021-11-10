package com.clinical.management.main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    // Posição da janela
    private double offsetX;
	private double offsetY;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(buildWindow(stage), null);

        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    private Parent buildWindow(Stage stage) {
        BorderPane windowContainer = new BorderPane();  // Container da janela
        windowContainer.setPrefSize(900, 500); // Tamanho inicial da janela

        Node titleBarContainer = buildTitleBar(stage);

        windowContainer.setTop(titleBarContainer); // Adicina a barra de titulo na janela

        // Estilos da janela
        windowContainer.setId("windowContainer");
        windowContainer.getStylesheets().add(getClass().getResource("../view/css/applicationStyles.css").toString());

        return windowContainer;
    }

    private Node buildTitleBar(Stage stage) {
        HBox titleBarContainer = new HBox();  // Container da barra de titulo

        // Estilos da barra de titulo
        titleBarContainer.setId("titleBar");
        titleBarContainer.getStylesheets().add(getClass().getResource("../view/css/applicationStyles.css").toString());

		Button closeButton = new Button();  // Botão de fechar a janela
        closeButton.setId("closeButton"); //windowCloseIcon

        // imagem do botão
        Image imageOk = new Image(getClass().getResourceAsStream("../view/images/windowCloseIcon.png"));
        ImageView img = new ImageView(imageOk);
        img.setPreserveRatio(true);
        img.fitWidthProperty().set(10);
        img.fitHeightProperty().set(10);
        closeButton.setGraphic(img);
        closeButton.setPrefSize(35, 35);

		closeButton.setOnAction(e -> stage.close());
        titleBarContainer.getChildren().add(closeButton);  // Adiciona o botão de fechar na barra de titulo

        // Inicio mover tela
		titleBarContainer.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	offsetX = stage.getX() - event.getScreenX();
            	offsetY = stage.getY() - event.getScreenY();
            }
        });
		
		titleBarContainer.setOnMouseDragged((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	stage.setX(event.getScreenX() + offsetX);
            	stage.setY(event.getScreenY() + offsetY);
            }
        });
		// fim mover tela

        return titleBarContainer;
    }
}
