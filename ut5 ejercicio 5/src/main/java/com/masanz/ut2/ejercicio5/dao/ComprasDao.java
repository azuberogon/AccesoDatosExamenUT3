package com.masanz.ut2.ejercicio5.dao;

import com.masanz.ut2.ejercicio5.database.DatabaseManager;
import com.masanz.ut2.ejercicio5.dto.Compras;
import com.masanz.ut2.ejercicio5.entity.ArticulosEntity;
import com.masanz.ut2.ejercicio5.entity.ComprasEntity;
import com.masanz.ut2.ejercicio5.entity.UsuariosEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComprasDao {
    private EntityManagerFactory emf;
    public ComprasDao(EntityManagerFactory emf){
        this.emf = emf;
    }

    public ComprasEntity crearCompras(ComprasEntity compras){
        return crearCompras(compras.getIdObjeto(), compras.getIdComprador(), compras.getIdVendedor(), compras.getFechaCompra());
    }

    public ComprasEntity crearCompras(int idArticulo, int idComprador, int idVendedor, Date fechaComprar){
        EntityManager em = emf.createEntityManager();

        ComprasEntity compras = new ComprasEntity();
        compras.setIdObjeto(idArticulo);
        compras.setIdComprador(idComprador);
        compras.setIdVendedor(idVendedor);
        compras.setFechaCompra(fechaComprar);

        System.out.println(compras);
        em.persist(compras);
        System.out.println(compras);

        em.persist(compras);
        return compras;





//        Compras compras = new Compras(idArticulo, idComprador, idVendedor);
//        String sql = "INSERT INTO compras (id_objeto, id_comprador, id_vendedor,  fecha_compra) VALUES (?, ?, ?, ?)";
//        Object[] params = {idArticulo, idComprador, idVendedor, fechaComprar};
//        int registrosIncluidos = DatabaseManager.ejecutarUpdateSQL(sql, params);
//        if(registrosIncluidos>0){
//            return compras;
//        }
//        return null;
    }

    public List<ComprasEntity> obtenerCompras(){
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT c FROM ComprasEntity c";
        List<ComprasEntity> comprasEntities = em.createQuery(jpql, ComprasEntity.class).getResultList();
        return comprasEntities;


//        String sql = "SELECT * FROM compras";
//        Object[] params = null;
//        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
//        List<Compras> compras = procesarResultado(resultado);
//        return compras;
    }

//    private List<Compras> procesarResultado(Object[][] resultado){
//        List<Compras> compras = null;
//        if (resultado != null) {
//            compras = new ArrayList<>();
//            for (Object[] fila : resultado) {
//                Compras compra = new Compras();
//                compra.setId((Integer) fila[0]);
//                compra.setIdArticulo((Integer) fila[1]);
//                compra.setIdComprador((Integer) fila[2]);
//                compra.setIdVendedor((Integer) fila[3]);
//                compra.setFechaComprar((Date) fila[4]);
//                compras.add(compra);
//            }
//        } else {
//            System.out.println("El resultado es nulo.");
//        }
//        return compras;
//    }

}
