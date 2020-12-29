package main.java;
import java.lang.Character;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class StringCharacters {
    public static void main(String[] args){
        String testText = " To be or not to be, that is the question;" +
                "Whether 'this noblue in the mind to suffer" +
                " the slings and arros of outrageous fortune," +
                " and by opposing end them?";
        String text = "Helo world Cat Bee Car Ant Dog Termite Zebra Dolphin Whale Snake fish bird!";
        int space = 0,
                vowels = 0,
                letters = 0;
        // Insert your code here

        List<Character> vowelList = Arrays.asList('a', 'e', 'i', 'o', 'u');
        for (int i = 0; i < text.length(); i++){
            if (Character.isWhitespace(text.charAt(i))){
                space += 1;
            } else{
                char c = Character.toLowerCase(text.charAt(i));
                if (Character.isLetter(c)){
                    letters += 1;
                }
                if (vowelList.contains(c)){
                    vowels += 1;
                }
            }
        }

        System.out.println("the text contains vowels: " + vowels + " \n" +
                " consonants " + (letters - vowels) + "\n" +
                " spaces: " + space);

        System.out.println("...let's sort the words in this soliloquy...");
        List<String> listWord = extractWord(text);
        bubbleSortWords(listWord);
        for (int i = 0; i < listWord.size();i++){
            System.out.println(listWord.get(i));
        }
    }

    public static List<String> extractWord(String str){
        List<String> result = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < str.length(); i++){
            if (Character.isWhitespace(str.charAt(i))){
                result.add(temp.toString());
                temp = new StringBuilder();
            }
            else{
                temp.append(Character.toLowerCase(str.charAt(i)));
            }
        }
        return result;
    }

    public static void bubbleSortWords(List<String> item){
        Boolean notSorted = true;
        while (notSorted){
            Boolean onePass = true;
            for (int i = 0; i < item.size() - 1; i++){
                String first = item.get(i);
                String second = item.get(i + 1);
                if(first.compareTo(second) > 0){
                    Collections.swap(item,i,i+1);
                    onePass = false;
                }
            }
            if (onePass){
                notSorted = false;
            }
        }
    }
}
