import modelos.Componentes;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Componentes componente = new Componentes();
        Scanner leer = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("""
                    *********************************************
                    Sea bienvenido/a al Conversor de Moneda =]

                    1) Dólar =>> Peso argentino
                    2) Peso argentino =>> Dólar
                    3) Dólar =>> Real brasileño
                    4) Real brasileño =>> Dólar
                    5) Dólar =>> Peso colombiano
                    6) Peso colombiano =>> Dólar
                    7) Historial de búsqueda
                    8) Salir
                    Elija una opción válida:
                    *********************************************
                    """);

            try {
                opcion = leer.nextInt();

            }catch (InputMismatchException e) {
                // Manejando la exception, si ingresa algo que no sea un int
                System.out.println("Por favor digite un numero de opción");
                opcion = 0;
                leer = new Scanner(System.in);
            }
            

            switch (opcion) {
                case 1:
                    System.out.println("Opción 1");
                    componente.procesoOutput("USD", "ARS");
                    break;
                case 2:
                    System.out.println("Opción 2");
                    componente.procesoOutput("ARS", "USD");
                    break;
                case 3:
                    System.out.println("Opción 3");
                    componente.procesoOutput("USD", "BRL");
                    break;
                case 4:
                    System.out.println("Opción 4");
                    componente.procesoOutput("BRL", "USD");
                    break;
                case 5:
                    System.out.println("Opción 5");
                    componente.procesoOutput("USD", "COP");
                    break;
                case 6:
                    System.out.println("Opción 6");
                    componente.procesoOutput("COP", "USD");
                    break;
                case 7:
                    System.out.println("Opción 7");
                    System.out.println(" ");
                    System.out.println("Historial:");
                    System.out.println(" ");
                    componente.mostrarHistorial();
                    System.out.println(" ");
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Elija un numero dentro de las opciones!!");
            }

        } while (opcion != 8);
    }
}
