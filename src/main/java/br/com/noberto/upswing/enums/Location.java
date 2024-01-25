package br.com.noberto.upswing.enums;

public enum Location {
    NEIGHBORHOOD("Bairro"),
    CITY("Cidade"),
    STATE("Estado");

    private final String location;

    Location(String location){
        this.location = location;
    }

    public static String fromString(String data){
        for (Location locations : Location.values()){
            if (locations.location.equalsIgnoreCase(data)){
                return data;
            }
        }
        throw new IllegalArgumentException("Local: " + data + " não encontrado!");
    }
}
