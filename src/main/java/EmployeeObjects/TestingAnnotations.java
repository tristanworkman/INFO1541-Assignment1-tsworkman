package EmployeeObjects;

//imports
import EmployeeBlueprints.Employee;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestingAnnotations
{
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(); //I create a NumberFormat instance for formating currency

    public static void main(String[] args) //main method
    {
        List<Employee> employeesList = startingInformation(); //I get list of starting employees

        classTest(employeesList); //call classTest and pass in employeesList

        //'Comparator.comparing(Employee::getEmployeeType)' is the same as '(a, b) -> a.getEmployeeType().compareTo(b.getEmployeeType())'
        employeesList.stream().sorted(Comparator.comparing(Employee::getEmployeeType)).forEach( //I wanted to sort the list, so I used streams and created a Comparator<? extends Employee> using method reference operator.
                employee -> { //for each employee, I get class (it is a subclass of Employee)
                    Class<? extends Employee> employeeClass = employee.getClass();
                    EmployeeType employeeTypeAnnotation = employeeClass.getAnnotation(EmployeeType.class); //I get the annotation of EmployeeType
                    Method[] methods = employeeClass.getDeclaredMethods(); //I also get all the methods.

                    System.out.println(employeeTypeAnnotation.type() + " Employee (" + employee.getEmployeeNumber() + " employee number)"); //I print out the type from the annotation value given with the employee number
                    try //try and catch
                    {
                        fieldTest(employeeClass, employee); //call fieldTest and pass in parameters
                        methodTest(methods, employee); //call methodTest and pass in parameters
                    } catch (IllegalAccessException | InvocationTargetException e)
                    {
                        System.out.println("Error: " + e.getMessage()); //print error message
                    }
                }
        );
/*  This also works for looping, but I wanted to use Lambdas because we went over it in learning materials
        for (Employee employee : employeesList) {
            Class<? extends Employee> employeeClass = employee.getClass();
            EmployeeType employeeTypeAnnotation = employeeClass.getAnnotation(EmployeeType.class);
            Method[] methods = employeeClass.getDeclaredMethods();

            System.out.println(employeeTypeAnnotation.type() + " Employee (" + employee.getEmployeeNumber() + " employee number)");
            fieldTest(employeeClass, employee);
            methodTest(methods, employee, employeeTypeAnnotation);
       }
 */
    }

    private static List<Employee> startingInformation()
    {
        List<Employee> employeesList = new ArrayList<>(); //employee list

        //I am creating employees
        CommissionEmployee commissionEmployee = new CommissionEmployee("Clint", "Barton", 6847, "Sales", "Customer Representative", .0265);
        HourlyEmployee hourlyEmployee = new HourlyEmployee("Tony", "Stark", 5749, "Service", "Lead Service Manager", 32.85);
        SalaryEmployee salaryEmployee = new SalaryEmployee("Steve", "Rodgers", 3781, "Sales", "Manager", 64325);
        //I am adding hours/sales and setting pay
        hourlyEmployee.increaseHours(40);
        hourlyEmployee.setPay(55);
        commissionEmployee.increaseSales(30);
        commissionEmployee.setPay(30);
        //I add the employees to the list and return the list.
        employeesList.add(commissionEmployee);
        employeesList.add(hourlyEmployee);
        employeesList.add(salaryEmployee);
        return employeesList;
    }
    private static void fieldTest(Class<? extends Employee> employeeClass, Employee employee) throws IllegalAccessException
    {
        Field[] fields = employeeClass.getDeclaredFields(); //get fields
        for (Field field : fields) { //loop through the fields
            if (field.isAnnotationPresent(PayRate.class)) { //if field has the annotation PayRate
                field.setAccessible(true); //probably do not need to do this, but just in case
                PayRate payRate = field.getAnnotation(PayRate.class); //I get the annotation
                //use a switch statement and store a string based on the type value of the annotation.
                String stringForPrint = switch (payRate.type()) {
                    case "Hourly" -> "Hourly Employee";
                    case "Salary" -> "Salary Employee";
                    case "Commission" -> "Commission Employee";
                    default -> ""; //if it is not one of the others. This will not ever be happened as Employee is an abstract class. I did not realize this at first
                };
                if (!stringForPrint.isEmpty()) { //if it is not empty, print value of field
                    System.out.printf(" - Pay Rate: %s\n", currencyFormat.format(field.get(employee))); //field.get(employee) gets the value of the field.
                } else {
                    System.out.println(" - Pay Rate Unknown!"); //just in case, if it is an empty string
                }
            }
        }
    }
    private static void methodTest(Method[] methods, Employee employee) throws InvocationTargetException, IllegalAccessException
    {
        for (Method method : methods) { //loop through methods
            if (method.isAnnotationPresent(WeeklyPayCalculator.class)) { //if method has this annotation
                method.setAccessible(true); //setAccessible to true. We do not really need this as the method is public, but I just put it here.
                double value = (double) method.invoke(employee); //I invoke the method and cast the result to a double
                System.out.println(" - Weekly Pay: " + currencyFormat.format(value)); //I print it.
            }
        }
    }
    private static void classTest(List<Employee> employeesList)
    {
        //I create count variables
        int count = 0;
        int hourlyEmployeeCount = 0;
        int salaryEmployeeCount = 0;
        int commissionEmployeeCount = 0;

        for (Employee employee : employeesList) { //loop through the employees
            if (employee.getClass().isAnnotationPresent(EmployeeType.class)) { //if annotation is present, get annotation
                EmployeeType employeeTypeAnnotation = employee.getClass().getAnnotation(EmployeeType.class);
                count++; //increment overall count
                switch (employeeTypeAnnotation.type()) { //based on the type, increment the variable
                    case  "Hourly": hourlyEmployeeCount++;
                        break;
                    case "Salary": salaryEmployeeCount++;
                        break;
                    case "Commission": commissionEmployeeCount++;
                        break;
                }
            }
        }
        //printf statement
        System.out.printf("You have %d employees (%d Hourly, %d Salary, %d Commission employees)\n\n", count, hourlyEmployeeCount, salaryEmployeeCount, commissionEmployeeCount);
    }
}
