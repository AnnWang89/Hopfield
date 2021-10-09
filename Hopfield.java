package nn.hw3_2;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class Hopfield extends Application{
	public static Stage mainStage;
	public static Scene mainScene;
	public static Slider _learning_rate;
	@Override
	
	public void start(Stage primaryStage) throws IOException{
		mainStage = primaryStage;
	    URL fxmlURL = this.getClass().getResource("view/main.fxml");
	    FXMLLoader loader = new FXMLLoader(fxmlURL);
	    Parent main = loader.load();    
	    mainScene = new Scene(main);
	    mainStage.setTitle("Hopfield");//title name
	    mainStage.setScene(mainScene);//set scene to mainScene
	   
	    mainStage.show();//show the Stage
	    
	}
	public static void main(String[] args) throws Exception{
		launch(args);
		// TODO Auto-generated method stub

	}

}
