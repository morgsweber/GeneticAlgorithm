/*  PROJETO E OTIMIZAÇÃO DE ALGORITMOS
    Prof. João Batista
    Turma 010
    Morgana Weber
    Trabalho Prático 4
*/

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static double AUX = 0;
    public static double BESTWAY = 0;
    public static String START = "65.6478 68.3254 Cid1000";
    public static String A = START;
        
    public static void main(String[] args) throws IOException{
        File file = new File("data.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> cities = new ArrayList<String>();
        ArrayList<String> visited = new ArrayList<String>();

        while (sc.hasNextLine()){
            String city = sc.nextLine();
            cities.add(city);

            AUX += calcDistance(A, city);
            A = city;
            System.out.println(sc.nextLine());
        }
        AUX += calcDistance(A, START);
        BESTWAY = AUX;
        AUX = 0;
        sc.close();  
        System.out.println("Best way until now: " + BESTWAY); 
        getWays(cities, visited);     
    }

    public static void getWays(ArrayList<String> cities, ArrayList<String> visited){
        if(cities.size() == 0){
            if(AUX < BESTWAY){
                System.out.println("New best way: " + BESTWAY);
            }
            //chama de novo 
        }

        String citySorted = genetic(); 
        visited.add(citySorted);
        cities.remove(citySorted);

        getWays(cities, visited);
    }

    public static String genetic(){
        //algoritmo genético
        return null;
    }

    public static double calcDistance(String city0, String city1){
        String[] c0 = city0.split(" ");
        String[] c1 = city1.split(" ");

        double x0 = Double.parseDouble(c0[0]);
        double y0 = Double.parseDouble(c0[1]);

        double x1 = Double.parseDouble(c1[0]);
        double y1 = Double.parseDouble(c1[1]);

        double dist = Math.sqrt(Math.pow(x1-x0, 2) + Math.pow(y1-y0,2));
        return dist;
    }
}