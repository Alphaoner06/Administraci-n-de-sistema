/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administración.sistema;

/**
 *
 * @author Alanm
 */
public class ListaAtencion {
    NodoAtencion inicio;

    public void agregar(Paciente paciente) {
        NodoAtencion nuevo = new NodoAtencion(paciente);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            NodoAtencion actual = inicio;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public void eliminar(String id) {
        if (inicio == null) return;
        if (inicio.paciente.id.equals(id)) {
            inicio = inicio.siguiente;
            return;
        }
        NodoAtencion actual = inicio;
        while (actual.siguiente != null && !actual.siguiente.paciente.id.equals(id)) {
            actual = actual.siguiente;
        }
        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
        }
    }

    public void mostrar() {
        NodoAtencion actual = inicio;
        while (actual != null) {
            System.out.println(actual.paciente);
            actual = actual.siguiente;
        }
    }
}
