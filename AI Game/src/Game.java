import java.util.Scanner;

/**
 * This class coordinates the AI and Human classes to play the game of sticks.
 * @author S. Camilleri
 *
 */
public class Game {
    private AI ai1;
    private AI ai2;
    private Human human1;
    private Human human2;
    private int initialSticks;
    private static int remove;
    private static int remove2;

    private static final String menu =
            "Menu" +
                    "\n1. Human plays Human" +
                    "\n2. Display AI-1" +
                    "\n3. Display AI-2" +
                    "\n4. Human trains AI-1" +
                    "\n5. Human plays AI-1" +
                    "\n6. AI-1 and AI-2 train each other 1 game" +
                    "\n7. AI-1 and AI-2 train each other 10000 games";

    private Scanner input = new Scanner(System.in);

    public Game()
    {
        human1 = new Human(input, 1);
        human2 = new Human(input, 2);

        ai1 = new AI();
        ai2 = new AI();
    }

    /**
     * Two human players play against one another.
     */
    public void playHumanVSHuman()
    {
        int sticks = initialSticks;

        while (true)
        {
            System.out.println("There are " + sticks + " sticks on the board.");
            int move = getLegalMove(sticks, human1);
            sticks -= move;
            if (sticks == 0)
            {
                System.out.println("Player 2 wins.");
                return;
            }

            System.out.println("There are " + sticks + " sticks on the board.");
            move = getLegalMove(sticks, human2);
            sticks -= move;
            if (sticks == 0)
            {
                System.out.println("Player 1 wins.");
                return;
            }
        }
    }

    /**
     * Repeatedly prompts a human player for a valid move.
     * @param sticks The number of sticks on the board.
     * @param human The human player object making the move.
     * @return The number of sticks for the first valid move entered.
     */
    public int getLegalMove(int sticks, Human human)
    {
        int move = -1;

        while ( !isLegalMove(sticks, move) )
        {
            move = human.getMove(sticks);

            if ( isLegalMove(sticks, move) )
            {
                return move;
            }
        }
        return -1;
    }

    /**
     * Determines if a move is legal given the number of sticks on the board.
     * @param sticks The number of sticks on the board.
     * @param move The number of sticks to take.
     * @return True if move is a valid number of sticks to take.
     */
    public boolean isLegalMove(int sticks, int move)
    {
        return (move >= 1 && move <= 3) && move <= sticks;
    }

    /**
     * This method trains an AI by playing a human.
     */
    public void humanTrainsAI()
    {
        // TODO: The Human plays and trains an AI object.

        int sticks = initialSticks;
        while (sticks >= 1) {
            System.out.println("Human, how many sticks do you want to remove? (1-3)");
            remove = Integer.parseInt(input.nextLine());
            sticks -= remove;
            System.out.println("There are now " + sticks + " sticks left.");
            if (sticks == 0) {
                System.out.println("You Lose!");
                ai1.winTrainingGame();
                break;
            }

            remove = ai1.getTrainingMove(sticks);
            sticks -= remove;
            System.out.println("AI removes " + remove + ". There are now " + sticks + " sticks left." );
            if (sticks == 0) {
                System.out.println("You Win!");
                ai1.loseTrainingGame();
                break;
            }
        }
    }

    /**
     * This method plays an AI against a human. Training doesn't occur.
     */
    public void humanPlaysAI()
    {
        int sticks = initialSticks;

        while (true)
        {
            System.out.println("There are " + sticks + " sticks.");
            int humanMove = human1.getMove(sticks);
            while (humanMove > 3 || humanMove < 1 || humanMove >= initialSticks) {
                System.out.println("Invalid choice! Try again (1-3)");
                humanMove = input.nextInt();
            }
            sticks -= humanMove;
            System.out.println("Human 1 takes " + humanMove + ".\n");
            if (sticks == 0)
            {
                System.out.println("AI-1 wins.");
                return;
            }

            System.out.println("There are " + sticks + " sticks.");
            int aiMove = ai1.getMove(sticks);
            sticks -= aiMove;
            System.out.println("AI-1 takes " + aiMove + ".\n");
            if (sticks == 0)
            {
                System.out.println("Player 1 wins.");
                return;
            }
        }
    }

    /**
     * This method trains both AIs against each other.
     */
    public void aiTrainsAI()
    {
        // TODO: An AI object plays and trains another AI object.
        int sticks = initialSticks;
        while (sticks >= 1) {
            remove = ai1.getTrainingMove(sticks);
            sticks -= remove;
            System.out.println("AI1 removes " + remove + ". There are now " + sticks + " sticks left." );
            if (sticks == 0) {
                System.out.println("AI2 Wins!");
                ai1.loseTrainingGame();
                ai2.winTrainingGame();
                break;
            }

            remove2 = ai2.getTrainingMove(sticks);
            sticks -= remove2;
            System.out.println("AI2 removes " + remove2 + ". There are now " + sticks + " sticks left." );
            if (sticks == 0) {
                System.out.println("AI1 Wins!");
                ai2.loseTrainingGame();
                ai1.winTrainingGame();
                break;
            }
        }
    }

    /**
     * Begins the game.
     */
    public void start()
    {
        // Establish the number of sticks to start the game with.
        initialSticks = -1;
        while (initialSticks < 10 || initialSticks > 100)
        {
            System.out.println("How many sticks are there on the board initially (10-100)?");
            try
            {
                initialSticks = Integer.parseInt(input.nextLine());
            } catch(NumberFormatException e)
            {
                System.out.println("Invalid number");
            }
        }

        // Menu loop
        int option;
        while (true)
        {
            System.out.println(menu);
            option = Integer.parseInt(input.nextLine());

            System.out.println("The game starts with " + initialSticks + " sticks on the board.");


            switch (option)
            {
                case 1:
                    playHumanVSHuman();
                    break;
                case 2:
                    ai1.printHats();
                    break;
                case 3:
                    ai2.printHats();
                    break;
                case 4:
                    humanTrainsAI();
                    break;
                case 5:
                    humanPlaysAI();
                    break;
                case 6:
                    aiTrainsAI();
                    break;
                case 7:
                    for (int i=0; i<10000; i++)
                        aiTrainsAI();
                    break;
            }
        }
    }
}


