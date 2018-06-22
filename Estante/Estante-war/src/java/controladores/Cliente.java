package controladores;

import entidades.Clientes;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entidades.ClientesFacade;
import javax.enterprise.context.RequestScoped;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;

@Named(value = "cliente")
@RequestScoped
public class Cliente implements Serializable {

    /**
     * Creates a new instance of Cliente
     */
    public Cliente() {
    }
    
    @EJB
    private ClientesFacade clientesFacade;
    private Clientes cliente = new Clientes();
    

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    
    public List <Clientes> getListaClientes(){
        return this.clientesFacade.findAll();
    }
        
   public Clientes pegarCliente(int id){
       return this.clientesFacade.find(id);
   }
    
    
    
    public String insert(){
        try{
            Clientes cliente = new Clientes();
            cliente.setIdCliente(this.cliente.getIdCliente());
            cliente.setNome(this.cliente.getNome());
            cliente.setEndereco(this.cliente.getEndereco());
            cliente.setCidade(this.cliente.getCidade());
            cliente.setUf(this.cliente.getUf());
            cliente.setTelefone(this.cliente.getTelefone());
            cliente.setEmail(this.cliente.getEmail());
            cliente.setSenha(this.cliente.getSenha());
            this.clientesFacade.create(cliente);
            return "index";
        }catch(Exception e){
            FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao adicionar!"));
        }finally{
            zerarCampos();
        }

        return "cadastro";
    }
    
    public void delete(Clientes cliente){
        this.clientesFacade.remove(cliente);
    }
    
    public void confirmaCadastro(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizado com sucesso!"));
    }
    
    public void zerarCampos(){
        cliente.setNome(null);
        cliente.setEndereco(null);
        cliente.setCidade(null);
        cliente.setUf(null);
        cliente.setTelefone(null);
        cliente.setEmail(null);
    }
        
    
    
}
