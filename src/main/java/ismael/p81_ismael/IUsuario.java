/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ismael.p81_ismael;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ismael
 */
public interface IUsuario {

    
    // Método para obtener todos los registros de la tabla
    List<UsuarioVO> getAll() throws SQLException;
    
    // Méodo para obtener un registro a partir de la PK
    UsuarioVO findByPk(int pk) throws SQLException;
    
    // Método para insertar un registro
    int insertUsuario (UsuarioVO persona) throws SQLException;
    
    // Método para insertar varios registros
    int insertUsuario (List<UsuarioVO> lista) throws SQLException;
    
    // Método para borrar una persona
    int deleteUsuario (UsuarioVO u) throws SQLException;
    
    // Método para borrar toda la tabla
    int deleteUsuario() throws SQLException;
    
    // Método para modificar una persona. Se modifica a la persona que tenga esa 'pk'
    // con los nuevos datos que traiga la persona 'nuevosDatos'
    int updateUsuario (int pk, UsuarioVO nuevosDatos) throws SQLException;
    
}

