import models.TipoTarefa;
import models.Usuario;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class Bootstrap extends Job {
	
	@SuppressWarnings("deprecation")
	public void doJob() {
        if(Usuario.count() == 0 | TipoTarefa.count() == 0) {
            Fixtures.load("data.yml");
        }
    }

}
