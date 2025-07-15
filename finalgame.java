import java.util.Random;
import java.util.Scanner;

public class finalgame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int totalScore = 0;

        System.out.println("🎯 Welcome to the Number Guessing Game!");
        System.out.println("You will play 5 rounds. Guess the number between 1 and 100.");

        for (int round = 1; round <= 5; round++) {
            int correctNumber = rand.nextInt(100) + 1; // Generate a new number between 1 and 100
            int attemptsLeft = 5;
            boolean guessedCorrectly = false;

            System.out.println("\n🔄 Round " + round + " - You have 5 attempts to guess the number.");

            do {
                System.out.print("Enter your guess (1-100): ");
                int guess = sc.nextInt();

                // Validate input
                if (guess < 1 || guess > 100) {
                    System.out.println("❗ Invalid input! Please enter a number between 1 and 100. Try again.");
                    continue; // Don't count invalid input as an attempt
                }

                attemptsLeft--;

                if (guess == correctNumber) {
                    guessedCorrectly = true;
                    int points = 100 - (5 - attemptsLeft - 1) * 20;
                    totalScore += points;
                    System.out.println("✅ Correct! You earned " + points + " points.");
                    break;
                } else if (guess < correctNumber) {
                    System.out.println("⬆️ Too low!");
                } else {
                    System.out.println("⬇️ Too high!");
                }

                if (attemptsLeft == 0) {
                    System.out.println("❌ You've run out of attempts! The correct number was: " + correctNumber);
                } else {
                    System.out.println("You have " + attemptsLeft + " attempts left.");
                }

            } while (attemptsLeft > 0);

            System.out.println("🔹 Total Score after Round " + round + ": " + totalScore);
        }

        System.out.println("\n🏁 Game Over! Your final score is: " + totalScore);
        sc.close();
    }
}

