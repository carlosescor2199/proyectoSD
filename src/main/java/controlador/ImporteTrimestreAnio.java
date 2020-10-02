package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class ImporteTrimestreAnio extends Thread {
    Map<String, BigDecimal> listImpYear = new TreeMap<>();
    private String file;

    public Map<String, BigDecimal> getSum() {
        return listImpYear;
    }


    public ImporteTrimestreAnio(String file) {
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
                    if(dateParts[1].equals("01") || dateParts[1].equals("02") || dateParts[1].equals("03")){
                        if(listImpYear.get(dateParts[0]+"-1T") == null){
                            BigDecimal num = new BigDecimal(parts[3]);
                            listImpYear.put(dateParts[0]+"-1T", num);
                        } else {
                            BigDecimal newValor = listImpYear.get(dateParts[0]+"-1T").add(new BigDecimal(parts[3]));
                            listImpYear.put(dateParts[0]+"-1T", newValor);
                        }
                    } else if(dateParts[1].equals("04") || dateParts[1].equals("05") || dateParts[1].equals("06")){
                        if(listImpYear.get(dateParts[0]+"-2T") == null){
                            BigDecimal num = new BigDecimal(parts[3]);
                            listImpYear.put(dateParts[0]+"-2T", num);
                        } else {
                            BigDecimal newValor = listImpYear.get(dateParts[0]+"-2T").add(new BigDecimal(parts[3]));
                            listImpYear.put(dateParts[0]+"-2T", newValor);
                        }
                    } else if(dateParts[1].equals("07") || dateParts[1].equals("08") || dateParts[1].equals("09")){
                        if(listImpYear.get(dateParts[0]+"-3T") == null){
                            BigDecimal num = new BigDecimal(parts[3]);
                            listImpYear.put(dateParts[0]+"-3T", num);
                        } else {
                            BigDecimal newValor = listImpYear.get(dateParts[0]+"-3T").add(new BigDecimal(parts[3]));
                            listImpYear.put(dateParts[0]+"-3T", newValor);
                        }
                    } else if(dateParts[1].equals("10") || dateParts[1].equals("11") || dateParts[1].equals("12")){
                        if(listImpYear.get(dateParts[0]+"-4T") == null){
                            BigDecimal num = new BigDecimal(parts[3]);
                            listImpYear.put(dateParts[0]+"-4T", num);
                        } else {
                            BigDecimal newValor = listImpYear.get(dateParts[0]+"-4T").add(new BigDecimal(parts[3]));
                            listImpYear.put(dateParts[0]+"-4T", newValor);
                        }
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
