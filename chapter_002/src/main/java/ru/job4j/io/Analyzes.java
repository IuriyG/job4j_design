package ru.job4j.io;

import java.io.*;

/**
 * @author Iuriy Gaydarzhi.
 * @since 31.05.2022
 */
public class Analyzes {

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unavailable(String source, String target) {
        try (BufferedReader br = new BufferedReader(new FileReader(source));
             PrintWriter pr = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            String line;
            boolean marker = false;
            while ((line = br.readLine()) != null) {
                if (line.contains("400") || line.contains("500")) {
                    if (!marker) {
                        String[] in = line.split(" ");
                        pr.print(in[1] + ";");
                        marker = true;
                    }
                } else if (marker) {
                    String[] in = line.split(" ");
                    pr.println(in[1] + ";");
                    marker = false;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
