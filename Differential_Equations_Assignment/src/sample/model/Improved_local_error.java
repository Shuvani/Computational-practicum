package sample.model;

public class Improved_local_error implements Local_error {

    private double x0, y0, h, T;

    public Improved_local_error(double x, double y, double d){
        x0 = x;
        y0 = y;
        h = d;
    }

    public double get_T(double x1, double x2){
        Exact_solution exact = new Exact_solution(x0, y0);
        Improved_euler_method i_e = new Improved_euler_method(x0, y0, h);
        double source_y = exact.get_y(x1, exact.get_C());
        double k = i_e.get_k(x1, source_y);
        double D = i_e.get_D(k, i_e.get__k1(x2, source_y, k));
        T = Math.abs(exact.get_y(x2, exact.get_C())- i_e.get_y(source_y, D));
        return(T);
    }
}
