/**
 * Author: J. David McPeek
 *
 * Contact: david.mcpeek@yale.edu
 *
 * A useful tool to list all possible permutations of a given string (CL arg 1).
 * Also includes a combinations calculator.
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List; 

public class Permutations {
	private final String word;
	private final List<String> permutations;
	private final long numberOfPermutations; 

	public Permutations(String originalWord) {
		word = originalWord;
		permutations = this.permute(); 
		numberOfPermutations = permutations.size();
	}

	public static void main(String[] args) {
		String word = args[0];
		int chooseNum = args.length > 1 ? Integer.parseInt(args[1]) : 1; 
		Permutations permutations = new Permutations(word);
		System.out.println(permutations);
		if (args.length > 1) {
			System.out.println(word.length() + " choose " + chooseNum + " = " + permutations.numberOfCombinations(chooseNum));
		}

	}

	public String getWord() { return word; }
	public List<String> getPermutations() { return permutations; }
	public long numberOfPermutations() { return numberOfPermutations; }


	@Override
	public String toString() {
		return "Word: " + word + "\nTotal number: " + numberOfPermutations + "\n" + permutations;
	}
	

	
	private List<String> permute() {
		return combine(addLetter(new ArrayList<String>())); 
	}

	private List<String> combine(List<String> longList) {
		int length = this.word.length();
		int lengthList = longList.size();
		List<String> permutations = new ArrayList<>();

		for (int i = 0; i < lengthList; i += length) {
			char[] permutation = new char[length]; 
			for (int j = 0; j < length; j++) {
				permutation[j] = longList.get(j + i).charAt(0);
			}
			permutations.add(String.valueOf(permutation));

		}
		return permutations;

	}  


	private List<String> addLetter(List<String> phrase) {
		// this function just should return the completed set of permutations for 
		List<String> otherLetters = listOtherLetters(phrase);
		
		if (otherLetters.size() == 0) {
			return phrase;
		} else {
			List<String> permutations = new ArrayList<>(); 
			for (String c : otherLetters) {
				List<String> tmp = new ArrayList<>(phrase);
				tmp.add(c);
				permutations.addAll(addLetter(tmp)); 
			}
			return permutations; 
		} 		
	}
	

	private List<String> listOtherLetters(List<String> phrase) {
			int wordLength = this.word.length();
			// the return value (a list)
			List<String> otherLetters = new ArrayList<>();
			// the letters we want to EXCLUDE from otherLetters
			List<Integer> lettersInPhrase = new ArrayList<>();
			
			if (phrase.size() == 0) {
				otherLetters.addAll(Arrays.asList(this.word.split("")));
				return otherLetters;
			}

			// add ALL matching indices for later comparison, including duplicates
			for (String letter : phrase) {
				int index = this.word.indexOf(letter);
				// loop through the different instances of the same letter until we find unused one
				while (lettersInPhrase.contains(index)) { 
					index = this.word.indexOf(letter, index + 1);
				}
				lettersInPhrase.add(index);
			}

			for (int i = 0; i < wordLength; i++) {
				// check if letter index isn't in lettersInPhrase. If not, add to return ArrayList
				if (!lettersInPhrase.contains(i)) {
					otherLetters.add(Character.toString(this.word.charAt(i))); 
				}
			}

			return otherLetters;

	}


	public long numberOfCombinations(int k) {
		return numberOfCombinations(word.length(), k);
	}

	/**
	 * Generic method for finding the possible number of combinations given the length of the string and the size of the groups.
	 * @param  stringLength length of the string
	 * @param  chooseNum    size of the groups
	 * @return              the total number of possible combinations
	 */
	public static long numberOfCombinations(int stringLength, int k) {
		assert k > 0 && k < stringLength : "You cannot find the combinations of k symbols when k is above the given string length or below 1"; 

		try {
			if (k < 1 || k > stringLength) {
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal argument exception: " + e.getMessage());
			System.out.println("Your choose-k value (arg[1]) must be greater than 0 or less than the number of symbols in the given string");
			System.out.println("Converting k to 1...");
			k = 1;
		}

		// numerator factorial
		long numPermutations = 1;

		// implement classic permutations with `i` number of slots left over
		for (int i = stringLength; i > stringLength - k; i--) {
			numPermutations *= i; 
		}
		long kPrime = 1;
		for (int i = k; i > 0; i--) {
			kPrime *= i;
		}

		return numPermutations / kPrime;

	}

}




