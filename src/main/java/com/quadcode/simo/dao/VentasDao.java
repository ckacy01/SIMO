package com.quadcode.simo.dao;

import com.quadcode.simo.controller.VentasController;
import com.quadcode.simo.model.Lentes;
import com.quadcode.simo.model.Mica;
import com.quadcode.simo.model.Venta;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.mariadb.jdbc.Connection;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class VentasDao {
    private Connection connection;

    public VentasDao() {
        try {
            this.connection = (Connection) DatabaseConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Venta> obtenerVentas(){
        List<Venta> ventas = new ArrayList<Venta>();
        String sql = "select * from obtener_venta_detalles";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Venta venta = new Venta();
                venta.setId(rs.getInt("Id"));
                venta.setNombreCliente(rs.getString("NombreCliente"));
                venta.setNombrePaciente(rs.getString("NombrePaciente"));
                venta.setNombreProducto(rs.getString("NombreProducto"));
                venta.setNombreMica(rs.getString("NombreMica"));
                venta.setTinte(rs.getString("Tinte"));
                venta.setMetodoPago(rs.getString("MetodoPago"));
                venta.setPeriodoAbonos(rs.getString("PeriodoAbonos"));
                venta.setSaldoActual(rs.getFloat("SaldoActual"));
                venta.setCostoTotal(rs.getFloat("CostoTotal"));
                venta.setEnganche(rs.getFloat("Enganche"));
                venta.setFechaVenta(rs.getDate("FechaVenta"));
                ventas.add(venta);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ventas;
    }

    public List<TableColumn<Venta, ? >> getColumnsFromDatabase(){
        List<TableColumn<Venta, ?>> columns = new ArrayList<>();
        String sql = "SELECT * FROM obtener_venta_detalles";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet stm = stmt.executeQuery();

            ResultSetMetaData metaData = stm.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                TableColumn<Venta, String> column = new TableColumn<>(columnName);
                column.setCellValueFactory(new PropertyValueFactory<>(columnName));
                columns.add(column);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return columns;
    }

    public int obtenerNAbonos(int id){
        int abonos = 0;
        String sql = "select COUNT(*) As Abonos from abonos where VentaId = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                abonos = rs.getInt("Abonos");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return abonos;
    }

    public Lentes obtenerPrecioProducto(String NombreProducto){
        Lentes lentes = new Lentes();
        String sql = "select PrecioContado, PrecioCredito from productos where Nombre = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, NombreProducto);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                lentes.setPrecioContado(rs.getFloat("PrecioContado"));
                lentes.setPrecioCredito(rs.getFloat("PrecioCredito"));
                return lentes;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Mica obtenerPrecioMica(String NombreMica){
        Mica mica = new Mica();
        String sql = "select Contado, Credito from micas where Tipo = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, NombreMica);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                mica.setContado(rs.getFloat("Contado"));
                mica.setCredito(rs.getFloat("Credito"));
                return mica;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<String> obtenerNombresProductos(){
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT Nombre FROM productos";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                nombres.add(rs.getString("Nombre"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return nombres;
    }

    public List<String> obtenerNombresMicas(){
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT Tipo FROM micas";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                nombres.add(rs.getString("Tipo"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return nombres;
    }

    public float obtenerSumaAbonos(int id){
        float suma = 0;
        String sql = "select SUM(Monto) As Abonos from abonos where VentaId = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                suma = rs.getFloat("Abonos");
                return suma;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public void modificarVenta(Venta venta){
        String sql = "{CALL actualizar_venta(?,?,?,?,?,?,?,?,?,?)}";
        try(CallableStatement stmt = connection.prepareCall(sql)){
            stmt.setInt(1, venta.getId());
            stmt.setString(2, venta.getNombreProducto());
            stmt.setFloat(3,venta.getEnganche());
            stmt.setString(4, venta.getNombreMica());
            stmt.setString(5, venta.getMetodoPago());
            stmt.setString(6, venta.getTinte());
            stmt.setDate(7,venta.getFechaVenta());
            stmt.setFloat(8,venta.getCostoTotal());
            stmt.setFloat(9,venta.getSaldoActual());
            stmt.setString(10,venta.getPeriodoAbonos());
            stmt.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public int obtenerStockProducto(String producto ){
        int stock = 0;
        String sql = "select Stock from productos where Nombre = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,producto);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                stock = rs.getInt("Stock");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return stock;
    }

    public String obtenerProductoActual(int idVenta) {
        String productoActual = null;
        String query = "SELECT  NombreProducto FROM obtener_venta_detalles WHERE Id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, idVenta);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                productoActual = rs.getString("NombreProducto");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productoActual;
    }
}



