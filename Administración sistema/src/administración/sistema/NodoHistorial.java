/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administración.sistema;

/**
 *
 * @author Alanm
 */
public class NodoHistorial {
    Paciente paciente;
    NodoHistorial izquierda, derecha;

    public NodoHistorial(Paciente paciente) {
        this.paciente = paciente;
    }
}

