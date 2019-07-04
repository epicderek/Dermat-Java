package dermatj.utils;

import dermatj.eval.time.Timer;
import dermatj.utils.seq.Array;

import java.util.ArrayList;
import java.util.List;

public abstract class Sort
{
	
	public static <T extends Comparable<? super T>> T max(ArrayList<T> input)
	{
		T max = input.get(0);
		for(int i=0; i<input.size(); i++)
			if(input.get(i).compareTo(max)>0)
				max = input.get(i);
		return max;
	}
	
	public static <T extends Comparable<? super T>> int maxInd(T[] input)
	{
		T max = input[0];
		int index = 0;
		for(int i=0; i<input.length; i++)
			if(input[i].compareTo(max)>0)
			{
				max = input[i];
				index = i;
			}
		return index;
	}
	
	public static <T extends Comparable<? super T>> T[] bubbleSort(T[] input)//Applicable to any object that either directly implements the Comparable interface or inherit from a super class that implements the interface, sort the array.
	{
		boolean judge = false;
		while(!judge)//If the array is not ordered.
		{
			judge = true;//Set it to true and start checking the array.
			for(int i=0; i<input.length-1; i++)//Loop through the array.
			{
				if(input[i].compareTo(input[i+1])>0)//If the adjacent is not in order, flip the two.
				{
					judge = false;//The array is not ordered.
					T media = input[i];//Temporary holder.
					input[i] = input[i+1];
					input[i+1] = media;//Flip.
				}
			}
		}
		return input;
	}
	
	public static <T extends Comparable<? super T>> T[] selectionSort(T[] input)//Applicable to any object that either directly implements the Comparable interface or inherit from a super class that implements the interface, sort the array.
	{
		T min;//The minimum of the array.
		T media;//A temporary variable holder.
		int counter = 0;//The index of the minimum.
		for(int i=0; i<input.length; i++)
		{
			min = input[i];
			media = input[i];
			counter = i;//Assume the first element is the minimum.
			for(int j=i; j<input.length; j++)
			{
				if(input[j].compareTo(min)<0)//If have smaller ones than the assumed, record the index and assign it to minimum.
				{
					min = input[j];
					counter = j;
				}
			}
			input[i] = min;//Assign the minimum in correct sequence of the array.
			input[counter] = media;//Assign the first element into the spot that used to hold the minimum.
		}
		return input;
	}
	
	public static <T extends Comparable<? super T>> T[] quickSort(T[] input)
	{
		quickSorting(input,0,input.length-1);
		return input;
	}
	
	public static <T extends Comparable<? super T>> List<T> quickSort(List<T> input)
	{
		quickSorting(input,0,input.size()-1);
		return input;
	}
	
	public static <T extends Comparable<? super T>> void quickSorting(List<T> input, int ini, int fin)//Applicable to any object that either directly implements the Comparable interface or inherit from a super class that implements the interface, sort the array.
	{
		if(fin-ini==0)//Base case, nothing to sort, exit.
			return;
		T pivot = input.get((ini+fin)/2);//The pivot point to compare the other numbers.
		int counter1 = ini;
		int counter2 = fin;
		while(counter1<counter2)//While still checking, possibly not in order.
		{
			while(input.get(counter1).compareTo(pivot)<0)//If valid, go on.
				counter1++;
			while(input.get(counter2).compareTo(pivot)>0)//If valid, go on.
				counter2--;
			if(counter1<=counter2)//If still valid, still checking.
			{
				T temp = input.get(counter1);
				input.set(counter1, input.get(counter2));
				input.set(counter2, temp);
				counter1++;
				counter2--;//Counters go on.
			}
		}
		if(counter1<fin)//If haven't reach base case, solve recursively.
			quickSorting(input,counter1,fin);
		if(counter2>ini)//If haven't reach base case, solve recursively.
			quickSorting(input,ini,counter2);
	}
	
