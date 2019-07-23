/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4war;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.transaction.UserTransaction;
import lab4ejb.EmployeesFacadeLocal;
import lab4entities.Employees;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Student1
 */
@Named(value = "empBean")
@SessionScoped
public class EmpBean implements Serializable {

    @EJB
    private EmployeesFacadeLocal employeesFacade;
    
    @Resource
    UserTransaction utx;
    
    Integer mid;
    List<Employees> empList;
    
    Employees selectedEmp;
    Employees reservedEmp;

    public Employees getSelectedEmp() {
        return selectedEmp;
    }

    public void setSelectedEmp(Employees selectedEmp) {
        this.selectedEmp = selectedEmp;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public List<Employees> getEmpList() {
        return empList;
    }

    public void setEmpList(List<Employees> empList) {
        this.empList = empList;
    }
    
    public String searchEmpAction(){
        empList = employeesFacade.getWorkers(mid);
        return null;
    }
    
    public String editEmpAction(){
        reservedEmp = new Employees();
        reservedEmp.setEmployeeId(selectedEmp.getEmployeeId());
        reservedEmp.setSalary(selectedEmp.getSalary());
        reservedEmp.setEmail(selectedEmp.getEmail());
        reservedEmp.setLastName(selectedEmp.getLastName());
        reservedEmp.setFirstName(selectedEmp.getFirstName());
        return "edit";
    }
    
    public String saveEmpAction() throws Exception {
        try {
            utx.begin();
            employeesFacade.mergeEmployee(selectedEmp);
            employeesFacade.sendMailMessage("system@mycomp.ru", "alex@mycomp.ru", "Employee changed", selectedEmp.getLastName()+"' data changed!!!");
            utx.commit();
            return "success";
        } catch (Exception ex) {
            Logger.getLogger(EmpBean.class.getName()).log(Level.SEVERE, null, ex);
            utx.rollback();
            this.cancelEmpAction();
            return "cancel";
        }
    }
    
    public void onRowSelect(SelectEvent event) {
        selectedEmp = (Employees)event.getObject();
        
    }
    
    public String cancelEmpAction() {
        
        selectedEmp.setEmployeeId(reservedEmp.getEmployeeId());
        selectedEmp.setSalary(reservedEmp.getSalary());
        selectedEmp.setEmail(reservedEmp.getEmail());
        selectedEmp.setLastName(reservedEmp.getLastName());
        selectedEmp.setFirstName(reservedEmp.getFirstName());
        return "cancel";
    }
    
    public boolean isRendered (){
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("top_manager");
    }

    public Employees getReservedEmp() {
        return reservedEmp;
    }

    public void setReservedEmp(Employees reservedEmp) {
        this.reservedEmp = reservedEmp;
    }
    
    
    
    public EmpBean() {
    }
    
}
