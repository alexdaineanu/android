package daie1895.ubb.ro.foodmanager.Domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by adaineanu on 11/6/2017.
 */

@Entity
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "recipe")
    private String recipe;

    @ColumnInfo(name = "photo")
    private String photo;

    public Recipe(String name, String recipe, String photo) {
        this.name = name;
        this.recipe = recipe;
        this.photo = photo;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", recipe='" + recipe + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
