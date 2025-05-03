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

