import java.io.*;
import java.math.BigDecimal;

public class readFile extends Thread{
    private BigDecimal sum = BigDecimal.ZERO;
    private String file;

    public BigDecimal getSum() {
        return sum;
    }


    public readFile(String file) {
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
                    sum =  sum.add(num);
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
