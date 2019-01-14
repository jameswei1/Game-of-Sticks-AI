import java.util.Scanner;
import java.util.Random;

public class Main {
    static int remove = 0;
    static int totalProb = 0;
    static double oneBall = 0.00;
    static double twoBall = 0.00;
    static double threeBall = 0.00;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Game of Sticks. Would you like to play against a Friend[1] or Computer[2]?");
        int opponent = input.nextInt();

        if (opponent == 1) {
            System.out.println("Welcome to Game of Sticks. How many sticks would you like to start out with? (10-100)");
            int total = input.nextInt();
            while (total > 100 || total < 10) {
                System.out.println("Invalid Choice! Please try again (10 - 100)");
                total = input.nextInt();
            }
            boolean playing = true;
            int turn = 0;

            while (playing) {
                if (total == 1) {
                    if (turn %2 == 0) {
                        System.out.println( "There is 1 stick left in the game. Player 2 Wins! Game over");
                        break;
                    }

                    if (turn %2 == 1) {
                        System.out.println("There is 1 stick left in the game. Player 1 Wins! Game over");
                        break;
                    }
                }

                else if (turn %2 == 0) {
                    System.out.println("There are currently " + total + " sticks in the game. Player 1 , how many sticks would you like to remove? (1-3)");
                    int remove = input.nextInt();
                    while (remove != 1 && remove != 2 && remove != 3 || remove >= total) {
                        System.out.println("Invalid choice! Please try again");
                        remove = input.nextInt();
                    }
                    total -= remove;
                    turn ++;
                }

                else if (turn % 2 == 1) {
                    System.out.println("There are currently " + total + " sticks in the game. Player 2, how many sticks would you like to remove? (1-3)");
                    int remove = input.nextInt();
                    while (remove != 1 && remove != 2 && remove != 3 || remove >= total) {
                        System.out.println("Invalid choice! Please try again");
                        remove = input.nextInt();
                    }
                    total -= remove;
                    turn ++;
                }
            }
        }

        else if (opponent == 2) {
            System.out.println("Player 1, how many sticks would you like to start with? (10 - 100)");
            int total = input.nextInt();
            while (total > 100 || total < 10) {
                System.out.println("Invalid Choice! Please try again (10 - 100)");
                total = input.nextInt();
            }

            boolean playing = true;
            int turn = 0;
            int balls = 3;


            int[][] hats = new int[balls][total];
            for (int i = 0; i < hats.length; i ++) {
                for (int k = 0; k < hats[i].length; k ++) {
                    hats[i][k] = 1;
                }
            }
//        	print(hats);
//            int moves = 0;
//            int[][] choice = new int[100][2];

            while (playing) {
                if (total == 1) {
                    if (turn %2 == 0) {
                        System.out.println( "There is 1 stick left in the game. You Lose! Game over");
                        print(hats);
                        break;
                    }

                    if (turn %2 == 1) {
                        System.out.println("There is 1 stick left in the game. You Win! Game over");
                        print(hats);
                        break;
                    }
                }

                if (turn %2 == 0) {
                    System.out.println("There are currently " + total + " sticks in the game. Player 1, how many sticks would you like to remove?");
                    int remove = input.nextInt();
                    while (remove != 1 && remove != 2 && remove != 3 || remove >= total) {
                        System.out.println("Invalid choice! Please try again");
                        remove = input.nextInt();
                    }
                    total -= remove;
                    turn ++;
//                    int totalProb = total(hats, total);
//                    System.out.print(hats[1][total]/totalProb);
//                    System.out.println(totalProb);
//                    System.out.print(randomDecimal(0.00, 1.00));
//                    break;

                }

                else if (turn %2 == 1) {
                    totalProb = total(hats, total);
                    System.out.println(totalProb);
                    System.out.println("hats[0][total] is " + hats[0][total]);
                    double n = randomDecimal(0.00, 1.00);
                    System.out.println("n is " + n);

                    oneBall = (double)hats[0][total]/totalProb;
                    twoBall = (double)hats[1][total]/totalProb;
                    threeBall = (double)hats[2][total]/totalProb;
                    System.out.print("OneBall is " + oneBall);
                    System.out.println("OneBall should be " + hats[0][total]/totalProb);
                    System.out.print("twoBall is " + twoBall);
                    System.out.println("twoBall should be " + hats[1][total]/totalProb);
                    System.out.println("threeBall is " + threeBall);
                    System.out.println("threeBall should be " + hats[2][total]/totalProb);

                    if (n < oneBall ) {
                        remove = 1;
                        System.out.println("There are currently " + total + " sticks in the Game. AI removes " + remove + ".");
                        total -= remove;
                        turn ++;
                        totalProb = 0;
//                        moves += 2;
                    }
                    else if (n > oneBall && n < (oneBall + twoBall)) {
                        remove = 2;
                        System.out.println("There are currently " + total + " sticks in the Game. AI removes " + remove + ".");
                        total -= remove;
                        turn ++;
                        totalProb = 0;
//                        moves += 2;
                    }
                    else if (n > (oneBall + twoBall)) {
                        remove = 3;
                        System.out.println("There are currently " + total + " sticks in the Game. AI removes " + remove + ".");
                        total -= remove;
                        turn ++;
                        totalProb = 0;
//                        moves += 2;
                    }
                }
            }
        }
    }

    public static void print(int[][] list) {
        for (int i = 0; i < list.length; i ++) {
            for (int j = 0; j <list[i].length; j ++ ) {
                System.out.print(list[i][j] + ", ");
            }
            System.out.println();
        }
    }

    public static int total(int[][] list, int sticks) {
        for (int i = 0; i <= 2; i ++) {
            totalProb += list[i][sticks];
        }
        return totalProb;
    }

    public static double randomDecimal(double min, double max) {
        Random r = new Random();
        return (r.nextInt((int)((max-min)*10+1))+min*10) / 10.0;
    }
}
