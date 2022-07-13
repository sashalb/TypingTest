package typingtest;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Starter
{
	static Scanner scan = new Scanner(System.in);
	static String FilePath = "C:\\Users\\pacho\\eclipse-workspace\\NewTypingTest\\src\\typingtest\\parole.txt";
	static int loopCounter = 0;
	static int sumWPM, sumErr = 0;
	static float avgWPM, avgErr = 0;
	static int maxWPM = 0;
	static int minWPM = 1000;
	static int highScore = 0;
	//static ArrayList<String> generatedWords;	//has to be filled with the random generated words
	static String generatedWords[];

	public static void main(String[] args) throws IOException, InterruptedException
	{
		//start
		if (!InputOutput.startGame(FilePath)) {
			//file cannot be found
			System.out.println("File " + FilePath + " non esistente!");
		} else
		{
			//here the file exists
			ArrayList<String> ListOfWords = InputOutput.readFile(FilePath);
			int MaxNumberOfWords = ListOfWords.size();
			TimeUnit.SECONDS.sleep(1);
			
			try {
				boolean ContinueProcess = true;
				while (ContinueProcess) {
					int n = InputOutput.askForNumber(MaxNumberOfWords);
					if (GenerateRandomWords(n)) {
						ManageWords(n, generatedWords);		//here n != 0
					} else {								//here the conditions are not satisfied: exit or retry
						if (n == 0) {
							InputOutput.finalStats(loopCounter, highScore, avgWPM, avgErr, maxWPM, minWPM);
							ContinueProcess = false;
						} else {							//here user has to retry typing the number
							System.out.println("Numero non accettato, riprovare.");
						}
					}
				}
				
			} catch (Exception ex) {
				System.out.println("Si è verificato un errore: " + ex.getMessage());
			}
		}					
	}
	
	private static boolean GenerateRandomWords(int n) throws InterruptedException, IOException
	{
		//generates the number of random words input by user
		//input must satisfy the conditions
		
		ArrayList<String> ListOfWords = InputOutput.readFile(FilePath);
		int MaxNumberOfWords = ListOfWords.size();
		if (n>0 && n<=MaxNumberOfWords) {
			loopCounter++;
			InputOutput.countDown(loopCounter);
			generatedWords = InputOutput.printRandomWords(ListOfWords, n);
		} else
			return false;
		
		System.out.println();
		return true;
	}
	
	private static void ManageWords(int n, String[] generatedWords) throws IOException
	{
		//user types the words + calculate WPM
		
		double nanoSecondStart = LocalTime.now().toNanoOfDay();			//storing the nanosecond the words are generated
		try {
			String typedWords = InputOutput.writeWords();
			double nanoSecondEnd = LocalTime.now().toNanoOfDay();		//storing the nanosecond user finishes typing pressing enter
			
			int wpm = Stats.calculateWPM(nanoSecondStart, nanoSecondEnd, typedWords);
			maxWPM = Stats.maximumWPM(maxWPM, wpm);
			minWPM = Stats.minimumWPM(minWPM, wpm);
			sumWPM = sumWPM + wpm;
			avgWPM = Stats.averageWPM(sumWPM, loopCounter);
			
			int err = Stats.errorCount(generatedWords,  typedWords, n);
			sumErr = sumErr + err;
			avgErr = Stats.averageErrors(sumErr, loopCounter);
			
			int finalScore = Stats.scoreWithErrors(nanoSecondStart, nanoSecondEnd, typedWords, err);
			highScore = Stats.highScore(highScore, finalScore);
			System.out.println("WPM: " + wpm);
			System.out.println("Parole sbagliate: " + err);
			System.out.println("Punteggio finale: " + finalScore);
			//System.out.println("Media WPM: " + avgWPM);
			
		} catch (Exception ex) {
			System.out.println("Si è verificato un errore: " + ex.getMessage());
		}
	}
}
