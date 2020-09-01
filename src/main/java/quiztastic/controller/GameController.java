package quiztastic.controller;

import quiztastic.app.ListQuestionRepository;
import quiztastic.app.QuestionReader;
import quiztastic.core.Board;
import quiztastic.core.Category;
import quiztastic.core.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GameController {


    //New listQuestionRepository class so we can access the questions list
    ListQuestionRepository listQuestionRepository = new ListQuestionRepository();

    Board board = new Board();

    //New validator class
    QuestionsController questionsController = new QuestionsController();


    public void init(InputStream inputStream) throws IOException, ParseException {

        //Initialize Question reader with the path to the questions file
        QuestionReader questionReader = new QuestionReader(new InputStreamReader(inputStream));

        //Create a question object
        Question question;

        //Loop trough the data file via the reader, and extract valid questions that match the questions length
        while((question = questionReader.readQuestion()) != null){

                //Add all questions to the questions array list one by one
                listQuestionRepository.addQuestionsToList(question);

        }

        //Question array variable, so we can modify det original
        List<Question> questionsModList;

        //Get the original questions list and assign it to the questionsModList variable
        questionsModList = listQuestionRepository.getQuestionsList();

        System.out.println(questionsModList.size());

        //Validate list: Remove wrong scores (0, 5000, 2000, 1200, 1600 etc...)
//        questionsModList = questionsController.removeZeroScore(questionsModList);
//        questionsModList = questionsController.remove1200Score(questionsModList);
//        questionsModList = questionsController.remove1600Score(questionsModList);
//        questionsModList = questionsController.remove2000Score(questionsModList);
//        questionsModList = questionsController.remove3400Score(questionsModList);
//        questionsModList = questionsController.remove4000Score(questionsModList);
//        questionsModList = questionsController.remove5000Score(questionsModList);
//        questionsModList = questionsController.remove6000Score(questionsModList);

        //

        //Question array variable, so we can modify det original
        ArrayList<Question> ListOrderdByCategory;

        //Validate list: Match 5 questions per category
        ListOrderdByCategory = questionsController.matchQuestionsPerCategory(questionsModList);

        /**
         *
         * Easy Questions
         *
         */

        //Question array variable, so we can modify det original
        ArrayList<Question> easyQuestionsModList;

        //Add the easy questions to the array
        easyQuestionsModList = questionsController.matchEasyCategories(ListOrderdByCategory);

        //We make a Hashmap to link the categories to the questions
        HashMap<Category, ArrayList<Question>> easyCategoryHashMap;

        //We add the questions to the category => link
        easyCategoryHashMap = questionsController.convertArrayToHashMap(easyQuestionsModList);

        //We make a Category list for the easy questions
        ArrayList<Category> easyCategoryList;

        //We add the Categories to the list
        easyCategoryList = questionsController.extractCategories(easyCategoryHashMap);

        //Question array variable, so we can modify det original
        ArrayList<Integer> easyQuestionsRandomNumbers;

        //Add the random numbers to an array
        easyQuestionsRandomNumbers = generateRandomNumbers(easyQuestionsModList);

        //Create board with easy questions
//        createBoard(easyQuestionsModList,
//                easyQuestionsRandomNumbers.get(0),
//                easyQuestionsRandomNumbers.get(1),
//                easyQuestionsRandomNumbers.get(2),
//                easyQuestionsRandomNumbers.get(3),
//                easyQuestionsRandomNumbers.get(4),
//                easyQuestionsRandomNumbers.get(5));
        createBoard(easyQuestionsModList, easyQuestionsRandomNumbers);

        //
//        for( int i = 0 ; i < easyQuestionsModList.size() ; i++ ){
//            System.out.println("Index: " + i + " " + easyQuestionsModList.get(i).toString());
//        }








        /**
         *
         * Hard Board
         *
         */


        //Question array variable, so we can modify det original
        ArrayList<Question> hardQuestionsModList;

        //Add the easy questions to the array
        hardQuestionsModList = questionsController.matchHardCategories(ListOrderdByCategory);

        //We make a Hashmap to link the categories to the questions
        HashMap<Category, ArrayList<Question>> hardCategoryHashMap;

        //We add the questions to the category => link
        hardCategoryHashMap = questionsController.convertArrayToHashMap(hardQuestionsModList);

        //We make a Category list for the easy questions
        ArrayList<Category> hardCategoryList;

        //We add the Categories to the list
        hardCategoryList = questionsController.extractCategories(hardCategoryHashMap);

        //Question array variable, so we can modify det original
        ArrayList<Integer> hardQuestionsRandomNumbers;

        //Add the random numbers to an array
        hardQuestionsRandomNumbers = generateRandomNumbers(hardQuestionsModList);

        //Create board with hard questions
//        createBoard(hardQuestionsModList,
//                hardQuestionsRandomNumbers.get(0),
//                hardQuestionsRandomNumbers.get(1),
//                hardQuestionsRandomNumbers.get(2),
//                hardQuestionsRandomNumbers.get(3),
//                hardQuestionsRandomNumbers.get(4),
//                easyQuestionsRandomNumbers.get(5));
        createBoard(hardQuestionsModList,hardQuestionsRandomNumbers);


//        for( int i = 0 ; i < hardQuestionsModList.size() ; i++ ){
//            System.out.println("Index: " + i + " " + hardQuestionsModList.get(i).toString());
//        }


        /**
         *
         *
         * Total questions
         *
         */

        System.out.println("All questions: " + (easyQuestionsModList.size() + hardQuestionsModList.size()));
        System.out.println("Easy questions: " + easyQuestionsModList.size() + " categories: " + easyCategoryList.size());
        System.out.println("Hard questions: " + hardQuestionsModList.size() + " categories: " + hardCategoryList.size());


    }

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
