/**
 * Klasa kontrolera gry Memory.
 * @author Daniel Gie�dowski
 *
 */
public class MemoryController {
	
	/**
	 * Widok gry, kt�rym zajmuje si� kontroler.
	 */
	private MemoryView view;
	/**
	 * Model gry kt�rym zajmuje si� kontroler.
	 */
	private MemoryModel model;
	
	/**
	 * Konstruktor kontrolera. Montuje otrzymany model w konrolerze.
	 * @param model Model montowany w kontrolerze.
	 */
	public MemoryController(MemoryModel model){
		this.model = model;
	}
	
	/**
	 * Montuje podany obraz gry w kontrolerze.
	 * @param view Montowany obraz.
	 */
	public void registerView(MemoryView view){
		this.view = view;
	}
	
	/**
	 * Funcja wywo�ywana przyciskiem zaczynaj�cym gr�. Resetuje wynik. Wydaje polecenie zmiany zamontowanego
	 * w ramce panelu z menu na gr�. Nakazuje modelowi wylosowa� miejsca wyst�powania kart. Resetuje
	 * wszystkie przyciski kart oraz ilo�� poprawnie znalezionych kart.
	 */
	public void startGame(){
		model.resetScore();
		view.setScoreLabel(0);
		view.changePanelToGame(true);
		model.setCurrentlyUsedIcons(view.getPossibleIcons());
		view.resetButtonsIcons();
		model.resetAmountOfCardsLeft();
	}
	
	/**
	 * Wywo�ywana przyciskiem wy�wietlania tablicy wynik�w. Przekazuje tablic� wynik�w z modelu do widoku
	 * by ten m�g� wy�wietli� komunikat.
	 */
	public void handleScoreboard(){
		view.showMessageWindow(model.getScoreboard());
	}
	
	/**
	 * Wywo�ywana przyciskiem wy��czania gry. Wy��cza ca�� aplikacj�.
	 */
	public void quitGame(){
		System.exit(0);
	}
	
	/**
	 * Wywo�ywana przez naci�ni�cie dowolnego przyciksu karty. Odpowiada za ca�� faz� rozgrywki.
	 * Przekazuje obliczenia modelu wykonywane w trakcie rozgrywki do widoku.
	 * @param buttonNumber Numer naci�ni�tego przycisku karty.
	 */
	public  void buttonPressed(int buttonNumber){
		if(!model.getFirstCardSelected()&&model.getCurrentlyUsedIcon(buttonNumber)!=null){
			if(!model.getIsAPair()){
				view.resetButtonIcon(model.getFirstCardNumber());
				view.resetButtonIcon(model.getSecondCardNumber());
			}
			model.setFirstCardNumber(buttonNumber);
			view.setButtonIcon(buttonNumber,model.getCurrentlyUsedIcon(buttonNumber));
			model.setFirstCardSelected(true);	
		}
		
		if(model.getFirstCardSelected()&&buttonNumber!=model.getFirstCardNumber()&&model.getCurrentlyUsedIcon(buttonNumber)!=null){
			model.setSecondCardNumber(buttonNumber);
			view.setButtonIcon(buttonNumber,model.getCurrentlyUsedIcon(buttonNumber));
			if(model.getCurrentlyUsedIcon(model.getFirstCardNumber())==model.getCurrentlyUsedIcon(model.getSecondCardNumber())){
				model.incrementScore();
				model.setCurrentlyUsedIcon(model.getFirstCardNumber(),null);
				model.setCurrentlyUsedIcon(model.getSecondCardNumber(),null);
				model.setIsAPair(true);
				model.decrementAmountOfCardsLeft();
			}else{
				model.decrementScore();
				model.setIsAPair(false);
			}
			view.setScoreLabel(model.getScore());
			model.setFirstCardSelected(false);
			if(model.getAmountOfCardsLeft()==0){
				view.changePanelToGame(false);
				view.showMessageWindow("Player " + view.getPlayerNameInput() + " scored " + model.getScore() + " points.\n");
				model.writeToScoreboard(view.getPlayerNameInput());
			}
		}
	}
	
}
