/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ismael.p81_ismael;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ismael
 */
public class UsuarioDAO implements IUsuario {

    private Connection con = null;

    public UsuarioDAO() {
        con = Conexion.getInstance();
    }

    @Override
    public List<UsuarioVO> getAll() throws SQLException {
        List<UsuarioVO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try ( Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from usuarios");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                UsuarioVO u = new UsuarioVO();
                // Recogemos los datos de la persona, guardamos en un objeto
                u.setPk(res.getInt("codUsuario"));
                u.setFechaNacimiento(res.getDate("fecNac").toLocalDate());
                u.setNombre(res.getString("nomUsuario"));
                u.setApellidos(res.getString("apellidosUsuario"));

                //Añadimos el objeto a la lista
                lista.add(u);
            }
        }

        return lista;
    }

    @Override
    public UsuarioVO findByPk(int pk) throws SQLException {

        ResultSet res = null;
        UsuarioVO usuario = new UsuarioVO();

        String sql = "select * from usuarios where codUsuario=?";

        try ( PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setInt(1, pk);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.next()) {
                // Recogemos los datos de la persona, guardamos en un objeto
                usuario.setPk(res.getInt("codUsuario"));
                usuario.setFechaNacimiento(res.getDate("fecNac").toLocalDate());
                usuario.setNombre(res.getString("nomUsuario"));
                usuario.setApellidos(res.getString("apellidosUsuario"));

                return usuario;
            }

            return null;
        }
    }

    @Override
    public int insertUsuario(UsuarioVO usuarios) throws SQLException {

        int numFilas = 0;
        String sql = "insert into usuarios values (?,?,?,?)";

        if (findByPk(usuarios.getPk()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try ( PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setInt(1, usuarios.getPk());
                prest.setDate(2, Date.valueOf(usuarios.getFechaNacimiento()));
                prest.setString(3, usuarios.getNombre());
                prest.setString(4, usuarios.getApellidos());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }

    }

    @Override
    public int insertUsuario(List<UsuarioVO> lista) throws SQLException {
        int numFilas = 0;

        for (UsuarioVO tmp : lista) {
            numFilas += insertUsuario(tmp);
        }

        return numFilas;
    }

    @Override
    public int deleteUsuario() throws SQLException {

        String sql = "delete from usuarios";

        int nfilas = 0;

        // Preparamos el borrado de datos  mediante un Statement
        // No hay parámetros en la sentencia SQL
        try ( Statement st = con.createStatement()) {
            // Ejecución de la sentencia
            nfilas = st.executeUpdate(sql);
        }

        // El borrado se realizó con éxito, devolvemos filas afectadas
        return nfilas;

    }

    @Override
    public int deleteUsuario(UsuarioVO usuario) throws SQLException {
        int numFilas = 0;

        String sql = "delete from usuarios where codUsuario = ?";

        // Sentencia parametrizada
        try ( PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setInt(1, usuario.getPk());
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int updateUsuario(int pk, UsuarioVO nuevosDatos) throws SQLException {

        int numFilas = 0;
        String sql = "update usuarios set fecNac = ?, nomUsuario = ?, apellidosUsuario = ? where codUsuario=?";

        if (findByPk(pk) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try ( PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setDate(1, Date.valueOf(nuevosDatos.getFechaNacimiento()));
                prest.setString(2, nuevosDatos.getNombre());
                prest.setString(3, nuevosDatos.getApellidos());
                prest.setInt(4, pk);

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    public int cambiarNombres(String newName, String oldName) throws SQLException {

        int res = 0;
        // Dos ?, uno para newName y otro para oldName

        String sql = "{call cambiar_nombres (?,?)}";

        // Preparamos la llamada al procedimiento almacenado
        try ( CallableStatement call = con.prepareCall(sql)) {
            // Establecemos parámetros a pasar al procedimiento
            call.setString(1, newName);
            call.setString(2, oldName);
            // Ejecutamos el procedimiento
            res = call.executeUpdate();

        }
        return res;
    }
}
