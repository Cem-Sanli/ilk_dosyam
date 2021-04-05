import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


class Tetromino {

    // Private data fields
    private Color[][] color; // color of the tetromino L
    public boolean[][] shapeMatrix; // shape of the tetromino L
    public Point[][] coordinateMatrix; // coordinates of the tetromino L w.r.t the game grid
    private int gridWidth, gridHeight; // dimensions of the tetris game grid
    public ArrayList<boolean[][]> Shapes;
    public boolean[][] newShape;
    public int count;
    public ArrayList<boolean[][]> Shapes2;
    public int[][] initialNumbers;
    public int[][] locOfNumbers;

    // Constructor
    Tetromino(int gridHeight, int gridWidth, int count, ArrayList<boolean[][]> Shapes2) {

        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
        locOfNumbers = new int[gridWidth][gridHeight];
        // color of the tetromino L is determined randomly
        Random random = new Random();
//        int red = random.nextInt(256), green = random.nextInt(256), blue = random.nextInt(256);
//        color = new Color(red, green, blue);
        // shape of the tetromino L in its initial orientation
        Shapes = new ArrayList<>();

        boolean[][] LShape = {{false, false, false}, {true, true, true}, {true, false, false}};
        boolean[][] OShape = {{true, true}, {true, true}};
        boolean[][] ZShape = {{false, false, false,}, {true, true, false}, {false, true, true}};
        boolean[][] JShape = {{false, false, false}, {true, true, true}, {false, false, true}};
        boolean[][] IShape = {{false, true, false, false}, {false, true, false, false}, {false, true, false, false}, {false, true, false, false}};
        boolean[][] TShape = {{false, false, false}, {true, true, true}, {false, true, false}};
        boolean[][] SShape = {{false, false, false}, {false, true, true}, {true, true, false}};
        Shapes.add(LShape);
        Shapes.add(IShape);
        Shapes.add(OShape);
        Shapes.add(SShape);
        Shapes.add(TShape);
        Shapes.add(ZShape);
        Shapes.add(JShape);
        shapeMatrix = getShape();
        newShape = getShape();
        if (count > 0) {
            shapeMatrix = Shapes2.get(count - 1);

        }


        int n_rows = shapeMatrix.length, n_cols = shapeMatrix[0].length;
        initialNumbers = new int[n_rows][n_cols];
        Random hý = new Random();
        for (int i = 0; i < initialNumbers.length; i++) {
            for (int j = 0; j < initialNumbers[0].length; j++) {
                if (shapeMatrix[i][j]) {
                    initialNumbers[i][j] = hý.nextInt(3) + 1;
                }
                initialNumbers[i][j] = (int) Math.pow(2, initialNumbers[i][j]);


            }


        }
        color = new Color[n_rows][n_cols];
        for (int i = 0; i < initialNumbers.length; i++) {
            for (int j = 0; j < initialNumbers[0].length; j++) {
                if (shapeMatrix[i][j]) {
                    if (initialNumbers[i][j] == 2) {
                        color[i][j] = new Color(102, 102, 102);
                    }
                    if (initialNumbers[i][j] == 4) {
                        color[i][j] = new Color(81, 100, 19);
                    }
                    if (initialNumbers[i][j] == 8) {
                        color[i][j] = new Color(45, 89, 202);
                    }

                } else {
                    color[i][j] = new Color(196, 185, 161);
                }


            }
        }


        // initial coordinates just before the tetromino L enters the game grid from the upper side
        // at a random horizontal position

        coordinateMatrix = new Point[n_rows][n_cols];

        int lowerLeftCornerX = random.nextInt(gridWidth - (n_cols - 1)), lowerLeftCornerY = gridHeight;
        coordinateMatrix[n_rows - 1][0] = new Point(lowerLeftCornerX, lowerLeftCornerY);
        for (int row = n_rows - 1; row >= 0; row--)
            for (int col = 0; col < n_cols; col++) {
                if (row == n_rows - 1 && col == 0)
                    continue;
                else if (col == 0) {
                    int currentX = coordinateMatrix[row + 1][col].x;
                    int currentY = coordinateMatrix[row + 1][col].y + 1;
                    coordinateMatrix[row][col] = new Point(currentX, currentY);
                    continue;
                }
                int currentX = coordinateMatrix[row][col - 1].x + 1;
                int currentY = coordinateMatrix[row][col - 1].y;
                coordinateMatrix[row][col] = new Point(currentX, currentY);
            }


    }

