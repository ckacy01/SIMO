package com.quadcode.simo.dao;

import com.quadcode.simo.model.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.mariadb.jdbc.Connection;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class NuevaVentaDao extends VentasDao{
    private Connection connection;

    public NuevaVentaDao() {
        try {
            this.connection = (Connection) DatabaseConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PacienteSearch> obtenerNombrePacientes(String patron){
        List<PacienteSearch> pacientes = new ArrayList<>();
        String sql = "select obtener_nombres_pacientes(?) AS nombres";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, patron + '%');
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    String nombresConcatenados = rs.getString("nombres");
                    if(nombresConcatenados != null && !nombresConcatenados.isEmpty()){
                        String[] nombresArray = nombresConcatenados.split(", ");
                        for(String nombre: nombresArray){
                            pacientes.add(new PacienteSearch(nombre));
                        }
                    }
                }
            }
        }catch (Exception e) { e.printStackTrace(); }

        return pacientes;
    }

    public List<TableColumn<ProductosyMicas, ?>> getColums(String producto, String mica){
        List<TableColumn<ProductosyMicas, ?>> columns = new ArrayList<>();
        String sql = "{CALL seleccionar_mica_productos(?,?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, producto);
            stmt.setString(2, mica);
            ResultSet stm = stmt.executeQuery();

            ResultSetMetaData metaData = stm.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                javafx.scene.control.TableColumn<ProductosyMicas, String> column = new TableColumn<>(columnName);
                column.setCellValueFactory(new PropertyValueFactory<>(columnName));
                columns.add(column);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return columns;
    }

    public List<ProductosyMicas> obtenerProductosMicas(String producto, String mica){
        List<ProductosyMicas> productosyMicas = new ArrayList<>();
        String sql = "{CALL seleccionar_mica_productos(?,?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, producto);
            stmt.setString(2, mica);
            ResultSet stm = stmt.executeQuery();
            while (stm.next()){
                ProductosyMicas productoMicas = new ProductosyMicas();
                productoMicas.setId(stm.getInt("Id"));
                productoMicas.setNombre(stm.getString("Nombre"));
                productoMicas.setPrecioContado(stm.getFloat("PrecioContado"));
                productoMicas.setPrecioCredito(stm.getFloat("PrecioCredito"));
                productosyMicas.add(productoMicas);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return productosyMicas;
    }

    public void insertarVenta(NuevaVenta nuevaVenta) {
        CallableStatement stmt = null;

        try {
            // Prepara la llamada al procedimiento almacenado
            String sql = "{CALL insertar_venta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            stmt = connection.prepareCall(sql);

            // Establece los parámetros basados en el objeto NuevaVenta
            stmt.setString(1, nuevaVenta.getNombrePaciente());
            stmt.setDate(2, nuevaVenta.getFechaVenta());
            stmt.setFloat(3, nuevaVenta.getCostoTotal());
            stmt.setFloat(4, nuevaVenta.getSaldoActual());
            stmt.setString(5, nuevaVenta.getPeriodoAbonos());
            stmt.setString(6, nuevaVenta.getNombreProducto());
            stmt.setString(7, nuevaVenta.getNombreMica());
            stmt.setFloat(8, nuevaVenta.getEnganche());
            stmt.setString(9, nuevaVenta.getMetodoPago());
            stmt.setString(10, nuevaVenta.getTinte());

            // Ejecuta el procedimiento almacenado
            stmt.execute();

            System.out.println("Venta insertada exitosamente!");

        } catch (Exception e) {
            e.printStackTrace();
            // Manejar errores según sea necesario
        }
    }
}
