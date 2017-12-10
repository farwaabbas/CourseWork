package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Arrays;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private int wordLength = DEFAULT_WORD_LENGTH;
    private Random random = new Random();
    private HashSet<String> wordSet;
    private HashMap<String, ArrayList<String>> lettersToWord;
    private ArrayList<String> wordList;
    private HashMap<Integer, ArrayList<String>> sizeToWords;
    ArrayList<String> theList = new ArrayList<>();


    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        lettersToWord = new HashMap<>();
        wordSet = new HashSet<>();
        wordList = new ArrayList<>();
        sizeToWords = new HashMap<>();
        ArrayList<String> result = new ArrayList<String>();
        String sortTargetWord = "";
        while ((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);

            int length = word.length();
            if (sizeToWords.containsKey(length))
                sizeToWords.get(length).add(word);
            else {
                theList = new ArrayList<>();
                theList.add(word);
                sizeToWords.put(length, theList);
            }

            String tWord = sort(word);

            if (lettersToWord.containsKey(tWord))
                lettersToWord.get(tWord).add(word);
            else {
                result = new ArrayList<>();
                result.add(word);
                lettersToWord.put(tWord, result);
            }


        }
    }

    public boolean isGoodWord(String word, String base) {
        String str = word;
        boolean containBase = str.toLowerCase().contains(base.toLowerCase().toString());
        if (!containBase && wordSet.contains(word))
            return true;
        else
            return false;
    }


    public String sort(String Word) {
        char word[] = new char[Word.length()];
        Arrays.sort(word);
        String myWord = new String(word);
        return myWord;
        //  if(!(lettersToWord.containsKey(myWord)))
        //  lettersToWord.put(myWord,new ArrayList<String>());

    }

    public ArrayList<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        String tWord = sort(targetWord);
        result = lettersToWord.get(tWord);

        return result;
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        String tWord = sort(word);
        String sKey = "";
        String sortTargetWord;
        //  if(lettersToWord.containsKey(sKey))
        ArrayList<String> list = new ArrayList<String>();

        for (char i = 'a'; i <= 'z'; i++) {
            sKey = tWord + i;
            sKey = sort(sKey);

            if (lettersToWord.containsKey(sKey))
                result.addAll(lettersToWord.get(sKey));

        }

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).contains(word))
                result.remove(i);
        }
        return result;
    }

    public String pickGoodStarterWord() {

        int length = 0;
        ArrayList<String> temp = sizeToWords.get(wordLength);
        int sizeArrayLength = temp.size();
        while (length < MIN_NUM_ANAGRAMS) {
            String word = temp.get(new Random().nextInt(sizeArrayLength));
            length = getAnagramsWithOneMoreLetter(word).size();
            if (length >= MIN_NUM_ANAGRAMS) {
                if (wordLength < MAX_WORD_LENGTH) wordLength++;
                return word;
            }
        }
        return "led";

    }
}