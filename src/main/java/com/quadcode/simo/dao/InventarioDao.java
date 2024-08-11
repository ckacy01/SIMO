package com.quadcode.simo.dao;

import com.quadcode.simo.model.ClienteDetalle;
import com.quadcode.simo.model.Lentes;
import com.quadcode.simo.model.Mica;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.mariadb.jdbc.Connection;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class InventarioDao {
    private Connection connection;

    public InventarioDao() {
        try {
            this.connection = (Connection) DatabaseConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Lentes> obetenerLentes(){
        List<Lentes> l = new ArrayList<>();
        String sql = "select * from obtener_productos";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lentes lentes = new Lentes();
                lentes.setId(rs.getInt("Id"));
                lentes.setNombre(rs.getString("Nombre"));
                lentes.setStock(rs.getInt("Stock"));
                lentes.setPrecioContado(rs.getFloat("PrecioContado"));
                lentes.setPrecioCredito(rs.getFloat("PrecioCredito"));
                lentes.setDescripcion(rs.getString("Descripcion"));
                l.add(lentes);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public List<TableColumn<Lentes, ?>> getColumnsFromDatabaseLentes() {
        List<TableColumn<Lentes, ?>> columns = new ArrayList<>();
        String sql = "SELECT * FROM obtener_productos";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet stm = stmt.executeQuery();

            ResultSetMetaData metaData = stm.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                TableColumn<Lentes, String> column = new TableColumn<>(columnName);
                column.setCellValueFactory(new PropertyValueFactory<>(columnName));
                columns.add(column);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return columns;
    }

    public List<Mica> obetenerMicas(){
        List<Mica> mica  = new ArrayList<>();
        String sql = "select * from obtener_micas";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mica micas = new Mica();
                micas.setId(rs.getInt("Id"));
                micas.setTipo(rs.getString("Tipo"));
                micas.setContado(rs.getFloat("Contado"));
                micas.setCredito(rs.getFloat("Credito"));
                mica.add(micas);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return mica;
    }

    public List<TableColumn<Mica, ?>> getColumnsFromDatabaseMicas() {
        List<TableColumn<Mica, ?>> columns = new ArrayList<>();
        String sql = "SELECT * FROM obtener_micas";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet stm = stmt.executeQuery();

            ResultSetMetaData metaData = stm.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                TableColumn<Mica, String> column = new TableColumn<>(columnName);
                column.setCellValueFactory(new PropertyValueFactory<>(columnName));
                columns.add(column);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return columns;
    }

    public void insertarLente(Lentes lentes){
        String sql = "{CALL insertar_productos(?,?,?,?,?)}";
        try(CallableStatement stmt = connection.prepareCall(sql)){

            stmt.setString(1, lentes.getNombre());
            stmt.setInt(2, lentes.getStock());
            stmt.setFloat(3, lentes.getPrecioContado());
            stmt.setFloat(4, lentes.getPrecioCredito());
            stmt.setString(5, lentes.getDescripcion());
            stmt.execute();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void insertarMica(Mica micas){
        String sql = "{CALL insertar_mica(?,?,?)}";
        try(CallableStatement stmt = connection.prepareCall(sql)){
            stmt.setString(1, micas.getTipo());
            stmt.setFloat(2, micas.getContado());
            stmt.setFloat(3, micas.getCredito());
            stmt.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void modificarLente(Lentes lente){
        String sql = "{CALL modificar_productos(?,?,?,?,?,?)}";
        try(CallableStatement stmt = connection.prepareCall(sql)){
            stmt.setInt(1, lente.getId());
            stmt.setString(2, lente.getNombre());
            stmt.setInt(3, lente.getStock());
            stmt.setFloat(4, lente.getPrecioContado());
            stmt.setFloat(5, lente.getPrecioCredito());
            stmt.setString(6, lente.getDescripcion());
            stmt.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void modificarMica(Mica mica){
        String sql = "{CALL modificar_mica(?,?,?,?)}";
        try(CallableStatement stmt = connection.prepareCall(sql)){
            stmt.setInt(1, mica.getId());
            stmt.setString(2, mica.getTipo());
            stmt.setFloat(3, mica.getContado());
            stmt.setFloat(4, mica.getCredito());
            stmt.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    public List<Lentes> buscarLentePorNombre(String nombre){
        List<Lentes> lentes = new ArrayList<>();
        String sql = "{SELECT * from productos where nombre = ?}";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
                stmt.setString(1, nombre + "%");
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    lentes.add(new Lentes());
                }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lentes;
    }
     */






}
