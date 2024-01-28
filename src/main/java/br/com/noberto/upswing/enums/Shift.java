package br.com.noberto.upswing.enums;

public enum Shift {
    MORNING("Matutino"),
    AFTERNOON("Vespertino"),
    EVENING("Noturno");

    private final String shift;

    Shift(String shift){
        this.shift = shift;
    }

    public static String fromString(Shift data){
        return data.shift;
    }

    public static Shift fromShift(String data){
        for (Shift shifts : Shift.values()){
            if (shifts.shift.equalsIgnoreCase(data)){
                return shifts;
            }
        }
        throw new IllegalArgumentException("Turno: " + data + " não encontrado!");
    }
}
