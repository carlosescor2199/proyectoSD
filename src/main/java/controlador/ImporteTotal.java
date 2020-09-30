package controlador;

import java.io.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ImporteTotal extends Thread{
    private BigDecimal importeVentasTotal = BigDecimal.ZERO;
    private String file;

    public BigDecimal getSum() {
        return importeVentasTotal;
    }


    public ImporteTotal(String file) {
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
                if(cont > 0){
                    BigDecimal num = new BigDecimal(parts[3]);
                    importeVentasTotal =  importeVentasTotal.add(num);
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
