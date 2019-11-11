package sample;

import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Button;
import sample.model.*;

public class Controller implements Initializable {

    double x0=2, y0=0, n=10, X=12, h=1;

    @FXML
    //The field for changing x0 by user
    private JFXTextField Change_x0;

    @FXML
    //The field for changing y0 by user
    private JFXTextField Change_y0;

    @FXML
    //The field for changing number of steps by user
    private JFXTextField Change_n;

    @FXML
    //The field for changing right sight of the interval by user
    private JFXTextField Change_X;

    @FXML
    //the linechart to plot the graphs of the solutions
    javafx.scene.chart.LineChart<Number, Number> Graphs_of_solutions;

    @FXML
    //the linechart to plot the graphs of the local errors
    javafx.scene.chart.LineChart<Number, Number> Graphs_of_local_errors;

    @FXML
    //the linechart to plot the graphs of the global errors
    javafx.scene.chart.LineChart<Number, Number> Graphs_of_global_errors;

    @FXML
    //Button to plot the graph of the exact solution on the linechart Graphs_of_solutions
    private Button Button_exact_solution;

    @FXML
    //Button to plot the graph of the euler solution on the linechart Graphs_of_solutions
    private Button Button_eulers_method;

    @FXML
    //Button to plot the graph of the improved euler solution on the linechart Graphs_of_solutions
    private Button Button_improved_euler_method;

    @FXML
    //Button to plot the graph of the runge-kutta solution on the linechart Graphs_of_solutions
    private Button Button_Runge_Kutta_method;

    @FXML
    //Button to plot the graph of the euler local error on the linechart Graphs_of_local_errors
    private Button Button_euler_local;

    @FXML
    //Button to plot the graph of the improved euler local error on the linechart Graphs_of_local_errors
    private Button Button_improved_local;

    @FXML
    //Button to plot the graph of the runge-kutta local error on the linechart Graphs_of_local_errors
    private Button Button_rungekutta_local;

    @FXML
    //Button to plot the graph of the euler global error on the linechart Graphs_of_global_errors
    private Button Button_euler_global;

    @FXML
    //Button to plot the graph of the improved euler global error on the linechart Graphs_of_global_errors
    private Button Button_improved_global;

    @FXML
    //Button to plot the graph of the runge-kutta global error on the linechart Graphs_of_global_errors
    private Button Button_rungekutta_global;

    public void change(JFXTextField field){
        //show to the user the angry message if the input is not valid
        field.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue){
                if(!newValue){
                    field.validate();
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        DoubleValidator Validator = new DoubleValidator();
        Validator.setMessage("Only numbers");
        Change_x0.getValidators().add(Validator);
        change(Change_x0);
        Change_y0.getValidators().add(Validator);
        change(Change_y0);
        Change_n.getValidators().add(Validator);
        change(Change_n);
        Change_X.getValidators().add(Validator);
        change(Change_X);
    }

    public void button_c_c(ActionEvent event){
        x0 = Double.valueOf(Change_x0.getText());
        y0 = Double.valueOf(Change_y0.getText());
        n = Double.valueOf(Change_n.getText());
        X = Double.valueOf(Change_X.getText());
        h = (X-x0)/n;
    }

    public void button_e_s(ActionEvent event){
        Exact_solution solution = new Exact_solution(x0, y0);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(x0, y0));
        for (double i=x0+h; i<=X; i=i+h) {
            series.getData().add(new XYChart.Data<>(i, solution.get_y(i, solution.get_C())));
        }
        series.setName("Exact solution");
        Graphs_of_solutions.getData().addAll(series);
        Button_exact_solution.setDisable(true);
    }

