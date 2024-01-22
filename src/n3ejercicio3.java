import java.io.*;
import java.util.*;

public class n3ejercicio3 {

    public static void main(String[] args) {
        try {
            String filePath = "c:/paises/countries.txt";
            Map<String, String> mapaPaisesCapitales = leerArchivo(filePath);

            // Iniciar el juego
            Scanner myObj = new Scanner(System.in);
            System.out.print("Ingresa tu nombre: ");
            String nombreUsuario = myObj.nextLine();

            int puntuacion = jugar(mapaPaisesCapitales, myObj);

            crearArchivoResultado(nombreUsuario, puntuacion);

            myObj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> leerArchivo(String filePath) throws IOException {
        Map<String, String> mapaPaisesCapitales = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                if (partes.length == 2) {
                    String pais = partes[0];
                    String capital = partes[1];
                    mapaPaisesCapitales.put(pais, capital);
                }
            }

        }

        return mapaPaisesCapitales;
    }

    private static int jugar(Map<String, String> mapaPaisesCapitales, Scanner myObj) {
        int puntuacion = 0;
        Set<String> paises = mapaPaisesCapitales.keySet();

        for (int i = 0; i < 10; i++) {
            String pais = obtenerPaisAleatorio(paises);
            String capitalCorrecta = mapaPaisesCapitales.get(pais);

            System.out.print(" ¿Cual es la capital de " + pais + "? ");
            String capitalUsuario = myObj.nextLine();

            if (capitalUsuario.equalsIgnoreCase(capitalCorrecta)) {
                System.out.println(" ¡Correcto! Sumas 1 punto.");
                puntuacion++;
            } else {
                System.out.println("Incorrecto. La capital correcta es " + capitalCorrecta + ".");
            }
        }

        System.out.println("Juego terminado. Puntación final: " + puntuacion + " puntos.");
        return puntuacion;
    }

    private static String obtenerPaisAleatorio(Set<String> paises) {
        List<String> listaPaises = new ArrayList<>(paises);
        Random numRandom = new Random();
        int indice = numRandom.nextInt(listaPaises.size());
        return listaPaises.get(indice);
    }

    private static void crearArchivoResultado(String nombreUsuario, int puntuacion) throws IOException {
        String resultado = nombreUsuario + " - Puntaje: " + puntuacion + " puntos";
        String filePath = "c:/paises/resultado.txt";

        try (BufferedWriter ficheroFinal = new BufferedWriter(new FileWriter(filePath))) {
            ficheroFinal.write(resultado);
        }
    }
}



