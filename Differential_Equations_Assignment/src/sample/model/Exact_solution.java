package sample.model;

import java.lang.*;

public class Exact_solution implements Solution {

    private double y0, x0, C, y;

    public Exact_solution(double x, double y){
        x0 = x;
        y0 = y;
    }

    public double get_C() {
        C = (y0 + Math.log(x0)) / (Math.pow(Math.log(x0), 2));
        return (C);
    }

    public double get_y(double x, double C){
        y = C*(Math.pow(Math.log(x), 2)) - Math.log(x);
        return (y);
    }
}