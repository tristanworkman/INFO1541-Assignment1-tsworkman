package EmployeeObjects;

import EmployeeBlueprints.Employee;
import EmployeeBlueprints.EmployeeType;

/**
 * Commission Employee that holds the data on sales and their commission rate.
 * @author lhartman2
 * @version 1.0.1
 */
public final class CommissionEmployee extends Employee {
    
    private double sales;
    private double rate;

    /**
     * Constructor that takes on employee information.
     * @param fn String for the first name
     * @param ln String for the last name
     * @param en int for the employee number
     * @param dept String for the department worked in
     * @param job String for the job title
     * @param r double for the commission rate
     */
    public CommissionEmployee(String fn, String ln, int en, String dept, String job, double r)
    {
        super(fn, ln, en, dept, job, EmployeeType.COMMISSION);
        rate = r;
        sales = 0.0;
    }

    /**
     * Increases the sales by 100 as a default.
     */
    public void increaseSales()
    {
        sales += 100.0;
    }

    /**
     * Increases the sales by sent amount 's'. Makes sure 's' is positive.
     * @param s double for the sales to add
     */
    public void increaseSales(double s)
    {
        if (s > 0)
        {
            sales += s;
        } else {
            System.out.println("Sales amount is not greater than 0.");
        }
    }

    /**
     * Returns back the sales
     * @return a double for the sales
     */
    public double getSales() {
        return sales;
    }

    /**
     * Returns back the commission rate
     * @return a double for the rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * Calculates the employee's pay. rate * sales
     * @return a double for the pay
     */
    @Override
    public double calculateWeeklyPay()
    {
        return rate * sales;
    }

    /**
     * Increase's the employees commission rate by .002
     */
    @Override
    public void annualRaise()
    {
       rate += .002;
    }

    /**
     * No holiday bonus for commission employees
     * @return a double of 0
     */
    @Override
    public double holidayBonus()
    {
        return 0;
    }

    /**
     * Sets the sales to 0
     */
    @Override
    public void resetWeek()
    {
        sales = 0.0;
    }

    /**
     * Updates the commission rate for the employee
     * @param pay double for the new rate value
     */
    @Override
    public void setPay(double pay)
    {
        rate = pay;
    }

    /**
     * Users the Employee toString and adds the rate and sales for printing out employee
     * @return String of the employee
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nRate: " + percent.format(rate) +"\nSales: " + currency.format(sales);
    }
}

