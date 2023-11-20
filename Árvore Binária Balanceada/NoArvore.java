public class NoArvore {
  int valor;
  NoArvore esquerda, direita;

  public NoArvore(int valor) {
    this.valor = valor;
    this.esquerda = this.direita = null;
  }
}