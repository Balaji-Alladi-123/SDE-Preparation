package Questions.LoggingFrameWorkDesign.appender;

import Questions.LoggingFrameWorkDesign.core.LogMessage;

public interface LogAppender {
    void append(LogMessage logMessage);
}
