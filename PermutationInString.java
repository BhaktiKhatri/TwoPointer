package TwoPointer;

import java.util.Arrays;

/*
 * 567. Permutation in String
 * https://leetcode.com/problems/permutation-in-string/description/
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
 * Example 1: Input:s1 = "ab" s2 = "eidbaooo"; Output:True; Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2: Input:s1= "ab" s2 = "eidboaoo"; Output: False
 * Note: The input strings only contain lower case letters. The length of both given strings is in range [1, 10,000].
 * Explanation and Code from: @shawngao https://leetcode.com/problems/permutation-in-string/discuss/102588/Java-Solution-Sliding-Window
 * Microsoft, Amazon
 * Medium
 */

public class PermutationInString {
	
		/*
		 * How do we know string p is a permutation of string s? Easy, each character in p is in s too. So we can abstract all permutation strings of s to a map 
		 * (Character -> Count). i.e. abba -> {a:2, b:2}. Since there are only 26 lower case letters in this problem, we can just use an array to represent the map.
		 * How do we know string s2 contains a permutation of s1? We just need to create a sliding window with length of s1, move from beginning to the end of s2. 
		 * When a character moves in from right of the window, we subtract 1 to that character count from the map. When a character moves out from left of the window, 
		 * we add 1 to that character count. So once we see all zeros in the map, meaning equal numbers of every characters between s1 and the substring in the 
		 * sliding window, we know the answer is true.
		 * 
		 * In first loop we assume that s1 and s2 has same length, we use a map, for every char of s1 we add to map, for every char of s2 we delete from map. 
		 * After that we check if for every char in map, we have a perfect balance.(each char has count zero)
		 * In second loop we start to move the windows from left to right. Each step we deal with the head and tail of the window,
		 * then check if the map has a balance.
		 * 
		 */
	    public static boolean checkInclusion1(String s1, String s2) {
	    	System.out.println("s1: "+s1+" s2: "+s2);
	    	
	    	int len1 = s1.length();
	        int len2 = s2.length();
	        
	        System.out.println("len1: "+len1+" len2: "+len2);
	        
	        if(len1 > len2) { 
	        	return false;
	        }
	        
	        int[] count = new int[26];
	        
	        for(int i=0; i<len1; i++) {
	        	System.out.println("i: "+i+" s1.charAt(i): "+s1.charAt(i)+" s2.charAt(i): "+s2.charAt(i));
	            
	        	count[s1.charAt(i) - 'a']++;
	            count[s2.charAt(i) - 'a']--;
	        }
	        
	        System.out.println("count: "+Arrays.toString(count));
	        
	        if(allZero(count)) { 
	        	return true;
	        }
	        
	        for(int i=len1; i<len2; i++) {
	        	System.out.println("i: "+i+" (i-len1): "+(i-len1)+" s2.charAt(i): "+s2.charAt(i)+" s2.charAt(i-len1): "+s2.charAt(i-len1));
	            
	        	count[s2.charAt(i) - 'a']--;
	            count[s2.charAt(i - len1) - 'a']++;
	            
	            System.out.println("count: "+Arrays.toString(count));
	            
	            if(allZero(count)) { 
	            	return true;
	            }
	        }
	        
	        return false;
	    }
	    
	    private static boolean allZero(int[] count) {
	        for(int i = 0; i < 26; i++) {
	            if(count[i] != 0) { 
	            	System.out.println("i: "+i+" count[i]: "+count[i]);
	            	return false;
	            }
	        }
	        return true;
	    }
	    
	    public boolean checkInclusion(String s1, String s2) {
	        if (s1.length() > s2.length())
	            return false;
	        
	        int[] s1map = new int[26];
	        int[] s2map = new int[26];
	        
	        for (int i=0; i<s1.length(); i++) {
	            s1map[s1.charAt(i) - 'a']++;
	            s2map[s2.charAt(i) - 'a']++;
	        }
	        
	        System.out.println("s1map: "+Arrays.toString(s1map));
	        System.out.println("s2map: "+Arrays.toString(s2map));
	        
	        int count = 0;
	        for(int i=0; i<26; i++) {
	        	System.out.println("i: "+i+" s1map[i]: "+s1map[i]+" s2map[i]: "+s2map[i]+" count: "+count);
	        	
	        	if (s1map[i] == s2map[i]) {
	                count++;
	            }
	        }
	        
	        for(int i=0; i<s2.length() - s1.length(); i++) {
	            
	        	int r = s2.charAt(i + s1.length()) - 'a';
	            int l = s2.charAt(i) - 'a';
	            
	            if (count == 26)
	                return true;
	            s2map[r]++;
	            if (s2map[r] == s1map[r])
	                count++;
	            else if (s2map[r] == s1map[r] + 1)
	                count--;
	            s2map[l]--;
	            if (s2map[l] == s1map[l])
	                count++;
	            else if (s2map[l] == s1map[l] - 1)
	                count--;
	        }
	        return count == 26;
	    }

	    //Refer this
	    //https://leetcode.com/problems/permutation-in-string/discuss/102590/8-lines-slide-window-solution-in-Java
	    public static boolean checkInclusion2(String s1, String s2) {
	        int[] count = new int[26];
	        
	        System.out.println("s1: "+s1+" s2: "+s2);
	        
	        for(int i=0; i<s1.length(); i++) { 
	        	System.out.println("i: "+i+" s1.charAt(i): "+s1.charAt(i)+" count[s1.charAt(i) - 'a']: "+count[s1.charAt(i) - 'a']);
	        	count[s1.charAt(i) - 'a']--;
	        }
	        
	        System.out.println("count: "+Arrays.toString(count));
	        
	        for(int l=0, r=0; r < s2.length(); r++) {
	            System.out.println("l: "+l+" r: "+r+" s2.charAt(r): "+s2.charAt(r)+" count[s2.charAt(r) - 'a']: "+count[s2.charAt(r) - 'a']);
	        	
	        	if(++count[s2.charAt(r) - 'a'] > 0) {			//elements in s2 (which are not in s1) has 0 so ++ will be 1 so goes inside if i.e extra

	        		System.out.println("s2.charAt(l): "+s2.charAt(l)+" count[s2.charAt(l) - 'a']: "+count[s2.charAt(l) - 'a']);
	            	
	        		while(l <= r) {
	            		System.out.println("in while l: "+l+" s2.charAt(l): "+s2.charAt(l)+" count[s2.charAt(l) - 'a']: "+count[s2.charAt(l) - 'a']);
	                    
	            		if(--count[s2.charAt(l) - 'a'] == 0) {	//-- because we did not find all elements of s1 in s2 so decrement and make it as it was at first in the first for loop
	                        l++;
	                        break;
	                    }
	                    l++;
	                }       
	        	}
	            else if ((r - l + 1) == s1.length()) { 		//elements in s1 has -1 
	            	System.out.println("count: "+Arrays.toString(count));
	            	return true;
	            }
	        }
	        return s1.length() == 0;
	    }
	    
		public static void main(String[] args) {
			String s1 = "hello"; //"adc"; //"hello";
			String s2 = "olleolleh"; //"dcda"; //"ooolleoooleh";
			
			System.out.println(checkInclusion1(s1, s2));
		}
}
