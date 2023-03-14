import java.util.*;
import java.io.*;
public class Controlador {
    HashMap<String, String> capitalCities = new HashMap<String, String>();

    //Lector de archivo
    String DIr = "C:\\Users\\Usuario\\Documents\\GitHub\\Proyecto-Datos\\src\\src\\Dats.txt";
    Reader read = new Reader();

    public void Iniciar(){

    }


    public void Reader(){
        try(BufferedReader br = new BufferedReader(new FileReader(Dire))){
            String ln;
            while ((ln = br.readLine()) != null){
                String[] partes = ln.split("\\|");
                Key = partes[0].trim();
                Valor = partes[1].trim();
                //TESTEO 1
                if(mp.containsKey(Key)){
                    mp.get(Key).add(Valor);
                }
                else {
                    ArrayList<String> Prueba = new ArrayList<String>();
                    ArrayList<String> Prueba2 = new ArrayList<String>();
                    mp.put(Key,Prueba);
                    checkout.put(Key,Prueba2);
                    mp.get(Key).add(Valor);
                    Prueba2.removeAll(Prueba2);
                }
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    }

public class FileReaderExample {
    public static void main(String[] args) {
        String filePath = "path/to/your/file.txt";
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Process the array of lines as required
        for (String line : lines) {
            System.out.println(line);
        }
    }
}












}

