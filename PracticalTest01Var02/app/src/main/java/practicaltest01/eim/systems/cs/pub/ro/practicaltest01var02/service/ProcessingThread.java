package practicaltest01.eim.systems.cs.pub.ro.practicaltest01var02.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

import practicaltest01.eim.systems.cs.pub.ro.practicaltest01var02.general.Constants;

/**
 * Created by MARY on 4/1/2018.
 */

public class ProcessingThread extends Thread {

    Context context;
    boolean isRunning = true;
    Random random = new Random();
    int suma;
    int dif;

    public ProcessingThread(Context context, int firstNumber, int secondNumber) {
        this.context =  context;
        suma = firstNumber + secondNumber;
        dif = firstNumber - secondNumber;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            // trm 2 msj cu intarziere de 5 sec intre ele
            sendMessage();
            Log.d("[sendMessage]", "SUMA");
            sleep();
            Log.d("[sleep]", "Doarme");
            sendMessage1();
            Log.d("[sendMessage1]", "DIF");
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.actionTypes[0]);
        String text = String.valueOf(suma);
        intent.putExtra("messageSuma", text);
        // NU uita
        context.sendBroadcast(intent);
    }
    private void sendMessage1() {
        Intent intent = new Intent();
        intent.setAction(Constants.actionTypes[1]);
        String text1 = String.valueOf(dif);
        intent.putExtra("messageDif", text1);
        // NU uita
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            // la fiecare 5 sec se trm cate un msj
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stopThread() {
        isRunning = false;
    }

}