    Tetromino(int gridHeight, int gridWidth, int count) {
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
        locOfNumbers = new int[gridWidth][gridHeight];
        // color of the tetromino L is determined randomly
        Random random = new Random();
//        int red = random.nextInt(256), green = random.nextInt(256), blue = random.nextInt(256);
//        color = new Color(red, green, blue);
        // shape of the tetromino L in its initial orientation
        Shapes = new ArrayList<>();

        boolean[][] LShape = {{false, false, false}, {true, true, true}, {true, false, false}};
        boolean[][] OShape = {{true, true}, {true, true}};
        boolean[][] ZShape = {{false, false, false,}, {true, true, false,}, {false, true, true,}};
        boolean[][] JShape = {{false, false, false}, {true, true, true}, {false, false, true}};
        boolean[][] IShape = {{false, true, false, false}, {false, true, false, false}, {false, true, false, false}, {false, true, false, false}};
        boolean[][] TShape = {{false, false, false}, {true, true, true}, {false, true, false}};
        boolean[][] SShape = {{false, false, false}, {false, true, true}, {true, true, false}};
        Shapes.add(LShape);
        Shapes.add(IShape);
        Shapes.add(OShape);
        Shapes.add(SShape);
        Shapes.add(TShape);
        Shapes.add(ZShape);
        Shapes.add(JShape);
        shapeMatrix = getShape();
        newShape = getShape();


        int n_rows = shapeMatrix.length, n_cols = shapeMatrix[0].length;
        initialNumbers = new int[n_rows][n_cols];
        Random hý = new Random();
        for (int i = 0; i < initialNumbers.length; i++) {
            for (int j = 0; j < initialNumbers[0].length; j++) {
                if (shapeMatrix[i][j]) {
                    initialNumbers[i][j] = hý.nextInt(3) + 1;
                }
                initialNumbers[i][j] = (int) Math.pow(2, initialNumbers[i][j]);
            }
        }


        // initial coordinates just before the tetromino L enters the game grid from the upper side
        // at a random horizontal position

        coordinateMatrix = new Point[n_rows][n_cols];
        int lowerLeftCornerX = random.nextInt(gridWidth - (n_cols - 1)), lowerLeftCornerY = gridHeight;
        coordinateMatrix[n_rows - 1][0] = new Point(lowerLeftCornerX, lowerLeftCornerY);
        for (int row = n_rows - 1; row >= 0; row--)
            for (int col = 0; col < n_cols; col++) {
                if (row == n_rows - 1 && col == 0)
                    continue;
                else if (col == 0) {
                    int currentX = coordinateMatrix[row + 1][col].x;
                    int currentY = coordinateMatrix[row + 1][col].y + 1;
                    coordinateMatrix[row][col] = new Point(currentX, currentY);
                    continue;
                }
                int currentX = coordinateMatrix[row][col - 1].x + 1;
                int currentY = coordinateMatrix[row][col - 1].y;
                coordinateMatrix[row][col] = new Point(currentX, currentY);
            }

        color = new Color[n_rows][n_cols];

        for (int i = 0; i < initialNumbers.length; i++) {
            for (int j = 0; j < initialNumbers[0].length; j++) {
                if (shapeMatrix[i][j]) {
                    if (initialNumbers[i][j] == 2) {
                        color[i][j] = new Color(102, 102, 102);
                    }
                    if (initialNumbers[i][j] == 4) {
                        color[i][j] = new Color(81, 100, 19);
                    }
                    if (initialNumbers[i][j] == 8) {
                        color[i][j] = new Color(45, 89, 202);
                    }
                } else {
                    color[i][j] = new Color(196, 185, 161);
                }


            }
        }

    }

    // Getter method for getting the color of tetromino L
    public Color[][] getColor() {
        return color;
    }

    public boolean[][] getNewShape() {
        return newShape;
    }

