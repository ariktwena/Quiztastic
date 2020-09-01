package quiztastic.controller;

import org.junit.jupiter.api.Test;
import quiztastic.core.Category;
import quiztastic.core.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    @Test
    void doesRandomNumbersGenerateEasy(){

        // create instance of Random class
        Random rand = new Random();

        // Generate random integers in range 0 to hardQuestionsModList.size() - 5
        int random1 = rand.nextInt(51265 - 5);
        int random2 = rand.nextInt(51265 - 5);

        while( random1 % 5 != 0 || random2 % 5 != 0 || random1 == random2){

            random1 = rand.nextInt(51265 - 5);
            random2 = rand.nextInt(51265 - 5);

        }

        assertEquals(random1 == random2, false);
        assertEquals(random1 < 51260, true);
        assertEquals(random2 < 51260, true);

    }

    @Test
    void getTheRightQuestionAndCategory() throws IOException {
        ArrayList<Question> list = new ArrayList<Question>();
        ArrayList<Integer> randomNumberList = new ArrayList<Integer>();
        Question question;
        Category category;
        int count = 1, id, score;
        String theQuestion, answer;


        String questionText = "100\tLAKES & RIVERS\tRiver mentioned most often in the Bible\tthe Jordan\n100\tFOREIGN CUISINE\tThe \"coq\" in coq au vin\tchicken\n";
        BufferedReader reader = new BufferedReader(new StringReader(questionText));
        String line;
        while((line = reader.readLine()) != null){

            String[] token = line.toString().split(Pattern.quote("\t"));

            id = count;
            score = Integer.parseInt(token[0]);
            category = new Category(token[1]);
            theQuestion = token[2];
            answer = token[3];

            list.add(new Question(id, score, category, questionText, answer));

            count ++;

        }

        randomNumberList.add(0);
        randomNumberList.add(1);

        assertEquals(2, list.size());
        assertEquals("LAKES & RIVERS", list.get(randomNumberList.get(0)).getCategory().getCategoryName());
        assertEquals("FOREIGN CUISINE", list.get(randomNumberList.get(1)).getCategory().getCategoryName());
        assertEquals(100, list.get(randomNumberList.get(0)).getScore());
        assertEquals(100, list.get(randomNumberList.get(1)).getScore());

    }

}