import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener{
    private final JFrame FRAME;
    private final JTextField ACTION;
    private final JTextField HISTORY;
    private final JButton[] NUMBER_BUTTONS = new JButton[10];
    private final JButton[] FUNCTION_BUTTONS = new JButton[9];
    private final JButton ADD_BUTTON, SUBTRACT_BUTTON, MULTIPLE_BUTTON, DIVIDE_BUTTON;
    private final JButton DECIMAL_BUTTON, EQUAL_BUTTON, NEGATIVE_BUTTON, DELETE_BUTTON, CLEAR_BUTTON;
    private final JPanel PANEL;
    private boolean equalsActed = false;

    private final static Color BACKGROUND_COLOR = Color.decode("#fc90c6");
    private final static Color BUTTON_BACKGROUND_COLOR = Color.decode("#f5c9df");
    private final static Color BUTTON_COLOR = Color.decode("#e6127d");
    private final static Color ACTION_BACKGROUND_COLOR = Color.decode("#c486a5");
    private final static Color ACTION_TEXT_COLOR = Color.decode("#73073d");
    private final static Color HISTORY_TEXT_COLOR = Color.decode("#cf277b");
    private final static Color PANEL_BACKGROUND_COLOR = Color.decode("#db86b1");
    private final static Font font = new Font("Impact", Font.PLAIN, 20);
    private final static Font smallFont = new Font("Impact", Font.PLAIN, 15);
    private double num1 = 0;
    private double num2 = 0;
    private double result;
    private char operator;

    Calculator() {
        FRAME = new JFrame("Calculator");
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.setSize(315, 455);
        FRAME.setLayout(null);
        FRAME.setResizable(false);
        FRAME.getContentPane().setBackground(BACKGROUND_COLOR);

        ACTION = new JTextField();
        ACTION.setBounds(25, 25, 250, 40);
        ACTION.setFont(font);
        ACTION.setForeground(ACTION_TEXT_COLOR);
        ACTION.setBackground(ACTION_BACKGROUND_COLOR);
        ACTION.setCaretColor(ACTION_BACKGROUND_COLOR);
        ACTION.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        ACTION.setEditable(false);

        HISTORY = new JTextField();
        HISTORY.setBounds(25, 5, 250, 15);
        HISTORY.setFont(smallFont);
        HISTORY.setBackground(BACKGROUND_COLOR);
        HISTORY.setForeground(HISTORY_TEXT_COLOR);
        HISTORY.setCaretColor(BACKGROUND_COLOR);
        HISTORY.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        HISTORY.setEditable(false);

        ADD_BUTTON = new JButton("+");
        SUBTRACT_BUTTON = new JButton("-");
        MULTIPLE_BUTTON = new JButton("*");
        DIVIDE_BUTTON = new JButton("/");
        DECIMAL_BUTTON = new JButton(".");
        EQUAL_BUTTON = new JButton("=");
        NEGATIVE_BUTTON = new JButton("(-)");
        DELETE_BUTTON = new JButton("DEL");
        CLEAR_BUTTON = new JButton("CLR");
        FUNCTION_BUTTONS[0] = ADD_BUTTON;
        FUNCTION_BUTTONS[1] = SUBTRACT_BUTTON;
        FUNCTION_BUTTONS[2] = MULTIPLE_BUTTON;
        FUNCTION_BUTTONS[3] = DIVIDE_BUTTON;
        FUNCTION_BUTTONS[4] = DECIMAL_BUTTON;
        FUNCTION_BUTTONS[5] = EQUAL_BUTTON;
        FUNCTION_BUTTONS[6] = NEGATIVE_BUTTON;
        FUNCTION_BUTTONS[7] = DELETE_BUTTON;
        FUNCTION_BUTTONS[8] = CLEAR_BUTTON;

        for (JButton functionButton : FUNCTION_BUTTONS) {
            functionButton.addActionListener(this);
            functionButton.setFont(font);
            functionButton.setFocusable(false);
            functionButton.setBackground(BUTTON_BACKGROUND_COLOR);
            functionButton.setForeground(BUTTON_COLOR);
        }

        for (int i = 0; i < NUMBER_BUTTONS.length; i++) {
            NUMBER_BUTTONS[i] = new JButton(String.valueOf(i));
            NUMBER_BUTTONS[i].addActionListener(this);
            NUMBER_BUTTONS[i].setFont(font);
            NUMBER_BUTTONS[i].setFocusable(false);
            NUMBER_BUTTONS[i].setBackground(BUTTON_BACKGROUND_COLOR);
            NUMBER_BUTTONS[i].setForeground(BUTTON_COLOR);
        }

        NEGATIVE_BUTTON.setBounds(25, 350, 55, 40);
        DELETE_BUTTON.setBounds(90, 350, 90, 40);
        CLEAR_BUTTON.setBounds(185, 350, 90, 40);

        PANEL = new JPanel();
        PANEL.setBounds(25, 90, 250, 250);
        PANEL.setLayout(new GridLayout(4, 4, 10, 10));
        PANEL.setBackground(PANEL_BACKGROUND_COLOR);

        PANEL.add(NUMBER_BUTTONS[1]);
        PANEL.add(NUMBER_BUTTONS[2]);
        PANEL.add(NUMBER_BUTTONS[3]);
        PANEL.add(ADD_BUTTON);
        PANEL.add(NUMBER_BUTTONS[4]);
        PANEL.add(NUMBER_BUTTONS[5]);
        PANEL.add(NUMBER_BUTTONS[6]);
        PANEL.add(SUBTRACT_BUTTON);
        PANEL.add(NUMBER_BUTTONS[7]);
        PANEL.add(NUMBER_BUTTONS[8]);
        PANEL.add(NUMBER_BUTTONS[9]);
        PANEL.add(MULTIPLE_BUTTON);
        PANEL.add(DECIMAL_BUTTON);
        PANEL.add(NUMBER_BUTTONS[0]);
        PANEL.add(EQUAL_BUTTON);
        PANEL.add(DIVIDE_BUTTON);

        FRAME.add(HISTORY);
        FRAME.add(ACTION);
        FRAME.add(PANEL);
        FRAME.add(NEGATIVE_BUTTON);
        FRAME.add(DELETE_BUTTON);
        FRAME.add(CLEAR_BUTTON);
        FRAME.setVisible(true);
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
        for (JButton button : NUMBER_BUTTONS) {
            if (e.getSource() == button) {
                ACTION.setText(ACTION.getText().concat(button.getText()));
            }
        }

        if (e.getSource() == DECIMAL_BUTTON) {
            ACTION.setText(ACTION.getText().concat(DECIMAL_BUTTON.getText()));
        }

        if (e.getSource() == NEGATIVE_BUTTON) {
            double temp = Double.parseDouble(ACTION.getText());
            temp *= -1;
            ACTION.setText(String.valueOf(temp));
        }

        if (e.getSource() == ADD_BUTTON) {
            if(HISTORY.getText().isEmpty()) {
                num1 = Double.parseDouble(ACTION.getText());
                HISTORY.setText(ACTION.getText());
            } else if(equalsActed) {
                equalsActed = false;
                HISTORY.setText(String.valueOf(num1));
            } else {
                num1 = count(operator, num1, Double.parseDouble(ACTION.getText()));
                HISTORY.setText(HISTORY.getText().concat(ACTION.getText()));
            }
            HISTORY.setText(HISTORY.getText().concat(ADD_BUTTON.getText()));
            ACTION.setText("");
            operator = '+';
        }

        if (e.getSource() == SUBTRACT_BUTTON) {
            if(HISTORY.getText().isEmpty()) {
                num1 = Double.parseDouble(ACTION.getText());
                HISTORY.setText(ACTION.getText());
            } else if(equalsActed) {
                equalsActed = false;
                HISTORY.setText(String.valueOf(num1));
            } else {
                num1 = count(operator, num1, Double.parseDouble(ACTION.getText()));
                HISTORY.setText(HISTORY.getText().concat(ACTION.getText()));
            }
            HISTORY.setText(HISTORY.getText().concat(SUBTRACT_BUTTON.getText()));
            ACTION.setText("");
            operator = '-';
        }

        if (e.getSource() == MULTIPLE_BUTTON) {
            if(HISTORY.getText().isEmpty()) {
                num1 = Double.parseDouble(ACTION.getText());
                HISTORY.setText(ACTION.getText());
            } else if(equalsActed) {
                equalsActed = false;
                HISTORY.setText(String.valueOf(num1));
            } else {
                num1 = count(operator, num1, Double.parseDouble(ACTION.getText()));
                HISTORY.setText(HISTORY.getText().concat(ACTION.getText()));
            }
            HISTORY.setText(HISTORY.getText().concat(MULTIPLE_BUTTON.getText()));
            ACTION.setText("");
            operator = '*';

        }

        if (e.getSource() == DIVIDE_BUTTON) {
            if(HISTORY.getText().isEmpty()) {
                num1 = Double.parseDouble(ACTION.getText());
                HISTORY.setText(ACTION.getText());
            } else if(equalsActed) {
                equalsActed = false;
                HISTORY.setText(String.valueOf(num1));
            } else {
                num1 = count(operator, num1, Double.parseDouble(ACTION.getText()));
                HISTORY.setText(HISTORY.getText().concat(ACTION.getText()));
            }
            HISTORY.setText(HISTORY.getText().concat(DIVIDE_BUTTON.getText()));
            ACTION.setText("");
            operator = '/';
        }

        if(e.getSource() == EQUAL_BUTTON) {
            equalsActed = true;
            num2 = Double.parseDouble(ACTION.getText());
            HISTORY.setText(HISTORY.getText().concat(ACTION.getText()).concat(EQUAL_BUTTON.getText()));

            result = count(operator, num1, num2);
            ACTION.setText(String.valueOf(result));
            num1 = result;
            num2 = 0;
        }

        if (e.getSource() == CLEAR_BUTTON) {
            ACTION.setText("");
            HISTORY.setText("");
            num1 = 0;
            num2 = 0;
        }

        if (e.getSource() == DELETE_BUTTON) {
            String value = ACTION.getText();
            ACTION.setText(value.substring(0, value.length() - 1));
        }
    }
}