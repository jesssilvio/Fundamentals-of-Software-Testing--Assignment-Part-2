public class SystemState {
    private final boolean isLoggedIn;

    public SystemState(boolean isLoggedIn, String loggedInUserId) {
        this.isLoggedIn = isLoggedIn;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
