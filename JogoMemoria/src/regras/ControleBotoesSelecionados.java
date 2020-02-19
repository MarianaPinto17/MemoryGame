package regras;

import telas.TelaPrincipal;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ControleBotoesSelecionados {
    //numero de pares encontrados
    private String nmBotao;
    private Map<JButton, EstadosBotoes> referenciaBotoes;
    
    public ControleBotoesSelecionados() {
        this.referenciaBotoes = new HashMap<>();
    }
    
    public void executarAcaoBotoes(JButton botao, EstadosBotoes estado) {
        alterarSelecao(botao, estado);
        if (this.isTodasSelecionadas()) {
            // quando se encontra o par
            alterarEstadoTodosBotoes(EstadosBotoes.PARES_ENCONTRADOS);
            JOptionPane.showMessageDialog(null, "Boa! Acertaste!"); 
        }
        else {
          //quando não se encontra o par
          alterarVisualizacaoBotao(botao);
        }
    }
    
    private void alterarEstadoTodosBotoes(EstadosBotoes estado) {
        for (JButton botao : this.referenciaBotoes.keySet()) {
            alterarSelecao(botao, estado);
            alterarVisualizacaoBotao(botao);
        }
    }
    
    public String getNmBotao() {
        return nmBotao;
    }
    public void setNmBotao(String nmBotao) {
        this.nmBotao = nmBotao;
    }
    public Map<JButton, EstadosBotoes> getReferenciaBotoes() {
        return referenciaBotoes;
    }
    public void setReferenciaBotoes(Map<JButton, EstadosBotoes> referenciaBotoes) {
        this.referenciaBotoes = referenciaBotoes;
    }
    public void adicionarBotao(JButton botao) {
        this.referenciaBotoes.put(botao, EstadosBotoes.NORMAL);
    }  
    public void alterarSelecao(JButton botao, EstadosBotoes selecionado) {
        this.referenciaBotoes.put(botao, selecionado);
    }
    
    // botões dentro do jogo para escolher B1 e B2
    private void alterarVisualizacaoBotao(JButton botao) {
        EstadosBotoes selecionado = this.referenciaBotoes.get(botao);
        switch (selecionado) {
            case NORMAL: // Cinza, não exibe texto
                botao.setBackground(null);
                botao.setText("Jogo");
                break;
            case SELECIONADO: // Exibir texto, mudar a cor
                botao.setBackground(Color.green);
                botao.setText(this.nmBotao);
                break;
            case PARES_ENCONTRADOS: // Mudar a cor, exibir o texto
                botao.setBackground(Color.blue);
                botao.setText(this.nmBotao);
                botao.setEnabled(false);
                break;
            case TODOS_PARES_ENCONTRADOS:
                botao.setBackground(Color.blue);
                botao.setText(this.nmBotao);
                botao.setEnabled(false);
                break;
        }
    }
    
    public void zerarSelecoes() {
       alterarEstadoTodosBotoes(EstadosBotoes.NORMAL);
    }
    public boolean isTodasSelecionadas() {
        for (EstadosBotoes b : this.referenciaBotoes.values()) {
            if (b != EstadosBotoes.SELECIONADO) {
                // Não foram todos selecionados
               return false;
            }   
        }
        //JOptionPane.showMessageDialog(null, "Jogo terminado!");
        return true;
    }
    
    public void ParesTodosEncontrados(){
        alterarEstadoTodosBotoes(EstadosBotoes.TODOS_PARES_ENCONTRADOS);
        
        int result = JOptionPane.showConfirmDialog(null,"GANHASTE! JOGAR DE NOVO?", 
               "FIM DO JOGO",JOptionPane.OK_CANCEL_OPTION);
        //sai do jogo
        if (result == JOptionPane.CANCEL_OPTION) System.exit(0);
        
        //cria um novo jogo
        if( result == JOptionPane.OK_OPTION){
            TelaPrincipal tela = new TelaPrincipal();
        };
    }
}
