package typingtest;

public class Stats {

	public static int calculateWPM(double start, double end, String str)		//nanosec Start, nanosec End, typedWords
	{
		double nanoDiff = end - start;			//total time in nanoseconds
		double totalSecs = nanoDiff / 1000000000.0;		//nanoseconds to seconds
		
		//calculate Words Per Minute: (xChars/5) / 1minute 
		int totalChars = str.length();
		int wpm = (int)((((double) totalChars / 5) / totalSecs) * 60);
			//double casting prevents losing data from the int division totalChars/5
	        //int casting in order to convert the final result (double) to an integer
		
		return wpm;
	}
	
	/*
	public static boolean checkLength(String a[], String b[])
	{
		if (a.length == b.length)
			return true;
		else
			return false;
	}
	*/
	
	//IN: String[] randomWords, String typedWords
	public static int errorCount(String a[], String b, int n)		//A randomWords (array of strings), B typedWords (string)
	{
		//first we convert B to an string array
		String[] newB = "b".split("");
		
		//error count
		int err = 0;
		for (int i=0; i<n; i++) {
			if(a[i] == newB[i]);
				err++;
		}
		
		return err;
	}
	
	//string to string array test
	public static void arrayTest(String x, int n)
	{
		String[] newX = "x".split("");
		for (int i=0; i<n; i++) {
			System.out.print(newX[i] + " ");
		}
	}
}