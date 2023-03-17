import java.io.*;
import java.util.*;
public class Reader{
    ArrayList<String> lineas = new ArrayList<String>();

    public void Leer(String Arch){
        try (BufferedReader br = new BufferedReader(new FileReader(Arch))) {
            String lineaActual;
            while ((lineaActual = br.readLine()) != null) {
                lineas.add(lineaActual);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }
}
