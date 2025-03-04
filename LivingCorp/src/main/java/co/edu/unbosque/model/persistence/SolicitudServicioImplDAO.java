package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.entity.SolicitudServicio;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class SolicitudServicioImplDAO implements GenericDAO<SolicitudServicio, Integer>{

    @PersistenceContext(unitName = "LivingCorpPU")
    private EntityManager entityManager;
    @Override
    public SolicitudServicio crear(SolicitudServicio entidad) {
        entityManager.persist(entidad);
        return entidad;
    }

    @Override
    public SolicitudServicio leer(Integer id) {
        return entityManager.find(SolicitudServicio.class, id);
    }

    @Override
    public SolicitudServicio actualizar(SolicitudServicio entidad) {
        entityManager.merge(entidad);
        return entidad;
    }

    @Override
    public void eliminar(SolicitudServicio entidad) {
        entityManager.remove(entidad);
    }

    @Override
    public List<SolicitudServicio> obtenerTodos() {
        return entityManager.createQuery(
                "SELECT s FROM SolicitudServicio s", SolicitudServicio.class
        ).getResultList();
    }
}
