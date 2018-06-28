package io.lenar.examples;

import io.lenar.easy.log.annotations.LogIt;

public class SomeClass {


    public String toBeLogged(String param) {
        return param + " :: " + param;
    }

    @LogIt
    public String anotherMethodToBeLogged(String param) {
        return param + " :!!!: " + param;
    }

}
