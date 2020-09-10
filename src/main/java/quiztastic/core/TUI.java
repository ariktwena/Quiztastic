package quiztastic.core;

import javax.imageio.IIOException;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.PortUnreachableException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

public class TUI {

    private  final PrintWriter writer;
    private final Scanner scanner;

    public TUI() {

    }

    public void halloWorld(Socket socket) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(socket.getInputStream());
        writer.print("Hallo world");
        writer.flush();
    }

    //Get input from player
    public String playerInput(Socket socket){

        String input = null;

        try{

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(socket.getInputStream());

            writer.println("");
            writer.print("What do you want to do? > ");
            writer.flush();
            input = scanner.nextLine();

        } catch (IOException e) {
        e.printStackTrace();
        }
        return input;
    }

    //Get player name
    public String getPlayerName(Socket socket) {
        String name = null;
        
        try{

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            Scanner scanner = new Scanner(socket.getInputStream());

            System.out.println("");
            writer.print("Enter your name > ");
            writer.flush();
            name = scanner.nextLine();
            loader(socket);
            writer.println("");
            writer.println("Welcome to the Quiz show " + name + "!!");
            writer.println("");
            writer.println("Here are the roles of the game: ");
            writer.println("You start by choosing a category A, B, C etc. Then you choose a question for points.");
            writer.println("If you answer correctly, you get the points.");
            writer.println("");
            writer.println("If you ever need help, then just write [help].");
            writer.println("");
            writer.println("Write [Play] to start the game. Have a Happy Quizzy :)");
            writer.flush();
            

        } catch (IOException e) {
            e.printStackTrace();
        }


        return name;
    }

    //Loading text
    public void loader(Socket socket){
        try{
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("Loading.....");
            writer.flush();
            Thread.sleep(1500);
        } catch (InterruptedException i){
            throw new UnsupportedOperationException("You got an InterruptedException: " + i.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Loading text
    public void loaderLong(Socket socket){
        try{
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("Loading.....");
            writer.flush();
            Thread.sleep(3000);
        } catch (InterruptedException i){
            throw new UnsupportedOperationException("You got an InterruptedException: " + i.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Get help in the game
    public void getHelpGame(Socket socket){
        try{
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("");
            writer.println("You have to choose a category or question by typing: A, B, C, D, E or F.");
            writer.println("");
            writer.println("Here are the help commands:");
            writer.println("[Help]" + "\t" + "Get help and options");
            writer.println("[Score]" + "\t" + "See your current score");
            writer.println("[Board]" + "\t" + "See the question status");
            writer.println("[Exit]" + "\t" + "Exit the game");
            writer.flush();
            loaderLong(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Exit the game
    public void exitGame(String name, Socket socket){
        try{
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("");
            writer.println("I have never met a successful person that was a quitter. Successful people never, ever, give up! " + name);
            writer.flush();
            socket.close();
            //System.exit(0);
        } catch (IOException e) {
            new UnsupportedOperationException("The socket was closed" + e.getMessage());
        }
    }

    //Get player score
    public void getScore(Player player, Socket socket){
        try{
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("");
            writer.println(player.getName() + " you have " + player.getScore() + " points.");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Get player score
    public void getBoardStatus(int boardStatus, Socket socket){
        try{
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("");
            writer.println("You have answered " + boardStatus + " questions. You still have " + (30-boardStatus) + " to go :)");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Game default message
    public void gameDefaultMessage(Socket socket){
        try{
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("");
            writer.println("Wrong input....");
            writer.println("Do you need help? Then just write [help], or [exit] to end the game.");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

//    try{
//        PrintWriter writer = new PrintWriter(socket.getOutputStream());
//
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
    /**
     *
     * Play Board - Category
     *
     */

    //Choose category
    public String playerCategoryInput(Socket socket){
        String input = null;

        try{
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            Scanner scanner = new Scanner(socket.getInputStream());
            writer.println("");
            writer.print("Choose a category > ");
            writer.flush();
            input = scanner.nextLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return input;
    }


    //Show chosen category
    public void getCategoryTitle(int index_start, ArrayList<Question_board> list, Socket socket){

        try{

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("");
            writer.println("You have chosen the Category '" + list.get(index_start).getCategory().getCategoryName() + "', here are the the available questions and possible score prize!");
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     *
     * Play Board - Questions
     *
     */

    //Show available questions
    public void availableQuestionsInCategory(int index_start, int index_end, ArrayList<Question_board> list, Socket socket){

        String[] choiseSpots = {"A", "B", "C", "D", "E"};
        int choiseCount = 0;

        for( int i = index_start ; i < index_end ; i++ ){
            if(list.get(i).getAnswered() == null){
                try{

                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    writer.println(choiseSpots[choiseCount] + ": " + list.get(i).getScore());
                    writer.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try{

                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    writer.println(choiseSpots[choiseCount] + ": Question has already been answered.");
                    writer.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            choiseCount ++;
        }

    }

    //Choose question choice
    public String playerQuestionInputChoice(Socket socket){
        String input = null;

        try{

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            Scanner scanner = new Scanner(socket.getInputStream());
            writer.println("");
            writer.print("Choose a question > ");
            writer.flush();
            input = scanner.nextLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return input;
    }

    //Get the question
    public boolean getTheQuestion(ArrayList<Question_board> list, int index_start, int question_index, Socket socket){
        if(list.get(index_start + question_index).getAnswered() != null){
            try{

                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                writer.print("The question has already been played\n");
                writer.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        } else {
            try{

                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                writer.print(list.get(index_start + question_index).getQuestion());
                writer.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        }
    }

    //Choose question answer
    public String playerQuestionInputAnswer(Socket socket){
        String input = null;

        try{

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            Scanner scanner = new Scanner(socket.getInputStream());
            writer.println("");
            writer.print("What is you answer? > ");
            writer.flush();
            input = scanner.nextLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return input;
    }

    //Validate answer
    public void validateAnswer(ArrayList<Question_board> list, int index_start, int question_index, String answer, Player player, Socket socket){
        if(list.get(index_start + question_index).getAnswer().equalsIgnoreCase(answer)){
            try{

                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                //Message to the player
                writer.print("Correct " + player.getName() + "!!! You have earned " + list.get(index_start + question_index).getScore() + " points :)\n");
                writer.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

            //We add the score to the player
            player.setScore(player.getScore() + list.get(index_start + question_index).getScore());

            //We deactivet the question
            list.get(index_start + question_index).setAnswered("---");

        } else {
            try{

                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                //Message to the player
                writer.print("Incorrect " + player.getName() + "! The right answer is: " + list.get(index_start + question_index).getAnswer() + "\n");
                writer.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

            //We deactivet the question
            list.get(index_start + question_index).setAnswered("---");

        }
    }

    /**
     *
     * Play Board - Hard
     *
     */

    //Get help
    public void getHardBoardMessage(Socket socket){
        try{

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("");
            writer.println("You have reached the hard part of the game. You will now be tested on the hardest questions for maximum prizes :)");
            writer.println("Write [play] to start the game or [help] to see your options.");
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     *
     * System.out.print
     *
     */
//
//    Scanner scanner = new Scanner(System.in);
//
//    //Get input from player
//    public String playerInput(){
//        System.out.println("");
//        System.out.print("What do you want to do? > ");
//        String input = scanner.nextLine();
//        return input;
//    }
//
//    //Get player name
//    public String getPlayerName() {
//
//        System.out.println("");
//        System.out.print("Enter your name > ");
//        String name = scanner.nextLine();
//        loader();
//        System.out.println("");
//        System.out.println("Welcome to the Quiz show " + name + "!!");
//        System.out.println("");
//        System.out.println("Here are the roles of the game: ");
//        System.out.println("You start by choosing a category A, B, C etc. Then you choose a question for points.");
//        System.out.println("If you answer correctly, you get the points.");
//        System.out.println("");
//        System.out.println("If you ever need help, then just write [help].");
//        System.out.println("");
//        System.out.println("Write [Play] to start the game. Have a Happy Quizzy :)");
//        return name;
//    }
//
//    //Loading text
//    public void loader(){
//        try{
//            System.out.println("Loading.....");
//            Thread.sleep(1500);
//        } catch (InterruptedException i){
//            throw new UnsupportedOperationException("You got an InterruptedException: " + i.getMessage());
//        }
//    }
//
//    //Loading text
//    public void loaderLong(){
//        try{
//            System.out.println("Loading.....");
//            Thread.sleep(3000);
//        } catch (InterruptedException i){
//            throw new UnsupportedOperationException("You got an InterruptedException: " + i.getMessage());
//        }
//    }
//
//    //Get help
//    public void getHelp(){
//        System.out.println("");
//        System.out.println("Here are the help commands:");
//        System.out.println("[Help]" + "\t" + "Get help and options");
//        System.out.println("[Play]" + "\t" + "Start the game");
//        System.out.println("[Score]" + "\t" + "See your current score");
//        System.out.println("[Board]" + "\t" + "See the question status");
//        System.out.println("[Back]" + "\t" + "Go back to the categories");
//        System.out.println("[Exit]" + "\t" + "Exit the game");
//        loaderLong();
//    }
//
//    //Get help in the game
//    public void getHelpGame(){
//        System.out.println("");
//        System.out.println("You have to choose a category or question by typing: A, B, C, D, E or F.");
//        System.out.println("");
//        System.out.println("Here are the help commands:");
//        System.out.println("[Help]" + "\t" + "Get help and options");
//        System.out.println("[Score]" + "\t" + "See your current score");
//        System.out.println("[Board]" + "\t" + "See the question status");
//        System.out.println("[Exit]" + "\t" + "Exit the game");
//        loaderLong();
//    }
//
//    //Exit the game
//    public void exitGame(String name){
//        System.out.println("");
//        System.out.println("I have never met a successful person that was a quitter. Successful people never, ever, give up! " + name);
//        System.exit(0);
//    }
//
//    //Get player score
//    public void getScore(Player player){
//        System.out.println("");
//        System.out.println(player.getName() + " you have " + player.getScore() + " points.");
//    }
//
//    //Get player score
//    public void getBoardStatus(int boardStatus){
//        System.out.println("");
//        System.out.println("You have answered " + boardStatus + " questions. You still have " + (30-boardStatus) + " to go :)");
//    }
//
//    //Game default message
//    public void gameDefaultMessage(){
//        System.out.println("");
//        System.out.println("Wrong input....");
//        System.out.println("Do you need help? Then just write [help], or [exit] to end the game.");
//    }
//
//
//    /**
//     *
//     * Play Board - Category
//     *
//     */
//
//    //Choose category
//    public String playerCategoryInput(){
//        System.out.println("");
//        System.out.print("Choose a category > ");
//        String input = scanner.nextLine();
//        return input;
//    }
//
//    //Get help
//    public void getHelpCategory(){
//        System.out.println("");
//        System.out.println("You have to choose a category or question by typing: A, B, C, D, E or F.");
//        System.out.println("");
//        System.out.println("Here are the help commands:");
//        System.out.println("[Help]" + "\t" + "Get help and options");
//        System.out.println("[Score]" + "\t" + "See your current score");
//        System.out.println("[Board]" + "\t" + "See the question status");
//        System.out.println("[Exit]" + "\t" + "Exit the game");
//    }
//
//    //Category default message
//    public void categoryDefaultMessage(){
//        System.out.println("");
//        System.out.print("Wrong input....");
//        System.out.println("You have to choose a category: A, B, C, D, E or F.");
//        System.out.println("You can also ask for [help] or [exit] the game...");
//    }
//
//    //Show chosen category
//    public void getCategoryTitle(int index_start, ArrayList<Question_board> list){
//        System.out.println("");
//        System.out.println("You have chosen the Category '" + list.get(index_start).getCategory().getCategoryName() + "', here are the the available questions and possible score prize!");
//    }
//
//
//    /**
//     *
//     * Play Board - Questions
//     *
//     */
//
//    //Show available questions
//    public void availableQuestionsInCategory(int index_start, int index_end, ArrayList<Question_board> list){
//
//        String[] choiseSpots = {"A", "B", "C", "D", "E"};
//        int choiseCount = 0;
//
//        for( int i = index_start ; i < index_end ; i++ ){
//            if(list.get(i).getAnswered() == null){
//                System.out.println(choiseSpots[choiseCount] + ": " + list.get(i).getScore());
//            } else {
//                System.out.println(choiseSpots[choiseCount] + ": Question has already been answered.");
//            }
//            choiseCount ++;
//        }
//
//    }
//
//    //Choose question choice
//    public String playerQuestionInputChoice(){
//        System.out.println("");
//        System.out.print("Choose a question > ");
//        String input = scanner.nextLine();
//        return input;
//    }
//
//    //Get the question
//    public boolean getTheQuestion(ArrayList<Question_board> list, int index_start, int question_index){
//        if(list.get(index_start + question_index).getAnswered() != null){
//            System.out.print("The question has already been played\n");
//            return false;
//        } else {
//            System.out.print(list.get(index_start + question_index).getQuestion());
//            return true;
//        }
//    }
//
//    //Choose question answer
//    public String playerQuestionInputAnswer(){
//        System.out.println("");
//        System.out.print("What is you answer? > ");
//        String input = scanner.nextLine();
//        return input;
//    }
//
//    //Validate answer
//    public void validateAnswer(ArrayList<Question_board> list, int index_start, int question_index, String answer, Player player){
//        if(list.get(index_start + question_index).getAnswer().equalsIgnoreCase(answer)){
//
//            //Message to the player
//            System.out.print("Correct " + player.getName() + "!!! You have earned " + list.get(index_start + question_index).getScore() + " points :)\n");
//
//            //We add the score to the player
//            player.setScore(player.getScore() + list.get(index_start + question_index).getScore());
//
//            //We deactivet the question
//            list.get(index_start + question_index).setAnswered("---");
//
//        } else {
//
//            //Message to the player
//            System.out.print("Incorrect " + player.getName() + "! The right answer is: " + list.get(index_start + question_index).getAnswer() + "\n");
//
//            //We deactivet the question
//            list.get(index_start + question_index).setAnswered("---");
//
//        }
//    }
//
//    //Get help
//    public void getHelpQuestions(){
//        System.out.println("");
//        System.out.println("Here are the help commands:");
//        System.out.println("[Help]" + "\t" + "Get help and options");
//        System.out.println("[Score]" + "\t" + "See your current score");
//        System.out.println("[Board]" + "\t" + "See the question status");
//        System.out.println("[Back]" + "\t" + "Go back to the categories");
//        System.out.println("[Exit]" + "\t" + "Exit the game");
//    }
//
//    //Question default message
//    public void questionsDefaultMessage(){
//        System.out.println("");
//        System.out.print("Wrong input....");
//        System.out.println("You have to choose a question: A, B, C, D or E.");
//        System.out.println("You can also ask for [help] or [exit] the game...");
//    }
//
//
//    /**
//     *
//     * Play Board - Hard
//     *
//     */
//
//    //Get help
//    public void getHardBoardMessage(){
//        System.out.println("");
//        System.out.println("You have reached the hard part of the game. You will now be tested on the hardest questions for maximum prizes :)");
//    }

}
