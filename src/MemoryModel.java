import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Klasa modelu gry Memory.
 * @author Daniel Gie�dowski
 *
 */
public class MemoryModel {

	/**
	 * Aktualny wynik gry.
	 */
	private int score;
	/**
	 * Numer karty klikni�tej jako pierwsza.
	 */
	private int firstCardNumber;
	/**
	 * Numer karty klikni�tej jako druga.
	 */
	private int secondCardNumber;
	/**
	 * Ilo�� kart pozosta�ych w grze, kart dla kt�rych nie znaleziono jeszcze par.
	 */
	private int amountOfCardsLeft;
	/**
	 * Kombinacja 16 obraz�w u�ywanych w grze jako wygl�dy kolejnych przycisk�w kart.
	 */
	private String[] currentlyUsedIcons = new String[16];
	/**
	 * Znacznik pokazuj�cy czy klikn��e� pierwsz� kart� z wybieranej pary.
	 */
	private boolean firstCardSelected;
	/**
	 * Zancznik pokazuj�cy czy ostatnia wybrana para kart ma takie same obrazy.
	 */
	private boolean isAPair;
	/**
	 * Plik zawieraj�cy tablic� wynik�w.
	 */
	private File scoreboard;
	
	/**
	 * Konstruktor klasy modelu gry Memory. Resetuje wska�niki i ustawia nazw� pliku zawieraj�cego
	 * tablic� wynik�w.
	 */
	public MemoryModel(){
		firstCardSelected=false;
		isAPair=false;
		scoreboard = new File("scoreboard.txt");
	}
	
	/**
	 * Resetuje wynik rozgrywki.
	 */
	public void resetScore(){
		score=0;
	}
	
	/**
	 * Losuje kolejno�� kart w grze wykorzystuj�c otrzymane obrazy.Zapisuje sekwencj� w macierzy 
	 * string�w currentlyUsedIcons.
	 * @param possibleIcons Wszystkie mo�liwe obrazy kart.
	 * @return Zwraca macierz string zawieraj�ce nazwy obraz�w kolejnych kart.
	 */
	public String[] setCurrentlyUsedIcons(String[] possibleIcons) {
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

	/**
	 * Zmienia aktualnie u�ywany obraz dla jednego wybranego przycisku.
	 * @param iconNumber Numer przycisku kt�rego obraz jest zmieniany.
	 * @param icon Nowy wygl�d przycisku.
	 */
	public void setCurrentlyUsedIcon(int iconNumber, String icon) {
		currentlyUsedIcons[iconNumber]=icon;
	}

	/**
	 * Zwraca aktualny obraz wybranego przycisku.
	 * @param i Numer przycisku.
	 * @return Nazw� pliku zaweiraj�cego obraz przycisku.
	 */
	public String getCurrentlyUsedIcon(int i) {
		return currentlyUsedIcons[i];
	}
	
	/**
	 * Resetuje ilo�� kart pozosta�ych w grze ustawiaj�c j� na maksimum.
	 */
	public void resetAmountOfCardsLeft() {
		amountOfCardsLeft=16;
	}
	
	/**
	 * Zmniejsza ilo�� kart pozosta�ych w grze o par�. Wywo�ywana kiedy znajdowanajest poprawna para.
	 */
	public void decrementAmountOfCardsLeft() {
		amountOfCardsLeft-=2;
	}
	
	/**
	 * Zwraca ilo�� kart pozosta�ych w grze.
	 * @return Ilo�� niesparowanych kart pozosta�ych w grze.
	 */
	public int getAmountOfCardsLeft() {
		return amountOfCardsLeft;
	}

	/**
	 * Ustawia numer pierwszej wybieranej karty z pary.
	 * @param i Numer karty.
	 */
	public void setFirstCardNumber(int i) {
		firstCardNumber = i;
	}

	/**
	 * Zwraca numer pierwszej wybranej karty.
	 * @return Numer pierwszej karty z wybieranej pary.
	 */
	public int getFirstCardNumber() {
		return firstCardNumber;
	}

	/**
	 * 
	 * Ustawia numer drugiej wybieranej karty z pary.
	 * @param i Numer karty.
	 */
	public void setSecondCardNumber(int i) {
		secondCardNumber=i;
	}
	
	/**
	 * Zwraca numer drugiej wybranej karty.
	 * @return Numer drugiej karty z wybieranej pary.
	 */
	public int getSecondCardNumber() {
		return secondCardNumber;
	}

	/**
	 * Ustawia warto�� znacznika firstCardSelected.
	 * @param b Nowa warto�c znacznika.
	 */
	public void setFirstCardSelected(boolean b) {
		firstCardSelected=b;
	}

	/**
	 * Zwraca warto�c znacznika firstCard selected.
	 * @return Warto�c znacznika firstCardSelected.
	 */
	public boolean getFirstCardSelected(){
		return firstCardSelected;
	}

	/**
	 * Ustawia warto�� znacznika isAPair
	 * @param b Nowa warto�c znacznika.
	 */
	public void setIsAPair(boolean b) {
		isAPair=b;
	}

	/**
	 * Zwraca warto��  isAPair.
	 * @return Warto�� znacznika isAPair.
	 */
	public boolean getIsAPair() {
		return isAPair;
	}

	/**
	 * Zwraca aktualn� warto�� wyniku trwaj�cej gry.
	 * @return Aktualny wynik rozgrywki.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Zwi�ksza wynik rozgrywki o 5. Wywo�ywany kiedy gracz odnajdzie w�a�ciw� par�.
	 */
	public void incrementScore() {
		score+=5;
	}

	/**
	 * Zmniejsza wynik rozgrywki o 2. Wywo�ywany kiedy gracz wybierze 2 karty nie b�d�ce par�.
	 */
	public void decrementScore() {
		score-=2;
	}

	/**
	 * Zapisuje wynik aktualnie zako�czonej gry do pliku zawieraj�cego tablic� wynik�w na odpowiednie
	 * miejsce.Je�li gracz przed rozgrywk� nie poda� indywidualnego imienia jego wynik zostanie
	 * zapisany pod imieniem AnonymousPlayer.
	 * @param playerName imi� gracza podane w odpowiednim polu przed gr�.
	 */
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
	
	/**
	 * Otwiera plik z tablic� wynik�w i zwraca string zawieraj�cy 10 najlepszych wynik�w gotowy do 
	 * wy�wietlenia w oknie.
	 * @return String wy�wietlany potem w oknie zawieraj�cy tablic� najlepszych wynik�w.
	 */
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
	
}
