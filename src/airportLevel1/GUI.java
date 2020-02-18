/**
 * Creation of a GUI to allow user input of several variables and shows results of simulation
 * 
 * @author Josh Merritt
 * @version 2014.4.29
 */
package airportLevel1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class GUI {

	/**
	 * Primary frame for GUI
	 */
	private JFrame mainFrame;
	
	/**
	 * TextArea that shows results at end of simulation
	 * 
	 * @see #printResults()
	 * @see #printSuggestions()
	 */
	private JTextArea result;
	
	/**
	 * TextField for input of probability 'p'
	 */
	private JTextField pField;
	
	/**
	 * TextField for input of 'runtime'
	 */
	private JTextField runField;
	
	/**
	 * Label for pField
	 */
	private JLabel pFieldLabel;
	
	/**
	 * Label for runField
	 */
	private JLabel runFieldLabel;

	/**
	 * Currently selected queueing system
	 */
	private int qType;
	
	/**
	 * JRadioButton for selecting the level one queueing system
	 */
	private JRadioButton l1QButton;
	
	/**
	 * JRadioButton for selecting the level two queueing system
	 */
	private JRadioButton l2QButton;
	
	/**
	 * Input probability of Commercial Aircraft created each step
	 */
	private double p;
	
	/**
	 * Input runtime specified by user
	 */
	private int runtime;
	
	/**
	 * Complete simulator used to run simulation
	 */
	private Simulator simulator;

	
	/**
	 * Main method to be called from command line that creates the GUI. 
	 * 
	 *  @param <code>String</code>. Not used for calling the method
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		GUI gooey = new GUI();
	}
	
	/**
	 * Creates the GUI ready to run the simulation
	 */
	public GUI() {

		String l1QText = "Level 1 Queueing System";
		String l2QText = "Level 2 Queueing System";
		final int blankSpace = 6;
		qType = 0;

		//1 create components
		JButton startButton = new JButton();
		JButton exitButton = new JButton();
		l1QButton = new JRadioButton(l1QText);
		l2QButton = new JRadioButton(l2QText);	
		pField = new JTextField("0.1");
		runField= new JTextField("2880");
		pFieldLabel = new JLabel();
		runFieldLabel = new JLabel();

		result = new JTextArea();
		JLabel resLab = new JLabel();
		JScrollPane resultScroller = new JScrollPane(result);
		resultScroller.setPreferredSize(new Dimension(300, 300));
		resultScroller.setMinimumSize(new Dimension(200,200));

		//2 properties
		result.setEditable(false);
		resLab.setText("Simulation results:");
		resLab.setToolTipText("After the simulation is completed, statistics will be shown below");
		startButton.setText("Simulate");
		startButton.setToolTipText("Click to begin simulation");
		exitButton.setText("Quit");
		exitButton.setToolTipText("Quit application");
		l1QButton.setToolTipText("Air queue ordered by fuel time left");
		l2QButton.setToolTipText("Queues ordered by first come, first served");
		pFieldLabel.setText("Enter Probability:");
		pFieldLabel.setToolTipText("Enter the probability that a Commercial Aircraft is created for each step");
		pField.setPreferredSize(new Dimension(25, 20));
		pField.setToolTipText("Value entered must be a double lower than 1.0");
		runFieldLabel.setText("Enter Runtime:");
		runFieldLabel.setToolTipText("Set the length of time (in 30s ticks) that you wish the simulation to run for");
		runField.setPreferredSize(new Dimension(25, 20));
		runField.setToolTipText("Value entered must be an integer higher than 1");

		//3 create containers
		ButtonGroup qButtons = new ButtonGroup();
		qButtons.add(l1QButton);
		qButtons.add(l2QButton);

		mainFrame = new JFrame("Airport Simulation");
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		JPanel options = new JPanel();
		JPanel buttons = new JPanel();

		JPanel left = new JPanel();
		JPanel right = new JPanel();

		JPanel stick = new JPanel();

		//4 specify layout managers
		mainFrame.setLayout(new FlowLayout());

		stick.setLayout(new BorderLayout());
		stick.setBorder(new EmptyBorder(blankSpace, blankSpace, blankSpace, blankSpace));

		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		resLab.setAlignmentX(Component.LEFT_ALIGNMENT);
		resultScroller.setAlignmentX(Component.LEFT_ALIGNMENT);

		left.setLayout(new BorderLayout());
		left.setBorder(new EmptyBorder(blankSpace, blankSpace, blankSpace, blankSpace));

		buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
		l1QButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		l2QButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		// The following labels refuse to be aligned left :(
		//	pFieldLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		//	runFieldLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

		//5 add stuff to containers		
		options.add(l1QButton);
		options.add(l2QButton);
		options.add(pFieldLabel);
		options.add(pField);
		options.add(runFieldLabel);
		options.add(runField);
		buttons.add(startButton);
		buttons.add(exitButton);

		left.add(options, BorderLayout.NORTH);
		left.add(buttons, BorderLayout.SOUTH);

		right.add(resLab);
		right.add(resultScroller);

		stick.add(left, BorderLayout.WEST);
		stick.add(right, BorderLayout.EAST);

		mainFrame.add(stick);

		//6 add listeners and arrange event handlers
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitApp();
			}
		});

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitApp();
			}
		});

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startSim(); //write this method
			}
		});

		l1QButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setQType(1);
			}
		});

		l2QButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setQType(2);
			}
		});


		//7 display
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	/**
	 * Closes GUI, giving a confirmation dialog box before doing so
	 */
	private void exitApp() {
		int response = JOptionPane.showConfirmDialog(mainFrame, 
				"Do you really want to quit?",
				"Quit?",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		else {
			// Don't quit
		}
	}

	/**
	 * Begins simulation after checking for parsing errors in pField and runField, and confirming a queueing system has been selected. Highlights errors to user
	 * 
	 * @return <code>boolean</code> used to easily exit method when error found
	 * @exception <code>Exception</code> thrown when parsing error discovered
	 */
	private boolean startSim() {
		pFieldLabel.setForeground(Color.BLACK);
		runFieldLabel.setForeground(Color.BLACK);
		l1QButton.setForeground(Color.BLACK);
		l2QButton.setForeground(Color.BLACK);
		boolean pParseError = false;
		boolean runParseError = false;
		boolean qError = false;
		try {
			p = Double.parseDouble(pField.getText());
			if (p > 1.0) {
				throw new NumberFormatException();
			}
		} 
		catch(NumberFormatException e){
			showPFieldError();
			pParseError = true;
		}
		try {
			runtime = Integer.parseInt(runField.getText());
			if (runtime < 1) {
				throw new NumberFormatException();
			}
		}
		catch(NumberFormatException e){
			showRunFieldError();
			runParseError = true;
		}
		try {
			if (qType == 0) {
				throw new NumberFormatException();
			}
		}
		catch(NumberFormatException e){
			showQError();
			qError = true;
		}
		if ((pParseError == false) && (runParseError == false) && (qError == false)){
			simulator = new Simulator(p, runtime, qType);
			simulator.simulate();
			printResults();
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Sets queueing system
	 */
	private void setQType(int q) {
		if (q == 1) {
			qType = 1;
		}
		else if (q == 2) {
			qType = 2;
		}
		else {

		}
	}

	/**
	 * Prints results of simulation to {@link #result}
	 */
	public void printResults() {
		result.setText(null);
		result.append("~~~~~ End of Simulation Report: ~~~~~ \n");
		result.append("\n");
		result.append("Number of Crashes: " + simulator.getCrashed() + "\n");
		result.append("Number of Aircraft Sent to Hangar: " + Simulator.getHangarNum() + "\n");
		result.append("Number of Take Offs: " + Simulator.getTakeOffs() + "\n");
		result.append("Number of Landings: " + Simulator.getLandings() + "\n");
		result.append ("Average Waiting Time: " + Simulator.getAverageTotalTicks() + " ticks");
		result.append("\n");
		result.append(printSuggestions());
	}

	/**
	 * Alerts user of error in {@link #pField}
	 */
	public void showPFieldError(){
		pFieldLabel.setForeground(Color.RED);
		JOptionPane.showMessageDialog(mainFrame, "An error was found in the Probability field, please consult its tooltip and change your input.");
	}

	/**
	 * Alerts user of parsing error in {@link #runField}
	 */
	public void showRunFieldError(){
		runFieldLabel.setForeground(Color.RED);
		JOptionPane.showMessageDialog(mainFrame, "An error was found in the Runtime field, please consult its tooltip and change your input.");
	}
	
	/**
	 * Alerts user to say they have no selected a queueing system ({@link #qType})
	 */
	public void showQError(){
		l1QButton.setForeground(Color.RED);
		l2QButton.setForeground(Color.RED);
		JOptionPane.showMessageDialog(mainFrame, "Please select a queueing system before running the simulation.");
		}

	/**
	 * Prints suggestions for future simulations to {@link #result}
	 */
	public String printSuggestions(){
		String suggest = "";
		if (simulator.getCrashed() != 0){
			suggest = "Crashes occurred, consider lowering the value of 'p' \n to prevent crashes. \n";
		}
		else if (simulator.getCrashed() == 0){
			suggest = "No crashes occurred, consider raising the value of 'p'\n for increased efficiency. \n";
		}
		if (suggest.isEmpty() == false){
			suggest = "\n~~~~~ Suggestions: ~~~~~ \n" +"\n" + suggest;
		}
		return suggest;
	}
}