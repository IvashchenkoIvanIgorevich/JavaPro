import homework.practiceexception.ArrayValueCalculator;

public class Main {

    public static void main(String[] args) {
        String[][] input = {
                {"1","2","3","4"},
                {"5","6","7","8"},
                {"9","10","11","12"},
                {"13","14","15", "+"}
        };

        try {
            int sum = ArrayValueCalculator.doCalc(input);
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
