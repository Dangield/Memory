import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * 
 * Klasa widoku gry. Zawiera ramk�, panele, przyciski i ca�� reszt� tego co si� wy�wietla.
 * @author Daniel Gie�dowski
 *
 */
public class MemoryView {
	
	/**
	 * Ramka okna.
	 */
	private JFrame window = new JFrame("Memory");
	
	/**
	 * Panel menu na kt�rym znajduj� si� przyciski.
	 */
	private JPanel menuPanel = new JPanel();
	/**
	 * Napis wskazuj�cy gdzie wpisa� imi� gracza.
	 */
	private JLabel playerNameLabel = new JLabel("Player name: ");
	/**
	 * Miejsce na wpisanie imienia gracza.
	 */
	private JTextField playerNameInput = new JTextField(10);
	/**
	 * Przycisk do rozpoczynania gry.
	 */
	private JButton newGameButton = new JButton("New Game");
	/**
	 * Przycisk do wy�wietlania listy wynik�w.
	 */
	private JButton scoreboardButton = new JButton("Scoreboard");
	/**
	 * Przycisk wy��czaj�cy gr�.
	 */
	private JButton quitButton = new JButton("Quit");
	
	/**
	 * Panel gry zawieraj�cy obszar na kt�rym dzieje si� w�a�ciwa akcja.
	 */
	private JPanel gamePanel = new JPanel();
	/**
	 * Pole wy�wietlaj�ce aktualny wynik.
	 */
	private JLabel scoreLabel = new JLabel("");
	/**
	 * Przyciski zachowuj�ce si� jak karty memory.
	 */
	private JButton[] cardButtons = new JButton[16];
	/**
	 * Ikona grzbietu karty.
	 */
	private String coverIcon="ico.png";
	/**
	 * Ikony kart.
	 */
	private String[] possibleIcons= {"1.png","2.png","3.png","4.png","5.png","6.png","7.png","8.png"};
	
	/**
	 * Konstruktor widoku. Inicjuje wygl�d pocz�tkowy gry. Montuje elementy w odpowiednich panelach.
	 * Rejestruje widok w kontrolerze. Tworzy s�uchacze zdarze� odwo�uj�ce si� do funkcji w kontrolerze.
	 * Montuje panel menu.Ustawia w�a�ciwo�ci okna aplikacji.
	 * @param controller Kontroler kt�ry zajmuje si� view.
	 */
	public MemoryView(MemoryController controller) {
		controller.registerView(this);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Memory");
		window.setSize(800, 800);
		
		menuPanel.setLayout(new GridBagLayout());
		
		Font font = new Font("Helvetica", Font.PLAIN, 30);
		
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

	/**
	 * Dodaje wybrany komponent do wybranego panelu w wybranym miejscu i we wskazany spos�b w uk�adzie
	 * element�w GridBagLayout.
	 * @param thePanel Panel na kt�ry montowany jest element.
	 * @param comp Element dodawany na panel.
	 * @param xPos Pozycja elementu w poziomie.
	 * @param yPos Pozycja elementu dodawanego w pionie.
	 * @param compWidth Ilo�� miejsc zajmowanych w poziomie w uk�adzie GridBagLayout.
	 * @param compHeight Ilo�� miejsc zajmowanych w pionie w uk�adzie GridBagLayout.
	 * @param place Przesuni�cie elementu wewn�trz danego miejsca w GridBagLayout np. WEST - element po
	 * lewej stronie ramki.
	 * @param stretch Rozci�gni�cie elementu wewn�trz danego miejsca w GridBagLayout np. CENTER - element
	 * rozci�gni�ty maksymalnie w swojej ramce.
	 * @see GridBagConstraints
	 */
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

	/**
	 * Zmienia aktualnie zamontowany w ramce panel.
	 * @param gameMode Okre�la kt�ry panel ma by� zamontowany. Je�eli jest r�wny true montowany jest
	 * panel gry. Je�eli jest r�wny false montowany jest panel menu.
	 */
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

	/**
	 * Zwarac imi� gracza wpisane w PlayerNameInput
	 * @return Text b�d�cy imieniem gracza.
	 */
	public String getPlayerNameInput() {
		return playerNameInput.getText();
	}
	
	/**
	 * Zwraca obrazy kart memory.
	 * @return Tablica String zawieraj�ce nazwy obrazk�w kart memory.
	 */
	public String[] getPossibleIcons(){
		return possibleIcons;
	}

	/**
	 * Ustawia wygl�d wybranego przycisku karty.
	 * @param buttonNumber Numer przycisku karty.
	 * @param newIcon Obraz ustawiany jako wygl�d przycisku.
	 */
	public void setButtonIcon(int buttonNumber, String newIcon) {
		cardButtons[buttonNumber].setIcon(new ImageIcon(newIcon));
	}

	/**
	 * Resetuje wygl�d wybranego przycisku karty nadaj�c mu wygl�d grzbietu karty.
	 * @param buttonNumber numer przycisku kt�rego wygl�d ma by� zresetowany.
	 */
	public void resetButtonIcon(int buttonNumber) {
		cardButtons[buttonNumber].setIcon(new ImageIcon(coverIcon));
	}

	/**
	 * Resetuje wygl�d wszystkich przycisk�w kart nadaj�c im wygl�d grzbietu karty.
	 */
	public void resetButtonsIcons() {
		for(int i=0;i<16;i++)
			cardButtons[i].setIcon(new ImageIcon(coverIcon));
	}

	/**
	 * Aktualizuje wy�wietlany aktualny wynik gry.
	 * @param score Nowa warto�c wyniku.
	 */
	public void setScoreLabel(int score) {
		scoreLabel.setText("Score: " + score);
	}

	/**
	 * Wy�wietla otrzyman� wiadomo�� informacyjn� ( w mojej grze otrzymuje tylko tre�� tablicy wynik�w
	 * b�d� wynik aktualnie zako�czonej rozgrywki dlatego nazwa okna jest sta�a "Score").
	 * @param message  Wiadomo�� wy�wietlana w oknie.
	 */
	public void showMessageWindow(String message) {
		JOptionPane.showMessageDialog(window, message, "Score", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
