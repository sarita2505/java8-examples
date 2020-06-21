package com.java8.streams.model;

public class Employee {
    public Integer id;
    public String name;
    public Double salary;
    public Integer age;

    public static Employee getStaticEmployee() {
        return new Employee(1, "giri", 300.1, 22);
    }

    public Employee() {
    }

    public Employee(Integer id, String name, Double salary, Integer age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
