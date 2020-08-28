package quiztastic.core;

/**
 * How do we store categories?
 */
public class Category {
    private final String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
