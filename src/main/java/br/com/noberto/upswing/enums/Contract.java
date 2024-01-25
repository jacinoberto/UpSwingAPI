package br.com.noberto.upswing.enums;

public enum Contract {
    INTERNSHIP("Estágio"),
    FIXED("Efetivo");

    private final String contract;

    Contract(String contract){
        this.contract = contract;
    }

    public static String fromString(String data){
        for (Contract contracts : Contract.values()){
            if (contracts.contract.equalsIgnoreCase(data)){
                return data;
            }
        }
        throw new IllegalArgumentException("Contrato: " + data + " não encontrado!");
    }
}
