package DataCredito;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Yonathan
 */
public class Principal implements Serializable{
    
    public BufferedReader entradaDatos=new BufferedReader(new InputStreamReader (System.in));
    private HashMap<Integer,Persona> personas;
    
    public Principal() {
        personas= new HashMap();
    }
    
    public void menu(){
        try{
            String opcion="";
            int idCliente;
            do{
                leerArchivo();

                System.out.println("+ - - - - - - - - - - - - - - - - - - - - +");
                System.out.println("|        Bienvenido a DataCredito.        |");
                System.out.println("+ - - - - - - - - - - - - - - - - - - - - +");
                System.out.println("|                                         |");
                System.out.println("|   1. Agregar Usuario.                   |");
                System.out.println("|   2. Agregar Reporte.                   |");
                System.out.println("|   3. Buscar Usuario.                    |");
                System.out.println("|   4. Eliminar Reporte.                  |");
                System.out.println("|   0. Salir.                             |");
                System.out.println("+ - - - - - - - - - - - - - - - - - - - - +");
                opcion = entradaDatos.readLine();
                if(opcion.equals("1")){
                    System.out.println("Datos del Cliente");
                    System.out.println("Nombre");
                    String nombre=entradaDatos.readLine();
                    System.out.println("Apellido");
                    String apellido=entradaDatos.readLine();
                    System.out.println("Identificación");
                    idCliente=Integer.parseInt(entradaDatos.readLine());
                    agregarPersona(idCliente,apellido,nombre);
                    guardarArchivo();
                }else if(opcion.equals("2")){
                    System.out.println("Identificacion del Cliente: ");
                    idCliente=Integer.parseInt(entradaDatos.readLine());
                    agregarReporte(idCliente);
                    guardarArchivo();
                }else if(opcion.equals("3")){
                    leerArchivo();
                    System.out.println("Identificacion del Cliente");
                    idCliente=Integer.parseInt(entradaDatos.readLine());
                    verReportes(idCliente);
                }else if(opcion.equals("4")){
                    System.out.println("Identificación del Ciente: ");
                    idCliente=Integer.parseInt(entradaDatos.readLine());
                    eliminarReporte(idCliente);
                    guardarArchivo();
                }else if(opcion.equals("0")){
                    System.exit(0);
                }
                else{
                    System.out.println("Opcion no Válida...");
                }
            }while(!(opcion.equals("0")));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void agregarPersona(int id, String apellido, String nombre){
        Persona cliente=new Persona(id,apellido,nombre);
        if(personas.containsKey(id)){
            System.out.println("El cliente ya está registrado en el Sistema.");
        }else{
            personas.put(id, cliente);
        }
    }
    public void eliminarReporte(int idCliente){
        try{
            if(personas.containsKey(idCliente)){
                if(personas.get(idCliente).reporte.isEmpty()){
                    System.out.println("El cliente no tiene reportes en el Sistema");
                }else{
                    for (Reporte rep : personas.get(idCliente).getReporte().values()) {
                        if(rep.getEstado().equals("-")){
                            personas.get(idCliente).getReporte().remove(rep.getCodigo());
                            System.out.println("Reportes con codigo "+rep.getCodigo()+" Eliminado.");
                            eliminarReporte(idCliente);
                        }
                    }
                }
            }else{
                System.out.println("El cliente no está registrado en el Sistema.");
            }
        }catch(Exception e){
            
        }
    }
    public void agregarReporte(int id){
        try{
            if(personas.containsKey(id)){
                System.out.println("Datos del Reporte");
                System.out.println("Codigo");
                int codigo=Integer.parseInt(entradaDatos.readLine());
                System.out.println("Nombre");
                String nombre=entradaDatos.readLine();
                System.out.println("Descripcion");
                String descripcion=entradaDatos.readLine();
                System.out.println("Estado (+ ó -)");
                String tipo=entradaDatos.readLine();
                System.out.println("Valor: ");
                Double valor=Double.parseDouble(entradaDatos.readLine());
                Reporte rep=new Reporte(codigo,nombre,descripcion,tipo,valor);
                personas.get(id).getReporte().put(codigo, rep);
            }else{
                System.out.println("El cliente no está registrado en el Sistema.");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void verReportes(int id){
        int i=1;
        if(personas.containsKey(id)){
            if(personas.get(id).reporte.isEmpty()){
                System.out.println("El cliente no tiene reportes en el Sistema");
            }else{
                System.out.println("Reportes de: "+personas.get(id).getNombre()+" "+personas.get(id).getApellido());
                for (Reporte r : personas.get(id).getReporte().values()) {
                    System.out.println("Datos del reporte "+i);
                    System.out.println("\tCodigo: "+r.getCodigo());
                    System.out.println("\tTitulo: "+r.getNombre());
                    System.out.println("\tDescripción: "+r.getDescripcion());
                    System.out.println("\tEstado: "+r.getEstado());
                    System.out.println("\tMonto: "+r.getValor());
                    i++;
                }
            }
        }else
            System.out.println("El cliente no está registrado en el Sistema");
    }
    
    public void leerArchivo() throws FileNotFoundException, IOException{
        File f;
        f=new File("Clientes.txt");
        ObjectInputStream entrada=new ObjectInputStream(new FileInputStream(f));
        try{
            personas=(HashMap)entrada.readObject();
        }catch(Exception ex){
            System.out.println("Error al leer el archivo");
            System.err.println(ex.getMessage());
        }finally{
            entrada.close();
        }
        
    }
    public void guardarArchivo() throws FileNotFoundException, IOException{
        File f;
        f=new File("Clientes.txt");
        ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream(f));
        try{
            salida.writeObject(personas);
        }catch(Exception ex){
            System.out.println("Error al Guardar el archivo");
            ex.getMessage();
        }finally{
            salida.close();
        }
    }
    
}
