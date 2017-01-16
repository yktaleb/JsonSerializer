import serializer.JsonSerializer;
import writer.IndentedJsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        LinkedList<double[]> list = new LinkedList<double[]>();
        double[] array1={1.1,2.2,3.3};
        double[] array2 = {4.4,5.5,6.6};
        double[] array3 = {7.7,8.8,9.9};
        list.add(array1);
        list.add(array2);
        list.add(array3);


        try {
            IndentedJsonWriter writer = new IndentedJsonWriter(new FileWriter("test.json"));

            JsonSerializer serializer = new JsonSerializer();
            System.out.println(serializer.serialize(list));

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
