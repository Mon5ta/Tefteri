package model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class Item {

    @NonNull
    @PrimaryKey
    public String title;

    @ColumnInfo(name = "category")
    public Category category;

    @ColumnInfo(name = "amount")
    public int amount;

    public Item() {
        title = "test";
    }

    public Item(String title, Category category, int amount) {
        this.title = title;
        this.category = category;
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
