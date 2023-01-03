import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MarketAlertUMTest {
    private MarketAlertUM marketAlertUM;
    private String userId;

    @Before
    public void setup() {
        marketAlertUM = new MarketAlertUM();
        userId = "4414c849-bd0e-4f98-9bd4-405c4d6df075";
    }

    @Test
    public void testInitialState() {
        assertEquals(MarketAlertUM.State.INITIAL, marketAlertUM.getCurrentState());
    }

    @Test
    public void testAccessWebsite() {
        marketAlertUM.handleAccessWebsite();
        assertEquals(MarketAlertUM.State.LOGGED_OUT, marketAlertUM.getCurrentState());
    }

    @Test
    public void testClickLogInLink() {
        marketAlertUM.handleClickLogInLink(userId);
        assertEquals(MarketAlertUM.State.LOGGING_IN, marketAlertUM.getCurrentState());
    }

    @Test
    public void testLogInSuccess() {
        marketAlertUM.handleLogInSuccess();
        assertEquals(MarketAlertUM.State.LOGGED_IN, marketAlertUM.getCurrentState());
    }

    @Test
    public void testClickMyAlertsLink() {
        marketAlertUM.handleClickMyAlertsLink();
        assertEquals(MarketAlertUM.State.VIEWING_ALERTS, marketAlertUM.getCurrentState());
    }

    @Test
    public void testReturnToAlertsPage() {
        marketAlertUM.handleReturnToAlertsPage();
        assertEquals(MarketAlertUM.State.LOGGED_IN, marketAlertUM.getCurrentState());
    }

    @Test
    public void testClickLogOutLink() {
        marketAlertUM.handleClickLogOutLink(userId);
        assertEquals(MarketAlertUM.State.LOGGING_OUT, marketAlertUM.getCurrentState());
    }

    @Test
    public void testLogOutSuccess() {
        marketAlertUM.handleLogOutSuccess();
        assertEquals(MarketAlertUM.State.INITIAL, marketAlertUM.getCurrentState());
    }

    @Test
    public void testGetAlerts() {
        assertNull(marketAlertUM.getAlerts());
    }
}
