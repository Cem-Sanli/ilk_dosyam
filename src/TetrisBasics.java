import java.awt.*;
import java.util.ArrayList;


public class TetrisBasics {
    public static void main(String[] args) {

        // set the size of the drawing canvas
        int score = 0;
        int border = 300;
        StdDraw.setCanvasSize(600, 750);
        // set the scale of the coordinate system
        StdDraw.setXscale(-0.5, 20);
        StdDraw.setYscale(-0.5, 19.5);
        // double buffering is used for speeding up drawing needed to enable computer animations
        // check https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html for details
        StdDraw.enableDoubleBuffering();

        ArrayList<boolean[][]> Shapes;
        Shapes = new ArrayList<>();
        // create a grid as the tetris game environment
        Grid gameGrid = new Grid(20, 16);
        // create the first tetromino to enter the game grid
        int count = 0;
        Tetromino t = new Tetromino(20, 16, count);
        boolean[][] newShape = t.getNewShape();
        int[][] numArray;
        int[][] locOfNums = new int[20][16];
        int[][] locOfNumsTemp = new int[20][16];
        int[] finalArray = new int[4];
        Shapes.add(newShape);
        boolean createANewTetromino;
        boolean isGameOver = false;
        boolean isGamePaused = false;
        int time = 600;
        //create the second tetromino
        // main animation loop
        while (true) {
            while (!isGameOver) {

                // keyboard interaction for moving the active tetromino left or right
                boolean success = false;
                numArray = t.initialNumbers;
                int count_1 = 0;
                for (int i = 0; i < numArray.length; i++) {
                    for (int j = 0; j < numArray[0].length; j++) {
                        if (numArray[i][j] != 1) {
                            finalArray[count_1] = numArray[i][j];
                            count_1++;
                        }
                    }
                }

                if (StdDraw.hasNextKeyTyped()) {
                    char ch = StdDraw.nextKeyTyped();
                    if (ch == 'a')
                        success = t.goLeft(gameGrid);
                    if (ch == 'd')
                        success = t.goRight(gameGrid);

                    if (ch == 'c') {
                        success = t.rotateRight(gameGrid);


                    }
                    if (ch == 'z')
                        success = t.rotateLeft(gameGrid);


                    if (ch == 's')
                        success = t.goDown(gameGrid);

                    if (ch == 't') {
                        Font font = new Font("Arial", Font.BOLD, 50);
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.setFont(font);
                        StdDraw.text(8, 10, "Game Stopped");
                        Font font2 = new Font("Arial", Font.BOLD, 20);
                        StdDraw.setFont(font2);
                        StdDraw.text(8, 9, "press any key to continue");
                        StdDraw.show();
                        while (!StdDraw.hasNextKeyTyped()) {

                        }

                    }


                }


                // move the active tetromino down by one if a successful move left/right is not performed
                if (!success) {

                    success = t.goDown(gameGrid);

                }

                // place (stop) the active tetromino on the game grid if it cannot go down anymore

                createANewTetromino = !success;
                Point[] occupiedSquares;

                if (createANewTetromino) {
                    locOfNumsTemp = t.getArray();


                    // update the game grid by adding the placed tetromino
                    occupiedSquares = t.getOccupiedSquares();

                    Color[][] color = t.getColor();
                    for (int i = 0; i < locOfNumsTemp.length; i++) {
                        for (int j = 0; j < locOfNumsTemp[0].length; j++) {

                            locOfNums[i][j] += locOfNumsTemp[i][j];

                        }

                    }

                    t.locOfNumbers = locOfNums;
                    gameGrid.updateGrid(occupiedSquares, t);


//                    System.out.println("********************************************************************");
//                    for (int z = 0; z < t.locOfNumbers.length; z++) {
//                        for (int v = 0; v < t.locOfNumbers[0].length; v++) {
//                            System.out.print(t.locOfNumbers[z][v] + " ");
//                        }
//                        System.out.println();
//                    }


                    isGameOver = gameGrid.gameOver(occupiedSquares);
                    for (int i = 0; i < occupiedSquares.length; i++) {
                        //System.out.println("y= "+occupiedSquares[i].y+" x ="+occupiedSquares[i].x);
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.text(occupiedSquares[i].x, occupiedSquares[i].y, String.valueOf(finalArray[i]));
                    }
                    if (isGameOver) {
                        Font font = new Font("Arial", Font.BOLD, 50);
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.setFont(font);
                        StdDraw.text(8, 10, "Game Over");
                        font = new Font("Arial", Font.BOLD, 20);
                        StdDraw.setFont(font);
                        StdDraw.text(8, 9, "press r to restart");
                        StdDraw.show();
                        break;
                    }


                    // create the next tetromino to enter the game grid
                    count++;
                    t = new Tetromino(20, 16, count, Shapes);
                    newShape = t.getNewShape();
                    Shapes.add(newShape);


                }
                Color col = new Color(133, 125, 109);
                StdDraw.clear(col);
                // draw the game grid
                gameGrid.display();
                // draw the active tetromino
                t.display();
                Font font = new Font("Arial", Font.BOLD, 20);
                StdDraw.setPenColor(StdDraw.BLACK);
                t.displayNext();
                StdDraw.setFont(font);
                StdDraw.setPenColor(StdDraw.WHITE);
                StdDraw.text(17.8, 4.5, "next");
                StdDraw.text(17.8, 18, "Score:");
                StdDraw.text(17.8, 17.2, String.valueOf(gameGrid.score));
                // copy offscreen buffer to onscreen (double buffering)
                StdDraw.show();
                // pause for 200 ms (double buffering)

                StdDraw.pause(200);


            }
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                if (c == 'r') {
                    StdDraw.setCanvasSize(600, 750);
                    // set the scale of the coordinate system
                    StdDraw.setXscale(-0.5, 20);
                    StdDraw.setYscale(-0.5, 19.5);
                    // double buffering is used for speeding up drawing needed to enable computer animations
                    // check https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html for details
                    StdDraw.enableDoubleBuffering();
                    Shapes.clear();
                    gameGrid = new Grid(20, 16);
                    count = 0;
                    t = new Tetromino(20, 16, count);
                    locOfNums = new int[20][16];
                    locOfNumsTemp = new int[20][16];
                    newShape = t.getNewShape();
                    Shapes.add(newShape);
                    isGameOver = false;
                    isGamePaused = false;
                }

            }
        }
    }


}
