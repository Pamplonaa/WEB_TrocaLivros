package entidades;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
@LocalBean
public class LivrosFacade extends AbstractFacade<Livros>{

    @PersistenceContext(unitName = "Estante-ejbPU")
    private EntityManager em;

    public LivrosFacade() {
        super(Livros.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
    
    public void persist(Object object) {
        em.persist(object);
    }
    
    /**
     *
     * @return
     */
    public List<Livros> getLivros(){
        Query query = em.createNamedQuery("Livros.findAll");
        return query.getResultList();
    }
    
    public List<Livros> getLivrosDono(){
        Query query = em.createNamedQuery("Livros.findByDono");
        return query.getResultList();
    }
    
    public List<Livros> getLivrosOutroDono(){
        Query query = em.createNamedQuery("Livros.findByNotDono");
        return query.getResultList();
    }
    
    public List<Livros> getLivrosDono(int idDono){
        Query query = em.createNamedQuery("Livros.findByDono").setParameter("dono",idDono);
        return query.getResultList();
    }
    
    public List<Livros> getLivrosOutroDono(int idDono){
        Query query = em.createNamedQuery("Livros.findByNotDono").setParameter("dono",idDono);
        return query.getResultList();
    }
    
    public List<Livros> getLivrosDispOutroDono(int idDono){
        Query query = em.createNamedQuery("Livros.findByDispNotDono").setParameter("dono",idDono);
        return query.getResultList();
    }    
}
