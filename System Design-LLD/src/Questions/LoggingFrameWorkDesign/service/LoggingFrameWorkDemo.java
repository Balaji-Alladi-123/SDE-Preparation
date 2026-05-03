package Questions.LoggingFrameWorkDesign.service;

import Questions.LoggingFrameWorkDesign.enums.LogLevel;
import Questions.LoggingFrameWorkDesign.model.Logger;

public class LoggingFrameWorkDemo {

    public static void run() {
        Logger logger = LogManager.getLogger();
        // Logging with default configuration
        // Should log debug and above log levels
        logger.info("This is an information message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");

        // Changing log level
        logger.setLogLevel(LogLevel.WARN);

        // Should only log warn and above
        logger.debug("This is a debug message");
        logger.info("This is an information message");
        logger.warn("This is an information message");

        logger.shutdown();
    }

    public static void main(String[] args) {
        run();
    }
}
