package daie1895.ubb.ro.foodmanager.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import daie1895.ubb.ro.foodmanager.Domain.Recipe;

/**
 * Created by adaineanu on 11/28/2017.
 */

@Dao
public interface RecipeDao {
    @Query("SELECT * FROM recipe")
    List<Recipe> getAll();

    @Insert
    void insertAll(Recipe...recipes);

    @Update
    void updateRecipes(Recipe...recipes);

    @Delete
    void delete(Recipe recipe);
}