    public boolean[][] getShape() {
        Random r = new Random();
        int num = r.nextInt(7);
        return Shapes.get(num);
    }

    public int[][] getArray() {
        Point[][] pointOfSquares = new Point[initialNumbers.length][initialNumbers[0].length];
        for (int row = 0; row < coordinateMatrix.length; row++) {
            for (int col = 0; col < coordinateMatrix[0].length; col++) {

                Point point = coordinateMatrix[row][col];
                pointOfSquares[row][col] = point;
                // considering newly entered tetromino  objects to the game grid that may have squares with point.y >= gridHeight
                if (point.y < gridHeight && shapeMatrix[row][col]) {
//                    for (int i = 0; i < color.length; i++) {
//                        for (int j = 0; j < color[0].length; j++) {
//                            StdDraw.setPenColor(color[i][j]);
//                            StdDraw.filledSquare(point.x, point.y, 0.5);
//                            StdDraw.setPenColor(StdDraw.WHITE);
                    if (initialNumbers[row][col] != 1) {
                        locOfNumbers[point.x][point.y] = initialNumbers[row][col];
                    } else {
                        locOfNumbers[point.x][point.y] = 0;
                    }

//                            StdDraw.text(point.x, point.y, String.valueOf(initialNumbers[row][col]));

                }
            }

        }


//            }

//        }
        locOfNumbers = rotateDigitsToLeft(locOfNumbers);
//        System.out.println("--------------------------------------");

        return locOfNumbers;
    }

    // Method for displaying tetromino L on the game grid
    public void display() {
        for (int row = 0; row < coordinateMatrix.length; row++) {
            for (int col = 0; col < coordinateMatrix[0].length; col++) {
                Point point = coordinateMatrix[row][col];
                // considering newly entered tetromino  objects to the game grid that may have squares with point.y >= gridHeight
                if (point.y < gridHeight && shapeMatrix[row][col]) {

                    StdDraw.setPenColor(color[row][col]);
                    StdDraw.filledSquare(point.x, point.y, 0.5);
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.text(point.x, point.y, String.valueOf(initialNumbers[row][col]));


                }
//                System.out.println(coordinateMatrix[row][col].x+ " " + coordinateMatrix[row][col].y);
            }


        }

    }

    public void displayNext() {
        double x = 17.8;
        double y = 2.0;
        if (newShape == Shapes.get(0)) {//L
            StdDraw.filledSquare(16.8, 1, 0.5);
            StdDraw.filledSquare(16.8, 2, 0.5);
            StdDraw.filledSquare(17.8, 2, 0.5);
            StdDraw.filledSquare(18.8, 2, 0.5);

        }
        if (newShape == Shapes.get(1)) {//I
            StdDraw.filledSquare(17.8, 3.5, 0.5);
            StdDraw.filledSquare(17.8, 2.5, 0.5);
            StdDraw.filledSquare(17.8, 1.5, 0.5);
            StdDraw.filledSquare(17.8, 0.5, 0.5);
        }
        if (newShape == Shapes.get(2)) {//O
            StdDraw.filledSquare(18.3, 2.5, 0.5);
            StdDraw.filledSquare(17.3, 2.5, 0.5);
            StdDraw.filledSquare(17.3, 1.5, 0.5);
            StdDraw.filledSquare(18.3, 1.5, 0.5);
        }
        if (newShape == Shapes.get(3)) {//S
            StdDraw.filledSquare(18.8, 2, 0.5);
            StdDraw.filledSquare(17.8, 2, 0.5);
            StdDraw.filledSquare(17.8, 1, 0.5);
            StdDraw.filledSquare(16.8, 1, 0.5);
        }
        if (newShape == Shapes.get(4)) {//T
            StdDraw.filledSquare(16.8, 2, 0.5);
            StdDraw.filledSquare(17.8, 1, 0.5);
            StdDraw.filledSquare(17.8, 2, 0.5);
            StdDraw.filledSquare(18.8, 2, 0.5);
        }
        if (newShape == Shapes.get(5)) {//Z
            StdDraw.filledSquare(16.8, 2, 0.5);
            StdDraw.filledSquare(17.8, 2, 0.5);
            StdDraw.filledSquare(17.8, 1, 0.5);
            StdDraw.filledSquare(18.8, 1, 0.5);
        }
        if (newShape == Shapes.get(6)) {//J
            StdDraw.filledSquare(16.8, 2, 0.5);
            StdDraw.filledSquare(17.8, 2, 0.5);
            StdDraw.filledSquare(18.8, 2, 0.5);
            StdDraw.filledSquare(18.8, 1, 0.5);
        }
    }


