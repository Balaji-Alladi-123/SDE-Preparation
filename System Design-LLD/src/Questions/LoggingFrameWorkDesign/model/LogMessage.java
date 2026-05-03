package Questions.LoggingFrameWorkDesign.model;

import Questions.LoggingFrameWorkDesign.enums.LogLevel;

import java.sql.Timestamp;

public class LogMessage {

    private final LogLevel logLevel;
    private final String message;
    private final Timestamp timestamp;
    private final String threadName;

    LogMessage(LogLevel logLevel, String message) {
       this.logLevel = logLevel;
       this.message = message;
       this.timestamp = new Timestamp(System.currentTimeMillis());
       this.threadName = Thread.currentThread().getName();
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getThreadName() {
        return threadName;
    }

    @Override
    public String toString() {
        return "[" + logLevel + "] " + timestamp + " - " + message;
    }
}
