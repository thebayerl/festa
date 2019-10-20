package view;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    static Boolean answer;

    public static boolean display(String messages){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);


        return answer;
    }
}
