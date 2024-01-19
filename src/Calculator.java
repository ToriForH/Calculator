import javax.swing.*;
import javax.swing.text.Caret;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener{
    private final JFrame frame;
    private final JTextField action;
    private final JTextField history;
    private final JButton[] numberButtons = new JButton[10];
    private JButton[] functionButtons = new JButton[9];
    private final JButton addButton, subtractButton, multipleButton, divideButton;
    private final JButton decimalButton, equalButton, negativeButton, deleteButton, clearButton;
    private final JPanel panel;
    private boolean equalsActed = false;

    private Font font = new Font("Impact", Font.PLAIN, 20);
    private Font smallFont = new Font("Impact", Font.PLAIN, 15);
    private double num1 = 0;
    private double num2 = 0;
    private double result;
    private char operator;

    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(315, 455);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.decode("#fc90c6"));

        action = new JTextField();
        action.setBounds(25, 25, 250, 40);
        action.setFont(font);
        action.setForeground(Color.decode("#73073d"));
        action.setBackground(Color.decode("#c486a5"));
        action.setCaretColor(Color.decode("#c486a5"));
        action.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        action.setEditable(false);

        history = new JTextField();
        history.setBounds(25, 5, 250, 15);
        history.setFont(smallFont);
        history.setBackground(Color.decode("#fc90c6"));
        history.setForeground(Color.decode("#cf277b"));
        history.setCaretColor(Color.decode("#fc90c6"));
        history.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        history.setEditable(false);

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multipleButton = new JButton("*");
        divideButton = new JButton("/");
        decimalButton = new JButton(".");
        equalButton = new JButton("=");
        negativeButton = new JButton("(-)");
        deleteButton = new JButton("DEL");
        clearButton = new JButton("CLR");
        functionButtons[0] = addButton;
        functionButtons[1] = subtractButton;
        functionButtons[2] = multipleButton;
        functionButtons[3] = divideButton;
        functionButtons[4] = decimalButton;
        functionButtons[5] = equalButton;
        functionButtons[6] = negativeButton;
        functionButtons[7] = deleteButton;
        functionButtons[8] = clearButton;

        for (JButton functionButton : functionButtons) {
            functionButton.addActionListener(this);
            functionButton.setFont(font);
            functionButton.setFocusable(false);
            functionButton.setBackground(Color.decode("#f5c9df"));
            functionButton.setForeground(Color.decode("#e6127d"));
        }

        for (int i = 0; i < numberButtons.length; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(font);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(Color.decode("#f5c9df"));
            numberButtons[i].setForeground(Color.decode("#e6127d"));
        }

        negativeButton.setBounds(25, 350, 55, 40);
        deleteButton.setBounds(90, 350, 90, 40);
        clearButton.setBounds(185, 350, 90, 40);

        panel = new JPanel();
        panel.setBounds(25, 90, 250, 250);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(Color.decode("#db86b1"));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subtractButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(multipleButton);
        panel.add(decimalButton);
        panel.add(numberButtons[0]);
        panel.add(equalButton);
        panel.add(divideButton);

        frame.add(history);
        frame.add(action);
        frame.add(panel);
        frame.add(negativeButton);
        frame.add(deleteButton);
        frame.add(clearButton);
        frame.setVisible(true);
    }

    private double count(char operation, double n1, double n2) {
        return switch (operation) {
            case '+' -> n1 + n2;
            case '-' -> n1 - n2;
            case '*' -> n1 * n2;
            case '/' -> n1 / n2;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton button : numberButtons) {
            if (e.getSource() == button) {
                action.setText(action.getText().concat(button.getText()));
            }
        }

        if (e.getSource() == decimalButton) {
            action.setText(action.getText().concat(decimalButton.getText()));
        }

        if (e.getSource() == negativeButton) {
            double temp = Double.parseDouble(action.getText());
            temp *= -1;
            action.setText(String.valueOf(temp));
        }

        if (e.getSource() == addButton) {
            if(history.getText().isEmpty()) {
                num1 = Double.parseDouble(action.getText());
                history.setText(action.getText());
            } else if(equalsActed) {
                equalsActed = false;
                history.setText(String.valueOf(num1));
            } else {
                num1 = count(operator, num1, Double.parseDouble(action.getText()));
                history.setText(history.getText().concat(action.getText()));
            }
            history.setText(history.getText().concat(addButton.getText()));
            action.setText("");
            operator = '+';
        }

        if (e.getSource() == subtractButton) {
            if(history.getText().isEmpty()) {
                num1 = Double.parseDouble(action.getText());
                history.setText(action.getText());
            } else if(equalsActed) {
                equalsActed = false;
                history.setText(String.valueOf(num1));
            } else {
                num1 = count(operator, num1, Double.parseDouble(action.getText()));
                history.setText(history.getText().concat(action.getText()));
            }
            history.setText(history.getText().concat(subtractButton.getText()));
            action.setText("");
            operator = '-';
        }

        if (e.getSource() == multipleButton) {
            if(history.getText().isEmpty()) {
                num1 = Double.parseDouble(action.getText());
                history.setText(action.getText());
            } else if(equalsActed) {
                equalsActed = false;
                history.setText(String.valueOf(num1));
            } else {
                num1 = count(operator, num1, Double.parseDouble(action.getText()));
                history.setText(history.getText().concat(action.getText()));
            }
            history.setText(history.getText().concat(multipleButton.getText()));
            action.setText("");
            operator = '*';

        }

        if (e.getSource() == divideButton) {
            if(history.getText().isEmpty()) {
                num1 = Double.parseDouble(action.getText());
                history.setText(action.getText());
            } else if(equalsActed) {
                equalsActed = false;
                history.setText(String.valueOf(num1));
            } else {
                num1 = count(operator, num1, Double.parseDouble(action.getText()));
                history.setText(history.getText().concat(action.getText()));
            }
            history.setText(history.getText().concat(divideButton.getText()));
            action.setText("");
            operator = '/';
        }

        if(e.getSource() == equalButton) {
            equalsActed = true;
            num2 = Double.parseDouble(action.getText());
            history.setText(history.getText().concat(action.getText()).concat(equalButton.getText()));

            result = count(operator, num1, num2);
            action.setText(String.valueOf(result));
            num1 = result;
            num2 = 0;
        }

        if (e.getSource() == clearButton) {
            action.setText("");
            history.setText("");
            num1 = 0;
            num2 = 0;
        }

        if (e.getSource() == deleteButton) {
            String value = action.getText();
            action.setText(value.substring(0, value.length() - 1));
        }
    }
}