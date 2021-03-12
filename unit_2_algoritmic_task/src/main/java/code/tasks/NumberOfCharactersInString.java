package code.tasks;

import java.util.HashMap;

public class NumberOfCharactersInString {
    private String str_num_char;

    public NumberOfCharactersInString(String str_num_char) {
        this.str_num_char = str_num_char;
    }

    public void countChar() {
        str_num_char = str_num_char.toLowerCase();
        String latin = "abcdefghijklmnopqrstuvwxyz";
        System.out.println("Found latin characters and their number: ");
        for (int a = 0; a < latin.length(); a++) {
            char letter_latin = latin.charAt(a);
            HashMap<Character, Integer> numCharsLatin = new HashMap<Character, Integer>(40);
            for (int i = 0; i < str_num_char.length(); ++i) {
                char c = str_num_char.charAt(i);
                if (Character.isLetter(c)) {
                    if (numCharsLatin.containsKey(c)) {
                        numCharsLatin.put(c, numCharsLatin.get(c) + 1);
                    } else {
                        numCharsLatin.put(c, 1);
                    }
                }
            }
            if (numCharsLatin.get(letter_latin) == null){}
            else {
                System.out.println(letter_latin + " - " + numCharsLatin.get(letter_latin));
            }
        }
        String cyrillic = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        System.out.println("Found cyrillic characters and their number: ");
        for (int a = 0; a < cyrillic.length(); a++) {
            char letter_cyrillic = cyrillic.charAt(a);
            HashMap<Character, Integer> numCharsCyrillic = new HashMap<Character, Integer>(40);
            for (int i = 0; i < str_num_char.length(); ++i) {
                char c = str_num_char.charAt(i);
                if (Character.isLetter(c)) {
                    if (numCharsCyrillic.containsKey(c)) {
                        numCharsCyrillic.put(c, numCharsCyrillic.get(c) + 1);
                    } else {
                        numCharsCyrillic.put(c, 1);
                    }
                }
            }
            if (numCharsCyrillic.get(letter_cyrillic) == null) {} else {
                System.out.println(letter_cyrillic + " - " + numCharsCyrillic.get(letter_cyrillic));
            }
        }
    }
}
