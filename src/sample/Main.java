package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1280, 900));
        primaryStage.show();
    }


    public static void main(String[] args) {
        new smile.License(
                "SMILE LICENSE dd064e06 805645fc c99154ea " +
                        "THIS IS AN ACADEMIC LICENSE AND CAN BE USED " +
                        "SOLELY FOR ACADEMIC RESEARCH AND TEACHING, " +
                        "AS DEFINED IN THE BAYESFUSION ACADEMIC " +
                        "SOFTWARE LICENSING AGREEMENT. " +
                        "Serial #: 1v7l43thjxrgb9iuua7v0ktas " +
                        "Issued for: Diou Medeiros (diou.182@gmail.com) " +
                        "Academic institution: Universidade do sul de Santa Catarina " +
                        "Valid until: 2020-12-22 " +
                        "Issued by BayesFusion activation server",
                new byte[] {
                        21,15,5,0,86,63,71,121,109,120,-73,-123,-82,61,-43,48,
                        115,-77,-19,122,-4,-34,61,-52,-92,-120,-105,42,73,79,127,-10,
                        -50,-21,111,-3,77,80,-103,-118,32,-4,-112,-21,-90,67,84,-33,
                        -30,4,-47,85,42,103,-102,-1,-55,107,-39,-79,11,-96,-102,-51
                }
        );
        launch(args);
    }
}
