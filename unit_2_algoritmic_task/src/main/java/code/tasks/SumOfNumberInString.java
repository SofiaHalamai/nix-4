package code.tasks;

public class SumOfNumberInString {
    private String str_sum_number;

    public SumOfNumberInString(String str_sum_number) {
        this.str_sum_number = str_sum_number;
    }

    public int findingSum() {
        int sum = 0;
        str_sum_number = str_sum_number.replaceAll("[^0-9]+", " ").trim();
        if (str_sum_number.length() == 0) System.out.println("The string is missing numbers");
        else {
            String[] parts = str_sum_number.split(" ");
            for (String subStr : parts) {
                int result = Integer.parseInt(subStr);
                sum += result;
            }
        }
        return sum;
    }
}
