/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administración.sistema;

/**
 *
 * @author Alanm
 */
import javax.swing.*;

public class MenuJOption {
    public static String mostrarMenu() {
        return JOptionPane.showInputDialog(
            "MENÚ PRINCIPAL\n" +
            "1. Registrar paciente\n" +
            "2. Atender paciente\n" +
            "3. Entregar medicamento\n" +
            "4. Mostrar historial\n" +
            "5. Generar reportes\n" +
            "6. Salir\n" +
            "Seleccione una opción:"
        );
    }
}
