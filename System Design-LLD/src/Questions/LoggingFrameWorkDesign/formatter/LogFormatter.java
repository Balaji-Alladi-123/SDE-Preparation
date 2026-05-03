package Questions.LoggingFrameWorkDesign.formatter;

import Questions.LoggingFrameWorkDesign.model.LogMessage;

public interface LogFormatter {
    String formate(LogMessage logMessage);
}
