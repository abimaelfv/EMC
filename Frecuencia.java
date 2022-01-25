
import java.util.Arrays;

// Como ejemplo debe usar el ejercicio de los tiempos quincenales, en dólares recopilados de una muestra de 45 empleados

public class Frecuencia {

    private double[] tiempos = new double[] {21.3,21.9,20.5,15.8,12.2,20.5,19.7,26.8,18.4,22.3,18.5,17.3,15.1,22.8,8.3,18,19.6,17.9,24.6,23.9,15.8,
                                            12.3,11,26.4,13.4,16.2,22.7,22.7,13.4,23,11.2,19.1};
    private int cantidad = tiempos.length;
    private double Maximo;
    private double Minimo;
    private double Rango;
    private double Intervalo;
    private double Amplitud;
    private double[][] tabla;


    // 1. Determinar la cantidad de datos (N)

    // 2. Determinar los valores máximo y mínimo
    public static double OP_Maximo(double[] tiempos){
        double maxNum = tiempos[0];
        for (double j : tiempos) {
            if (j > maxNum)
                maxNum = j;
        }

        return maxNum;
    }

    public static double OP_Minimo(double[] tiempos){
        double minNum = tiempos[0];
        for (double j : tiempos) {
            if (j < minNum)
                minNum = j;
        }

        return minNum;
    }

    // 3. Determinar el rango (R) de valores.
    public static double OP_Rango(double Max, double Min){
        double rango = (Max - Min) + 0.1;

        return rango;
    }

    // 4. Determinar el número de intervalos (K) según la regla de Sturges.
    public static int OP_Intervalo(int N){
        int redondeo;
        double interTemp = 1+ 3.3*Math.log10(N);

        if(interTemp % 1 == 0){
            redondeo = (int) interTemp;
        }else{
            redondeo = (int) interTemp + 1;
        }
        
        return  redondeo;
    }

    // 5. Determinar la amplitud (A). 
    public static double OP_Amplitud(double rango, double intervalo){
        double amplitudTemp = rango/intervalo;

        double amplitud = amplitudTemp;
        return amplitud;
    }

    public void realizarOperaciones(){
        Maximo = OP_Maximo(tiempos);
        Minimo = OP_Minimo(tiempos);
        Rango = OP_Rango(Maximo, Minimo);
        Intervalo = OP_Intervalo(cantidad);
        Amplitud = OP_Amplitud(Rango, Intervalo);

    }

    public void imprimirResultados(){
        System.out.println("\nDATOS: \n" + Arrays.toString(tiempos));
        System.out.println("\nCantidad de datos: " + cantidad);
        System.out.println("Valor Maximo: " + Maximo);
        System.out.println("Valor Minimo: " + Minimo);
        System.out.println("Rango de valores: " + Rango);
        System.out.println("Numero de Intervalos: " + Intervalo);
        System.out.println("Amplitud: " + Amplitud);
    }

    // Tabla de frecuencias
    public void ejecutarTabla(){
        tabla = new double[(int)Intervalo][8];
        double min=Minimo, max=Minimo + Amplitud, Fi=0;
        double Hi = 0;

        for (int i = 0; i < tabla.length; i++) {
            int cant = 0;
            for (int j = 0; j < tiempos.length; j++){
                if (tiempos[j] >= min && tiempos[j] < max)
                    cant++;
            }

            Fi += cant;
            Hi += (double)cant/cantidad;

            tabla[i][0] = i+1;
            tabla[i][1] = min;
            tabla[i][2] = max;
            tabla[i][3] = cant;
            tabla[i][4] = Fi;
            tabla[i][5] = Math.round(((double)cant/cantidad)*100.0)/100.0;
            tabla[i][6] = Math.round(((double)Hi)*100.0)/100.0;
            tabla[i][7] = Math.round((float)(min + max)/2);

            min += Amplitud;
            max += Amplitud;
        }

        System.out.println("\nTABLA DE FRECUENCIAS:\n N      Minimo  Maximo   fi      Fi       hi      Hi      xi");
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {

                if (j != 5 && j != 6) {
                    System.out.print(" " + (int)tabla[i][j] + "\t");
                }else{
                    System.out.print(" " + tabla[i][j] + "\t");
                }
                
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Frecuencia frecuencia = new Frecuencia();

        frecuencia.realizarOperaciones();
        frecuencia.imprimirResultados();
        frecuencia.ejecutarTabla();
    }


}