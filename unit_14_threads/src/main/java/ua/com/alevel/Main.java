package ua.com.alevel;

public class Main {

    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        Thread threadString = new Thread(new AccumulatorThreadString(stringBuffer));
        Thread threadFile = new Thread(new WritingThreadForFile(stringBuffer));

        threadString.start();
        threadFile.start();
    }
}
