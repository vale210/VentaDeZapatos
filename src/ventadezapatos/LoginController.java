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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jesus
 */
public class LoginController implements Initializable {

    @FXML
    private Button LOGEAR;
    @FXML
    private Button Cancelacion;
    @FXML
    private TextField usuario;
    @FXML
    private TextField contra;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Log(ActionEvent event) throws IOException {
        if (usuario.getText().equalsIgnoreCase("Cooperativa") && contra.getText().equals("2023")) {
            // Crear una nueva ventana e invocar el método openVentanaTabla
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Obten la referencia al Stage actual
            openwindows(currentStage);
            usuario.setText("");
            contra.setText("");
        } else if ((usuario.getText() == null ? ("Cooperativa") != null : !usuario.getText().equalsIgnoreCase("Cooperativa")) && contra.getText() != ("2023")) {
            // Mostrar un cuadro de diálogo de alerta si los datos de inicio de sesión son incorrectos
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Datos Erroneos");
            alerta.setHeaderText("Vuelva a ingresar los datos");
            alerta.setContentText("Los datos ingresados no son validos");
            alerta.showAndWait();
        }
    }

    public void openwindows(Stage previousStage) throws IOException {

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

    @FXML
    private void Cancel(ActionEvent event) {
        System.exit(0);
    }

}
