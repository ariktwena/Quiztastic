package quiztastic.controller;

import quiztastic.core.Board;
import quiztastic.core.Question;
import quiztastic.core.Question_board;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BoardController {

    Board board;

    public ArrayList<Integer> generateRandomNumbers(ArrayList<Question> list){

        //Create a Integer list
        ArrayList<Integer> randomNumberList = new ArrayList<>();

        // create instance of Random class
        Random rand = new Random();

        // Generate random integers in range 0 to hardQuestionsModList.size() - 5
        int random1 = rand.nextInt(list.size() - 5);
        int random2 = rand.nextInt(list.size() - 5);
        int random3 = rand.nextInt(list.size() - 5);
        int random4 = rand.nextInt(list.size() - 5);
        int random5 = rand.nextInt(list.size() - 5);
        int random6 = rand.nextInt(list.size() - 5);


        while( random1 % 5 != 0
                ||
                random2 % 5 != 0
                ||
                random3 % 5 != 0
                ||
                random4 % 5 != 0
                ||
                random5 % 5 != 0
                ||
                random6 % 5 != 0
                ||
                random1 == random2
                ||
                random1 == random3
                ||
                random1 == random4
                ||
                random1 == random5
                ||
                random1 == random6
                ||
                random2 == random3
                ||
                random2 == random4
                ||
                random2 == random5
                ||
                random2 == random6
                ||
                random3 == random4
                ||
                random3 == random5
                ||
                random3 == random6
                ||
                random4 == random5
                ||
                random4 == random6
                ||
                random5 == random6){

            random1 = rand.nextInt(list.size() - 5);
            random2 = rand.nextInt(list.size() - 5);
            random3 = rand.nextInt(list.size() - 5);
            random4 = rand.nextInt(list.size() - 5);
            random5 = rand.nextInt(list.size() - 5);
            random6 = rand.nextInt(list.size() - 5);

        }

//        System.out.println(random1);
//        System.out.println(random2);
//        System.out.println(random3);
//        System.out.println(random4);
//        System.out.println(random5);
//        System.out.println(random6);

        randomNumberList.add(random1);
        randomNumberList.add(random2);
        randomNumberList.add(random3);
        randomNumberList.add(random4);
        randomNumberList.add(random5);
        randomNumberList.add(random6);

        return randomNumberList;
    }

    //public void createBoard(ArrayList<Question> list, int cat1, int cat2, int cat3, int cat4, int cat5, int cat6){
    public void createBoard(ArrayList<Question_board> list, Socket socket){

        //We create a new board object
        board = new Board();

        try{

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            //We print the header of the board (The list has 30 spots. Every 5 spot is a now line of questions)
            writer.println(board.getHeader());
            writer.format(board.getCategoryLeftAlignFormat(),
                    "A: " + list.get(0).getCategory().getCategoryName(),
                    "B: " +list.get(5).getCategory().getCategoryName(),
                    "C: " +list.get(10).getCategory().getCategoryName(),
                    "D: " +list.get(15).getCategory().getCategoryName(),
                    "E: " +list.get(20).getCategory().getCategoryName(),
                    "F: " +list.get(25).getCategory().getCategoryName());

            //We print 5 rows of point on the board
            for( int i = 0 ; i < 5 ; i++ ){
                writer.println(board.getSeparator());
                writer.format(board.getScoreLeftAlignFormatRow(),
                        list.get(0 + i).getAnswered() == null ? list.get(0 + i).getScore() : list.get(0 + i).getAnswered(),
                        list.get(5 + i).getAnswered() == null ? list.get(5 + i).getScore() : list.get(5 + i).getAnswered(),
                        list.get(10 + i).getAnswered() == null ? list.get(10 + i).getScore() : list.get(10 + i).getAnswered(),
                        list.get(15 + i).getAnswered() == null ? list.get(15 + i).getScore() : list.get(15 + i).getAnswered(),
                        list.get(20 + i).getAnswered() == null ? list.get(20 + i).getScore() : list.get(20 + i).getAnswered(),
                        list.get(25 + i).getAnswered() == null ? list.get(25 + i).getScore() : list.get(25 + i).getAnswered());
            }
            writer.println(board.getFooter());
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

//    //public void createBoard(ArrayList<Question> list, int cat1, int cat2, int cat3, int cat4, int cat5, int cat6){
//    public void createBoard(ArrayList<Question_board> list){
//
//        //We create a new board object
//        board = new Board();
//
//        //We print the header of the board (The list has 30 spots. Every 5 spot is a now line of questions)
//        System.out.println(board.getHeader());
//        System.out.format(board.getCategoryLeftAlignFormat(),
//                "A: " + list.get(0).getCategory().getCategoryName(),
//                "B: " +list.get(5).getCategory().getCategoryName(),
//                "C: " +list.get(10).getCategory().getCategoryName(),
//                "D: " +list.get(15).getCategory().getCategoryName(),
//                "E: " +list.get(20).getCategory().getCategoryName(),
//                "F: " +list.get(25).getCategory().getCategoryName());
//
//        //We print 5 rows of point on the board
//        for( int i = 0 ; i < 5 ; i++ ){
//            System.out.println(board.getSeparator());
//            System.out.format(board.getScoreLeftAlignFormatRow(),
//                    list.get(0 + i).getAnswered() == null ? list.get(0 + i).getScore() : list.get(0 + i).getAnswered(),
//                    list.get(5 + i).getAnswered() == null ? list.get(5 + i).getScore() : list.get(5 + i).getAnswered(),
//                    list.get(10 + i).getAnswered() == null ? list.get(10 + i).getScore() : list.get(10 + i).getAnswered(),
//                    list.get(15 + i).getAnswered() == null ? list.get(15 + i).getScore() : list.get(15 + i).getAnswered(),
//                    list.get(20 + i).getAnswered() == null ? list.get(20 + i).getScore() : list.get(20 + i).getAnswered(),
//                    list.get(25 + i).getAnswered() == null ? list.get(25 + i).getScore() : list.get(25 + i).getAnswered());
//        }
//        System.out.println(board.getFooter());
//
//
//
////        System.out.println(board.getHeader());
////        System.out.format(board.getCategoryLeftAlignFormat(),
////                list.get(cat1).getCategory().getCategoryName(),
////                list.get(cat2).getCategory().getCategoryName(),
////                list.get(cat3).getCategory().getCategoryName(),
////                list.get(cat4).getCategory().getCategoryName(),
////                list.get(cat5).getCategory().getCategoryName(),
////                list.get(cat6).getCategory().getCategoryName());
////        System.out.println(board.getSeparator());
////        System.out.format(board.getScoreLeftAlignFormatRow1(),
////                list.get(cat1).getScore(),
////                list.get(cat2).getScore(),
////                list.get(cat3).getScore(),
////                list.get(cat4).getScore(),
////                list.get(cat5).getScore(),
////                list.get(cat6).getScore());
////        System.out.println(board.getSeparator());
////        System.out.format(board.getScoreLeftAlignFormatRow2(),
////                list.get(cat1 + 1).getScore(),
////                list.get(cat2 + 1).getScore(),
////                list.get(cat3 + 1).getScore(),
////                list.get(cat4 + 1).getScore(),
////                list.get(cat5 + 1).getScore(),
////                list.get(cat6 + 1).getScore());
////        System.out.println(board.getSeparator());
////        System.out.format(board.getScoreLeftAlignFormatRow3(),
////                list.get(cat1 + 2).getScore(),
////                list.get(cat2 + 2).getScore(),
////                list.get(cat3 + 2).getScore(),
////                list.get(cat4 + 2).getScore(),
////                list.get(cat5 + 2).getScore(),
////                list.get(cat6 + 2).getScore());
////        System.out.println(board.getSeparator());
////        System.out.format(board.getScoreLeftAlignFormatRow4(),
////                list.get(cat1 + 3).getScore(),
////                list.get(cat2 + 3).getScore(),
////                list.get(cat3 + 3).getScore(),
////                list.get(cat4 + 3).getScore(),
////                list.get(cat5 + 3).getScore(),
////                list.get(cat6 + 3).getScore());
////        System.out.println(board.getSeparator());
////        System.out.format(board.getScoreLeftAlignFormatRow5(),
////                list.get(cat1 + 4).getScore(),
////                list.get(cat2 + 4).getScore(),
////                list.get(cat3 + 4).getScore(),
////                list.get(cat4 + 4).getScore(),
////                list.get(cat5 + 4).getScore(),
////                list.get(cat6 + 4).getScore());
////        System.out.println(board.getFooter());
//
//    }

}
