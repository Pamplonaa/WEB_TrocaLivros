package entidades;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
@LocalBean
public class ClientesFacade extends AbstractFacade<Clientes>{

    @PersistenceContext(unitName = "Estante-ejbPU")
    private EntityManager em;

    public ClientesFacade() {
        super(Clientes.class);
        
    }

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Clientes verificaUsuario(String email, String senha){
        
        Query query = em.createNamedQuery("Clientes.login").setParameter("email",email).setParameter("senha", senha);
        return (Clientes) query.getSingleResult();

    }
}
