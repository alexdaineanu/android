package daie1895.ubb.ro.foodmanager.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import daie1895.ubb.ro.foodmanager.DAO.RecipeDao;
import daie1895.ubb.ro.foodmanager.Domain.Recipe;

/**
 * Created by adaineanu on 11/28/2017.
 */

@Database(entities = {Recipe.class}, version = 2)
public abstract class RecipeDatabase extends RoomDatabase{
    private static RecipeDatabase INSTANCE;
    public abstract RecipeDao recipeDao();

}
