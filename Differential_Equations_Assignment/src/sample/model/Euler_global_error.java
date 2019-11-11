package sample.model;

public class Euler_global_error implements Global_error {

    private double x0, y0, h, T;

    public Euler_global_error(double x, double y, double d){
        x0 = x;
        y0 = y;
        h = d;
    }

    public double get_T(double x1, double x2, double source_y){
        Exact_solution exact = new Exact_solution(x0, y0);
        Euler_method euler = new Euler_method(x0, y0, h);
        T = Math.abs(exact.get_y(x2, exact.get_C()) - euler.get_y(source_y, euler.get_k(x1, source_y)));
        return(T);
    }
}
