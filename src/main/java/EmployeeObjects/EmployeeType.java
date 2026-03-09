package EmployeeObjects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//I create annotation for classes with a String type value and it is retained in runtime.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EmployeeType
{
    String type();
}
