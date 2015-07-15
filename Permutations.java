import java.util.ArrayList;
import java.util.Arrays;
import java.util.List; 

// create a package once I'm finished and have it as a Github repository. 
// maybe add a combinations faculty to this??

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
		Permutations permutations = new Permutations(word);
		System.out.println(permutations);
		Permutations.Combinations combinations = permutations.new Combinations(2);
		System.out.println(combinations.numberOfCombinations);

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

	class Combinations {
		private int chooseNum;
		private long numberOfCombinations;

		/**
		 * Default constructor: chooseNum is simply the word length
		 * @return numberOfCombinations = 1;
		 */
		public Combinations() {
			this(Permutations.this.word.length());
		}

		public Combinations(int chooseNum) {
			assert chooseNum > 0 && chooseNum < Permutations.this.word.length() : "You cannot take a combination of n symbols when n is above the word length or below 1"; 
			this.chooseNum = chooseNum; 
			numberOfCombinations = this.chooseNCombine();
		}

		private long chooseNCombine() {
			// numerator factorial
			long numPermutations = 1;
	
			// implement classic permutations with `i` number of slots left over
			for (int i = Permutations.this.word.length(); i > chooseNum; i--) {
				numPermutations *= i; 
			}
			long numPossibleArrangements = 1;
			for (int i = chooseNum; i > 0; i--) {
				numPossibleArrangements *= i;
			}

			return numPermutations / numPossibleArrangements;

		}

		public void setChooseNum(int newNum) {
			chooseNum = newNum;
			numberOfCombinations = this.chooseNCombine();
		}

		public int getChooseNum() { return chooseNum; }
		public long getNumberOfCombinations() { return numberOfCombinations; }

	}

}











