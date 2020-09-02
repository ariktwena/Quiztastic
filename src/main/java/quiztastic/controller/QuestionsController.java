package quiztastic.controller;


import quiztastic.core.Category;
import quiztastic.core.Question;
import quiztastic.core.Question_board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionsController {

    //Remove questions where score is 0
    public List<Question> removeWrongScores(List<Question> list){

        for( int i = 0 ; i < list.size() ; i++ ){

            //Check if the score equals the values
            if (list.get(i).getScore() == 0
                    ||
                    list.get(i).getScore() == 1200
                    ||
                    list.get(i).getScore() == 1600
                    ||
                    list.get(i).getScore() == 2000
                    ||
                    list.get(i).getScore() == 3400
                    ||
                    list.get(i).getScore() == 3800
                    ||
                    list.get(i).getScore() == 4000
                    ||
                    list.get(i).getScore() == 5000
                    ||
                    list.get(i).getScore() == 5200
                    ||
                    list.get(i).getScore() == 6000){

                //Remove the index fom the list
                list.remove(i);

                //Go back one index, because the list is shorter because of the removal
                i--;

            }

        }

//        for(int i = 0 ; i < list.size() ; i++ ){
//            System.out.println(list.get(i).toString());
//        }

        //Return the list
        return list;
    }


    //Remove categories that don't have 5 questions
    public ArrayList<Question> matchQuestionsPerCategory(List<Question> list) {

        //We make a altered list to put the values Questions in
        ArrayList<Question> alteredList = new ArrayList<Question>();

        for( int i = 0 ; i < list.size()-4 ; i++ ) {

            if(list.get(i).getCategory().getCategoryName().equals(list.get(i + 1).getCategory().getCategoryName())
                    &&
                    list.get(i).getCategory().getCategoryName().equals(list.get(i + 2).getCategory().getCategoryName())
                    &&
                    list.get(i).getCategory().getCategoryName().equals(list.get(i + 3).getCategory().getCategoryName())
                    &&
                    list.get(i).getCategory().getCategoryName().equals(list.get(i + 4).getCategory().getCategoryName())){

                //We add the category entries to a new array
                alteredList.add(list.get(i));
                alteredList.add(list.get(i + 1));
                alteredList.add(list.get(i + 2));
                alteredList.add(list.get(i + 3));
                alteredList.add(list.get(i + 4));

                //We skip the entries and continue
                i += 4;

            }

        }

//        for( int i = 0 ; i < alteredList.size() ; i++ ){
//            System.out.println("Index: " + i + " " + alteredList.get(i).toString());
//        }

        return alteredList;

    }


    //Convert Questions array to hashMp
    public HashMap<Category, ArrayList<Question>> convertArrayToHashMap(ArrayList<Question> list) {

        //We make a hashMap to store the category as key and questions array as value
        HashMap<Category, ArrayList<Question>> questionsByCategory = new HashMap<>();

        for( int i = 0 ; i < list.size() - 5 ; i+=5 ) {

            //We make a altered list to put the values Questions in
            ArrayList<Question> alteredList = new ArrayList<Question>();

            //We add the category entries to a new array
            alteredList.add(list.get(i));
            alteredList.add(list.get(i + 1));
            alteredList.add(list.get(i + 2));
            alteredList.add(list.get(i + 3));
            alteredList.add(list.get(i + 4));

            //We add to the category to the HashMap
            questionsByCategory.put(list.get(i).getCategory(), alteredList);

        }

//        for( int i = 0 ; i < alteredList.size() ; i++ ){
//            System.out.println("Index: " + i + " " + alteredList.get(i).toString());
//        }

//        for(Map.Entry<Category, ArrayList<Question>> category : questionsByCategory.entrySet()){
//
//            System.out.println(category.getKey().getCategoryName());
//            System.out.println(category.getValue().size());
//
//            for( int i = 0 ; i < category.getValue().size() ; i++ ) {
//                System.out.println(category.getValue().get(i).toString());
//            }
//
//        }

//        for (Map.Entry<Category,ArrayList<Question>> entry : questionsByCategory.entrySet())
//            System.out.println("Key = " + entry.getKey().getCategoryName() +
//                    ", Value = " + entry.getValue());

        return questionsByCategory;

    }

    public ArrayList<Category> extractCategories(HashMap<Category, ArrayList<Question>> hashMap){

        //We make a altered list to put the values Questions in
        ArrayList<Category> alteredList = new ArrayList<>();

        for(Map.Entry<Category, ArrayList<Question>> hashElement : hashMap.entrySet()){

            alteredList.add(hashElement.getKey());

        }

//        for( int i = 0 ; i < alteredList.size() ; i++ ){
//            System.out.println(alteredList.get(i).getCategoryName());
//        }

        return alteredList;

    }


    public ArrayList<Question_board> extractBoardQuestions(ArrayList<Question> list, ArrayList<Integer> randomNumbers){

        //We make a altered list to put the values Questions in
        ArrayList<Question_board> alteredList = new ArrayList<>();

        //We go to the random number that is the index
        for(int i = 0 ; i < randomNumbers.size() ; i++){

            //We extract the index and the next 4 questions and make a Question_board from them
            for(int j = 0 ; j < 5 ; j++){
                alteredList.add(new Question_board(
                        list.get(randomNumbers.get(i) + j).getId(),
                        list.get(randomNumbers.get(i) + j).getScore(),
                        list.get(randomNumbers.get(i) + j).getCategory(),
                        list.get(randomNumbers.get(i) + j).getQuestion(),
                        list.get(randomNumbers.get(i) + j).getAnswer()));
            }

        }

//        for( int i = 0 ; i < alteredList.size() ; i++ ){
//            if(alteredList.get(i).getAnswered() == null){
//
//                System.out.println(alteredList.get(i).toString());
//
//            }
//        }

        return alteredList;

    }



    //We make an array list of the easy questions
    public ArrayList<Question> matchEasyCategories(List<Question> list) {

        //We make a altered list to put the values in
        ArrayList<Question> alteredList = new ArrayList<Question>();

        for( int i = 0 ; i < list.size()-4 ; i++ ) {

            if(list.get(i).getScore() == 100
                    &&
                    list.get(i + 1).getScore() == 200
                    &&
                    list.get(i + 2).getScore() == 300
                    &&
                    list.get(i + 3).getScore() == 400
                    &&
                    list.get(i + 4).getScore() == 500){

                //We add the category entries to a new array
                alteredList.add(list.get(i));
                alteredList.add(list.get(i + 1));
                alteredList.add(list.get(i + 2));
                alteredList.add(list.get(i + 3));
                alteredList.add(list.get(i + 4));

                //We skip the entries and continue
                i += 4;

            }

        }

        return alteredList;

    }

    //We make an array list of the hard questions
    public ArrayList<Question> matchHardCategories(List<Question> list) {

        //We make a altered list to put the values in
        ArrayList<Question> alteredList = new ArrayList<Question>();

        for( int i = 0 ; i < list.size()-4 ; i++ ) {

            if(list.get(i).getScore() == 200
                    &&
                    list.get(i + 1).getScore() == 400
                    &&
                    list.get(i + 2).getScore() == 600
                    &&
                    list.get(i + 3).getScore() == 800
                    &&
                    list.get(i + 4).getScore() == 1000){

                //We add the category entries to a new array
                alteredList.add(list.get(i));
                alteredList.add(list.get(i + 1));
                alteredList.add(list.get(i + 2));
                alteredList.add(list.get(i + 3));
                alteredList.add(list.get(i + 4));

                //We skip the entries and continue
                i += 4;

            }

        }

        return alteredList;

    }


}
