import java.util.ArrayList;

/**
 * Registros de temperaturas diarios de una estación meteorológica.
 *
 * Invariante de clase:
 * - 1 ≤ dia ≤ 31
 * - 1 ≤ mes ≤ 12
 * - registros no es null
 * - todas las temperaturas registradas son ≥ -273.15°C
 */
public class MedicionesTemperaturas
{
    private ArrayList<Double> registros;
    private int dia;
    private int mes;

    /**
     * Constructor.
     * Precondición: 1 ≤ dia ≤ 31, 1 ≤ mes ≤ 12
     * Postcondición: se crea un objeto con registros vacíos y fecha válida
     */
    public MedicionesTemperaturas(int dia, int mes) {
        assert dia >= 1 && dia <= 31 : "Día inválido";
        assert mes >= 1 && mes <= 12 : "Mes inválido";

        this.dia = dia;
        this.mes = mes;
        this.registros = new ArrayList<>();

        assert repOk() : "Invariante violado en constructor";
    }

    /**
     * Agrega una nueva temperatura al registro.
     * Precondición: temperatura ≥ -273.15
     * Postcondición: registros.size() aumenta en 1
     */
    public void agregarRegistro(Double nuevaTemperatura) {
        assert nuevaTemperatura >= -273.15 : "Temperatura inválida";

        registros.add(nuevaTemperatura);

        assert registros.contains(nuevaTemperatura) : "Temperatura no registrada";
        assert repOk();
    }

    /**
     * Devuelve la cantidad de temperaturas extremas:
     * mayores a 35°C o menores a -15°C.
     * Precondición: true
     * Postcondición: retorna la cantidad de temperaturas extremas detectadas
     */
    public int cantTemperaturasExtremas() {
        int cantTempC = 0;
        int cantTempF = 0;

        for (Double temperatura : registros) {
            if (temperatura > 35) {
                cantTempC++;
            } else if (temperatura < -15) {
                cantTempF++;
            }
        }

        int tempExtremas = cantTempC + cantTempF;
        return tempExtremas;
    }

    /**
     * Calcula el promedio de temperaturas registradas.
     * Precondición: true
     * Postcondición: retorna el promedio o 0.0 si no hay datos
     */
    public double calcularPromedio() {
        if (registros.isEmpty()) {
            return 0.0;
        }

        double suma = 0.0;
        for (double temp : registros) {
            suma += temp;
        }

        return suma / registros.size();
    }

    /**
     * Devuelve la mayor temperatura registrada.
     * Precondición: true
     * Postcondición: retorna la temperatura máxima o -273.15 si no hay registros
     */
    public double maximaTemperatura() {
        if (registros.isEmpty()) {
            return -273.15;
        }

        double max = registros.get(0);
        for (double temp : registros) {
            if (temp > max) {
                max = temp;
            }
        }

        return max;
    }

    /**
     * Devuelve la menor temperatura registrada.
     * Precondición: true
     * Postcondición: retorna la mínima o -273.15 si no hay registros
     */
    public double minimaTemperatura() {
        if (registros.isEmpty()) {
            return -273.15;
        }

        double min = registros.get(0);
        for (double temp : registros) {
            if (temp < min) {
                min = temp;
            }
        }

        return min;
    }

    /**
     * Calcula la amplitud térmica (máxima - mínima).
     * Precondición: true
     * Postcondición: retorna la amplitud térmica o 0.0 si no hay registros
     */
    public double amplitudTermica() {
        if (registros.isEmpty()) {
            return 0.0;
        }

        return maximaTemperatura() - minimaTemperatura();
    }

    /**
     * Cuenta la mayor cantidad de repeticiones consecutivas de una misma temperatura.
     * Precondición: true
     * Postcondición: retorna la cantidad máxima de repeticiones consecutivas
     */
    public int contadorRepeticionesConsecutivas() {
        if (registros.isEmpty()) {
            return 0;
        }

        int maxRepeticiones = 1;
        int contadorActual = 1;

        for (int i = 1; i < registros.size(); i++) {
            if (registros.get(i).equals(registros.get(i - 1))) {
                contadorActual++;
                if (contadorActual > maxRepeticiones) {
                    maxRepeticiones = contadorActual;
                }
            } else {
                contadorActual = 1;
            }
        }

        assert repOk();
        return maxRepeticiones;
    }

    /**
     * Imprime todas las temperaturas registradas.
     * Precondición: true
     * Postcondición: imprime cada temperatura con su posición
     */
    public void imprimirTemperaturas() {
        System.out.println(dia + "/" + mes);
        int pos = 0;
        for (Double temperatura : registros) {
            System.out.println(pos + ": " + temperatura + "°C");
            pos++;
        }

        assert repOk();
    }

    /**
     * Verifica el invariante de clase.
     */
    public boolean repOk() {
    if (dia < 1 || dia > 31) {
        return false;
    }
    if (mes < 1 || mes > 12) {
        return false;
    }
    if (registros == null) {
        return false;
    }

    for (Double temp : registros) {
        if (temp < -273.15) {
            return false;
        }
    }

    return true;
}

}
