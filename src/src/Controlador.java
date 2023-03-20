import java.util.*;
import java.io.*;
public class Controlador {


    //Lector de archivo
    String Dir = "C:\\Users\\Usuario\\Documents\\GitHub\\Proyecto-Datos\\src\\src\\Dats.txt";
    ArrayList<String> Cadenas = new ArrayList<String>();
    HashMap<String, String> VariablesDefinir = new HashMap<>();
    HashMap<String, ArrayList<String>> Listas = new HashMap<>();
    HashMap<String, ArrayList<String>> Funciones = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    String Respuesta = "";
    ArrayList<String> KeysLists = new ArrayList<String>();
    ArrayList<String> KeysNormal = new ArrayList<String>();
    ArrayList<String> DEFUN = new ArrayList<String>();
    String[] Tokens;
    String[] DefunTokens;
    String Temporal = "";
    Boolean loops = true;
    String defunkey = "";
    String temporal2 = "";
    int SumaParentesis;

    /**
     * Esta funcion sirve para realizar todos los posibles procesos logicos que se piden en el programa.
     * Guarda las lineas leidas en un array, para mayor facilidad de acceso.
     * @author Juan Pablo Solis / Esteban Zambrano
     * @param
     * @return No regresa ningun dato, al ser una funcion del tipo "void".
     */
    public void Iniciar(){
        Reader read = new Reader();
        System.out.println("Escriba la direccion de su archivo: ");
        Respuesta = Dir;
        //Respuesta = sc.nextLine();
        read.Leer(Respuesta);
        Cadenas = read.lineas;
        for(int i = 0;i <= Cadenas.size()-1;i++) {
            Temporal = Cadenas.get(i);

            temporal2 = Temporal;
            temporal2 = temporal2.substring(1,temporal2.length()-1);
            DefunTokens = temporal2.split(" ");
            if(Funciones.containsKey(DefunTokens[0])){
                String KeyDefun = Funciones.get(DefunTokens[0]).get(0);
                String[] Key_Procesada = KeyDefun.split(" ");

                for(int e = 1; e <= Funciones.get(DefunTokens[0]).size()-1;e++){
                    String CadenaDefun = Funciones.get(DefunTokens[0]).get(e);
                    int r = 0;
                    for(int b = 0; b <= Key_Procesada.length-1;b++){
                        for(int z = 1+r; z <= DefunTokens.length-1;z++){
                            CadenaDefun = CadenaDefun.replace(Key_Procesada[b],DefunTokens[z]);
                        }
                        r++;
                    }
                    Interpretes(i,CadenaDefun);
                }





            }
            //Inicio Funcion Defun, guarda el nombre de la funcion en un hashmap junto a sus parametros
            if(Temporal.contains("defun") || (loops == false) ){
                loops = false;
                if(Temporal.contains("defun")){
                    defunkey = "";
                    SumaParentesis = 1;
                    Temporal = Cadenas.get(i).substring(1, Cadenas.get(i).length());
                    Tokens = Temporal.split(" ");
                    int CI = 0;
                    int CF = 0;
                    String[] valtemp = Temporal.split("");
                    for(int d = 0; d <= valtemp.length-1;d++){
                        if(valtemp[d].equals("(")){
                            CI = d;
                            SumaParentesis++;
                        }
                        if(valtemp[d].equals(")")) {
                            SumaParentesis--;
                            CF = d;
                            String temporal2 = Temporal.substring(CI+1,CF);
                            ArrayList<String> tempo = new ArrayList<String>();
                            tempo.add(temporal2);
                            Funciones.put(Tokens[1],tempo);
                            defunkey = Tokens[1];
                        }
                    }
                }
            }
            //Funcion aritmetica, quita los parentesis.
            if (((Temporal.contains("+")) || (Temporal.contains("-")) || (Temporal.contains("*")) || (Temporal.contains("/"))) && (loops)) {
                Temporal = Cadenas.get(i).substring(1, Cadenas.get(i).length() - 1);
                if (!(Temporal.contains("quote"))) {
                    if (!KeysNormal.isEmpty()) {
                        for (int m = 0; m <= KeysNormal.size() - 1; m++) {
                            if (Temporal.contains(KeysNormal.get(m))) {
                                Temporal = Temporal.replace(KeysNormal.get(m), VariablesDefinir.get(KeysNormal.get(m)));
                            }
                        }
                    }
                    Temporal = Temporal.replace("(", "");
                    Temporal = Temporal.replace(")", "");
                    System.out.println("Operacion Artimetica Detectada");
                    System.out.println(Aritmetica.Matematicas(Temporal));
                }

            }
            // Esta funcion hace que no se opere lo que se encuentra en la linea
            if (Temporal.contains("quote") && (loops)) {
                Temporal = Cadenas.get(i).substring(1, Cadenas.get(i).length() - 1);
                int Cont = 0;
                int Cont2 = 0;
                Tokens = Temporal.split("");
                for (int p = 0; p <= Tokens.length - 1; p++) {
                    if (Tokens[p].contains("(")) {
                        Cont = p;
                    }
                    if (Tokens[p].contains(")")) {
                        Cont2 = p;
                        System.out.println("Operacion quote detectada: ");
                        System.out.println(Temporal.substring(Cont + 1, Cont2));
                    }
                }
            }
            if (Temporal.contains("setq") && (loops)) {
                Temporal = Cadenas.get(i).substring(1, Cadenas.get(i).length() - 1);
                System.out.println("Variables definidas");
                Tokens = Temporal.split(" ");
                if (Tokens.length == 3) {
                    VariablesDefinir.put(Tokens[1], Tokens[2]);
                    String[] strings = VariablesDefinir.keySet().toArray(new String[VariablesDefinir.size()]);
                    KeysNormal = new ArrayList<String>(Arrays.asList(strings));
                }
                if (Tokens.length > 3) {
                    int ContadorI = 0;
                    int ContadorF = 0;
                    String[] valortemporal = Temporal.split("");
                    for (int u = 0; u <= valortemporal.length - 1; u++) {
                        if (valortemporal[u].equals("(")) {
                            ContadorI = u;
                        }
                        if (valortemporal[u].equals(")")) {
                            ContadorF = u;
                            String valor = Temporal.substring(ContadorI, ContadorF);
                            ArrayList<String> Valores = new ArrayList<String>();
                            Collections.addAll(Valores, valor);
                            Listas.put(Tokens[1], Valores);
                            String[] strings = Listas.keySet().toArray(new String[Listas.size()]);
                            KeysLists = new ArrayList<String>(Arrays.asList(strings));
                        }
                    }
                }
            }
            if (Temporal.contains("atom") && (loops)) {
                Temporal = Cadenas.get(i).substring(1, Cadenas.get(i).length() - 1);
                Tokens = Temporal.split(" ");
                boolean kis = false;
                for (int b = 0; b <= KeysNormal.size() - 1; b++) {
                    if (Tokens[1].equals(KeysNormal.get(b))) {
                        System.out.println("T");
                        kis = true;

                    }
                }
                for (int j = 0; j <= KeysLists.size() - 1; j++) {
                    if (Tokens[1].equals(KeysLists.get(j))) {
                        System.out.println("NIL");
                        kis = true;
                    }
                }
                if (!kis) {
                    System.out.println("No existe el atomo que busca");
                }


            }
            if (Temporal.contains("equal") && (loops)) {
                Temporal = Cadenas.get(i).substring(1, Cadenas.get(i).length() - 1);
                boolean Comparacion = false;
                Tokens = Temporal.split(" ");
                try {
                    if (VariablesDefinir.get(Tokens[1]).equals(VariablesDefinir.get(Tokens[2]))) {
                        Comparacion = true;
                    }

                } catch (Exception e) {
                }
                try {
                    if (Listas.get(Tokens[1]).equals(Listas.get(Tokens[2]))) {
                        Comparacion = true;
                    }

                } catch (Exception e) {
                }
                try {
                    int numero1 = Integer.valueOf(Tokens[1]);
                    int numero2 = Integer.valueOf(Tokens[2]);
                    if (numero1 == numero2) {
                        Comparacion = true;
                    }

                } catch (Exception e) {
                }
                if (Comparacion) {
                    System.out.println("T");
                }
                if (!Comparacion) {
                    System.out.println("NIL");
                }
            }
            if(!(Temporal.contains("defun")) && !loops){
                String[] valtemp = Temporal.split("");
                for(int h = 0; h<= valtemp.length-1;h++){
                    if(valtemp[h].equals("(")){
                        SumaParentesis++;
                    }
                    if(valtemp[h].equals(")")){
                        SumaParentesis--;
                    }
                }
                if(SumaParentesis == 0){
                    Temporal = Temporal.substring(0,Temporal.length()-1);
                    Funciones.get(defunkey).add(Temporal);
                    loops = true;
                    System.out.println("Funcion agregada con exito");
                }
                else {
                    Funciones.get(defunkey).add(Temporal);
                }
            }
            }
        }

        
        /**
         *  Esta funcion sirve para realizar todos los posibles procesos logicos que se piden en el programa.
         *  Es una copia casi exacta del codigo utilizado en la funcion principal, solo que este es utilizado para cuando se utiliza la funcion defun
         * @param i
         * @param Temporal
         * @return No regresa nada al ser la funcion tipo void.
         */
        public void Interpretes(int i, String Temporal){
            if ((Temporal.contains("+")) || (Temporal.contains("-")) || (Temporal.contains("*")) || (Temporal.contains("/"))){
                Temporal = Temporal.substring(1, Temporal.length() - 1);
                if (!(Temporal.contains("quote"))) {
                    if (!KeysNormal.isEmpty()) {
                        for (int m = 0; m <= KeysNormal.size() - 1; m++) {
                            if (Temporal.contains(KeysNormal.get(m))) {
                                Temporal = Temporal.replace(KeysNormal.get(m), VariablesDefinir.get(KeysNormal.get(m)));
                            }
                        }
                    }
                    Temporal = Temporal.replace("(", "");
                    Temporal = Temporal.replace(")", "");
                    System.out.println("Operacion Artimetica Detectada");
                    System.out.println(Aritmetica.Matematicas(Temporal));
                }

            }if (Temporal.contains("quote") && (loops)) {
                Temporal = Temporal.substring(1, Temporal.length() - 1);
                int Cont = 0;
                int Cont2 = 0;
                Tokens = Temporal.split("");
                for (int p = 0; p <= Tokens.length - 1; p++) {
                    if (Tokens[p].contains("(")) {
                        Cont = p;
                    }
                    if
             (Tokens[p].contains(")")) {
                        Cont2 = p;
                        System.out.println("Operacion quote detectada: ");
                        System.out.println(Temporal.substring(Cont + 1, Cont2));
                    }
                }
            }
            if (Temporal.contains("setq") && (loops)) {
                Temporal = Temporal.substring(1, Temporal.length() - 1);
                System.out.println("Variables definidas");
                Tokens = Temporal.split(" ");
                if (Tokens.length == 3) {
                    VariablesDefinir.put(Tokens[1], Tokens[2]);
                    String[] strings = VariablesDefinir.keySet().toArray(new String[VariablesDefinir.size()]);
                    KeysNormal = new ArrayList<String>(Arrays.asList(strings));
                }
                if (Tokens.length > 3) {
                    int ContadorI = 0;
                    int ContadorF = 0;
                    String[] valortemporal = Temporal.split("");
                    for (int u = 0; u <= valortemporal.length - 1; u++) {
                        if (valortemporal[u].equals("(")) {
                            ContadorI = u;
                        }
                        if (valortemporal[u].equals(")")) {
                            ContadorF = u;
                            String valor = Temporal.substring(ContadorI, ContadorF);
                            ArrayList<String> Valores = new ArrayList<String>();
                            Collections.addAll(Valores, valor);
                            Listas.put(Tokens[1], Valores);
                            String[] strings = Listas.keySet().toArray(new String[Listas.size()]);
                            KeysLists = new ArrayList<String>(Arrays.asList(strings));
                        }
                    }
                }
            }
            if (Temporal.contains("atom") && (loops)) {
                Temporal = Temporal.substring(1, Temporal.length() - 1);
                Tokens = Temporal.split(" ");
                boolean kis = false;
                for (int b = 0; b <= KeysNormal.size() - 1; b++) {
                    if (Tokens[1].equals(KeysNormal.get(b))) {
                        System.out.println("T");
                        kis = true;

                    }
                }
                for (int j = 0; j <= KeysLists.size() - 1; j++) {
                    if (Tokens[1].equals(KeysLists.get(j))) {
                        System.out.println("NIL");
                        kis = true;
                    }
                }
                if (!kis) {
                    System.out.println("No existe el atomo que busca");
                }


            }
            if (Temporal.contains("equal") && (loops)) {
                Temporal = Temporal.substring(1, Temporal.length() - 1);
                boolean Comparacion = false;
                Tokens = Temporal.split(" ");
                try {
                    if (VariablesDefinir.get(Tokens[1]).equals(VariablesDefinir.get(Tokens[2]))) {
                        Comparacion = true;
                    }

                } catch (Exception e) {
                }
                try {
                    if (Listas.get(Tokens[1]).equals(Listas.get(Tokens[2]))) {
                        Comparacion = true;
                    }

                } catch (Exception e) {
                }
                try {
                    int numero1 = Integer.valueOf(Tokens[1]);
                    int numero2 = Integer.valueOf(Tokens[2]);
                    if (numero1 == numero2) {
                        Comparacion = true;
                    }

                } catch (Exception e) {
                }
                if (Comparacion) {
                    System.out.println("T");
                }
                if (!Comparacion) {
                    System.out.println("NIL");
                }
            }

        }



        }


