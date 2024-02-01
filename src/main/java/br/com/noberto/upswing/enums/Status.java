package br.com.noberto.upswing.enums;

public enum Status {
    APPROVED("Aprovado"),
    NOT_APPROVED("Reprovado"),
    PENDING("Pendente");

    private String status;

    Status(String status){
        this.status = status;
    }

    public static Status fromStatus(String data){
        for (Status requests : Status.values()){
            if (requests.status.equalsIgnoreCase(data)){
                return requests;
            }
        }
        throw new IllegalArgumentException("Status: " + data + " não encontrado!");
    }

    public static String fromString(Status data){
        return data.status;
    }
}
