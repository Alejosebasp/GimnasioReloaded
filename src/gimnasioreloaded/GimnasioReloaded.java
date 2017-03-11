/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasioreloaded;

import java.io.*;
import java.util.*;

/**
 *
 * @author alejosebasp
 * @since viernes 10 de marzo 2017
 * @version 1.0
 */
public class GimnasioReloaded {

    public static class Estudiante{ //Clase estudiante manejara los datos de cada estudiante.
        private String nombre;
        private String apellido;
        private int cedula;
        private int nro_entradas; // para contar las entradas al gimnasio
        private int nro_casillero; //para saber que casillero tiene assignado
        private int nro_cubiculo; //para conocer el cubiculo asignado
        
        //Constructor clase estudiante
        public Estudiante(String nombre, String apellido, int cedula){
            this.nombre = nombre;
            this.apellido = apellido;
            this.cedula = cedula;
            this.nro_entradas = 0;
        }
        
        //gets and sets de cada atributo de la clase estudiante.
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public int getCedula() {
            return cedula;
        }

        public void setCedula(int cedula) {
            this.cedula = cedula;
        }

        public int getNro_entradas() {
            return nro_entradas;
        }

        public void setNro_entradas(int nro_entradas) {
            this.nro_entradas = nro_entradas;
        }

        public int getNro_casillero() {
            return nro_casillero;
        }

        public void setNro_casillero(int nro_casillero) {
            this.nro_casillero = nro_casillero;
        }

        public int getNro_cubiculo() {
            return nro_cubiculo;
        }

        public void setNro_cubiculo(int nro_cubiculo) {
            this.nro_cubiculo = nro_cubiculo;
        }
        
        //Aumenta el numero de entradas de cada estudiante y valida si es mayor 
        //o menor que 3 si si, enviia false, en caso contrario true. 
        public boolean sumarEntrada(){
            nro_entradas++;
            return nro_entradas <= 3;
        }
    }
    
    /**
     * 
     * Cubiculo: Clase que maneja los cubiculos
     * @param cas_llenos: numero de casileros llenos en el cubiculo.
     * @param num_casilleros: numero de casilleros totales del cubiculo, sin importar 
     * si estan llenos o vacios
     * @param estudiantes: arreglo de tamaño num_casilleros, 
     */
    public static class Cubiculo{
        private int cas_llenos;
        private Estudiante[] estudiantes;
        private int num_casilleros;
        
        public Cubiculo(int num_casilleros){
            this.num_casilleros = num_casilleros;
            this.estudiantes = new Estudiante[num_casilleros];
            cas_llenos = 0;
        }
        
        /**
         * 
         * @param nombre 
         * @param apellido
         * @param cedula 
         * agrega a un estudiante en la primera posicion vacia que encuentre 
         * y aumenta el numero de cassilleros llenos
         */
        public void agregarEstudiante(Estudiante estudiante, int num_cubiculo){
            for (int i = 0; i < num_casilleros; i++) {
                if (estudiantes[i] == null) {
                    estudiantes[i] = estudiante;
                    estudiante.setNro_cubiculo(num_cubiculo); //edita el cubiculo
                    estudiante.setNro_casillero(i); //edita el casillero asignado
                    cas_llenos++; 
                    return;
                }
            }
        }
        
        /**
         * 
         * @param cedula 
         * elimina el estudiante con la cedula dada, si
         */
        public void eliminarEstudiante(int cedula){
            for (Estudiante estudiante : estudiantes) {
                if (estudiante.cedula == cedula) {
                    String nombre = estudiante.nombre;
                    estudiante = null; 
                    cas_llenos--; //disminuye el numero de casilleros llenos
                    System.out.println(nombre);
                    return;
                }
            }
        }
        
        //retorna el numero de casilleros llenos.
        public int casillerosLlenos(){
            return cas_llenos;
        }
        
