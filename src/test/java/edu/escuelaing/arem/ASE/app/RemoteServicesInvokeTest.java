package edu.escuelaing.arem.ASE.app;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class RemoteServicesInvokeTest {

    /**
     * Validar el funcionamiento ciclico del método RoundRobin
     */
    @Test
    public void testRoundRobin() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class<?> remoteServicesInvokeClass = RemoteServicesInvoke.class;


        Method roundRobinMethod = remoteServicesInvokeClass.getDeclaredMethod("roundRobin");

        roundRobinMethod.setAccessible(true);

        String url1 = (String) roundRobinMethod.invoke(null);
        String url2 = (String) roundRobinMethod.invoke(null);
        String url3 = (String) roundRobinMethod.invoke(null);
        String url4 = (String) roundRobinMethod.invoke(null);


        assertEquals("http://log2:46000", url1);
        assertEquals("http://log3:46000", url2);
        assertEquals("http://log1:46000", url3);
        assertEquals("http://log2:46000", url4);
    }
}
