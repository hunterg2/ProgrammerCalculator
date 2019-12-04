/* Calculator Program based of Windows 10 Programmer Calculator
 * Written by Hunter Girvan for Khan's CS 2336 Spring 2019
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

//TODO
/* 
 * -keyBoard ActionListener
 * -byteMode conversions (rollover numbers!)
 * -bug checking!
 * 
 * */

public class Calc extends JFrame implements ActionListener {

	private JFrame frmProgrammerCalculator;
	private JButton btn2nd, btnA, btnC, btnE, btnMod, btnB, btnD, btnF, btnCE, btn7, btn4, btn1, btnClear, btn8, btn5, btn2, btnDelete, btn9, btn6, btn3, btnDiv, btnX, btnSub, btnAdd, btnOpenP, btnCloseP, btnSwapSign, btn0, btnDecimal, btnEqual;
	private JButton btnHex, btnDec, btnOct, btnBin, btnMode;
	private JLabel lblBin, lblOct, lblDec, lblHex, lblProgrammer, lblInput, lblHistory;
	private JLabel lblBackground1;
	
	private ImageIcon background = new ImageIcon("C:\\Users\\hunte\\eclipse-workspace\\Calculator\\src\\bkgd2.jpg");
	
	private String[] byteMode = {"QWORD", "DWORD", "WORD", "BYTE"};
	private int currentByteMode = 0;
	private String calcMode = "DEC";
	private String lastInput = "";
	private String lastOp = "";
	private String lastRealOp = "";
	private int parthCount = 0;
	
	private boolean refreshInput = false;
	private boolean equaled = false;
	private JLabel lblSelected;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calc window = new Calc();
					window.frmProgrammerCalculator.setVisible(true);
					//window.frmProgrammerCalculator.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Calc() {

		frmProgrammerCalculator = new JFrame();
		frmProgrammerCalculator.getContentPane().setBackground(new Color(173, 216, 230));
		frmProgrammerCalculator.setFont(new Font("Dialog", Font.BOLD, 12));
		frmProgrammerCalculator.setTitle("Hunna's Calculator");
		frmProgrammerCalculator.setBackground(new Color(0, 0, 0));
		frmProgrammerCalculator.setForeground(new Color(204, 255, 255));
		frmProgrammerCalculator.setBounds(100, 100, 450, 600);
		frmProgrammerCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProgrammerCalculator.getContentPane().setLayout(null);
		
		lblSelected = new JLabel("");
		lblSelected.setForeground(new Color(0, 0, 0));
		lblSelected.setBackground(new Color(0, 0, 0));
		lblSelected.setBounds(0, 125, 16, 14);
		frmProgrammerCalculator.getContentPane().add(lblSelected);
		
		//add all the buttons
		btn2nd = new JButton("2nd");
		btn2nd.setBounds(10, 306, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btn2nd);
		btn2nd.setBorder(null);
		btn2nd.setFocusPainted(false);
		
		btnMod = new JButton("Mod");
		btnMod.setBounds(80, 306, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnMod);
		btnMod.setBorder(null);
		btnMod.setFocusPainted(false);
		
		btnCE = new JButton("CE");
		btnCE.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCE.setBounds(150, 306, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnCE);
		btnCE.setBorder(null);
		btnCE.setFocusPainted(false);
		
		btnClear = new JButton("C");
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnClear.setBounds(220, 306, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnClear);
		btnClear.setBorder(null);
		btnClear.setFocusPainted(false);
		
		btnDelete = new JButton("\u232B");
		btnDelete.setBounds(292, 306, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnDelete);
		btnDelete.setBorder(null);
		btnDelete.setFocusPainted(false);
		
		btnA = new JButton("A");
		btnA.setEnabled(false);
		btnA.setBounds(10, 357, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnA);
		btnA.setBorder(null);
		btnA.setFocusPainted(false);
		
		btnB = new JButton("B");
		btnB.setEnabled(false);
		btnB.setBounds(80, 357, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnB);
		btnB.setBorder(null);
		btnB.setFocusPainted(false);
		
		btnC = new JButton("C");
		btnC.setEnabled(false);
		btnC.setBounds(10, 408, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnC);
		btnC.setBorder(null);
		btnC.setFocusPainted(false);
		
