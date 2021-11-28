package com.clinical.management.main;

import com.clinical.management.util.FXResizeHelper;
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
	
	//redimensiona
	FXResizeHelper re;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(buildWindow(stage), null);

        stage.setScene(scene);
        FXResizeHelper re = new FXResizeHelper(stage, 0, 10);
        this.re = re;
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    private BorderPane buildWindow(Stage stage) {
        BorderPane windowContainer = new BorderPane();  // Container da janela
        windowContainer.setPrefSize(1175, 651); // Tamanho inicial da janela
        BorderPane top = new BorderPane(); //Top

        Node titleBarContainer = buildTitleBar(stage);
        top.setCenter(titleBarContainer);
        top.setLeft(stackNavigator.getBackButton());

        windowContainer.setTop(top); // Adicina a barra de titulo na janela

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
        
        
        /// bottaõ de minimizar
        Button minimizeButton = new Button();  // Botão de min a janela
        minimizeButton.setId("minimizeButton"); //windowCloseIcon
        minimizeButton.setOnAction(e -> this.handleMinimize());
        Image minimizeIcon = new Image(getClass().getResourceAsStream("../view/images/windowMinimizeIcon.png"));
        ImageView windowMinimizeIcon = new ImageView(minimizeIcon);
        windowMinimizeIcon.setPreserveRatio(true);
        windowMinimizeIcon.fitWidthProperty().set(10);
        windowMinimizeIcon.fitHeightProperty().set(10);
        minimizeButton.setGraphic(windowMinimizeIcon);
        minimizeButton.setPrefSize(45, 35);
        titleBarContainer.getChildren().add(minimizeButton);
        
        Button maxButton = new Button();  // Botão de max a janela
        maxButton.setId("maxButton"); //windowCloseIcon
        maxButton.getStyleClass().add("svv");
        maxButton.setOnAction(e -> this.handleMaximize());
        Image maximizeIcon = new Image(getClass().getResourceAsStream("../view/images/windowMaximizeIcon.png"));
        ImageView windowMaximizeIcon = new ImageView(maximizeIcon);
        windowMaximizeIcon.setPreserveRatio(true);
        windowMaximizeIcon.fitWidthProperty().set(10);
        windowMaximizeIcon.fitHeightProperty().set(10);
        maxButton.setGraphic(windowMaximizeIcon);
        maxButton.setPrefSize(45, 35);
        titleBarContainer.getChildren().add(maxButton);

        // imagem do botão de fechcar
        Image imageOk = new Image(getClass().getResourceAsStream("../view/images/windowCloseIcon.png"));
        ImageView img = new ImageView(imageOk);
        img.setPreserveRatio(true);
        img.fitWidthProperty().set(10);
        img.fitHeightProperty().set(10);
        closeButton.setGraphic(img);
        closeButton.setPrefSize(45, 35);

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
		
		// double click para window mode
		titleBarContainer.setOnMouseClicked(new EventHandler<MouseEvent>() {
			 
		    @Override
		    public void handle(MouseEvent click) {
		 
		        if (click.getClickCount() == 2) {
		        	handleMaximize();
		        }
		    }
		});

        return titleBarContainer;
    }

    public void handleMaximize() {
    	if (this.re != null) {
    		this.re.switchWindowedMode();
    	}
    }
    
    public void handleMinimize() {
    	if (this.re != null) {
    		this.re.minimize();
    	}
    }
}
