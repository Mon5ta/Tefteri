package model;

public enum Category {
    WATER,
    COFFEE,
    FIZZY,
    ALCOHOL,
    FOOD,
    ICE_CREAM;

    public static String getValue(Category category){
        switch (category) {
            case WATER:
                return "ΝΕΡΟ";
            case COFFEE:
                return "ΚΑΦΕΣ/ΡΟΦΗΜΑ";
            case FIZZY:
                return "ΑΝΑΨΥΚΤΙΚΟ/ΧΥΜΟΣ";
            case ALCOHOL:
                return "ΑΛΚΟΛ/ΠΟΤΟ";
            case FOOD:
                return "ΣΝΑΚ/ΦΑΓΗΤΟ";
            default:
                return "ΠΑΓΩΤΟ";
        }
    }

    public static Category getCategoryFromString(String categoryValue){
        switch (categoryValue) {
            case "ΝΕΡΟ":
                return WATER;
            case "ΚΑΦΕΣ/ΡΟΦΗΜΑ":
                return COFFEE;
            case "ΑΝΑΨΥΚΤΙΚΟ/ΧΥΜΟΣ":
                return FIZZY;
            case "ΑΛΚΟΛ/ΠΟΤΟ":
                return ALCOHOL;
            case "ΣΝΑΚ/ΦΑΓΗΤΟ":
                return FOOD;
            default:
                return ICE_CREAM;
        }
    }
}
