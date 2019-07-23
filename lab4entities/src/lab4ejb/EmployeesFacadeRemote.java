/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4ejb;

import java.util.List;
import javax.ejb.Remote;
import lab4entities.Employees;

/**
 *
 * @author Student1
 */
@Remote
public interface EmployeesFacadeRemote {

    void create(Employees employees);

    void edit(Employees employees);

    void remove(Employees employees);

    Employees find(Object id);

    List<Employees> findAll();

    List<Employees> findRange(int[] range);

    int count();
    
    List<Employees> findEmployeesByManager (Integer mid);
    List<Employees> getWorkers(Integer m_id);
    public Double findObjSalary (Integer did);
}
