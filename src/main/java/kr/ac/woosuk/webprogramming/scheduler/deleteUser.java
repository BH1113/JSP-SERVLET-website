package kr.ac.woosuk.webprogramming.scheduler;

import kr.ac.woosuk.webprogramming.models.DAO.UserDAO;
import kr.ac.woosuk.webprogramming.models.DAO.UserDAOImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class deleteUser implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        UserDAO userDAO = new UserDAOImpl();
        userDAO.completeDelete();
    }
}