		btnD = new JButton("D");
		btnD.setEnabled(false);
		btnD.setBounds(80, 408, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnD);
		btnD.setBorder(null);
		btnD.setFocusPainted(false);
		
		btnE = new JButton("E");
		btnE.setEnabled(false);
		btnE.setBounds(10, 459, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnE);
		btnE.setBorder(null);
		btnE.setFocusPainted(false);
		
		btnF = new JButton("F");
		btnF.setEnabled(false);
		btnF.setBounds(80, 459, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnF);
		btnF.setBorder(null);
		btnF.setFocusPainted(false);
		
		btn7 = new JButton("7");
		btn7.setForeground(new Color(0, 0, 0));
		btn7.setBounds(150, 357, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btn7);
		btn7.setBorder(null);
		btn7.setFocusPainted(false);
		
		btn8 = new JButton("8");
		btn8.setBounds(220, 357, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btn8);
		btn8.setBorder(null);
		btn8.setFocusPainted(false);
		
		btn9 = new JButton("9");
		btn9.setBounds(292, 357, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btn9);
		btn9.setBorder(null);
		btn9.setFocusPainted(false);
		
		btn4 = new JButton("4");
		btn4.setBounds(150, 408, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btn4);
		btn4.setBorder(null);
		btn4.setFocusPainted(false);
		
		btn5 = new JButton("5");
		btn5.setBounds(220, 408, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btn5);
		btn5.setBorder(null);
		btn5.setFocusPainted(false);
		
		btn6 = new JButton("6");
		btn6.setBounds(292, 408, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btn6);
		btn6.setBorder(null);
		btn6.setFocusPainted(false);
		
		btn1 = new JButton("1");
		btn1.setBounds(150, 459, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btn1);
		btn1.setBorder(null);
		btn1.setFocusPainted(false);
		
		btn2 = new JButton("2");
		btn2.setBounds(220, 459, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btn2);
		btn2.setBorder(null);
		btn2.setFocusPainted(false);
		
		btn3 = new JButton("3");
		btn3.setBounds(292, 459, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btn3);
		btn3.setBorder(null);
		btn3.setFocusPainted(false);
		
		btn0 = new JButton("0");
		btn0.setBounds(220, 510, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btn0);
		btn0.setBorder(null);
		btn0.setFocusPainted(false);
		
		btnDiv = new JButton("\u00F7");
		btnDiv.setBounds(364, 306, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnDiv);
		btnDiv.setBorder(null);
		btnDiv.setFocusPainted(false);
		
		btnX = new JButton("X");
		btnX.setBounds(364, 357, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnX);
		btnX.setBorder(null);
		btnX.setFocusPainted(false);
		
		btnSub = new JButton("-");
		btnSub.setBounds(364, 408, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnSub);
		btnSub.setBorder(null);
		btnSub.setFocusPainted(false);
		
		btnAdd = new JButton("+");
		btnAdd.setBounds(364, 459, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnAdd);
		btnAdd.setBorder(null);
		btnAdd.setFocusPainted(false);
		
		btnOpenP = new JButton("(");
		btnOpenP.setBounds(10, 510, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnOpenP);
		btnOpenP.setBorder(null);
		btnOpenP.setFocusPainted(false);
		
		btnCloseP = new JButton(")");
		btnCloseP.setBounds(80, 510, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnCloseP);
		btnCloseP.setBorder(null);
		btnCloseP.setFocusPainted(false);
		
		btnSwapSign = new JButton("+/-");
		btnSwapSign.setBounds(150, 510, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnSwapSign);
		btnSwapSign.setBorder(null);
		btnSwapSign.setFocusPainted(false);
		
		btnDecimal = new JButton(".");
		btnDecimal.setBounds(292, 510, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnDecimal);
		btnDecimal.setBorder(null);
		btnDecimal.setFocusPainted(false);
		
		btnEqual = new JButton("=");
		btnEqual.setBounds(364, 510, 60, 40);
		frmProgrammerCalculator.getContentPane().add(btnEqual);
		btnEqual.setBorder(null);
		btnEqual.setFocusPainted(false);
		
