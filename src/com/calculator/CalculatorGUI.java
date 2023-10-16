package com.calculator; // Package declaration

import java.awt.Color; // Importing Color class from the java.awt package
import java.awt.EventQueue; // Importing EventQueue class from the java.awt package
import java.awt.Font; // Importing Font class from the java.awt package
import java.awt.event.ActionEvent; // Importing ActionEvent class from the java.awt package
import java.awt.event.ActionListener; // Importing ActionListener class from the java.awt package
import java.awt.event.MouseAdapter; // Importing MouseAdapter class from the java.awt package
import java.awt.event.MouseEvent; // Importing MouseEvent class from the java.awt package
import java.awt.event.KeyAdapter; // Importing KeyAdapter class from the java.awt.event package
import java.awt.event.KeyEvent; // Importing KeyEvent class from the java.awt.event package

import javax.swing.BorderFactory; // Importing the BorderFactory class from the javax.swing package
import javax.swing.JButton; // Importing JButton class from the javax.swing package
import javax.swing.JFrame; // Importing JFrame class from the javax.swing package
import javax.swing.JTextField; // Importing JTextField class from the javax.swing package
import javax.swing.SwingConstants; // Importing the SwingConstants class from the javax.swing package

public class CalculatorGUI {

    private JFrame frame; // Declaration of a private JFrame variable named frame
    private JTextField textField; // Declaration of a private JTextField variable named textField
    private String inputBuffer = ""; // Declaration and initialization of a private String variable named inputBuffer

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() { // Main method, runs the GUI in the event dispatch thread
            public void run() {
                try {
                    CalculatorGUI window = new CalculatorGUI(); // Create an instance of CalculatorGUI
                    window.frame.setVisible(true); // Make the GUI frame visible
                } catch (Exception e) {
                    e.printStackTrace(); // Print any exceptions that occur
                }
            }
        });
    }

    public CalculatorGUI() {
        initialize(); // Constructor method, initializes the GUI
    }

    private void initialize() {
        frame = new JFrame(); // Create a new JFrame
        frame.getContentPane().setBackground(new Color(36, 77, 64)); // Set the background color
        frame.setResizable(false); // Make the frame not resizable
        frame.setBounds(100, 100, 346, 541); // Set the frame's position and size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation
        frame.getContentPane().setLayout(null); // Set the layout to null (custom layout)
        frame.setTitle("Basic Calculator"); // Set the title of the frame

        textField = new JTextField(); // Create a new JTextField
        textField.setForeground(Color.WHITE); // Set text color to white
        textField.setHorizontalAlignment(SwingConstants.RIGHT); // Set text alignment to the right
        textField.setFont(new Font("Consolas", Font.PLAIN, 40)); // Set the font
        textField.setBounds(10, 10, 313, 80); // Set the position and size
        frame.getContentPane().add(textField); // Add the text field to the frame
        textField.setColumns(10); // Set the number of columns
        textField.setOpaque(false); // Make the text field transparent
        frame.getContentPane().add(textField); // Add the text field again (duplicated code)
        textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4, true)); // Set the border style

        createButton("7", 10, 100, 70, 70, Color.YELLOW); // Create and configure a button
        createButton("8", 90, 100, 70, 70, Color.YELLOW);
        createButton("9", 170, 100, 70, 70, Color.YELLOW);
        createButton("4", 10, 180, 70, 70, Color.YELLOW);
        createButton("5", 90, 180, 70, 70, Color.YELLOW);
        createButton("6", 170, 180, 70, 70, Color.YELLOW);
        createButton("1", 10, 260, 70, 70, Color.YELLOW);
        createButton("2", 90, 260, 70, 70, Color.YELLOW);
        createButton("3", 170, 260, 70, 70, Color.YELLOW);
        createButton("0", 90, 340, 70, 70, Color.YELLOW);
        createButton(".", 10, 340, 70, 70, Color.BLUE);

        createButton("+", 250, 100, 70, 70, Color.MAGENTA);
        createButton("-", 250, 180, 70, 70, Color.MAGENTA);
        createButton("*", 250, 260, 70, 70, Color.MAGENTA);
        createButton("/", 250, 340, 70, 70, Color.MAGENTA);

        createButton("=", 170, 420, 150, 70, Color.GREEN);
        createButton("Clear", 10, 420, 150, 70, Color.RED);
        createButton("<", 170, 340, 70, 70, Color.BLUE);

        textField.addKeyListener(new KeyAdapter() { // Add a key listener to the text field
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    calculateResult(); // Calculate the result when the Enter key is pressed
                }
            }
        });
    }

    private JButton createButton(String label, int x, int y, int width, int height, Color color) {
        JButton button = new JButton(label); // Create a new button with the given label
        button.setFont(new Font("Tahoma", Font.BOLD, 30)); // Set the font of the button
        button.setBounds(x, y, width, height); // Set the position and size of the button
        button.setForeground(Color.WHITE); // Set the text color

        frame.getContentPane().add(button); // Add the button to the frame

        button.setOpaque(true); // Make the button opaque
        button.setContentAreaFilled(false); // Set the content area to be transparent
        button.setBorderPainted(true); // Paint the border
        button.setBorder(BorderFactory.createLineBorder(null, 4, true)); // Set the border style

        button.addMouseListener(new MouseAdapter() { // Add a mouse listener to the button
            @Override
            public void mousePressed(MouseEvent e) {
                button.setBorder(BorderFactory.createLineBorder(color, 4, true)); // Change border color when pressed
                button.setForeground(color); // Change text color
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBorder(BorderFactory.createLineBorder(null, 4, true)); // Restore default colors after releasing
                button.setForeground(Color.WHITE);
            }
        });

        button.addActionListener(new ActionListener() { // Add an action listener to the button
            public void actionPerformed(ActionEvent e) {
                handleButtonClick(label); // Handle button click based on the label
            }
        });
        return button; // Return the configured button
    }

    private void handleButtonClick(String label) {
        switch (label) { // Handle button clicks
            case "=":
                calculateResult(); // Calculate the result
                break;
            case "Clear":
                clearInput(); // Clear the input
                break;
            case "<":
                backspace(); // Perform backspace
                break;
            default:
                addToInput(label); // Add the button label to the input
                break;
        }
    }

    private void addToInput(String value) {
        inputBuffer += value; // Append the value to the input buffer
        textField.setText(inputBuffer); // Update the text field
    }

    private void clearInput() {
        inputBuffer = ""; // Clear the input buffer
        textField.setText(""); // Clear the text field
    }

    private void backspace() {
        if (!inputBuffer.isEmpty()) {
            inputBuffer = inputBuffer.substring(0, inputBuffer.length() - 1); // Remove the last character
            textField.setText(inputBuffer); // Update the text field
        }
    }

    private void calculateResult() {
        try {
            String expression = textField.getText(); // Get the expression from the text field
            if (!expression.isEmpty()) {
                String result = evaluateExpression(expression); // Evaluate the expression
                textField.setText(result); // Display the result
            }
        } catch (ArithmeticException ex) {
            if (textField.getText().isEmpty()) {
                textField.setText(""); // Clear the text field if it's empty
            } else {
                textField.setFont(new Font("Consolas", Font.PLAIN, 25)); // Change font size
                textField.setText("Can't divide by zero"); // Display an error message
            }
        }
    }

    private String evaluateExpression(String expression) {
        try {
            double result = evaluate(expression); // Evaluate the expression
            return Double.toString(result); // Convert the result to a string
        } catch (ArithmeticException ex) {
            if (expression.isEmpty()) {
                return ""; // Return an empty string if the expression is empty
            }
            throw new ArithmeticException("Can't divide by zero"); // Throw an exception for division by zero
        } catch (Exception ex) {
            throw new ArithmeticException("Invalid expression"); // Throw an exception for an invalid expression
        }
    }

    private double evaluate(String expression) {
        return new Object() { // Create an anonymous inner class for parsing and evaluating the expression
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ')
                    nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length())
                    throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+'))
                        x += parseTerm();
                    else if (eat('-'))
                        x -= parseTerm();
                    else
                        return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*'))
                        x *= parseFactor();
                    else if (eat('/')) {
                        double divisor = parseFactor();
                        if (divisor == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        x /= divisor;
                    } else
                        return x;
                }
            }

            double parseFactor() {
                if (eat('+'))
                    return parseFactor();
                if (eat('-'))
                    return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.')
                        nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                return x;
            }
        }.parse();
    }
}
