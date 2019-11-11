package sample.model;

public class RungeKutta_local_error implements Local_error{

    private double x0, y0, h, T;

    public RungeKutta_local_error(double x, double y, double d){
        x0 = x;
        y0 = y;
        h = d;
    }

    public double get_T(double x1, double x2){
        Exact_solution exact = new Exact_solution(x0, y0);
        RungeKutta_method r_k = new RungeKutta_method(x0, y0, h);
        double source_y = exact.get_y(x1, exact.get_C());
        double k = r_k.get_k(x1, source_y);
        double k1 = r_k.get__k1(x1, source_y, k);
        double k2 = r_k.get__k2(x1, source_y, k1);
        double k3 = r_k.get__k3(x2, source_y, k2);
        double D = r_k.get_D(k, k1, k2, k3);
        T = Math.abs(exact.get_y(x2, exact.get_C())-r_k.get_y(source_y, D));
        return(T);
    }
}
