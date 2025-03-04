package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.entity.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class UsuarioImplDAO implements GenericDAO<Usuario, String> {

    @PersistenceContext(unitName = "LivingCorpPU")
    private EntityManager entityManager;

    @Override
    public Usuario crear(Usuario entidad) {
        entityManager.persist(entidad);
        return entidad;
    }

    @Override
    public Usuario leer(String id) {
        return entityManager.find(Usuario.class, id);
    }

    @Override
    public Usuario actualizar(Usuario entidad) {
        entityManager.merge(entidad);
        return entidad;
    }

    @Override
    public void eliminar(Usuario entidad) {
        entityManager.remove(entidad);
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }
}
