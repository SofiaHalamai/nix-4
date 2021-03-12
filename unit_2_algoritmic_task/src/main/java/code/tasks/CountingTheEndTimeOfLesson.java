package code.tasks;


import java.time.LocalTime;

public class CountingTheEndTimeOfLesson {
    private LocalTime time = LocalTime.of(9, 00);
    private int lessonNumber;

    public CountingTheEndTimeOfLesson(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public void calculationOfTime (){
        System.out.println("The end time of the lesson at: " + time.plusMinutes(45 * lessonNumber +(lessonNumber/2*5) + (lessonNumber-1)/2*15));
    }
}
