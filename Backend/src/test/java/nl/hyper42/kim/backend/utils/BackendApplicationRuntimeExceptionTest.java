package nl.hyper42.kim.backend.utils;

import org.junit.Assert;
import org.junit.Test;

public class BackendApplicationRuntimeExceptionTest {

    @Test
    public void testApplicationRuntimeException() {
        try {
            throw new BackendApplicationRuntimeException();
        } catch (BackendApplicationRuntimeException are) {
            Assert.assertNull(are.getMessage());
            Assert.assertNull(are.getCause());
        }
    }

    @Test
    public void testApplicationRuntimeExceptionMessage() {
        try {
            throw new BackendApplicationRuntimeException("message");
        } catch (BackendApplicationRuntimeException are) {
            Assert.assertEquals("message", are.getMessage());
            Assert.assertNull(are.getCause());
        }
    }

    @Test
    public void testApplicationRuntimeExceptionSource() {
        try {
            throw new BackendApplicationRuntimeException(new RuntimeException());
        } catch (BackendApplicationRuntimeException are) {
            Assert.assertEquals("java.lang.RuntimeException", are.getCause().getClass().getName());
        }
    }

    @Test
    public void testApplicationRuntimeExceptionMessageSource() {
        try {
            throw new BackendApplicationRuntimeException("message", new RuntimeException());
        } catch (BackendApplicationRuntimeException are) {
            Assert.assertEquals("java.lang.RuntimeException", are.getCause().getClass().getName());
            Assert.assertEquals("message", are.getMessage());
        }
    }
}
