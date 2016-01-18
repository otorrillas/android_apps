package easycooking.otorrillas.easycooking;

/**
 * Created by otorr on 08/01/2016.
 */
public class RecipeIngredient {
    public Integer quantity;
    public String name;
    public String unit;
    public String[] alternatives;

    public RecipeIngredient(Integer quantity, String name, String unit, String[] alternatives) {
        this.quantity = quantity;
        this.name = name;
        this.unit = unit;
        this.alternatives = alternatives;
    }

    public void edit(Integer quantity, String name, String unit, String[] alternatives) {
        this.quantity = quantity;
        this.name = name;
        this.unit = unit;
        this.alternatives = alternatives;
    }
}
