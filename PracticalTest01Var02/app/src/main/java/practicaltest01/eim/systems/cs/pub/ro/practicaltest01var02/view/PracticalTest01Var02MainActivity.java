package practicaltest01.eim.systems.cs.pub.ro.practicaltest01var02.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;

import practicaltest01.eim.systems.cs.pub.ro.practicaltest01var02.R;
import practicaltest01.eim.systems.cs.pub.ro.practicaltest01var02.general.Constants;
import practicaltest01.eim.systems.cs.pub.ro.practicaltest01var02.service.PracticalTest01Var02Service;

public class PracticalTest01Var02MainActivity extends AppCompatActivity {
    EditText firstEdit;
    EditText secondEdit;
    EditText thirdEdit;
    Button plusButton;
    Button minusButton;
    Button navigateButton;
    private  final int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
    // serviciu este oprit initial
    int serviceStatus = Constants.SERVICE_STOPPED;

    //ascultator
    private IntentFilter intentFilter = new IntentFilter();

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Ascultator]", intent.getStringExtra("messageSuma"));
        }
    }

    ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String  text = " ";
            boolean digitsOnly;
            boolean digitsOnlyTwo;

            if (view.getId() == plusButton.getId()) {
                digitsOnly = TextUtils.isDigitsOnly(firstEdit.getText());
                digitsOnlyTwo = TextUtils.isDigitsOnly(secondEdit.getText());
                thirdEdit.setText(text);
                if (!digitsOnly || !digitsOnlyTwo){
                    Toast.makeText(getApplicationContext(), "You didn't write a Integer number" , Toast.LENGTH_LONG).show();
                } else {
                    int sum = Integer.parseInt(firstEdit.getText().toString()) + Integer.parseInt(secondEdit.getText().toString());
                    text = "+ , " + String.valueOf(sum);
                    thirdEdit.setText(text);

                    //serv este pornit
                    if (serviceStatus == Constants.SERVICE_STOPPED) {
                        serviceStatus = Constants.SERVICE_STARTED;

                        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02Service.class);
                        intent.putExtra("firstNumber", Integer.parseInt(firstEdit.getText().toString()));
                        intent.putExtra("secondNumber", Integer.parseInt(secondEdit.getText().toString()));
                        // pornesti serviciul
                        getApplicationContext().startService(intent);

                    }
                }
            }
            if (view.getId() == minusButton.getId()) {
                digitsOnly = TextUtils.isDigitsOnly(firstEdit.getText());
                digitsOnlyTwo = TextUtils.isDigitsOnly(secondEdit.getText());
                if (!digitsOnly || !digitsOnlyTwo) {
                    Toast.makeText(getApplicationContext(), "You didn't write a Integer number" , Toast.LENGTH_LONG).show();
                    thirdEdit.setText(text);
                } else {
                    int minus = Integer.parseInt(firstEdit.getText().toString()) - Integer.parseInt(secondEdit.getText().toString());
                    text = "- ," + String.valueOf(minus);
                    thirdEdit.setText(text);
                    //serv este pornit
                    if (serviceStatus == Constants.SERVICE_STOPPED) {
                        serviceStatus = Constants.SERVICE_STARTED;

                        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02Service.class);
                        intent.putExtra("firstNumber", Integer.parseInt(firstEdit.getText().toString()));
                        intent.putExtra("secondNumber", Integer.parseInt(secondEdit.getText().toString()));
                        // pornesti serviciul
                        getApplicationContext().startService(intent);

                    }
                }
            }
            //Secondary activity
            if (view.getId() == navigateButton.getId()) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02SecondaryActivity.class);
                intent.putExtra("thirdEdit", thirdEdit.getText().toString());
                startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
            }

        }
    }

    //Secondary activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE){
          Toast.makeText(this,"the Main activity returned with" + resultCode, Toast.LENGTH_LONG).show();
      }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_main);

        plusButton = (Button) findViewById(R.id.plus_button);
        plusButton.setOnClickListener(buttonClickListener);

        minusButton = (Button) findViewById(R.id.minus_button);
        minusButton.setOnClickListener(buttonClickListener);

        firstEdit = (EditText) findViewById(R.id.first_edit);
        secondEdit = (EditText) findViewById(R.id.second_edit);
        thirdEdit = (EditText) findViewById(R.id.third_edit);

        //Restaurarea starii FIRST
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("firstEdit")) {
                firstEdit.setText(savedInstanceState.getString("firstEdit"));
            } else {
                firstEdit.setText("E");
            }

            if (savedInstanceState.containsKey("secondEdit")) {
                secondEdit.setText(savedInstanceState.getString("secondEdit"));
            } else {
                secondEdit.setText("E");
            }

            if (savedInstanceState.containsKey("thirdEdit")) {
                thirdEdit.setText(savedInstanceState.getString("thirdEdit"));
            } else {
                thirdEdit.setText("E");
            }

        }

        //Secondary activity
        navigateButton = (Button) findViewById(R.id.navigate_button);
        navigateButton.setOnClickListener(buttonClickListener);

        //ascultator adaugi actiuni
//        for (int index = 0; index < Constants.actionTypes.length; index++) {
//            intentFilter.addAction(Constants.actionTypes[index]);
//        }
        intentFilter.addAction(Constants.actionTypes[0]);

    }

    //Salvarea elem EditText
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("firstEdit", firstEdit.getText().toString());
        outState.putString("secondEdit", secondEdit.getText().toString());
        outState.putString("thirdEdit", thirdEdit.getText().toString());
    }

    //Restaurarea starii TWO
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("firstEdit")) {
                firstEdit.setText(savedInstanceState.getString("firstEdit"));
            } else {
                firstEdit.setText("E");
            }

            if (savedInstanceState.containsKey("secondEdit")) {
                secondEdit.setText(savedInstanceState.getString("secondEdit"));
            } else {
                secondEdit.setText("E");
            }

            if (savedInstanceState.containsKey("thirdEdit")) {
                thirdEdit.setText(savedInstanceState.getString("thirdEdit"));
            } else {
                thirdEdit.setText("E");
            }

        }
    }

    // obligatorii
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onStop() {
        Intent intent = new Intent(this, PracticalTest01Var02Service.class);
        stopService(intent);
        super.onStop();
    }
}
