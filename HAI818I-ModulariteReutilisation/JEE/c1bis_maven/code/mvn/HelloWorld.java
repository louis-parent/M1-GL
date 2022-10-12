package ig4.helloworld;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class HelloWorld {
	public static void main(String... args) {
		Logger logger = Logger.getLogger(HelloWorld.class);
		BasicConfigurator.configure();
 
        logger = Logger.getLogger(HelloWorld.class);
        logger.debug("this is a debug log message");
        logger.info("this is a information log message");
        logger.warn("this is a warning log message");
        logger.error("this is an error log message");
	}
}
