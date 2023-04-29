import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class MergeSort {
	public static void main(String args[]) throws FileNotFoundException {
		int[] nums;
		
		if (args.length == 0) {
			System.out.println("Please enter a list of numbers or a file name containing #s to be sorted.");
			System.out.println("File extension must be included, and file should contain one number per line");
			return;
		}
		
		//look for file input, produce file as output
		else if (args.length == 1) {
			nums = null;
			readFromFile(nums, args[0]);
			mergesort(nums);
			printArr(nums);
		}
		
		//take input from command line, produce output at command line
		else {
			//set nums
			nums = new int[args.length];
			for (int i = 0; i < args.length; i++)
				nums[i] = Integer.parseInt(args[i]);
			//nums is set to command line args
			mergesort(nums);
			printArr(nums);
		}
	}
	
	private static void readFromFile(int[] arr, String filename) throws FileNotFoundException {
		ArrayList<Integer> temp = new ArrayList<>();
		Scanner sc = new Scanner(new File(filename));
		String line;
		for (int i = 0; sc.hasNextLine(); i++)
			temp.add(Integer.parseInt(sc.nextLine()));
		arr = new int[temp.size()];
		for (int i = 0; i < temp.size(); i++)
			arr[i] = temp.get(i);
	}
	
	private static void mergesort(int[] arr) {
		if (arr.length <= 1)
			return;
		int[] left, right;
		int lc = 0, rc = 0; //left counter, right counter
		left = Arrays.copyOfRange(arr, 0, arr.length / 2);
		right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
		mergesort(left);
		mergesort(right);
		for (int i = 0; lc < left.length || rc < right.length; i++) {
			if (lc == left.length) {
				arr[i] = right[rc++];
				continue;
			}
			if (rc == right.length) {
				arr[i] = left[lc++];
				continue;
				
			}
			if (left[lc] < right[rc])
				arr[i] = left[lc++];
			else
				arr[i] = right[rc++];
		}
	}
	
	private static void printArr(int[] arr) {
		for (int x : arr)
			System.out.print(x + " ");
		System.out.println();
	}
}