	public static <T extends Comparable<? super T>> void quickSorting(T[] input, int ini, int fin)//Applicable to any object that either directly implements the Comparable interface or inherit from a super class that implements the interface, sort the array.
	{
		if(fin-ini==0)//Base case, nothing to sort, exit.
			return;
		T pivot = input[(ini+fin)/2];//The pivot point to compare the other numbers.
		int counter1 = ini;
		int counter2 = fin;
		while(counter1<counter2)//While still checking, possibly not in order.
		{
			while(input[counter1].compareTo(pivot)<0)//If valid, go on.
				counter1++;
			while(input[counter2].compareTo(pivot)>0)//If valid, go on.
				counter2--;
			if(counter1<=counter2)//If still valid, still checking.
			{
				T temp = input[counter1];
				input[counter1] = input[counter2];
				input[counter2] = temp;//Flip the two unordered values.
				counter1++;
				counter2--;//Counters go on.
			}
		}
		if(counter1<fin)//If haven't reach base case, solve recursively.
			quickSorting(input,counter1,fin);
		if(counter2>ini)//If haven't reach base case, solve recursively.
			quickSorting(input,ini,counter2);
	}
	
//	public static <T extends Comparable<? super T>> int binarySearch(T[] input, T tar)
//	{
//		quickSort(input);
//		return binarySearching(input,tar,0,input.length-1);
//	}
//	
//	public static <T extends Comparable<? super T>> int binarySearching(T[] input, T tar, int ini, int fin)
//	{
//		if(ini==fin)
//		{
//			if(input[ini].equals(tar))
//				return ini;
//			return -1;
//		}
//		int mid = (ini+fin)/2;
//		if(input[ini].compareTo(tar)==0)
//			return ini;
//		if(input[fin].compareTo(tar)==0)
//			return fin;
//		if(input[ini].compareTo(tar)<0&&input[fin].compareTo(tar)>0)
//		{
//			if(input[mid].compareTo(tar)==0)
//				return mid;
//			if(input[mid].compareTo(tar)<0)
//				return binarySearching(input,tar,mid+1,fin);
//			return binarySearching(input,tar,ini,mid-1);
//		}
//		return -1;
//	}
	
	public static void main(String[] args) 
	{
		Array.printArray("Warm Up Printing Of A Random Double Array:",Array.randomDoubles(-10.0,10.0,10));
		System.out.println("Note: all the records of time of various algorithms printed below are in nano seconds");
		System.out.println();
		System.out.println("Short run behavior of random elements");
		System.out.println("*******************");
		Integer[] tester1 = Array.randomIntegers(-1000, 1000, 10);
		Integer[] tester2 = new Integer[10];
		Integer[] tester3 = new Integer[10];
		for(int i=0; i<10; i++)
		{
			tester2[i] = tester1[i];
			tester3[i] = tester1[i];
		}
		System.out.println("Bubble");
		Timer.timerStart();
		bubbleSort(tester1);
		Timer.timerStop();
		System.out.println("Selection");
		Timer.timerStart();
		selectionSort(tester2);
		Timer.timerStop();
		System.out.println("Quick");
		Timer.timerStart();
		quickSort(tester3);
		Timer.timerStop();
		System.out.println("*******************");
		System.out.println();
		System.out.println("Sorted Arrays Short Run");
		System.out.println("*******************");
		Character[] tester4 = {'a','b','c','d','e','f','g'};
		System.out.println("Bubble");
		Timer.timerStart();
		bubbleSort(tester4);
		Timer.timerStop();
		System.out.println("Selection");
		Timer.timerStart();
		selectionSort(tester4);
		Timer.timerStop();
		System.out.println("Quick");
		Timer.timerStart();
		quickSort(tester4);
		Timer.timerStop();
		System.out.println("*******************");
		System.out.println();
		System.out.println("Reversed Arrays Short Run");
		System.out.println("*******************");
		String[] tester5 = {"fg","de","bc","a"};
		String[] tester6 = new String[4];
		String[] tester7 = new String[4];
		for(int i=0; i<4; i++)
		{
			tester6[i] = tester5[i];
			tester7[i] = tester5[i];
		}
		System.out.println("Bubble");
		Timer.timerStart();
		bubbleSort(tester5);
		Timer.timerStop();
		System.out.println("Selection");
		Timer.timerStart();
		selectionSort(tester6);
		Timer.timerStop();
		System.out.println("Quick");
		Timer.timerStart();
		quickSort(tester7);
		Timer.timerStop();
		System.out.println("*******************");
		System.out.println();
		System.out.println("Long Run Behavior");
		System.out.println("*******************");
		Double[] tester8 = Array.randomDoubles(-100000.0, 100000.0, 10000);
		Double[] tester9 = new Double[10000];
		Double[] tester10 = new Double[10000];
		for(int i=0; i<10000; i++)
		{
			tester9[i] = tester8[i];
			tester10[i] = tester8[i];
		}
		System.out.println("Bubble");
		Timer.timerStart();
		bubbleSort(tester8);
		Timer.timerStop();
		System.out.println("Selection");
		Timer.timerStart();
		selectionSort(tester9);
		Timer.timerStop();
		System.out.println("Quick");
		Timer.timerStart();
		quickSort(tester10);
		Timer.timerStop();
		System.out.println("*******************");
		System.out.println();
		System.out.println("Conclusion");
		System.out.println("For short run behavior of random elements, selection sort appears to be the most efficient and bubble sort is likely to be the worst, though some times quick sort can be more inefficient.");
		System.out.println("For short run behavior of arrays that have been already sorted, either selection sort or quick sort appears to be the most efficient and bubble sort seems the worst.");
		System.out.println("For short run behavior of elements of reversed order, selection sort is generally the most efficient and bubble sort should be the worst.");
		System.out.println("For long run behavior, it is clear that quick sort has the absolute highest efficiency and bubble sort has the least.");
	}
	

}
