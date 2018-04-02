package practicaltest01.eim.systems.cs.pub.ro.practicaltest01var02.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import practicaltest01.eim.systems.cs.pub.ro.practicaltest01var02.R;

public class PracticalTest01Var02SecondaryActivity extends AppCompatActivity {
    Button correctButt;
    Button incorrectButt;
    EditText resultEdit;

    ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == correctButt.getId()) {
                setResult(RESULT_OK, null);
            }
            if (view.getId() == incorrectButt.getId()) {
                setResult(RESULT_CANCELED, null);
            }
            // pt a te intoarce la activitatea principala
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_secondary);

        resultEdit = (EditText)  findViewById(R.id.result_edit);
        // Trebuie sa obtii intentia!
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("thirdEdit")) {
           String text = intent.getStringExtra("thirdEdit");
           resultEdit.setText(text);
        }

        correctButt = (Button) findViewById(R.id.correct_butt);
        correctButt.setOnClickListener(buttonClickListener);

        incorrectButt = (Button) findViewById(R.id.incorrect_butt);
        incorrectButt.setOnClickListener(buttonClickListener);


    }
}
