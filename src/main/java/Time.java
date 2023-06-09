public class Time {
    public static double timeStart = System.nanoTime();

    public static double getTime() {
        return (System.nanoTime() - timeStart) * 1E-9;
    }
}
