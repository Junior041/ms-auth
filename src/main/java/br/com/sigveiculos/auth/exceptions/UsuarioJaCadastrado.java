package br.com.sigveiculos.auth.exceptions;

public class UsuarioJaCadastrado extends RuntimeException{
    public UsuarioJaCadastrado() {
        super("Usuário já cadastrado.");
    }
}
