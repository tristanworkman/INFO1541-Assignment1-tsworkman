import EmployeeObjects.HourlyEmployee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HourlyEmployeeTest {
    HourlyEmployee employee = new HourlyEmployee("Tony", "Stark", 5749, "Service", "Lead Service Manager", 32.85);

    @Test
    void increaseHours()
    {
        employee.increaseHours(5);
        assertEquals(5, employee.getHoursWorked());
        employee.increaseHours(-5);
        assertEquals(5, employee.getHoursWorked());
        employee.increaseHours(10); //will not do anything because it is a negative number.
        assertEquals(15, employee.getHoursWorked());
        employee.increaseHours(-10); //will not do anything because it is a negative number.
        assertEquals(15, employee.getHoursWorked());
    }

    @Test
    void annualRaise()
    {
        employee.annualRaise();
        assertEquals(34.49, employee.getWage());
    }

    @Test
    void calculateWeeklyPay()
    {
        testWeeklyPayForHours(35, 1149.75);
        testWeeklyPayForHours(45, 1560.38);
    }

    private void testWeeklyPayForHours(int hoursAmount, double expectedPay) {
        employee.resetWeek(); //resets hours to 0. Instead, we could use a method and create the employee in that so that we don't have to do this.
        employee.increaseHours(hoursAmount);
        assertEquals(expectedPay, employee.calculateWeeklyPay());
    }
}