package parte1;

public class MediaAritmetica implements CalcularMedia {
    public double calc(double... notas) {
        double soma = 0.0;

        if (notas != null && notas.length == 2) {
            for (double n : notas) {
                soma += n;
            }

            return soma / notas.length;
        }

        return 0.0;
    }
}
