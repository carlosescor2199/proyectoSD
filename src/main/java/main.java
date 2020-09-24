import java.math.BigDecimal;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) {
        long tiempoInicial = System.currentTimeMillis();
        BigDecimal res = BigDecimal.ZERO;
        int hilo = 0;
        int numCores = Runtime.getRuntime().availableProcessors();
        int cont = 0;
        ArrayList<readFile> readFiles = new ArrayList<readFile>();

        for(int i=0; i < 13; i++){
            readFile rf = new readFile("C:/Users/carli/OneDrive/Documents/10mo semestre/Distribuidos/Archivos CSV/Sales_Data_"+ (i+1) +".csv");
            readFiles.add(rf);
        }

        System.out.println("Número de Cores: " + numCores);

        for(readFile rf : readFiles) {
            if(cont < numCores){
                System.out.println("Hilo " + (hilo+1) + " en ejecución");
                rf.start();
                cont++;
                hilo++;
            } else {
                for(int i=0; i<numCores; i++){
                    while (readFiles.get(i).isAlive());
                }
                System.out.println("Hilo " + (hilo+1) + " en ejecución");
                rf.start();
                cont++;
                hilo++;
                cont=0;
            }
        }


        for(readFile rf : readFiles) {
            while (rf.isAlive());
            res = res.add(rf.getSum());
        }

        System.out.println("El resultado es:" + res);

        long tiempoFinal = System.currentTimeMillis();

        long tiempoTotal = (tiempoFinal - tiempoInicial)/1000;

        System.out.println("El tiempo requerido es:" + tiempoTotal);
    }
}
