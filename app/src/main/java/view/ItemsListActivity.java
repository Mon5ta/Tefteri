package view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tefteri.R;

import model.Category;

public class ItemsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        Intent intent = getIntent();
        Category choice = Category.getCategoryFromString(intent.getStringExtra("choice"));

        if (!choice.equals("error")){
            RecyclerView recyclerView = findViewById(R.id.itemsRecyclerView);
            ItemsListAdapter adapter = new ItemsListAdapter(ItemsListActivity.this,choice);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            Toast.makeText(this, "ΚΑΤΙ ΠΗΓΕ ΣΤΡΑΒΑ ΦΩΝΑΞΕ ΤΟ ΛΑΚΗ ΝΑ ΣΤΟ ΛΥΣΕΙ!", Toast.LENGTH_LONG).show();
        }

    }
}