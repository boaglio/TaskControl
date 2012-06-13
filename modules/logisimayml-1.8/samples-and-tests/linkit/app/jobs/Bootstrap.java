package jobs;

import play.Logger;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class Bootstrap extends Job {

    public void doJob() {
        Logger.info("load yml file");
        Fixtures.loadModels("data.yml");
    }
}
