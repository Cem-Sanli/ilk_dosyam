import java.awt.*;

import java.util.ArrayList;

public class Grid {
    // Private data fields
    private Color emptySquare; // color used for empty squares
    private Color[][] colorMatrix; // a matrix storing colors of all squares
    //    public ArrayList<Integer> yardým = new ArrayList<>();
    private int[][] numberMatrix;
    public long score;

    // Constructor
    Grid(int n_rows, int n_cols) {
        // assigning color used for empty squares

        emptySquare = new Color(196, 185, 161);
        // creating colorMatrix with given dimensions
        colorMatrix = new Color[n_rows][n_cols];
        // initializing colorMatrix with color emptySquare for all its elements
        // using initMatrix method defined below
        numberMatrix = new int[n_rows][n_cols];
        initMatrix();
        score = 0;

    }

    // Method used for initializing colorMatrix
    public void initMatrix() {
        for (int row = 0; row < colorMatrix.length; row++)
            for (int col = 0; col < colorMatrix[0].length; col++)
                colorMatrix[row][col] = emptySquare;
    }

    // Method used for checking whether the square with given indices is inside the grid or not
    public boolean isInside(int row, int col) {
        if (row < 0 || row >= colorMatrix.length)
            return false;
        if (col < 0 || col >= colorMatrix[0].length)
            return false;
        return true;
    }

    // Getter method for getting color of the square with given indices
    public Color getColor(int row, int col) {
        if (isInside(row, col))
            return colorMatrix[row][col];
        else
            return null;
    }

    // Setter method for setting color of the square with given indices
    public void setColor(Color color, int row, int col) {
        if (isInside(row, col))
            colorMatrix[row][col] = color;
    }

    // Method used for checking whether the square with given indices is occupied or empty
    public boolean isOccupied(int row, int col) {
        return colorMatrix[row][col] != emptySquare;

    }

