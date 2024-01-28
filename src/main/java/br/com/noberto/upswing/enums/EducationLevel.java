package br.com.noberto.upswing.enums;

public enum EducationLevel {
    HIGH_SCHOOL("Ensino Médio"),
    PROFESSIONAL_TRAINING("Livre"),
    CERTIFICATE("Técnico"),
    GRADUATION("Superior");

    private final String educationLevel;

    EducationLevel(String educationLevel){
        this.educationLevel = educationLevel;
    }

public static String fromString(EducationLevel data){
    return data.educationLevel;
    }

    public static EducationLevel fromEducationLevel(String data){
        for (EducationLevel levels : EducationLevel.values()){
            if (levels.educationLevel.equalsIgnoreCase(data)){
                return levels;
            }
        }
        throw new IllegalArgumentException("Nível Educacional: " + data + " não encontrado!");
    }
}
