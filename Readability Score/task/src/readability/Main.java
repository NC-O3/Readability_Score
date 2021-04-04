package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File file = new File(args[0]);

        try (Scanner scanner = new Scanner(file)) {

            int numberOfCharacters = 0;
            int numberOfWords = 0;
            int numberOfSentences = 0;
            int numberOfSyllables = 0;
            int numberOfPolysyllables = 0;

            String endRegex = ".*[.?!]"; //regex to find the end of a sentence
            String plainRegex = ".*[.,?!]"; //regex to find find if a word ends with a special character

            String currentWord = "";
            String lastWord = "";
            String vowels = "aeiouyAEIOUY";

            while (scanner.hasNext()) {
                currentWord = scanner.next();

                //Find syllables and polysyllables
                char[] characters;
                if (currentWord.matches(plainRegex)) {
                    characters = new char[currentWord.length() - 1];
                    currentWord.getChars(0, currentWord.length() - 1, characters, 0);
                }
                else {
                    characters = new char[currentWord.length()];
                    currentWord.getChars(0, currentWord.length(), characters, 0);
                }

                int numberOfVowels = 0;
                for (int i = 0; i < characters.length; i ++) {
                    if (i == 0) {
                        if (vowels.indexOf(characters[i]) != -1) {
                            numberOfVowels ++;
                        }
                    }
                    else if (i == characters.length - 1) {
                        if (vowels.indexOf(characters[i]) != -1 && vowels.indexOf(characters[i - 1]) == -1 && characters[i] != 'e') {
                            numberOfVowels ++;
                        }
                    }
                    else if (vowels.indexOf(characters[i]) != -1 && vowels.indexOf(characters[i - 1]) == -1) {
                        numberOfVowels ++;
                    }
                }

                if (numberOfVowels == 0) {
                    numberOfSyllables ++;
                }
                else if (numberOfVowels <= 2) {
                    numberOfSyllables += numberOfVowels;
                }
                else {
                    numberOfSyllables += numberOfVowels;
                    numberOfPolysyllables ++;
                }

                //Find the number of characters
                numberOfCharacters += currentWord.length();

                //Find the number of words
                numberOfWords ++;

                //Find the number of Sentences
                if (currentWord.matches(endRegex)) {
                    numberOfSentences ++;
                }

                lastWord = currentWord;

            }

            if (!lastWord.matches(endRegex)) {
                numberOfSentences ++;
            }

            System.out.printf("Words: %d%n", numberOfWords);
            System.out.printf("Sentences: %d%n", numberOfSentences);
            System.out.printf("Characters: %d%n", numberOfCharacters);
            System.out.printf("Syllables: %d%n", numberOfSyllables);
            System.out.printf("Polysyllables: %d%n", numberOfPolysyllables);

            System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");

            Scanner scannerInput = new Scanner(System.in);
            String option = scannerInput.next();

            AutomatedReadabilityIndex ari = new AutomatedReadabilityIndex(numberOfCharacters, numberOfWords, numberOfSentences);
            FleschKincaidIndex fk = new FleschKincaidIndex(numberOfWords, numberOfSentences, numberOfSyllables);
            SmogIndex smog = new SmogIndex(numberOfPolysyllables, numberOfSentences);
            ColemanLiauIndex cl = new ColemanLiauIndex(numberOfCharacters, numberOfWords, numberOfSentences);

            switch (option) {
                case "ARI":
                    System.out.printf("Automated Readability Index: %s (about %s).%n", ari.getScore(), ari.getAge());
                    break;
                case "FK":
                    System.out.printf("Flesch–Kincaid readability tests: %s (about %s).%n", fk.getScore(), fk.getAge());
                    break;
                case "SMOG":
                    System.out.printf("Simple Measure of Gobbledygook: %s (about %s).%n", smog.getScore(), smog.getAge());
                    break;
                case "CL":
                    System.out.printf("Coleman–Liau index: %s (about %s).%n", cl.getScore(), cl.getAge());
                    break;
                case "all":
                    System.out.printf("Automated Readability Index: %s (about %s).%n", ari.getScore(), ari.getAge());
                    System.out.printf("Flesch–Kincaid readability tests: %s (about %s).%n", fk.getScore(), fk.getAge());
                    System.out.printf("Simple Measure of Gobbledygook: %s (about %s).%n", smog.getScore(), smog.getAge());
                    System.out.printf("Coleman–Liau index: %s (about %s).%n", cl.getScore(), cl.getAge());
                    double average = (ari.getAgeDouble() + fk.getAgeDouble() + smog.getAgeDouble() + cl.getAgeDouble()) / 4.0;
                    System.out.printf("This text should be understood in average by %.2f-year-olds.%n", average);
                    break;
            }

        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}
