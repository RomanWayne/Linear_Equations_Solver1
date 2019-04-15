package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        /*ComplexNumber v1 = new ComplexNumber(4, 5);
        ComplexNumber v2 = new ComplexNumber(-1, 0);
        v1.multiply(v2);
        System.out.println(v1.toString());*/


        File file = new File(args[1]);
        try(PrintWriter printWriter = new PrintWriter(args[3]);
                Scanner scanner = new Scanner(file)){
        String[] record = scanner.nextLine().split(" ");
        List<Row> rowList = new ArrayList<>();
        int cntVar = Integer.parseInt(record[0]);
        int cntEq = Integer.parseInt(record[1]);
        for(int i = 0; i <cntEq; i++){
            List<Double> listD = new ArrayList<>();
            record = scanner.nextLine().split(" ");
            for (String s : record){
                listD.add(Double.parseDouble(s));
            }
            Row row = new Row(listD, i);
            rowList.add(row);
        }
        Matrix matrix = new Matrix(rowList);


        for(int i = 0; i < (cntVar < cntEq ? cntVar : cntEq); i++){
            if(matrix.toDiagonalView(i)) {
                for (int j = i + 1; j < cntEq; j++) {
                    matrix.plus(i, j);
                }
            }else {break;}
        }
        //System.out.println(matrix.toString());

        if(matrix.isExistsSolution()) {
            if(!matrix.isInfSolutions()) {
                for (int i = cntVar - 1; i > 0; i--) {
                    for (int j = i - 1; j >= 0; j--) {
                        matrix.plus(i, j);
                    }
                }
                printWriter.print(matrix.getSolution());
            } else {
                printWriter.print("Infinite solutions");
            }
        } else{
            printWriter.print("No solutions");
        }

        } catch (IOException e){
            System.out.println("файлы не найдены");
        }



    }
}
