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

public class FileHelperDates {


    public static final String FILENAME2 = "dateslist.dat";

    //writes new data into the dates file
    public static void writeData(List<String> dates, Context context) {

        try {
            FileOutputStream fos = context.openFileOutput(FILENAME2, context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dates);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //reads data from dates file
    public static List<String> readData(Context context) {
        List<String> dateList = null;
        try {
            FileInputStream fis = context.openFileInput(FILENAME2);
            ObjectInputStream ois = new ObjectInputStream(fis);
            //gets the dates array from the file
            dateList = (List<String>) ois.readObject();
        } catch (FileNotFoundException e) {
            //if there is no existing file, creates new empty array list
            dateList = new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return dateList;
    }
}