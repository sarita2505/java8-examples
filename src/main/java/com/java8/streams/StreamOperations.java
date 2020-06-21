package com.java8.streams;

import com.java8.streams.model.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class StreamOperations {
    private static final Employee[] EMPLOYEES = new Employee[4];

    static {
        Employee e1 = new Employee(1, "n1", 101.0, 29);
        Employee e2 = new Employee(2, "n2", 102.0, 26);
        Employee e3 = new Employee(3, "n3", 103.0, 21);
        Employee e4 = new Employee(4, "n4", 104.0, 22);
        EMPLOYEES[0] = e1;
        EMPLOYEES[1] = e2;
        EMPLOYEES[2] = e3;
        EMPLOYEES[3] = e4;
    }

    public static void main(String[] args) {
        //m6();
        // m4();
        //m3();
        //m2();
        //   m1();
        // m5();
//m7();
  //      m8();
        m9();
    }
    private static void m9(){

        Function<Integer,Integer>  f1=(i)->{
            return i*i;
        };
        Function<Integer,Integer>  f2=(i)->{
            return i*2;
        };
        Function function=f1.andThen(f2);
        System.out.println(function.apply(3));
        Function function1=f1.compose(f2).compose(f2);
        System.out.println(function1.apply(3));
    }

    private static void m8() {
        UnaryOperator<Employee> function = (e) -> {
            e.name = "sarita";
            return e;
        };
        Employee employee = function.apply(Employee.getStaticEmployee());
        System.out.println(employee);


    }

    private static void m7() {
        Function<Employee[], List<Employee>> function = (e) -> {
            List<Employee> list = new ArrayList<>();
            for (Employee employee : e) {
                if (employee.age > 25) {
                    list.add(employee);
                }
            }
            return list;
        };
        List<Employee> list = function.apply(EMPLOYEES);
        System.out.println(list);
    }

    private static void m6() {
        displaySupplier(new DisplaySupplier());
        displaySupplier(() -> {
            return Employee.getStaticEmployee();
        });
        Supplier<Employee> employeeSupplier = () -> {
            return Employee.getStaticEmployee();
        };
        displaySupplier(employeeSupplier);
    }

    private static void m5() {
        Stream<Employee> employeeStream = Stream.of(EMPLOYEES);
        displayParallerStream(employeeStream);
    }

    private static void m4() {
        Stream<Employee> employeeStream = Stream.<Employee>builder().add(EMPLOYEES[0]).add(EMPLOYEES[1]).build();
        displayDot_LineStream(employeeStream);
    }

    private static void m3() {
        Stream<Employee> employeeStream = Stream.<Employee>builder().add(EMPLOYEES[0]).add(EMPLOYEES[1]).build();
        displayDotStream(employeeStream);
    }

    private static void m2() {
        Stream<Employee> employeeStream = Stream.<Employee>builder().add(EMPLOYEES[0]).add(EMPLOYEES[1]).build();
        displayStream(employeeStream);
    }

    private static void m1() {
        Stream<Employee> employeeStream = Stream.of(EMPLOYEES);
        displayStream(employeeStream);
    }


    private static void displayStream(Stream stream) {
        Consumer<Employee> consumer = (e) -> {
            // e.id = 100;
            System.out.println(e);
        };

        stream.forEach(consumer);
    }

    private static void displayParallerStream(Stream stream) {
        Consumer<Employee> consumer = (e) -> {
            // e.id = 100;
            System.out.println(e);
        };
        //stream.forEach(consumer);
        //stream.forEachOrdered(consumer);
        stream = (Stream) stream.parallel();
        // stream.forEachOrdered(consumer);
        stream.forEach(consumer);
    }

    private static void displayDot_LineStream(Stream stream) {
        Consumer dc = new DisplayDotConsumer();
        Consumer dl = new DisplayLineConsumer();
        Consumer consumer = dl.andThen(dl);
        stream.forEach(consumer);
    }

    private static void displayDotStream(Stream stream) {
        Consumer c = new DisplayDotConsumer();

        stream.forEach(c);
    }

    private static void displaySupplier(Supplier supplier) {
        System.out.println(supplier.get());
    }

    private static final class DisplayDotConsumer implements Consumer<Employee> {
        @Override
        public void accept(Employee employee) {
            System.out.println(employee + "...");
        }
    }

    private static final class DisplayLineConsumer implements Consumer<Employee> {
        @Override
        public void accept(Employee employee) {
            System.out.println(employee + "---");
        }
    }

    private static final class DisplaySupplier implements Supplier<Employee> {
        @Override
        public Employee get() {
            return new Employee(1, "harish", 200.0, 22);
        }
    }
}

