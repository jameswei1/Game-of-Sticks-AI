import java.util.Random;

/**
 * This class stores, trains, and uses an AI to play the game of sticks.
 * @author S. Camilleri
 * @author
 *
 */
public class AI {

    private int[][] hats;
    private int[] table;
    private Random picker;

    public AI()
    {
        // TODO: Initialize hats, table, picker for gameplay.
        hats = new int[100][3];
        for (int hat = 0; hat < hats.length; hat++)
        {
            for (int ball = 0; ball < 3; ball++)
            {
                hats[hat][ball] = 1;
            }
        }

        table = new int[100];
        for (int i = 0; i < table.length; i ++) {
            table[i] = 0;
        }
        picker = new Random();
    }

    /**
     * This method is called before each training game.
     */
    public void startNewTrainingGame()
    {
        table = new int[100];
    }

    /**
     * This method is called when the AI has won a training game.
     */
    public void winTrainingGame()
    {
        // TODO: This AI has won, modify the hats accordingly.
        for (int i = 0; i < table.length; i ++) {
            if (table[i] == 1) {
                hats[i][0] += 1;
            }
            if (table[i] == 2) {
                hats[i][1] += 1;
            }
            if (table[i] == 3) {
                hats[i][2] += 1;
            }
        }
        table = new int[100];

    }

    /**
     * This method is called when the AI loses a training game.
     */
    public void loseTrainingGame()
    {
        // TODO: This AI has lost, modify the hats accordingly.
        for (int i = 0; i < table.length; i ++) {
            if (table[i] == 1 && hats[i][0] > 1) {
                hats[i][0] --;
            }
            if (table[i] == 2 && hats[i][1] > 1) {
                hats[i][1] --;
            }
            if (table[i] == 3 && hats[i][2] > 1) {
                hats[i][2] --;
            }
        }
        table = new int[100];
    }

    /**
     * This method returns a move from getMove() and saves the result to the table.
     * @param currentSticks The current number of sticks on the board.
     * @return A valid number of sticks that the AI takes on its move.
     */
    public int getTrainingMove(int currentSticks)
    {
        int move = getMove(currentSticks);
        table[currentSticks-1] = move;
        return move;
    }

    /**
     * This method returns the move made by the AI.
     * @param currentSticks The number of sticks on the board.
     * @return The number of sticks to take from the board.
     */
    public int getMove(int currentSticks)
    {
        int balls1 = hats[currentSticks-1][0];
        int balls2 = hats[currentSticks-1][1];
        int balls3 = hats[currentSticks-1][2];

        int choices = 0;
        if (currentSticks >= 3)
        {
            choices = balls1 + balls2 + balls3;
        }
        else if (currentSticks == 2)
        {
            choices = balls1 + balls2;
        }
        else
        {
            choices = balls1;
        }

        int pick = picker.nextInt(choices);
        int move = -1;

        if (pick < balls1)
        {
            move = 1;
        }
        else if (pick < balls1 + balls2)
        {
            move = 2;
        }
        else
        {
            move = 3;
        }

        return move;
    }

    /**
     * Prints the contents of the hats.
     */
    public void printHats()
    {
        String text = "AI\n" +
                "Ball\\Hat";

        for (int hat = 1; hat <= hats.length; hat++)
        {
            text += String.format("%5d", hat);
        }

        for (int ball = 0; ball<3; ball++)
        {
            text += "\n        ";

            for (int hat = 0; hat < hats.length; hat++)
            {
                text += String.format("%5d", hats[hat][ball]);
            }
        }

        System.out.println(text);
    }
}


