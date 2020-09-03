package quiztastic.core;

import java.net.PortUnreachableException;
import java.util.ArrayList;
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
        System.out.println("Here are the roles of the game: ");
        System.out.println("You start by choosing a category A, B, C etc. Then you choose a question for points.");
        System.out.println("If you answer correctly, you get the points.");
        System.out.println("");
        System.out.println("If you ever need help, then just write [help].");
        System.out.println("");
        System.out.println("Write [Play] to start the game. Have a Happy Quizzy :)");
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
        System.out.println("Here are the help commands:");
        System.out.println("[Help]" + "\t" + "Get help and options");
        System.out.println("[Play]" + "\t" + "Start the game");
        System.out.println("[Score]" + "\t" + "See your current score");
        System.out.println("[Board]" + "\t" + "See the question status");
        System.out.println("[Exit]" + "\t" + "Exit the game");
    }

    //Exit the game
    public void exitGame(String name){
        System.out.println("");
        System.out.println("I have never met a successful person that was a quitter. Successful people never, ever, give up! " + name);
        System.exit(0);
    }

    //Get player score
    public void getScore(Player player){
        System.out.println("");
        System.out.println(player.getName() + " you have " + player.getScore() + " points.");
    }

    //Get player score
    public void getBoardStatus(int boardStatus){
        System.out.println("");
        System.out.println("You have answered " + boardStatus + " questions. You still have " + (60-boardStatus) + " to go :)");
    }


    /**
     *
     * Play Board - Category
     *
     */

    //Choose category
    public String playerCategoryInput(){
        System.out.println("");
        System.out.print("Choose a category > ");
        String input = scanner.nextLine();
        return input;
    }

    //Get help
    public void getHelpCategory(){
        System.out.println("");
        System.out.println("Here are the help commands:");
        System.out.println("[Help]" + "\t" + "Get help and options");
        System.out.println("[Score]" + "\t" + "See your current score");
        System.out.println("[Board]" + "\t" + "See the question status");
        System.out.println("[Exit]" + "\t" + "Exit the game");
    }

    //Show chosen category
    public void getCategoryTitle(int index_start, ArrayList<Question_board> list){
        System.out.println("");
        System.out.println("You have chosen the Category '" + list.get(index_start).getCategory().getCategoryName() + "', here are the the available questions and possible score prize!");
    }


    /**
     *
     * Play Board - Questions
     *
     */

    //Show available questions
    public void availableQuestionsInCategory(int index_start, int index_end, ArrayList<Question_board> list){

        String[] choiseSpots = {"A", "B", "C", "D", "E"};
        int choiseCount = 0;

        for( int i = index_start ; i < index_end ; i++ ){
            if(list.get(i).getAnswered() == null){
                System.out.println(choiseSpots[choiseCount] + ": " + list.get(i).getScore());
            } else {
                System.out.println(choiseSpots[choiseCount] + ": Question has already been answered.");
            }
            choiseCount ++;
        }

    }

    //Choose question choice
    public String playerQuestionInputChoice(){
        System.out.println("");
        System.out.print("Choose a question > ");
        String input = scanner.nextLine();
        return input;
    }

    //Get the question
    public boolean getTheQuestion(ArrayList<Question_board> list, int index_start, int question_index){
        if(list.get(index_start + question_index).getAnswered() != null){
            System.out.print("The question has already been played");
            return false;
        } else {
            System.out.print(list.get(index_start + question_index).getQuestion());
            return true;
        }
    }

    //Choose question answer
    public String playerQuestionInputAnswer(){
        System.out.println("");
        System.out.print("What is you answer? > ");
        String input = scanner.nextLine();
        return input;
    }

    //Validate answer
    public void validateAnswer(ArrayList<Question_board> list, int index_start, int question_index, String answer, Player player){
        if(list.get(index_start + question_index).getAnswer().equalsIgnoreCase(answer)){

            //Message to the player
            System.out.print("Correct " + player.getName() + "!!! You have earned " + list.get(index_start + question_index).getScore() + " points :)\n");

            //We add the score to the player
            player.setScore(player.getScore() + list.get(index_start + question_index).getScore());

            //We deactivet the question
            list.get(index_start + question_index).setAnswered("---");

        } else {

            //Message to the player
            System.out.print("Incorrect " + player.getName() + "! The right answer is: " + list.get(index_start + question_index).getAnswer() + "\n");

            //We deactivet the question
            list.get(index_start + question_index).setAnswered("---");

        }
    }

    //Get help
    public void getHelpQuestions(){
        System.out.println("");
        System.out.println("Here are the help commands:");
        System.out.println("[Help]" + "\t" + "Get help and options");
        System.out.println("[Score]" + "\t" + "See your current score");
        System.out.println("[Board]" + "\t" + "See the question status");
        System.out.println("[Back]" + "\t" + "Go back to the categories");
        System.out.println("[Exit]" + "\t" + "Exit the game");
    }


    /**
     *
     * Play Board - Hard
     *
     */

    //Get help
    public void getHardBoardMessage(){
        System.out.println("");
        System.out.println("You have reached the hard part of the game. You will now be tested on the hardest questions for maximum prizes :)");
    }

}
