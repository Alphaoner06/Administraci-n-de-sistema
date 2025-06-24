/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administración.sistema;

/**
 *
 * @author Alanm
 */
public class PilaRecetas {
    NodoReceta cima;

    public void apilar(String receta) {
        NodoReceta nuevo = new NodoReceta(receta);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public String desapilar() {
        if (cima == null) return null;
        String receta = cima.receta;
        cima = cima.siguiente;
        return receta;
    }

    public void mostrar() {
        NodoReceta actual = cima;
        while (actual != null) {
            System.out.println(actual.receta);
            actual = actual.siguiente;
        }
    }
}
