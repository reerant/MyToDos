package com.myapp.mytodos;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    public static final String FILENAME = "tasklist.dat";

    //writes new data into the tasks file
    public static void writeData(List<String> tasks, Context context) {

        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //reads data from tasks file
    public static List<String> readData(Context context) {
        List<String> taskList = null;
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            //gets the tasks array from the file
            taskList = (List<String>) ois.readObject();
        } catch (FileNotFoundException e) {
            //if there is no existing file, creates new empty array list
            taskList = new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return taskList;
    }
}