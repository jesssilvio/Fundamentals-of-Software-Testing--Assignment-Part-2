import java.util.Scanner;

public class LoginLogoutSFA {
    // Constants for the different states of the SFA
    private static final int STATE_INITIAL = 0;
    private static final int STATE_LOGGED_OUT = 1;
    private static final int STATE_LOG_IN = 2;
    private static final int STATE_LOGGED_IN = 3;
    private static final int STATE_LOG_OUT = 4;

    public static void main(String[] args) {
        // Initialize the SFA in the initial state
        int state = STATE_INITIAL;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            switch (state) {
                case STATE_INITIAL:
                    // Transition to the logged out state
                    state = STATE_LOGGED_OUT;
                    break;
                case STATE_LOGGED_OUT:
                    System.out.println("Welcome to the website! You are currently logged out.");
                    System.out.println("1. Log in");
                    System.out.println("2. Exit");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    if (choice == 1) {
                        // Transition to the log in state
                        state = STATE_LOG_IN;
                    } else if (choice == 2) {
                        // Exit the SFA
                        return;
                    } else {
                        System.out.println("Invalid choice. Try again.");
                    }
                    break;
                case STATE_LOG_IN:
                    System.out.print("Enter your user ID to log in: ");
                    String userId = scanner.next();
                    // Check if the user ID is valid
                    if (isValidUserId(userId)) {
                        // Transition to the logged in state
                        state = STATE_LOGGED_IN;
                    } else {
                        System.out.println("Invalid user ID. Try again.");
                    }
                    break;
                case STATE_LOGGED_IN:
                    System.out.println("You are now logged in.");
                    System.out.println("1. View My Alerts");
                    System.out.println("2. Log out");
                    System.out.println("3. Exit");
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                    if (choice == 1) {
                        // View the Alerts page
                        viewMyAlerts();
                    } else if (choice == 2) {
                        // Transition to the log-out state
                        state = STATE_LOG_OUT;
                    } else if (choice == 3) {
                        // Exit the SFA
                        return;
                    } else {
                        System.out.println("Invalid choice. Try again.");
                    }
                    break;
                case STATE_LOG_OUT:
                    System.out.print("Enter your user ID to log out: ");
                    userId = scanner.next();
                    // Check if the user ID is valid
                    if (isValidUserId(userId)) {
                        // Transition to the logged out state
                        state = STATE_LOGGED_OUT;
                    } else {
                        System.out.println("Invalid user ID. Try again.");
                    }
                    break;
            }
        }
    }

    // Dummy method to simulate viewing the Alerts page
    private static void viewMyAlerts() {
        System.out.println("Viewing My Alerts...");
    }

    // Dummy method to check if a user ID is valid
    private static boolean isValidUserId(String userId) {
        // Assume all user IDs are valid for simplicity
        return true;
    }
}
