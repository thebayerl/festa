package view;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InscricaoTurma extends Application {
	
	private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
    	InscricaoTurma.stage = stage;
    }

	@Override
	public void start(Stage stage) throws Exception {

		 Parent root = FXMLLoader.load(getClass().getResource("/view/InscricaoTurma.fxml"));//Carrega FXML
	        
        Scene scene = new Scene(root);
        stage.setTitle("CadastrarTurma");
        stage.setScene(scene);
        stage.show();
        this.setStage(stage);
	}
	
	public static void main(String[] args) {
        launch(args);
    }

}
