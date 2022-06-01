/*  PROJETO E OTIMIZAÇÃO DE ALGORITMOS
    Prof. João Batista
    Turma 010
    Morgana Weber
    Trabalho Prático 4
*/

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static ArrayList<Coordinate> POPULATION = new ArrayList<Coordinate>();
    public static double BESTWAY; 

    public static class Coordinate {
        double x;
        double y;
    }

    public static void main(String[] args) {
        readFile();
        BESTWAY = calcTotalKm(POPULATION);
        System.out.println("Best way until now: " + BESTWAY);

        while (true) {
            changeWay0();
            changeWay1();
            changeWay2();            
        }
    }

    public static void readFile() {
        File f = new File("data.txt");
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String city = sc.nextLine();
                String[] coord = city.split(" ");
                if(coord.length != 1){
                    Coordinate cityAux = new Coordinate();
                    cityAux.x = Double.parseDouble(coord[0]);
                    cityAux.y = Double.parseDouble(coord[1]);
                    POPULATION.add(cityAux);
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
    }

    public static double calcTotalKm(ArrayList<Coordinate> cities) {
        double total = 0;
        Coordinate a = cities.get(0);

        for (int i = 1; i < cities.size(); i++) {
            Coordinate city = cities.get(i);
            total += calcDistanceAB(a, city);
            a = city;
        }
        total += calcDistanceAB(a, cities.get(0));
        return total;
    }

    public static double calcDistanceAB(Coordinate city0, Coordinate city1) {
        double dist = Math.sqrt((Math.pow((city1.x - city0.x), 2)) + (Math.pow((city1.y - city0.y), 2)));
        return dist;
    }

    //Swap two random numbers 
    public static void changeWay0() {
        Random rand = new Random();
        int upperbound = POPULATION.size() - 1;

        int i = rand.nextInt(upperbound);
        int i1 = rand.nextInt(upperbound);

        Coordinate a = POPULATION.get(i);
        Coordinate b = POPULATION.get(i1);

        POPULATION.set(i1, a);
        POPULATION.set(i, b);

        double newDist = calcTotalKm(POPULATION);
        if (newDist < BESTWAY) {
            BESTWAY = newDist;
            System.out.println("New best way: " + BESTWAY);
        }else{
            POPULATION.set(i, a);
            POPULATION.set(i1, b);
        }
    }

    //Choose a random position and switch places with the next one
    public static void changeWay1() {
        Random rand = new Random();
        int upperbound = POPULATION.size() - 1;

        int i = rand.nextInt(upperbound);

        Coordinate a = POPULATION.get(i);
        Coordinate b = POPULATION.get(i + 1);

        POPULATION.set((i + 1), a);
        POPULATION.set(i, b);

        double newDist = calcTotalKm(POPULATION);
        if (newDist < BESTWAY) {
            BESTWAY = newDist;
            System.out.println("New best way: " + BESTWAY);
        }else{
            POPULATION.set(i, a);
            POPULATION.set((i+1), b);
        }
    }

    //Swap the first element with the last one
    public static void changeWay2() {
        int size = POPULATION.size() - 1;

        Coordinate a = POPULATION.get(0);
        Coordinate b = POPULATION.get(size);

        POPULATION.set(size, a);
        POPULATION.set(0, b);

        double newDist = calcTotalKm(POPULATION);
        if (newDist < BESTWAY) {
            BESTWAY = newDist;
            System.out.println("New best way: " + BESTWAY);
        }else{
            POPULATION.set(0, a);
            POPULATION.set(size, b);
        }
    }
}
