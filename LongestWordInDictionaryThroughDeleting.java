package TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 524. Longest Word in Dictionary through Deleting
 * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.
 * Example 1: Input: s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * Output: "apple"
 * Example 2: Input: s = "abpcplea", d = ["a","b","c"]
 * Output: "a"
 * Code from: @hot13399 https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
 * Time Complexity: O(nk), where n is the length of string s and k is the number of words in the dictionary.
 */

public class LongestWordInDictionaryThroughDeleting {

	/*
	 The Java String class provides the .compareTo () method in order to lexicographically compare Strings. 
	 It is used like this "apple".compareTo ("banana").
	 
	 The return of this method is an int which can be interpreted as follows:

		returns < 0 then the String calling the method is lexicographically first (comes first in a dictionary)
		returns == 0 then the two strings are lexicographically equivalent
		returns > 0 then the parameter passed to the compareTo method is lexicographically first.
		More specifically, the method provides the first non-zero difference in ASCII values.
		
		If you compare string with blank or empty string, it returns length of the string. 
		If second string is empty, result would be positive. If first string is empty, result would be negative.
		
		Thus "computer".compareTo ("comparison") will return a value of (int) 'u' - (int) 'a' (20). 
		Since this is a positive result, the parameter ("comparison") is lexicographically first.
	
		There is also a variant .compareToIgnoreCase () which will return 0 for "a".compareToIgnoreCase ("A"); for example.
		
		https://www.javatpoint.com/java-string-compareto
		https://stackoverflow.com/questions/4064633/string-comparison-in-java
	 */
	public static String findLongestWord1(String s, List<String> dictionary) {
		System.out.println("s: "+s+" dictionary: "+dictionary);
		
	    String longest="";
	    
	    for(String dictWord : dictionary) {
	    	System.out.println("dictWord: "+dictWord+" dictWord.length(): "+dictWord.length());
	        int i = 0;
	        
	        for(char c: s.toCharArray()) {
	        	if(i < dictWord.length())
	        		System.out.println(" i: "+i+" c: "+c+" dictWord.charAt(i): "+dictWord.charAt(i));
	            
	        	if(i < dictWord.length() && c == dictWord.charAt(i)) { 
	            	i++;
	            }
	        }
	        System.out.println("i: "+i+" dictWord: "+dictWord+" dictWord.length(): "+dictWord.length()+" longest: "+longest+" longest.length(): "+longest.length()+" dictWord.compareTo(longest): "+(dictWord.compareTo(longest)));

	        if(i == dictWord.length() && dictWord.length() >= longest.length()) { 
	            if(dictWord.length() > longest.length() || dictWord.compareTo(longest) < 0) {
	                longest = dictWord;
	            }
	        }
	        System.out.println("longest: "+longest);
	    }
	    System.out.println("longest: "+longest);
	    
	    return longest;
	}
	
	//Refer this: No sorting, go through the dictionary and check if any word is a subsequence of s
    public static String findLongestWord(String s, List<String> dictionary) {
    	System.out.println("s: "+s+" dictionary: "+dictionary);
        String res = "";
        
        for(String w: dictionary) {
        	System.out.println("w: "+w);
        	
          if(isSubsequence(w, s)) {
        	  System.out.println("w: "+w+" res: "+res+" w.length(): "+w.length()+" res.length(): "+res.length());
        	  
        	  if(w.length() > res.length()) { 
        		  res = w; 
        	  }
        	  System.out.println("w: "+w+" res: "+res+" w.length(): "+w.length()+" res.length(): "+res.length()+" w.compareTo(res): "+w.compareTo(res));
            
        	  //w.compareTo(res) < 0 means w comes first in dictionary than res; w.compareTo(res) > 0 means res comes first in dictionary than w
        	  if(w.length() == res.length() && w.compareTo(res) < 0) { 
        		  res = w;  
        	  }
          }  
        }    
        System.out.println("res: "+res);
        return res;
     }
     
     public static boolean isSubsequence(String w, String s) {
       System.out.println("s: "+s+" w: "+w);
       
       int i = 0, j = 0;
       
       System.out.println("w.length(): "+w.length()+" s.length(): "+s.length());
       
       while(i < w.length() && j < s.length()) {
    	   System.out.println("i: "+i+" j: "+j+" w.charAt(i): "+w.charAt(i)+" s.charAt(j): "+s.charAt(j));
         if(w.charAt(i) == s.charAt(j)) { 
        	 i++;
         }
         j++;    
       }  
       System.out.println("i: "+i);
       return i == w.length();  
     }
	
	public static void main(String[] args) {
		String s = "abpcplea";
		List<String> dictionary = new ArrayList<String>();
//		dictionary.add("a");
//		dictionary.add("b");
//		dictionary.add("c");
		dictionary.add("ale");
		dictionary.add("apple");
		dictionary.add("monkey");
		dictionary.add("plea");
		
		System.out.println(findLongestWord(s, dictionary));
	}

}
