import java.util.Scanner;

/**
 * This class takes input from the use to be use in the game of sticks.
 * @author S. Camilleri
 *
 */
public class Human {
    private Scanner input;
    private int playerNumber;

    public Human(Scanner input, int playerNumber)
    {
        this.input = input;
        this.playerNumber = playerNumber;
    }

    /**
     * Prompts the user until an integer representing the number of sticks to take is entered.
     * @param currentSticks The number of sticks currently on the board.
     * @return The number of sticks to be take from the table.
     */
    public int getMove(int currentSticks)
    {
        int move = 0;

        System.out.println("Player " + playerNumber + ": How many sticks do you take?");

        boolean inputIsInteger = false;
        while (!inputIsInteger)
        {
            try
            {
                move = Integer.parseInt(input.nextLine());
                inputIsInteger = true;
            }
            catch (NumberFormatException e)
            {
                inputIsInteger = false;
            }

        }
        return move;
    }
}


