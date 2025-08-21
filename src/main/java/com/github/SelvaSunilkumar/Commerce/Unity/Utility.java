package com.github.SelvaSunilkumar.Commerce.Unity;

public class Utility {
    public static String checkIfItWorks() {
        return "Yes it is working as expected. Message from Unity";
    }

    public static String checkIfItWorks(String message) {
        return String.format("Yes it is working as expected. Message from Unity is %s", message);
    }
}
