package daie1895.ubb.ro.foodmanager.Domain;

/**
 * Created by adaineanu on 11/6/2017.
 */

public class Recipe {
    private String id;
    private String name;
    private String recipe;
    private String email;
    private Boolean approved;

    public Recipe(String id, String name, String recipe, String email, Boolean approved) {
        this.id = id;
        this.name = name;
        this.recipe = recipe;
        this.email = email;
        this.approved = approved;
    }

    public Recipe() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", recipe='" + recipe + '\'' +
                ", email='" + email + '\'' +
                ", approved=" + approved +
                '}';
    }

    public String getId() {
        return id;
    }
}
