package com.quadcode.simo.dao;

import com.quadcode.simo.model.Abonos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.mariadb.jdbc.Connection;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class AbonosDao {
    private Connection connection;

    public AbonosDao() {
        try {
            this.connection = (Connection) DatabaseConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Abonos> ObtenerAbonos(int ventaid) {
        List<Abonos> abonos = new ArrayList<>();
        String sql = "SELECT * FROM abonos where VentaId = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, ventaid);
            System.out.println(ventaid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Abonos abono = new Abonos();
                abono.setId(rs.getInt("Id"));
                abono.setVentaId(rs.getInt("VentaId"));
                abono.setFechaAbono(rs.getDate("FechaAbono"));
                abono.setMonto(rs.getFloat("Monto"));
                abonos.add(abono);
                System.out.println(abono.getId());

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return abonos;
    }

    public List<TableColumn<Abonos, ?>> obtenerColumnas(){
        List<TableColumn<Abonos, ?>> columns = new ArrayList<>();
        String sql = "SELECT * FROM abonos";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for(int i = 1; i <= columnCount; i++){
                String columnName = rsmd.getColumnName(i);
                TableColumn<Abonos, String> column = new TableColumn<>(columnName);
                column.setCellValueFactory(new PropertyValueFactory<>(columnName));
                columns.add(column);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(columns);
        return columns;
    }

    public void insertarAbono(Abonos abono) {
        String sql = "{CALL insertar_abono(?,?,?)}";
        try(CallableStatement stmt = connection.prepareCall(sql)){
            stmt.setInt(1, abono.getVentaId());
            stmt.setDate(2, abono.getFechaAbono());
            stmt.setFloat(3, abono.getMonto());
            stmt.execute();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float obtenerDeuda(int id){
        float deuda = 0;
        String sql = "select SaldoActual from ventas where Id=?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                deuda = rs.getFloat("SaldoActual");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return deuda;
    }

    public void modificarAbono(Abonos abono) {
        String sql = "{CALL modificar_abono(?,?,?)}";
        try(CallableStatement stmt = connection.prepareCall(sql) ){
            stmt.setInt(1, abono.getId());
            stmt.setFloat(2, abono.getMonto());
            stmt.setDate(3, abono.getFechaAbono());
            stmt.execute();
            System.out.println(abono.getId());
            System.out.println(abono.getMonto());
            System.out.println(abono.getFechaAbono());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

