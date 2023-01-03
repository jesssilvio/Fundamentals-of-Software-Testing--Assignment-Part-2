import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EventLogAPI {
    // Method to retrieve event logs from the API
    public static List<EventLog> getEventLogs(String userId) throws MalformedURLException {
        // Make a GET request to the API to retrieve the event logs for the specified user
        try {
            // Construct the URL for the API endpoint
            URL apiEndpoint = new URL("https://api.marketalertum.com/EventsLog/" + userId);

            // Open a connection to the API endpoint
            HttpURLConnection connection = (HttpURLConnection) apiEndpoint.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Set the Accept header to application/json to receive a JSON response
            connection.setRequestProperty("Accept", "application/json");

            // Connect to the API
            connection.connect();

            // Check the response code
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                // Response code is not OK - return an empty list
                return new ArrayList<>();
            }

            // Read the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response
            JSONArray eventLogsJson = new JSONArray(response.toString());

            // Convert the JSON event logs to EventLog objects
            List<EventLog> eventLogs = new ArrayList<>();
            for (int i = 0; i < eventLogsJson.length(); i++) {
                JSONObject eventLogJson = eventLogsJson.getJSONObject(i);
                String id = eventLogJson.getString("id");
                String timestamp = eventLogJson.getString("timestamp");
                EventLogTypes eventLogType = EventLogTypes.valueOf(eventLogJson.getString("eventLogType"));
                String eventLogUserId = eventLogJson.getString("userId");
                JSONObject systemStateJson = eventLogJson.getJSONObject("systemState");
                boolean isLoggedIn = systemStateJson.getBoolean("isLoggedIn");
                String loggedInUserId = systemStateJson.getString("loggedInUserId");
                SystemState systemState = new SystemState(isLoggedIn, loggedInUserId);
                EventLog eventLog = new EventLog(id, timestamp, eventLogType, eventLogUserId, systemState);
                eventLogs.add(eventLog);
            }

            // Return the list of event logs
            return eventLogs;
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