    // Method for updating the game grid with a placed (stopped) tetromino
    public void updateGrid(Point[] occupiedSquaresByTetromino, Tetromino t) {

        numberMatrix = t.locOfNumbers;


        for (Point point : occupiedSquaresByTetromino) {
            if (isInside(point.y, point.x)) {
                if (numberMatrix[19 - point.y][point.x] == 2)
                    colorMatrix[point.y][point.x] = new Color(102, 102, 102);
                if (numberMatrix[19 - point.y][point.x] == 4)
                    colorMatrix[point.y][point.x] = new Color(81, 100, 19);
                if (numberMatrix[19 - point.y][point.x] == 8)
                    colorMatrix[point.y][point.x] = new Color(45, 89, 202);


            }


        }


        Color[][] temp = new Color[colorMatrix.length][colorMatrix[0].length];
        boolean[][] full = new boolean[colorMatrix.length][colorMatrix[0].length];
        for (
                int i = 0;
                i < colorMatrix.length; i++) {
            for (int j = 0; j < colorMatrix[0].length; j++) {
                temp[i][j] = colorMatrix[i][j];
                if (isOccupied(i, j))
                    full[i][j] = true;
                else
                    full[i][j] = false;

            }
        }

        boolean isCellDeleted = false;
        ArrayList<Integer> deletedLines = new ArrayList<>();
        for (
                int i = 0;
                i < full.length; i++) {
            if (allTrue(full[i])) {
                deletedLines.add(i);
                isCellDeleted = true;

            }


        }

        ArrayList<int[]> tempDeleted = new ArrayList<>();
        if (isCellDeleted) {


            for (int i = 0; i < numberMatrix.length; i++) {
                if (allTrueInt(numberMatrix[i])) {
                    tempDeleted.add(numberMatrix[i]);
                }
            }
            for (int i = numberMatrix.length - 1; i >= 0; i--) {
                if (allTrueInt(numberMatrix[i])) {

                    for (int j = i; j >= deletedLines.size(); j--) {


                        numberMatrix[j] = numberMatrix[j - deletedLines.size()];

                        numberMatrix[j - deletedLines.size()] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};


                    }


                }


            }

            for (int i = 0; i < tempDeleted.size(); i++) {
                for (int j = 0; j < tempDeleted.get(0).length; j++) {
                    score += tempDeleted.get(i)[j];
                }

            }


        }

//        for (int i = 0; i < numberMatrix.length; i++) {
//            for (int j = 0; j < numberMatrix[0].length; j++) {
//
//            }
//        }


    }

    // Method used for displaying the grid
    public void display() {
        // drawing squares


        for (int row = 0; row < colorMatrix.length; row++) {
            for (int col = 0; col < colorMatrix[0].length; col++) {


                if (numberMatrix[row][col] != 0) {
                    if (numberMatrix[row][col] == 2) {
                        setColor(new Color(102, 102, 102), 19 - row, col);
                    }

                    if (numberMatrix[row][col] == 4) {
                        setColor(new Color(81, 100, 19), 19 - row, col);

                    }
                    if (numberMatrix[row][col] == 8) {
                        setColor(new Color(45, 89, 202), 19 - row, col);

                    }
                    if (numberMatrix[row][col] == 16) {
                        setColor(new Color(58, 61, 57), 19 - row, col);
                    }
                    if (numberMatrix[row][col] == 32) {
                        setColor(new Color(92, 10, 79), 19 - row, col);
                    }
                    if (numberMatrix[row][col] == 64) {
                        setColor(new Color(12, 56, 13), 19 - row, col);
                    }

                    if (numberMatrix[row][col] == 128) {
                        setColor(new Color(156, 156, 0), 19 - row, col);
                    }

                    if (numberMatrix[row][col] == 256) {
                        setColor(new Color(230, 156, 0), 19 - row, col);
                    }

                    if (numberMatrix[row][col] == 512) {
                        setColor(new Color(0, 200, 200), 19 - row, col);
                    }

                    if (numberMatrix[row][col] == 1024) {
                        setColor(new Color(90, 156, 0), 19 - row, col);
                    }

                    if (numberMatrix[row][col] == 2048) {
                        setColor(new Color(80, 80, 160), 19 - row, col);
                    }
                } else {
                    setColor(emptySquare, 19 - row, col);
                }


                StdDraw.setPenColor(colorMatrix[row][col]);
                StdDraw.filledSquare(col, row, 0.5);

                if (numberMatrix[row][col] != 0) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.text(col, 19 - row, String.valueOf(numberMatrix[row][col]));
                }


            }


        }


        // drawing the grid
        Color co = new Color(107, 83, 62);
        StdDraw.setPenColor(co);
        for (double x = -0.5; x < colorMatrix[0].length; x++) // vertical lines
            StdDraw.line(x, -0.5, x, colorMatrix.length - 0.5);
        for (double y = -0.5; y < colorMatrix.length; y++) // horizontal lines
            StdDraw.line(-0.5, y, colorMatrix[0].length - 0.5, y);


//        for (int j = 0; j < numberMatrix[0].length; j++) {
//
//            for (int i = 1; i <= numberMatrix.length - 1; i++) {
//                if (numberMatrix[i][j] == numberMatrix[i - 1][j]) {
//                    numberMatrix[i][j] += numberMatrix[i - 1][j];
//                    numberMatrix[i-1][j] = 0;
//                    isAdded = true;
//                }
//            }
//        }

        for (int j = 0; j < numberMatrix[0].length; j++) {
            for (int i = 0; i <= numberMatrix.length - 2; i++) {
                if (numberMatrix[i + 1][j] != 0 && numberMatrix[i + 1][j] == numberMatrix[i][j]) {
                    numberMatrix[i + 1][j] *= 2;
                    numberMatrix[i][j] = 0;
                    for (int k = numberMatrix.length - 2; k >= 0; k--) {
                        if (numberMatrix[k + 1][j] == 0) {
                            numberMatrix[k + 1][j] = numberMatrix[k][j];
                            numberMatrix[k][j] = 0;

                        }
                    }

                }
            }
        }


    }

    public boolean gameOver(Point[] occupiedSquaresByTetromino) {
        for (int i = 0; i < occupiedSquaresByTetromino.length; i++) {
            if (occupiedSquaresByTetromino[i].y == 19)
                return true;
        }
        return false;
    }


    private boolean allTrue(boolean[] b) {
        for (boolean value : b) {
            if (!value)
                return false;

        }
        return true;
    }

    private boolean allTrueInt(int[] b) {
        for (int x : b) {
            if (x == 0)
                return false;
        }

        return true;

    }


}
