package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class ImporteAnio extends Thread{
    Map<String, BigDecimal> listImpYear = new TreeMap<>();
    private String file;

    public Map<String, BigDecimal> getSum() {
        return listImpYear;
    }


    public ImporteAnio(String file) {
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
                    if(listImpYear.get(dateParts[0]) == null){
                        BigDecimal num = new BigDecimal(parts[3]);
                        listImpYear.put(dateParts[0], num);
                    } else {
                        BigDecimal newValor = listImpYear.get(dateParts[0]).add(new BigDecimal(parts[3]));
                        listImpYear.put(dateParts[0], newValor);
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
