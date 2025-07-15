import java.util.*;

class User {
    String username;
    String password;
    String name;
    String email;

    User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    boolean login(String uname, String pass) {
        return this.username.equals(uname) && this.password.equals(pass);
    }

    void updateProfile(Scanner sc) {
        System.out.print("Enter new name: ");
        this.name = sc.nextLine();
        System.out.print("Enter new email: ");
        this.email = sc.nextLine();
        System.out.println("Profile updated successfully.");
    }

    void changePassword(Scanner sc) {
        System.out.print("Enter current password: ");
        String oldPass = sc.nextLine();
        if (oldPass.equals(this.password)) {
            System.out.print("Enter new password: ");
            this.password = sc.nextLine();
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Incorrect password.");
        }
    }
}

class Question {
    String question;
    String[] options;
    char correct;

    Question(String q, String[] op, char ans) {
        question = q;
        options = op;
        correct = ans;
    }

    void display() {
        System.out.println(question);
        System.out.println("a) " + options[0]);
        System.out.println("b) " + options[1]);
        System.out.println("c) " + options[2]);
        System.out.println("d) " + options[3]);
    }

    boolean isCorrect(char userAnswer) {
        return Character.toLowerCase(userAnswer) == correct;
    }
}

class Exam {
    List<Question> questions = new ArrayList<>();
    int score = 0;

    Exam() {
        questions.add(new Question("What is the capital of India?", new String[]{"Mumbai", "Delhi", "Chennai", "Kolkata"}, 'b'));
        questions.add(new Question("Which keyword is used to inherit a class in Java?", new String[]{"extends", "this", "super", "implements"}, 'a'));
        questions.add(new Question("What is 10 + 20?", new String[]{"10", "20", "30", "40"}, 'c'));
    }

    void startExam(Scanner sc) {
        long startTime = System.currentTimeMillis();
        long totalTime = 60 * 1000; // 1 minute

        for (int i = 0; i < questions.size(); i++) {
            if (System.currentTimeMillis() - startTime >= totalTime) {
                System.out.println("\nTime's up! Auto-submitting your test...");
                break;
            }

            Question q = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ":");
            q.display();
            System.out.print("Your answer (a/b/c/d): ");
            char ans = sc.next().charAt(0);
            if (q.isCorrect(ans)) score++;
        }

        System.out.println("\nYou scored: " + score + " out of " + questions.size());
    }
}

public class onlineexamportal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Predefined user
        User user = new User("student01", "pass123", "John Doe", "john@example.com");

        System.out.println("=== Welcome to Online Exam Portal ===");

        boolean loggedIn = false;
        int attempts = 3;

        while (!loggedIn && attempts > 0) {
            System.out.print("Enter Username: ");
            String uname = sc.nextLine();
            System.out.print("Enter Password: ");
            String pass = sc.nextLine();

            if (user.login(uname, pass)) {
                loggedIn = true;
                System.out.println("Login successful.\n");
            } else {
                attempts--;
                System.out.println("Invalid credentials. Attempts left: " + attempts);
            }
        }

        if (!loggedIn) {
            System.out.println("Too many failed attempts. Exiting...");
            return;
        }

        int choice;
        do {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. Update Profile");
            System.out.println("2. Change Password");
            System.out.println("3. Start Exam");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    user.updateProfile(sc);
                    break;
                case 2:
                    user.changePassword(sc);
                    break;
                case 3:
                    Exam exam = new Exam();
                    exam.startExam(sc);
                    break;
                case 4:
                    System.out.println("Logging out");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);

        sc.close();
    }
}

