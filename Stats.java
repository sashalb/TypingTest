package typingtest;

public class Stats
{
	public static int calculateWPM(double start, double end, String str)
	{
		//calculate Words Per Minute after one game
		
		double nanoDiff = end - start;					//total time in nanoseconds
		double totalSecs = nanoDiff / 1000000000.0;		//nanoseconds to seconds
		
		//calculate WPM: (totalChars/5)/1minute
		int totalChars = str.length();
		int wpm = (int)((((double) totalChars / 5) / totalSecs) * 60);
			//double casting: no data lost when dividing integers
			//int casting: converts final result to an integer
		return wpm;
	}
	
	public static int errorCount(String[] generatedWords, String userInput, int n)
	{
		//compares two stringsArrays word by word
		
		//String randomWords[] = new String[generatedWords.size()];		//convert list to array
		//generatedWords.toArray(randomWords);
		String newUserInput[] = userInput.split(" ");			//convert user-typed string to array
		int err = 0;
		for(int i=0; i<n; i++) {
			if (generatedWords[i].contentEquals(newUserInput[i]))
				err = err + 0;
			else {
				err++;
			}
		}
		return err;
	}
	
	public static int scoreWithErrors(double start, double end, String str, int errors)
	{
		//calculates the final score including the errors in the wpm calculation
		
		int score = 0;
		int totalChars = str.length();
		double nanoDiff = end - start;
		double totalSecs = nanoDiff / 1000000000.0;
		
		if (errors != 0)
			score = (int)(((((double) totalChars / 5) - errors) / totalSecs) * 60);
				else
			score = (int)((((double) totalChars / 5) / totalSecs) * 60);
		return score;
	}
	
	public static float averageWPM(int totalWPM, int cont)
	{
		//calculates the average WPM score
		
		float avg = totalWPM / cont;
		return avg;
	}
	
	public static float averageErrors(int totalErr, int cont)
	{
		//calculates the average number of errors
		
		float avg = totalErr / cont;
		return avg;
	}
	
	public static int maximumWPM(int max, int wpm)
	{
		//returns the maximum WPM reached
		
		if (wpm > max)
			max = wpm;
		return max;
	}
	
	public static int minimumWPM(int min, int wpm)
	{
		//returns the minimum WPM reached
		
		if (wpm < min)
			min = wpm;
		return min;
	}
	
	public static int highScore(int hs, int score)
	{
		//return the best score
		
		if (score > hs)
			hs = score;
		return hs;
	}
}
