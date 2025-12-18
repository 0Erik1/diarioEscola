package parte1;

public class MediaPonderada implements CalcularMedia {
    private double pesoN1;
    private double pesoN2;

    public void setPesos(double pesoN1, double pesoN2) {
        this.pesoN1 = pesoN1;
        this.pesoN2 = pesoN2;
    }

    public double calc(double... notas) {
        if (notas != null && notas.length == 2) {
            double n1 = notas[0];
            double n2 = notas[1];

            return (n1 * pesoN1 + n2 * pesoN2) / (pesoN1 + pesoN2);
        }

        return 0.0;
    }
}
