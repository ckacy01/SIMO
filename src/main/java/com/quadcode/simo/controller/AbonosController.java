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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        abonosDao = new AbonosDao();
        ventaDao = new VentasDao();
        List<TableColumn<Abonos, ?>> abonos = abonosDao.obtenerColumnas();
        tbAbonos.getColumns().setAll(abonos);
        mostrarAbonos();
        eventosTablas();
    }

    public void setDatos(String NombreC, String NombreP, Float Deuda, Float Total, int IdVenta) {
        this.NombreC = NombreC;
        this.NombreP = NombreP;
        this.Deuda = Deuda;
        this.Total = Total;
        this.IdVenta = IdVenta;
        setDatosEnPantalla();
    }

    public void setDatosEnPantalla() {
        fldNombreC.setText(NombreC);
        fldNombreP.setText(NombreP);
        lblSaldo.setText(Float.toString(abonosDao.obtenerDeuda(IdVenta)));
        lblNVenta.setText(Integer.toString(IdVenta));
        lblTotal.setText(Float.toString(Total));
        ventaDao = new VentasDao();
        lblNAbonos.setText(Integer.toString(ventaDao.obtenerNAbonos(IdVenta)));
    }

    public void mostrarAbonos() {
        try{
            List<Abonos> abonos = abonosDao.ObtenerAbonos();
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

    @FXML
    public void InsertarAbono(){
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

}

