package controladores;

import entidades.Livros;
import entidades.LivrosFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "livro")
@RequestScoped
public class Livro {

    @EJB
    private LivrosFacade livrosFacade;
    private Livros livro = new Livros();

    /**
     * Creates a new instance of Livro
     */
    public Livro() {
    }

    public LivrosFacade getLivrosFacade() {
        return livrosFacade;
    }

    public void setLivrosFacade(LivrosFacade livrosFacade) {
        this.livrosFacade = livrosFacade;
    }  
    
    public Livros getLivro(){
        return this.livro;
    }
    
    public void setLivro(Livros livro){
        this.livro = livro;
    }
    
    public List<Livros> listarLivros(){
        return livrosFacade.getLivros();
    }
    
    public String strDisp(int disp){
        if(disp == 1){
            return "Disponivel";
        }
        return "Nao disponivel";
    }
    
    public String strTextoBotaoAltDisp(int disp){
        if(disp == 1){
            return "Nao quero mais trocar";
        }
        return "Quero trocar!";
    }
    
    public List<Livros> getLivrosDonoAtual(int idDono){
        return this.livrosFacade.getLivrosDono(idDono);
    }
    
    public List<Livros> getLivrosNotDonoAtual(int idDono){
        return this.livrosFacade.getLivrosDispOutroDono(idDono);
    }
    
    public String trocar(int idUser, int idLivro){
        Livros livroAtual = this.livrosFacade.find(idLivro);
        
        livroAtual.setDono(idUser);
        livroAtual.setDisponibilidade(0);
        
        this.livrosFacade.edit(livroAtual);
        
        return "livrosDisponiveis.xhtml?faces-redirect=true";
    }
    
    public String alterarDisp(int idLivro){
        Livros livroAtual = this.livrosFacade.find(idLivro);
        
        if(livroAtual.getDisponibilidade() == 1){
            livroAtual.setDisponibilidade(0);
        }else{
            livroAtual.setDisponibilidade(1);
        }
        
        this.livrosFacade.edit(livroAtual);
        
        return "home.xhtml?faces-redirect=true";
    }
    
     public String insert(int idDono){
        try{
            Livros livroAtual = new Livros();
            livroAtual.setIdLivro(this.livro.getIdLivro());
            livroAtual.setTitulo(this.livro.getTitulo());
            livroAtual.setDisponibilidade(1);
            livroAtual.setDono(idDono);
            
            this.livrosFacade.create(livroAtual);
            return "home";
        }catch(Exception e){
            FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao adicionar!"));
        }finally{
            zerarCampos();
        }

        return "addLivro";
    }
     
     public void zerarCampos(){
        livro.setTitulo(null);
        livro.setDono(0);
    }
    
}
