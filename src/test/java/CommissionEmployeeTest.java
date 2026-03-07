import EmployeeObjects.CommissionEmployee;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class CommissionEmployeeTest
{
    CommissionEmployee employee = new CommissionEmployee("Clint", "Barton", 6847, "Sales", "Customer Representative", .0265);

    @Test
    void increaseSales()
    {

        employee.increaseSales(100);
        assertEquals(100, employee.getSales());
        employee.increaseSales(10);
        assertEquals(110, employee.getSales());
        employee.increaseSales(-10); //will not do anything because it is a negative number.
        assertEquals(110, employee.getSales());
        employee.increaseSales(-100); //will not do anything because it is a negative number.
        assertEquals(110, employee.getSales());

    }

    @Test
    void holidayBonus()
    {
        double bonus = employee.holidayBonus();

        assertEquals(0, bonus);
    }

    @Test
    void annualRaise()
    {
        employee.annualRaise();
        employee.annualRaise();

        assertEquals(0.0305, employee.getRate());
    }
}