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
        historial.mostrarHistorial();
    }

    private void generarReportes() {
        int total = historialPacientes(historial.raiz);
        Paciente mayorEspera = pacienteMayorEspera();
        tipoUrgencia(historial.raiz);
        String medicamento = medicamentoMasEntregado();
        
        String reporte =  "Reporte general\n" +
                          "Total de pacientes atendidos " + total + "\n\n"+
                          "Mediamentos más entregados " + medicamento + "\n\n";
        
        
        if(mayorEspera != null){
            reporte += "Paciente con mayor tiempo de espera:\n" +
                       "Nombre: " + mayorEspera.nombre + "\n" +
                       "Hora de llegada: " + mayorEspera.horaLlegada + "\n" +
                       "Urgencia: " + mayorEspera.urgencia + "\n";
        }else{
            reporte += "No hay pacientes en cola.";
        }
        JOptionPane.showMessageDialog(null, reporte);
    }
    
    //REPORTES 
    //----- Total pacientes atendidos
    private int historialPacientes(NodoHistorial nodo){
    if(nodo == null) return 0;   
        return 1 + historialPacientes(nodo.izquierda)+ historialPacientes(nodo.derecha);
    }
    
    //----- Paciente con mayor tiempo de espera
    private Paciente pacienteMayorEspera(){
        if( cola.alta != null) return cola.alta.paciente;
        if( cola.media != null) return cola.media.paciente;
        if( cola.baja != null) return cola.baja.paciente;
        
        return null;
    }
    
    //----- Cantidad de pacientes por tipo de urgencia.
    private void tipoUrgencia(NodoHistorial nodo){
        int alta = 0;
        int media = 0;
        int baja = 0;
        
        alta = contarAlta(nodo);
        media = contarMedia(nodo);
        baja = contarBaja(nodo);
        
        String texto = "Cantidad de pacientes por urgencia:\n";
        texto += "Alta: "+alta + "\n";
        texto += "Media: "+media + "\n";
        texto += "Baja: "+baja + "\n";
        
        JOptionPane.showMessageDialog(null, texto);
       
    }
     //----- Medicamentos más entregados.
    private String medicamentoMasEntregado(){
        NodoReceta actual = pilaRecetas.cima;
        String frecuente = "";
        int mayor =0;
        
        while(actual != null){
            String nombre = actual.receta;
            int conteo= 0;
            NodoReceta recorrer = pilaRecetas.cima;
            
            while(recorrer !=  null){
                if(recorrer.receta.equalsIgnoreCase(nombre)){
                    conteo++;
                }
                recorrer = recorrer.siguiente;
            }
            if(conteo> mayor){
                mayor= conteo;
                frecuente = nombre;
            }
            actual = actual.siguiente;
        }
        if(frecuente.equals("")){
            return "No hay";
        }else{
            return frecuente + mayor + "veces";
        }
    }
    
        private int contarAlta(NodoHistorial nodo){
            if (nodo == null) return 0;
            
            int contar = 0;
            if(nodo.paciente.urgencia.equalsIgnoreCase("alta"))contar =1;
            return contar + contarAlta(nodo.izquierda)+contarAlta(nodo.derecha);
        }
        private int contarMedia(NodoHistorial nodo){
            if (nodo == null) return 0;
            
            int contar = 0;
            if(nodo.paciente.urgencia.equalsIgnoreCase("media"))contar =1;
            return contar + contarMedia(nodo.izquierda)+contarMedia(nodo.derecha);
        }
        private int contarBaja(NodoHistorial nodo){
            if (nodo == null) return 0;
            
            int contar = 0;
            if(nodo.paciente.urgencia.equalsIgnoreCase("baja"))contar =1;
            return contar + contarBaja(nodo.izquierda)+contarBaja(nodo.derecha);
        }
    
    //Metodos de Guardar y Cargar

    public void cargarDatos(){
        cargarPacientesArchivo("cola.txt" , "cola");
        cargarPacientesArchivo("historial.txt" , "historial");
        cargarPacientesArchivo("atencion.txt" , "atencion");
        
    }  
     private void cargarPacientesArchivo(String archivo, String destino){
        String texto = UtilidadesArchivo.leerArchivo(archivo);
        if(!texto.equals("")){
            String [] linea = texto.split("\n");
            for(int i = 0; i<linea.length; i++){
                Paciente p = convertirPaciente(linea[i]);
                if (p == null) continue;
                
                if (destino.equals("cola")){
                    cola.encolar(p);
                }else if (destino.equals("historial")){
                    historial.insertar(p);
                } else if ( destino.equals("atencion")){
                    listaAtencion.agregar(p);
                }
        }
            
     }
    }
    private void guardarDatos(){
        String ColaTexto = "";
        NodoPaciente actualCola;
        
        actualCola = cola.alta;
        while(actualCola != null ){
            ColaTexto += PacientesATexto(actualCola.paciente) + "\n";
            actualCola = actualCola.siguiente;
        }
        
        actualCola= cola.media;
        while(actualCola != null ){
            ColaTexto += PacientesATexto(actualCola.paciente) + "\n";
            actualCola = actualCola.siguiente;
        }
        actualCola= cola.baja;
        while(actualCola != null ){
            ColaTexto += PacientesATexto(actualCola.paciente) + "\n";
            actualCola = actualCola.siguiente;
        }
        UtilidadesArchivo.guardarTexto("cola.txt", ColaTexto, false);
        
        //Guardar el historial
        String textoHistorial ="";
        textoHistorial = recorrerArbol(historial.raiz, textoHistorial);
        UtilidadesArchivo.guardarTexto("historial.txt", textoHistorial, false);
        
        //Guardar lista de Atencion 
        guardarAtencion("atencion.txt");
    
    }
     private void guardarAtencion(String archivo){
        String texto = "";
        NodoAtencion actual = listaAtencion.inicio;
        
        while(actual!= null){
            texto += PacientesATexto(actual.paciente)+"\n";
            actual = actual.siguiente;
        }
        UtilidadesArchivo.guardarTexto(archivo, texto, false);
    }
    
    //Metodos Auxiliares de Archivo
     
    private Paciente convertirPaciente(String espacio){
          if (espacio == null || espacio.trim().equals("") || !espacio.contains(",")) {
        return null;
    }
        String nombre = "", id ="", edadTxt = "", motivo = "", urgencia = "", hora = "";
        int campo = 0;
       String dato = " ";
       
        for(int i = 0; i<espacio.length(); i++){
            char c = espacio.charAt(i);
            if(c == ','){
                if(campo == 0) nombre = dato;
                else if(campo == 1) id = dato;
                else if(campo == 2) edadTxt = dato;
                else if(campo == 3) motivo = dato;
                else if(campo == 4) urgencia = dato;
                campo++;
                dato = "";
                
            }else{
                dato += c;
            }  
        }
        hora = dato;
        
        // Verificación  para que no haya datos vacíos 
    if (nombre.equals("") || id.equals("") || edadTxt.equals("")) {
        return null;
    }
        
        int edad = 0;
         try {
        edad = Integer.parseInt(edadTxt);
    } catch (NumberFormatException e) {
        return null;
    }
        
        return new Paciente(nombre, id, edad, motivo, urgencia, hora);
                
    }
    
    private String PacientesATexto (Paciente p){
        return p.nombre +","+ p.id + "," + p.edad + "," + p.motivo + "," + p.urgencia + "," + p.horaLlegada;
    }
    private String recorrerArbol(NodoHistorial nodo, String texto){
        if(nodo != null){
            texto = recorrerArbol(nodo.izquierda, texto);
            texto += PacientesATexto(nodo.paciente) + "\n";
            texto = recorrerArbol(nodo.derecha, texto);
                    
        }
        return texto;
    }
    
}
