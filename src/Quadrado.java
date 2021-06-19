public class Quadrado {
    public Quadrado() {
        this.validado = false;
    }

    private String valor;
    private boolean validado;

    public String getValor() {
        return valor;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }
}
