package quiztastic.entry;

import quiztastic.controller.Controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.text.ParseException;

public class DisplayQuestions {

    //New File object to access the file
    private static final URL url = DisplayQuestions.class
            .getClassLoader()
            .getResource("master_season1-35clean.tsv");

    //Path to the file (can be null)
    private static final Path questionsFile = Path.of(url.getFile());


    public static void main(String[] args) throws NullPointerException, IOException {


        try{

            Controller controller = new Controller();
            controller.init(questionsFile);

        } catch (NullPointerException NullPointExecption) {
            throw new ParseException("The file gave null", NullPointExecption.hashCode());
        } catch (IOException IOException) {
            throw new ("You got a IOException", IOException.getMessage());
        }


    }




}
