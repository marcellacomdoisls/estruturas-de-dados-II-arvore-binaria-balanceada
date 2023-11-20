import java.util.Random;

public class ArvoreBinaria {
  private NoArvore raiz;

  public ArvoreBinaria() {
    this.raiz = null;
  }

  private NoArvore inserir(NoArvore no, int valor) {
    if (no == null) {
      return new NoArvore(valor);
    }

    if (valor < no.valor) {
      no.esquerda = inserir(no.esquerda, valor);
    } else if (valor > no.valor) {
      no.direita = inserir(no.direita, valor);
    }

    return no;
  }

  private NoArvore balancearArvore(NoArvore no) {
    no = listaEncadeada(no);
    no = balancearLista(no);
    return no;
  }

  private NoArvore listaEncadeada(NoArvore no) {
    NoArvore novaRaiz = new NoArvore(0);
    novaRaiz.direita = no;

    int n = contarNos(no);
    for (int i = 0; i < n; i++) {
      novaRaiz = rotacaoDireita(novaRaiz);
    }

    return novaRaiz.direita;
  }

  private NoArvore rotacaoDireita(NoArvore raiz) {
    if (raiz == null || raiz.esquerda == null) {
      return raiz;
    }

    NoArvore novaRaiz = raiz.esquerda;
    raiz.esquerda = novaRaiz.direita;
    novaRaiz.direita = raiz;
    return novaRaiz;
  }

  private int contarNos(NoArvore no) {
    if (no == null) {
      return 0;
    }
    return 1 + contarNos(no.esquerda) + contarNos(no.direita);
  }

  private NoArvore balancearLista(NoArvore no) {
    int n = contarNos(no);
    int m = (int) Math.pow(2, (int) (Math.log(n + 1) / Math.log(2))) - 1;

    NoArvore raiz = no;
    for (int i = 0; i < n - m; i++) {
      raiz = rotacaoEsquerda(raiz);
    }

    while (m > 1) {
      m /= 2;
      raiz = rotacaoEsquerda(raiz);
    }

    return raiz;
  }

  private NoArvore rotacaoEsquerda(NoArvore raiz) {
    if (raiz == null || raiz.direita == null) {
      return raiz;
    }

    NoArvore novaRaiz = raiz.direita;
    raiz.direita = novaRaiz.esquerda;
    novaRaiz.esquerda = raiz;
    return novaRaiz;
  }

  public void inserir(int valor) {
    raiz = inserir(raiz, valor);
  }

  public void balancearArvore() {
    raiz = balancearArvore(raiz);
  }

  private void imprimirOrdem(NoArvore no) {
    if (no != null) {
      imprimirOrdem(no.esquerda);
      System.out.print(no.valor + " ");
      imprimirOrdem(no.direita);
    }
  }

  public void imprimirArvore() {
    imprimirOrdem(raiz);
    System.out.println();
  }

  public static void main(String[] args) {
    ArvoreBinaria arvore = new ArvoreBinaria();
    Random random = new Random();
    for (int i = 0; i < 100; i++) {
      arvore.inserir(random.nextInt(101));
    }

    System.out.println("Árvore antes de adicionar 20 números:");
    arvore.imprimirArvore();

    for (int i = 0; i < 20; i++) {
      arvore.inserir(random.nextInt(101));
    }

    System.out.println("Árvore após adicionar 20 números:");
    arvore.imprimirArvore();

    arvore.balancearArvore();

    System.out.println("Árvore após balanceamento:");
    arvore.imprimirArvore();
  }
}
