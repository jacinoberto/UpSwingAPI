package br.com.noberto.upswing.enums;

public enum LearningMode {
    REMOTE("EAD"),
    HYBRID("Híbrido"),
    IN_PERSON("Presencial");

    private final String learningMode;

    LearningMode(String learningMode){
        this.learningMode = learningMode;
    }

    public static String fromString(LearningMode data){
        return data.learningMode;
    }

    public static LearningMode fromLearningMode(String data){
        for (LearningMode learningModes : LearningMode.values()){
            if (learningModes.learningMode.equalsIgnoreCase(data)){
                return learningModes;
            }
        }
        throw new IllegalArgumentException("Modalidade: " + data + " não encontrada!");
    }
}
