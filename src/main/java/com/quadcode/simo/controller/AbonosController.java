package com.quadcode.simo.controller;

import com.quadcode.simo.dao.AbonosDao;
import com.quadcode.simo.dao.VentasDao;
import com.quadcode.simo.model.Abonos;
import com.quadcode.simo.model.Venta;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.checkerframework.checker.units.qual.N;

import java.net.URL;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class AbonosController extends NavBarController implements Initializable  {
    @FXML
    private TableView <Abonos> tbAbonos;
    @FXML
    private TextField fldNombreC;
    @FXML
    private TextField fldNombreP;
    @FXML
    private Label lblSaldo;
    @FXML
    private Label lblNVenta;
    @FXML
    private Label lblNAbonos;
    @FXML
    private TextField fldMonto;
    @FXML
    private DatePicker dateBoxVenta;
    @FXML
    private Label lblTotal;

    private String NombreC;
    private String NombreP;
    private Float Deuda;
    private Float Total;
    private int IdVenta;
    private VentasDao ventaDao;
    private AbonosDao abonosDao;
    private int AbonoId;
    public boolean  modificar = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        abonosDao = new AbonosDao();
        ventaDao = new VentasDao();
        List<TableColumn<Abonos, ?>> abonos = abonosDao.obtenerColumnas();
        tbAbonos.getColumns().setAll(abonos);
        eventosTablas();
    }

    public void setDatos(String NombreC, String NombreP, Float Deuda, Float Total, int IdVenta) {
        this.NombreC = NombreC;
        this.NombreP = NombreP;
        this.Deuda = Deuda;
        this.Total = Total;
        this.IdVenta = IdVenta;
        setDatosEnPantalla();
        mostrarAbonos();
        // ListenerDeuda();
        }

    public void setDatosEnPantalla() {
        fldNombreC.setText(NombreC);
        fldNombreP.setText(NombreP);
        lblNVenta.setText(Integer.toString(IdVenta));
        ventaDao = new VentasDao();
        lblNAbonos.setText(Integer.toString(ventaDao.obtenerNAbonos(IdVenta)));

        // Dar formato de dinero a los labels
        Float deuda = abonosDao.obtenerDeuda(IdVenta);
        NumberFormat currecy = NumberFormat.getCurrencyInstance(Locale.US);
        String deudaFormateada = currecy.format(deuda);
        String costoFormateada = currecy.format(Total);

        lblTotal.setText(costoFormateada);
        lblSaldo.setText(deudaFormateada);
    }

    public void mostrarAbonos() {
        try{
            List<Abonos> abonos = abonosDao.ObtenerAbonos(IdVenta);
            ObservableList<Abonos> observableList = FXCollections.observableArrayList(abonos);
            tbAbonos.setItems(observableList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void eventosTablas(){
        tbAbonos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->  {
           if(newValue != null){
               fldMonto.setText(Float.toString(newValue.getMonto()));
               dateBoxVenta.setValue(newValue.getFechaAbono().toLocalDate());
               AbonoId = newValue.getId();
           }
        });
    }

    /*private void ListenersDeuda(){
        fldMonto.textProperty().addListener((observable) -> {
            float deuda = 0.0f;
            deuda = this.Deuda;
            deuda = deuda - Float.parseFloat(fldMonto.getText());
            NumberFormat currecy = NumberFormat.getCurrencyInstance(Locale.US);
            String deudaFormateada = currecy.format(deuda);

            lblSaldo.setText(deudaFormateada);
        });
    }*/

    @FXML
    public void InsertarAbono(){
        if(Deuda <= 0){
            showAlert(Alert.AlertType.ERROR, "Error al insertar abono!", "Error al insertar abono! no se puede insertar un abono cuando ya no hay adeudos");
            return;
        }
        if(Float.parseFloat(fldMonto.getText()) > Deuda){
            showAlert(Alert.AlertType.ERROR, "Error al insertar abono!", "Error al insertar abono! El monto del abono no puede ser mayor al de la deuda");
            return;
        }
        try {
            Abonos abono = new Abonos();
            abono.setMonto(Float.parseFloat(fldMonto.getText()));
            abono.setVentaId(Integer.parseInt(lblNVenta.getText()));
            abono.setFechaAbono(Date.valueOf(dateBoxVenta.getValue()));
            abonosDao.insertarAbono(abono);
            showAlert(Alert.AlertType.INFORMATION, "Agregar abono!", "Abono Agregado exitosamente");
            mostrarAbonos();
            setDatosEnPantalla();
        }catch (Exception e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error al insertar abono!", "Error al insertar abono!");
        }
    }

    @FXML
    public void modificarAbono(){
        if(Deuda <= 0){
            showAlert(Alert.AlertType.ERROR, "Error al insertar abono!", "Error al insertar abono! no se puede insertar un abono cuando ya no hay adeudos");
            return;
        }
        if(Float.parseFloat(fldMonto.getText()) > Deuda){
            showAlert(Alert.AlertType.ERROR, "Error al insertar abono!", "Error al insertar abono! El monto del abono no puede ser mayor al de la deuda");
            return;
        }
        try{
            Abonos abono = new Abonos();
            System.out.println(AbonoId);
            abono.setMonto(Float.parseFloat(fldMonto.getText()));
            abono.setId(AbonoId);
            abono.setFechaAbono(Date.valueOf(dateBoxVenta.getValue()));
            Alert alert = showAlertConfirmation("Modificar Abono!", "Al modificar el abono cambiara la deuda del cliente, ¿Seguro que desea modificar?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                abonosDao.modificarAbono(abono);
            }
            mostrarAbonos();
            setDatosEnPantalla();
            modificar = true;
        }catch (Exception e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error al editar abono!", "Error al editar abono!");
        }
    }


    public Alert showAlertConfirmation( String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }

    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void Limpiar(){
        fldMonto.setText("");
        dateBoxVenta.setValue(null);
    }

    private void validarNumeros(){
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*\\.?[0-9]*")) {
                return change;
            }
            return null;
        };
        fldMonto.setTextFormatter(new TextFormatter<>(filter));
    }

}

