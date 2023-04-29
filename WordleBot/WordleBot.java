import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Scanner;

public class WordleBot {

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length == 0) {
			System.out.println("Welcome to WordleBot! Format is as follows:");
			System.out.println("    java WordleBot [guess1] [guess2] ...");
			return;
		}
		
		// args[] = {guess1, guess2, guess3, guess4, guess5, guess6}, could be missing guesses but should be at least 1, so min args.length = 1
		
		String[] words = getWords("words.txt");	// stores all words from words.txt in an array
		String[] wrongs = new String[6];		// {wrongs1, wrongs2, ...wrongs5, commonwrongs}
		
		// initialize wrongs[] to empty strings ""
		for (int i = 0; i < 6; i++)
			wrongs[i] = "";
		
		System.out.println(" Guess | # Possibilities\n-------+-----------------");
		System.out.println("       | " + words.length);
		
		// compare to each guess in args[]
		for (int i = 0; i < args.length - 1; i++) {
			updateWrongs(args[args.length - 1], args[i], wrongs);
			System.out.println(" " + args[i].toUpperCase() + " | " + getCount(words, formPattern(wrongs)));
		}
		
		System.out.println(" " + args[args.length - 1].toUpperCase() + " | Done!");
	}
	
	private static void printarr(String[] arr) {
		for (String x : arr)
			System.out.print(x + ", ");
		System.out.println();
	}
	
	private static int getCount(String[] words, Pattern pattern) {
		Matcher m;
		int count = 0;
		
		for (String word : words) {
			m = pattern.matcher(word);
			if (m.find())
				count++;
		}
		
		return count;
	}
	
	private static void updateWrongs(String answer, String guess, String[] wrongs) {
		answer = answer.toLowerCase();
		guess = guess.toLowerCase();
		// go thru each letter, update wrongs[]
		for (int i = 0; i < 5; i++) {
			if (answer.charAt(i) == guess.charAt(i)) {								// letter is in the correct spot
				wrongs[i] = Character.toString(answer.charAt(i)).toUpperCase();		//		change wrongs[i] to be JUST the correct letter, but CAPITALIZED
			}
			else if (answer.contains(Character.toString(guess.charAt(i)))) {		// letter is in the word, but not in the correct spot
				if (!(wrongs[i].contains(Character.toString(guess.charAt(i))))) {		//		check if letter is already in wrongs[i]
					wrongs[i] = wrongs[i] + guess.charAt(i);						//		add letter to wrongs[i]
				}
			}
			else if (!(wrongs[5].contains(Character.toString(guess.charAt(i))))) {	// letter is not in the word; check if letter is already in common wrongs
					wrongs[5] = wrongs[5] + guess.charAt(i);						//		add to commmon wrongs (wrongs[5])
			}
		}
	}
	
	// returns the regex pattern based on wrongs[], updates wrongletters[]
	private static Pattern formPattern(String[] wrongs) {
		String pattern = "";
		
		// go thru wrongs[] and create regex pattern
		for (int i = 0; i < 5; i++) {
			if (wrongs[i].length() == 0)
				;
			else if (Character.isUpperCase(wrongs[i].charAt(0))) {
				pattern = pattern + wrongs[i];
				continue;
			}
			
			pattern = pattern + "[^\\s" + wrongs[5] + wrongs[i] + "]";
		}
		
		return Pattern.compile(pattern.toLowerCase());
	}
	
	// reads words in from file, returns String array
	private static String[] getWords(String filename) throws FileNotFoundException {
		Scanner words = new Scanner(new File(filename));
		ArrayList<String> result = new ArrayList<>();
		
		while (words.hasNextLine())
			result.add(words.nextLine());
		
		words.close();
		
		String[] end_result = new String[result.size()];
		
		for (int i = 0; i < end_result.length; i++)
			end_result[i] = result.get(i);
		
		return end_result;
	}
}
