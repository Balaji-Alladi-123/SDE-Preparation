package Questions.LoggingFrameWorkDesign.appender;

import Questions.LoggingFrameWorkDesign.model.LogMessage;

public interface LogAppender {
    void append(LogMessage logMessage);
}
