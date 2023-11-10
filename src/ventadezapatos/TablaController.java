/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ventadezapatos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    @FXML
    private TextField IDE;
    @FXML
    private TextField PRECIO;
    @FXML
    private TextField MARCA;
    @FXML
    private TextField TALLA;
    @FXML
    private TextField COLOR;
    @FXML
    private TextField UNIDADES;
    @FXML
    private TextField TIPO;

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
            System.out.println("El archivo no se pudo encontrar");
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
        // Obtener el automóvil seleccionado en la tabla
        Nodo zap = tabla.getSelectionModel().getSelectedItem();

        if (zap == null) {
            // Mostrar mensaje de advertencia si no se ha seleccionado ningún zapato
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("No se ha seleccionado ningún zapato");
            alerta.setContentText("Seleccione un zapato de la tabla para comprar.");
            alerta.showAndWait();
            return;
        }

        // Crear un diálogo para obtener la cantidad de unidades a comprar
        TextInputDialog dialogo = new TextInputDialog("");
        dialogo.setTitle("Cantidad a comprar");
        dialogo.setHeaderText(null);
        dialogo.setContentText("Ingrese la cantidad a comprar, debe ser menor a " + zap.getUnidades() + "\n");
        Optional<String> cantidad = dialogo.showAndWait();

        if (!cantidad.isPresent()) {
            return; // El usuario ha cerrado el diálogo
        } else if (!cantidad.get().matches("^[1-9]\\d*$")) {
            // La entrada no es un número entero positivo
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Entrada inválida");
            alerta.setContentText("La cantidad de unidades debe ser un número entero positivo.");
            alerta.showAndWait();
            return;
        }

        int cantidadComprar = Integer.parseInt(cantidad.get());

        if (cantidadComprar > zap.getUnidades()) {
            // Mostrar mensaje de advertencia si la cantidad de unidades no está disponible
            Alert ale = new Alert(Alert.AlertType.INFORMATION);
            ale.setHeaderText("Información");
            ale.setContentText("La cantidad de unidades no está disponible");
            ale.showAndWait();
            return;
        }

        // Actualizar la cantidad de unidades en el objeto Nodo
        zap.setUnidades(zap.getUnidades() - cantidadComprar);

        // Abre el archivo para lectura y escritura
        String archivoRuta = "src/Archivo/archivo.txt";
        File archivo = new File(archivoRuta);

        try {
            // Crear un ObservableList para almacenar las líneas del archivo
            ObservableList<String> lineas = FXCollections.observableArrayList();

            // Leer todo el contenido del archivo y almacenarlo en el ObservableList
            Scanner scanner = new Scanner(archivo);
            while (scanner.hasNextLine()) {
                lineas.add(scanner.nextLine());
            }
            scanner.close();

            // Encuentra y actualiza el elemento correspondiente en el ObservableList
            for (int i = 0; i < lineas.size(); i++) {
                String[] elementos = lineas.get(i).split(",");
                if (elementos.length >= 5 && elementos[0].equals(zap.getID())) {
                    int unidades = Integer.parseInt(elementos[4]);
                    unidades -= cantidadComprar;
                    elementos[4] = Integer.toString(unidades);
                    lineas.set(i, String.join(",", elementos));
                    break;
                }
            }

            // Abre el archivo nuevamente para escritura
            FileWriter fileWriter = new FileWriter(archivoRuta);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Escribe todas las líneas actualizadas en el archivo
            for (String linea : lineas) {
                bufferedWriter.write(linea);
                bufferedWriter.newLine();
            }

            // Cierra el BufferedWriter
            bufferedWriter.close();

            // Mostrar información de la compra y confirmar el pago
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setHeaderText("¿Deseas comprar el zapato " + zap.getMarca() + "?");
            alerta.setContentText("El precio del zapato es: " + zap.getPrecio() + "\nEl total a pagar es: " + (zap.getPrecio() * cantidadComprar));
            Optional<ButtonType> result = alerta.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Pago realizado correctamente
                Alert notify = new Alert(Alert.AlertType.INFORMATION);
                notify.setTitle("¡Proceso Exitoso!");
                notify.setHeaderText("Cargando información...");
                notify.setContentText("Pago Realizado Correctamente!");
                notify.show();
            }

            if (zap.getUnidades() <= 0) {
                // Si no quedan unidades disponibles, eliminar el automóvil de la lista
                nodos.remove(zap);

                // Actualizar la tabla de zapatos
                tabla.setItems(null);
                tabla.layout();
                tabla.setItems(FXCollections.observableList(nodos));

                // Mostrar mensaje de información si no quedan unidades disponibles
                Alert ale = new Alert(Alert.AlertType.INFORMATION);
                ale.setHeaderText("Información");
                ale.setContentText("Ya no quedan unidades disponibles");
                ale.showAndWait();
            }

            // Actualizar la tabla
            tabla.refresh();
        } catch (IOException e) {
            // Mostrar un mensaje de error en caso de excepción
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("Error al actualizar el archivo");
            alerta.setContentText("Se produjo un error al actualizar el archivo.");
            alerta.showAndWait();
        }
    }

    @FXML
    private void Busqueda(ActionEvent event) {
        // Crear un cuadro de diálogo de entrada de texto para solicitar la id al usuario
        TextInputDialog dialogo = new TextInputDialog("");
        dialogo.setTitle("Buscar zapatos");
        dialogo.setHeaderText(null);
        dialogo.setContentText("Ingrese la ID:");

        // Mostrar el cuadro de diálogo y obtener la id ingresada por el usuario
        Optional<String> ID = dialogo.showAndWait();

        if (ID.isPresent()) {
            if (nodos.isEmpty()) {
                // Mostrar mensaje de advertencia si la lista está vacía
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setHeaderText("Lista vacía");
                alerta.setContentText("No hay zapatos en la lista.");
                alerta.showAndWait();
                return;
            }

            // Buscar la id en la lista de nodos
            Nodo actual = nodos.get(0);
            do {
                if (actual.getID().equals(ID.get())) {
                    // Mostrar información del automóvil si se encuentra la id
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setHeaderText("Información del zapato");
                    alerta.setTitle("Zapato encontrado");
                    alerta.setContentText("Precio: $" + actual.getPrecio() + " dolares"
                            + "\nMarca: " + actual.getMarca()
                            + "\nTalla: " + actual.getTalla()
                            + "\nUnidades: " + actual.getUnidades()
                            + "\nColor: " + actual.getColor()
                            + "\nTipo: " + actual.getTipo());
                    alerta.showAndWait();
                    return; // Salir del método si se encuentra la id
                }
                actual = actual.getSig();
            } while (actual != nodos.get(0)); // Continuar mientras no se regrese al primer nodo

            // Mostrar mensaje de advertencia si no se encuentra la id
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("ID no encontrada");
            alerta.setContentText("No se encontró ningún zapato con la ID ingresada.");
            alerta.showAndWait();
        }
    }

    @FXML
    private void Push(ActionEvent event) {
        // Verificar si todos los campos de texto están llenos
        if (IDE.getText().trim().isEmpty() || MARCA.getText().trim().isEmpty() || PRECIO.getText().trim().isEmpty() || TALLA.getText().trim().isEmpty()
                || UNIDADES.getText().trim().isEmpty() || COLOR.getText().trim().isEmpty() || TIPO.getText().trim().isEmpty()) {
            // Mostrar mensaje de advertencia sobre campos vacíos
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("Mensaje de información");
            alerta.setTitle("Diálogo de advertencia");
            alerta.setContentText("Es necesario llenar todos los campos");
            alerta.showAndWait();
            return;
        }

        // Crear un nuevo nodo con los datos ingresados en los campos de texto
        Nodo nuevo = new Nodo(IDE.getText().trim(), MARCA.getText().trim(), Double.parseDouble(PRECIO.getText().trim()),
                Integer.parseInt(TALLA.getText().trim()), Integer.parseInt(UNIDADES.getText().trim()), COLOR.getText().trim(), TIPO.getText().trim());

        // Agregar el nuevo nodo al inicio de la lista
        if (!nodos.isEmpty()) {
            Nodo ultimo = nodos.get(nodos.size() - 1);
            nuevo.setSig(nodos.get(0)); // El nuevo nodo apunta al primer nodo actual
            ultimo.setSig(nuevo); // El último nodo actual apunta al nuevo nodo
        } else {
            // Si la lista está vacía, hacer que el nuevo nodo apunte a sí mismo
            nuevo.setSig(nuevo); // El único nodo apunta a sí mismo
        }
        nodos.add(0, nuevo);

        // Actualizar la tabla y limpiar los campos de texto
        tabla.setItems(nodos);
        tabla.refresh();
        IDE.clear();
        MARCA.clear();
        PRECIO.clear();
        TALLA.clear();
        UNIDADES.clear();
        COLOR.clear();
        TIPO.clear();

        // Guardar el nuevo nodo en el archivo al inicio del archivo
        guardarNodoEnArchivoInicio(nuevo);
    }

    private void guardarNodoEnArchivoInicio(Nodo nodo) {
        // Ruta del archivo
        String archivoRuta = "src/Archivo/archivo.txt";

        try {
            // Leer el contenido actual del archivo
            Scanner scanner = new Scanner(new File(archivoRuta));
            StringBuilder fileContent = new StringBuilder();

            while (scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine()).append("\n");
            }
            scanner.close();

            // Agregar los datos del nuevo nodo al inicio del contenido del archivo
            String nuevoDato = nodo.getID() + "," + nodo.getMarca() + "," + nodo.getPrecio() + "," + nodo.getTalla() + "," + nodo.getUnidades() + "," + nodo.getColor() + "," + nodo.getTipo() + "\n";
            fileContent.insert(0, nuevoDato);

            // Escribir el contenido actualizado en el archivo
            FileWriter fileWriter = new FileWriter(archivoRuta);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(fileContent.toString());
            bufferedWriter.close();

            // Mostrar un mensaje de éxito
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Guardado en archivo");
            alerta.setContentText("Los datos se han guardado en el archivo correctamente.");
            alerta.showAndWait();
        } catch (IOException e) {
            // Mostrar un mensaje de error en caso de excepción
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al guardar en archivo");
            alerta.setContentText("Se produjo un error al guardar los datos en el archivo.");
            alerta.showAndWait();
        }
    }

    @FXML
    private void Pop(ActionEvent event) {
        // Verificar si la pila no está vacía
        if (!nodos.isEmpty()) {
            // Eliminar el último elemento de la pila
            nodos.remove(nodos.size() - 1);

            // Abre el archivo para lectura y escritura
            String archivoRuta = "src/Archivo/archivo.txt";
            File archivo = new File(archivoRuta);

            try {
                // Crear un ObservableList para almacenar las líneas del archivo
                ObservableList<String> lineas = FXCollections.observableArrayList();

                // Leer todo el contenido del archivo y almacenarlo en el ObservableList
                Scanner scanner = new Scanner(archivo);
                while (scanner.hasNextLine()) {
                    lineas.add(scanner.nextLine());
                }
                scanner.close();

                // Abre el archivo nuevamente para escritura
                FileWriter fileWriter = new FileWriter(archivoRuta);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                // Escribir todas las líneas en el archivo, excepto la última
                for (int i = 0; i < lineas.size() - 1; i++) {
                    bufferedWriter.write(lineas.get(i));
                    bufferedWriter.newLine();
                }

                // Cerrar el BufferedWriter
                bufferedWriter.close();

                // Mostrar un mensaje de éxito
                Alert ale = new Alert(Alert.AlertType.INFORMATION);
                ale.setHeaderText("Información");
                ale.setContentText("Elemento eliminado al final de la lista y del archivo!");
                ale.showAndWait();
                tabla.refresh();
            } catch (IOException e) {
                // Mostrar un mensaje de error en caso de excepción
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText("Error al eliminar");
                alerta.setContentText("Se produjo un error al eliminar el elemento del archivo.");
                alerta.showAndWait();
            }
        }
    }

}
