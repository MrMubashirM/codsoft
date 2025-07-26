import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain;

        do {
            int attempts = 0;
            int score = 0;
            int maxAttempts = 5; // Limit the number of attempts
            int numberToGuess = random.nextInt(100) + 1; // Generate random number (1-100)

            System.out.println("Guess the number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess the correct number.");

            boolean guessedCorrectly = false;
            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < numberToGuess) {
                    System.out.println("Too low!");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high!");
                } else {
                    guessedCorrectly = true;
                    System.out.println("Congratulations! You've guessed the correct number: " + numberToGuess);
                    score = maxAttempts - attempts + 1; // Calculate score based on attempts
                    System.out.println("Your score for this round: " + score);
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry! You've used all attempts. The correct number was: " + numberToGuess);
            }

            // Ask if the user wants to play again
            System.out.print("Do you want to play again? (true/false): ");
            playAgain = scanner.nextBoolean();

        } while (playAgain);

        System.out.println("Thank you for playing!");
        scanner.close();
    }
}
