import java.util.ArrayList;

public class PrimeFact {
	private static int[] primes;
	private static ArrayList<Integer> primeFact;
	
	public static void main(String args[]) {
		long number;
		
		// fill up primes[] with prime numbers
		init(100);
		
		for (int i = 0; i < args.length; i++) {
			number = Long.parseLong(args[i]);
			
			primeFact.clear();
			createPrimeFact(number);
			
			printPrimeFact(number);
		}
	}
	
	// use to populate primeFact<>
	private static void createPrimeFact(long n) {
		if (n == 1)
			return;
		
		for (int x : primes) {
			if (n % x == 0) {
				primeFact.add(x);
				createPrimeFact(n / x);
				return;
			}
		}
		
		primeFact.add((int) n);
	}
	
	// use to create primes[] up to (n - 1)th prime
	private static void init(int n) {
		primeFact = new ArrayList<>();
		primes = new int[n];
		prime(n - 1);
	}
	
	// create primes[] from 0th prime (2) to (n - 1)th prime, recursive
	private static void prime(int n) {
		if (n < 2) {
			primes[0] = 2;
			primes[1] = 3;
			return;
		}
		
		prime(n - 1);
		
		boolean flag;
		
		for (int guess = primes[n - 1] + 2; ; guess += 2) {
			flag = true;
			
			for (int i = 0; i < n; i++) {
				if (guess % primes[i] == 0) {
					flag = false;
					break;
				}
			}
			
			if (flag) {
				primes[n] = guess;
				return;
			}
		}
	}
	
	private static void printPrimeFact(long n) {
		System.out.print(n + " = ");
		
		for (int i = 0; i < primeFact.size(); i++) {
			if (i > 0)
				System.out.print("x ");
			System.out.print(primeFact.get(i) + " ");
		}
		
		System.out.println();
	}
	
	private static void printarr(int[] arr) {
		for (int x : arr)
			System.out.println(x);
	}
}