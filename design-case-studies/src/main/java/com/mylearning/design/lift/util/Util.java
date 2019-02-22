package com.mylearning.design.lift.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * Utility class to be used for common utility functions like interacting with
 * {@link File}, parsing inputs, etc.
 * 

 */
public class Util {
  
  public static Integer getMin(Queue<Integer> list) {
    int min = Integer.MAX_VALUE;
    for (Integer l : list)
      min = Math.min(min, l);
    return min;
  }
  
  public static Integer getMax(Queue<Integer> list) {
    int max = Integer.MIN_VALUE;
    for (Integer l : list)
      max = Math.max(max, l);
    return max;
  }

  /**
   * Reads the input file and returns its content as a {@link List<String>} with
   * each line as a separate entry.
   * 
   * @param filepath
   *          - Path of the input file to read.
   * @return list - List of string holding the file data in-memory.
   */
  public static List<String> read(String filepath) {
    List<String> list = new ArrayList<String>();
    FileReader fr = null;
    try {
      fr = new FileReader(Objects.requireNonNull(Util.class.getClassLoader().getResource(filepath)).getFile());
      BufferedReader file = new BufferedReader(fr);
      String line = null;
      while ((line = file.readLine()) != null) {
        list.add(line.trim());
      }
      file.close();
    } catch (IOException e) {
      System.out.println("Error occurred while reading from: " + filepath);
      System.out.println("Error Message: " + e.getMessage());
    } finally {
      if (fr != null) {
        try {
          fr.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return list;
  }

  /**
   * Writes the given {@link List<String>} into a file at the given path. If the
   * file is not present, than it is created. If the file is present than it is
   * overwritten
   * 
   * @param list
   *          - List of string to write into file.
   * @param filepath
   *          - Path of the file at which the List is to be written.
   */
  public static void write(List<String> list, String filepath) {
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(filepath);
      Writer writer = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"));
      for (String line : list)
        writer.write(line + "\n");
      writer.flush();
      writer.close();
      System.out.println("File successfully written to: " + filepath);
    } catch (IOException e) {
      System.out.println("Error occurred while writing to: " + filepath);
      System.out.println("Error Message: " + e.getMessage());
    } finally {
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * Deletes the file at the given file path.
   * 
   * @param filepath
   *          - Path of the file to be delete.
   */
  public static void delete(String filepath) {
    try {
      new File(filepath).delete();
    } catch (Exception e) {
      // Do nothing as file doesn't exists.
    }
  }

  /**
   * Checks if a file is present at the given file path.
   * 
   * @param filepath
   *          - Path of the file to be checked for existence.
   */
  public static boolean exists(String filepath) {
    try {
      return new File(Objects.requireNonNull(Util.class.getClassLoader().getResource(filepath)).getFile()).isFile();
    } catch (Exception e) {
      // Do nothing.
    }
    return false;
  }

  /**
   * Checks if a given file path is valid. It can either be a invalid file path
   * a valid directory path but not a file. Both cases should return false.
   * Hence file might not exists but the path should be valid for creating a
   * file.
   * 
   * @param filepath
   *          - File path to be validated.
   */
  public static boolean isValidFilePath(String filepath) {
    try {
      // Would signify that file path is a directory.
      if (filepath.endsWith("/"))
        return false;

      StringBuilder sb = new StringBuilder();
      String[] fsegments = filepath.split("/");
      for (int i = 0; i < fsegments.length - 1; i++)
        sb.append(fsegments[i]).append("/");
      return new File(sb.toString()).isDirectory();
    } catch (Exception e) {
      return false;
    }
  }

}
