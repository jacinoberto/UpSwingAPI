package br.com.noberto.upswing.enums;

public enum Category {
    REMOTE("EAD"),
    HYBRID("Híbrido"),
    IN_PERSON("Pessoalmente");

    private final String category;

    Category(String category){
        this.category = category;
    }

    public static String fromString(String data){
        for (Category categories : Category.values()){
            if (categories.category.equalsIgnoreCase(data)){
                return data;
            }
        }
        throw new IllegalArgumentException("Categoria: " + data + " não encontrada!");
    }
}
