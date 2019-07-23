/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4ejb;

import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.MANDATORY;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lab4entities.Employees;
import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Topic;
import javax.naming.InitialContext;

/**
 *
 * @author Student1
 */
@Stateless
public class EmployeesFacade extends AbstractFacade<Employees> implements EmployeesFacadeLocal, EmployeesFacadeRemote {

    @PersistenceContext(unitName = "Lab4-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeesFacade() {
        super(Employees.class);
    }
    
    
    public List<Employees> findEmployeesByManager (Integer mid){
        return em.createNamedQuery("Employees.findByManager").setParameter("m_id", mid).getResultList();
    }
    
    public Double findObjSalary (Integer did){
        return (Double)em.createNamedQuery("Employees.findByAVGSalary").setParameter("departmentId", did).getSingleResult();
    }
    
    public List<Employees> getWorkers(Integer m_id){
        List <Employees> working = new Vector();
        List <Employees> finding = new Vector();
        working = (Vector)this.findEmployeesByManager(m_id);
        while (true){
            if (working.size()>0){
              finding.addAll(working);
            }
            else break;
            
            Object [] e = working.toArray();
            working.clear();
            for (Object o: e){
              Employees e1 = (Employees) o;
              working.addAll(this.findEmployeesByManager(e1.getEmployeeId()));          
            }        
        }

    return finding;
    }
    @TransactionAttribute(MANDATORY)
    public void mergeEmployee(Employees emp){
        em.merge(emp);
    }
    
    public void sendMailMessage(String from, String to, String subject, String content)throws Exception {
        
            System.out.println("Looking up TCF");
            ConnectionFactory connectionFactory = (ConnectionFactory) new InitialContext()
                .lookup("jms/HRConFactory");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            
            System.out.println("Starting Topic Session");
            Session topicSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination topic = (Topic) new InitialContext().lookup("jms/HRTopic");
            MessageProducer publisher = topicSession.createProducer(topic);

            Message message = topicSession.createMessage();
            message.setJMSType("MailMessage");
            message.setStringProperty("from", from);
            message.setStringProperty("to", to);
            message.setStringProperty("subject", subject);
            message.setStringProperty("content", content);
            
            publisher.send(message);
            System.out.println("Message Sent to JMS Topic");
            publisher.close();
            topicSession.close();
            connection.close();


        
      
    }

    
}
