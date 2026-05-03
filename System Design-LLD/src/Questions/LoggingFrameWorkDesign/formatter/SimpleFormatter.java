package Questions.LoggingFrameWorkDesign.formatter;

import Questions.LoggingFrameWorkDesign.model.LogMessage;

public class SimpleFormatter implements LogFormatter {
    @Override
    public String formate(LogMessage logMessage) {
        return String.format("[%s] [%s] [%s]: [%s]",
                logMessage.getTimestamp(),
                logMessage.getLogLevel(),
                logMessage.getThreadName(),
                logMessage.getMessage()
        );
    }
}
