package nl.hyper42.kim.offchain.utils;

import org.junit.Assert;
import org.junit.Test;

public class OffchainApplicationRuntimeExceptionTest {

    @Test
    public void testApplicationRuntimeException() {
        try {
            throw new OffchainApplicationRuntimeException();
        } catch (OffchainApplicationRuntimeException are) {
            Assert.assertNull(are.getMessage());
            Assert.assertNull(are.getCause());
        }
    }

    @Test
    public void testApplicationRuntimeExceptionMessage() {
        try {
            throw new OffchainApplicationRuntimeException("message");
        } catch (OffchainApplicationRuntimeException are) {
            Assert.assertEquals("message", are.getMessage());
            Assert.assertNull(are.getCause());
        }
    }

    @Test
    public void testApplicationRuntimeExceptionSource() {
        try {
            throw new OffchainApplicationRuntimeException(new RuntimeException());
        } catch (OffchainApplicationRuntimeException are) {
            Assert.assertEquals("java.lang.RuntimeException", are.getCause().getClass().getName());
        }
    }

    @Test
    public void testApplicationRuntimeExceptionMessageSource() {
        try {
            throw new OffchainApplicationRuntimeException("message", new RuntimeException());
        } catch (OffchainApplicationRuntimeException are) {
            Assert.assertEquals("java.lang.RuntimeException", are.getCause().getClass().getName());
            Assert.assertEquals("message", are.getMessage());
        }
    }
}
