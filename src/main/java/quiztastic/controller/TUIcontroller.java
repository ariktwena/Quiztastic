package quiztastic.controller;

import java.util.Scanner;

public class TUIcontroller {

    Scanner scanner = new Scanner(System.in);

    //Get input from player
    public String playerInput(){
        System.out.print("> ");
        String input = scanner.next();
        return input;
    }

    //Get player name
    public String getPlayerName(){
        System.out.println("Enter your name: ");
        String name = scanner.next();
        System.out.println("");
        System.out.println("Welcome to the Quiz show " + name + "!!");
        System.out.println("");
        System.out.println("Here are the roles of the game...");
        System.out.println("");
        System.out.println("If you need evr need help, then just write [help] in the command line. Have a Happy Quizzy");
        return name;
    }

    //Loading text
    public void loader(){
        try{
            System.out.println("Loading.....");
            Thread.sleep(1500);
        } catch (InterruptedException i){
            throw new UnsupportedOperationException("You got an InterruptedException: " + i.getMessage());
        }
    }

    //Get help
    public void getHelp(){
        System.out.println("Enter your name: ");
        String name = scanner.next();
        System.out.println("");
        System.out.println("Welcome to the Quiz show " + name);
    }

    //Exit the game
    public void exitGame(String name){
        System.out.println("I have never met a successful person that was a quitter. Successful people never, ever, give up! " + name);
        System.exit(0);
    }

    //We typing unknown operation
    public void unknownOperation(String name){
        System.out.println(name + " you have typed an unknown operation. If you need help, then just type [Help].");
    }

}
