package typingtest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class InputOutput
{
	static Scanner scan = new Scanner(System.in);
	
	public static boolean startGame(String path)
	{
		//starts the game and checks if the words file exists
		
		System.out.println("*** TYPING TEST! ***");
		File f = new File(path);
		if (f.exists())
			return true;
		else
			return false;			
	}
	
	public static ArrayList<String> readFile(String path) throws IOException
	{
		//returns the total number of words in the file
		
		ArrayList<String> ListOfWords = new ArrayList<String>();	//new list containing the words file
		FileReader file = new FileReader(path);						
		BufferedReader buff = new BufferedReader(file);				//load words from the file
		String line = buff.readLine();								//reading the single lines as strings
		while (line != null) {										//checks EOF
			ListOfWords.add(line);
			line = buff.readLine();
		}
		buff.close();
		return ListOfWords;
	}
	
	public static String[] printRandomWords(ArrayList<String> list, int n)
	{
		//prints random words from the file
		
		//ArrayList<String> generatedWords = new ArrayList<String>();
		
		String randomWords[] = list.toArray(new String[list.size()]);		//convert list to array
		Random rand = new Random();
		for (int i=0; i<n; i++) {
			randomWords[i] = randomWords[rand.nextInt(randomWords.length)];
		}
		for (int i=0; i<n; i++) {
			System.out.print(randomWords[i] + " ");		//print the words
		}
		return randomWords;
	}
	
	public static int askForNumber(int words)
	{
		//asks user the number of words he wants to generate + user input
		
		scan = new Scanner(System.in);
		System.out.println();
		System.out.print("Inserisci il numero di parole da scrivere (da 1 a " + words + ", digita 0 per uscire): ");
		int n = scan.nextInt();
		//scan.close();
		return n;
	}
	
	public static void countDown(int count) throws InterruptedException
	{
		//prints the countdown before the start of the game and the number of tries
		
		System.out.println("Tentativo numero " + count);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Riscrivi le parole tra 3");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Riscrivi le parole tra 2");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Riscrivi le parole tra 1");
        TimeUnit.SECONDS.sleep(1);
        System.out.println();
	}
	
	public static String writeWords()
	{
		scan = new Scanner(System.in);
		String s = scan.nextLine();
		return s;
	}
	
	public static void finalStats(int cont, int hs, float avgWPM, float avgErr, int max, int min)
	{
		//before the program ends, it shows the final statistics of user
		
		System.out.println();
		System.out.println("*** RISULTATI FINALI *** ");
		System.out.println("High Score: " + hs);
    	System.out.println("Tentativi totali: " + cont);
    	System.out.println("Media WPM: " + avgWPM);
    	System.out.println("WPM massimi: " + max);
    	System.out.println("WPM minimi: " + min);
    	System.out.println("Media errori: " + avgErr);
    	System.out.println();
    	System.out.println("Chiusura programma.");
	}
}
