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
public class VentanaBebesController implements Initializable {

    @FXML
    private Button MENUp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void menu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        openWindow("VentanaPrincipal.fxml", stage);
    }
    
    private void openWindow(String fxmlFileName, Stage stage) throws IOException {
        // Crear un nuevo cargador de FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));

        // Cargar el archivo FXML y asignarlo como la raíz de la ventana
        Parent root = loader.load();

        // Obtener el controlador de la ventana cargada (si es necesario)
        // VentanaPrincipalController controller = loader.getController();
        // Crear una nueva escena con la raíz cargada
        Scene scene = new Scene(root);

        // Establecer la nueva escena como la escena de la ventana
        stage.setScene(scene);
        stage.show();
    }
}