    public void button_e_m(ActionEvent event){
        double source_y = y0;
        Euler_method euler = new Euler_method(x0, y0, h);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(x0, y0));
        for (double i=x0+h; i<=X; i=i+h) {
            series.getData().add(new XYChart.Data<>(i, euler.get_y(source_y, euler.get_k((i-h), source_y))));
            source_y = euler.get_y(source_y, euler.get_k((i-h), source_y));
        }
        series.setName("Euler's method");
        Graphs_of_solutions.getData().addAll(series);
        Button_eulers_method.setDisable(true);
    }

    public void button_i_e_m(ActionEvent event){
       double source_y = y0;
       Improved_euler_method i_euler = new Improved_euler_method(x0, y0, h);
       XYChart.Series<Number, Number> series = new XYChart.Series<>();
       series.getData().add(new XYChart.Data<>(x0, y0));
        for (double i=x0+h; i<=X; i=i+h) {
            double k = i_euler.get_k((i-h), source_y);
            double D = i_euler.get_D(k, i_euler.get__k1(i, source_y, k));
            series.getData().add(new XYChart.Data<>(i, i_euler.get_y(source_y, D)));
            source_y = i_euler.get_y(source_y, D);
        }
        series.setName("Improved Euler");
        Graphs_of_solutions.getData().addAll(series);
        Button_improved_euler_method.setDisable(true);
    }

    public void button_r_k_m(ActionEvent event){
        double source_y = y0;
        RungeKutta_method r_k = new RungeKutta_method(x0, y0, h);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(x0, y0));
        for (double i=x0+h; i<=X; i=i+h) {
            double k = r_k.get_k((i-h), source_y);
            double k1 = r_k.get__k1((i-h), source_y, k);
            double k2 = r_k.get__k2((i-h), source_y, k1);
            double k3 = r_k.get__k3(i, source_y, k2);
            double D = r_k.get_D(k, k1, k2, k3);
            series.getData().add(new XYChart.Data<>(i, r_k.get_y(source_y, D)));
            source_y = r_k.get_y(source_y, D);
        }
        series.setName("Runge-Kutta");
        Graphs_of_solutions.getData().addAll(series);
        Button_Runge_Kutta_method.setDisable(true);
    }

    public void button_clear_all(ActionEvent event){
        Graphs_of_solutions.getData().clear();
        Button_exact_solution.setDisable(false);
        Button_eulers_method.setDisable(false);
        Button_improved_euler_method.setDisable(false);
        Button_Runge_Kutta_method.setDisable(false);
    }

    public void button_e_l_e(ActionEvent event){
        Euler_local_error e_l_e = new Euler_local_error(x0, y0, h);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(x0, 0));
        for (double i=x0+h; i<=X; i=i+h) {
            series.getData().add(new XYChart.Data<>(i, e_l_e.get_T(i-h, i)));
        }
        series.setName("Euler");
        Graphs_of_local_errors.getData().addAll(series);
        Button_euler_local.setDisable(true);
    }

    public void button_i_e_l_e(ActionEvent event){
        Improved_local_error i_e_l_e = new Improved_local_error(x0, y0, h);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(x0, 0));
        for (double i=x0+h; i<=X; i=i+h) {
            series.getData().add(new XYChart.Data<>(i, i_e_l_e.get_T(i-h, i)));
        }
        series.setName("Improved Euler");
        Graphs_of_local_errors.getData().addAll(series);
        Button_improved_local.setDisable(true);
    }

    public void button_r_k_l_e(ActionEvent event){
        RungeKutta_local_error r_k_l_e = new RungeKutta_local_error(x0, y0, h);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(x0, 0));
        for (double i=x0+h; i<=X; i=i+h) {
            series.getData().add(new XYChart.Data<>(i, r_k_l_e.get_T(i-h, i)));
        }
        series.setName("Runge-Kutta");
        Graphs_of_local_errors.getData().addAll(series);
        Button_rungekutta_local.setDisable(true);
    }

    public void button_clear_all_local(ActionEvent event){
        Graphs_of_local_errors.getData().clear();
        Button_euler_local.setDisable(false);
        Button_improved_local.setDisable(false);
        Button_rungekutta_local.setDisable(false);
    }

    public void button_e_g_e(ActionEvent event){
        double source_y = y0;
        Euler_method euler = new Euler_method(x0, y0, h);
        Euler_global_error e_g_e = new Euler_global_error(x0, y0, h);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(x0, 0));
        for (double i=x0+h; i<=X; i=i+h) {
            series.getData().add(new XYChart.Data<>(i, e_g_e.get_T(i-h, i, source_y)));
            source_y = euler.get_y(source_y, euler.get_k((i-h), source_y));
        }
        series.setName("Euler");
        Graphs_of_global_errors.getData().addAll(series);
        Button_euler_global.setDisable(true);
    }

    public void button_i_e_g_e(ActionEvent event){
        double source_y = y0;
        Improved_euler_method i_e = new Improved_euler_method(x0, y0, h);
        Improved_global_error i_g_e = new Improved_global_error(x0, y0, h);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(x0, 0));
        for (double i=x0+h; i<=X; i=i+h) {
            double k = i_e.get_k((i-h), source_y);
            double k1 = i_e.get__k1(i, source_y, k);
            double D = i_e.get_D(k, k1);
            series.getData().add(new XYChart.Data<>(i, i_g_e.get_T(i-h, i, source_y)));
            source_y = i_e.get_y(source_y, D);
        }
        series.setName("Improved");
        Graphs_of_global_errors.getData().addAll(series);
        Button_improved_global.setDisable(true);
    }

    public void button_r_k_g_e(ActionEvent event){
        double source_y = y0;
        RungeKutta_method r_k = new RungeKutta_method(x0, y0, h);
        RungeKutta_global_error r_k_g_e = new RungeKutta_global_error(x0, y0, h);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(x0, 0));
        for (double i=x0+h; i<=X; i=i+h) {
            double k = r_k.get_k((i-h), source_y);
            double k1 = r_k.get__k1(i-h, source_y, k);
            double k2 = r_k.get__k2(i-h, source_y, k1);
            double k3 = r_k.get__k3(i, source_y, k2);
            double D = r_k.get_D( k, k1, k2, k3);
            series.getData().add(new XYChart.Data<>(i, r_k_g_e.get_T(i-h, i, source_y)));
            source_y = r_k.get_y(source_y, D);
        }
        series.setName("Runge-Kutta");
        Graphs_of_global_errors.getData().addAll(series);
        Button_rungekutta_global.setDisable(true);
    }

    public void button_clear_all_global(ActionEvent event){
        Graphs_of_global_errors.getData().clear();
        Button_euler_global.setDisable(false);
        Button_improved_global.setDisable(false);
        Button_rungekutta_global.setDisable(false);
    }

}
