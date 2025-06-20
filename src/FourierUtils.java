public class FourierUtils {
    public static double[] computeFFT(double[] signal) {
        int n = signal.length;
        double[] mag = new double[n];
        for (int k = 0; k < n; k++) {
            double re = 0, im = 0;
            for (int t = 0; t < n; t++) {
                re += signal[t] * Math.cos(2 * Math.PI * t * k / n);
                im -= signal[t] * Math.sin(2 * Math.PI * t * k / n);
            }
            mag[k] = Math.sqrt(re * re + im * im);
        }
        return mag;
    }
}
