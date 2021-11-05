package Server.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PerformConcat  implements Runnable{

    private StringModel theModel;
    private BufferedReader socketIn;
    private PrintWriter socketOut;

    public PerformConcat(StringModel theModel, BufferedReader socketIn, PrintWriter socketOut){
        this.theModel = theModel;
        this.socketIn = socketIn;
        this.socketOut = socketOut;
    }



    public void concatenate(){
        try {
            String text = socketIn.readLine();
            String[] words = text.split("/");
            String word1 = words[0];
            String word2 = words[1];
            String letterCase = words[2];

            switch(letterCase){
                case "u":
                    word1 = word1.toUpperCase();
                    word2 = word2.toUpperCase();
                    theModel.concatenate(word1,word2);
                    break;
                case "l":
                    word1 = word1.toLowerCase();
                    word2 = word2.toLowerCase();
                    theModel.concatenate(word1,word2);
                    break;
            }
            String finalString = theModel.getMyString();
            socketOut.println(finalString);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        concatenate();
    }
}
