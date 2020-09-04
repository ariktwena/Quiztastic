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

        int boardStatus = 0;

        while (boardStatus < 30){

            switch (tui.playerInput().toLowerCase()){
                case "play":
                    tui.loader();
                    playBoardAndGetCategories(player, list, boardStatus);
                    break;
                case "help":
                    tui.loader();
                    tui.getHelp();
                    break;
                case "score":
                    tui.getScore(player);
                    break;
                case "board":
                    tui.getBoardStatus(boardStatus);
                    break;
                case "exit":
                    tui.exitGame(player.getName());
                    break;
                default:
                    tui.gameDefaultMessage();
            }

        }

    }

    public void loadBoard(ArrayList<Question_board> list){
        boardController.createBoard(list);
    }

    public void playBoardAndGetCategories(Player player, ArrayList<Question_board> list, int boardStatus){

        int index_start, index_end;

        loadBoard(list);

        while (boardStatus < 30){

            switch (tui.playerCategoryInput().toLowerCase()){
                case "a":
                    index_start = 0;
                    index_end = 5;
                    tui.getCategoryTitle(index_start, list);
                    tui.availableQuestionsInCategory(index_start, index_end, list);
                    playQuestions(player, list, index_start, boardStatus);
                    break;
                case "b":
                    index_start = 5;
                    index_end = 10;
                    tui.getCategoryTitle(index_start, list);
                    tui.availableQuestionsInCategory(index_start, index_end, list);
                    playQuestions(player, list, index_start, boardStatus);
                    break;
                case "c":
                    index_start = 10;
                    index_end = 15;
                    tui.getCategoryTitle(index_start, list);
                    tui.availableQuestionsInCategory(index_start, index_end, list);
                    playQuestions(player, list, index_start, boardStatus);
                    break;
                case "d":
                    index_start = 15;
                    index_end = 20;
                    tui.getCategoryTitle(index_start, list);
                    tui.availableQuestionsInCategory(index_start, index_end, list);
                    playQuestions(player, list, index_start, boardStatus);
                    break;
                case "e":
                    index_start = 20;
                    index_end = 25;
                    tui.getCategoryTitle(index_start, list);
                    tui.availableQuestionsInCategory(index_start, index_end, list);
                    playQuestions(player, list, index_start, boardStatus);
                    break;
                case "f":
                    index_start = 25;
                    index_end = 30;
                    tui.getCategoryTitle(index_start, list);
                    tui.availableQuestionsInCategory(index_start, index_end, list);
                    playQuestions(player, list, index_start, boardStatus);
                    break;
                case "help":
                    tui.loader();
                    tui.getHelpCategory();
                    break;
                case "score":
                    tui.getScore(player);
                    break;
                case "board":
                    tui.getBoardStatus(boardStatus);
                    break;
                case "exit":
                    tui.exitGame(player.getName());
                    break;
                default:
                    tui.categoryDefaultMessage();
            }

        }
    }

    public void playQuestions(Player player, ArrayList<Question_board> list, int index_start, int boardStatus){

        int question_index;

        while (boardStatus < 30){

            switch (tui.playerQuestionInputChoice().toLowerCase()){
                case "a":
                    question_index = 0;
                    //If the question is active
                    if(tui.getTheQuestion(list, index_start, question_index)){
                        String answer = tui.playerQuestionInputAnswer();
                        tui.validateAnswer(list, index_start, question_index, answer, player);
                        boardStatus += 1;
                    };
                    //Restart categories
                    tui.loader();
                    playBoardAndGetCategories(player, list, boardStatus);
                    break;
                case "b":
                    question_index = 1;
                    //If the question is active
                    if(tui.getTheQuestion(list, index_start, question_index)){
                        String answer = tui.playerQuestionInputAnswer();
                        tui.validateAnswer(list, index_start, question_index, answer, player);
                        boardStatus += 1;
                    };
                    //Restart categories
                    tui.loader();
                    playBoardAndGetCategories(player, list, boardStatus);
                    break;
                case "c":
                    question_index = 2;
                    //If the question is active
                    if(tui.getTheQuestion(list, index_start, question_index)){
                        String answer = tui.playerQuestionInputAnswer();
                        tui.validateAnswer(list, index_start, question_index, answer, player);
                        boardStatus += 1;
                    };
                    //Restart categories
                    tui.loader();
                    playBoardAndGetCategories(player, list, boardStatus);
                    break;
                case "d":
                    question_index = 3;
                    //If the question is active
                    if(tui.getTheQuestion(list, index_start, question_index)){
                        String answer = tui.playerQuestionInputAnswer();
                        tui.validateAnswer(list, index_start, question_index, answer, player);
                        boardStatus += 1;
                    };
                    //Restart categories
                    tui.loader();
                    playBoardAndGetCategories(player, list, boardStatus);
                    break;
                case "e":
                    question_index = 4;
                    //If the question is active
                    if(tui.getTheQuestion(list, index_start, question_index)){
                        String answer = tui.playerQuestionInputAnswer();
                        tui.validateAnswer(list, index_start, question_index, answer, player);
                        boardStatus += 1;
                    };
                    //Restart categories
                    tui.loader();
                    playBoardAndGetCategories(player, list, boardStatus);
                    break;
                case "help":
                    tui.loader();
                    tui.getHelpQuestions();
                    break;
                case "score":
                    tui.getScore(player);
                    break;
                case "board":
                    tui.getBoardStatus(boardStatus);
                    break;
                case "back":
                    tui.loader();
                    playBoardAndGetCategories(player, list, boardStatus);
                    break;
                case "exit":
                    tui.exitGame(player.getName());
                    break;
                default:
                    tui.questionsDefaultMessage();
            }

        }
    }





}
