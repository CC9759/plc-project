/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author 
 **/

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JottTokenizer {

   /**
    * Takes in a file object and returns the lines as a list of strings
    * @param myFile a file object to be parsed
    * @return an ArrayList of Strings
    */
    public static ArrayList<String> getLines(File myFile) throws FileNotFoundException{
        ArrayList<String> returnMe = new ArrayList<String>();
        Scanner myReader = new Scanner(myFile);
        while(myReader.hasNextLine()) {
            returnMe.add(myReader.nextLine());
        }
        myReader.close();
        return returnMe;
    }

	/**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     * @param filename the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String filename) throws FileNotFoundException{
        ArrayList<String> myLines = getLines(new File(filename));
		return myLines;
	}

}
