package Questions.LoggingFrameWorkDesign.service;

import Questions.LoggingFrameWorkDesign.appender.ConsoleAppender;
import Questions.LoggingFrameWorkDesign.appender.FileAppender;
import Questions.LoggingFrameWorkDesign.enums.LogLevel;
import Questions.LoggingFrameWorkDesign.formatter.SimpleFormatter;
import Questions.LoggingFrameWorkDesign.model.Logger;
import Questions.LoggingFrameWorkDesign.model.LoggerBuilder;

public class LogManager {
    private static Logger logger;

    public static Logger getLogger() {
        if(logger == null) {
            synchronized (LogManager.class) {
                if(logger == null) {
                    logger = new LoggerBuilder().setLevel(LogLevel.DEBUG)
                            .addAppender(new FileAppender("lda",new SimpleFormatter()))
                            .addAppender(new ConsoleAppender(new SimpleFormatter()))
                            .build();
                }
            }
        }
        return logger;
    }
}
