package EmployeeObjects;


import EmployeeBlueprints.Employee;
import EmployeeBlueprints.EmployeeType;

/**
 * Object used to represent the Salary employee in the system.
 * @author lhartman2
 * @version 1.0.1
 */
public final class SalaryEmployee extends Employee {
    
    private double salary;

    /**
     * Main constructor that sets up the Employee
     * @param fn String for the first name
     * @param ln String for the last name
     * @param en int for the employee number
     * @param dept String for the department worked in
     * @param job String for the job title
     * @param s double for the salary
     */
    public SalaryEmployee(String fn, String ln, int en, String dept, String job, double s)
    {
        super(fn, ln, en, dept, job, EmployeeType.SALARY);
        salary = s;
    }

    /**
     * Calculates the weekly pay of the employee. salary/52
     * @return double for the weekly pay
     */
    @Override
    public double calculateWeeklyPay()
    {
        double pay = (double)Math.round(salary/52*100)/100;
        return pay;
    }

    /**
     * Gives the employee annual raise of 6.25%
     */
    @Override
    public void annualRaise()
    {
       salary += salary * .0625;
    }

    /**
     * Sends back the salary of the employee
     * @return double for the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Sends back the holiday bonus of 3.365% of the annual salary
     * @return double of the holiday bonus
     */
    @Override
    public double holidayBonus()
    {
        return (double) Math.round(salary * .03365 * 100) / 100; //this rounds to the second decimal.
    }

    /**
     * Does nothing as there are no values to reset.
     */
    @Override
    public void resetWeek()
    {
        // nothing to reset
    }

    /**
     * Users the Employee toString and adds the salary for printing out employee
     * @return String of the employee
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nSalary: " + currency.format(salary);
    }

    /**
     * Updates the salary of the employee
     * @param pay double for the new salary value
     */
    @Override
    public void setPay(double pay)
    {
        salary = pay;
    }
}
