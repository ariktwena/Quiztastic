package quiztastic.controller;

import quiztastic.app.ListQuestionRepository;
import quiztastic.app.QuestionReader;
import quiztastic.core.*;

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

    //New question controller class
    QuestionsController questionsController = new QuestionsController();

    //New board controller class
    BoardController boardController = new BoardController();

    //New TUI controller class
    TUIcontroller tuiController = new TUIcontroller();

    Player player;


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
        questionsModList = questionsController.removeWrongScores(questionsModList);

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
        ArrayList<Integer> easyCategoryRandomNumbers;

        //Add the random numbers to an array
        easyCategoryRandomNumbers = boardController.generateRandomNumbers(easyQuestionsModList);

        //Make a list of the category/questions that go into the board
        ArrayList<Question_board> easyBoardQuestions;

        //We extract the questions that go on the board
        easyBoardQuestions = questionsController.extractBoardQuestions(easyQuestionsModList, easyCategoryRandomNumbers);

        //Create board with easy questions
        //boardController.createBoard(easyBoardQuestions);


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
        ArrayList<Integer> hardCategoryRandomNumbers;

        //Add the random numbers to an array
        hardCategoryRandomNumbers = boardController.generateRandomNumbers(hardQuestionsModList);

        //Make a list of the category/questions that go into the board
        ArrayList<Question_board> hardBoardQuestions;

        //We extract the questions that go on the board
        hardBoardQuestions = questionsController.extractBoardQuestions(hardQuestionsModList, hardCategoryRandomNumbers);

        //Create board with hard questions
        //boardController.createBoard(hardBoardQuestions);






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




        player = new Player(tuiController.tui.getPlayerName());

        tuiController.startGame(player, easyBoardQuestions);
        //tuiController.printHelp(player, hardBoardQuestions);

    }


//    private void testRemoveFromArrayList(){
//        ArrayList<Integer> numbers = new ArrayList<Integer>();
//        ArrayList<Integer> numbers1 = new ArrayList<Integer>();
//
//        numbers.add(1);
//        numbers.add(2);
//        numbers.add(3);
//        numbers.add(4);
//        numbers.add(5);
//
//
//        for(int i = 0 ; i < numbers.size() ; i++){
//            numbers.remove(i);
//        }
//
//        for(int i = 0 ; i < numbers.size() ; i++){
//            System.out.println(numbers.get(i));
//        }
//
//
//        numbers1.add(1);
//        numbers1.add(2);
//        numbers1.add(3);
//        numbers1.add(4);
//        numbers1.add(5);
//
//        for(int i = 0 ; i < numbers1.size() ; i++){
//            numbers1.remove(i);
//            i--;
//        }
//
//        for(int i = 0 ; i < numbers1.size() ; i++){
//            System.out.println(numbers1.get(i));
//        }
//    }


}
