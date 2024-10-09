package com.example.kopapirollo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Integer emberscore = 0;
    private ImageView emberpick;
    private Integer emberValInt = 0;
    private Integer gepValInt = 0;
    private ImageView geppick;
    private Integer gepscore = 0;
    private Button kogomb;
    private Button papirgomb;
    private Button ollogomb;
    Random r = new Random();
    private TextView eredmeny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        kogomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emberpick.setImageResource(R.drawable.rock);
                emberValInt = 0;
                chooseone();
            }

        });
        papirgomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emberpick.setImageResource(R.drawable.paper);
                emberValInt = 1;
                chooseone();
            }
        });
        ollogomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emberpick.setImageResource(R.drawable.scissors);
                emberValInt = 2;
                chooseone();
            }
        });


    }
    public void eredmenykozles(){
        String s = String.format("Eredmény: Ember %s, Compjuter %s", emberscore, gepscore);
        eredmeny.setText(s);
        if (gepscore >= 3 || emberscore >= 3){
            jatekvege();
        }
    }

    public void chooseone(){
        int[] lehetosegek = new int[]{R.drawable.rock, R.drawable.paper, R.drawable.scissors};
        gepValInt = r.nextInt(3);
        geppick.setImageResource(lehetosegek[gepValInt]);
        if (emberValInt.equals(gepValInt)){
            Toast.makeText(MainActivity.this, "dontetlen", Toast.LENGTH_SHORT).show();

        }
        else if ( emberValInt == 0 && gepValInt == 1){
            gepscore ++;
            Toast.makeText(MainActivity.this, "gep nyert", Toast.LENGTH_SHORT).show();
            eredmenykozles();
        }
        else if ( emberValInt == 1 && gepValInt == 2){
            gepscore ++;
            Toast.makeText(MainActivity.this, "gep nyert", Toast.LENGTH_SHORT).show();
            eredmenykozles();
        }
        else if ( emberValInt == 2 && gepValInt == 0){
            gepscore ++;
            Toast.makeText(MainActivity.this, "gep nyert", Toast.LENGTH_SHORT).show();
            eredmenykozles();
        }
        else if ( emberValInt == 1 && gepValInt == 0){
            emberscore ++;
            Toast.makeText(MainActivity.this, "ember nyert", Toast.LENGTH_SHORT).show();
            eredmenykozles();
        }
        else if ( emberValInt == 2 && gepValInt == 1){
            emberscore ++;
            Toast.makeText(MainActivity.this, "ember nyert", Toast.LENGTH_SHORT).show();
            eredmenykozles();
        }
        else if ( emberValInt == 0 && gepValInt == 2){
            emberscore ++;
            Toast.makeText(MainActivity.this, "ember nyert", Toast.LENGTH_SHORT).show();
            eredmenykozles();
        }

    }

    public void ujJatek(){
        gepscore = 0;
        emberscore = 0;
        eredmenykozles();
    }

    public void jatekvege(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        if (emberscore > gepscore){
            builder.setTitle("Győzelem");
        }
        else{
            builder.setTitle("Vereség");
        }
        builder.setMessage("Szeretnél új játékot játszani?");
        builder.setPositiveButton("Igen", (DialogInterface.OnClickListener) (dialog, which) ->{
            ujJatek();
        } );
        builder.setNegativeButton("Nem", (DialogInterface.OnClickListener) (dialog, which) -> {
            finish();
        });
        builder.show();

    }

    void init() {
        emberpick = findViewById(R.id.jatekospick);
        geppick = findViewById(R.id.geppick);
        kogomb = findViewById(R.id.kogomb);
        papirgomb = findViewById(R.id.papirgomb);
        ollogomb = findViewById(R.id.ollogomb);
        eredmeny = findViewById(R.id.eredmeny);


    }


}