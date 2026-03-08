import EmployeeObjects.SalaryEmployee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryEmployeeTest
{
    SalaryEmployee employee = new SalaryEmployee("Steve", "Rodgers", 3781, "Sales", "Manager", 64325);


    @Test
    void calculateWeeklyPay()
    {
        double pay = employee.calculateWeeklyPay();

        assertEquals(1237.02, pay);
    }

    @Test
    void holidayBonus()
    {
        double bonus = employee.holidayBonus();

        assertEquals(2164.54, bonus);
    }
}