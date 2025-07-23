/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administración.sistema;
import javax.swing.JOptionPane;

/**
 *
 * @author Alanm
 */
public class ArbolHistorial {
    NodoHistorial raiz;

    public void insertar(Paciente paciente) {
        raiz = insertarRec(raiz, paciente);
    }

    private NodoHistorial insertarRec(NodoHistorial nodo, Paciente paciente) {
        if (nodo == null) return new NodoHistorial(paciente);
        if (paciente.id.compareTo(nodo.paciente.id) < 0)
            nodo.izquierda = insertarRec(nodo.izquierda, paciente);
        else
            nodo.derecha = insertarRec(nodo.derecha, paciente);
        return nodo;
    }
    
    public void mostrarHistorial() {
    if (raiz == null) {
        JOptionPane.showMessageDialog(null, "No hay pacientes en el historial.");
        return;
    }

    StringBuilder historial = new StringBuilder();
    construirTextoHistorial(raiz, historial);

        JOptionPane.showMessageDialog(null, historial.toString(), "Historial Clínico", JOptionPane.INFORMATION_MESSAGE);
    }

    private void construirTextoHistorial(NodoHistorial nodo, StringBuilder historial) {
        if (nodo != null) {
            construirTextoHistorial(nodo.izquierda, historial);
            historial.append("ID: ").append(nodo.paciente.id).append("\n");
            historial.append("Nombre: ").append(nodo.paciente.nombre).append("\n");
            historial.append("Edad: ").append(nodo.paciente.edad).append("\n");
            historial.append("Motivo: ").append(nodo.paciente.motivo).append("\n");
            historial.append("Urgencia: ").append(nodo.paciente.urgencia).append("\n");
            historial.append("Hora de llegada: ").append(nodo.paciente.horaLlegada).append("\n");
            historial.append("----------------------\n");
            construirTextoHistorial(nodo.derecha, historial);
        }
}


    public void inOrden() {
        inOrdenRec(raiz);
    }

    private void inOrdenRec(NodoHistorial nodo) {
        if (nodo != null) {
            inOrdenRec(nodo.izquierda);
            System.out.println(nodo.paciente);
            inOrdenRec(nodo.derecha);
        }
    }
}
