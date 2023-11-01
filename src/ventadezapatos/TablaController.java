/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ventadezapatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author jesus
 */
public class TablaController implements Initializable {

    @FXML
    private TableView<Nodo> tabla;
    @FXML
    private TableColumn ide;
    @FXML
    private TableColumn marc;
    @FXML
    private TableColumn preci;
    @FXML
    private TableColumn tall;
    @FXML
    private TableColumn unid;
    @FXML
    private TableColumn colo;
    @FXML
    private TableColumn tip;
    @FXML
    private Button compr;
    @FXML
    private Button Busc;
    @FXML
    private Button Agreg;
    @FXML
    private Button elimn;

    private ObservableList<Nodo> nodos;

    @FXML
    private Button menup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nodos = FXCollections.observableArrayList();
        // Se crea un objeto File con la ruta del archivo "C:/Prueba/prueba.txt"
        File file = new File("src/Archivo/archivo.txt");

        try {
            // Se crea un objeto Scanner para leer el contenido del archivo
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // Se lee una línea del archivo y se divide en partes utilizando la coma como separador
                String[] line = scanner.nextLine().split(",");
                // Se crea un objeto "nodo" utilizando los valores obtenidos de la línea
                Nodo zap = new Nodo(line[0], line[1], Double.parseDouble(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]), line[5], line[6]);
                // Se agrega el objeto "zap" a la lista "nodos"
                nodos.add(zap);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // Si ocurre una excepción FileNotFoundException, se imprime un mensaje de error
            JOptionPane.showMessageDialog(null, "El archivo no se pudo encontrar.");
        }

        // Configuración de las propiedades de las columnas de la tabla
        // Se utiliza PropertyValueFactory para asignar el valor de los atributos de "nodo" a las celdas de la tabla
        ide.setCellValueFactory(new PropertyValueFactory<>("ID"));
        ide.setStyle("-fx-alignment: CENTER-LEFT");
        marc.setCellValueFactory(new PropertyValueFactory<>("marca"));
        marc.setStyle("-fx-alignment: CENTER-LEFT");
        preci.setCellValueFactory(new PropertyValueFactory<>("precio"));
        preci.setStyle("-fx-alignment: CENTER-LEFT");
        tall.setCellValueFactory(new PropertyValueFactory<>("talla"));
        tall.setStyle("-fx-alignment: CENTER-LEFT");
        unid.setCellValueFactory(new PropertyValueFactory<>("unidades"));
        unid.setStyle("-fx-alignment: CENTER-LEFT");
        colo.setCellValueFactory(new PropertyValueFactory<>("color"));
        colo.setStyle("-fx-alignment: CENTER-LEFT");
        tip.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tip.setStyle("-fx-alignment: CENTER-LEFT");

        // Hacer que la lista "nodos" sea circular
        int size = nodos.size();
        for (int i = 0; i < size; i++) {
            int prevIndex = i == 0 ? size - 1 : i - 1;
            int nextIndex = i == size - 1 ? 0 : i + 1;
            // Se establecen las referencias al nodo anterior y siguiente en cada nodo de la lista
            nodos.get(i).setAnt(nodos.get(prevIndex));
            nodos.get(i).setSig(nodos.get(nextIndex));
        }

        // Se asigna la lista "nodos" como origen de datos de la tabla "tablaauto"
        tabla.setItems(nodos);
    }

    @FXML
    private void MenuP(ActionEvent event) throws IOException {
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

    @FXML
    private void CompID(ActionEvent event) {
    }

    @FXML
    private void Busqueda(ActionEvent event) {
    }

    @FXML
    private void Push(ActionEvent event) {
    }

    @FXML
    private void Pop(ActionEvent event) {
        if (!nodos.isEmpty()) {
            // Actualizar la tabla
            int lastIndex = tabla.getItems().size() - 1;
            tabla.getItems().remove(lastIndex);

            // Eliminar el último elemento del archivo (suponiendo que estás usando ObjectOutputStream)
            File archivo = new File("src/Archivo/archivo.txt");

            try {
                // Crear un flujo de salida de archivo
                FileOutputStream fileOutputStream = new FileOutputStream(archivo);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                // Crear un ObservableList temporal sin el último elemento
                ObservableList<Nodo> nodosTemp = FXCollections.observableArrayList(nodos.subList(0, nodos.size() - 1));
                
                // Escribir el ObservableList temporal en el archivo
                objectOutputStream.writeObject(nodosTemp);

                // Cerrar el flujo de salida
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                // Manejar excepciones de E/S apropiadamente
            }
        }
    }

}
