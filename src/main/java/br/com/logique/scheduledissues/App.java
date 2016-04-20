package br.com.logique.scheduledissues;

import br.com.logique.scheduledissues.model.business.QuartzManager;
import org.quartz.SchedulerException;

import java.io.IOException;

/**

 * Created by Gustavo on 10/04/2016.
 */
public class App {

    public static void main(String[] args) throws IOException, SchedulerException {

        SparkEngine engine = new SparkEngine();
        engine.setUp();

        QuartzManager.getInstance().scheduleMonitoringJob();


    }

}
