package quiztastic.controller;

import quiztastic.app.ListQuestionRepository;
import quiztastic.app.QuestionReader;
import quiztastic.core.Board;
import quiztastic.core.Question;
import quiztastic.entry.DisplayQuestions;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {


    //New listQuestionRepository class so we can access the questions list
    ListQuestionRepository listQuestionRepository = new ListQuestionRepository();

    Board board = new Board();

    //New validator class
    Validator validator = new Validator();


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
        ArrayList<Question> questionsModList;

        //Get the original questions list and assign it to the questionsModList variable
        questionsModList = listQuestionRepository.getQuestionsList();

        System.out.println(questionsModList.size());

        //Validate list: Remove wrong scores (0, 5000, 2000, 1200, 1600 etc...)
        questionsModList = validator.removeZeroScore(questionsModList);
        questionsModList = validator.remove1200Score(questionsModList);
        questionsModList = validator.remove1600Score(questionsModList);
        questionsModList = validator.remove2000Score(questionsModList);
        questionsModList = validator.remove3400Score(questionsModList);
        questionsModList = validator.remove4000Score(questionsModList);
        questionsModList = validator.remove5000Score(questionsModList);
        questionsModList = validator.remove6000Score(questionsModList);

        //Validate list: Match 5 questions per category
        questionsModList = validator.matchQuestionsPerCategory(questionsModList);

        //Question array variable, so we can modify det original
        ArrayList<Question> easyQuestionsModList;

        //Add the easy questions to the array
        easyQuestionsModList = validator.matchEasyCategories(questionsModList);

        //Question array variable, so we can modify det original
        ArrayList<Question> hardQuestionsModList;

        //Add the easy questions to the array
        hardQuestionsModList = validator.matchHardCategories(questionsModList);


        System.out.println("All questions: " + (easyQuestionsModList.size() + hardQuestionsModList.size()));
        System.out.println("Easy questions: " + easyQuestionsModList.size());
        System.out.println("Hard questions: " + hardQuestionsModList.size());


        /**
         *
         * Easy Board
         *
         */

        //Question array variable, so we can modify det original
        ArrayList<Integer> easyQuestionsRandomNumbers;

        //Add the random numbers to an array
        easyQuestionsRandomNumbers = generateRandomNumbers(easyQuestionsModList);

        //Create board with easy questions
        createBoard(easyQuestionsModList,
                easyQuestionsRandomNumbers.get(0),
                easyQuestionsRandomNumbers.get(1),
                easyQuestionsRandomNumbers.get(2),
                easyQuestionsRandomNumbers.get(3),
                easyQuestionsRandomNumbers.get(4));


        /**
         *
         * Hard Board
         *
         */

        //Question array variable, so we can modify det original
        ArrayList<Integer> hardQuestionsRandomNumbers;

        //Add the random numbers to an array
        hardQuestionsRandomNumbers = generateRandomNumbers(hardQuestionsModList);

        //Create board with hard questions
        createBoard(hardQuestionsModList,
                hardQuestionsRandomNumbers.get(0),
                hardQuestionsRandomNumbers.get(1),
                hardQuestionsRandomNumbers.get(2),
                hardQuestionsRandomNumbers.get(3),
                hardQuestionsRandomNumbers.get(4));


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
                random1 == random2
                ||
                random1 == random3
                ||
                random1 == random4
                ||
                random1 == random5
                ||
                random2 == random3
                ||
                random2 == random4
                ||
                random2 == random5
                ||
                random3 == random4
                ||
                random3 == random5
                ||
                random4 == random5){

            random1 = rand.nextInt(list.size() - 5);
            random2 = rand.nextInt(list.size() - 5);
            random3 = rand.nextInt(list.size() - 5);
            random4 = rand.nextInt(list.size() - 5);
            random5 = rand.nextInt(list.size() - 5);

        }

//        System.out.println(random1);
//        System.out.println(random2);
//        System.out.println(random3);
//        System.out.println(random4);
//        System.out.println(random5);

        randomNumberList.add(random1);
        randomNumberList.add(random2);
        randomNumberList.add(random3);
        randomNumberList.add(random4);
        randomNumberList.add(random5);

        return randomNumberList;
    }

    public void createBoard(ArrayList<Question> list, int cat1, int cat2, int cat3, int cat4, int cat5){

        System.out.println(board.getHeader());
        System.out.format(board.getCategoryLeftAlignFormat(),
                list.get(cat1).getCategory(),
                list.get(cat2).getCategory(),
                list.get(cat3).getCategory(),
                list.get(cat4).getCategory(),
                list.get(cat5).getCategory());
        System.out.println(board.getSeparator());
        System.out.format(board.getScoreLeftAlignFormatRow1(),
                list.get(cat1).getScore(),
                list.get(cat2).getScore(),
                list.get(cat3).getScore(),
                list.get(cat4).getScore(),
                list.get(cat5).getScore());
        System.out.println(board.getSeparator());
        System.out.format(board.getScoreLeftAlignFormatRow2(),
                list.get(cat1 + 1).getScore(),
                list.get(cat2 + 1).getScore(),
                list.get(cat3 + 1).getScore(),
                list.get(cat4 + 1).getScore(),
                list.get(cat5 + 1).getScore());
        System.out.println(board.getSeparator());
        System.out.format(board.getScoreLeftAlignFormatRow3(),
                list.get(cat1 + 2).getScore(),
                list.get(cat2 + 2).getScore(),
                list.get(cat3 + 2).getScore(),
                list.get(cat4 + 2).getScore(),
                list.get(cat5 + 2).getScore());
        System.out.println(board.getSeparator());
        System.out.format(board.getScoreLeftAlignFormatRow4(),
                list.get(cat1 + 3).getScore(),
                list.get(cat2 + 3).getScore(),
                list.get(cat3 + 3).getScore(),
                list.get(cat4 + 3).getScore(),
                list.get(cat5 + 3).getScore());
        System.out.println(board.getSeparator());
        System.out.format(board.getScoreLeftAlignFormatRow5(),
                list.get(cat1 + 4).getScore(),
                list.get(cat2 + 4).getScore(),
                list.get(cat3 + 4).getScore(),
                list.get(cat4 + 4).getScore(),
                list.get(cat5 + 4).getScore());
        System.out.println(board.getFooter());

    }



}
