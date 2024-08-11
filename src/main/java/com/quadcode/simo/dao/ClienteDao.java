package com.quadcode.simo.dao;


import com.quadcode.simo.model.ClienteDetalle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.mariadb.jdbc.Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    private Connection connection;

    public ClienteDao() {
        try {
            this.connection = (Connection) DatabaseConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ClienteDetalle> obtenerClientesDetalle() throws SQLException {
        List<ClienteDetalle> clientesDetalles = new ArrayList<>();
        String sql = "SELECT * FROM obtener_clientes_detalle";

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

                while (resultSet.next()) {
                    ClienteDetalle detalle = new ClienteDetalle();
                    detalle.setClienteId(resultSet.getInt("clienteId"));
                    detalle.setClienteNombre(resultSet.getString("clienteNombre"));
                    detalle.setClienteTelefono(resultSet.getString("clienteTelefono"));
                    detalle.setReferidoNombre(resultSet.getString("referidoNombre"));
                    detalle.setReferidoTelefono(resultSet.getString("referidoTelefono"));
                    detalle.setReferidoRelacion(resultSet.getString("referidoRelacion"));
                    detalle.setDireccionClienteCalleNumero(resultSet.getString("direccionClienteCalleNumero"));
                    detalle.setDireccionClienteCodigoPostal(resultSet.getString("direccionClienteCodigoPostal"));
                    detalle.setDireccionClienteEntreCalles(resultSet.getString("direccionClienteEntreCalles"));
                    detalle.setDireccionClienteColonia(resultSet.getString("direccionClienteColonia"));
                    detalle.setDireccionClienteReferencia(resultSet.getString("direccionClienteReferencia"));
                    detalle.setDireccionReferidoCalleNumero(resultSet.getString("direccionReferidoCalleNumero"));
                    detalle.setDireccionReferidoCodigoPostal(resultSet.getString("direccionReferidoCodigoPostal"));
                    detalle.setDireccionReferidoEntreCalles(resultSet.getString("direccionReferidoEntreCalles"));
                    detalle.setDireccionReferidoColonia(resultSet.getString("direccionReferidoColonia"));
                    detalle.setDireccionReferidoReferencia(resultSet.getString("direccionReferidoReferencia"));

                    clientesDetalles.add(detalle);
                }

        }
        return clientesDetalles;
    }

    public List<TableColumn<ClienteDetalle, ?>> getColumnsFromDatabase() {
        List<TableColumn<ClienteDetalle, ?>> columns = new ArrayList<>();
        String sql = "SELECT * FROM obtener_clientes_detalle";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet stm = stmt.executeQuery();

            ResultSetMetaData metaData = stm.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                TableColumn<ClienteDetalle, String> column = new TableColumn<>(columnName);
                column.setCellValueFactory(new PropertyValueFactory<>(columnName));
                columns.add(column);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return columns;
    }

    public void insertarClienteConReferido(ClienteDetalle clienteDetalle) {
        String sql = "{CALL InsertarClienteConReferido(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)){
            stmt.setString(1, clienteDetalle.getClienteNombre());
            stmt.setString(2, clienteDetalle.getClienteTelefono());
            stmt.setString(3, clienteDetalle.getDireccionClienteCalleNumero());
            stmt.setString(4, clienteDetalle.getDireccionClienteCodigoPostal());
            stmt.setString(5, clienteDetalle.getDireccionClienteEntreCalles());
            stmt.setString(6, clienteDetalle.getDireccionClienteColonia());
            stmt.setString(7, clienteDetalle.getDireccionClienteReferencia());
            stmt.setString(8, clienteDetalle.getReferidoNombre());
            stmt.setString(9, clienteDetalle.getReferidoTelefono());
            stmt.setString(10, clienteDetalle.getReferidoRelacion());
            stmt.setString(11, clienteDetalle.getDireccionReferidoCalleNumero());
            stmt.setString(12, clienteDetalle.getDireccionReferidoCodigoPostal());
            stmt.setString(13, clienteDetalle.getDireccionReferidoEntreCalles());
            stmt.setString(14, clienteDetalle.getDireccionReferidoColonia());
            stmt.setString(15, clienteDetalle.getDireccionReferidoReferencia());

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modificarCliente(ClienteDetalle clienteDetalle) {
        String sql = "{CALL modificarCliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try(CallableStatement callableStatement = connection.prepareCall(sql)){
            // Establece los parámetros del procedimiento almacenado
            callableStatement.setInt(1, clienteDetalle.getClienteId());
            callableStatement.setString(2, clienteDetalle.getClienteNombre());
            callableStatement.setString(3, clienteDetalle.getClienteTelefono());
            callableStatement.setString(4, clienteDetalle.getDireccionClienteCalleNumero());
            callableStatement.setString(5, clienteDetalle.getDireccionClienteCodigoPostal());
            callableStatement.setString(6, clienteDetalle.getDireccionClienteEntreCalles());
            callableStatement.setString(7, clienteDetalle.getDireccionClienteColonia());
            callableStatement.setString(8, clienteDetalle.getDireccionClienteReferencia());
            callableStatement.setString(9, clienteDetalle.getReferidoNombre());
            callableStatement.setString(10, clienteDetalle.getReferidoTelefono());
            callableStatement.setString(11, clienteDetalle.getReferidoRelacion());
            callableStatement.setString(12, clienteDetalle.getDireccionReferidoCalleNumero());
            callableStatement.setString(13, clienteDetalle.getDireccionReferidoCodigoPostal());
            callableStatement.setString(14, clienteDetalle.getDireccionReferidoEntreCalles());
            callableStatement.setString(15, clienteDetalle.getDireccionReferidoColonia());
            callableStatement.setString(16, clienteDetalle.getDireccionReferidoReferencia());

            // Ejecuta el procedimiento almacenado
            callableStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
