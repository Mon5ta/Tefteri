package repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import model.Item;
import model.ItemDao;

@Database(entities = {Item.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ItemDao itemDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "puebloDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
