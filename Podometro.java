/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author Yumurdzhan Yalmaz
 * 
 */
public class Podometro {
    private final char HOMBRE = 'H';  // indican el sexo de una persona
    private final char MUJER = 'M';        
    private final double ZANCADA_HOMBRE = 0.45; //para calcular longitudZancada
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6; //  nº de día de la semana
    private final int DOMINGO = 7; //  nº de día de la semana

    private String marca;  //  guarda la marca del podómetro
    private double altura;  //  guarda la altura de la persona en centímetros
    private char sexo; // guarda el sexo de una persona (en un carácter)
    private double longitudZancada; // longitud de la zancada en centímetros
    private int totalPasosLaborables; // nº de pasos dados en días laborables
    private int totalPasosSabado; // nº de pasos dados el sábado
    private int totalPasosDomingo; // guarda el nº de pasos dados el domingo
    private double totalDistanciaSemana; // distancia recorrida total(Km)
    private double totalDistanciaFinSemana;//distancia recorrida finSemana(Km)
    private int tiempo; // tiempo total caminado durante la semana (minutos)
    private int caminatasNoche; // paseos a partir de las 21H en la semana

    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca; // devuelve el nombre de la marca
    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura;
        sexo = queSexo;
        if (sexo == HOMBRE) {
            longitudZancada = altura * ZANCADA_HOMBRE;
            longitudZancada = Math.ceil(longitudZancada); 
        }
        else if (sexo == MUJER) {
            longitudZancada = altura * ZANCADA_MUJER;
            longitudZancada = Math.floor(longitudZancada);
        }
        else {
            System.out.println("Error, inserte sexo valido");
        }
    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFina – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
    int horaFin) {
        totalDistanciaSemana += pasos * longitudZancada;
        switch (dia) {
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            totalPasosLaborables += pasos;
            break;

            case SABADO: 
            totalDistanciaFinSemana += pasos * longitudZancada; 
            totalPasosSabado += pasos;
            break;

            case DOMINGO: 
            totalDistanciaFinSemana += pasos * longitudZancada;
            totalPasosDomingo += pasos;
            break;
        }
        if (horaInicio > 2100 && horaFin < 2400) {
            caminatasNoche++; 
        }
        int horasFin = (int) horaFin / 100; 
        int minutosFin =(horaFin) % 100;
        int totalMinutosFin = (horasFin * 60) + minutosFin; // hora final convertido a minutos

        int horasInicio = (int) horaInicio / 100;
        int minutosInicio =(horaInicio) % 100;
        int totalMinutosInicio = (horasInicio * 60) + minutosInicio; // hora inicio convertido a minutos

        tiempo += totalMinutosFin - totalMinutosInicio; // tiempo caminado durante toda la semana en minutos

    }

    /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println("Configuración del podómetro" + 
            "\n**************************" + 
            "\nAltura: " + (altura / 100) + " mtos" +
            "\nSexo: " + sexo +
            "\nLongitudZancada: " + (longitudZancada / 100) + " mtos");
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println("Estadísticas" +
            "\n*********************************" +
            "\nDistancia recorrida toda la semana: " + 
            (totalDistanciaSemana / 100000) + " Km " +
            "\nDistancia recorrida fin de semana: " + 
            (totalDistanciaFinSemana / 100000) + " Km " +
            "\nNº pasos días laborables: " + totalPasosLaborables +
            "\nNº pasos SÁBADO: " + totalPasosSabado +
            "\nNº pasos DOMINGO: " + totalPasosDomingo +
            "\nNº caminatas realizadas a partir de las 21h.: "
            + caminatasNoche +
            "\nTiempo total caminado en la semana: " 
            + (tiempo/60) + "h" + " y " + (tiempo % 60) + " minutos " +
            "\nDía/s con más pasos caminados: " + diaMayorNumeroPasos());
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String srt = "";
        if (totalPasosLaborables > totalPasosSabado && totalPasosLaborables > 
        totalPasosDomingo) {
            srt = "LABORABLE";
        }
        else if (totalPasosLaborables == totalPasosSabado && totalPasosLaborables > totalPasosDomingo) {
            srt = "LABORABLE Y SÁBADO";
        }
        else if (totalPasosLaborables == totalPasosDomingo && totalPasosLaborables > totalPasosSabado) {
            srt = "LABORABLE Y DOMINGO";
        }
        else if (totalPasosLaborables == totalPasosSabado && totalPasosLaborables == totalPasosDomingo && totalPasosSabado == totalPasosDomingo) {
            srt = "LABORABLES , SÁBADO Y DOMINGO";
        }
        else if (totalPasosSabado > totalPasosLaborables && totalPasosSabado >
        totalPasosDomingo) {
            srt = "SÁBADO";
        }
        else if(totalPasosDomingo > totalPasosLaborables && totalPasosDomingo >
        totalPasosSabado) {
            srt = "DOMINGO";
        }
        else if (totalPasosSabado == totalPasosDomingo && totalPasosSabado > totalPasosLaborables) {
            srt = "SÁBADO Y DOMINGO";
        }
        return srt;
    }

    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset() {
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

}
