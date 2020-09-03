package quiztastic.controller;

import quiztastic.core.Player;
import quiztastic.core.Question_board;
import quiztastic.core.TUI;

import java.util.ArrayList;
import java.util.Scanner;

public class TUIcontroller {

    TUI tui = new TUI();

    BoardController boardController = new BoardController();

    public void startGame(Player player, ArrayList<Question_board> list){

        while (true){

            switch (tui.playerInput().toLowerCase()){
                case "play":
                    tui.loader();
                    loadBoard(list);
                    playBoardAndGetCategories(player, list);
                    break;
                case "help":
                    tui.loader();
                    tui.getHelp();
                    break;
                case "exit":
                    tui.exitGame(player.getName());
                    break;
                default:
                    System.out.println("");
                    System.out.println("Do you need help? Then just write [help], or [exit] to end the game.");
            }

        }

    }

    public void loadBoard(ArrayList<Question_board> list){
        boardController.createBoard(list);
    }

    public void playBoardAndGetCategories(Player player, ArrayList<Question_board> list){

        int index_start, index_end;

        while (boardController.board.getStatus() <= 30){

            switch (tui.playerCategoryInput().toLowerCase()){
                case "a":
                    index_start = 0;
                    index_end = 5;
                    tui.getCategoryTitle(index_start, list);
                    tui.availableQuestionsInCategory(index_start, index_end, list);

                    break;
                case "b":
                    index_start = 5;
                    index_end = 10;
                    tui.getCategoryTitle(index_start, list);
                    tui.availableQuestionsInCategory(index_start, index_end, list);

                    break;
                case "c":
                    index_start = 10;
                    index_end = 15;
                    tui.getCategoryTitle(index_start, list);
                    tui.availableQuestionsInCategory(index_start, index_end, list);

                    break;
                case "d":
                    index_start = 15;
                    index_end = 20;
                    tui.getCategoryTitle(index_start, list);
                    tui.availableQuestionsInCategory(index_start, index_end, list);

                    break;
                case "e":
                    index_start = 20;
                    index_end = 25;
                    tui.getCategoryTitle(index_start, list);
                    tui.availableQuestionsInCategory(index_start, index_end, list);

                    break;
                case "f":
                    index_start = 25;
                    index_end = 30;
                    tui.getCategoryTitle(index_start, list);
                    tui.availableQuestionsInCategory(index_start, index_end, list);

                    break;
                case "help":
                    tui.loader();
                    tui.getHelpCategory();
                    break;
                case "exit":
                    tui.exitGame(player.getName());
                    break;
                default:
                    System.out.println("");
                    System.out.print("Wrong input....");
                    System.out.println("You have to choose a category: A, B, C, D, E or F.");
                    System.out.println("You can also ask for [help] or [exit] the game...");
            }

        }
    }

    public void playQuestions(Player player, ArrayList<Question_board> list, int index_start){

        int question_index;

        while (boardController.board.getStatus() <= 30){

            switch (tui.playerQuestionInputChoice().toLowerCase()){
                case "a":
                    question_index = 0;
                    if(tui.getTheQuestion(list, index_start, question_index)){
                        String answer = tui.playerQuestionInputAnswer();
                        tui.validateAnswer(list, index_start, question_index, answer, player);
                        boardController.board.setStatus(boardController.board.getStatus() + 1);
                    };

                    break;
                case "b":
                    question_index = 1;
                    if(tui.getTheQuestion(list, index_start, question_index)){
                        String answer = tui.playerQuestionInputAnswer();
                        tui.validateAnswer(list, index_start, question_index, answer, player);
                        boardController.board.setStatus(boardController.board.getStatus() + 1);
                    };

                    break;
                case "c":
                    question_index = 2;
                    if(tui.getTheQuestion(list, index_start, question_index)){
                        String answer = tui.playerQuestionInputAnswer();
                        tui.validateAnswer(list, index_start, question_index, answer, player);
                        boardController.board.setStatus(boardController.board.getStatus() + 1);
                    };

                    break;
                case "d":
                    question_index = 3;
                    if(tui.getTheQuestion(list, index_start, question_index)){
                        String answer = tui.playerQuestionInputAnswer();
                        tui.validateAnswer(list, index_start, question_index, answer, player);
                        boardController.board.setStatus(boardController.board.getStatus() + 1);
                    };

                    break;
                case "e":
                    question_index = 4;
                    if(tui.getTheQuestion(list, index_start, question_index)){
                        String answer = tui.playerQuestionInputAnswer();
                        tui.validateAnswer(list, index_start, question_index, answer, player);
                        boardController.board.setStatus(boardController.board.getStatus() + 1);
                    };

                    break;
                case "help":
                    tui.loader();
                    tui.getHelpQuestions();
                    break;
                case "exit":
                    tui.exitGame(player.getName());
                    break;
                default:
                    System.out.println("");
                    System.out.print("Wrong input....");
                    System.out.println("You have to choose a category: A, B, C, D, E or F.");
                    System.out.println("You can also ask for [help] or [exit] the game...");
            }

        }
    }





}
