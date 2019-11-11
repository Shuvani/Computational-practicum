package sample.model;

public class Euler_method implements Solution {

    private double x0, y0, h, desired_y;

    public Euler_method(double x, double y, double d){
        x0 = x;
        y0 = y;
        h = d;
    }

    public double get_k(double x, double source_y){
        double f = 1/x + (2*source_y)/(x*Math.log(x));
        return(f);
    }

    public double get_y(double source_y, double k){
        desired_y = source_y + h*k;
        return (desired_y);
    }
}
