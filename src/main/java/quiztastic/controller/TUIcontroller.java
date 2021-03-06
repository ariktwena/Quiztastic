package quiztastic.controller;

import quiztastic.core.Player;
import quiztastic.core.Question_board;
import quiztastic.core.TUI;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TUIcontroller {


    private final TUI tui;

    Player player;

    BoardController boardController = new BoardController();

    //State
    int numberOfAnswers = 0;
    boolean gameStart = false;

    public TUIcontroller() {
    }


    //Create player
    public Player createPlayer(Socket socket){

        //Create player name
        player = new Player(tui.getPlayerName(socket));

        //Return player
        return player;
    }

    //Creates the playing board from a list og questions
    public void loadBoard(ArrayList<Question_board> list, Socket socket){

        //Displays the bord to the player
        boardController.createBoard(list, socket);
    }

    //Starts the UI game
    public void startGame(Player player, ArrayList<Question_board> list, Socket socket){

        //Player start game choice
        String playerStartChoice = tui.playerInput(socket).toLowerCase();

        //If the player write "play" the game starts
        if(playerStartChoice.equalsIgnoreCase("play")){

            //Game starts
            playBoardAndGetCategories(player, list, numberOfAnswers, socket);

        } else {

            //If the player answer doesn't match the answerIndex, we got to the switch statements
            getSwitch(player, list, numberOfAnswers, playerStartChoice, socket);

            //Go back to category choice via "redirect
            redirectAfterSwitch(player, list, numberOfAnswers, gameStart, socket);
        }



    }

    //Show the board categories and selector to the player
    public void playBoardAndGetCategories(Player player, ArrayList<Question_board> list, int numberOfAnswers, Socket socket){

        int index_start, index_end;

        //Set the gameStart to true so the player can be redirected
        gameStart = true;

        if (numberOfAnswers != 30) {

            //Load the current status of the board
            loadBoard(list, socket);

            //A list of the answers possibilities for the categories/questions
            List<String> answerIndex = List.of("a", "b", "c", "d", "e", "f");

            //Player category choice
            String playerCategoryChoice = tui.playerCategoryInput(socket).toLowerCase();

            //We check if the players input is on the list and return the index number. Else it returns -1
            int input_index_categoty = answerIndex.lastIndexOf(playerCategoryChoice);

            //If the input_index is greater den -1, list contains the player answer/input
            if(input_index_categoty >= 0){

                //We calculate the index_start: 0, 5, 10, 15, 20, 25. Example third element with index 2 => 10
                index_start = 5 * input_index_categoty;

                //We calculate the index_start: 5, 10, 15, 20, 25, 30. Example third element with index 2 => 15
                index_end = 5 + (5 * input_index_categoty);

                //Get the category title to display
                tui.getCategoryTitle(index_start, list, socket);

                //Get the available questions and non if they are answered
                tui.availableQuestionsInCategory(index_start, index_end, list, socket);

                //Player Question choice
                String playerQuestionChoice = tui.playerQuestionInputChoice(socket).toLowerCase();

                //We check if the players input is on the list and return the index number. Else it returns -1
                int input_index_question = answerIndex.lastIndexOf(playerQuestionChoice);

                //If the input_index is greater den -1, list contains the player answer/input
                if(input_index_question >= 0){

                    //input_index_question will be 0, 1, 2, 3 or 4
                    int question_index = input_index_question;

                    //We check if the question is NOT answered og else the methode will display a message to the player
                    if(tui.getTheQuestion(list, index_start, question_index, socket)){

                        //Player answer
                        String answer = tui.playerQuestionInputAnswer(socket);

                        //We check if the players answer matches the right answer
                        tui.validateAnswer(list, index_start, question_index, answer, player, socket);

                        //We update the number of questions that have been played
                        numberOfAnswers += 1;
                    }

                    //We restart the categories by showing the "loader"
                    tui.loaderLong(socket);

                    //Go back to category choice via "redirect
                    redirectAfterSwitch(player, list, numberOfAnswers, gameStart, socket);

                } else {

                    //If the player answer doesn't match the answerIndex, we got to the switch statements
                    getSwitch(player, list, numberOfAnswers, playerQuestionChoice, socket);

                    //Go back to category choice via "redirect
                    redirectAfterSwitch(player, list, numberOfAnswers, gameStart, socket);

                }
            } else {

                //If the player answer doesn't match the answerIndex, we got to the switch statements
                getSwitch(player, list, numberOfAnswers, playerCategoryChoice, socket);

                //Go back to category choice via "redirect
                redirectAfterSwitch(player, list, numberOfAnswers, gameStart, socket);

            }
        } else {

            //Reset boardStatus for the next game
            numberOfAnswers = 0;

        }

    }

    public void getSwitch(Player player, ArrayList<Question_board> list, int numberOfAnswers, String playerInput, Socket socket){
        switch (playerInput) {
            case "help":
                tui.loader(socket);
                tui.getHelpGame(socket);
                break;
            case "score":
                tui.getScore(player, socket);
                break;
            case "board":
                tui.getBoardStatus(numberOfAnswers, socket);
                break;
            case "back":
                tui.loader(socket);
                playBoardAndGetCategories(player, list, numberOfAnswers, socket);
                break;
            case "exit":
                tui.exitGame(player.getName(), socket);
                break;
            default:
                tui.gameDefaultMessage(socket);
        }
    }

    public void redirectAfterSwitch(Player player, ArrayList<Question_board> list, int numberOfAnswers, boolean gameStart, Socket socket){
        //Checks if the game has started though the gameStart boolean
        if(gameStart){

            //Redirects to the category selector
            playBoardAndGetCategories(player, list, numberOfAnswers, socket);

        } else {

            //Redirects to the start game screen where you can write "play"
            startGame(player, list, socket);

        }
    }


//    public void startGame(Player player, ArrayList<Question_board> list){
//
//        switch (tui.playerInput().toLowerCase()) {
//            case "play":
//                tui.loader();
//                playBoardAndGetCategories(player, list, numberOfAnswers);
//                break;
//            case "help":
//                tui.loader();
//                tui.getHelp();
//                startGame(player, list);
//                break;
//            case "score":
//                tui.getScore(player);
//                startGame(player, list);
//                break;
//            case "board":
//                tui.getBoardStatus(numberOfAnswers);
//                startGame(player, list);
//                break;
//            case "exit":
//                tui.exitGame(player.getName());
//                break;
//            default:
//                tui.gameDefaultMessage();
//                startGame(player, list);
//        }
//
//    }

//    public void playBoardAndGetCategories(Player player, ArrayList<Question_board> list, int boardStatus){
//
//        int index_start, index_end;
//
//        if (boardStatus != 30) {
//
//            loadBoard(list);
//
//            switch (tui.playerCategoryInput().toLowerCase()) {
//                case "a":
//                    index_start = 0;
//                    index_end = 5;
//                    tui.getCategoryTitle(index_start, list);
//                    tui.availableQuestionsInCategory(index_start, index_end, list);
//                    playQuestions(player, list, index_start, boardStatus);
//                    break;
//                case "b":
//                    index_start = 5;
//                    index_end = 10;
//                    tui.getCategoryTitle(index_start, list);
//                    tui.availableQuestionsInCategory(index_start, index_end, list);
//                    playQuestions(player, list, index_start, boardStatus);
//                    break;
//                case "c":
//                    index_start = 10;
//                    index_end = 15;
//                    tui.getCategoryTitle(index_start, list);
//                    tui.availableQuestionsInCategory(index_start, index_end, list);
//                    playQuestions(player, list, index_start, boardStatus);
//                    break;
//                case "d":
//                    index_start = 15;
//                    index_end = 20;
//                    tui.getCategoryTitle(index_start, list);
//                    tui.availableQuestionsInCategory(index_start, index_end, list);
//                    playQuestions(player, list, index_start, boardStatus);
//                    break;
//                case "e":
//                    index_start = 20;
//                    index_end = 25;
//                    tui.getCategoryTitle(index_start, list);
//                    tui.availableQuestionsInCategory(index_start, index_end, list);
//                    playQuestions(player, list, index_start, boardStatus);
//                    break;
//                case "f":
//                    index_start = 25;
//                    index_end = 30;
//                    tui.getCategoryTitle(index_start, list);
//                    tui.availableQuestionsInCategory(index_start, index_end, list);
//                    playQuestions(player, list, index_start, boardStatus);
//                    break;
//                case "help":
//                    tui.loader();
//                    tui.getHelpCategory();
//                    break;
//                case "score":
//                    tui.getScore(player);
//                    break;
//                case "board":
//                    tui.getBoardStatus(boardStatus);
//                    break;
//                case "exit":
//                    tui.exitGame(player.getName());
//                    break;
//                default:
//                    tui.categoryDefaultMessage();
//                    playBoardAndGetCategories(player, list, boardStatus);
//            }
//
//        }
//
//    }

//    public void playQuestions(Player player, ArrayList<Question_board> list, int index_start, int boardStatus){
//
//        int question_index;
//
//        if (boardStatus != 30){
//
//            switch (tui.playerQuestionInputChoice().toLowerCase()){
//                case "a":
//                    question_index = 0;
//                    //If the question is active
//                    if(tui.getTheQuestion(list, index_start, question_index)){
//                        String answer = tui.playerQuestionInputAnswer();
//                        tui.validateAnswer(list, index_start, question_index, answer, player);
//                        boardStatus += 1;
//                    };
//
//                    //Restart categories
//                    tui.loader();
//                    playBoardAndGetCategories(player, list, boardStatus);
//                    break;
//                case "b":
//                    question_index = 1;
//                    //If the question is active
//                    if(tui.getTheQuestion(list, index_start, question_index)){
//                        String answer = tui.playerQuestionInputAnswer();
//                        tui.validateAnswer(list, index_start, question_index, answer, player);
//                        boardStatus += 1;
//                    };
//                    //Restart categories
//                    tui.loader();
//                    playBoardAndGetCategories(player, list, boardStatus);
//                    break;
//                case "c":
//                    question_index = 2;
//                    //If the question is active
//                    if(tui.getTheQuestion(list, index_start, question_index)){
//                        String answer = tui.playerQuestionInputAnswer();
//                        tui.validateAnswer(list, index_start, question_index, answer, player);
//                        boardStatus += 1;
//                    };
//                    //Restart categories
//                    tui.loader();
//                    playBoardAndGetCategories(player, list, boardStatus);
//                    break;
//                case "d":
//                    question_index = 3;
//                    //If the question is active
//                    if(tui.getTheQuestion(list, index_start, question_index)){
//                        String answer = tui.playerQuestionInputAnswer();
//                        tui.validateAnswer(list, index_start, question_index, answer, player);
//                        boardStatus += 1;
//                    };
//                    //Restart categories
//                    tui.loader();
//                    playBoardAndGetCategories(player, list, boardStatus);
//                    break;
//                case "e":
//                    question_index = 4;
//                    //If the question is active
//                    if(tui.getTheQuestion(list, index_start, question_index)){
//                        String answer = tui.playerQuestionInputAnswer();
//                        tui.validateAnswer(list, index_start, question_index, answer, player);
//                        boardStatus += 1;
//                    };
//                    //Restart categories
//                    tui.loader();
//                    playBoardAndGetCategories(player, list, boardStatus);
//                    break;
//                case "help":
//                    tui.loader();
//                    tui.getHelpQuestions();
//                    break;
//                case "score":
//                    tui.getScore(player);
//                    break;
//                case "board":
//                    tui.getBoardStatus(boardStatus);
//                    break;
//                case "back":
//                    tui.loader();
//                    playBoardAndGetCategories(player, list, boardStatus);
//                    break;
//                case "exit":
//                    tui.exitGame(player.getName());
//                    break;
//                default:
//                    tui.questionsDefaultMessage();
//                    playQuestions(player, list, index_start, boardStatus);
//            }
//
//        }
//    }





}
