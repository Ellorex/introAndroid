package com.aston_ecole.helloworld;

public class TemperatureConverter {
    public static String farFromCel(double celcius) {
        return Double.valueOf((9.0/5.0)*celcius+32.0).toString();
    }
    public static String celFromFar(double fahrenheit) {
        return Double.valueOf((5.0/9.0)*fahrenheit-32.0).toString();
    }
}
