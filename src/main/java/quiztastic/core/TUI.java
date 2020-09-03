package quiztastic.core;

import java.util.Scanner;

public class TUI {

    Scanner scanner = new Scanner(System.in);

    //Get input from player
    public String playerInput(){
        System.out.println("");
        System.out.print("> ");
        String input = scanner.nextLine();
        return input;
    }

    //Get player name
    public String getPlayerName(){
        System.out.println("");
        System.out.print("Enter your name > ");
        String name = scanner.nextLine();
        loader();
        System.out.println("");
        System.out.println("Welcome to the Quiz show " + name + "!!");
        System.out.println("");
        System.out.println("Here are the roles of the game...");
        System.out.println("");
        System.out.println("If you ever need help, then just write [help] in the command line. Have a Happy Quizzy");
        System.out.println("");
        System.out.println("If you want to exit the game, just write [exit].");
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
        System.out.println("");
        System.out.println("This is the help...");
    }

    //Exit the game
    public void exitGame(String name){
        System.out.println("");
        System.out.println("I have never met a successful person that was a quitter. Successful people never, ever, give up! " + name);
        System.exit(0);
    }

    //We typing unknown operation
    public void unknownOperation(String name){
        System.out.println("");
        System.out.println(name + " you have typed an unknown operation. If you need help, then just type [Help].");
    }


    /**
     *
     * Play Board
     *
     */

    //Choose category
    public String playerCategoryInput(){
        loader();
        System.out.println("");
        System.out.print("Choose Category > ");
        String input = scanner.nextLine();

        while(input.equalsIgnoreCase("a")
                || input.equalsIgnoreCase("b")
                || input.equalsIgnoreCase("c")
                || input.equalsIgnoreCase("d")
                || input.equalsIgnoreCase("e")
                || input.equalsIgnoreCase("f")
                || input.equalsIgnoreCase("help")
                || input.equalsIgnoreCase("exit")) {
            System.out.println("");
            System.out.print("Wrong input....");
            System.out.print("Choose a Category > ");
            input = scanner.nextLine();
        }

        return input;
    }
}
