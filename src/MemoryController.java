
public class MemoryController {
	
	private MemoryView view;
	private MemoryModel model;
	
	public MemoryController(MemoryModel model){
		this.model = model;
//		this.registerView(new MemoryView(this));
	}
	
	public void registerView(MemoryView view){
		this.view = view;
	}
	
	public void startGame(){
		model.resetScore();
		view.setScoreLabel(0);
		view.changePanelToGame(true);
		model.setIconsPosition(view.getPossibleIcons());
		view.resetButtonsIcons();
		model.setAmountOfCardsLeft();
	}
	
	public void handleScoreboard(){
		view.showMessageWindow(model.getScoreboard());
	}
	
	public void quitGame(){
		System.exit(0);
	}
	
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
				model.decrementNumberOfCardsLeft();
			}else{
				model.decrementScore();
				model.setIsAPair(false);
			}
			view.setScoreLabel(model.getScore());
			model.setFirstCardSelected(false);
			if(model.getAmountOfCardsLeft()==0){
//			if(model.noCardsLeft()){
				view.changePanelToGame(false);
				view.showMessageWindow("Player " + view.getPlayerNameInput() + " scored " + model.getScore() + " points.\n");
				model.writeToScoreboard(view.getPlayerNameInput());
			}
		}
	}
	
}
