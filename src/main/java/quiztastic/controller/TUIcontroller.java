package quiztastic.controller;

import quiztastic.core.Question_board;
import quiztastic.core.TUI;

import java.util.ArrayList;
import java.util.Scanner;

public class TUIcontroller {

    TUI tui = new TUI();

    BoardController boardController = new BoardController();

    public void printHelp(String name, ArrayList<Question_board> easyList, ArrayList<Question_board> hardList){

        while (true){

            switch (tui.playerInput().toLowerCase()){
                case "play":
                    tui.loader();
                    loadBoard(easyList);
                    break;
                case "help":
                    tui.loader();
                    tui.getHelp();
                    break;
                case "exit":
                    tui.exitGame(name);
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

    public void playBoard(String name, ArrayList<Question_board> list){

        while (boardController.board.getStatus() <= 30){

            switch (tui.playerCategoryInput().toLowerCase()){
                case "a":

                    break;
                case "b":

                    break;
                case "c":

                    break;
                case "d":

                    break;
                case "e":

                    break;
                case "f":

                    break;
                case "help":
                    tui.loader();
                    tui.getHelp();
                    break;
                case "exit":
                    tui.exitGame(name);
                    break;
                default:
                    System.out.println("");
                    System.out.println("You have to choose a category: A, B, C, D, E or F. You can ask for []");
                    System.out.println("You can also ask for [help] or [exit] the game...");
            }

        }
    }





}
