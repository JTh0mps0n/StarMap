import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class Runner {
    public static void main(String[] args) throws IOException{

        HashTable[] tables = readCoords(new File("stars.txt"));
        LinkedList<Integer> keys = new LinkedList<>();

        Scanner fileReader = new Scanner(new File("stars.txt"));
        while (fileReader.hasNextLine()){
            String line = fileReader.nextLine();
            Scanner lineReader = new Scanner(line);
            Tuple<Double> coords = new Tuple(lineReader.nextDouble(), lineReader.nextDouble());
            lineReader.nextDouble();
            int id = lineReader.nextInt();
            double magnitude = lineReader.nextDouble();
            int harvardID = lineReader.nextInt();
            keys.add(id);
        }

        plotSquares(tables, keys);

        File[] files = {new File("BigDipper_lines.txt"),
                new File("Bootes_lines.txt"),
                new File("Cas_lines.txt"),
                new File("Cyg_lines.txt"),
                new File("Gemini_lines.txt"),
                new File("Hydra_lines.txt"),
                new File("UrsaMajor_lines.txt"),
                new File("UrsaMinor_lines.txt")};
        drawLines(files, tables);
    }

    public static void drawLines(File[] files, HashTable[] tables)throws IOException{
        StdDraw.setPenColor(StdDraw.YELLOW);
        for (int i = 0; i < files.length; i++) {
            Scanner scanner = new Scanner(files[i]);

            while(scanner.hasNext()){
                String line = scanner.nextLine();
                Scanner lineReader = new Scanner(line);
                lineReader.useDelimiter(",");
                String starNameOne = lineReader.next();
                Tuple<Double> one = (Tuple) tables[0].get((Integer)tables[2].get(starNameOne));

                String starNameTwo = lineReader.next();

                Tuple<Double> two = (Tuple) tables[0].get((Integer)tables[2].get(starNameTwo));
                StdDraw.line(one.x, one.y, two.x, two.y);
            }
        }
    }

    public static HashTable[] readCoords(File file) throws IOException {
        HashTable[] tables = new HashTable[3];
        tables[0] = new HashTable(10007);
        tables[1] = new HashTable(10007);
        tables[2] = new HashTable(10007);
        Scanner fileReader = new Scanner(file);

        while (fileReader.hasNextLine()){
            String line = fileReader.nextLine();
            Scanner lineReader = new Scanner(line);
            Tuple<Double> coords = coordsToPixels(lineReader.nextDouble(), lineReader.nextDouble(), 1);
            lineReader.nextDouble();
            int id = lineReader.nextInt();
            double magnitude = lineReader.nextDouble();
            int harvardID = lineReader.nextInt();

            tables[0].put(id, coords);
            tables[1].put(id, magnitude);
            if (lineReader.hasNext()){
                lineReader.useDelimiter("");
                lineReader.next();
            }

            lineReader.useDelimiter("; ");
            while(lineReader.hasNext()){
                String name = lineReader.next();
                tables[2].put(name, id);
            }
        }

        return tables;
    }


    public static Tuple coordsToPixels(double orig_x, double orig_y, int size){

        double x = 1 + orig_x;
        double y = 1 + orig_y;

        x = x * size / 2;
        y = y * size / 2;

        Tuple<Double> coords = new Tuple<>(x, y);

        return coords;
    }

    public static void plotSquares(HashTable[] tables, LinkedList keys){
        StdDraw.setCanvasSize(700, 700);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        HashTable pairs = tables[0];
        HashTable magnitudes = tables[1];

            for (int i = (int) keys.remove(); !keys.isEmpty() ; i = (int) keys.remove()) {
                double magnitude = (double) magnitudes.get(i)*3/9500;
                if (magnitude < 0){
                    magnitude = Math.abs(magnitude);
                }
                StdDraw.filledCircle(((Tuple<Double>)pairs.get(i)).getX(), ((Tuple<Double>)pairs.get(i)).getY(), magnitude);
            }
    }
}
