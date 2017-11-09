package daie1895.ubb.ro.foodmanager;

/**
 * Created by adaineanu on 11/6/2017.
 */

public class Recipe {
    private String name;
    private String recipe;

    public Recipe(String name, String recipe) {
        this.name = name;
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", recipe='" + recipe + '\'' +
                '}';
    }
}
