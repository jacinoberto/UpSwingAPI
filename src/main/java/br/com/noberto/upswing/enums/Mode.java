package br.com.noberto.upswing.enums;

public enum Mode {
    VIRTUAL("EAD"),
    REMOTE("Remoto"),
    HYBRID("Híbrido"),
    IN_PERSON("Presencial");

    private final String learningMode;

    Mode(String learningMode){
        this.learningMode = learningMode;
    }

    public static String fromString(Mode data){
        return data.learningMode;
    }

    public static Mode fromLearningMode(String data){
        for (Mode modes : Mode.values()){
            if (modes.learningMode.equalsIgnoreCase(data)){
                return modes;
            }
        }
        throw new IllegalArgumentException("Modalidade: " + data + " não encontrada!");
    }
}
