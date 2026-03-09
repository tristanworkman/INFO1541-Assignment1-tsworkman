package EmployeeObjects;

import EmployeeBlueprints.Employee;
import EmployeeBlueprints.EmployeeType;

/**
 * This holds information of the hourly wage and hours worked for an hourly employee in the system.
 * @author lhartman2
 * @version 1.0.1
 */
//annotation EmployeeType with type as 'Hourly'
@EmployeeObjects.EmployeeType(type = "Hourly")
public final class HourlyEmployee extends Employee {
    @PayRate(type = "Hourly") //annotation PayRate with type as 'Hourly'
    private double wage;
    private double hoursWorked;

    /**
     * Main constructor that takes on employee information.
     * @param fn String for the first name
     * @param ln String for the last name
     * @param en int for the employee number
     * @param dept String for the department worked in
     * @param job String for the job title
     * @param w double for the wage/hourly pay
     */
    public HourlyEmployee(String fn, String ln, int en, String dept, String job, double w)
    {
        super(fn, ln, en, dept, job, EmployeeType.HOURLY);
        wage = w;
        hoursWorked = 0.0;
    }

    /**
     * adds 1 to hours worked by default
     */
    public void increaseHours()
    {
        hoursWorked += 1;
    }

    /**
     * Add to the hours worked by the value sent. Checks to make sure value is positive.
     * @param h double of hours to add
     */
    public void increaseHours(double h)
    {
        if (h > 0) {
            hoursWorked += h;
        } else {
            System.out.println("Hours must be greater than 0");
        }
    }

    /**
     * Sends back the number of hours worked by employee
     * @return double value of the hours worked.
     */
    public double getHoursWorked() {
        return hoursWorked;
    }

    /**
     * Sends back the hourly pay of the employee
     * @return double for the wage
     */
    public double getWage() {
        return wage;
    }

    /**
     * Calculate the employees pay. If hours is greater than 40 then does overtime.
     *
     * @return a double for the weekly pay
     */
    @Override
    @WeeklyPayCalculator //annotation WeeklyPayCalculator
    public double calculateWeeklyPay()
    {
        double pay = wage * hoursWorked;
        if (hoursWorked > 40)
        {
            pay = wage * 40 + (wage*1.5) * (hoursWorked-40); //changed wage * 1.25 to * 1.5
        }
        
        return (double) Math.round(pay * 100) / 100; //rounded it to the second decimal
    }

    /**
     * Increases the employee's wage by 5%
     */
    @Override
    public void annualRaise()
    {
        double raise = (double)Math.round(wage * .05 * 100)/100;
        wage += raise;
    }

    /**
     * Calculates the annual bonus equivalent to 40 hours of work.
     * @return double for holdiay bonus
     */
    @Override
    public double holidayBonus()
    {
        return 40 * wage;
    }

    /**
     * Sets the hours for the week back to 0
     */
    @Override
    public void resetWeek()
    {
        hoursWorked = 0.0;
    }

    /**
     * Users the Employee toString and adds the wage and hours worked for printing out employee
     * @return String of the employee
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nWage: " + currency.format(wage) +"\nHours Worked: " + hoursWorked;
    }

    /**
     * Sets the pay to new value sent
     * @param pay double for the new wage value
     */
    @Override
    public void setPay(double pay)
    {
        wage = pay;
    }
}
