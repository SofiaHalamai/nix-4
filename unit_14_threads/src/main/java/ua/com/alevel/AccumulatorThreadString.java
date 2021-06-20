package ua.com.alevel;

import java.util.Scanner;

public class AccumulatorThreadString implements Runnable {

    StringBuffer stringBuffer;

    public AccumulatorThreadString(StringBuffer stringBuffer) {
        this.stringBuffer = stringBuffer;
    }

    @Override
    public void run() {
        var reader = new Scanner(System.in);
        String tmp;
        do {
            tmp = reader.nextLine();
            stringBuffer.append(tmp);
        } while (!tmp.equals("quit"));
    }
}
