package ua.com.alevel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.RandomAccessFile;

public class WritingThreadForFile implements Runnable {

    private Logger log = LogManager.getLogger(WritingThreadForFile.class);

    private final StringBuffer stringBuffer;

    public WritingThreadForFile(StringBuffer stringBuffer) {
        this.stringBuffer = stringBuffer;
    }

    @Override
    public void run() {
        try (RandomAccessFile writerAndReader = new RandomAccessFile("output.txt", "rw")) {
            String string;
            while (!stringBuffer.toString().contains("quit")) {
                synchronized (stringBuffer) {
                    string = stringBuffer.toString();
                    if (string.contains("quit")) break;
                    if (string.length() == 0) {
                        Thread.sleep(1000);
                        continue;
                    }
                    if (checkingIfStringChanged(writerAndReader, string)) {
                        writerAndReader.setLength(0L);
                        System.out.println("WritingThreadForFile.run");
                        writerAndReader.writeBytes(string);
                    }
                }
                Thread.sleep(1000);
            }
        } catch (IOException ex) {
            log.error("Error using RandomAccessFile");
        } catch (InterruptedException e) {
            log.error("Terminate the thread");
        }
    }

    private boolean checkingIfStringChanged(RandomAccessFile reader, String string) throws IOException {
        reader.seek(0);
        return !reader.readLine().equals(string);
    }
}
