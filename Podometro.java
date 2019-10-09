/**
 * La clase modela un sencillo pod�metro que registra informaci�n
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
    private final int SABADO = 6; //  n� de d�a de la semana
    private final int DOMINGO = 7; //  n� de d�a de la semana

    private String marca;  //  guarda la marca del pod�metro
    private double altura;  //  guarda la altura de la persona en cent�metros
    private char sexo; // guarda el sexo de una persona (en un car�cter)
    private double longitudZancada; // longitud de la zancada en cent�metros
    private int totalPasosLaborables; // n� de pasos dados en d�as laborables
    private int totalPasosSabado; // n� de pasos dados el s�bado
    private int totalPasosDomingo; // guarda el n� de pasos dados el domingo
    private double totalDistanciaSemana; // distancia recorrida total(Km)
    private double totalDistanciaFinSemana;//distancia recorrida finSemana(Km)
    private int tiempo; // tiempo total caminado durante la semana (minutos)
    private int caminatasNoche; // paseos a partir de las 21H en la semana

    /**
     * Inicializa el pod�metro con la marca indicada por el par�metro.
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
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
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
     *  Recibe cuatro par�metros que supondremos correctos:
     *    pasos - el n� de pasos caminados
     *    dia - n� de d�a de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *    horaInicio � hora de inicio de la caminata
     *    horaFina � hora de fin de la caminata
     *    
     *    A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *    y  actualizar� el pod�metro adecuadamente  
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
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println("Configuraci�n del pod�metro" + 
            "\n**************************" + 
            "\nAltura: " + (altura / 100) + " mtos" +
            "\nSexo: " + sexo +
            "\nLongitudZancada: " + (longitudZancada / 100) + " mtos");
    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstad�sticas() {
        System.out.println("Estad�sticas" +
            "\n*********************************" +
            "\nDistancia recorrida toda la semana: " + 
            (totalDistanciaSemana / 100000) + " Km " +
            "\nDistancia recorrida fin de semana: " + 
            (totalDistanciaFinSemana / 100000) + " Km " +
            "\nN� pasos d�as laborables: " + totalPasosLaborables +
            "\nN� pasos S�BADO: " + totalPasosSabado +
            "\nN� pasos DOMINGO: " + totalPasosDomingo +
            "\nN� caminatas realizadas a partir de las 21h.: "
            + caminatasNoche +
            "\nTiempo total caminado en la semana: " 
            + (tiempo/60) + "h" + " y " + (tiempo % 60) + " minutos " +
            "\nD�a/s con m�s pasos caminados: " + diaMayorNumeroPasos());
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String srt = "";
        if (totalPasosLaborables > totalPasosSabado && totalPasosLaborables > 
        totalPasosDomingo) {
            srt = "LABORABLE";
        }
        else if (totalPasosLaborables == totalPasosSabado && totalPasosLaborables > totalPasosDomingo) {
            srt = "LABORABLE Y S�BADO";
        }
        else if (totalPasosLaborables == totalPasosDomingo && totalPasosLaborables > totalPasosSabado) {
            srt = "LABORABLE Y DOMINGO";
        }
        else if (totalPasosLaborables == totalPasosSabado && totalPasosLaborables == totalPasosDomingo && totalPasosSabado == totalPasosDomingo) {
            srt = "LABORABLES , S�BADO Y DOMINGO";
        }
        else if (totalPasosSabado > totalPasosLaborables && totalPasosSabado >
        totalPasosDomingo) {
            srt = "S�BADO";
        }
        else if(totalPasosDomingo > totalPasosLaborables && totalPasosDomingo >
        totalPasosSabado) {
            srt = "DOMINGO";
        }
        else if (totalPasosSabado == totalPasosDomingo && totalPasosSabado > totalPasosLaborables) {
            srt = "S�BADO Y DOMINGO";
        }
        return srt;
    }

    /**
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
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