    // Method for moving tetromino  down by 1 in the game grid
    public boolean goDown(Grid gameGrid) {
        // Check whether tetromino L can go down or not
        boolean canGoDown = true;
        // determine the coordinates of the bottommost block for each column of tetromino L
        Point dummyPoint = new Point(-1, -1);
        Point[] bottommostBlock = {dummyPoint, dummyPoint, dummyPoint, dummyPoint};
        for (int col = 0; col < shapeMatrix[0].length; col++) {
            for (int row = shapeMatrix.length - 1; row >= 0; row--) {
                if (shapeMatrix[row][col]) {
                    bottommostBlock[col] = coordinateMatrix[row][col];
                    if (bottommostBlock[col].y == 0) { // tetromino L cannot go down if it is already at y = 0
                        canGoDown = false;

                    }
                    break; // break the inner for loop
                }
            }
            if (!canGoDown)
                break; // break the outer for loop
        }

        // check if the grid square below the bottommost block is occupied for each column of tetromino L
        if (canGoDown) {
            for (int i = 0; i < bottommostBlock.length; i++) {
                // skip each column of tetromino L that does not contain any blocks
                if (bottommostBlock[i].equals(dummyPoint))
                    continue;
                // skip each column of tetromino L whose bottommost block is out of the game grid
                // (newly entered tetromino L objects to the game grid)
                if (bottommostBlock[i].y > gridHeight)
                    continue;
                if (gameGrid.isOccupied(bottommostBlock[i].y - 1, bottommostBlock[i].x)) {
                    canGoDown = false;
                    break; // break the for loop
                }
            }
        }
        // move tetromino L down by 1 in the game grid if it can go down
        if (canGoDown) {
            for (int row = 0; row < coordinateMatrix.length; row++)
                for (int col = 0; col < coordinateMatrix[0].length; col++)
                    coordinateMatrix[row][col].y--;
        }

        // return the result
        return canGoDown;
    }


    // Method for returning the occupied squares w.r.t. the game grid by a placed (stopped) tetromino
    public Point[] getOccupiedSquares() {
        Point[] occupiedSquares = new Point[4];

        int count = 0;
        for (int row = 0; row < coordinateMatrix.length; row++)
            for (int col = 0; col < coordinateMatrix[0].length; col++)
                if (shapeMatrix[row][col])
                    occupiedSquares[count++] = coordinateMatrix[row][col];
        return occupiedSquares;
    }


    // Method for moving tetromino L left by 1 in the game grid
    public boolean goLeft(Grid gameGrid) {
        // Check whether tetromino L can go left or not
        boolean canGoLeft = true;
        // determine the coordinates of the leftmost block for each row of tetromino
        Point dummyPoint = new Point(-1, -1);
        Point[] leftmostBlock = {dummyPoint, dummyPoint, dummyPoint, dummyPoint};
        for (int row = 0; row < shapeMatrix.length; row++) {
            for (int col = 0; col < shapeMatrix[0].length; col++) {
                if (shapeMatrix[row][col]) {
                    leftmostBlock[row] = coordinateMatrix[row][col];
                    if (leftmostBlock[row].x == 0) // tetromino L cannot go left if it is already at x = 0
                        canGoLeft = false;
                    break; // break the inner for loop
                }
            }
            if (!canGoLeft)
                break; // break the outer for loop
        }
        // check if the grid square on the left of the leftmost block is occupied for each row of tetromino L
        if (canGoLeft) {
            for (int i = 0; i < leftmostBlock.length; i++) {
                // skip each row of tetromino L that does not contain any blocks
                if (leftmostBlock[i].equals(dummyPoint))
                    continue;
                // skip each row of tetromino L whose leftmost block is out of the game grid
                // (newly entered tetromino L objects to the game grid)
                if (leftmostBlock[i].y >= gridHeight)
                    continue;
                if (gameGrid.isOccupied(leftmostBlock[i].y, leftmostBlock[i].x - 1)) {
                    canGoLeft = false;
                    break; // break the for loop
                }
            }
        }
        // move tetromino L left by 1 in the game grid if it can go left
        if (canGoLeft) {
            for (int row = 0; row < coordinateMatrix.length; row++)
                for (int col = 0; col < coordinateMatrix[0].length; col++)
                    coordinateMatrix[row][col].x--;
        }
        // return the result
        return canGoLeft;
    }

