import java.util.ArrayList;
import java.util.Arrays;
import java.util.List; 

// create a package once I'm finished and have it as a Github repository. 

public class Permutations {
	public String word;
	public List<String> permutations;

	public Permutations(String originalWord) {
		word = originalWord;
		this.permutations = permute(originalWord); 
	}


	public static void main(String[] args) {
		String word = args[0];
		Permutations permutations = new Permutations(word);
		System.out.println(permutations);
	}


	@Override
	public String toString() {
		String permString = "";
		for (String p : this.permutations) {
			permString += p + ", ";
		}
		return permString;
	}
	

	
	public static List<String> permute(String word) {
		return combine(addLetter(new ArrayList<String>(), word), word); 
	}

	public static List<String> combine(List<String> longList, String word) {
		int length = word.length();
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


	public static List<String> addLetter(List<String> phrase, String word) {
		// this function just should return the completed set of permutations for 
		List<String> otherLetters = listOtherLetters(phrase, word);
		
		if (otherLetters.size() == 0) {
			return phrase;
		} else {
			List<String> permutations = new ArrayList<>(); 
			for (String c : otherLetters) {
				List<String> tmp = new ArrayList<>(phrase);
				tmp.add(c);
				permutations.addAll(addLetter(tmp, word)); 
			}
			return permutations; 
		} 		
	}
	

	public static List<String> listOtherLetters(List<String> phrase, String word) {
			int wordLength = word.length();
			// the return value (a list)
			List<String> otherLetters = new ArrayList<>();
			// the letters we want to EXCLUDE from otherLetters
			List<Integer> lettersInPhrase = new ArrayList<>();
			
			if (phrase.size() == 0) {
				otherLetters.addAll(Arrays.asList(word.split("")));
				return otherLetters;
			}

			// add ALL matching indices for later comparison, including duplicates
			for (String letter : phrase) {
				int index = word.indexOf(letter);
				// loop through the different instances of the same letter until we find unused one
				while (lettersInPhrase.contains(index)) { 
					index = word.indexOf(letter, index + 1);
				}
				lettersInPhrase.add(index);
			}

			for (int i = 0; i < wordLength; i++) {
				// check if letter index isn't in lettersInPhrase. If not, add to return ArrayList
				if (!lettersInPhrase.contains(i)) {
					otherLetters.add(Character.toString(word.charAt(i))); 
				}
			}

			return otherLetters;

	} 


}

// maybe add a combinations faculty to this??
// class Permutation {
// 	public String word;
// 	public List<String> permutations; 
// 	public Permutation(List<String> originalWord) {
// 		word = originalWord;
// 		permutations = originalWord.permute(); 
// 	}

// 	private List<String> permute(String word) {
// 		return combine(addLetter(new ArrayList<String>(), word), word); 
// 	}



// }









