import java.util.Random;
import java.util.Scanner;


public class Hangman {
	private String bank[] = {"Car","Giraffe","Hippo","economic","land","finicky","hospitable","jazzy","legs","lip","look","infamous","important","maid","light","rain","wool","fence","lovely","develop","gaze","help","messy","slim","name","nippy","stay","race","quirky","striped","brake","sour","cub","cannon","dad","pick","friend","slow","trite","strong","sturdy","uptight","foot"};
	private  Random randomGenerator = new Random();
	private int length;
	public StringBuilder current;
	private char word[]; 

	private StringBuilder pastGuesses;
	

	private int numOfGuesses;

	public Hangman(){

		generate();

	}

	public String guess(char guess){

		boolean past =checkPast(guess);
		
		if(numOfGuesses>4){
			System.out.println(numOfGuesses);
			generate();
			return "dead";
		}
		else if(new String(current).equalsIgnoreCase(new String(word))){
			generate();
			return "success";
		}
		
		boolean found =false;
		for(int i=0;i<length;i++){

			if(Character.toLowerCase(word[i])==Character.toLowerCase(guess)){
				current.setCharAt(i, guess);
				found =true;
			}
			
		}
		if(past){
			return "past";
		}
		else if(!found){
			
			numOfGuesses++;
			return "wrong";
		}
		else if(new String(current).equalsIgnoreCase(new String(word))){
			
			generate();
			return "success";
		}

		return current.toString();
	}

	public StringBuilder getCurrent() {
		return current;
	}

	private boolean checkPast(char guess){
		
		Boolean flag=false;
		for(int i=0;i<pastGuesses.length();i++){
			if(Character.toLowerCase(pastGuesses.charAt(i))==Character.toLowerCase(guess)){
				
				return true;
			}
			
		}
		
		pastGuesses.append(guess+" ");
		return false;
	}
	
	public int getNumOfGuesses() {
		return numOfGuesses;
	}

	public int getLength(){
		return length;
	}
	
	public String getPastGuesses() {
		return pastGuesses.toString();
	}
	
	public String getWord() {
		return new String(word);
	}
	
	public static void main(String args[]){

		Hangman h = new Hangman();

		String guess="";
		String in="";
		while(!guess.equals("exit")){

			try{
				System.out.println();
				System.out.println("Guess a letter:");
				in = new Scanner(System.in).next();
				System.out.println();
			}
			catch(Exception e){

				System.out.println("Guess a letter:");
				in = new Scanner(System.in).next();
			}
			
			System.out.println(h.guess(in.charAt(0)));
		}
	}
	
	private void generate(){
		
		int choice = randomGenerator.nextInt(bank.length);
		length=bank[choice].length();
		word=new char[length];
		current = new StringBuilder();
		pastGuesses=new StringBuilder();
		numOfGuesses=0;
		
		for(int i=0;i<length;i++){

			current.append("-");
			word[i]=bank[choice].charAt(i);
		}
	}

}
