import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MemoryModel {

	private int score, firstCardNumber, secondCardNumber, amountOfCardsLeft;
	private String[] currentlyUsedIcons = new String[16];
	private boolean firstCardSelected, isAPair;
	private File scoreboard;
	
	public MemoryModel(){
		firstCardSelected=false;
		isAPair=false;
		scoreboard = new File("scoreboard.txt");
	}
	
	public void setAmountOfCardsLeft() {
		amountOfCardsLeft=16;
	}
	
	public int getAmountOfCardsLeft() {
		return amountOfCardsLeft;
	}
	
	public void decrementAmountOfCardsLeft() {
		amountOfCardsLeft-=2;
	}
	
	public void resetScore(){
		score=0;
	}
	
	public String[] setIconsPosition(String[] possibleIcons) {
		int columnNumber,rowNumber;
		for (int i=0;i<8;i++){
			
			columnNumber=(int)(Math.random()*4);
			rowNumber=(int)(Math.random()*4);
			while(currentlyUsedIcons[4*columnNumber+rowNumber]!=null){
				columnNumber=(int)(Math.random()*4);
				rowNumber=(int)(Math.random()*4);
			}
			currentlyUsedIcons[4*columnNumber+rowNumber]=possibleIcons[i];
			
			columnNumber=(int)(Math.random()*4);
			rowNumber=(int)(Math.random()*4);
			while(currentlyUsedIcons[4*columnNumber+rowNumber]!=null){
				columnNumber=(int)(Math.random()*4);
				rowNumber=(int)(Math.random()*4);
			}
			currentlyUsedIcons[4*columnNumber+rowNumber]=possibleIcons[i];
			
		}
		return currentlyUsedIcons;
	}
	
	public String getScoreboard() {
		BufferedReader toRead;
		String message=new String("Best scores:\n");
		int previousScore=0;
		try {
			toRead = new BufferedReader(new FileReader(scoreboard));
			String info = toRead.readLine();
			for(int i=1;i<11&&info!=null;i++){

				String lines[] = info.split(" ");
				if(Integer.parseInt(lines[1])==previousScore)i--;
				message+=i +". " + info + "pkt\n";
				previousScore=Integer.parseInt(lines[1]);
				info = toRead.readLine();
			}
			toRead.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}

	public boolean getFirstCardSelected(){
		return firstCardSelected;
	}

	public String getCurrentlyUsedIcon(int i) {
		return currentlyUsedIcons[i];
	}

	public boolean getIsAPair() {
		return isAPair;
	}

	public int getFirstCardNumber() {
		return firstCardNumber;
	}
	
	public int getSecondCardNumber() {
		return secondCardNumber;
	}

	public void setFirstCardNumber(int i) {
		firstCardNumber = i;
	}

	public void setSecondCardNumber(int i) {
		secondCardNumber=i;
	}

	public void incrementScore() {
		score+=5;
	}

	public int getScore() {
		return score;
	}

	public void setCurrentlyUsedIcon(int iconNumber, String icon) {
		currentlyUsedIcons[iconNumber]=icon;
	}

	public void setFirstCardSelected(boolean b) {
		firstCardSelected=b;
	}

	public void setIsAPair(boolean b) {
		isAPair=b;
	}

	public void decrementScore() {
		score-=2;
	}

//	public boolean noCardsLeft() {
//		int cardsLeft=0;
//		for(int i=0;i<16;i++){
//			if(currentlyUsedIcons[i]!=null)
//				cardsLeft++;
//		}
//		if(cardsLeft==0)return true;
//		else return false;
//	}

	public void writeToScoreboard(String playerName) {
		String newPlayerName = playerName;
		int newScore = score;
		if(newPlayerName.length()==0)newPlayerName="AnonymousPlayer";
		BufferedReader toRead;
		
		try {
			toRead = new BufferedReader(new FileReader(scoreboard));
			ArrayList<String>playerNames = new ArrayList<String>();
			ArrayList<Integer>scores = new ArrayList<Integer>();
			String info = toRead.readLine();
			while(info!=null){
				String[] lines = info.split(" ");
				String tempName=lines[0];
				Integer tempScore=Integer.parseInt(lines[1]);
				if(tempScore<=newScore){
					playerNames.add(newPlayerName);
					scores.add(newScore);
					newPlayerName=tempName;
					newScore=tempScore;
				}else{
					playerNames.add(tempName);
					scores.add(tempScore);
				}
				info = toRead.readLine();			
			}
			playerNames.add(newPlayerName);
			scores.add(newScore);
			toRead.close();
			
			scoreboard.delete();
			scoreboard = new File("scoreboard.txt");
			
			PrintWriter toWrite = new PrintWriter(new BufferedWriter(new FileWriter(scoreboard)));
			for(int i=0;i<playerNames.size();i++){
				info=playerNames.get(i) + " " + String.valueOf(scores.get(i));
				toWrite.println(info);
			}
			toWrite.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
