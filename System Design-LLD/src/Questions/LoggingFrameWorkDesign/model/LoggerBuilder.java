package Questions.LoggingFrameWorkDesign.model;

import Questions.LoggingFrameWorkDesign.appender.LogAppender;
import Questions.LoggingFrameWorkDesign.enums.LogLevel;

import java.util.ArrayList;
import java.util.List;

public class LoggerBuilder {
    private LogLevel logLevel = LogLevel.ERROR;
    private List<LogAppender> appenders = new ArrayList<>();

   public LoggerBuilder setLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
        return this;
    }

   public LoggerBuilder addAppender(LogAppender logAppender) {
        this.appenders.add(logAppender);
        return this;
    }

    public Logger build() {
        return new Logger(logLevel, appenders);
    }

}
