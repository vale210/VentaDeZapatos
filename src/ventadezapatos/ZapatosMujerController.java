/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ventadezapatos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jesus
 */
public class ZapatosMujerController implements Initializable {

    @FXML
    private Button copm1;
    @FXML
    private Button comp2;
    @FXML
    private Button comp3;
    @FXML
    private Button MenuP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Compr1(ActionEvent event) {
    }

    @FXML
    private void compr2(ActionEvent event) {
    }

    @FXML
    private void compr3(ActionEvent event) {
    }

    @FXML
    private void Regresar(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Obten la referencia al Stage actual
        openwindows(currentStage);
    }

    public void openwindows(Stage previousStage) throws IOException {
        // Crea un nuevo Stage para la ventana principal
        Stage newStage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaPrincipal.fxml"));
        Parent root = loader.load();

        VentanaPrincipalController controller = loader.getController();

        Scene scene = new Scene(root);

        newStage.setScene(scene);

        // Cierra el Stage anterior
        previousStage.close();

        // Muestra el nuevo Stage
        newStage.show();
    }

}
