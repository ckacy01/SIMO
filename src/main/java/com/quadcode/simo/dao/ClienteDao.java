package com.quadcode.simo.dao;


import com.quadcode.simo.model.ClienteDetalle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.mariadb.jdbc.Connection;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
        String sql = "{CALL obtener_clientes_detalle()}";

        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                ClienteDetalle detalle = new ClienteDetalle();
                detalle.setId(resultSet.getInt("clienteId"));
                detalle.setNombre(resultSet.getString("clienteNombre"));
                detalle.setTelefono(resultSet.getString("clienteTelefono"));
                detalle.setNombreFam(resultSet.getString("referidoNombre"));
                detalle.setTelefonoFam(resultSet.getString("referidoTelefono"));
                detalle.setRelacionConCliente(resultSet.getString("referidoRelacion"));
                detalle.setCalle1(resultSet.getString("direccionClienteCalleNumero"));
                detalle.setCodigoPostal1(resultSet.getString("direccionClienteCodigoPostal"));
                detalle.setEntreCalles1(resultSet.getString("direccionClienteEntreCalles"));
                detalle.setColonia1(resultSet.getString("direccionClienteColonia"));
                detalle.setReferencia(resultSet.getString("direccionClienteReferencia"));
                detalle.setCalle2(resultSet.getString("direccionReferidoCalleNumero"));
                detalle.setCodigoPostal2(resultSet.getString("direccionReferidoCodigoPostal"));
                detalle.setEntreCalles2(resultSet.getString("direccionReferidoEntreCalles"));
                detalle.setColonia2(resultSet.getString("direccionReferidoColonia"));
                detalle.setReferencia2(resultSet.getString("direccionReferidoReferencia"));

                clientesDetalles.add(detalle);
            }
        }
        return clientesDetalles;
    }

    public List<TableColumn<ClienteDetalle, ?>> getColumnsFromDatabase() {
        List<TableColumn<ClienteDetalle, ?>> columns = new ArrayList<>();
        String sql = "{CALL obtener_clientes_detalle()}";

        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
            ResultSet stm = callableStatement.executeQuery();

            ResultSetMetaData metaData = stm.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                TableColumn<ClienteDetalle, String> column = new TableColumn<>(columnName);
                column.setCellValueFactory(new PropertyValueFactory<>(columnName));
                System.out.println(columnName);
                columns.add(column);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return columns;
    }

}
