import java.util.ArrayList;
import java.util.List;

public class MarketAlertUM {
    // Enum for the states of the SFSA
    public enum State {
        INITIAL,
        LOGGED_OUT,
        LOGGING_IN,
        LOGGED_IN,
        VIEWING_ALERTS,
        LOGGING_OUT
    }

    // Fields for the current state and user ID
    private State currentState;
    private String userId;

    // Constructor to set the initial state
    public MarketAlertUM() {
        currentState = State.INITIAL;
    }

    // Method to handle the "access website" event
    public void handleAccessWebsite() {
        if (currentState == State.INITIAL) {
            currentState = State.LOGGED_OUT;
        }
    }

    // Method to handle the "click Log In link" event
    public void handleClickLogInLink(String userId) {
        if (currentState == State.LOGGED_OUT) {
            this.userId = userId;
            currentState = State.LOGGING_IN;
            // API call to verify user's credentials
        }
    }

    // Method to handle the "log in success" event
    public void handleLogInSuccess() {
        if (currentState == State.LOGGING_IN) {
            currentState = State.LOGGED_IN;
        }
    }

    // Method to handle the "click My Alerts link" event
    public void handleClickMyAlertsLink() {
        if (currentState == State.LOGGED_IN) {
            currentState = State.VIEWING_ALERTS;
            // API call to retrieve user's alerts
        }
    }

    // Method to handle the "return to alerts page" event
    public void handleReturnToAlertsPage() {
        if (currentState == State.VIEWING_ALERTS) {
            currentState = State.LOGGED_IN;
        }
    }

    // Method to handle the "click Log Out link" event
    public void handleClickLogOutLink(String userId) {
        if (currentState == State.LOGGED_IN && this.userId.equals(userId)) {
            currentState = State.LOGGING_OUT;
        }
    }

    // Method to handle the "log out success" event
    public void handleLogOutSuccess() {
        if (currentState == State.LOGGING_OUT) {
            currentState = State.LOGGED_OUT;
            // API call to log out user
        }
    }

    // Method to get the current alerts for the user
    public List<String> getAlerts() {
        if (currentState == State.VIEWING_ALERTS) {
            return new ArrayList<>();
        }
        return null;
    }

    // Method to get the current state of the user
    public State getCurrentState() {
        return currentState;
    }

}
