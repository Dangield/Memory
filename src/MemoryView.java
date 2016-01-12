import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MemoryView {
	
	private JFrame window = new JFrame("Memory");
	
	private JPanel menuPanel = new JPanel();
//	private JButton DefaultCardsButton = new JButton();
	private JLabel playerNameLabel = new JLabel("Player name: ");
	private JTextField playerNameInput = new JTextField(10);
	private JButton newGameButton = new JButton("New Game");
	private JButton scoreboardButton = new JButton("Scoreboard");
	private JButton quitButton = new JButton("Quit");
	
	private JPanel gamePanel = new JPanel();
	private JLabel scoreLabel = new JLabel("");
	private JButton[] cardButtons = new JButton[16];
	private String coverIcon="ico.png";
	private String[] possibleIcons= {"1.png","2.png","3.png","4.png","5.png","6.png","7.png","8.png"};
	
	public MemoryView(MemoryController controller) {
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Memory");
		window.setSize(800, 800);
		
		menuPanel.setLayout(new GridBagLayout());
		
		Font font = new Font("Helvetica", Font.PLAIN, 30);
		
//		addComp(menuPanel, DefaultCardsButton, 0,0,1,1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
//		DefaultCardsButton.setIcon(new ImageIcon("ico.png"));
		
		addComp(menuPanel, playerNameLabel, 0,0,1,1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		playerNameLabel.setFont(font);
		
		addComp(menuPanel, playerNameInput, 1,0,1,1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		playerNameInput.setFont(font);
		playerNameInput.requestFocus();

		
		addComp(menuPanel, newGameButton, 0,1,2,1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		newGameButton.setFont(font);
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.startGame();
			}
		});
		
		addComp(menuPanel, scoreboardButton, 0,2,2,1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		scoreboardButton.setFont(font);
		scoreboardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 controller.handleScoreboard();
			}
		});
		
		addComp(menuPanel, quitButton, 0,3,2,1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		quitButton.setFont(font);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.quitGame();
			}
		});
		
		gamePanel.setLayout(new GridBagLayout());
		
		addComp(gamePanel, scoreLabel, 0,0,4,1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		scoreLabel.setFont(font);

		for(int i=0; i<16; i++){
			cardButtons[i]=new JButton("");
			int columnNumber,rowNumber;
			rowNumber=i%4;
			columnNumber=(i-rowNumber)/4;
			addComp(gamePanel, cardButtons[i], columnNumber,rowNumber+1,1,1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
			cardButtons[i].setBorderPainted(false);
			cardButtons[i].setContentAreaFilled(false);
			final int nr=i;
			cardButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.buttonPressed(nr);
				}
			});
		}
		
		window.add(menuPanel);
		window.setResizable(false);
		window.setVisible(true);
	}

	private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight,int place, int stretch){
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx=xPos;
		gridConstraints.gridy=yPos;
		gridConstraints.gridwidth=compWidth;
		gridConstraints.gridheight=compHeight;
		gridConstraints.weightx=100;
		gridConstraints.weighty=100;
		gridConstraints.insets = new Insets(5,5,5,5);
		gridConstraints.anchor = place;
		gridConstraints.fill = stretch;
		thePanel.add(comp, gridConstraints);
	}

	public void changePanelToGame(boolean gameMode) {
		
		if(gameMode){
			this.window.remove(menuPanel);
			this.window.add(gamePanel);
		}else{
			this.window.remove(gamePanel);
			this.window.add(menuPanel);
		}
		this.window.repaint();
		
	}
	
	public String[] getPossibleIcons(){
		return possibleIcons;
	}

	public void resetButtonsIcons() {
		for(int i=0;i<16;i++)
			cardButtons[i].setIcon(new ImageIcon(coverIcon));
	}

	public void showMessageWindow(String message) {
		JOptionPane.showMessageDialog(window, message, "Scoreboard", JOptionPane.INFORMATION_MESSAGE);
	}

	public void resetButtonIcon(int buttonNumber) {
		cardButtons[buttonNumber].setIcon(new ImageIcon(coverIcon));
	}

	public void setButtonIcon(int buttonNumber, String newIcon) {
		cardButtons[buttonNumber].setIcon(new ImageIcon(newIcon));
	}

	public void setScoreLabel(int score) {
		scoreLabel.setText("Score: " + score);
	}

	public String getPlayerNameInput() {
		return playerNameInput.getText();
	}
	
}
