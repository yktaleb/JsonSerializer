import serializer.JsonSerializer;
import writers.IndentedJsonWriter;
import writers.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        List list1 = new ArrayList<double[]>();
        double[] array1={1.1,2.2,3.3};
        double[] array2 = {4.4,5.5,6.6};
        double[] array3 = {7.7,8.8,9.9};
        list1.add(array1);
        list1.add(array2);
        list1.add(array3);

        List list2 = new ArrayList<double[]>();
        double[] array4={1.1,2.2,3.3};
        list2.add(array4);

        List list3 = new ArrayList<double[]>();
        double[] array5 = {};
        list3.add(array5);

        Map map = new HashMap<String, List<Double[]>>();
        map.put("1", null);
        map.put("Second", list1);
        map.put("333", null);
        map.put("4", list2);
        map.put("Five", list3);

        Author author = new Author("Джоан Роулинг", "Великобритания", 1965);
        Book book = new Book("Harry Potter and the Half-Blood Prince", 2005, author);

        int[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {},
                {7, 8, 9}
        };

        TestClass testClass = new TestClass("hello", 2, null, new double[]{2.2, 3.4, 0.11, -2,9}, 100, 937, "world", true, book, false, map, array);

        try {
            JsonWriter writer = new IndentedJsonWriter(new FileWriter("test.json"));

            JsonSerializer serializer = new JsonSerializer();
            serializer.serialize(testClass, writer);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
