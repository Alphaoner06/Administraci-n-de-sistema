/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administración.sistema;

/**
 *
 * @author Alanm
 */
public class NodoReceta {
    String receta;
    NodoReceta siguiente;

    public NodoReceta(String receta) {
        this.receta = receta;
        this.siguiente = null;
    }
}
