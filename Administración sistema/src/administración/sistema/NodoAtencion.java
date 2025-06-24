/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administración.sistema;

/**
 *
 * @author Alanm
 */
public class NodoAtencion {
    Paciente paciente;
    NodoAtencion siguiente;

    public NodoAtencion(Paciente paciente) {
        this.paciente = paciente;
        this.siguiente = null;
    }
}
