
/*
    Created by:
        Michael Norman
        Student number C00192191
 */
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.*;

public class Calculator extends javax.swing.JFrame implements ActionListener {

    //this sets up the amount of decimal places following the decimal point
    private NumberFormat nf = new DecimalFormat("##.###");

    //setting up variables
    private char operands ;
    private int buttonCount=5;
    private double num1;
    private double num2;
    private double answer;
    private boolean newCycle = false;
    private JButton ButAdd;
    private JButton ButClear;
    private JButton ButDivide;
    private JButton ButEquals;
    private JButton ButMultiply;
    private JButton ButPeriod;
    private JButton ButSubtract;
    private JButton ButMemAdd;
    private JButton ButMemSub;
    private JButton ButMemClear;
    private JButton ButMemRecall;

    private JPanel ButtonsPanel;
    private JPanel TextFieldPanel;

    private JTextField Input;
    private JTextField PrevCalc;

    private JButton[] array = new JButton[20];
    private ArrayList<String> memory = new ArrayList<>();

    private Calculator() {
        //constructor calls initComponents method to initialize components
        initComponents();
    }

    private void initComponents() {

        ButtonsPanel = new JPanel();
        TextFieldPanel = new JPanel();

        ButAdd = new JButton();
        ButClear = new JButton();
        ButDivide = new JButton();
        ButEquals = new JButton();
        ButPeriod = new JButton();
        ButMultiply = new JButton();
        ButSubtract = new JButton();
        ButMemAdd = new JButton();
        ButMemSub = new JButton();
        ButMemClear = new JButton();
        ButMemRecall = new JButton();

        Input = new JTextField();
        PrevCalc = new JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setMinimumSize(new java.awt.Dimension(300, 455));

        ButtonsPanel.setLayout(new java.awt.GridLayout(5, 4, 2, 2));

        for (int i =0;i<=9;i++)
        {
            array[i] = new JButton(String.valueOf(i));
            array[i].addActionListener(this);
            array[i].setFont(new java.awt.Font("Times New Roman", 0, 24));
        }

        //<editor-fold "Setting up buttons that are not numbers">
        ButMultiply.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        ButMultiply.setText("*");
        ButMultiply.addActionListener(this);

        ButDivide.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        ButDivide.setText("/");
        ButDivide.addActionListener(this);

        ButAdd.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        ButAdd.setText("+");
        ButAdd.addActionListener(this);

        ButSubtract.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        ButSubtract.setText("-");
        ButSubtract.addActionListener(this);

        ButEquals.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        ButEquals.setText("=");
        ButEquals.addActionListener(this);

        ButPeriod.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        ButPeriod.setText(".");
        ButPeriod.addActionListener(this);

        ButMemAdd.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ButMemAdd.setText("M+");
        ButMemAdd.addActionListener(this);

        ButMemSub.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ButMemSub.setText("M-");
        ButMemSub.addActionListener(this);

        ButMemClear.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ButMemClear.setText("MC");
        ButMemClear.addActionListener(this);

        ButMemRecall.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ButMemRecall.setText("MR");
        ButMemRecall.addActionListener(this);
        //</editor-fold>


        //array numbers reflect actual numbers on screen I.E array[7] = number 7 button
        ButtonsPanel.add(ButMemAdd);
        ButtonsPanel.add(ButMemSub);
        ButtonsPanel.add(ButMemClear);
        ButtonsPanel.add(ButMemRecall);
        ButtonsPanel.add(array[7]);
        ButtonsPanel.add(array[8]);
        ButtonsPanel.add(array[9]);
        ButtonsPanel.add(ButMultiply);
        ButtonsPanel.add(array[4]);
        ButtonsPanel.add(array[5]);
        ButtonsPanel.add(array[6]);
        ButtonsPanel.add(ButDivide);
        ButtonsPanel.add(array[1]);
        ButtonsPanel.add(array[2]);
        ButtonsPanel.add(array[3]);
        ButtonsPanel.add(ButAdd);
        ButtonsPanel.add(ButEquals);
        ButtonsPanel.add(array[0]);
        ButtonsPanel.add(ButPeriod);
        ButtonsPanel.add(ButSubtract);

        getContentPane().add(ButtonsPanel, java.awt.BorderLayout.CENTER);

        // SETTING UP TEXT AREAS WITH CLEAR BUTTON
        TextFieldPanel.setLayout(new java.awt.BorderLayout());

        Input.setColumns(2);
        Input.setFont(new java.awt.Font("Times New Roman", 0, 48));
        Input.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Input.setBorder(null);
        Input.setMinimumSize(new java.awt.Dimension(200, 470));
        TextFieldPanel.add(Input, java.awt.BorderLayout.CENTER);

        ButClear.setText("Clear");
        ButClear.setMaximumSize(new java.awt.Dimension(50, 32));
        ButClear.setMinimumSize(new java.awt.Dimension(20, 20));
        ButClear.addActionListener(this);
        TextFieldPanel.add(ButClear, java.awt.BorderLayout.PAGE_END);

        PrevCalc.setEditable(false);
        PrevCalc.setBackground(Input.getBackground());
        PrevCalc.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        PrevCalc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        PrevCalc.setBorder(null);
        PrevCalc.setMinimumSize(new java.awt.Dimension(0, 50));
        PrevCalc.setPreferredSize(new java.awt.Dimension(0, 56));
        TextFieldPanel.add(PrevCalc, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(TextFieldPanel, java.awt.BorderLayout.PAGE_START);

        ButMemRecall.setEnabled(false);
        ButMemClear.setEnabled(false);
        ButMemSub.setEnabled(false);
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private double calculation(double num1, double num2){
        double result=0;

        //figuring out which calculation to do
        switch(operands)
        {
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
        }
        return result;
    }

    private void addDigit(ActionEvent button) {
        //getting value
        String val = ((JButton)button.getSource()).getText();

        //if its a new cycle clear the main screen and move the answer to the top screen
        if (newCycle){
            Input.setText("");
            PrevCalc.setText("");
            Input.setText(val);
            newCycle = false;
        }//if
        //else add to the main screen
        else
        {
            Input.setText(Input.getText() + val);
        }
    }

    private void memoryAdd() {
        newCycle = true;
        //while the text screen isn't empty
        // and the memory arrayList doesn't contain the desired number
        while (!Input.getText().equals("") && !memory.contains(Input.getText()))
        {
            //setting the max amount of numbers in the arrayList to 6
            //if the array is full remove the last one and push all down by one
            // and add number to the start of the array
            if (memory.size() == 6) {
                memory.add(0, Input.getText());
                memory.remove(5);
            }
            //else just add to the start of the array push others down one
            else {
                memory.add(0, Input.getText());
            }
            //while the array isnt empty enable all other memory buttons
            ButMemRecall.setEnabled(true);
            ButMemClear.setEnabled(true);
            ButMemSub.setEnabled(true);
        }
    }

    private void memRemove(){
        //if the array contains the number
        if (memory.contains(Input.getText()))
        {
            //sets the location of the number for deletion
            int location = memory.indexOf(Input.getText());
            //removes the number at the location from earlier
            memory.remove(location);
        }
        //if the array list is empty set all the buttons to disabled
        if (memory.isEmpty()){
            ButMemClear.setEnabled(false);
            ButMemRecall.setEnabled(false);
            ButMemSub.setEnabled(false);
        }
    }

    private void memoryClear(){
        //clears all items in the array list
        memory.clear();
        //sets the screen to blank
        Input.setText("");
        //sets all the memory buttons to disabled
        ButMemRecall.setEnabled(false);
        ButMemClear.setEnabled(false);
        ButMemSub.setEnabled(false);
    }

    private void memoryRecall()throws IndexOutOfBoundsException {
        //button count set to 5 to match array limit
        //try to set the corresponding button count
        //to index number and print to screen
        //iterate down through the array
        try {
            Input.setText(memory.get(buttonCount));
            buttonCount--;
        }
        //if it goes out of bounds
        catch (IndexOutOfBoundsException e){
            //if the array is empty set the screen to blank
            if (memory.isEmpty())
            {
                Input.setText("");
            }
            //else set the buttoncount back to 5
            //iterate again
            else {
                buttonCount = memory.size() - 1;
                Input.setText(memory.get(buttonCount));
                buttonCount--;
            }
        }
    }

    private void operandProcessing (String operand){
        //checks if the text field isn't empty
        if (Input.getText()!="")
        {
            //if your not continuing a calculation
            if (newCycle){
                PrevCalc.setText(nf.format(answer));
                newCycle = false;
            }//if newCycle

            //setting number 1 to the input for calculation later
            num1 = Double.parseDouble(Input.getText());
            //set operand for future calculation
            operands=operand.charAt(0);
            //moving the number and operand to the top screen
            PrevCalc.setText(Input.getText() + operand);
            //clearing the main text field
            Input.setText("");
        }//if input isnt empty
    }//operandProcessing

    private void addOperands(ActionEvent button) throws NumberFormatException {

        //try setting addOperands
        try {
            //get the operand pressed
            String operand = ((JButton)button.getSource()).getText();

            //decide what operand to send to the operand set method
            switch (operand)
            {
                case "+":
                    operandProcessing(operand);
                    break;

                case "-":
                    operandProcessing(operand);
                    break;

                case "*":
                    operandProcessing(operand);
                    break;

                case "/":
                    operandProcessing(operand);
                    break;

                case "=":
                    //sets number two the input and parses to double
                    num2 = Double.parseDouble(Input.getText());
                    //if its a new cycle
                    if (newCycle) {
                        //set the equation to the top screen and changes new cycle to false
                        PrevCalc.setText(PrevCalc.getText()+ operands + nf.format(num1));
                        newCycle = false;
                    }//if
                    else {
                        //if newcycle is false just top text to the calculation
                        PrevCalc.setText(PrevCalc.getText() + Input.getText());
                    }//else

                    //get answer from calculation method
                    answer = calculation(num1,num2);
                    //set text to answer and format it by numberformat method
                    Input.setText(nf.format(answer));
                    newCycle = true;
                    break;

                case "Clear":
                    //stops newCycle
                    newCycle = false;
                    //clears both textfields
                    Input.setText("");
                    PrevCalc.setText("");
                    num1=0;
                    num2=0;
                    break;

                case  ".":
                    //set check to the text on the screen
                    String check = Input.getText();
                    //tests to see if the string check already contains a decimal point
                    boolean test = check.contains(".");
                    //if test isnt true allow the decimal point to be used
                    if (!test){
                        Input.setText(Input.getText()+".");
                    }//if
                    break;

                case "MR":
                    memoryRecall();
                    break;

                case "M+":
                    memoryAdd();
                    break;

                case "M-":
                    memRemove();
                    break;

                case "MC":
                    memoryClear();
                    break;
            }//switch
        }//try

        // catch the exception where no number has been entered first
        catch (NumberFormatException e) {
            if (PrevCalc.getText().equals("")) {
                PrevCalc.setText("Enter Number First");
                newCycle = true;
            }//if
        }//catch
    }//addOperands method

    public static void main(String args[]) {
        //creat new calculator and make it visable
        java.awt.EventQueue.invokeLater(() -> {
            new Calculator().setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //setting value of button pressed
        String val = ((JButton)e.getSource()).getText();
        //if a number is pressed go to addDigit method
        if (val.matches("[0-9]")) {
            addDigit(e);
        }//if else

        //else go to addOperands method
        else{
            addOperands(e);
        }
    }//actionPerformed

}//Calculator