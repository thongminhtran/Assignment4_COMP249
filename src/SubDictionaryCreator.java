import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SubDictionaryCreator {
    public static String[] punctuations = {"?", ".", ",", ":", "=", "!", ";"};
    public static String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    //    public static String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    public static Character[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args) {
        // write your code here
        // Read File by Scanner
        displayWelcomeMessage();
        String content = readFileByScanner();
        String[] parsedLines = content.split("\n");

        String[] words;
        // Create an array with all signs that appear the end of word.
        ArrayList<String> inputData = new ArrayList<String>();
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
                    words[j] = words[j].replace("'", "");
                }

                //If the words doesn't satisfy the condition, ignore it.
                if (containsDigit(words[j]))
                    continue;

                if (words[j].length() == 1) {
                    if (containsOnlyOneLetterAOrI(words[j])) {
                        words[j].replaceAll("\\s+", "");     //Remove all spaces in any words.
                        inputData.add(words[j]);
                    } else {
                        continue;
                    }
                } else {
                    words[j].replaceAll("\\s+", "");     //Remove all spaces in any words.
                    inputData.add(words[j]);
                }
            }
        }
        transferToUpperCase(inputData);
        removeDuplicateWords(inputData);
        ArrayList<String> sortedList = sortAlphabetOrder(inputData);

        //Remove any Empty Elements After Sorted.
        for (int i = 0; i < sortedList.size(); i++) {
            if (sortedList.get(i) == "")
                sortedList.remove(i);
        }
        writeToSubDictionary(sortedList);
    }

    public static void writeToSubDictionary(ArrayList<String> stringArrayList) {
        // Create a new file.
        try {
            File file = new File("outputPart1.txt");
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
            FileWriter writer = new FileWriter("outputPart1.txt");
            writer.write("The document produced this sub-dictionary, which includes " + stringArrayList.size() + " entries." + "\n\n");
            for (int i = 0; i < alphabet.length; i++) {
                writer.write(alphabet[i] + "\n");
                writer.write("==" + "\n");
                for (String word : stringArrayList) {
                    if (word.charAt(0) == alphabet[i]) {
                        writer.write(word + "\n");
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

    public static void transferToUpperCase(ArrayList<String> stringArrayList) {
        for (int i = 0; i < stringArrayList.size(); i++) {
            // Upper case all words in the list.
            String temp = stringArrayList.get(i);
            stringArrayList.remove(i);
            stringArrayList.add(i, temp.toUpperCase());
        }
    }

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
        return tempArrayList;
    }

    public static void removeDuplicateWords(ArrayList<String> stringArrayList) {
        for (int i = 0; i < stringArrayList.size(); i++) {
            // Remove all duplicate words.
            for (int j = i + 1; j < stringArrayList.size(); j++) {
                if (stringArrayList.get(i).compareToIgnoreCase(stringArrayList.get(j)) == 0)
                    stringArrayList.remove(j);
            }
        }
    }


    public static void displayWelcomeMessage() {
        System.out.println("Welcome to Sub-Dictionary Creator");
    }

    // This method will return true if there exists a number in a word.
    public static boolean containsDigit(String word) {
        for (String digit : digits) {
            if (word.contains(digit))
                return true;
        }
        return false;
    }

    public static boolean containsOnlyOneLetterAOrI(String word) {
        if (word.charAt(0) == 'A' || word.charAt(0) == 'a' || word.charAt(0) == 'I' || word.charAt(0) == 'i')
            return true;
        return false;
    }


    public static boolean containsSingleQuote(String word) {
        if (word.contains("'"))
            return true;
        return false;
    }

    public static boolean containsPunctuation(String word) {
        for (String punctuation : punctuations) {
            if (word.contains(punctuation)) {
                return true;
            }

        }
        return false;
    }

    public static String readFileByScanner() {
        String data = "";
        try {
            System.out.print("Enter the file name with path: ");
            Scanner input = new Scanner(System.in);
            File file = new File(input.nextLine());
            input = new Scanner(file);
            while (input.hasNextLine()) {
                data += input.nextLine();
                data += "\n";
            }
            input.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Cannot find any files to read.");
            System.exit(0);
        }
        return data;
    }
}

