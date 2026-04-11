package dev.atanu.ds.java.sliding.window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-i/
 *
 *
 */
public class SubStringContainingVowelsAndConsonants {

    public static void main(String[] args) {
        SubStringContainingVowelsAndConsonants solution =
                new SubStringContainingVowelsAndConsonants();

        System.out.println(solution.countOfSubstrings("iqeaouqi", 2));
    }

    /**
     * https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-i/
     *
     * @param word
     * @param k
     * @return
     */
    public int countOfSubstrings(String word, int k) {

        Map<Character, Integer> map = new HashMap<>();
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        int result = 0, count = 0, consonants = 0;
        int start = 0, end = 0;

        while(end < word.length()) {
            char endChar = word.charAt(end);
            boolean isVowel = vowels.contains(endChar);
            if(isVowel) {
                map.put(endChar, map.getOrDefault(endChar, 0) + 1);
            } else {
                consonants++;
            }
            if(isVowel) {
                while(map.containsKey(endChar)) {
                    char startChar = word.charAt(start);
                    boolean startVowel = vowels.contains(startChar);
                    if(startVowel) {
                        map.put(startChar, map.getOrDefault(startChar, 0) - 1);
                        if(map.get(startChar) == 0) {
                            map.remove(startChar);
                        }
                    } else {
                        consonants--;
                    }
                    start++;
                }
                map.put(endChar, map.getOrDefault(endChar, 0) + 1);
            } else {
                consonants += 1;
                while(consonants > k) {
                    char startChar = word.charAt(start);
                    boolean startVowel = vowels.contains(startChar);
                    if(startVowel) {
                        map.put(startChar, map.getOrDefault(startChar, 0) - 1);
                        if(map.get(startChar) == 0) {
                            map.remove(startChar);
                        }
                    } else {
                        consonants--;
                    }
                    start++;
                }
            }
            if(map.size() == vowels.size() && consonants == k) {
                result += 1;
            }
            end++;
        }

        return result;
    }
}
