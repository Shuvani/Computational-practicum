package sample.model;

public class Improved_global_error implements Global_error{

    private double x0, y0, h, T;

    public Improved_global_error(double x, double y, double d){
        x0 = x;
        y0 = y;
        h = d;
    }

    public double get_T(double x1, double x2, double source_y){
        Exact_solution exact = new Exact_solution(x0, y0);
        Improved_euler_method i_e = new Improved_euler_method(x0, y0, h);
        double k = i_e.get_k(x1, source_y);
        double k1 = i_e.get__k1(x2, source_y, k);
        double D = i_e.get_D(k, k1);
        T = Math.abs(exact.get_y(x2, exact.get_C()) - i_e.get_y(source_y, D));
        return(T);
    }
}
