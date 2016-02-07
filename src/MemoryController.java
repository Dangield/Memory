/**
 * Klasa kontrolera gry Memory.
 * @author Daniel Gie³dowski
 *
 */
public class MemoryController {
	
	/**
	 * Widok gry, którym zajmuje siê kontroler.
	 */
	private MemoryView view;
	/**
	 * Model gry którym zajmuje siê kontroler.
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
	 * Funcja wywo³ywana przyciskiem zaczynaj¹cym grê. Resetuje wynik. Wydaje polecenie zmiany zamontowanego
	 * w ramce panelu z menu na grê. Nakazuje modelowi wylosowaæ miejsca wystêpowania kart. Resetuje
	 * wszystkie przyciski kart oraz iloœæ poprawnie znalezionych kart.
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
	 * Wywo³ywana przyciskiem wyœwietlania tablicy wyników. Przekazuje tablicê wyników z modelu do widoku
	 * by ten móg³ wyœwietliæ komunikat.
	 */
	public void handleScoreboard(){
		view.showMessageWindow(model.getScoreboard());
	}
	
	/**
	 * Wywo³ywana przyciskiem wy³¹czania gry. Wy³¹cza ca³¹ aplikacjê.
	 */
	public void quitGame(){
		System.exit(0);
	}
	
	/**
	 * Wywo³ywana przez naciœniêcie dowolnego przyciksu karty. Odpowiada za ca³¹ fazê rozgrywki.
	 * Przekazuje obliczenia modelu wykonywane w trakcie rozgrywki do widoku.
	 * @param buttonNumber Numer naciœniêtego przycisku karty.
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
