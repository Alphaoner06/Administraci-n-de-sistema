/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administración.sistema;

import javax.swing.*;

public class ControladorPrincipal {
    ColaPrioridad cola;
    ListaAtencion listaAtencion;
    PilaRecetas pilaRecetas;
    ArbolHistorial historial;

    public ControladorPrincipal() {
        cola = new ColaPrioridad();
        listaAtencion = new ListaAtencion();
        pilaRecetas = new PilaRecetas();
        historial = new ArbolHistorial();
    }

    public void iniciar() {
        String opcion;
        do {
            opcion = MenuJOption.mostrarMenu();
            switch (opcion) {
                case "1":
                    registrarPaciente();
                    break;
                case "2":
                    atenderPaciente();
                    break;
                case "3":
                    entregarMedicamento();
                    break;
                case "4":
                    mostrarHistorial();
                    break;
                case "5":
                    generarReportes();
                    break;
                case "6":
                    guardarDatos();
                    JOptionPane.showMessageDialog(null, "Sistema cerrado.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (!opcion.equals("6"));
    }

    private void registrarPaciente() {
        String nombre = JOptionPane.showInputDialog("Nombre:");
        String id = JOptionPane.showInputDialog("ID:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
        String motivo = JOptionPane.showInputDialog("Motivo:");
        String urgencia = JOptionPane.showInputDialog("Urgencia (Alta/Media/Baja):");
        String hora = JOptionPane.showInputDialog("Hora de llegada:");

        Paciente p = new Paciente(nombre, id, edad, motivo, urgencia, hora);
        cola.encolar(p);
        JOptionPane.showMessageDialog(null, "Paciente registrado.");
    }

    private void atenderPaciente() {
        Paciente paciente = cola.desencolar();
        if (paciente != null) {
            listaAtencion.agregar(paciente);
            historial.insertar(paciente);
            String receta = JOptionPane.showInputDialog("Ingrese receta del paciente:");
            pilaRecetas.apilar(receta);
            listaAtencion.eliminar(paciente.id);
            JOptionPane.showMessageDialog(null, "Paciente atendido y receta registrada.");
        } else {
            JOptionPane.showMessageDialog(null, "No hay pacientes en cola.");
        }
    }

    private void entregarMedicamento() {
        String receta = pilaRecetas.desapilar();
        if (receta != null) {
            JOptionPane.showMessageDialog(null, "Receta entregada: " + receta);
        } else {
            JOptionPane.showMessageDialog(null, "No hay recetas pendientes.");
        }
    }

    private void mostrarHistorial() {
        historial.inOrden();
        JOptionPane.showMessageDialog(null, "Historial mostrado por consola.");
    }

    private void generarReportes() {
        JOptionPane.showMessageDialog(null, "Funcionalidad de reportes por implementar.");
    }

    private void guardarDatos() {
        UtilidadesArchivo.guardarTexto("historial.txt", "Historial generado."); // Implementar real
    }
}
