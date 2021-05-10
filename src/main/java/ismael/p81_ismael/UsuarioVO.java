/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ismael.p81_ismael;

import java.time.LocalDate;

/**
 *
 * @author ismael
 */
public class UsuarioVO {

    private int pk;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String Apellidos;

    public UsuarioVO(int pk, LocalDate fechaNacimiento, String nombre, String Apellidos) {
        this.pk = pk;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.Apellidos = Apellidos;
    }

    public UsuarioVO() {

    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "UsuarioVO{" + "pk=" + pk + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", Apellidos=" + Apellidos + '}';
    }



}
