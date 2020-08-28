package quiztastic.app;

import org.junit.jupiter.api.Test;
import quiztastic.core.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class QuestionReaderTest {

    @Test
    void shouldReadALine() throws IOException {
        BufferedReader a = new BufferedReader(new StringReader("Hallo, World!"));
        assertEquals(a.readLine(), "Hallo, World!");
    }

    @Test
    void shouldReadMoreLines() throws IOException {
        BufferedReader x = new BufferedReader(new StringReader("Hallo, World!\nOther Line"));
        assertEquals(x.readLine(), "Hallo, World!");
        assertEquals(x.readLine(), "Other Line");
        assertNull(x.readLine());
    }

    @Test
    void shouldSetBufferedReader () {
        BufferedReader x = new BufferedReader(new StringReader("Hallo, World!\nOther Line"));
        QuestionReader a = new QuestionReader(x);
        assertEquals(x, a.getUnderlyingReader());
    }

    @Test
    void useSplitter () throws IOException{
        String questionText = "100\tLAKES & RIVERS\tRiver mentioned most often in the Bible\tthe Jordan\n";
        BufferedReader reader = new BufferedReader(new StringReader(questionText));
        String line = reader.readLine();
        if(line != null){

            String[] token = line.toString().split(Pattern.quote("\t"));
            assertEquals(token.length, 4);
            assertEquals("100", token[0]);
            assertEquals("LAKES & RIVERS", token[1]);
            assertEquals("River mentioned most often in the Bible", token[2]);
            assertEquals("the Jordan", token[3]);

        }

    }

    @Test
    void useSplitterNextLine () throws IOException{

        String[] token1 = null;
        String[] token2 = null;

        String questionText = "100\tLAKES & RIVERS\n200\tLAKES & RIVERS\n";
        BufferedReader reader = new BufferedReader(new StringReader(questionText));
        String line;
        while((line = reader.readLine()) != null){

            if(token1 == null){

                token1 = line.toString().split(Pattern.quote("\t"));

            } else {

                token2 = line.toString().split(Pattern.quote("\t"));

            }

        }

        assertEquals(token1[0], "100");
        assertEquals(token1[1], "LAKES & RIVERS");
        assertEquals(token2[0], "200");
        assertEquals(token2[1], "LAKES & RIVERS");
    }

    @Test
    void testCount () throws IOException{

        int count = 1;
        String[] token1 = null;
        String[] token2 = null;

        String questionText = "100\tLAKES & RIVERS\n200\tLAKES & RIVERS\n";
        BufferedReader reader = new BufferedReader(new StringReader(questionText));
        String line;
        while((line = reader.readLine()) != null){

            if(token1 == null){
                int firstCount = count;
                token1 = line.toString().split(Pattern.quote("\t"));
                count ++;

                assertEquals(firstCount, 1);

            } else {
                int secondCount = count;
                token2 = line.toString().split(Pattern.quote("\t"));
                count ++;

                assertEquals(secondCount, 2);
            }

        }

        int lastCount = count;

        assertEquals(lastCount, 3);

    }

    @Test
    void shouldReadSingleQuestion() throws IOException, ParseException {
        String questionText = "100\tLAKES & RIVERS\tRiver mentioned most often in the Bible\tthe Jordan\n";
        QuestionReader reader = new QuestionReader(new StringReader(questionText));
        Question q = reader.readQuestion();
        assertNotNull(q);
        // Insert more tests
        assertEquals(q.getScore(), 100);

        Question end = reader.readQuestion();
        assertNull(end);
    }

    @Test
    void shouldReadManyQuestions() throws IOException, ParseException {
        URL url = this.getClass()
                .getClassLoader()
                .getResource("questions-small.tsv");
        if (url == null) fail();
        Path smallQuestions = Path.of(url.getFile());

        QuestionReader reader = new QuestionReader(
                Files.newBufferedReader(smallQuestions));
        int count = 0;
        while (reader.readQuestion() != null) {
            count++;
        }

        assertEquals(13, count);
    }

    @Test
    void shouldThrowParseExceptionOnTooFewFields() throws IOException {
        String questionText = "100\tLAKES & RIVERS\tthe Jordan\n";
        QuestionReader render = new QuestionReader(new StringReader(questionText));
        ParseException exception = assertThrows(ParseException.class, () -> {
            render.readQuestion();
        });

        assertEquals("Expected 4 fields, got: 3", exception.getMessage());

    }

    @Test
    void shouldThrowParseExceptionOnBadInteger() throws IOException {
        String questionText = "xxx\tLAKES & RIVERS\tthe Jordan\n";
        QuestionReader render = new QuestionReader(new StringReader(questionText));
        ParseException exception = assertThrows(ParseException.class, () -> {
            render.readQuestion();
        });

        assertEquals("Expected an integer in fields 1, but got \"xxx\"", exception.getMessage());

    }



}