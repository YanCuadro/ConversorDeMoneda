package modelos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ConnectException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Componentes {

    // Función que produce la salida y utiliza la función conversión de la clase búsqueda
    public void procesoOutput(String base_code, String target_code) {

        try {
            // Instancia de Scanner/ clase búsqueda/ valor Double/ clase Moneda
            Scanner leerV = new Scanner(System.in);
            Busqueda busqueda = new Busqueda();
            double valor;
            Moneda moneda;

            // Recibir el valor, pasar los argumentos al método búsqueda y produciendo la salida
            System.out.println("Ingrese el valor que desea convertir:");
            valor = leerV.nextDouble();
            moneda = busqueda.conversion(base_code, target_code, valor);
            String salida = "El valor "+valor+" ["+base_code+"] corresponde al valor final de =>>> "+
                    moneda.getConversion_result()+" ["+target_code+"] ";
            System.out.println(salida);
            System.out.println(" ");

            // Obteniendo la fecha y uniéndola a la salida y pasarla como argumento al método
            // guardarHistorial
            String fecha = "---> "+ LocalDate.now();
            guardarHistorial(salida+fecha);

        } catch (InputMismatchException e) {
            System.out.println("ERROR!!!, Introduzca un valor numérico, si es decimal utilice una coma(,)");
        } catch (ConnectException e) {
            //throw new RuntimeException(e.getMessage());
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void guardarHistorial(String linea) throws IOException {

        // Instancia de la lista hist/ FileWriter/ File/ Scanner
        List<String> hist = new ArrayList<>();
        FileWriter historial;

        File arch = new File("Historial.txt");
        Scanner leerH;


        if (arch.exists()) {

            // Si el archivo existe lo lee y extrae y añade todas sus líneas a la lista hist
            leerH = new Scanner(arch);
            while (leerH.hasNextLine()) {
                hist.add(leerH.nextLine());

            }
            // añade la última búsqueda a la lista
            hist.add(linea);

            // Vuelve a crear el archivo y lo reemplaza y se le agrega todas las búsquedas de
            // la lista hist
            historial = new FileWriter("Historial.txt");
            for (String s : hist) {
                historial.write(s + "\n");

            }

            historial.close();

        } else {
            // Si no existe el archivo lo crea y agrega la búsqueda que se realizó
            historial = new FileWriter("Historial.txt");
            historial.write(linea);
            historial.close();
        }
    }

    public void mostrarHistorial() {


        try {
            // Instancia de File/ Scanner
            File arch = new File("Historial.txt");
            Scanner leerH = new Scanner(arch);

            // Lee el archivo y lo muestra en pantalla
            while (leerH.hasNextLine()) {
                System.out.println(leerH.nextLine());

            }
        } catch (FileNotFoundException e) {
            System.out.println("No hay Historial");
        }


    }
}
