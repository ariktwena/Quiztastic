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


    public static void main(String[] args) {


        try{

            Controller controller = new Controller();
            controller.init(questionsFile);

        } catch (NullPointerException NullPointExecption) {
            throw new UnsupportedOperationException("The file gave null");
        } catch (IOException IOException) {
            throw new UnsupportedOperationException("You got an IOException");
        }


    }




}
