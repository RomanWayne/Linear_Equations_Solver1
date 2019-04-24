package solver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
     /*   ComplexNumber v1 = new ComplexNumber(1, 0);
        ComplexNumber v2 = new ComplexNumber(-1, 3);
        v1.multiply(v2);
        System.out.println(v1.toString());*/


        try(BufferedReader reader = new BufferedReader(new FileReader(args[1]));
            FileWriter fileWriter = new FileWriter(args[3])){
        String[] record = reader.readLine().split(" ");
        List<ComplexRow> rowList = new ArrayList<>();
        int cntVar = Integer.parseInt(record[0]);
        int cntEq = Integer.parseInt(record[1]);
        for(int i = 0; i <cntEq; i++){
            /*List<Double> listD = new ArrayList<>();
            record = reader.readLine().split(" ");
            for (String s : record){
                listD.add(Double.parseDouble(s));
            }
            Row row = new Row(listD, i);
            rowList.add(row);*/
            List<ComplexNumber> listD = new ArrayList<>();
            record = reader.readLine().split(" ");
            for(String s : record){
                listD.add(new ComplexNumber(s));
            }
            ComplexRow row = new ComplexRow(listD, i);
            rowList.add(row);

        }



        ComplexMatrix matrix = new ComplexMatrix(rowList);
        for(int i = 0; i < (cntVar < cntEq ? cntVar : cntEq); i++){
            System.out.println(matrix);
            if(matrix.toDiagonalView(i)) {
                System.out.println(matrix);
                for (int j = i + 1; j < cntEq; j++) {
                    System.out.println(matrix);
                    matrix.plus(i, j);
                }
            }else {break;}
        }

        if(matrix.isExistsSolution()) {
            if(!matrix.isInfSolutions()) {
                for (int i = cntVar - 1; i > 0; i--) {
                    for (int j = i - 1; j >= 0; j--) {
                        System.out.println(matrix);
                        matrix.plus(i, j);
                    }
                }
                fileWriter.write(matrix.getSolution());
            } else {
                fileWriter.write("Infinite solutions");
            }
        } else{
            fileWriter.write("No solutions");
        }

        } catch (IOException e){
            System.out.println("файлы не найдены");
        }



    }
}
