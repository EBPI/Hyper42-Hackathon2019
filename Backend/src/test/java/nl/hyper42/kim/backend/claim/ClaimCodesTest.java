package nl.hyper42.kim.backend.claim;

import org.junit.Assert;
import org.junit.Test;

public class ClaimCodesTest {

    @Test
    public void TestName() {
        Assert.assertEquals("EUCitizen", ClaimCodes.EUCitizen.name());
        Assert.assertEquals("FlyingBlueLevel", ClaimCodes.FlyingBlueLevel.name());
        Assert.assertEquals("OlderEightteen", ClaimCodes.OlderEightteen.name());
        Assert.assertEquals("OlderTwentyOne", ClaimCodes.OlderTwentyOne.name());
        Assert.assertEquals("TravelOutsideEU", ClaimCodes.TravelOutsideEU.name());
    }

    @Test
    public void TestValues() {
        Assert.assertEquals(5, ClaimCodes.values().length);
    }
}
