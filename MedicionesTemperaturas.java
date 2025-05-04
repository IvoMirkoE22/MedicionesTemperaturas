import java.util.ArrayList; 

/**
 * Registros de temperaturas diarios de una estacion meteorológica
 */
public class MedicionesTemperaturas
{
    /**
     * Contiene las mediciones de temperaturas.
     */
    private ArrayList<Double> registros;
    
    /**
     * Dia del registro
     */
    private int dia;
    
    /**
     * Mes del registro
     */
    private int mes;
    
    /**
     * Constructor de la clase. Inicializa dia y mes con los parámetros respectivos,
     * y la lista de registros se inicializa vacía (sin mediciones).
     * Precondición: 
     *  - dia debe ser un día válido (entre 1 y 31)
     *  - mes debe ser un mes válido (entre 1 y 12)
     */
    public MedicionesTemperaturas(int dia, int mes) {
        assert dia >= 1 && dia <= 31;
        assert mes >= 1 && mes <= 12;
        this.dia = dia;
        this.mes = mes;
        this.registros = new ArrayList<Double>();
    }
    
    /**
     * Agrega una temperatura al registro de temperaturas
     * Evitaremos que se ingrese temperatruas menores al cero absoluto
     * El cero absoluto es -273.15°C, la temperatura más bbaja físicamente posible
     */
    public void agregarRegistro(Double nuevaTemperatura) {
        //Precondición : la temperatura no puede ser menor al cero absoluto
        assert nuevaTemperatura >= -273.15: "Temperatura inferior a cero absoluto";
        registros.add((nuevaTemperatura));
    }

    /**
     * Muestra todas las temperaturas registradas en 
     * la pantalla.
     * Una temperatura se considera extrema si es:
     * -Mayor a 35°C (Caluroso)
     * -Menor a -15°C (Frío)
     * El método también cuenta por separado las temperaturas calurosas y frías
     * Por si se desea usar esa información más adelante
     */
    public int cantTemperaturasExtremas(){
        int cantTempC = 0;
        int cantTempF = 0;
        int tempExtremas;
        for(Double temperatura : registros){
            if(temperatura > 35) {
                cantTempC = cantTempC + 1;
            } else if(temperatura < -15){
                cantTempF = cantTempF + 1;
            }
        }
        tempExtremas = cantTempC + cantTempF;
        return tempExtremas;
    }
    /**
     * Devuelve el promedio de las temperaturas registradas
     */
    public double calcularPromedio() {
    // Verifica si la lista de registros está vacía (no hay temperaturas)
    if (registros.isEmpty()) {
        return 0.0; // Si no hay temperaturas, devuelve 0.0
    }

    double suma = 0.0; // Variable para acumular la suma de las temperaturas

    // Bucle que recorre cada temperatura en la lista 'registros'
    for (double temp : registros) {
        suma += temp; // Suma cada temperatura a la variable 'suma'
    }

    // Calcula el promedio dividiendo la suma total por la cantidad de registros
    return suma / registros.size(); // registros.size() devuelve cuántas temperaturas hay
    }
    /**
     * Devuelve la mayor temperatura registrada.
     * retorna la temperatura más alta o -273.15 si no hay registros.
     */
    public double maximaTemperatura() {
        if (registros.isEmpty()) {
            return -273.15; // Devuelve el cero absoluto si no hay datos
    }

    // Se toma como máximo inicial la primera temperatura de la lista
    double max = registros.get(0);

    // Recorre todas las temperaturas y actualiza el máximo si encuentra una mayor
    for (double temp : registros) {
        if (temp > max) {
            max = temp;
        }
    }

    return max; // Devuelve la mayor temperatura encontrada
    }
    /**
     * Devuelve la menor temperatura registrada.
     * Retorna la más baja o -273.15 si no hay registros.
     */
    public double minimaTemperatura() {
    if (registros.isEmpty()) {
        return -273.15; // Devuelve cero absoluto si no hay registros
    }

    // Toma como mínimo inicial la primera temperatura de la lista
    double min = registros.get(0);

    // Recorre todas las temperaturas para encontrar la menor
    for (double temp : registros) {
        if (temp < min) {
            min = temp;
        }
    }

    return min; // Devuelve la temperatura más baja encontrada
    }
    /**
     * Calcula la amplitud térmica (diferencia entre máxima y mínima).
     * Retorna la amplitud térmica o 0.0 si no hay registros.
     */
    public double amplitudTermica() {
        if (registros.isEmpty()) {
            return 0.0; // Si no hay datos, no se puede calcular
    }

    // Resta la menor temperatura a la mayor
    return maximaTemperatura() - minimaTemperatura();
    }
    /**
     * Método que calcula la cantidad de mediciones consecutivas con una misma temperatura
     */
    public int contadorRepeticionesConsecutivas(){
        if(registros.isEmpty()){
            return 0;// Si no hay datos, no se puede calcular
        }
        //cuando hay al menos una repetición registrado, ya tenemos una secuencia mínima de 1 repetición(la propia temperatura)
        int maxRepeticiones = 1;//ya vimos la primera medición
        int contadorActual =1;//el mínimo posible de repeticiones consecutivas es 1
        int i =1;
        while(i < registros.size()){
            if(registros.get(i).equals(registros.get(i-1))){
                contadorActual = contadorActual + 1;
                if(contadorActual > maxRepeticiones){
                    maxRepeticiones = contadorActual;
                }
            }else{
                contadorActual = 1;
            }
            i = i+1;
        }
        return maxRepeticiones;
    }
    /**
     * Muestra todas las temperaturas registradas en 
     * la pantalla. 
     */
    public void imprimirTemperaturas() {
        System.out.println(dia + "/" + mes);
        int pos = 0;
        for (Double temperatura: registros) {
            System.out.print(pos + ": ");
            System.out.println(temperatura + "°C");
            pos = pos + 1;
        }
    }
}

