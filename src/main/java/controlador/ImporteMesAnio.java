package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class ImporteMesAnio extends Thread{
    Map<String, BigDecimal> listImpMesAnio = new TreeMap<>();
    private BigDecimal importeMesAnio = BigDecimal.ZERO;
    private String file;

    public Map<String, BigDecimal> getSum() {
        return listImpMesAnio;
    }


    public ImporteMesAnio(String file) {
        this.file = file;
    }

    @Override
    public void run() {
        try {
            int cont = 0;
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = null;

            while ((line = reader.readLine()) != null) {
                String [] parts = line.split(",");
                String[] dateParts = parts[2].split("-");

                if(cont > 0){
                    String key = dateParts[0] + "-" + dateParts[1];
                    if(listImpMesAnio.get(key) == null){
                        BigDecimal num = new BigDecimal(parts[3]);
                        listImpMesAnio.put(key, num);
                    } else {
                        BigDecimal newValor = listImpMesAnio.get(key).add(new BigDecimal(parts[3]));
                        listImpMesAnio.put(key, newValor);
                    }
                }
                cont++;
            }
            reader.close();
            fr.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