		lblBin = new JLabel("0");
		lblBin.setBounds(75, 215, 189, 20);
		frmProgrammerCalculator.getContentPane().add(lblBin);
		
		lblOct = new JLabel("0");
		lblOct.setBounds(75, 184, 189, 20);
		frmProgrammerCalculator.getContentPane().add(lblOct);
		
		lblDec = new JLabel("0");
		lblDec.setBounds(75, 153, 189, 20);
		frmProgrammerCalculator.getContentPane().add(lblDec);
		
		lblHex = new JLabel("0");
		lblHex.setBounds(75, 122, 189, 20);
		frmProgrammerCalculator.getContentPane().add(lblHex);
		
		lblProgrammer = new JLabel("Programmer Calculator");
		lblProgrammer.setForeground(new Color(72, 61, 139));
		lblProgrammer.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblProgrammer.setBounds(10, 11, 414, 32);
		frmProgrammerCalculator.getContentPane().add(lblProgrammer);
		
		btnHex = new JButton("HEX");
		btnHex.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnHex.setBounds(10, 122, 60, 20);
		frmProgrammerCalculator.getContentPane().add(btnHex);
		btnHex.setBorder(null);
		btnHex.setFocusPainted(false);
		
		btnDec = new JButton("DEC");
		btnDec.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnDec.setBounds(10, 153, 60, 20);
		frmProgrammerCalculator.getContentPane().add(btnDec);
		btnDec.setFocusPainted(false);
		btnDec.setFocusPainted(false);
		
		btnOct = new JButton("OCT");
		btnOct.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnOct.setBounds(10, 184, 60, 20);
		frmProgrammerCalculator.getContentPane().add(btnOct);
		btnOct.setBorder(null);
		btnOct.setFocusPainted(false);
		
		btnBin = new JButton("BIN");
		btnBin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBin.setBounds(10, 215, 60, 20);
		frmProgrammerCalculator.getContentPane().add(btnBin);
		btnBin.setBorder(null);
		btnBin.setFocusPainted(false);
		
		lblInput = new JLabel("0");
		lblInput.setHorizontalAlignment(SwingConstants.TRAILING);
		lblInput.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblInput.setBounds(10, 67, 414, 47);
		frmProgrammerCalculator.getContentPane().add(lblInput);
		
		lblHistory = new JLabel("");
		lblHistory.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHistory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHistory.setBounds(10, 42, 414, 25);
		frmProgrammerCalculator.getContentPane().add(lblHistory);
		
		btnMode = new JButton(byteMode[0]);
		btnMode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMode.setBounds(165, 272, 99, 23);
		frmProgrammerCalculator.getContentPane().add(btnMode);
		btnMode.setBorder(null);
		btnMode.setFocusPainted(false);
		
		lblBackground1 = new JLabel("");
		lblBackground1.setBounds(0, 0, 434, 561);
		frmProgrammerCalculator.getContentPane().add(lblBackground1);
		lblBackground1.setIcon(background);
		
		
		//add all buttons to action listener
		btn7.addActionListener(this);
		btn8.addActionListener(this);
		btn9.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		btn6.addActionListener(this);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn0.addActionListener(this);
		
		btnA.addActionListener(this);
		btnB.addActionListener(this);
		btnC.addActionListener(this);
		btnD.addActionListener(this);
		btnE.addActionListener(this);
		btnF.addActionListener(this);
		
		btn2nd.addActionListener(this);
		btnMod.addActionListener(this);
		btnCE.addActionListener(this);
		btnClear.addActionListener(this);
		btnDelete.addActionListener(this);
		
		btnOpenP.addActionListener(this);
		btnCloseP.addActionListener(this);
		btnSwapSign.addActionListener(this);
		btnDecimal.addActionListener(this);
		
		btnDiv.addActionListener(this);
		btnX.addActionListener(this);
		btnSub.addActionListener(this);
		btnAdd.addActionListener(this);
		btnEqual.addActionListener(this);
		
		btnHex.addActionListener(this);
		btnDec.addActionListener(this);
		btnOct.addActionListener(this);
		btnBin.addActionListener(this);
		
