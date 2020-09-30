import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;

public class Prueba {

    public static void main(String[] args) {
        try{
            BigDecimal sum = BigDecimal.ZERO;
            FileReader fr = new FileReader("C:\\Users\\carli\\OneDrive\\Escritorio\\datos.txt");
            BufferedReader reader = new BufferedReader(fr);

            String line = null;

            while ((line = reader.readLine()) != null) {
                sum = sum.add(new BigDecimal(line));
            }

            System.out.println(sum);

        }catch (Exception e){

        }
    }

}
