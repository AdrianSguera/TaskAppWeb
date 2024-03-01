package com.ceica.taskappweb.modelos;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class ModeloBase {
    private static final String CONFIG_FILE = "config.properties";
    protected static String URL;
    protected static String USUARIO;
    protected static String PASSWORD;

    static {
        cargarConfiguracion();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void cargarConfiguracion() {
        Properties propiedades = new Properties();
        try (InputStream entrada = ModeloBase.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            propiedades.load(entrada);
            URL = propiedades.getProperty("db.url");
            USUARIO = propiedades.getProperty("db.usuario");
            PASSWORD = propiedades.getProperty("db.pass");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract String getNombreTabla();

    protected abstract String getVista();

    public boolean insertar(String consulta, Object... parametros) {
        consulta = "insert into " + getNombreTabla() + " " + consulta;
        return ejecutarQuery(consulta, parametros);
    }

    public boolean modificar(String consulta, Object... parametros) {
        consulta = "update " + getNombreTabla() + " set " + consulta;
        return ejecutarQuery(consulta, parametros);
    }

    public boolean eliminar(String consulta, Object... parametros) {
        consulta = "delete from " + getNombreTabla() + " where " + consulta;
        return ejecutarQuery(consulta, parametros);
    }

    protected List<Object> leerTodos() {
        List<Object> objectList = new ArrayList<>();
        String sql = String.format("select * from %s", getVista());
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()){
                Object[] objects = new Object[metaData.getColumnCount()];
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    objects[i] = resultSet.getObject(i+1);
                }
                objectList.add(objects);
            }
            return objectList;
        } catch (SQLException e) {
            return objectList;
        }
    }

    private boolean ejecutarQuery(String sql, Object... parametros) {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parametros.length; i++)
                preparedStatement.setObject(i + 1, parametros[i]);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            return false;
        }
    }
}