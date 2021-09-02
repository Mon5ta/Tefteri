package view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tefteri.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Item;
import repository.AppDatabase;

public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.ViewHolder> {

    private final List<Item> items = new ArrayList<>();
    private final Context context;

    public ItemsListAdapter(Context context,Category category) {
        this.context = context;
        items.addAll(AppDatabase.getInstance(context).itemDao().getCategorizedItems(category));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsListAdapter.ViewHolder holder, int position) {
        holder.titleTextView.setText(items.get(position).title);
        holder.amountEditText.setText(String.valueOf(items.get(position).amount));

        holder.itemView.setOnLongClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle("Πρόκειται να διαγραφεί το " + items.get(position).title)
                    .setMessage("Θέλεις να συνεχίσεις;")
                    .setPositiveButton("Ναι", null)
                    .setNegativeButton("Όχι", null)
                    .show();
            Button positive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            positive.setOnClickListener(v1 -> {
                AppDatabase.getInstance(context).itemDao().deleteItem(items.get(position));
                items.remove(position);
                notifyDataSetChanged();
                dialog.dismiss();
            });
            return false;
        });

        holder.continueButton.setOnClickListener(v -> {
            String amount = holder.amountEditText.getText().toString().trim();
            if (!amount.isEmpty()) {
                items.get(position).amount = Integer.parseInt(amount);
                AppDatabase.getInstance(context).itemDao().updateItem(items.get(position));
                Toast.makeText(context, "ΕΠΙΤΥΧΙΑ ΕΝΗΜΕΡΩΣΗΣ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "ΔΩΣΕ ΤΗΝ ΠΟΣΟΤΗΤΑ ΤΟΥ ΠΡΟΙΟΝΤΟΣ!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextInputEditText amountEditText;
        private final MaterialButton continueButton;

        public ViewHolder(View view) {
            super(view);
            titleTextView = view.findViewById(R.id.itemTitleTextView);
            amountEditText = view.findViewById(R.id.amountEditText);
            continueButton = view.findViewById(R.id.buttonContinue);
        }
    }
}
