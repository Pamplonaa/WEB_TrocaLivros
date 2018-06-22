package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "LIVROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livros.findAll", query = "SELECT l FROM Livros l")
    , @NamedQuery(name = "Livros.findByIdLivro", query = "SELECT l FROM Livros l WHERE l.idLivro = :idLivro")
    , @NamedQuery(name = "Livros.findByDisponibilidade", query = "SELECT l FROM Livros l WHERE l.disponibilidade = :disponibilidade")
    , @NamedQuery(name = "Livros.findByTitulo", query = "SELECT l FROM Livros l WHERE l.titulo = :titulo")
    , @NamedQuery(name = "Livros.findByDono", query = "SELECT l FROM Livros l WHERE l.dono = :dono")
    , @NamedQuery(name = "Livros.findByNotDono", query = "SELECT l FROM Livros l WHERE l.dono <> :dono")
    , @NamedQuery(name = "Livros.findByDispNotDono", query = "SELECT l FROM Livros l WHERE l.dono <> :dono AND l.disponibilidade = 1")})
public class Livros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LIVRO")
    private Integer idLivro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DISPONIBILIDADE")
    private int disponibilidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "TITULO")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DONO")
    private int dono;

    public Livros() {
    }

    public Livros(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public Livros(Integer idLivro, int disponibilidade, String titulo) {
        this.idLivro = idLivro;
        this.disponibilidade = disponibilidade;
        this.titulo = titulo;
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public int getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(int disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public int getDono() {
        return dono;
    }

    public void setDono(int dono) {
        this.dono = dono;
    }
    
    public Clientes pegarDono(){
        ClientesFacade c = new ClientesFacade();
        Clientes donoAtual = c.find(this.dono);
        return donoAtual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLivro != null ? idLivro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livros)) {
            return false;
        }
        Livros other = (Livros) object;
        if ((this.idLivro == null && other.idLivro != null) || (this.idLivro != null && !this.idLivro.equals(other.idLivro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "orm.Livros[ idLivro=" + idLivro + " ]";
    }
    
}
