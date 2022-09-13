package kr.ac.woosuk.webprogramming.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class schedulerForDelete {
    private Scheduler scheduler;
    private SchedulerFactory schedulerFactory;

    public schedulerForDelete() {
        try {
            schedulerFactory = new StdSchedulerFactory();
            scheduler = schedulerFactory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        try{
            JobDetail job = newJob(deleteUser.class).withIdentity("firstJob", "firstGroup").build();

            CronTrigger trigger = newTrigger().withIdentity("firstTrigger", "firstGroup").withSchedule(cronSchedule("* * * 6 * ?")).build();

            scheduler.scheduleJob(job, trigger);

            scheduler.start();
            Thread.sleep(3000);
            scheduler.shutdown();
        }catch (SchedulerException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
