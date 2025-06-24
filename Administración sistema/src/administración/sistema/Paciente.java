/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administración.sistema;

/**
 *
 * @author Alanm
 */
public class Paciente {
    String nombre;
    String id;
    int edad;
    String motivo;
    String urgencia; // Alta, Media, Baja
    String horaLlegada;

    public Paciente(String nombre, String id, int edad, String motivo, String urgencia, String horaLlegada) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.motivo = motivo;
        this.urgencia = urgencia;
        this.horaLlegada = horaLlegada;
    }

    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad + ", Urgencia: " + urgencia +
               ", Motivo: " + motivo + ", Hora: " + horaLlegada;
    }
}

