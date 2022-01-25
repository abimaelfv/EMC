// Inversa de una matriz de n x n en java, por la adjunta
import javax.swing.JOptionPane;

public class Inversa {

    private double[][] Matriz;
    private double[][] matrizresultado;
    private int row;
    private int col;

    public Inversa (){
        row = Integer.parseInt(JOptionPane.showInputDialog("Ingrese fila: "));
        col = Integer.parseInt(JOptionPane.showInputDialog("Ingrese columna: "));
        
        Matriz = new double[row][col];
        matrizresultado = new double[row][col];
    }

    public void setMatriz(){
        for (int i = 0; i < Matriz.length; i++) {
            for (int j = 0; j < Matriz[i].length; j++) {
                Matriz[i][j] = Integer.parseInt(JOptionPane.showInputDialog("ingrese valor Matriz A: "));
            }
        }
    }

    public double[][] matrizInversa(double[][] matriz) {
        double det=1/determinante(matriz);
        double[][] nmatriz=matrizAdjunta(matriz);
        multiplicarMatriz(det,nmatriz);
        return nmatriz;
    }
   
    public void multiplicarMatriz(double n, double[][] matriz) {
        for(int i=0;i<matriz.length;i++)
            for(int j=0;j<matriz.length;j++)
                matriz[i][j]*=n;
    }
   
    public static double[][] matrizAdjunta(double [][] matriz){
        return matrizTranspuesta(matrizCofactores(matriz));
    }
   
    public static double[][] matrizCofactores(double[][] matriz){
        double[][] nm=new double[matriz.length][matriz.length];
        for(int i=0;i<matriz.length;i++) {
            for(int j=0;j<matriz.length;j++) {
                double[][] det=new double[matriz.length-1][matriz.length-1];
                double detValor;
                for(int k=0;k<matriz.length;k++) {
                    if(k!=i) {
                        for(int l=0;l<matriz.length;l++) {
                            if(l!=j) {
                            int indice1=k<i ? k : k-1 ;
                            int indice2=l<j ? l : l-1 ;
                            det[indice1][indice2]=matriz[k][l];
                            }
                        }
                    }
                }
                detValor=determinante(det);
                nm[i][j]=detValor * (double)Math.pow(-1, i+j+2);
            }
        }
        return nm;
    }
   
    public static double[][] matrizTranspuesta(double [][] matriz){
        double[][]nuevam=new double[matriz[0].length][matriz.length];
        for(int i=0; i<matriz.length; i++)
        {
            for(int j=0; j<matriz.length; j++)
                nuevam[i][j]=matriz[j][i];
        }
        return nuevam;
    }
   
    public static double determinante(double[][] matriz){
        double det;
        if(matriz.length==2)
        {
            det=(matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
            return det;
        }
        double suma=0;
        for(int i=0; i<matriz.length; i++){
        double[][] nm=new double[matriz.length-1][matriz.length-1];
            for(int j=0; j<matriz.length; j++){
                if(j!=i){
                    for(int k=1; k<matriz.length; k++){
                    int indice=-1;
                    if(j<i)
                    indice=j;
                    else if(j>i)
                    indice=j-1;
                    nm[indice][k-1]=matriz[j][k];
                    }
                }
            }
            if(i%2==0)
            suma+=matriz[i][0] * determinante(nm);
            else
            suma-=matriz[i][0] * determinante(nm);
        }
        return suma;
    }

    public void getMatriz(){
        System.out.println("\nMATRIZ INGRESADO: ");
        for (int i = 0; i < Matriz.length; i++) {
            for (int j = 0; j < Matriz[i].length; j++) {
                System.out.print(Matriz[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void ejecutarInvertir(){
        System.out.println("\nINVERSA DE LA MATRIZ: ");
        matrizresultado = matrizInversa(Matriz);
        for (int i = 0; i < matrizresultado.length; i++) {
            for (int j = 0; j < matrizresultado[i].length; j++) {

                if (matrizresultado[i][j] == 0) {
                    System.out.print(-1 * matrizresultado[i][j]+" ");
                }else{
                    System.out.print(matrizresultado[i][j]+" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args){

        Inversa inversa = new Inversa();

        inversa.setMatriz();
        inversa.getMatriz();
        inversa.ejecutarInvertir();

        System.exit(0);
    }
}
