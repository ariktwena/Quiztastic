package quiztastic.controller;

import java.net.URL;
import java.nio.file.Path;

public class DataFile {

    URL url = this.getClass()
            .getClassLoader()
            .getResource("master_season1-35clean.tsv");

    Path questionsFile = Path.of(url.getFile());

    public Path getQuestionsFile() throws NullPointerException{
        return questionsFile;
    }
}
