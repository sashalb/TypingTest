package typingtest;
import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Starter
{
    //only one Scanner Declaration for the whole program
    static Scanner scan = new Scanner(System.in);

    //path of words file
    static String FilePath = "C:\\Users\\pacho\\eclipse-workspace\\TypingTest2\\src\\typingtest\\parole.txt";
    
    //static int n = 0;
    //static String typedWords = "";


    public static void main(String[] args) throws InterruptedException, IOException
    {
        //start
        System.out.println("*** TYPING TEST! ***");
        
        //check if the words file exists
        File f = new File(FilePath);	
        if (f.exists()) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println();
            System.out.print("Inserisci il numero di parole da scrivere: ");
            int n = scan.nextInt();

            try {    		 
                if (GenerateRandomWords(n));
                    ManageWords();	    
                    
            } catch (Exception ex) {
                System.out.println("Si è verificato un errore: " + ex.getMessage());
            }
    }
        else
            System.out.println("File " + FilePath + " non esistente!");
    }

    //GenerateRandomWords() = user input (number of words) + words generation
    //if user types a word number !=0 && <= totalWords in the file, return true
    	//else, we can't proceed
    //if true, generate that exact number of random words taken from the file

    private static Boolean GenerateRandomWords(int n) throws IOException, InterruptedException
    {
    	//countdown
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Riscrivi le parole tra 3");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Riscrivi le parole tra 2");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Riscrivi le parole tra 1");
        TimeUnit.SECONDS.sleep(1);

        System.out.println();
        
    	//list containing the words file
        ArrayList<String> WordsFileList = new ArrayList<String>();

        //load words from the file
        FileReader file = new FileReader(FilePath);
        BufferedReader buff = new BufferedReader(file);

        //reading the whole line as a string
        String line = buff.readLine();

        //check EOF
        while (line != null) {
            WordsFileList.add(line);
            line = buff.readLine();
        }
        
        buff.close();
        
        if (n>0 && n<=WordsFileList.size()) {
            //convert list to array
            String randomWords[] = WordsFileList.toArray(new String[WordsFileList.size()]);
            
            //print the words
            Random rand = new Random();

            for (int i=0; i<n; i++) {
                System.out.print(randomWords[rand.nextInt(randomWords.length)] + " ");		//generate random words from array
            }
        }
        else
            return false;

        System.out.println();
        return true;
	    
    }

    //ManageWords() = user input (words typed) + WPM calculation
    //user types requested words and the program calculates WPM
    
    private static void ManageWords() throws IOException
    {
        double nanoSecondStart = LocalTime.now().toNanoOfDay();		//nanosecond start (words are generated)

        try {
            scan = new Scanner(System.in);		//recreate Scanner Object to prevent "No line found" error

            String typedWords = "";
            typedWords = scan.nextLine();

            double nanoSecondEnd = LocalTime.now().toNanoOfDay();	//nanosecond end (user presses Enter)
            
            int wpm = Stats.calculateWPM(nanoSecondStart, nanoSecondEnd, typedWords);

            System.out.println("WPM: " + wpm + ".");            
        }
        finally {
            if (scan != null)
                scan.close();
        }
    }
}