		btnMode.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		try {
			
			//numbers
			if (e.getSource() == btn7) {
				addNumToInput("7");
			}
			else if (e.getSource() == btn8) {
				addNumToInput("8");
			}
			else if (e.getSource() == btn9) {
				addNumToInput("9");
			}
			else if (e.getSource() == btn4) {
				addNumToInput("4");
			}
			else if (e.getSource() == btn5) {
				addNumToInput("5");
			}
			else if (e.getSource() == btn6) {
				addNumToInput("6");
			}
			else if (e.getSource() == btn1) {
				addNumToInput("1");
			}
			else if (e.getSource() == btn2) {
				addNumToInput("2");
			}
			else if (e.getSource() == btn3) {
				addNumToInput("3");
			}
			else if (e.getSource() == btn0) {
				addNumToInput("0");
			}
		
			//letters
			else if (e.getSource() == btnA) {
				addNumToInput("a");
			}
			else if (e.getSource() == btnB) {
				addNumToInput("b");
			}
			else if (e.getSource() == btnC) {
				addNumToInput("c");
			}
			else if (e.getSource() == btnD) {
				addNumToInput("d");
			}
			else if (e.getSource() == btnE) {
				addNumToInput("e");
			}
			else if (e.getSource() == btnF) {
				addNumToInput("f");
			}
		
			//parenthesis 
			else if (e.getSource() == btnOpenP) {
				parthCount++;
				lblHistory.setText(lblHistory.getText()+"(");
				lastOp = "(";
				
			}
			else if (e.getSource() == btnCloseP) {
				
				if (parthCount > 0) {
					parthCount--;
					
					if (lastOp.equals(")"))  //update history with ), and only add input if needed
						lblHistory.setText(lblHistory.getText() + ")"); 
					else
						lblHistory.setText(lblHistory.getText()+lblInput.getText() + ")"); 
					
					if (parthCount == 0)
						lblInput.setText("" + rolloverNum(EvalExpression.runExpression(lblHistory.getText().substring(0,lblHistory.getText().length())))); //eval current exp
					
					refreshInput = true;
					lastOp = ")";
				}
			}
		
			//operators
			else if (e.getSource() == btnDiv) {
				operator("/");
			}
			else if (e.getSource() == btnX) {
				operator("*");
			}
			else if (e.getSource() == btnSub) {
				operator("-");
			}
			else if (e.getSource() == btnAdd) {
				operator("+");
			}
			else if (e.getSource() == btnEqual) {
				operator("=");
			}
			else if (e.getSource() == btnMod) {
				operator("%");
			}
		
			//delete, clear, and CE
			else if (e.getSource() == btnDelete) {
				if (lblInput.getText().length() > 1) {
					lblInput.setText(lblInput.getText().substring(0,lblInput.getText().length()-1));
				}
				else if (lblInput.getText().length() == 1) {
					zero();
				}
			}
			else if (e.getSource() == btnClear) {
				lblHistory.setText(" ");
				lblHex.setText("0");
				lblDec.setText("0");
				lblOct.setText("0");
				lblBin.setText("0");
				zero();
			}
			else if (e.getSource() == btnCE) {
				lblHex.setText("0");
				lblDec.setText("0");
				lblOct.setText("0");
				lblBin.setText("0");
				zero();
			}
			
			else if (e.getSource() == btnSwapSign) {
				lblInput.setText("" + (-1 * Long.parseLong(lblInput.getText())));
			}
			
			//byte mode change....
			else if (e.getSource() == btnMode) {
				changeByteMode();
			}
			
			//mode change
			else if (e.getSource() == btnHex) {
				changeMode("HEX");
			}
			else if (e.getSource() == btnDec) {
				changeMode("DEC");
			}
			else if (e.getSource() == btnOct) {
				changeMode("OCT");
			}
			else if (e.getSource() == btnBin) {
				changeMode("BIN");
			}
			
			
			//background change
			else if (e.getSource() == btn2nd) {
				//changeBackground();
			}
			
		}
		catch (Exception e1) {
			System.out.println("Something bad happened when you clicked that button! : \n" + e1);
		}
	}
	
	//updates the Hex, Dec, Oct, and Bin tabs. only works when sent a Dec Value
	private void updateTabs(long l) {
		lblHex.setText(Long.toHexString(l));
		lblDec.setText(l+"");
		lblOct.setText(Long.toOctalString(l));
		lblBin.setText(Long.toBinaryString(l));
	}
	
	private long cnvtAnyToDec(String num) {          //======================================================
		
		if (calcMode.equals("BIN"))  {        // BIN --> DEC
			num = ""+(Long.parseLong(num, 2));
		}
		else if (calcMode.equals("HEX")) {	  // HEX --> DEC
			num = ""+(Long.parseLong(num, 8));
		}	
		else if (calcMode.equals("OCT")) {	  // OCT --> DEC
			num = ""+(Long.parseLong(num, 16));
		}
		
		return Long.parseLong(num);
	}
	
	private long cnvtDecToAny(String num, String mode) {
		
		if (mode.equals("BIN"))  {        // BIN --> DEC
			num = Long.toBinaryString(Long.parseLong(lblInput.getText()));
		}
		else if (mode.equals("HEX")) {	  // HEX --> DEC
			num = Long.toHexString(Long.parseLong(lblInput.getText()));
		}	
		else if (mode.equals("OCT")) {	  // OCT --> DEC
			num = Long.toOctalString(Long.parseLong(lblInput.getText()));
		}
		
		return Long.parseLong(num);	
	}                                             //=========================================================
	
	//changes calc mode between Hex, Dec, Oct, Bin & converts all values into new number system (also needs to enable/disable certain buttons && change color of the mode buttons) !!!!!!!!!!!!!
	private void changeMode(String mode) {
		
		String[] expression = new String[30];  
		for (int i = 0; i < 30; i++) {         //make a string array and init all to blank
			expression[i] = "";
		}
		int exprCount = 0; //to count how much of the array we fill
		
		for (int i = 0; i < lblHistory.getText().length(); i++) { //loop each char in lblHistory string expr
			
			if (Character.isDigit((lblHistory.getText().charAt(i))) || Character.isAlphabetic(lblHistory.getText().charAt(i))) { //if the char of lblHistory at i is a digit (or a letter for hexacimal)
				expression[exprCount] += Character.toString(lblHistory.getText().charAt(i)); //add that digit to the string
			}
			else {
				exprCount++; //move to the next array element
				expression[exprCount++] = Character.toString(lblHistory.getText().charAt(i)); //and put it here!
			}
		}
		
		lblHistory.setText(""); //reset history text
		
		for (int i = 0; i < exprCount; i++) {   //now loop through the array again and convert all values!
			
			if (expression[i].length() != 0 &&  (Character.isDigit(expression[i].charAt(0)) || Character.isAlphabetic(expression[i].charAt(0)))) { //if this array element is a number or letter, convert it
				
				if (calcMode.equals("DEC") && mode.equals("BIN"))      // DEC --> BIN
					expression[i] = Long.toBinaryString(Long.parseLong(expression[i])); 
				else if (calcMode.equals("DEC") && mode.equals("HEX")) // DEC --> HEX
					expression[i] = Long.toHexString(Long.parseLong(expression[i]));
				else if (calcMode.equals("DEC") && mode.equals("OCT")) // DEC --> OCT
					expression[i] = Long.toOctalString(Long.parseLong(expression[i])); 
				else if (calcMode.equals("HEX") && mode.equals("DEC")) // HEX --> DEC
					expression[i] =""+(Long.parseLong(expression[i], 16)); 
				else if (calcMode.equals("HEX") && mode.equals("OCT")) // HEX --> OCT
					expression[i] = Long.toOctalString(Long.parseLong(expression[i], 16));
				else if (calcMode.equals("HEX") && mode.equals("BIN")) // HEX --> BIN
					expression[i] = Long.toHexString(Long.parseLong(expression[i], 16));
				else if (calcMode.equals("OCT") && mode.equals("HEX")) // OCT --> HEX
					expression[i] = Long.toHexString(Long.parseLong(expression[i], 8));
				else if (calcMode.equals("OCT") && mode.equals("DEC")) // OCT --> DEC
					expression[i] = ""+(Long.parseLong(expression[i], 8));
				else if (calcMode.equals("OCT") && mode.equals("BIN")) // OCT --> BIN
					expression[i] = Long.toBinaryString(Long.parseLong(expression[i], 8));
				else if (calcMode.equals("BIN") && mode.equals("HEX")) // BIN --> HEX
					expression[i] = Long.toHexString(Long.parseLong(expression[i], 2));
				else if (calcMode.equals("BIN") && mode.equals("DEC")) // BIN --> DEC
					expression[i] = ""+(Long.parseLong(expression[i], 2));
				else if (calcMode.equals("BIN") && mode.equals("OCT")) // BIN --> OCT
					expression[i] = Long.toOctalString(Long.parseLong(expression[i], 2));	 
			}
			
			lblHistory.setText(lblHistory.getText()+expression[i]); //add that element to history text
			
		}
		
		//change input text & buttons
		if (calcMode.equals("DEC") && mode.equals("BIN"))  {        // DEC --> BIN
			lblInput.setText(Long.toBinaryString(Long.parseLong(lblInput.getText())));
			
			btnBin.setBorder(btnDec.getBorder());
			btnDec.setBorder(btnHex.getBorder());
			
			btn9.setEnabled(false);
			btn8.setEnabled(false);
			btn7.setEnabled(false);
			btn6.setEnabled(false);
			btn5.setEnabled(false);
			btn4.setEnabled(false);
			btn3.setEnabled(false);
			btn2.setEnabled(false);
		}
		else if (calcMode.equals("DEC") && mode.equals("HEX")) {	// DEC --> HEX
			lblInput.setText(Long.toHexString(Long.parseLong(lblInput.getText())));
			
			btnHex.setBorder(btnDec.getBorder());
			btnDec.setBorder(btnBin.getBorder());
			
			btnA.setEnabled(true);
			btnB.setEnabled(true);
			btnC.setEnabled(true);
			btnD.setEnabled(true);
			btnE.setEnabled(true);
			btnF.setEnabled(true);
		}	
		else if (calcMode.equals("DEC") && mode.equals("OCT")) {	// DEC --> OCT
			lblInput.setText(Long.toOctalString(Long.parseLong(lblInput.getText())));
			
			btnOct.setBorder(btnDec.getBorder());
			btnDec.setBorder(btnBin.getBorder());
			
			btn9.setEnabled(false);
			btn8.setEnabled(false);
		}
		else if (calcMode.equals("HEX") && mode.equals("DEC")) {	 // HEX --> DEC
			lblInput.setText(""+(Long.parseLong(lblInput.getText(), 16)));
			
			btnDec.setBorder(btnHex.getBorder());
			btnHex.setBorder(btnBin.getBorder());
			
			btnA.setEnabled(false);
			btnB.setEnabled(false);
			btnC.setEnabled(false);
			btnD.setEnabled(false);
			btnE.setEnabled(false);
			btnF.setEnabled(false);
		}
		else if (calcMode.equals("HEX") && mode.equals("OCT")) {	 // HEX --> OCT
			lblInput.setText(Long.toOctalString(Long.parseLong(lblInput.getText(), 16)));
			
			btnOct.setBorder(btnHex.getBorder());
			btnHex.setBorder(btnBin.getBorder());
			
			btnA.setEnabled(false);
			btnB.setEnabled(false);
			btnC.setEnabled(false);
			btnD.setEnabled(false);
			btnE.setEnabled(false);
			btnF.setEnabled(false);
			btn9.setEnabled(false);
			btn8.setEnabled(false);
		}
		else if (calcMode.equals("HEX") && mode.equals("BIN")) {	// HEX --> BIN
			lblInput.setText(Long.toBinaryString(Long.parseLong(lblInput.getText(), 16)));
			
			btnBin.setBorder(btnHex.getBorder());
			btnHex.setBorder(btnDec.getBorder());
			
			btnA.setEnabled(false);
			btnB.setEnabled(false);
			btnC.setEnabled(false);
			btnD.setEnabled(false);
			btnE.setEnabled(false);
			btnF.setEnabled(false);
			btn9.setEnabled(false);
			btn8.setEnabled(false);
			btn7.setEnabled(false);
			btn6.setEnabled(false);
			btn5.setEnabled(false);
			btn4.setEnabled(false);
			btn3.setEnabled(false);
			btn2.setEnabled(false);
		}
		else if (calcMode.equals("OCT") && mode.equals("HEX")) {	 // OCT --> HEX
			lblInput.setText(Long.toHexString(Long.parseLong(lblInput.getText(), 8)));
			
			btnHex.setBorder(btnOct.getBorder());
			btnOct.setBorder(btnBin.getBorder());
			
			btnA.setEnabled(true);
			btnB.setEnabled(true);
			btnC.setEnabled(true);
			btnD.setEnabled(true);
			btnE.setEnabled(true);
			btnF.setEnabled(true);
			btn9.setEnabled(true);
			btn8.setEnabled(true);
		}
		else if (calcMode.equals("OCT") && mode.equals("DEC")) {	// OCT --> DEC
			lblInput.setText(""+(Long.parseLong(lblInput.getText(), 8)));
			
			btnDec.setBorder(btnOct.getBorder());
			btnOct.setBorder(btnBin.getBorder());
			
			btn9.setEnabled(true);
			btn8.setEnabled(true);
		}
		else if (calcMode.equals("OCT") && mode.equals("BIN")) {	 // OCT --> BIN
			lblInput.setText(Long.toBinaryString(Long.parseLong(lblInput.getText(), 8)));
			
			btnBin.setBorder(btnOct.getBorder());
			btnOct.setBorder(btnDec.getBorder());
			
			btn7.setEnabled(false);
			btn6.setEnabled(false);
			btn5.setEnabled(false);
			btn4.setEnabled(false);
			btn3.setEnabled(false);
			btn2.setEnabled(false);
		}
		else if (calcMode.equals("BIN") && mode.equals("HEX")) { 	// BIN --> HEX
			lblInput.setText(Long.toHexString(Long.parseLong(lblInput.getText(), 2)));
			
			btnHex.setBorder(btnBin.getBorder());
			btnBin.setBorder(btnDec.getBorder());
			
			btnA.setEnabled(true);
			btnB.setEnabled(true);
			btnC.setEnabled(true);
			btnD.setEnabled(true);
			btnE.setEnabled(true);
			btnF.setEnabled(true);
			btn9.setEnabled(true);
			btn8.setEnabled(true);
			btn7.setEnabled(true);
			btn6.setEnabled(true);
			btn5.setEnabled(true);
			btn4.setEnabled(true);
			btn3.setEnabled(true);
			btn2.setEnabled(true);
		}
		else if (calcMode.equals("BIN") && mode.equals("DEC")) {	 // BIN --> DEC
			lblInput.setText(""+(Long.parseLong(lblInput.getText(), 2)));
			
			btnDec.setBorder(btnBin.getBorder());
			btnBin.setBorder(btnHex.getBorder());
			
			btn9.setEnabled(true);
			btn8.setEnabled(true);
			btn7.setEnabled(true);
			btn6.setEnabled(true);
			btn5.setEnabled(true);
			btn4.setEnabled(true);
			btn3.setEnabled(true);
			btn2.setEnabled(true);
		}
		else if (calcMode.equals("BIN") && mode.equals("OCT")) {	 // BIN --> OCT
			lblInput.setText(Long.toOctalString(Long.parseLong(lblInput.getText(), 2)));
			
			btnOct.setBorder(btnBin.getBorder());
			btnBin.setBorder(btnHex.getBorder());
			
			btn7.setEnabled(true);
			btn6.setEnabled(true);
			btn5.setEnabled(true);
			btn4.setEnabled(true);
			btn3.setEnabled(true);
			btn2.setEnabled(true);
		}

		calcMode = mode;
	}
	
	//changes calc values max byte usage. rolls over numbers when altered to smaller byte values (well not yet but it SHOULD)
	private void changeByteMode() {
		
		if (currentByteMode == 3) 
			currentByteMode = 0;
		else
			currentByteMode++;
		
		btnMode.setText(byteMode[currentByteMode]);
		
		lblInput.setText(""+rolloverNum(Long.parseLong(lblInput.getText()))); //convert our input text
		updateTabs(Long.parseLong(lblInput.getText())); //and update tabs
	}
	
	//keeps us in the bounds of our bytemode
	private long rolloverNum(long num) {
		
		long m1min = -2147483648, m1max = 2147483647, m2min = -32768, m2max = 32767, m3min = -128, m3max = 127;
		
		//if our current input number is outside the bounds of the new byte mode, roll over the numbers...
		if (currentByteMode == 1) { 
			while (!(num >= m1min && num <= m1max)) {
				long diff;
				
				if (num > m1max) {
					diff = num - m1max;
					num = m1min + diff - 1;
				}
				else {
					diff = m1min - num;
					num = m1max - diff + 1;
				}
			}
		}
		else if (currentByteMode == 2) {
			while (!(num >= m2min && num <= m2max)) {
				long diff;
				
				if (num > m2max) {
					diff = num - m2max;
					num = m2min + diff - 1;
				}
				else {
					diff = m2min - num;
					num = m2max - diff + 1;
				}
			}	
		}
		else if (currentByteMode == 3) {
			while (!(num >= m3min && num <= m3max)) {
				long diff;
				
				if (num > m3max) {
					diff = num - m3max;
					num = m3min + diff - 1;
				}
				else {
					diff = m3min - num;
					num = m3max - diff + 1;
				}
			}
		}
		
		
		return num;
	}
	
	//add an operator to the expression
	private void operator(String op) {
		
		String mode = calcMode; //save what mode we were in
		changeMode("DEC"); //convert everything to decimal in order to do calculations
		
		//updates history text
		if (!refreshInput) {
			lblHistory.setText(lblHistory.getText()+removeFront0((lblInput.getText()))+op); //only add to history text if user last hit a number (and is now adding an oper)
		}
		else {
			if (!lastOp.equals(")")) {
				lblHistory.setText(lblHistory.getText().substring(0,lblHistory.getText().length()-1)+op); //change the operator that user chose
			}
			else {
				lblHistory.setText(lblHistory.getText()+op); //unless its a )  (so we can layer them)
			}
		}
		
		if (parthCount == 0) { //as long the op before isnt openP, eval current expression and put into input field
			
			if (op.equals("=") && !(lastOp.equals("=") || lastOp.equals(""))) {
				lastInput = lblInput.getText(); //saves last input
			}
			
			lblInput.setText(("" + rolloverNum(EvalExpression.runExpression(lblHistory.getText().substring(0,lblHistory.getText().length()-1))))); //evaluate current expression excluding last char (since if we just hit an op, exp would not make sense)
			updateTabs(Long.parseLong(lblInput.getText())); //update conversion tabs
		}
		
		if (op.equals("=")) {
			
			if (lastOp.equals("=")) {
				lblInput.setText(""+rolloverNum(EvalExpression.runExpression((lblInput.getText()) + lastRealOp + lastInput))); //repeat last expression with new input
				updateTabs(Long.parseLong(lblInput.getText())); //update conversion tabs
			}
			else {
				lastRealOp = lastOp; //only update the "real" operator if we didnt get double =
			}
			
			lblHistory.setText(""); //clears history
			equaled = true; //user recently chose =
			
		}
		else {
			refreshInput = true; 
		}		
		
		lastOp = op; //save last used operator
		changeMode(mode); //convert everything back into original mode
	}
	
	//add a single digit to the input
	private void addNumToInput(String input) {
		
		if (refreshInput || equaled) {
			lblInput.setText(removeFront0(input)); //if user last hit op, overwrite the input field
			refreshInput = false; //and allow us to now add on to input field numbers
		}
		else {
			lblInput.setText((removeFront0(lblInput.getText()+input)));
		}
		
		equaled = false;
		updateTabs(Long.parseLong(lblInput.getText()));
		
	}
	
	//removes extra beginning 0's
	private static String removeFront0(String input) {
		
		if (input.charAt(0) == '0') {
			input = input.substring(1,input.length());
			removeFront0(input);
		}

		return input;	
	}
	
	//zeros out input field
	private void zero() {
		lblInput.setText("0");
	}

}