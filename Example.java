package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Example {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите максимальную грузоподъемность машины");
        String setProperty = System.setProperty("console.encoding", "Cp866");

        int wight = scanner.nextInt();
        if (wight < 0) {
            System.out.println("Введено неправильное число. Попробуйте снова");
        } else {
            // System.out.println(wight) ;
        }
        String res;

        String[] resM;
        String[] resM_ = null;
        String name;
        Integer wight2, price;
        System.out.println("Введите предметы для перевозки в формате название_предмета/вес/цена");
        Counteiner counteiner;
        List<Counteiner> massPrice = new ArrayList<>();
        List<Counteiner> massWight = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String vvod = br.readLine();
//            System.out.println(vvod);
            resM_ = vvod.split(" ");
        } catch (IOException ex) {
            Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (String res_ : resM_) {

            resM = res_.split("/");
            if (resM.length != 3) {
                System.out.println("Формат записи не соответствует шаблону.");
                continue;
            }
            name = resM[0];
            try {
                wight2 = Integer.valueOf(resM[1]);
                price = Integer.valueOf(resM[2]);
                counteiner = new Counteiner(name, wight2, price);

                massPrice.add(counteiner);
            } catch (NumberFormatException e) {
                System.out.println("Введенное значение - не целое число:" + resM[1] + " " + resM[2]);
            }
        }//end while
        Comparator<Counteiner> compareByIdPrice = (Counteiner o1, Counteiner o2) -> o1.getPrice().compareTo(o2.getPrice());
        Collections.sort(massPrice, compareByIdPrice.reversed());
        int ms = 0;
        boolean flag = true;
        while (flag) {
//max цена
            counteiner = massPrice.get(0);
            ms = ms + counteiner.getWight2();
            if (ms <= wight) {

                massWight.add(counteiner);
            } else {
                ms = ms - counteiner.getWight2();
            }
            massPrice.remove(counteiner);
            if (massPrice.isEmpty()) {
                flag = false;
            }
            Collections.sort(massPrice, compareByIdPrice.reversed());
            
        }
        String resOut = "";
            int sum = 0;
            for (Counteiner c : massWight) {
               // System.out.println(c.getName());
               resOut = resOut+c.getName()+" " ; 
            sum=sum+c.getPrice();
            }
        resOut = resOut +sum;
        System.out.println(resOut);
    }

    public static class Counteiner {

        private String name;
        private Integer wight2, price;

        public Counteiner(String name, Integer wight2, Integer price) {
            this.name = name;
            this.wight2 = wight2;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public Integer getWight2() {
            return wight2;
        }

        public Integer getPrice() {
            return price;
        }
    }
}
