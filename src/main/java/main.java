import controlador.ImporteMesAnio;
import controlador.ImporteTotal;
import controlador.ImporteAnio;
import controlador.ImporteTrimestreAnio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class main {
    private static BigDecimal importeTotal = BigDecimal.ZERO;
    private static ArrayList<BigDecimal> anios = new ArrayList<>();
    private static Map<String, BigDecimal> mesAnios = new TreeMap<>();
    private static Map<String, BigDecimal> trimestreAnios = new TreeMap<>();

    private static void importeTotal() {
        long tiempoInicial = System.currentTimeMillis();
        BigDecimal res = BigDecimal.ZERO;
        int hilo = 0;
        int numCores = Runtime.getRuntime().availableProcessors();
        int cont = 0;
        ArrayList<ImporteTotal> ImporteTotals = new ArrayList<ImporteTotal>();

        for(int i=0; i < 13; i++){
            ImporteTotal it = new ImporteTotal("C:/Users/carli/OneDrive/Documents/10mo semestre/Distribuidos/Archivos CSV/Sales_Data_"+ (i+1) +".csv");
            ImporteTotals.add(it);
        }

        System.out.println("Número de Cores: " + numCores);

        for(ImporteTotal it : ImporteTotals) {
            if(cont < numCores){
                System.out.println("Hilo " + (hilo+1) + " en ejecución");
                it.start();
                cont++;
                hilo++;
            } else {
                for(int i=0; i<numCores; i++){
                    while (ImporteTotals.get(i).isAlive());
                }
                System.out.println("Hilo " + (hilo+1) + " en ejecución");
                it.start();
                cont++;
                hilo++;
                cont=0;
            }
        }


        for(ImporteTotal it : ImporteTotals) {
            while (it.isAlive());
            res = res.add(it.getSum());
        }

        System.out.println("El resultado es:" + res);

        long tiempoFinal = System.currentTimeMillis();

        long tiempoTotal = (tiempoFinal - tiempoInicial)/1000;

        System.out.println("El tiempo requerido es:" + tiempoTotal);
    }

    private static BigDecimal importeAnio(String anio) {
        long tiempoInicial = System.currentTimeMillis();
        int hilo = 0;
        int numCores = Runtime.getRuntime().availableProcessors();
        int cont = 0;
        ArrayList<ImporteAnio> importeAnios = new ArrayList<ImporteAnio>();
        Map<String, BigDecimal> result = new TreeMap<>();

        for(int i=0; i < 13; i++){
            ImporteAnio ima = new ImporteAnio("C:/Users/carli/OneDrive/Documents/10mo semestre/Distribuidos/Archivos CSV/Sales_Data_"+ (i+1) +".csv");
            importeAnios.add(ima);
        }

        System.out.println("Número de Cores: " + numCores);

        for(ImporteAnio ima : importeAnios) {
            if(cont < numCores){
                System.out.println("Hilo " + (hilo+1) + " en ejecución");
                ima.start();
                cont++;
                hilo++;
            } else {
                for(int i=0; i<numCores; i++){
                    while (importeAnios.get(i).isAlive());
                }
                System.out.println("Hilo " + (hilo+1) + " en ejecución");
                ima.start();
                cont++;
                hilo++;
                cont=0;
            }
        }




        for(ImporteAnio ima : importeAnios) {
            while (ima.isAlive());
            for(String key : ima.getSum().keySet()){
                if(result.containsKey(key)){
                    BigDecimal num = result.get(key).add(ima.getSum().get(key));
                    result.put(key, num);
                } else {
                    BigDecimal num = ima.getSum().get(key);
                    result.put(key, num);
                }
            }
        }

        long tiempoFinal = System.currentTimeMillis();

        long tiempoTotal = (tiempoFinal - tiempoInicial)/1000;

        System.out.println("El tiempo requerido es:" + tiempoTotal);
        return result.get(anio);
    }

    private static BigDecimal importeMesAnio(String anioMes) {
        long tiempoInicial = System.currentTimeMillis();
        BigDecimal res = BigDecimal.ZERO;
        int hilo = 0;
        int numCores = Runtime.getRuntime().availableProcessors();
        int cont = 0;
        ArrayList<ImporteMesAnio> importeMesAnios = new ArrayList<ImporteMesAnio>();
        Map<String, BigDecimal> result = new TreeMap<>();

        for(int i=0; i < 13; i++){
            ImporteMesAnio ia = new ImporteMesAnio("C:/Users/carli/OneDrive/Documents/10mo semestre/Distribuidos/Archivos CSV/Sales_Data_"+ (i+1) +".csv");
            importeMesAnios.add(ia);
        }

        System.out.println("Número de Cores: " + numCores);

        for(ImporteMesAnio ia : importeMesAnios) {
            if(cont < numCores){
                System.out.println("Hilo " + (hilo+1) + " en ejecución");
                ia.start();
                cont++;
                hilo++;
            } else {
                for(int i=0; i<numCores; i++){
                    while (importeMesAnios.get(i).isAlive());
                }
                System.out.println("Hilo " + (hilo+1) + " en ejecución");
                ia.start();
                cont++;
                hilo++;
                cont=0;
            }
        }

        for(ImporteMesAnio ima : importeMesAnios) {
            while (ima.isAlive());
            for(String key : ima.getSum().keySet()){
                BigDecimal num;
                if(result.containsKey(key)){
                    num = result.get(key).add(ima.getSum().get(key));
                    result.put(key, num);
                } else {
                    num = ima.getSum().get(key);
                    result.put(key, num);
                }

            }
        }

        long tiempoFinal = System.currentTimeMillis();

        long tiempoTotal = (tiempoFinal - tiempoInicial)/1000;

        System.out.println("El tiempo requerido es:" + tiempoTotal);
        return result.get(anioMes);
    }

    private static BigDecimal importeTrimestreAnio(String anioTrimestre) {
        long tiempoInicial = System.currentTimeMillis();
        BigDecimal res = BigDecimal.ZERO;
        int hilo = 0;
        int numCores = Runtime.getRuntime().availableProcessors();
        int cont = 0;
        ArrayList<ImporteTrimestreAnio> importeTrimestreAnios = new ArrayList<ImporteTrimestreAnio>();
        Map<String, BigDecimal> result = new TreeMap<>();

        for(int i=0; i < 13; i++){
            ImporteTrimestreAnio ita = new ImporteTrimestreAnio("C:/Users/carli/OneDrive/Documents/10mo semestre/Distribuidos/Archivos CSV/Sales_Data_"+ (i+1) +".csv");
            importeTrimestreAnios.add(ita);
        }

        System.out.println("Número de Cores: " + numCores);

        for(ImporteTrimestreAnio ita : importeTrimestreAnios) {
            if(cont < numCores){
                System.out.println("Hilo " + (hilo+1) + " en ejecución");
                ita.start();
                cont++;
                hilo++;
            } else {
                for(int i=0; i<numCores; i++){
                    while (importeTrimestreAnios.get(i).isAlive());
                }
                System.out.println("Hilo " + (hilo+1) + " en ejecución");
                ita.start();
                cont++;
                hilo++;
                cont=0;
            }
        }




        for(ImporteTrimestreAnio ita : importeTrimestreAnios) {
            while (ita.isAlive());
            for(String key : ita.getSum().keySet()){
                BigDecimal num;
                if(result.containsKey(key)){
                    num = result.get(key).add(ita.getSum().get(key));
                    result.put(key, num);
                } else {
                    num = ita.getSum().get(key);
                    result.put(key, num);
                }

            }
        }

        long tiempoFinal = System.currentTimeMillis();

        long tiempoTotal = (tiempoFinal - tiempoInicial)/1000;

        System.out.println("El tiempo requerido es:" + tiempoTotal);
        return result.get(anioTrimestre);
    }


    public static void main(String[] args) {
        BigDecimal res = BigDecimal.ZERO;
        Scanner entry = new Scanner(System.in);
        int op = -1;
        while (op != 0){
        System.out.println("Bienvenido al Menu");
        System.out.println("1. Mostrar importe total");
        System.out.println("2. Buscar Importe por año");
        System.out.println("3. Buscar Importe por año-mes");
        System.out.println("4. Buscar Importe por año-trimestre");
        System.out.print("op:");
        op = entry.nextInt();

            switch (op){
                case 1:
                    importeTotal();
                    break;
                case 2:
                    System.out.println("Buscar por AÑO:");
                    String buscarAnio = entry.nextLine();
                    buscarAnio = entry.nextLine();
                    res = importeAnio(buscarAnio);
                    if(res != null) {
                        System.out.println("El resultado es: " + res);
                    } else {
                        System.out.println("Año no encontrado");
                    }
                    break;
                case 3:
                    System.out.println("Buscar por AÑO-MES:");
                    String buscarAnioMes = entry.nextLine();
                    buscarAnioMes = entry.nextLine();
                    res = importeMesAnio(buscarAnioMes);
                    if(res != null) {
                        System.out.println("El resultado es: " + res);
                    } else {
                        System.out.println("Año no encontrado");
                    }
                    break;
                case 4:
                    System.out.println("Buscar por AÑO-TRIMESTRE [AÑO-{Numero trimestre}T]:");
                    String buscarAnioTrimestre = entry.nextLine();
                    buscarAnioTrimestre = entry.nextLine();
                    res = importeTrimestreAnio(buscarAnioTrimestre);
                    if(res != null) {
                        System.out.println("El resultado es: " + res);
                    } else {
                        System.out.println("Año no encontrado");
                    }
                    break;
                default:
                    System.out.println("Ingrese opcion valida");
            }
        }
    }
}
