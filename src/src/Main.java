import java.util.Scanner;

/**
 * Navjot Kamboj- 40240781 and Yatish Chutani -40266553
 * COMP 6481 (Programming and Problem Solving)
 * Assignment #1 (Part-II)
 * Due Date: 11th Feb, 2024
 *
 */

/**
 * Driver Class controlling the gameplay.
 * Responsible for handling user inputs and displays gameplay commands
 */
public class Main {
    public static void main(String[] args) {

        BattleShip b = new BattleShip();
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi, let's play Battleship! ");

        /* Take user's input to enter the coordinates where they want to place their ships
        & use isCoordinateValid method to verify if user is entering correct and unused coordinates
        */
        for (int i = 0; i < 6; i++) {
            System.out.print("Enter the coordinates of your ship #" + (i + 1) + ": ");
            String upperCoord = sc.next().toUpperCase();
            while (b.isCoordinateValid(upperCoord)) {
                System.out.print("Enter the coordinates of your ship #" + (i + 1) + ": ");
                upperCoord = sc.next().toUpperCase();
            }
            b.setHumanUserShips(upperCoord);
        }

        /* Take user's input to enter the coordinates where they want to place their grenades
        & use isCoordinateValid method to verify if user is entering correct & unused coordinates
        */
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter the coordinates of your grenade #" + (i + 1) + ": ");
            String upperCoord = sc.next().toUpperCase();
            while (b.isCoordinateValid(upperCoord)) {
                System.out.print("Enter the coordinates of your grenade #" + (i + 1) + ": ");
                upperCoord = sc.next().toUpperCase();
            }
            b.setHumanUserGrenades(upperCoord);
        }

        //Function call to set ships and grenades for computer player randomly
        b.setComputerShipsAndGrenades();
        System.out.println("OK, the computer placed its ships and grenades at random. Let’s play.");






    }
}