    // Method for moving tetromino L right by 1 in the game grid
    public boolean goRight(Grid gameGrid) {
        // Check whether tetromino L can go right or not
        boolean canGoRight = true;
        // determine the coordinates of the rightmost block for each row of tetromino L
        Point dummyPoint = new Point(-1, -1);
        Point[] rightmostBlock = {dummyPoint, dummyPoint, dummyPoint, dummyPoint};
        for (int row = 0; row < shapeMatrix.length; row++) {
            for (int col = shapeMatrix[0].length - 1; col >= 0; col--) {
                if (shapeMatrix[row][col]) {
                    rightmostBlock[row] = coordinateMatrix[row][col];

                    if (rightmostBlock[row].x == gridWidth - 1) // tetromino L cannot go right if it is already at x = gridWidth - 1
                        canGoRight = false;
                    break; // break the inner for loop
                }
            }
            if (!canGoRight)
                break; // break the outer for loop
        }
        // check if the grid square on the right of the rightmost block is occupied for each row of tetromino L
        if (canGoRight) {
            for (int i = 0; i < rightmostBlock.length; i++) {
                // skip each row of tetromino L that does not contain any blocks
                if (rightmostBlock[i].equals(dummyPoint))
                    continue;
                // skip each row of tetromino L whose rightmost block is out of the game grid
                // (newly entered tetromino L objects to the game grid)
                if (rightmostBlock[i].y >= gridHeight)
                    continue;
                if (gameGrid.isOccupied(rightmostBlock[i].y, rightmostBlock[i].x + 1)) {
                    canGoRight = false;
                    break; // break the for loop
                }
            }
        }
        // move tetromino L right by 1 in the game grid if it can go right
        if (canGoRight) {
            for (int row = 0; row < coordinateMatrix.length; row++)
                for (int col = 0; col < coordinateMatrix[0].length; col++)
                    coordinateMatrix[row][col].x++;
        }
        // return the result
        return canGoRight;


    }

