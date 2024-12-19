import javax.swing.*;
import java.awt.*;

public class SudokuApplication {
    private final JTextField[][] textFieldGrid = new JTextField[9][9];
    private final SudokuSolver sudoku = new SudokuModel();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SudokuApplication app = new SudokuApplication();
            app.createWindow();
        });
    }

    private void createWindow() {
        JFrame frame = new JFrame("SudokuSolver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();

        sudokuGUI(pane); // Ska skapa sudokuGUI delen

        JPanel buttonPanel = new JPanel();

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> {
            solveButtonHandler();
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            for(int row = 0; row < 9; row++) {
                for(int col = 0; col < 9; col++){
                    textFieldGrid[row][col].setText("");
                }
            }
        });

        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        pane.add(buttonPanel, BorderLayout.SOUTH);

        frame.setPreferredSize(new Dimension(400,400));
        frame.pack();
        frame.setVisible(true);
    }

    private void sudokuGUI(Container pane) {
        JPanel sudokuPanel = new JPanel();
        sudokuPanel.setLayout(new GridLayout(9, 9));


        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField textField = new JTextField();
                textField.setHorizontalAlignment(SwingConstants.CENTER);
                int rowRegion = (row / 3);
                int colRegion = (col / 3);
                if((rowRegion + colRegion) % 2 == 0) {
                    textField.setBackground(Color.lightGray);
                }

                sudokuPanel.add(textField);

                textFieldGrid[row][col] = textField;
            }
        }
        pane.add(sudokuPanel, BorderLayout.CENTER);
    }

    private void solveButtonHandler(){
        int[][] numGrid = new int[9][9];

        // Kolla om textfälten uppfyller kraven
        try {
            for(int row = 0; row < 9; row++) {
                for(int col = 0; col < 9; col++){
                    String textValue = textFieldGrid[row][col].getText();
                    if(textValue.isEmpty()) {
                        numGrid[row][col] = 0;
                    }
                    else {
                        int numValue = Integer.parseInt(textValue);
                        if(numValue < 1 || numValue > 9) {
                            throw new NumberFormatException("Fel siffra");
                        }
                        numGrid[row][col] = numValue;
                    }
                }
            }

            sudoku.setGrid(numGrid);

            if(sudoku.solve()) {
                for(int row = 0; row < 9; row++) {
                    for(int col = 0; col < 9; col++){
                        textFieldGrid[row][col].setText(Integer.toString(sudoku.get(row,col)));
                    }
                }
            }

            else {
                JOptionPane.showMessageDialog(null, "Ej lösbart!");
            }
        } catch (NumberFormatException numException) {
            if(numException.getMessage().equals("Fel siffra")) {
                JOptionPane.showMessageDialog(null, "Siffror måste vara mellan 1 och 9!");
            }
            else {
                JOptionPane.showMessageDialog(null, "Du får bara använda siffror!");
            }
        }
    }
}
