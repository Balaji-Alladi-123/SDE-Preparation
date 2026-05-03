package Questions.LoggingFrameWorkDesign.appender;

import Questions.LoggingFrameWorkDesign.formatter.LogFormatter;
import Questions.LoggingFrameWorkDesign.model.LogMessage;

public class ConsoleAppender implements LogAppender{
    private final LogFormatter logFormatter;

    public ConsoleAppender(LogFormatter logFormatter) {
        this.logFormatter = logFormatter;
    }
    @Override
    public void append(LogMessage logMessage) {
        System.out.println(logFormatter.formate(logMessage));
    }
}
