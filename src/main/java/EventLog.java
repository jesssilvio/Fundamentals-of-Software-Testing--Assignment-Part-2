class EventLog {
    private final String id;
    private final String timestamp;
    private final EventLogTypes eventLogType;
    private final String userId;
    private final SystemState systemState;

    public EventLog(String id, String timestamp, EventLogTypes eventLogType, String userId, SystemState systemState) {
        this.id = id;
        this.timestamp = timestamp;
        this.eventLogType = eventLogType;
        this.userId = userId;
        this.systemState = systemState;
    }

    public String getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public EventLogTypes getEventLogType() {
        return eventLogType;
    }

    public String getUserId() {
        return userId;
    }

    public SystemState getSystemState() {
        return systemState;
    }
}