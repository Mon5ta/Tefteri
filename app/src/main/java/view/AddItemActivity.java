package view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.example.tefteri.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import model.Category;
import model.Item;
import repository.AppDatabase;

public class AddItemActivity extends AppCompatActivity{

    TextInputEditText itemTitle;
    AppCompatSpinner itemCategory;
    MaterialButton addItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        itemTitle = findViewById(R.id.itemTitleEditText);
        itemCategory = findViewById(R.id.categorySpinner);
        addItemButton = findViewById(R.id.addItemButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddItemActivity.this,R.array.categorySpinner, R.layout.custom_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemCategory.setAdapter(adapter);

        addItemButton.setOnClickListener(v -> {
            String title = itemTitle.getText().toString().trim();
            Category category = Category.getCategoryFromString(itemCategory.getSelectedItem().toString().trim());
            if (!title.isEmpty()){
                AppDatabase db = AppDatabase.getInstance(AddItemActivity.this.getApplicationContext());
                Item alreadyExistingItem = db.itemDao().getItemByTitle(title);
                if (alreadyExistingItem==null){
                    Item item = new Item();
                    item.title = title;
                    item.category = category;
                    db.itemDao().insertNewItem(item);
                    Toast.makeText(this, "Η ΕΙΣΑΓΩΓΗ ΟΛΟΚΛΗΡΩΘΗΚΕ!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                    Toast.makeText(this, "ΥΠΑΡΧΕΙ ΗΔΗ ΠΡΟΙΟΝ ΜΕ ΤΕΤΟΙΟ ΤΙΤΛΟ!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}