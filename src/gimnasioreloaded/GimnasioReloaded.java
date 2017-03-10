/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasioreloaded;

/**
 *
 * @author alejosebasp
 */
public class GimnasioReloaded {

    public class Estudiante{ //Clase estudiante manejara los datos de cada estudiante.
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
            
            if (nro_entradas > 3) {
                return false;
            } else {
                return true;
            }
        }
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
