package quiztastic.controller;


import quiztastic.core.Question;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    //Remove questions where score is 0
    public ArrayList<Question> removeZeroScore(ArrayList<Question> list){

        for( int i = 0 ; i < list.size() ; i++ ){

            if (list.get(i).getScore() == 0){

                list.remove(i);

            }

        }

        return list;
    }

    //Remove questions where score is 1200
    public ArrayList<Question> remove1200Score(ArrayList<Question> list){

        for( int i = 0 ; i < list.size() ; i++ ){

            if (list.get(i).getScore() == 1200){

                list.remove(i);

            }

        }

        return list;
    }

    //Remove questions where score is 1600
    public ArrayList<Question> remove1600Score(ArrayList<Question> list){

        for( int i = 0 ; i < list.size() ; i++ ){

            if (list.get(i).getScore() == 1600){

                list.remove(i);

            }

        }

        return list;
    }

    //Remove questions where score is 2000
    public ArrayList<Question> remove2000Score(ArrayList<Question> list){

        for( int i = 0 ; i < list.size() ; i++ ){

            if (list.get(i).getScore() == 2000){

                list.remove(i);

            }

        }

        return list;
    }

    //Remove questions where score is 3400
    public ArrayList<Question> remove3400Score(ArrayList<Question> list){

        for( int i = 0 ; i < list.size() ; i++ ){

            if (list.get(i).getScore() == 3400){

                list.remove(i);

            }

        }

        return list;
    }

    //Remove questions where score is 4000
    public ArrayList<Question> remove4000Score(ArrayList<Question> list){

        for( int i = 0 ; i < list.size() ; i++ ){

            if (list.get(i).getScore() == 4000){

                list.remove(i);

            }

        }

        return list;
    }

    //Remove questions where score is 5000
    public ArrayList<Question> remove5000Score(ArrayList<Question> list){

        for( int i = 0 ; i < list.size() ; i++ ){

            if (list.get(i).getScore() == 5000){

                list.remove(i);

            }

        }

        return list;
    }

    //Remove questions where score is 6000
    public ArrayList<Question> remove6000Score(ArrayList<Question> list){

        for( int i = 0 ; i < list.size() ; i++ ){

            if (list.get(i).getScore() == 6000){

                list.remove(i);

            }

        }

        return list;
    }

    //Remove categories that don't have 5 questions
    public ArrayList<Question> matchQuestionsPerCategory(List<Question> list) {

        //We make a altered list to put the values in
        ArrayList<Question> alteredList = new ArrayList<Question>();

        for( int i = 0 ; i < list.size()-4 ; i++ ) {

            if(list.get(i).getCategory().equals(list.get(i + 1).getCategory())
                    &&
                    list.get(i).getCategory().equals(list.get(i + 2).getCategory())
                    &&
                    list.get(i).getCategory().equals(list.get(i + 3).getCategory())
                    &&
                    list.get(i).getCategory().equals(list.get(i + 4).getCategory())){

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

    //Remove hard questions with scores (200 -> 400 -> 600 etc..)
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

    //Remove hard questions with scores (100 -> 200 -> 300 etc..)
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

//    public ArrayList<Question> removeWrongScore(ArrayList<Question> list){
//
//        //We make a altered list to put the values in
//        ArrayList<Question> alteredList = new ArrayList<Question>();
//
//        for( int i = 0 ; i < list.size() ; i++ ){
//
//            //Remove questions with score (0, 1200, 1600, 2000, 3400, 4000, 5000, 6000)
//            if (list.get(i).getScore() != 0
//                    ||
//                    list.get(i).getScore() != 1200
//                    ||
//                    list.get(i).getScore() != 1600
//                    ||
//                    list.get(i).getScore() != 2000
//                    ||
//                    list.get(i).getScore() != 3400
//                    ||
//                    list.get(i).getScore() != 4000
//                    ||
//                    list.get(i).getScore() != 5000
//                    ||
//                    list.get(i).getScore() != 6000){
//
//                alteredList.add(list.get(i));
//
//            }
//
//        }
//
//        for( int i = 0 ; i < alteredList.size() ; i++ ){
//            System.out.println("Index: " + i + " " + alteredList.get(i).toString());
//        }
//
//        return alteredList;
//    }
//

//    public boolean firstQuestionMatchSecond(List<Question> list, int index){
//
//        if(!(list.get(index).getCategory().contentEquals(list.get(index + 1).getCategory()))){
//            return false;
//        }
//
//        return true;
//    }
//
//    public boolean firstQuestionMatchThird(List<Question> list, int index){
//
//
//        if(!(list.get(index).getCategory().contentEquals(list.get(index + 2).getCategory()))){
//            return false;
//        }
//
//
//        return true;
//    }
//
//    public boolean firstQuestionMatchFouth(List<Question> list, int index){
//
//
//        if(!(list.get(index).getCategory().contentEquals(list.get(index + 3).getCategory()))){
//            return false;
//        }
//
//
//        return true;
//    }
//
//    public boolean firstQuestionMatchFifth(List<Question> list, int index){
//
//
//        if(!(list.get(index).getCategory().contentEquals(list.get(index + 4).getCategory()))){
//            return false;
//        }
//
//
//        return true;
//    }
//
//    public List<Question> matchFiveQuestionsPerCategory(List<Question> list) {
//
//
//        for( int i = 0 ; i < list.size() - 4 ; i++ ) {
//
//            if(!(firstQuestionMatchSecond(list, i) && firstQuestionMatchThird(list, i) && firstQuestionMatchFouth(list, i) && firstQuestionMatchFifth(list, i))){
//                list.remove(i);
//            } else {
//                i +=4;
//            }
//
//        }
//
//        return list;
//    }



}
