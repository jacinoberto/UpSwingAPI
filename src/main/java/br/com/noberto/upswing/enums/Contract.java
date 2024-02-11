package br.com.noberto.upswing.enums;

public enum Contract {
    INTERNSHIP("Estágio"),
    FIXED("Efetivo"),
    BOTH("Ambos");

    private final String contract;

    Contract(String contract){
        this.contract = contract;
    }

    public static String fromString(Contract data){
        return data.contract;
    }

    public static Contract fromContract(String data){
        for (Contract contracts : Contract.values()){
            if (contracts.contract.equalsIgnoreCase(data)){
                return contracts;
            }
        }
        throw new IllegalArgumentException("Contrato: " + data + " não encontrado!");
    }
}
