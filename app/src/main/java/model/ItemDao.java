package model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM items WHERE category = :category ORDER BY title")
    List<Item> getCategorizedItems(Category category);

    @Query("SELECT * FROM items WHERE title = :title")
    Item getItemByTitle(String title);

    @Insert
    void insertNewItem(Item item);

    @Update
    void updateItem(Item item);

    @Delete
    void deleteItem(Item item);
}
