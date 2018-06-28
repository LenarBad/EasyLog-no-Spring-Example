package io.lenar.examples;

import org.junit.Test;

public class LoggerTest {

    @Test
    public void loggerTest() {
        new SomeClass().anotherMethodToBeLogged("ParamValue");
    }

}
