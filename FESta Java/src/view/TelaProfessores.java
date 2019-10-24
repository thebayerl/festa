package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;;

public class TelaProfessores extends BorderPane {

	public TelaProfessores () {
		
		FXMLLoader loader = new FXMLLoader( getClass().getResource( "/view/TelaProfessores.fxml" ) );
	    loader.setRoot( this );
	    
	    try {
	        loader.load();
	    } catch ( IOException e ) {
	        throw new RuntimeException( e );
	    }
	}
}

