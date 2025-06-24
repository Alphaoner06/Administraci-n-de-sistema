/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administración.sistema;

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
