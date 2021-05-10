/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ismael.p81_ismael;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ismael
 */
public class Programa {

    public static void main(String[] args) {

        UsuarioDAO daoUsuario = new UsuarioDAO();
        List<UsuarioVO> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new UsuarioVO(1, LocalDate.of(1996, 10, 6), "Pedro", "Chacon"));
        listaUsuarios.add(new UsuarioVO(2, LocalDate.of(1997, 6, 6), "Ismael", "Melgar"));
        listaUsuarios.add(new UsuarioVO(3, LocalDate.of(1997, 6, 6), "Juanjo", "Perez"));

        try {

            System.out.println("Nº personas insertadas " + daoUsuario.insertUsuario(listaUsuarios));
            System.out.println("-----------------------------------------");
            System.out.println("Comprobamos en una nueva lista que se recogen los datos desde la tabla.");
            List<UsuarioVO> nuevaLista = daoUsuario.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
            nuevaLista.forEach(System.out::println);
            System.out.println("-----------------------------------------");
            System.out.println("Persona con primary key 1: ");
            System.out.println(daoUsuario.findByPk(1));
            System.out.println("-----------------------------------------");
            System.out.println("Se va a borrar la persona con pk 3");
            System.out.println("Nº personas borradas "
                    + daoUsuario.deleteUsuario((new UsuarioVO(3, LocalDate.of(1997, 6, 6), "Juanjo", "Perez"))));
            System.out.println("-----------------------------------------");
            nuevaLista = daoUsuario.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D despues de borrar una persona -------------");
            nuevaLista.forEach(System.out::println);
            System.out.println("-----------------------------------------");
            System.out.println("Modificación de la persona con pk 2");
            System.out.println("Nº Personas modificadas "
                    + daoUsuario.updateUsuario(2, new UsuarioVO(2, LocalDate.of(1997, 6, 6), "NuevoNombre", "Melgar")));
            System.out.println("-----------------------------------------");
            nuevaLista = daoUsuario.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D despues de modificar una persona -------------");
            nuevaLista.forEach(System.out::println);
            System.out.println("-----------------------------------------");
//            System.out.println("Ejecución del procedimiento almacenado");
//            System.out.println("Se cambia María Weston por Felipe Román");
//            System.out.println("Nombres cambiados " + daoUsuario.cambiarNombres("Pedro", "Francisco"));
//            System.out.println("-----------------------------------------");
//            nuevaLista = daoUsuario.getAll();
//            System.out.println("-------- Lista con datos recogidos desde la B.D despues de ejecutar proced. -------------");
//            nuevaLista.forEach(System.out::println);
//            System.out.println("-----------------------------------------");
        } catch (SQLException sqle) {
            System.out.println("No se ha podido realizar la operación:");
            System.out.println(sqle.getMessage());
        }
        System.out.println();
        listaUsuarios.forEach(System.out::println);

    }

}
