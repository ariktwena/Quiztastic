package quiztastic.app;

import quiztastic.core.Question;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

/**
 * The Question Reader should read the questions from a file.
 */
public class QuestionReader {

    //We set the BufferedReader til the "reader" variable
    private final BufferedReader reader;

    //LineCounter so we know witch line has the error in the line reader
    private int lineCounter = 0;

    public QuestionReader(BufferedReader reader) {

        this.reader = reader;
    }

    public QuestionReader(Reader reader) {

        this(new BufferedReader(reader));
    }


    //We read the lines from the data file
    public Question readQuestion() throws IOException, ParseException {

        //We initialize the variables
        String line, category, question, answer;
        int id, score;
        int count = 1;

        //We loop through the lines until null/0 lines
        while((line = reader.readLine()) != null){

            //Make a new line
            lineCounter += 1;

            //We make a token array of the line - 4 tokens
            String[] token = line.split("\t");

            //We sort the tokens
            if (token.length == 4){

                try{
                    //We set the variables
                    id = count;
                    score = Integer.parseInt(token[0]);
                    category = token[1];
                    question = token[2];
                    answer = token[3];

                    //We increase the id count
                    count++;

                    //We create and return a new Question object
                    return new Question(id, score, category, question, answer);

                } catch (NumberFormatException e){

                    throw new ParseException("Expected an integer in fields 1, but got \"" + token[0] + "\"", lineCounter);
                }


            } else {

                throw new ParseException("Expected 4 fields, got: " + token.length, lineCounter);

            }

        }

        //We return null if something goes wrong
        return null;
    }

    public BufferedReader getUnderlyingReader(){
        return reader;
    }

}
