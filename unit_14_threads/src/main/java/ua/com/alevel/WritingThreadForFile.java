package ua.com.alevel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.RandomAccessFile;

public class WritingThreadForFile implements Runnable {

    private static Logger log = LogManager.getLogger(WritingThreadForFile.class);

    StringBuffer stringBuffer;

    public WritingThreadForFile(StringBuffer stringBuffer) {
        this.stringBuffer = stringBuffer;
    }

    @Override
    public void run() {
        try (RandomAccessFile writerAndReader = new RandomAccessFile("output.txt", "rw")) {
            while (!stringBuffer.toString().contains("quit")) {
                if (stringBuffer.toString().length() != 0) {
                    writerAndReader.setLength(0L);
                    writerAndReader.writeBytes(stringBuffer.toString());
                }
                Thread.sleep(1000);
            }
        } catch (IOException ex) {
            log.error("Error using RandomAccessFile");
        } catch (InterruptedException e) {
            log.error("Terminate the thread");
        }
    }
}
