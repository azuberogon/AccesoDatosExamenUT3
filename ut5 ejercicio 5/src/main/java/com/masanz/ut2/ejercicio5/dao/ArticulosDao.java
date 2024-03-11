package com.masanz.ut2.ejercicio5.dao;

import com.masanz.ut2.ejercicio5.database.DatabaseManager;
import com.masanz.ut2.ejercicio5.dto.Articulo;
import com.masanz.ut2.ejercicio5.entity.ArticulosEntity;
import com.masanz.ut2.ejercicio5.entity.UsuariosEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class ArticulosDao {
    private EntityManagerFactory emf;
    public ArticulosDao(EntityManagerFactory emf){
        this.emf = emf;
    }

    public ArticulosEntity crearArticulo(ArticulosEntity articulo){
        return crearArticulo(articulo.getNombre(), articulo.getValor(), articulo.getIdPropietario());
    }

    public ArticulosEntity crearArticulo(String nombre, int valor, int idPropietario){
        EntityManager em = emf.createEntityManager();

        ArticulosEntity articulos = new ArticulosEntity();
        articulos.setNombre(nombre);
        articulos.setValor(valor);
        articulos.setIdPropietario(idPropietario);

        System.out.println(articulos);
        em.persist(articulos);
        System.out.println(articulos);

        return articulos;



//        ArticulosEntity articulo = new ArticulosEntity();
//        String sql = "INSERT INTO articulos (id, nombre, valor, id_propietario) VALUES (?, ?, ?, ?)";
//        Object[] params = {id, nombre, valor, idPropietario};
//        int registrosIncluidos = DatabaseManager.ejecutarUpdateSQL(sql, params);
//        if(registrosIncluidos>0){
//            return articulo;
//        }
//        return null;
    }

    public boolean borrarArticulo(ArticulosEntity articulo){
        boolean borradoExitoso = false;
        EntityManager em = emf.createEntityManager();
        ArticulosEntity articulos = em.find(ArticulosEntity.class, articulo);
        if (articulos != null) {
            em.remove(articulos);
        }
        System.out.println(borradoExitoso);
        return borradoExitoso;

//        boolean borradoExitoso = false;
//        String sql = "DELETE FROM articulos WHERE id = ?";
//        Object[] params = { articulo.getId() };
//        int registrosEliminados = DatabaseManager.ejecutarUpdateSQL(sql, params);
//        if (registrosEliminados > 0) {
//            borradoExitoso = true;
//        }
//        return borradoExitoso;
    }

    public ArticulosEntity actualizarArticulo(ArticulosEntity articulo){
        return actualizarArticulo(articulo.getId(), articulo.getNombre(), articulo.getValor(), articulo.getIdPropietario());
    }

    private ArticulosEntity actualizarArticulo(int id, String nombre, int valor, int idPropietario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // BUSCAR NUESTRA ENTITY
        ArticulosEntity articulos = new ArticulosEntity();
        articulos = em.find(ArticulosEntity.class, id);
        // HACER SETTERS PARA MODIFICAR Y DESPUES ACTUALIZAR
        System.out.println(articulos);
        articulos.setNombre(nombre);
        articulos.setValor(valor);
        articulos.setIdPropietario(idPropietario);
        em.merge(articulos);
        System.out.println(articulos);
        em.getTransaction().commit();
        return articulos;


//        String sql = "UPDATE articulos SET nombre = ?, valor = ?, id_propietario = ? WHERE id = ?";
//        Object[] params = {nombre, valor, idPropietario, id};
//        int registrosActualizados = DatabaseManager.ejecutarUpdateSQL(sql, params);
//        if (registrosActualizados > 0) {
//            ArticulosEntity articulo = new ArticulosEntity();
//            return articulo;
//        }
    }

    public List<ArticulosEntity> obtenerArticulos(){
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT a FROM ArticulosEntity a";
        List<ArticulosEntity> articulos = em.createQuery(jpql, ArticulosEntity.class).getResultList();
        return articulos;


        /*String sql = "SELECT * FROM articulos";
        Object[] params = null;
        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
        List<ArticulosEntity> articulos = procesarResultado(resultado);
        return articulos;*/
    }

    public ArticulosEntity obtenerArticulo(int articuloId){
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT a FROM ArticulosEntity a WHERE a.id = :idArticulo";
        List<ArticulosEntity> articulos = em.createQuery(jpql, ArticulosEntity.class)
                .setParameter("idArticulo", articuloId)
                .getResultList();
        if(articulos!=null && articulos.size()>0){
            return articulos.get(0);
        }
        return null;


//        String sql = "SELECT * FROM articulos WHERE id = ?";
//        Object[] params = {articuloId};
//        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
//        List<Articulo> articulos = procesarResultado(resultado);
    }

    public List<ArticulosEntity> obtenerArticulosPropietario(int idPropietario){
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT a FROM ArticulosEntity a WHERE a.idPropietario = :idPropietario";
            List<ArticulosEntity> articulos = em.createQuery(jpql, ArticulosEntity.class)
                .setParameter("idPropietario", idPropietario)
                .getResultList();
        System.out.println(articulos.toString());

        return articulos;


        /*String sql = "SELECT * FROM articulos WHERE id_propietario = ?";
        Object[] params = {idPropietario};
        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
        List<Articulo> articulos = procesarResultado(resultado);
        return articulos;*/
    }

    public ArticulosEntity obtenerUltimoArticulo(){
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT a FROM ArticulosEntity a ORDER BY a.id DESC";
            List<ArticulosEntity> articulos = em.createQuery(jpql, ArticulosEntity.class)
                    .getResultList();
            if (articulos != null && !articulos.isEmpty()) {
                return articulos.get(0);
            }
            return null;
        } finally {
            em.close();
        }

        /*String sql = "SELECT * FROM articulos ORDER BY id DESC LIMIT 1";
        Object[] params = null;
        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
        List<Articulo> articulos = procesarResultado(resultado);
        if(articulos!=null && articulos.size()>0){
            return articulos.get(0);
        }
        return null;*/
    }

    /*private List<Articulo> procesarResultado(Object[][] resultado){
        List<Articulo> articulos = null;
        if (resultado != null) {
            articulos = new ArrayList<>();
            for (Object[] fila : resultado) {
                Articulo articulo = new Articulo();
                articulo.setId((Integer) fila[0]);
                articulo.setNombre((String) fila[1]);
                articulo.setValor((Integer) fila[2]);
                articulo.setIdPropietario((Integer) fila[3]);
                articulos.add(articulo);
            }
        } else {
            System.out.println("El resultado es nulo.");
        }
        return articulos;
    }*/

}
