package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.entity.CotizacionServicio;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class CotizacionServicioImplDAO implements GenericDAO<CotizacionServicio, Integer>{

    @PersistenceContext(unitName = "LivingCorpPU")
    private EntityManager entityManager;

    @Override
    public CotizacionServicio crear(CotizacionServicio entidad) {
        entityManager.persist(entidad);
        return entidad;
    }

    @Override
    public CotizacionServicio leer(Integer id) {
        return entityManager.find(CotizacionServicio.class, id);
    }

    @Override
    public CotizacionServicio actualizar(CotizacionServicio entidad) {
        entityManager.merge(entidad);
        return entidad;
    }

    @Override
    public void eliminar(CotizacionServicio entidad) {
        entityManager.remove(entidad);
    }

    @Override
    public List<CotizacionServicio> obtenerTodos() {
        return entityManager.createQuery("SELECT c FROM CotizacionServicio c", CotizacionServicio.class).getResultList();
    }
}
