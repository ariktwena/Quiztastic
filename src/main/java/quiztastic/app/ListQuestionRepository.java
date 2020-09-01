package quiztastic.app;

import quiztastic.core.Category;
import quiztastic.core.Question;
import quiztastic.domain.QuestionRepository;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ListQuestionRepository implements QuestionRepository {

    //The list that holds the questions
    private final ArrayList<Question> questionsList = new ArrayList<Question>();


    public static ListQuestionRepository fromQuestionReader(QuestionReader reader){


        throw new UnsupportedOperationException("Not implemented yet!");

    }

    public void addQuestionsToList(Question question) {

        //We add Questions to the questionsList from the QuestionReader
        questionsList.add(question);
    }


    public ArrayList<Question> getQuestionsList(){
        return questionsList;
    }


    @Override
    public Question getQuestion(int id) {

        //We loop through the question list/array
        for(int i = 0 ; i < questionsList.size() ; i++ ){

            //We compare id's with the question id
            if(questionsList.get(i).getId() == id){

                //We return the question
                return questionsList.get(i);
            }

            //We return null if id's doesn't match
            return null;
        }

        //We return null if something goes wrong
        return null;
    }



    @Override
    public List<Category> getCategories() {

        //We make a empty category list
        List<Category> categoryList = new ArrayList<Category>();

        //We loop through our questions to single out the category name
        for(int i = 0 ; i < questionsList.size() ; i++ ){

            //If the category list is empty, we add the category (first one)
            if (categoryList.isEmpty() || categoryList.size() == 0){

                //We add the category to the category list
                categoryList.add(new Category(questionsList.get(i).getCategory().getCategoryName()));

                //If the category list doesn't equal the earlier category, we add it to the list (We expect only 5 equal categories in a row)
            } else if(!questionsList.get(i).getCategory().getCategoryName().equalsIgnoreCase(categoryList.get(categoryList.size() - 1).getCategoryName())){

                //We add the category to the category list
                categoryList.add(new Category(questionsList.get(i).getCategory().getCategoryName()));

            }

        }

        //We return the category list
        return categoryList;
    }



    @Override
    public List<Question> getQuestionsWithCategory(Category category) {

        //We make a empty question list by category search
        List<Question> questionByCategoryList = new ArrayList<Question>();

        //We loop through our questionsList, but leave a 4 spot gap
        for( int i = 0 ; i < questionsList.size() - 4 ; i++ ){

            //If the category name is the same on next 4 questions (5 total) we add the to the questionByCategoryList
            if (questionsList.get(i).getCategory().getCategoryName().equalsIgnoreCase(category.getCategoryName())){

                //We add the questions
                questionByCategoryList.add(questionsList.get(i));
                questionByCategoryList.add(questionsList.get(i + 1));
                questionByCategoryList.add(questionsList.get(i + 2));
                questionByCategoryList.add(questionsList.get(i + 3));
                questionByCategoryList.add(questionsList.get(i + 4));

                //We break the loop
                break;

            }

        }

        //We return the list
        return questionByCategoryList;
    }
}
