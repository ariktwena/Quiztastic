package quiztastic.entry;



import quiztastic.controller.GameController;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.ParseException;


public class DisplayQuestions {


    public void displayQuestions(Socket socket) {

        //We set the InputStream to our file in the resource folder
        InputStream inputStream = this.getClass()
        .getClassLoader()
        .getResourceAsStream("master_season1-35clean.tsv");

//        QuestionReader reader = new QuestionReader(new InputStreamReader(inputStream));
//        Question q;
//        while ((q = reader.readQuestion()) != null) {
//            System.out.println(q.toString());
//        }




        // If the InputStream is null we display a message
        if(inputStream == null){

            System.out.println("The files InputStream is empty/null");


        } else {

            //We try to initialize the program
            try{

                //We make a controller object so we can initialize the init() method
                GameController gameController = new GameController();

                //We initialize the init() method
                gameController.init(inputStream, socket);

                //We catch the different exceptions that come
            } catch (IOException IOException) {

                throw new UnsupportedOperationException("You got an IOException");

            } catch (ParseException parseException) {

                throw new UnsupportedOperationException("You got an ParseException");

            }

        }

    }


//    public static void main(String[] args) {
//
//
//        new DisplayQuestions().displayQuestions();
//
//
//    }


    /**
     *
     * Old code
     *
     */


//    public void displayQuestions() {
//
//        //We set the InputStream to our file in the resource folder
//        InputStream inputStream = this.getClass()
//                .getClassLoader()
//                .getResourceAsStream("master_season1-35clean.tsv");
//
////        QuestionReader reader = new QuestionReader(new InputStreamReader(inputStream));
////        Question q;
////        while ((q = reader.readQuestion()) != null) {
////            System.out.println(q.toString());
////        }
//
//
//
//
//        // If the InputStream is null we display a message
//        if(inputStream == null){
//
//            System.out.println("The files InputStream is empty/null");
//
//
//        } else {
//
//            //We try to initialize the program
//            try{
//
//                //We make a controller object so we can initialize the init() method
//                GameController gameController = new GameController();
//
//                //We initialize the init() method
//                gameController.init(inputStream);
//
//                //We catch the different exceptions that come
//            } catch (IOException IOException) {
//
//                throw new UnsupportedOperationException("You got an IOException");
//
//            } catch (ParseException parseException) {
//
//                throw new UnsupportedOperationException("You got an ParseException");
//
//            }
//
//        }
//
//    }
//
//
//    public static void main(String[] args) {
//
//
//        new DisplayQuestions().displayQuestions();
//
//
//    }




}
