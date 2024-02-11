package br.com.noberto.upswing.enums;

public enum Location {
    AREA("Bairro"),
    CITY("Cidade"),
    STATE("Estado");

    private final String location;

    Location(String location){
        this.location = location;
    }

    public static String fromString(Location data){
        return data.location;
    }
    public static Location fromLocation(String data){
        for (Location locations : Location.values()){
            if (locations.location.equalsIgnoreCase(data)){
                return locations;
            }
        }
        throw new IllegalArgumentException("Local: " + data + " não encontrado!");
    }

}
