package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class TelaAluno extends BorderPane{
	
	public TelaAluno () {
		
		FXMLLoader loader = new FXMLLoader( getClass().getResource( "/view/TelaAluno.fxml" ) );
	    loader.setRoot( this );
	    
	    try {
	        loader.load();
	    } catch ( IOException e ) {
	        throw new RuntimeException( e );
	    }
	}

}
