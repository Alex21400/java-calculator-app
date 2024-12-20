import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Calculator implements ActionListener {

    JFrame frame;
    JTextField textField;
    JButton[] numButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];

    JButton addButton, subButton, multiplyButton, divideButton;
    JButton decButton, equalButton, negativeButton, deleteButton, clearButton;

    JPanel panel;
    Font textFieldFont = new Font("Montserrat", Font.BOLD, 36);
    Font font = new Font("Montserrat", Font.BOLD, 30);

    double num1 = 0;
    double num2 = 0;
    double result = 0;

    char operator;

    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 580);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 70);
        textField.setFont(textFieldFont);
        textField.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        multiplyButton = new JButton("x");
        divideButton = new JButton("%");
        decButton = new JButton(".");
        equalButton = new JButton("=");
        negativeButton = new JButton("(-)");
        deleteButton = new JButton("<");
        clearButton = new JButton("Clear");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = multiplyButton;
        functionButtons[3] = divideButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equalButton;
        functionButtons[6] = negativeButton;
        functionButtons[7] = deleteButton;
        functionButtons[8] = clearButton;

        for(int i = 0; i < functionButtons.length; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(font);
            functionButtons[i].setFocusable(false);
        }

        for(int i = 0; i < numButtons.length; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].addActionListener(this);
            numButtons[i].setFont(font);
            numButtons[i].setFocusable(false);
        }

        deleteButton.setBounds(50, 450, 60, 50);
        clearButton.setBounds(120, 450, 110, 50);
        equalButton.setBounds(240, 450, 110, 50);

        panel = new JPanel();
        panel.setBounds(50, 120, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numButtons[1]);
        panel.add(numButtons[2]);
        panel.add(numButtons[3]);
        panel.add(addButton);

        panel.add(numButtons[4]);
        panel.add(numButtons[5]);
        panel.add(numButtons[6]);
        panel.add(subButton);

        panel.add(numButtons[7]);
        panel.add(numButtons[8]);
        panel.add(numButtons[9]);
        panel.add(multiplyButton);

        panel.add(decButton);
        panel.add(numButtons[0]);
        panel.add(negativeButton);
        panel.add(divideButton);

        frame.add(deleteButton);
        frame.add(clearButton);
        frame.add(equalButton);

        frame.add(panel);
        frame.add(textField);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        switch(source) {
            case JButton button when button == decButton -> {
                if(!textField.getText().contains(".")) {
                    textField.setText(textField.getText().concat("."));
                }
            }
            case JButton button when button == addButton -> {
                num1 = Double.parseDouble(textField.getText());
                operator = '+';
                textField.setText("");
            }
            case JButton button when button == subButton -> {
                num1 = Double.parseDouble(textField.getText());
                operator = '-';
                textField.setText("");
            }
            case JButton button when button == multiplyButton -> {
                num1 = Double.parseDouble(textField.getText());
                operator = '*';
                textField.setText("");
            }
            case JButton button when button == divideButton -> {
                num1 = Double.parseDouble(textField.getText());
                operator = '/';
                textField.setText("");
            }
            case JButton button when button == negativeButton -> {
                double temp = Double.parseDouble(textField.getText());
                temp = temp * -1;
                textField.setText(String.valueOf(temp));
            }
            case JButton button when button == equalButton -> {
                num2 = Double.parseDouble(textField.getText());

                result = switch(operator) {
                    case '+' -> num1 + num2;
                    case '-' -> num1 - num2;
                    case '*' -> num1 * num2;
                    case '/' -> num1 / num2;
                    default -> {
                        throw new IllegalStateException("Unexpected value: " + operator);
                    }
                };

                textField.setText(String.valueOf(result));
                num1 = result;
            }
            case JButton button when button == deleteButton -> {
                String text = textField.getText();
                if(!text.isEmpty()) {
                    textField.setText(text.substring(0, text.length() - 1));
                }
            }
            case JButton button when button == clearButton -> {
                textField.setText("");
            }
            default -> {
                for(int i = 0; i < numButtons.length; i++) {
                    if(source == numButtons[i]) {
                        textField.setText(textField.getText().concat(String.valueOf(i)));
                    }
                }
            }
        }
    }
}