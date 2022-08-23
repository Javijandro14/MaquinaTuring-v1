package maquinaturing_proyecto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MaquinaTuring_Proyecto {

    public static void main(String[] args) throws Exception {
        BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));

        String cadenachar;
        char[] carcad;
        char[] cabeza;
        String qI, q, qF;
        //esta sera la posicion de la cinta
        int iter = 0;
        //Nos servira para verificar si se mueve a la izquierda, derecha, si se detiene o si la cadena se aceptó
        boolean movizq = false, movder = false, stop = false, aceptado = false;

        //Estado Inicial
        qI = "q0";
        //Estado actual
        q = qI;
        //Caracteres que vamos a utilizar
        cadenachar = "abXYB";
        carcad = cadenachar.toCharArray();
        //Estado Final
        qF = "q4";
        //Nombre de cada estado
        String estados[] = {"q0", "q1", "q2", "q3", "q4"};
        //La tabla de movimientos representado en forma de arrays
        String movimientos[][] = {{"q1,X,R", "   -  ", "   -  ", "q3,Y,R", "   -  "}, {"q1,a,R", "q2,Y,L", "   -  ", "q1,Y,R", "   -  "},
        {"q2,a,L", "   -  ", "q0,X,R", "q2,Y,L", "   -  "}, {"   -  ", "   -  ", "   -  ", "q3,Y,R", "q4,B,R"},
        {"   S  ", "   S  ", "   S  ", "   S  ", "   S  "}};
        
        //Presentacion de la Maquina Turing al usuario
        System.out.println("Q " + Arrays.toString(estados));
        System.out.println("Σ " + carcad[0] + "," + carcad[1]);
        System.out.println("Γ " + Arrays.toString(carcad));
        System.out.println("F " + qF);
        System.out.println("qI " + qI);

        //Presentacion de la tabla de movimientos para el usuario
        System.out.println("La tabla de movimientos es la sigueinte:\n δ" + Arrays.toString(carcad).replace("[", "     ").replace(",", "     ").replace("]", "   "));
        for (int i = 0; i < estados.length; i++) {
            System.out.println(estados[i] + Arrays.toString(movimientos[i]).replace("[", "  ").replace(", ", " ").replace("]", " "));
        }
        //Usuario Ingresa cualquier cadena
        String cadena;
        System.out.println("Ingrese una cadena:");
        cadena = lectura.readLine() + "B";
        cabeza = cadena.toCharArray();
        
        //Si stop = false, entonces no se detendra el programa
        while (!stop) {
            //no tenemos ciclo for, este if es para verificar que el numero de iteracion no es mayor a la cantidad de caracteres que tiene la cadena, si se pasa, etnonces
            //vamos al else
            if (iter < cabeza.length) {
                System.out.println("----------------------------------");

                for (int j = 0; j < estados.length; j++) {
                    if (q.equals(estados[j])) {
                        for (int k = 0; k < carcad.length; k++) {
                            if (cabeza[iter] == carcad[k] && !movimientos[j][k].contains("-")) {
                                //Si la tabla de movimientos no muestra que la celda asignada contiene "-", entonces nos da la instruccion de la celda
                                System.out.println("Posicion Cinta: " + cabeza[iter] + " Estado Actual:" + q);
                                String[] mover = movimientos[j][k].split(",");
                                System.out.println("En nuestra tabla de movimientos, tenemos esta instruccion: " + movimientos[j][k]);
                                //Si es R, mueve ahcia la derecha, de lo contrario, ira a la izquierda
                                if (mover[2].equals("R")) {
                                    System.out.println("Nuevo estado: " + mover[0] + ", '" + cabeza[iter] + "' se remplaza por el valor:" + mover[1] + ", y su Direccion: --> ");
                                    q = mover[0];
                                    cabeza[iter] = mover[1].charAt(0);
                                    movder = true;
                                    movizq = false;
                                } else if (mover[2].equals("L")) {
                                    System.out.println("Nuevo estado: " + mover[0] + ", '" + cabeza[iter] + "' se remplaza por el valor:" + mover[1] + ", y su Direccion: <-- ");
                                    q = mover[0];
                                    cabeza[iter] = mover[1].charAt(0);
                                    movder = false;
                                    movizq = true;
                                }
                                break;
                            } else if (cabeza[iter] == carcad[k] && movimientos[j][k].contains("-")) {
                                //Si nuestra celda asignada SI tiene "-", entonces detendra el programa y nos dira que no acepto la cadena
                                System.out.println("Inicial:" + q);
                                System.out.println(movimientos[j][k]);
                                stop = true;
                                aceptado = false;
                                break;
                            }
                        }
                        break;
                    }
                }
                if (movder) {iter++;}//derecha
                if (movizq) {iter--;}//izquierda
            } else {
                if (qF.equals(q)) {
                    //Si el estado actual equivale al estado final, entonces este codigo se activara, se acepta la cadena
                    System.out.println("----------------------------------");
                    System.out.println("Estado Actual"+ q +" La nueva instruccion nos muestra:" + movimientos[estados.length - 1][carcad.length - 1]);
                    stop = true;
                    aceptado = true;
                    break;
                } else {
                    stop = true;
                    aceptado = false;
                    break;
                }
            }
            System.out.println("Nueva cadena" + Arrays.toString(cabeza).replace("[", " ").replace(", ", "").replace("]", ""));
        }

        if (aceptado) {System.out.println("La cadena fue aceptada");} 
        else {System.out.println("La cadena no fue aceptada");}

    }
}
