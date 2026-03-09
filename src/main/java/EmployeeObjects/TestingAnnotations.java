package EmployeeObjects;

import EmployeeBlueprints.Employee;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestingAnnotations
{
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException
    {
        List<Employee> employeesList = startingInformation();

        classTest(employeesList);

        for (Employee employee : employeesList) {
            Class<? extends Employee> employeeClass = employee.getClass();
            EmployeeType employeeTypeAnnotation = employeeClass.getAnnotation(EmployeeType.class);
            Method[] methods = employeeClass.getDeclaredMethods();

            fieldTest(employeeClass, employee);
            methodTest(methods, employee, employeeTypeAnnotation);
        }
    }

    private static List<Employee> startingInformation()
    {
        CommissionEmployee commissionEmployee = new CommissionEmployee("Clint", "Barton", 6847, "Sales", "Customer Representative", .0265);
        HourlyEmployee hourlyEmployee = new HourlyEmployee("Tony", "Stark", 5749, "Service", "Lead Service Manager", 32.85);
        SalaryEmployee salaryEmployee = new SalaryEmployee("Steve", "Rodgers", 3781, "Sales", "Manager", 64325);
        hourlyEmployee.increaseHours(40);
        hourlyEmployee.setPay(50);
        commissionEmployee.increaseSales(20);
        commissionEmployee.setPay(50);
        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(commissionEmployee);
        employeesList.add(hourlyEmployee);
        employeesList.add(salaryEmployee);
        return employeesList;
    }
    private static void fieldTest(Class<? extends Employee> employeeClass, Employee employee) throws IllegalAccessException
    {
        Field[] fields = employeeClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(PayRate.class)) {
                field.setAccessible(true);
                PayRate payRate = field.getAnnotation(PayRate.class);

                String stringForPrint = switch (payRate.type()) {
                    case "Hourly" -> "Hourly Employee";
                    case "Salary" -> "Salary Employee";
                    case "Commission" -> "Commission Employee";
                    default -> "";
                };
                if (!stringForPrint.isEmpty()) {
                    System.out.printf("%s Pay Rate: $%s\n", stringForPrint, field.get(employee));
                } else {
                    System.out.println("Pay Rate Unknown!");
                }

            }
        }
    }
    private static void methodTest(Method[] methods, Employee employee, EmployeeType employeeTypeAnnotation) throws InvocationTargetException, IllegalAccessException
    {
        for (Method method : methods) {
            if (method.isAnnotationPresent(WeeklyPayCalculator.class)) {
                method.setAccessible(true);
                double value = (double) method.invoke(employee);
                System.out.println(employeeTypeAnnotation.type() + " Employee (" + employee.getEmployeeNumber() + " employee number) - Weekly Pay: $" + value);
            }
        }
    }
    private static void classTest(List<Employee> employeesList)
    {
        int count = 0;
        int hourlyEmployeeCount = 0;
        int salaryEmployeeCount = 0;
        int commissionEmployeeCount = 0;
        for (Employee employee : employeesList) {
            if (employee.getClass().isAnnotationPresent(EmployeeType.class)) {
                EmployeeType employeeTypeAnnotation = employee.getClass().getAnnotation(EmployeeType.class);
                count++;
                switch (employeeTypeAnnotation.type()) {
                    case  "Hourly": hourlyEmployeeCount++;
                        break;
                    case "Salary": salaryEmployeeCount++;
                        break;
                    case "Commission": commissionEmployeeCount++;
                        break;
                }
            }
        }
        System.out.printf("You have %d employee objects (%d Salary, %d Commission, and %d Hourly employees)\n", count, salaryEmployeeCount, commissionEmployeeCount, hourlyEmployeeCount);
    }
}