    public boolean rotateRight(Grid gameGrid) {
        boolean canRotateRight = true;
        Point[][] temp = rotateRightMatrix(coordinateMatrix.length, coordinateMatrix);


        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                if (!gameGrid.isInside(temp[i][j].y, temp[i][j].x) || gameGrid.isOccupied(temp[i][j].y, temp[i][j].x)) {
                    canRotateRight = false;
                }
            }
        }

        if (canRotateRight) {
            initialNumbers = rotateDigitsToRight(initialNumbers.length, initialNumbers);
            color = rotateColorToRight(color.length, color);
            shapeMatrix = rotateRightShape(shapeMatrix.length, shapeMatrix);

        }


        return canRotateRight;


    }


    public boolean rotateLeft(Grid gameGrid) {
        boolean canRotateLeft = true;
        Point[][] temp = rotateLeftMatrix(coordinateMatrix.length, coordinateMatrix);
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                if (!gameGrid.isInside(temp[i][j].y, temp[i][j].x) || gameGrid.isOccupied(temp[i][j].y, temp[i][j].x)) {
                    canRotateLeft = false;
                }
            }
        }

        if (canRotateLeft) {
            initialNumbers = rotateDigitsToLeft(initialNumbers);
            color = rotateColorToLeft(color.length, color);
            shapeMatrix = rotateLeftShape(shapeMatrix.length, shapeMatrix);

        }

        return canRotateLeft;


    }

    public Point[][] rotateRightMatrix(int N, Point mat[][]) {
        Point[][] arr = new Point[mat.length][mat[0].length];
        // Traverse each cycle
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = mat[j][N - i - 1];
            }
        }

        return arr;
    }

    public boolean[][] rotateRightShape(int N, boolean mat[][]) {
        for (int i = 0; i < N / 2; i++) {
            for (int j = i; j < N - i - 1; j++) {

                // Swap elements of each cycle
                // in clockwise direction
                boolean temp = mat[i][j];
                mat[i][j] = mat[N - 1 - j][i];
                mat[N - 1 - j][i] = mat[N - 1 - i][N - 1 - j];
                mat[N - 1 - i][N - 1 - j] = mat[j][N - 1 - i];
                mat[j][N - 1 - i] = temp;
            }
        }

        return mat;
    }

    public Point[][] rotateLeftMatrix(int N, Point mat[][]) {

        Point[][] arr = new Point[mat.length][mat[0].length];
        // Traverse each cycle
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = mat[N - j - 1][i];
            }
        }

        return arr;

    }

    public boolean[][] rotateLeftShape(int N, boolean[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        boolean[][] transposedMatrix = new boolean[n][m];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                transposedMatrix[x][y] = mat[y][x];
            }
        }
        for (int col = 0; col < transposedMatrix[0].length; col++) {
            for (int row = 0; row < transposedMatrix.length / 2; row++) {
                boolean temp = transposedMatrix[row][col];
                transposedMatrix[row][col] = transposedMatrix[transposedMatrix.length - row - 1][col];
                transposedMatrix[transposedMatrix.length - row - 1][col] = temp;
            }
        }

        return transposedMatrix;
    }

    public int[][] rotateDigitsToRight(int N, int[][] mat) {
        for (int i = 0; i < N / 2; i++) {
            for (int j = i; j < N - i - 1; j++) {

                // Swap elements of each cycle
                // in clockwise direction
                int temp = mat[i][j];
                mat[i][j] = mat[N - 1 - j][i];
                mat[N - 1 - j][i] = mat[N - 1 - i][N - 1 - j];
                mat[N - 1 - i][N - 1 - j] = mat[j][N - 1 - i];
                mat[j][N - 1 - i] = temp;
            }
        }

        return mat;
    }

    public int[][] rotateDigitsToLeft(int mat[][]) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] transposedMatrix = new int[n][m];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                transposedMatrix[x][y] = mat[y][x];
            }
        }
        for (int col = 0; col < transposedMatrix[0].length; col++) {
            for (int row = 0; row < transposedMatrix.length / 2; row++) {
                int temp = transposedMatrix[row][col];
                transposedMatrix[row][col] = transposedMatrix[transposedMatrix.length - row - 1][col];
                transposedMatrix[transposedMatrix.length - row - 1][col] = temp;
            }
        }

        return transposedMatrix;

    }

    public Color[][] rotateColorToLeft(int N, Color mat[][]) {
        int m = mat.length;
        int n = mat[0].length;

        Color[][] transposedMatrix = new Color[n][m];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                transposedMatrix[x][y] = mat[y][x];
            }
        }
        for (int col = 0; col < transposedMatrix[0].length; col++) {
            for (int row = 0; row < transposedMatrix.length / 2; row++) {
                Color temp = transposedMatrix[row][col];
                transposedMatrix[row][col] = transposedMatrix[transposedMatrix.length - row - 1][col];
                transposedMatrix[transposedMatrix.length - row - 1][col] = temp;
            }
        }

        return transposedMatrix;
    }

    public Color[][] rotateColorToRight(int N, Color[][] mat) {
        for (int i = 0; i < N / 2; i++) {
            for (int j = i; j < N - i - 1; j++) {

                // Swap elements of each cycle
                // in clockwise direction
                Color temp = mat[i][j];
                mat[i][j] = mat[N - 1 - j][i];
                mat[N - 1 - j][i] = mat[N - 1 - i][N - 1 - j];
                mat[N - 1 - i][N - 1 - j] = mat[j][N - 1 - i];
                mat[j][N - 1 - i] = temp;
            }
        }

        return mat;
    }


}

