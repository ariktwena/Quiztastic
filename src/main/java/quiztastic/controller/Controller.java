package quiztastic.controller;

import quiztastic.app.ListQuestionRepository;
import quiztastic.app.QuestionReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Controller {

    ////New File object to acces the file
    //DataFile file = new DataFile();

    public void init(Path path) throws IOException {

        //Initialize Question reader with the path to the questions file
        QuestionReader questionReader = new QuestionReader(Files.newBufferedReader(path));


    }

    //New QuestionReader object to access the reader
    //QuestionReader questionReader = new QuestionReader(Files.newBufferedReader(file.questionsFile));

    //New listQuestionRepository object so we can access the questions list
    ListQuestionRepository listQuestionRepository = new ListQuestionRepository();



}
