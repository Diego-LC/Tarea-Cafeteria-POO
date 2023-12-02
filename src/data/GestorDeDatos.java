package data;

import model.*;
import java.io.*;
import java.util.Locale;

public class GestorDeDatos {
    public Cafeteria leerArchivoCafeteria(Cafeteria cafeteria, String direccionArchivoCafeteria, String direccionArchivoCafes) {
        String textoArchivo = "";
        try {
            File archivo = new File(direccionArchivoCafeteria);
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            //Lee cada línea del archivo hasta que la línea sea nula
            while((textoArchivo = br.readLine()) != null){
                String[] data = textoArchivo.split(",");
                cafeteria.setNombreCafeteria(data[0]);
                cafeteria.setDireccion(data[1]);
                cafeteria.setRedesSociales(new RedesSociales[] {
                        RedesSociales.valueOf(data[2].toUpperCase()),
                        RedesSociales.valueOf(data[3].toUpperCase()),
                        RedesSociales.valueOf(data[4].toUpperCase())
                });
                leerArchivoCafes(cafeteria, direccionArchivoCafes);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("erro al leer el archivo " + direccionArchivoCafes+ " : " + e.getMessage());
        }
        return cafeteria;
    }

    protected void leerArchivoCafes(Cafeteria cafeteria, String direccionArchivo) {
        String textoArchivo = "";
        try {
            File archivo = new File(direccionArchivo);
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            //Lee cada línea del archivo hasta que la línea sea nula
            while((textoArchivo = br.readLine()) != null){
                IngredientesOpcionales ingredientes;
                String[] data = textoArchivo.split(",");
                if (!data[4].equalsIgnoreCase("null")){
                    ingredientes = IngredientesOpcionales.valueOf(data[4].toUpperCase());
                }else{
                    ingredientes = null;
                }
                cafeteria.agregarNuevoCafe(
                        data[0],
                        Integer.parseInt(data[1]),
                        Integer.parseInt(data[2]),
                        Tamaño.valueOf(data[3].toUpperCase()),
                        ingredientes);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo " +direccionArchivo+ " : " +e.getMessage());
        }
    }

    public boolean registrarDato(Object objeto, String direccionArchivo) {
        boolean lineaVacia=false;
        try {
            File file = new File(direccionArchivo);
            if (!file.exists()) {
                file.createNewFile();
                lineaVacia=true;
            }
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            //Si el documento no es nuevo y ya tiene registrados datos, se debe crear un salto de línea
            if(!lineaVacia && file.length() > 0){
                bw.newLine();
            }
            bw.write(objeto.toString());
            System.out.println("Guardado: " + objeto.toString());
            bw.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error al ingresar dato, favor contactar con administrador: " + e.getMessage());
            return false;
        }
    }

    public void borrarDatosArchivo(String direccionArchivo){
        try {
            File file = new File(direccionArchivo);
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            BufferedWriter buff = new BufferedWriter(writer);
            buff.close();
        }catch (Exception e) {
            System.out.println("Error al borrar datos del archivo: " + e.getMessage());
        }
    }
}