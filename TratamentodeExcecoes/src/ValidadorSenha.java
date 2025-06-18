public class ValidadorSenha {
    public void validar(String senha) throws SenhaException{
        if(senha.length() < 8 ||
        !senha.matches(".*[A-Z].*") ||
        !senha.matches(".*[a-z].*") ||
        !senha.matches(".*\\d.*") ||
        !senha.matches(".*[!@#$%^&*()\\-+=].*")){
            throw new SenhaException("A senha inserida precisa conter 8 caracteres, uma letra maiúscula, minúscula, número e um caractere especial. (ex: !@#$)");
        }
    }
}
