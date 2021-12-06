package com.clinical.management.view.navigation;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * Gerencia a navegação entre paginas no projeto
 * @author Daneil Santa Rosa Santos
 *
 */
public class StackNavigator {
    private static BorderPane pages;
    private static StackPane sp;
    private static int actualPageIndex;
    private static Node backButton = new HBox();
    //private static Boolean isUndecorated;

    /**
     * Construtor padrão de Navigation. Cria uma unica instancia da classe
     * para todo o app
     */
    public StackNavigator() {
        if (pages != null) return; // So permite uma unica instancia de Navigation
        BorderPane bp = new BorderPane();
        bp.setId("bp");
        pages = bp;
    }

    public void setInitialPage(Node initialPage) {
        sp = new StackPane(); // Cria a stack de telas
        sp.getChildren().add(initialPage);
        actualPageIndex = 0;
        //isUndecorated = false;

        pages.setId("AplicationBackground");
        Button bt = new Button("");
        bt.setId("voltarButton");
        bt.setPrefSize(46, 32);
        bt.getStylesheets().add(getClass().getResource("../../view/css/applicationStyles.css").toString());
        bt.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent arg0) {
                removeLastPage();
            }
            
        });
        bt.setDisable(true);
        HBox hb = new HBox();
        hb.getChildren().add(bt);
        hb.setPadding(new Insets(10, 10, 10, 10));

        hb.setId("navigationContainer");
        
        ((HBox) backButton).getChildren().add(bt);
        ((HBox) backButton).setFillHeight(true);
        ((HBox) backButton).setAlignment(Pos.CENTER);
        pages.setCenter(sp);

        
    }

    public void changeInitialPage(Node initialPage) {
    	sp.getChildren().remove(0);
    	sp.getChildren().add(0, initialPage);
    }
    
    public static void addPage(Node newPage) {
        if (pages == null) {
            return;
        }
        newPage.translateYProperty().set(sp.getHeight());
        newPage.opacityProperty().set(0);
        newPage.scaleXProperty().set(0.95);
        newPage.scaleYProperty().set(0.95);
        sp.getChildren().add(newPage);

        Node rootPage = sp.getChildren().get(0);

        /*
         * Animar transição entre telas
         */
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(newPage.translateYProperty(), 0, Interpolator.EASE_BOTH);
        KeyValue kv2 = new KeyValue(newPage.opacityProperty(), 1, Interpolator.EASE_BOTH);
        KeyValue kv3 = new KeyValue(newPage.scaleXProperty(), 1.0, Interpolator.EASE_BOTH);
        KeyValue kv4 = new KeyValue(newPage.scaleYProperty(), 1.0, Interpolator.EASE_BOTH);
        KeyValue kv5 = new KeyValue(rootPage.opacityProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(0.8), kv2);
        KeyFrame kf3 = new KeyFrame(Duration.seconds(0.5), kv3);
        KeyFrame kf4 = new KeyFrame(Duration.seconds(0.5), kv4);
        KeyFrame kf5 = new KeyFrame(Duration.seconds(0.5), kv5);
        timeline.getKeyFrames().add(kf2);
        timeline.getKeyFrames().add(kf);
        timeline.getKeyFrames().add(kf3);
        timeline.getKeyFrames().add(kf4);
        timeline.getKeyFrames().add(kf5);
        timeline.play();
        actualPageIndex = actualPageIndex + 1;
        try {
			((HBox) backButton).getChildren().get(0).setDisable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

    public static void removeLastPage() {
        if (pages == null || sp.getChildren().size() == 1) {
            return;
        }
        Node lastPage = sp.getChildren().get(sp.getChildren().size() - 1);
        Node rootPage = sp.getChildren().get(0);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(lastPage.translateYProperty(), sp.getHeight(), Interpolator.EASE_BOTH);
        KeyValue kv2 = new KeyValue(lastPage.opacityProperty(), 0, Interpolator.EASE_BOTH);
        KeyValue kv3 = new KeyValue(lastPage.scaleXProperty(), 0.9, Interpolator.EASE_BOTH);
        KeyValue kv4 = new KeyValue(lastPage.scaleYProperty(), 0.9, Interpolator.EASE_BOTH);
        KeyValue kv5 = new KeyValue(rootPage.opacityProperty(), 1, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(0.8), kv2);
        KeyFrame kf3 = new KeyFrame(Duration.seconds(0.5), kv3);
        KeyFrame kf4 = new KeyFrame(Duration.seconds(0.5), kv4);
        KeyFrame kf5 = new KeyFrame(Duration.seconds(0.5), kv5);
        timeline.getKeyFrames().add(kf2);
        timeline.getKeyFrames().add(kf);
        timeline.getKeyFrames().add(kf3);
        timeline.getKeyFrames().add(kf4);
        if (sp.getChildren().size() == 2) {
            timeline.getKeyFrames().add(kf5);
        }
        timeline.play();
        actualPageIndex = actualPageIndex - 1;
        ((HBox) backButton).getChildren().get(0).setDisable(sp.getChildren().size() - 1 <= 1);
        timeline.setOnFinished(e -> sp.getChildren().remove(sp.getChildren().size() - 1));
    }

    /**
     * 
     * @return Null se não houver sido criado um objeto de navigation
     * ou um STackpane
     */
    public static BorderPane GetNavigator() {
        return pages;
    }

    public Node getBackButton() {
    	return backButton;
    }
}
