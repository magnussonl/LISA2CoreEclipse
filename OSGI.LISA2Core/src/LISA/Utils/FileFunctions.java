/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LISA.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joao
 */
public class FileFunctions {

    /**
     * Function that takes the name of the file as argument and reads the file
     * present in the project directory into a string
     *
     * @param fileName name of the file to be read
     * @return a string with the content of the file
     */
    public static String readFile(String fileName) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            String path = null;
            try {
                path = new java.io.File(".").getCanonicalPath();
            } catch (IOException ex) {
                Logger.getLogger(FileFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
            br = new BufferedReader(new FileReader(path + "\\" + fileName));
            try {

                String line = null;
                try {
                    line = br.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(FileFunctions.class.getName()).log(Level.SEVERE, null, ex);
                }
                while (line != null) {
                    sb.append(line);
                    sb.append("\n");
                    try {
                        line = br.readLine();
                    } catch (IOException ex) {
                        Logger.getLogger(FileFunctions.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(FileFunctions.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileFunctions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(FileFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sb.toString();
    }

    /**
     * Writes the content of toWrite in a file name by FileName
     *
     * @param FileName name of the file to be written
     * @param toWrite the content to be writte
     */
    public static void writeToFile(String fileName, String toWrite) {
        try {
            String path = new java.io.File(".").getCanonicalPath();
            File file = new File(path + "\\" + fileName);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            fw.write(toWrite);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the content of toWrite in a file name by FileName
     *
     * @param FileName name of the file to be written
     * @param toWrite the content to be write
     */
    public static void writeStringToFile(String fileName, String toWrite) {
        PrintWriter out = null;
        try {
            String path = new java.io.File(".").getCanonicalPath();
            File file = new File(path + "\\" + fileName);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            out = new PrintWriter(new BufferedWriter(new FileWriter(path + "\\" + fileName, true)));
            out.println(toWrite);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * Gets the directory of the current project
     *
     * @return
     */
    static public String getCurrentDir() {
        File d = new File(".");
        try {
            return d.getCanonicalPath();
        } catch (IOException ex) {
            return ".";
        }
    }

    /**
     * Validates if there is a file with name file
     * 
     * @param file name of the file to be validated
     * @return true if the name is valid, false otherwise
     */
    public static boolean isFilenameValid(String file) {
        File f = new File(file);
        return f.exists();
    }
}
