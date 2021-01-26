
import java.util.ArrayList;
import java.util.Scanner;

class Espiral{
    public String nome;
    public int qntd;
    public float preco;

    public Espiral(String nome, int qntd, float preco) {
        this.nome = nome;
        this.qntd = qntd;
        this.preco = preco;
    }
    
    public String toString(){
        return "Nome:"+nome+", Quantidade:"+qntd+", Preço:"+preco;
    }
}

class Machine{
    private ArrayList<Espiral> espirais = new ArrayList();
    private float saldoCliente;
    private float lucro;
    private int maxProdutos;
    
    public boolean inserirDinheiro(int valor){
        this.saldoCliente += valor;
        return true;
    }
    
    public float pedirTroco(){
        this.saldoCliente = 0;
        return 0;
    }
    
    public boolean comprar(int indice){
        if(indice < 0 || indice > espirais.size()){
            System.out.println("Indice invalido");
            return false;
        }
        if(espirais.get(indice) == null){
            System.out.println("Não há nada na posição");
            return false;
        }
        if(espirais.get(indice).qntd == 0){
            System.out.println("Acabou os produtos");
            return false;
        }
        if(saldoCliente > espirais.get(indice).preco){
            saldoCliente -= espirais.get(indice).preco;
            if(espirais.get(indice).qntd > 1){
                espirais.get(indice).qntd -= 1;
            }else{
                limparEspiral(indice);
            }
        }
        return true;
    }
    
    public boolean alterarEspiral(int indice, String nome, int qtd, float preco){
        espirais.set(indice, new Espiral(nome, qtd, preco));
        return true;
    }
    
    public void limparEspiral(int indice){
        espirais.set(indice, new Espiral(null, 0, 0));
    }

    public float getSaldoCliente() {
        return saldoCliente;
    }
    
    public Machine(){
        
    }
    
    public String toString(){
        return "Espiral:"+espirais;
    }
    
    public static void main(String[] args) {
        Machine machine = new Machine();
        ArrayList<Espiral> espirais = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            espirais.add(new Espiral("", 0, 0));
        }
        espirais.set(0, new Espiral("Nescau", 5, (float) 5.50));
        espirais.set(1, new Espiral("Cheetos", 3, (float) 7.50));
        espirais.set(2, new Espiral("Doritos", 7, (float) 9.90));
        espirais.set(3, new Espiral("Tampico", 2, (float) 2.10));
        espirais.set(4, new Espiral("Kinder", 9, (float) 10.50));
        
        while(true){
            Scanner tcl = new Scanner(System.in);
            String comando = tcl.nextLine();
            int valor = tcl.nextInt();
            if(comando.equals("inserirDinheiro")){
                machine.inserirDinheiro(valor);
            }else if(comando.equals("comprar")){
                machine.comprar(valor);
            }else if(comando.equals("troco")){
                machine.pedirTroco();
            }
        }
    }
}
