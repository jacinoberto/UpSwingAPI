package br.com.noberto.upswing.enums;

public enum EducationDegree {
    HSDIPLOMA("Matutino"),
    PROFESSIONAL_TRAINING("Livre"),
    CERTIFICATE("Técnico"),
    BACHELOR("Superior");

    private final String educationDegree;

    EducationDegree(String educationDegree){
        this.educationDegree = educationDegree;
    }

public static String fromString(String data){
    for (EducationDegree degrees : EducationDegree.values()){
        if (degrees.educationDegree.equalsIgnoreCase(data)){
            return data;
        }
    }
        throw new IllegalArgumentException("Grau Educacional: " + data + " não encontrado!");
    }
}
