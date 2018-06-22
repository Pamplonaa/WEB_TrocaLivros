package controladores;

import entidades.Clientes;
import entidades.ClientesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    /**
     * Creates a new instance of Login
     */
    public Login() {
    }
    
    @EJB
    private ClientesFacade clientesFacade;
    private String login;
    private String senha;
    
    @ManagedProperty(value = "#{userBO}")
    private Clientes userBO;


    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    public String logar(){
        try{
            Clientes cliente = clientesFacade.verificaUsuario(this.login, this.senha);
            if(cliente == null){
                throw new Exception ("Erro no login");
            }else{
                SessionContext.getInstance().setUsuarioLogado(cliente);
                return "home.xhtml?faces-redirect=true";
            }    
       }catch(Exception e){
            RequestContext.getCurrentInstance().update("growl");
            FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Usuário e/ou Senha inválido! Usuario "+ this.login + " senha " +this.senha));
        }finally{
            zerarCampos();
        }
        
       return "";
    }        

    public String fazerLogout(){
        SessionContext.getInstance().encerrarSessao();
        return "index.xhtml";
    }        
            
    
    
    public Clientes getUserBO() {
        Clientes cliente = (Clientes) SessionContext.getInstance().getUsuarioLogado();
        return cliente;
    }
    
    public void setUserBO(Clientes cliente){
        this.userBO = cliente;
    }
    
    public void zerarCampos(){
        setLogin(null);
        setSenha(null);
    }
    
}