        /**
         * 
         * @return true si esta lleno el cubiculo y false si hay casilleros vacios.
         */
        public boolean lleno(){
            return cas_llenos == num_casilleros;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String entrada = bf.readLine();
        int num_casos = Integer.parseInt(entrada);
        
        for (int i = 0; i < num_casos; i++) {
            
            //Lista para guardar todos los estudiantes ingresados al sistema.
            ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
            //Lectura del numero de cubiculos.
            entrada = bf.readLine();
            int num_cubiculos = Integer.parseInt(entrada);
            Cubiculo[] cubiculos = new Cubiculo[num_cubiculos];
            
            //se reciben los casilleros por cada cubiculo
            entrada = bf.readLine();
            String[] casilleros = entrada.split(" ");
            
            for (int j = 0; j < num_cubiculos; j++) {
                //Se crean los cubiculos con la cantidad de casilleros de cada uno.
                cubiculos[j] = new Cubiculo(Integer.parseInt(casilleros[j]));
            }
            
            //se guardan los numeros de comandos a ejecutar.
            entrada = bf.readLine();
            int num_comandos = Integer.parseInt(entrada);
            
            for (int j = 0; j < num_comandos; j++) {
                
                String nombre = null, apellido = null;
                int cedula = 0;
                
                entrada = bf.readLine();
                String[] comando = entrada.split(" ");
                
                if (comando[0].equals("ingresar")) { //se evalua si el comando es ingresar
                    
                    boolean cond1 = false, cond2 = false, cond3 = false;
                    
                    //se evalua las restrigciones de tamaño de caracteres para cada dato.
                    if (comando[1].length() <= 50 && comando[1].length() > 0) { 
                        nombre = comando[1];
                        cond1 = true;
                    }
                    if (comando[2].length() > 0 && comando[2].length() <= 50) {
                        apellido = comando[2];
                        cond2 = true;
                    }
                    if (comando[3].length() > 0 && comando[3].length() <= 10) {
                        cedula = Integer.parseInt(comando[3]);
                        cond3 = true;
                    }
                    
                    //se evalua si se cumplen las condiciones de longitud de caracteres
                    if (cond1 && cond2 && cond3) {
                        
                        //Se reviza si los casilleros estan llenos
                        int lleno = 0;
                        for (Cubiculo cubiculo : cubiculos) {
                            if (cubiculo.lleno()) {
                                lleno++;
                            }
                        }
                        
                        if (lleno == num_cubiculos) {
                            System.out.println("limite alcanzado");
                            continue;
                        }
                        
                        //se buscara el cubiculo con menor carga
                        double cargaTotal = 1;
                        for (Cubiculo cubiculo : cubiculos) {
                            //se calcula la carga de cada cubiculo
                            double cargaCubiculo = ((double)cubiculo.cas_llenos/(double)cubiculo.num_casilleros); //divison esta dando 0
                                                        
                            //si esa carga es menor a la carga total actual, se convierte en la nueva carga
                            if (cargaCubiculo < cargaTotal) {
                                cargaTotal = cargaCubiculo;
                            }
                        }

                        //se busca cuando la carga de cada cubiculo es igual a la carga total.
                        for (int k = 0; k < num_cubiculos; k++) {
                            double cargaCubiiculo = ((double)cubiculos[k].cas_llenos / (double)cubiculos[k].num_casilleros);

                            //si son iguales, se agrega el estudiante al cubiculo y casillero libre
                            //con menor valor y se rompe el ciclo
                            if (cargaCubiiculo == cargaTotal) {
                                //Si la lista esta vacia crea un estudiante y le asigna un casillero
                                if (listaEstudiantes.isEmpty()) {
                                    Estudiante e = new Estudiante(nombre, apellido, cedula);
                                    cubiculos[k].agregarEstudiante(e, k);
                                    boolean x = e.sumarEntrada(); //suma una entrada al estudiante e
                                    listaEstudiantes.add(e);
                                    System.out.println((e.getNro_cubiculo()+1) + " " + (e.getNro_casillero()+1));
                                    
                                }else{ //si no esta vacia se busca si el estudiante ya esta en la base de datos y se le asigna casillero.
                                    boolean condicion = true;
                                    for (Estudiante e : listaEstudiantes) {
                                        if (e.getCedula() == cedula) {
                                            if (e.sumarEntrada()) { //si el estudiante tiene menos de 4 ingresos se le asigna casillero
                                                cubiculos[k].agregarEstudiante(e, k);
                                                System.out.println((e.getNro_cubiculo()+1) + " " + (e.getNro_casillero()+1));
                                                condicion = false;
                                                break; 
                                            }else{ //si no tiene menos de 4 ingresos se le niega el acceso
                                                System.out.println("ingreso denegado");
                                                condicion = false;
                                                break;
                                            }
                                            
                                        }
                                    }
                                    //Si el estudiante no esta (condicion = false) entonces se agrega y se le asigna casillero
                                    if (condicion) {
                                        Estudiante estudiante = new Estudiante(nombre, apellido, cedula);
                                        cubiculos[k].agregarEstudiante(estudiante, k);
                                        boolean x = estudiante.sumarEntrada();//suma una entrada al estudiante
                                        listaEstudiantes.add(estudiante);
                                        System.out.println((estudiante.getNro_cubiculo()+1) + " " + (estudiante.getNro_casillero()+1));
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                //Se evalua si la operacion es salir y luego si se cumple con el tamaño de los datos.
                else if(comando[0].equals("salir")){ //se evalua si el comando es salir
                    if (comando[1].length() > 0 && comando[1].length() <= 10) {
                        cedula = Integer.parseInt(comando[1]);
                    }
                }
            }
        }
    }
    
}
