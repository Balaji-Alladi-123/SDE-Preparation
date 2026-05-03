package Questions.LoggingFrameWorkDesign.appender;

import Questions.LoggingFrameWorkDesign.formatter.LogFormatter;
import Questions.LoggingFrameWorkDesign.model.LogMessage;

import java.io.FileWriter;

public class FileAppender implements LogAppender {

    private FileWriter fileWriter;
    private final LogFormatter logFormatter;

    public FileAppender(String filePath, LogFormatter logFormatter) {
        this.logFormatter = logFormatter;
        try {
            this.fileWriter = new FileWriter(filePath, true);
        } catch (Exception e) {
            System.out.println("Failed to create writer for file logs, exception: " + e.getMessage());
        }
    }

    @Override
    public void append(LogMessage logMessage) {
        try {
            fileWriter.write(logFormatter.formate(logMessage) + "\n");
            fileWriter.flush();
            /**
             *Using .flush() ensures:
             *The log message is immediately visible in the log file.
             *Logs aren’t lost if the app crashes before auto-flushing.
             **/
        } catch (Exception e) {
            System.out.println("Failed to write logs to file, exception: " + e.getMessage());
        }
    }
}
