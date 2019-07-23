/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4client;

import java.util.List;
import javax.ejb.EJB;
import lab4ejb.EmployeesFacadeRemote;
import lab4entities.Employees;

/**
 *
 * @author Student1
 */
public class Main {

    @EJB
    private static EmployeesFacadeRemote employeesFacade;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        List<Employees> empList = employeesFacade.getWorkers(101);
        for (Employees e : empList){
            System.out.println(e.getLastName());
        }
    
    }
    
}
