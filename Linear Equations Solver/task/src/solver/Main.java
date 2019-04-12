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
    public static void main(String[] args) throws FileNotFoundException {
        //File file = new File("Linear Equations Solver/task/in.txt");
        File file = new File(args[1]);
        try(PrintWriter printWriter = new PrintWriter(args[3]);
                Scanner scanner = new Scanner(file);){
        String[] record = scanner.nextLine().split(" ");
        List<Row> rowList = new ArrayList<Row>();
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
        //System.out.println(matrix.toString());


        for(int i = 0; i < cntVar; i++){
            if(matrix.toDiagonalView(i)) {
                for (int j = i + 1; j < cntVar; j++) {
                    matrix.plus(i, j);
                }
            }else {break;}
        }

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
            //e.printStackTrace();
            System.out.println("файлы не найдены");
        }



    }
}
