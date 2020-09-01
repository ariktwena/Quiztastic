package quiztastic.controller;

import quiztastic.core.Board;
import quiztastic.core.Question;

import java.util.ArrayList;
import java.util.Random;

public class boardController {

    Board board;

    public ArrayList<Integer> generateRandomNumbers(ArrayList<Question> list){

        //Create a Integer list
        ArrayList<Integer> randomNumberList = new ArrayList<Integer>();

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
    public void createBoard(ArrayList<Question> list, ArrayList<Integer> randomNumberList){

        System.out.println(board.getHeader());
        System.out.format(board.getCategoryLeftAlignFormat(),
                list.get(randomNumberList.get(0)).getCategory().getCategoryName(),
                list.get(randomNumberList.get(1)).getCategory().getCategoryName(),
                list.get(randomNumberList.get(2)).getCategory().getCategoryName(),
                list.get(randomNumberList.get(3)).getCategory().getCategoryName(),
                list.get(randomNumberList.get(4)).getCategory().getCategoryName(),
                list.get(randomNumberList.get(5)).getCategory().getCategoryName());

        for( int i = 0 ; i < randomNumberList.size() - 1 ; i++ ){
            System.out.println(board.getSeparator());
            System.out.format(board.getScoreLeftAlignFormatRow(),
                    list.get(randomNumberList.get(0) + i).getScore(),
                    list.get(randomNumberList.get(1) + i).getScore(),
                    list.get(randomNumberList.get(2) + i).getScore(),
                    list.get(randomNumberList.get(3) + i).getScore(),
                    list.get(randomNumberList.get(4) + i).getScore(),
                    list.get(randomNumberList.get(5) + i).getScore());
        }
        System.out.println(board.getFooter());



//        System.out.println(board.getHeader());
//        System.out.format(board.getCategoryLeftAlignFormat(),
//                list.get(cat1).getCategory().getCategoryName(),
//                list.get(cat2).getCategory().getCategoryName(),
//                list.get(cat3).getCategory().getCategoryName(),
//                list.get(cat4).getCategory().getCategoryName(),
//                list.get(cat5).getCategory().getCategoryName(),
//                list.get(cat6).getCategory().getCategoryName());
//        System.out.println(board.getSeparator());
//        System.out.format(board.getScoreLeftAlignFormatRow1(),
//                list.get(cat1).getScore(),
//                list.get(cat2).getScore(),
//                list.get(cat3).getScore(),
//                list.get(cat4).getScore(),
//                list.get(cat5).getScore(),
//                list.get(cat6).getScore());
//        System.out.println(board.getSeparator());
//        System.out.format(board.getScoreLeftAlignFormatRow2(),
//                list.get(cat1 + 1).getScore(),
//                list.get(cat2 + 1).getScore(),
//                list.get(cat3 + 1).getScore(),
//                list.get(cat4 + 1).getScore(),
//                list.get(cat5 + 1).getScore(),
//                list.get(cat6 + 1).getScore());
//        System.out.println(board.getSeparator());
//        System.out.format(board.getScoreLeftAlignFormatRow3(),
//                list.get(cat1 + 2).getScore(),
//                list.get(cat2 + 2).getScore(),
//                list.get(cat3 + 2).getScore(),
//                list.get(cat4 + 2).getScore(),
//                list.get(cat5 + 2).getScore(),
//                list.get(cat6 + 2).getScore());
//        System.out.println(board.getSeparator());
//        System.out.format(board.getScoreLeftAlignFormatRow4(),
//                list.get(cat1 + 3).getScore(),
//                list.get(cat2 + 3).getScore(),
//                list.get(cat3 + 3).getScore(),
//                list.get(cat4 + 3).getScore(),
//                list.get(cat5 + 3).getScore(),
//                list.get(cat6 + 3).getScore());
//        System.out.println(board.getSeparator());
//        System.out.format(board.getScoreLeftAlignFormatRow5(),
//                list.get(cat1 + 4).getScore(),
//                list.get(cat2 + 4).getScore(),
//                list.get(cat3 + 4).getScore(),
//                list.get(cat4 + 4).getScore(),
//                list.get(cat5 + 4).getScore(),
//                list.get(cat6 + 4).getScore());
//        System.out.println(board.getFooter());

    }

}
