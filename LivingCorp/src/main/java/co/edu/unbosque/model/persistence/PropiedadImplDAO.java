package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.entity.Propiedad;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class PropiedadImplDAO implements GenericDAO<Propiedad, Integer> {

    @PersistenceContext(unitName = "LivingCorpPU")
    private EntityManager entityManager;

    @Override
    public Propiedad crear(Propiedad entidad) {
        entityManager.persist(entidad);
        return entidad;
    }

    @Override
    public Propiedad leer(Integer id) {
        return entityManager.find(Propiedad.class, id);
    }

    @Override
    public Propiedad actualizar(Propiedad entidad) {
        entityManager.merge(entidad);
        return entidad;
    }

    @Override
    public void eliminar(Propiedad entidad) {
        entityManager.remove(entidad);
    }

    @Override
    public List<Propiedad> obtenerTodos() {
        return entityManager.createQuery("SELECT p FROM Propiedad p", Propiedad.class).getResultList();
    }
}
