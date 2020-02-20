package Napiat_2600;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * Class napiat_2015_intellij.InputReader reads typed text input from the standard text terminal.
 * The text typed by a user is then chopped into words, and a set of words
 * is provided.
 *
 * @version    1.1  (20.Jan.2004)
 * @author     Michael Kolling and David J. Barnes
 */
public class InputReader
{
    private BufferedReader reader;

    /**
     * Create a new napiat_2015_intellij.InputReader that reads text from the text terminal.
     */
    public InputReader()
    {
        reader = new BufferedReader(new InputStreamReader(System.in));;
    }

    /**
     * Read a line of text from standard input (the text
     * terminal), and return it as a set of words.
     *
     * @return  A set of Strings, where each String is one of the
     *          words typed by the user
     */
    public HashSet getInput()
    {
        System.out.print("> ");                // print prompt
        String inputLine = readInputLine().trim().toLowerCase();

        String[] wordArray = inputLine.split(" ");  // split at spaces

        // add words from array into hashset
        HashSet words = new HashSet();
        for(int i=0; i < wordArray.length; i++) {
            words.add(wordArray[i]);
        }
        return words;
    }

    /**
     * Read one line of input and return it as a String.
     *
     * @return  A String representing the input, or an empty String
     *          if an error occurs.
     */
    private String readInputLine()
    {
        String line = "";

        try {
            line = reader.readLine();
        }
        catch(java.io.IOException exc) {
            System.out.println ("Read error: " + exc.getMessage());
        }
        return line;
    }
}

