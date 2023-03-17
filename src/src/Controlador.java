import java.util.*;
import java.io.*;
public class Controlador {


    //Lector de archivo
    String Dir = "C:\\Users\\Usuario\\Documents\\GitHub\\Proyecto-Datos\\src\\src\\Dats.txt";
    ArrayList<String> Cadenas = new ArrayList<String>();
    HashMap<String, String> VariablesDefinir = new HashMap<>();
    HashMap<String, ArrayList<String>> Listas = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    String Respuesta = "";
    ArrayList<String> KeysLists = new ArrayList<String>();
    ArrayList<String> KeysNormal = new ArrayList<String>();
    String[] Tokens;
    String Temporal = "";

    public void Iniciar(){
        Reader read = new Reader();
        System.out.println("Escriba la direccion de su archivo: ");
        Respuesta = Dir;
        //Respuesta = sc.nextLine();
        read.Leer(Respuesta);
        Cadenas = read.lineas;
        for(int i = 0;i <= Cadenas.size()-1;i++){
            Temporal = Cadenas.get(i).substring(1,Cadenas.get(i).length()-1);


            if((Temporal.contains("+"))||(Temporal.contains("-"))||(Temporal.contains("*"))||(Temporal.contains("/"))){
                if(!(Temporal.contains("quote"))){
                    if(!KeysNormal.isEmpty()) {
                        for (int m = 0; m <= KeysNormal.size() - 1; m++) {
                            if(Temporal.contains(KeysNormal.get(m))){
                               Temporal = Temporal.replace(KeysNormal.get(m),VariablesDefinir.get(KeysNormal.get(m)));
                            }
                        }
                    }
                Temporal = Temporal.replace("(","");
                Temporal = Temporal.replace(")","");
                System.out.println("Operacion Artimetica Detectada");
                System.out.println(Aritmetica.Matematicas(Temporal));
                }

            }
            if(Temporal.contains("quote")){
                int Cont = 0;
                int Cont2 = 0;
                Tokens = Temporal.split("");
                for(int p = 0; p <= Tokens.length-1;p++){
                    if(Tokens[p].contains("(")){
                        Cont = p;
                    }
                    if(Tokens[p].contains(")")){
                        Cont2 = p;
                        System.out.println("Operacion quote detectada: ");
                        System.out.println(Temporal.substring(Cont+1,Cont2));
                    }
                }
            }
            if(Temporal.contains("setq")){
                System.out.println("Variables definidas");
                Tokens = Temporal.split(" ");
                if(Tokens.length == 3) {
                    VariablesDefinir.put(Tokens[1], Tokens[2]);
                    String[] strings = VariablesDefinir.keySet().toArray(new String[VariablesDefinir.size()]);
                    KeysNormal = new ArrayList<String>(Arrays.asList(strings));
                }
                if(Tokens.length >3){
                    int ContadorI = 0;
                    int ContadorF = 0;
                    String[] valortemporal = Temporal.split("");
                    for(int u = 0; u <= valortemporal.length-1;u++){
                        if(valortemporal[u].equals("(")){
                            ContadorI = u;
                        }
                        if(valortemporal[u].equals(")")){
                            ContadorF = u;
                            String valor = Temporal.substring(ContadorI,ContadorF);
                            String[] val = valor.split("");
                            ArrayList<String> Valores = new ArrayList<String>();
                            Collections.addAll(Valores,valor);
                            Listas.put(Tokens[1],Valores);
                            String[] strings = Listas.keySet().toArray(new String[Listas.size()]);
                            KeysLists = new ArrayList<String>(Arrays.asList(strings));
                        }
                    }
                }
            }
            if(Temporal.contains("atom")){
                Tokens = Temporal.split(" ");
                for(int b = 0; b <= KeysNormal.size();b++){
                    if(Tokens[1].equals(KeysNormal.get(b))){

                    }

                }

            }
            if(Temporal.contains("cond")){
                
            }




        }
        }

    }


