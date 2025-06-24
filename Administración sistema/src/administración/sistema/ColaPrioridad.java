/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administración.sistema;

/**
 *
 * @author Alanm
 */
public class ColaPrioridad {
    NodoPaciente alta, media, baja;

    public void encolar(Paciente paciente) {
        NodoPaciente nuevo = new NodoPaciente(paciente);
        switch (paciente.urgencia.toLowerCase()) {
            case "alta":
                alta = insertar(alta, nuevo);
                break;
            case "media":
                media = insertar(media, nuevo);
                break;
            default:
                baja = insertar(baja, nuevo);
        }
    }

    private NodoPaciente insertar(NodoPaciente inicio, NodoPaciente nuevo) {
        if (inicio == null) return nuevo;
        NodoPaciente actual = inicio;
        while (actual.siguiente != null)
            actual = actual.siguiente;
        actual.siguiente = nuevo;
        return inicio;
    }

    public Paciente desencolar() {
        if (alta != null) {
            Paciente p = alta.paciente;
            alta = alta.siguiente;
            return p;
        } else if (media != null) {
            Paciente p = media.paciente;
            media = media.siguiente;
            return p;
        } else if (baja != null) {
            Paciente p = baja.paciente;
            baja = baja.siguiente;
            return p;
        } else {
            return null;
        }
    }

    public void mostrar() {
        System.out.println("Urgencia Alta:");
        mostrarCola(alta);
        System.out.println("Urgencia Media:");
        mostrarCola(media);
        System.out.println("Urgencia Baja:");
        mostrarCola(baja);
    }

    private void mostrarCola(NodoPaciente nodo) {
        while (nodo != null) {
            System.out.println(nodo.paciente);
            nodo = nodo.siguiente;
        }
    }
}

