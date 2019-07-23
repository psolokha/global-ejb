package lab4entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lab4entities.Employees;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-12T20:19:43")
@StaticMetamodel(Employees.class)
public class Employees_ { 

    public static volatile CollectionAttribute<Employees, Employees> employeesCollection;
    public static volatile SingularAttribute<Employees, String> firstName;
    public static volatile SingularAttribute<Employees, String> lastName;
    public static volatile SingularAttribute<Employees, Date> hireDate;
    public static volatile SingularAttribute<Employees, String> phoneNumber;
    public static volatile SingularAttribute<Employees, Integer> departmentId;
    public static volatile SingularAttribute<Employees, Integer> employeeId;
    public static volatile SingularAttribute<Employees, Employees> managerId;
    public static volatile SingularAttribute<Employees, BigDecimal> salary;
    public static volatile SingularAttribute<Employees, String> email;
    public static volatile SingularAttribute<Employees, BigDecimal> commissionPct;

}