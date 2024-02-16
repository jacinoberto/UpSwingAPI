package br.com.noberto.upswing.enums;

public enum UserRole {
    ADMIN("Admin"),
    STUDENT("Aluno"),
    COMPANY("Empreesa");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
