import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Names and ID: Thong Minh Tran (40072745)
 * Quan Nguyen The(40108890)
 * COMP249
 * Assignment #4 Part 1
 * Deadline: Dec 4 2020
 */

/**
 * This is a driver class for Part 1
 * Part 1 will only include this class.
 * Introduction: This program will read a file, then generate a file named "SubDictionary.txt".
 */
public class SubDictionaryCreator_Part1 {
    public static String[] punctuations = {"?", ".", ",", ":", "=", "!", ";"};
    public static String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    public static Character[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args) {
        System.out.println("Welcome to Sub-Dictionary Creator");
        String content;
        try {
            content = readFileByScanner();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            System.out.println("Cannot find any files to read.");
            return; // exit
        }
        ArrayList<String> inputData = copyDataToList(content);
        ArrayList<String> sortedData = sortAlphabetOrder(inputData);
        writeToSubDictionary(sortedData);
    }

    /**
     * This method creates an array list and sort for alphabetical order.
     *
     * @param stringArrayList
     * @return
     */
    public static ArrayList<String> sortAlphabetOrder(ArrayList<String> stringArrayList) {
        ArrayList<String> tempArrayList = (ArrayList<String>) stringArrayList.clone();
        for (int i = 0; i < tempArrayList.size(); i++) {
            for (int j = i + 1; j < tempArrayList.size(); j++) {
                //Compare all words, switch them with each other if necessary.
                if (tempArrayList.get(i).compareToIgnoreCase(tempArrayList.get(j)) > 0) {
                    String temp = tempArrayList.get(j);
                    tempArrayList.remove(j);
                    tempArrayList.add(j, tempArrayList.get(i));
                    tempArrayList.remove(i);
                    tempArrayList.add(i, temp);
                }
            }
        }
        for (int a = 0; a < tempArrayList.size(); a++) {
            // Remove all empty string
            if (tempArrayList.get(a) == "")
                tempArrayList.remove(a);
        }
        return tempArrayList;
    }


    /**
     * This method will go through the String content, analyze and take every single word to the ArrayList
     *
     * @param content
     * @return
     */
    public static ArrayList<String> copyDataToList(String content) {
        ArrayList<String> inputData = new ArrayList<String>();
        String[] parsedLines = content.split("\n");
        String words[];
        for (int i = 0; i < parsedLines.length; i++) {
            words = parsedLines[i].split(" ");
            for (int j = 0; j < words.length; j++) {
                // Replace all punctuation in any words.
                if (containsPunctuation(words[j])) {
                    for (String tempPunctuation : punctuations)
                        words[j] = words[j].replace(tempPunctuation, "");
                }
                // Replace all single quote in any words.
                if (containsSingleQuote(words[j])) {
                    // Replace all Single Quote from any words
                    words[j] = words[j].replace("'m", "");
                    words[j] = words[j].replace("'s", "");
                    words[j] = words[j].replace("'M", "");
                    words[j] = words[j].replace("'S", "");
                    words[j] = words[j].replace("'", "");
                }

                //If the words doesn't satisfy the condition, ignore it.
                if (containsDigit(words[j]))
                    continue;

                if (words[j].length() == 1) {
                    // Ignore any single words if it is not A or I
                    if (!containsOnlyOneLetterAOrI(words[j])) {
                        continue;
                    }
                }
                words[j].replaceAll("\\s+", "").trim();     //Remove all spaces in any words.
                if (!inputData.contains(words[j].toUpperCase())) {
                    inputData.add(words[j].toUpperCase());
                }
            }
        }
        return inputData;
    }


    /**
     * This method is for creating a new file
     *
     * @param stringArrayList
     */
    public static void writeToSubDictionary(ArrayList<String> stringArrayList) {

        try {
            File file = new File("SubDictionary.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println(file.getName() + " already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter("SubDictionary.txt");
            writer.write("The document produced this sub-dictionary, which includes " + stringArrayList.size() + " entries." + "\n\n");
            for (int i = 0; i < alphabet.length; i++) {
                writer.write(alphabet[i] + "\n");
                writer.write("==" + "\n");
                for (String word : stringArrayList) {
                    if (!word.isEmpty()) {
                        if (word.charAt(0) == alphabet[i]) {
                            writer.write(word + "\n");
                        }
                    }
                }
                writer.write("\n");
            }
            System.out.println("Wrote to file successfully.");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot find any files to write!");
            System.exit(0);
        }
    }


    /**
     * This method will return true if there exists a number in a word.
     *
     * @param word
     */
    public static boolean containsDigit(String word) {
        for (String digit : digits) {
            if (word.contains(digit))
                return true;
        }
        return false;
    }


    /**
     * This method will allow with the exception of A and I, can be recorded in the dictionary. (as specified in the assignment)
     *
     * @param word
     * @return
     */

    public static boolean containsOnlyOneLetterAOrI(String word) {
        if (word.charAt(0) == 'A' || word.charAt(0) == 'a' || word.charAt(0) == 'I' || word.charAt(0) == 'i')
            return true;
        return false;
    }


    /**
     * This method checks if it contains single quote
     *
     * @param word
     * @return
     */
    public static boolean containsSingleQuote(String word) {
        if (word.contains("'"))
            return true;
        return false;
    }

    /**
     * This method checks if it contains punctuation
     *
     * @param word
     * @return
     */
    public static boolean containsPunctuation(String word) {
        for (String punctuation : punctuations) {
            if (word.contains(punctuation)) {
                return true;
            }

        }
        return false;
    }

    /**
     * This method will read the file
     *
     * @return
     * @throws FileNotFoundException
     */
    public static String readFileByScanner() throws FileNotFoundException {
        String data = "";
        System.out.print("Enter the file name with path: ");
        Scanner input = new Scanner(System.in);
        File file = new File(input.nextLine());
        input = new Scanner(file);
        while (input.hasNextLine()) {
            data += input.nextLine();
            data += "\n";
        }
        input.close();

        return data;
    }
}