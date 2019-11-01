package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class TelaSecretario extends BorderPane {
	
	public TelaSecretario () {
		
		FXMLLoader loader = new FXMLLoader( getClass().getResource( "/view/TelaSecretario.fxml" ) );
	    loader.setRoot( this );
	    
	    try {
	        loader.load();
	    } catch ( IOException e ) {
	        throw new RuntimeException( e );
	    }
	}

}
