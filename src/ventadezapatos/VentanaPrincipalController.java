/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package ventadezapatos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author jesus
 */
public class VentanaPrincipalController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button ButtonWomen;
    @FXML
    private Button Bbaby;
    @FXML
    private Button Bmen;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void womenb(ActionEvent event) {
    }

    @FXML
    private void BabyB(ActionEvent event) {
    }

    @FXML
    private void MenB(ActionEvent event) {
    }
    
}
