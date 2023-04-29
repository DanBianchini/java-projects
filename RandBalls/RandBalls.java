import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.lang.Math;
import java.util.ArrayList;

public class RandBalls {
	public static void main(String args[]) throws IOException {
		if (args.length == 0) {
			System.out.println("Please provide the number of desired games");
			return;
		}
		
		createBalls(Integer.parseInt(args[0]));
		
		System.out.println("Done!");
	}
	
	private static void createBalls(int games) throws IOException {
		System.out.println("Generating balls.txt...");
		BufferedWriter bw = new BufferedWriter(new FileWriter("balls.txt"));
		
		for (int i = 0; i < games; i++)
			bw.write(randBalls(100) + "\n"); //will do 100 shuffles for now, recommend over 75 at least
		
		bw.close();
		System.out.println("balls.txt successfully generated!\nTotal number of games: " + games);
	}
	
	private static String randBalls(int shuffles) {
		ArrayList<Integer> balls = new ArrayList<>();
		int ind, temp;
		
		for (int i = 1; i <= 75; i++)
			balls.add(i);
		
		for (int i = 0; i < shuffles; i++) {
			ind = (int) (Math.random() * 75);
			balls.add(balls.get(ind));
			balls.remove(ind);
		}
		
		//check balls
		if (balls.size() != 75)
			System.out.println("Uh oh");
		
		//should be shuffled; convert to String
		String result = "" + balls.get(0);
		for (int i = 1; i < balls.size(); i++)
			result = result + ", " + balls.get(i);
		
		return result;
	}
	
	//prints an array on one line
	private static void printarr(long[] arr) {
		for (long x : arr)
			System.out.print(x + ", ");
		System.out.println();
	}
	
}