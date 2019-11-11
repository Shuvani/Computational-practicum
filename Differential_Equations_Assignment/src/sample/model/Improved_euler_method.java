package sample.model;

public class Improved_euler_method implements Solution {

    private double x0, y0, h, desired_y, D;

    public Improved_euler_method(double x, double y, double d){
        x0 = x;
        y0 = y;
        h = d;
    }

    public double get_k(double x, double source_y){
        double k = 1/x + (2*source_y)/(x*Math.log(x));
        return(k);
    }

    public double get__k1(double x, double source_y, double k){
        double y = source_y + h*k;
        double k1 = 1/x + (2*y)/(x*Math.log(x));
        return(k1);
    }

    public double get_D(double k, double k1){
        D = (h/2)*(k+k1);
        return D;
    }

    public double get_y(double source_y, double D){
        desired_y = source_y + D;
        return (desired_y);
    }
}
