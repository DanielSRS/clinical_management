package com.clinical.management.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MicaMaterial {
	
	private FXResizeHelper resizeHelper;
	
	/**
	 * Cria uma janela sem decoração e handlers para redimensionamento e movimentação da janela
	 * @param primaryStage Instancia de Stage da janela a ser criada
	 * @return StackPane com as 'camadas' que compõe o efeito de mica
	 * @see <a href="https://docs.microsoft.com/pt-br/windows/apps/design/style/mica" target="_top">Documentação do materia mica no windows</a>
	 */
	public StackPane getMica(Stage primaryStage) {
		String wallpaperLocation = "\\AppData\\Roaming\\Microsoft\\Windows\\Themes\\CachedFiles";
		String userHomeLocation = System.getProperty("user.home");
		String fileName = null;
		String appImg = null;

		File folder = new File(userHomeLocation + wallpaperLocation);
		File[] listOfFiles = folder.listFiles();
		
		if (listOfFiles != null && listOfFiles.length > 0) {
			fileName = listOfFiles[0].getName();
		}
		
		if (fileName != null) {
			System.out.println("Wallpaper path: " + userHomeLocation + wallpaperLocation + "\\" + fileName);
			appImg = userHomeLocation + wallpaperLocation + "\\" + fileName;
		}
		
		//*******************************************
		StackPane st = new StackPane();
		ImageView imageView = null;

		if (appImg != null) {
			
			try {
		        FileInputStream input = new FileInputStream(appImg);
		        Image image = new Image(input);
		        imageView = new ImageView(image);
		        
		        imageView.setEffect(new BoxBlur(255, 255, 6));
		        
		        imageView.getStyleClass().add("h");
				
		        st.getChildren().add(imageView);
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			}
		}

        Pane p1 = new Pane();
        p1.getStyleClass().add("mica");
        
        Blend blend = new Blend();
        blend.setMode(BlendMode.DARKEN);
        p1.setEffect(blend);
        p1.setOpacity(0.75);
        
        st.setId("micaBG");
        st.getChildren().add(p1);
		
		
		////////////////

        
        Scene scene = new Scene(st, 1175, 650);
        scene.setFill(null);
        primaryStage.setScene(scene);
        this.resizeHelper = new FXResizeHelper(imageView, primaryStage, 10, 10);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        return st;
	}

	/**
	 * Obtem resizeHelper
	 * @return objeto FXResizeHelper
	 * @see com.clinical.management.util.FXResizeHelper
	 */
	public FXResizeHelper getResizeHelper() {
		return this.resizeHelper;
	}
}
