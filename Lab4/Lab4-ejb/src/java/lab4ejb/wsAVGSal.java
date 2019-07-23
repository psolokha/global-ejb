/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4ejb;

import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import lab4entities.Employees;

/**
 *
 * @author Student1
 */
@WebService(serviceName = "wsAVGSal")
@Stateless()
public class wsAVGSal {

    @EJB
    private EmployeesFacadeLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "create")
    @Oneway
    public void create(@WebParam(name = "employees") Employees employees) {
        ejbRef.create(employees);
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "employees") Employees employees) {
        ejbRef.edit(employees);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "employees") Employees employees) {
        ejbRef.remove(employees);
    }

    @WebMethod(operationName = "find")
    public Employees find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Employees> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<Employees> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }

    @WebMethod(operationName = "findEmployeesByManager")
    public List<Employees> findEmployeesByManager(@WebParam(name = "mid") Integer mid) {
        return ejbRef.findEmployeesByManager(mid);
    }

    @WebMethod(operationName = "getWorkers")
    public List<Employees> getWorkers(@WebParam(name = "m_id") Integer m_id) {
        return ejbRef.getWorkers(m_id);
    }

    @WebMethod(operationName = "mergeEmployee")
    @Oneway
    public void mergeEmployee(@WebParam(name = "emp") Employees emp) {
        ejbRef.mergeEmployee(emp);
    }

    @WebMethod(operationName = "sendMailMessage")
    public void sendMailMessage(@WebParam(name = "from") String from, @WebParam(name = "to") String to, @WebParam(name = "subject") String subject, @WebParam(name = "content") String content) throws Exception {
        ejbRef.sendMailMessage(from, to, subject, content);
    }

    @WebMethod(operationName = "findObjSalary")
    public Double findObjSalary(@WebParam(name = "did") Integer did) {
        return ejbRef.findObjSalary(did);
    }
    
}
