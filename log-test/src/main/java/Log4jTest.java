import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by jinbiao.yao on 2018/8/10.
 */
public class Log4jTest {
    private static Logger logger = LogManager.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        //PropertyConfigurator.configure("D:/soft/git-repertory/Test/log-test/src/main/resources/log4j.properties");
        //logger.info("logInfo test");
        //logger.error("logError test");
        logger.debug("logDegub test");

        //logger.fatal("this is fatal!");
        // 追踪
        //logger.trace("this is trace!");
    }
}
