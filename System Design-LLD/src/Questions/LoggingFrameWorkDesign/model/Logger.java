package Questions.LoggingFrameWorkDesign.model;

import Questions.LoggingFrameWorkDesign.appender.LogAppender;
import Questions.LoggingFrameWorkDesign.enums.LogLevel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Logger {

    private LogLevel logLevel;
    private final List<LogAppender> logAppenders;
    private final ExecutorService executorService;

    Logger(LogLevel logLevel, List<LogAppender> logAppenders) {
        this.logLevel = logLevel;
        this.logAppenders = logAppenders;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void log(LogLevel logLevel, String message) {
        LogMessage logMessage = new LogMessage(logLevel, message);
        executorService.submit(() -> logAppenders.forEach(a -> a.append(logMessage)));
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public void shutdown() {
        executorService.shutdown();
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void debug(String message) {
        log(LogLevel.INFO, message);
    }

    public void error(String message) {
        log(LogLevel.INFO, message);
    }

    public void fatal(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.INFO, message);
    }
}
