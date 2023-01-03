import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MarketAlertUMMonitor {
    // User ID to monitor
    private final String userId;

    // Timer to schedule periodic checks of the system
    private final Timer timer;

    // Current state of the system
    private SystemState currentState;

    // Constructor to initialize the user ID and timer
    public MarketAlertUMMonitor(String userId) {
        this.userId = userId;
        timer = new Timer();
    }

    // Method to start monitoring the system
    public void start() {
        // Schedule a periodic task to check the system every 10 seconds
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    checkSystem();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 10000);
    }

    // Method to stop monitoring the system
    public void stop() {
        timer.cancel();
    }

    // Method to check the system and detect any bugs
    private void checkSystem() throws MalformedURLException {
        // Retrieve the latest event logs from the API
        List<EventLog> eventLogs = EventLogAPI.getEventLogs(userId);
        if (eventLogs.isEmpty()) {
            // No event logs - nothing to check
            return;
        }

        // Check the event logs and update the current state
        for (EventLog eventLog : eventLogs) {
            switch (eventLog.getEventLogType()) {
                case USER_VALID_LOGIN:
                    // Valid login event - update the current state
                    currentState = eventLog.getSystemState();
                    break;
                case USER_VIEWED_ALERTS:
                    // User viewed alerts event - check if the current state is consistent
                    if (currentState == null || !currentState.isLoggedIn()) {
                        // Inconsistent state - bug detected!
                        System.out.println("BUG DETECTED: User viewed alerts while not logged in");
                    }
                    break;
                case USER_LOGGED_OUT:
                    // User logged out event - update the current state
                    currentState = eventLog.getSystemState();
                    break;
            }
        }
    }
}



