package sample.model;

public class RungeKutta_method implements Solution {

    private double x0, y0, h, desired_y, D;

    public RungeKutta_method(double x, double y, double d){
        x0 = x;
        y0 = y;
        h = d;
    }

    public double get_k(double x, double source_y){
        double k = 1/x + (2*source_y)/(x*Math.log(x));
        return(k);
    }

    public double get__k1(double x, double source_y, double k){
        double x2 = x + h/2;
        double y = source_y + (h/2)*k;
        double k1 = 1/x2 + (2*y)/(x2*Math.log(x2));
        return(k1);
    }

    public double get__k2(double x, double source_y, double k1){
        double x2 = x + h/2;
        double y = source_y + (h/2)*k1;
        double k2 = 1/x2 + (2*y)/(x2*Math.log(x2));
        return(k2);
    }

    public double get__k3(double x, double source_y, double k2){
        double y = source_y + h*k2;
        double k3 = 1/x + (2*y)/(x*Math.log(x));
        return(k3);
    }

    public double get_D(double k, double k1, double k2, double k3){
        D = (h/6)*(k+2*k1+2*k2+k3);
        return(D);
    }

    public double get_y(double source_y, double D){
        desired_y = source_y + D;
        return (desired_y);
    }
}
