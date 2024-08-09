package com.quadcode.simo.dao;

import com.quadcode.simo.model.Cliente;
import com.quadcode.simo.model.Paciente;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.mariadb.jdbc.Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao {
    private Connection connection;

    public PacienteDao() {
        try {
            this.connection = (Connection) DatabaseConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Paciente> obtenerPacientes() throws SQLException {
        List<Paciente> PacientesDetalles = new ArrayList<>();
        String sql = "select * from pacientes_detalles";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Paciente detalle = new Paciente();
                detalle.setDiagnosticoId(resultSet.getInt("DiagnosticoId"));
                detalle.setPacienteId(resultSet.getInt("PacienteId"));
                detalle.setNombreCliente(resultSet.getString("NombreCliente"));
                detalle.setNombrePaciente(resultSet.getString("NombrePaciente"));
                detalle.setMaterial(resultSet.getString("Material"));
                detalle.setTratamiento(resultSet.getString("Tratamiento"));
                detalle.setLente(resultSet.getString("Lente"));
                detalle.setEsferaDerecho(resultSet.getFloat("EsferaDerecho"));
                detalle.setCilindroDerecho(resultSet.getFloat("CilindroDerecho"));
                detalle.setAdicionDerecho(resultSet.getFloat("AdicionDerecho"));
                detalle.setDIDerecho(resultSet.getFloat("DIDerecho"));
                detalle.setEjeDerecho(resultSet.getFloat("EjeDerecho"));
                detalle.setEsferaIzquierdo(resultSet.getFloat("EsferaIzquierdo"));
                detalle.setCilindroIzquierdo(resultSet.getFloat("CilindroIzquierdo"));
                detalle.setAdicionIzquierdo(resultSet.getFloat("AdicionIzquierdo"));
                detalle.setDIIzquierdo(resultSet.getFloat("DIIzquierdo"));
                detalle.setEjeIzquierdo(resultSet.getFloat("EjeIzquierdo"));

                PacientesDetalles.add(detalle);
            }
        }
        return PacientesDetalles;
    }


    public List<TableColumn<Paciente, ?>> getColumnsFromDatabase() {
        List<TableColumn<Paciente, ?>> columns = new ArrayList<>();
        String sql = "SELECT * FROM pacientes_detalles";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet stm = stmt.executeQuery();

            ResultSetMetaData metaData = stm.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                TableColumn<Paciente, String> column = new TableColumn<>(columnName);
                column.setCellValueFactory(new PropertyValueFactory<>(columnName));
                System.out.println(columnName);
                columns.add(column);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return columns;
    }

    public List<Cliente> obtenerNombreClientes(String patron){
        List<Cliente> clientes = new ArrayList<>();
        String sql = "select obtener_nombre_clientes(?) AS nombres";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, patron + '%');
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    String nombresConcatenados = rs.getString("nombres");
                    if(nombresConcatenados != null && !nombresConcatenados.isEmpty()){
                        String[] nombresArray = nombresConcatenados.split(", ");
                        for(String nombre: nombresArray){
                            clientes.add(new Cliente(nombre));
                        }
                    }
                }
            }
        }catch (Exception e) { e.printStackTrace(); }

        return clientes;
    }

    public void insertarDiagnostico(String nombreCliente, String nombrePaciente, String material, String tratamiento, String lente,
                                    float esferaDerecho, float cilindroDerecho, float adicionDerecho, float diDerecho, float ejeDerecho,
                                    float esferaIzquierdo, float cilindroIzquierdo, float adicionIzquierdo, float diIzquierdo, float ejeIzquierdo) throws SQLException {
        String sql = "{CALL insertar_diagnostico(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (PreparedStatement statement = connection.prepareStatement(sql)){

            // Establecer parámetros para el procedimiento almacenado
            statement.setString(1, nombreCliente);
            statement.setString(2, nombrePaciente);
            statement.setString(3, material);
            statement.setString(4, tratamiento);
            statement.setString(5, lente);
            statement.setFloat(6, esferaDerecho);
            statement.setFloat(7, cilindroDerecho);
            statement.setFloat(8, adicionDerecho);
            statement.setFloat(9, diDerecho);
            statement.setFloat(10, ejeDerecho);
            statement.setFloat(11, esferaIzquierdo);
            statement.setFloat(12, cilindroIzquierdo);
            statement.setFloat(13, adicionIzquierdo);
            statement.setFloat(14, diIzquierdo);
            statement.setFloat(15, ejeIzquierdo);

            // Ejecutar el procedimiento almacenado
            statement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void modificarPaciente(Paciente paciente){
        String sql = "{CALL modificarPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)}";
        try(CallableStatement stmt = connection.prepareCall(sql)){
            stmt.setInt(1, paciente.getPacienteId());
            stmt.setString(2, paciente.getNombreCliente());
            stmt.setString(3, paciente.getNombrePaciente());
            stmt.setString(4, paciente.getMaterial());
            stmt.setString(5, paciente.getTratamiento());
            stmt.setString(6, paciente.getLente());
            stmt.setFloat(7, paciente.getEsferaDerecho());
            stmt.setFloat(8, paciente.getCilindroDerecho());
            stmt.setFloat(9, paciente.getAdicionDerecho());
            stmt.setFloat(10, paciente.getDIDerecho());
            stmt.setFloat(11, paciente.getEjeDerecho());
            stmt.setFloat(12, paciente.getEsferaIzquierdo());
            stmt.setFloat(13, paciente.getCilindroIzquierdo());
            stmt.setFloat(14, paciente.getAdicionIzquierdo());
            stmt.setFloat(15, paciente.getDIIzquierdo());
            stmt.setFloat(16, paciente.getEjeIzquierdo());

            stmt.executeQuery();
        }catch (Exception e){
            e.printStackTrace();

        }
    }

}
