package view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tefteri.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import model.Category;
import model.Item;
import repository.AppDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton addItem;

    ImageButton waterImageButton,
                coffeeImageButton,
                fizzyImageButton,
                alcoholImageButton,
                foodImageButton,
                iceCreamImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItem = findViewById(R.id.addFloatingActionButton);

        waterImageButton = findViewById(R.id.waterImageButton);
        coffeeImageButton = findViewById(R.id.coffeeImageButton);
        fizzyImageButton = findViewById(R.id.fizzyImageButton);
        alcoholImageButton = findViewById(R.id.alcoholImageButton);
        foodImageButton = findViewById(R.id.foodImageButton);
        iceCreamImageButton = findViewById(R.id.iceCreamImageButton);

        waterImageButton.setOnClickListener(this);
        coffeeImageButton.setOnClickListener(this);
        fizzyImageButton.setOnClickListener(this);
        alcoholImageButton.setOnClickListener(this);
        foodImageButton.setOnClickListener(this);
        iceCreamImageButton.setOnClickListener(this);

        addItem.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,AddItemActivity.class)));
    }

    @Override
    public void onClick(View view) {
        Category choice;
        switch (view.getId()) {
            case R.id.waterImageButton:
                choice = Category.WATER;
                break;
            case R.id.coffeeImageButton:
                choice = Category.COFFEE;
                break;
            case R.id.fizzyImageButton:
                choice = Category.FIZZY;
                break;
            case R.id.alcoholImageButton:
                choice = Category.ALCOHOL;
                break;
            case R.id.foodImageButton:
                choice = Category.FOOD;
                break;
            default:
                choice = Category.ICE_CREAM;
        }
        Intent intent = new Intent(MainActivity.this,ItemsListActivity.class);
        intent.putExtra("choice",Category.getValue(choice));
        startActivity(intent);
    }
}