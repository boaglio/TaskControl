/**
 *  This file is part of LogiSima.
 *
 *  LogiSima is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  LogiSima is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with LogiSima.  If not, see <http://www.gnu.org/licenses/>.
 */
package play.modules.yml;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import play.Logger;
import play.Play;
import play.db.DBPlugin;
import play.db.jpa.JPA;
import play.db.jpa.JPABase;
import play.db.jpa.JPAPlugin;
import play.modules.yml.models.YmlObject;

/**
 * Main class of logisima-yml module.
 * 
 * @author bsimard
 * 
 */
public class YmlExtractor {

    /**
     * HashMap of all database object.
     */
    public static Map<String, YmlObject> ymlObjects = (Map<String, YmlObject>) Collections
                                                            .synchronizedMap(new HashMap<String, YmlObject>());

    /**
     * Main method !
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // we retrieve parameters
        String filename = "data";
        String output = "conf/";
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                if (args[i].startsWith("--filename=")) {
                    filename = args[i].substring(11);
                }
                if (args[i].startsWith("--output=")) {
                    output = args[i].substring(9);
                }
            }
        }

        // initiate play! framework
        File root = new File(System.getProperty("application.path"));
        Play.init(root, System.getProperty("play.id", ""));
        Thread.currentThread().setContextClassLoader(Play.classloader);
        Class c = Play.classloader.loadClass("play.modules.yml.YmlExtractor");
        Method m = c.getMethod("mainWork", String.class, String.class);
        m.invoke(c.newInstance(), filename, output);
        System.exit(0);
    }

    public static void mainWork(String filename, String output) throws Exception {
        // starting play DB plugin
        new DBPlugin().onApplicationStart();
        new JPAPlugin().onApplicationStart();
        JPAPlugin.startTx(true);

        // we search all entities classes
        List<Class> entities = Play.classloader.getAnnotatedClasses(Entity.class);
        for (Class entity : entities) {
            // we search all object for the specified class
            List<JPABase> objects = (List<JPABase>) JPA.em()
                    .createQuery("SELECT E FROM " + entity.getSimpleName() + " E").getResultList();

            for (JPABase jpaBase : objects) {
                YmlObject ymlObject = YmlExtractorUtil.object2YmlObject(jpaBase);
                ymlObjects.put(YmlExtractorUtil.getObjectId(jpaBase), ymlObject);
            }
        }
        JPAPlugin.closeTx(false);

        // write yml file.
        YmlExtractorUtil.writeYml(output, filename);

        // ending log
        Logger.info("*****************************************************************************");
        Logger.info("* Ending export yml process into file " + output + filename + ".yml");
        Logger.info("*****************************************************************************");
    }

}
