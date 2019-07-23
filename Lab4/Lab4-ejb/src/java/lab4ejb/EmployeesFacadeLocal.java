/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4ejb;

import java.util.List;
import javax.ejb.Local;
import lab4entities.Employees;

/**
 *
 * @author Student1
 */
@Local
public interface EmployeesFacadeLocal {

    void create(Employees employees);

    void edit(Employees employees);

    void remove(Employees employees);

    Employees find(Object id);

    List<Employees> findAll();

    List<Employees> findRange(int[] range);

    int count();
    List<Employees> findEmployeesByManager (Integer mid);
    List<Employees> getWorkers(Integer m_id);
    void mergeEmployee(Employees emp);
    void sendMailMessage(String from, String to, String subject, String content) throws Exception;
    public Double findObjSalary (Integer did);
}
