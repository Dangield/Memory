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
 * @author Daniel Gie³dowski
 *
 */
public class MemoryModel {

	/**
	 * Aktualny wynik gry.
	 */
	private int score;
	/**
	 * Numer karty klikniêtej jako pierwsza.
	 */
	private int firstCardNumber;
	/**
	 * Numer karty klikniêtej jako druga.
	 */
	private int secondCardNumber;
	/**
	 * Iloœæ kart pozosta³ych w grze, kart dla których nie znaleziono jeszcze par.
	 */
	private int amountOfCardsLeft;
	/**
	 * Kombinacja 16 obrazów u¿ywanych w grze jako wygl¹dy kolejnych przycisków kart.
	 */
	private String[] currentlyUsedIcons = new String[16];
	/**
	 * Znacznik pokazuj¹cy czy klikn¹³eœ pierwsz¹ kartê z wybieranej pary.
	 */
	private boolean firstCardSelected;
	/**
	 * Zancznik pokazuj¹cy czy ostatnia wybrana para kart ma takie same obrazy.
	 */
	private boolean isAPair;
	/**
	 * Plik zawieraj¹cy tablicê wyników.
	 */
	private File scoreboard;
	
	/**
	 * Konstruktor klasy modelu gry Memory. Resetuje wskaŸniki i ustawia nazwê pliku zawieraj¹cego
	 * tablicê wyników.
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
	 * Losuje kolejnoœæ kart w grze wykorzystuj¹c otrzymane obrazy.Zapisuje sekwencjê w macierzy 
	 * stringów currentlyUsedIcons.
	 * @param possibleIcons Wszystkie mo¿liwe obrazy kart.
	 * @return Zwraca macierz string zawieraj¹ce nazwy obrazów kolejnych kart.
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
	 * Zmienia aktualnie u¿ywany obraz dla jednego wybranego przycisku.
	 * @param iconNumber Numer przycisku którego obraz jest zmieniany.
	 * @param icon Nowy wygl¹d przycisku.
	 */
	public void setCurrentlyUsedIcon(int iconNumber, String icon) {
		currentlyUsedIcons[iconNumber]=icon;
	}

	/**
	 * Zwraca aktualny obraz wybranego przycisku.
	 * @param i Numer przycisku.
	 * @return Nazwê pliku zaweiraj¹cego obraz przycisku.
	 */
	public String getCurrentlyUsedIcon(int i) {
		return currentlyUsedIcons[i];
	}
	
	/**
	 * Resetuje iloœæ kart pozosta³ych w grze ustawiaj¹c j¹ na maksimum.
	 */
	public void resetAmountOfCardsLeft() {
		amountOfCardsLeft=16;
	}
	
	/**
	 * Zmniejsza iloœæ kart pozosta³ych w grze o parê. Wywo³ywana kiedy znajdowanajest poprawna para.
	 */
	public void decrementAmountOfCardsLeft() {
		amountOfCardsLeft-=2;
	}
	
	/**
	 * Zwraca iloœæ kart pozosta³ych w grze.
	 * @return Iloœæ niesparowanych kart pozosta³ych w grze.
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
	 * Ustawia wartoœæ znacznika firstCardSelected.
	 * @param b Nowa wartoœc znacznika.
	 */
	public void setFirstCardSelected(boolean b) {
		firstCardSelected=b;
	}

	/**
	 * Zwraca wartoœc znacznika firstCard selected.
	 * @return Wartoœc znacznika firstCardSelected.
	 */
	public boolean getFirstCardSelected(){
		return firstCardSelected;
	}

	/**
	 * Ustawia wartoœæ znacznika isAPair
	 * @param b Nowa wartoœc znacznika.
	 */
	public void setIsAPair(boolean b) {
		isAPair=b;
	}

	/**
	 * Zwraca wartoœæ  isAPair.
	 * @return Wartoœæ znacznika isAPair.
	 */
	public boolean getIsAPair() {
		return isAPair;
	}

	/**
	 * Zwraca aktualn¹ wartoœæ wyniku trwaj¹cej gry.
	 * @return Aktualny wynik rozgrywki.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Zwiêksza wynik rozgrywki o 5. Wywo³ywany kiedy gracz odnajdzie w³aœciw¹ parê.
	 */
	public void incrementScore() {
		score+=5;
	}

	/**
	 * Zmniejsza wynik rozgrywki o 2. Wywo³ywany kiedy gracz wybierze 2 karty nie bêd¹ce par¹.
	 */
	public void decrementScore() {
		score-=2;
	}

	/**
	 * Zapisuje wynik aktualnie zakoñczonej gry do pliku zawieraj¹cego tablicê wyników na odpowiednie
	 * miejsce.Jeœli gracz przed rozgrywk¹ nie poda³ indywidualnego imienia jego wynik zostanie
	 * zapisany pod imieniem AnonymousPlayer.
	 * @param playerName imiê gracza podane w odpowiednim polu przed gr¹.
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
	 * Otwiera plik z tablic¹ wyników i zwraca string zawieraj¹cy 10 najlepszych wyników gotowy do 
	 * wyœwietlenia w oknie.
	 * @return String wyœwietlany potem w oknie zawieraj¹cy tablicê najlepszych wyników.
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
