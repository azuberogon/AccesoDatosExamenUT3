package com.masanz.ut2.ejercicio5.dao;

import com.masanz.ut2.ejercicio5.database.DatabaseManager;
import com.masanz.ut2.ejercicio5.dto.Usuario;
import com.masanz.ut2.ejercicio5.entity.ArticulosEntity;
import com.masanz.ut2.ejercicio5.entity.UsuariosEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDao {

    private EntityManagerFactory emf;

    public UsuariosDao(EntityManagerFactory emf){
        this.emf = emf;
    }


    public UsuariosEntity crearUsuario(Usuario usuario){
        return crearUsuario(usuario.getFullName(), usuario.getUser(), usuario.getEmail(), usuario.getPassword(), usuario.getSaldo());
    }

    public UsuariosEntity crearUsuario(String fullName, String user, String email, String password, int saldo){
        EntityManager em = emf.createEntityManager();

        UsuariosEntity usuario = new UsuariosEntity();
        usuario.setFullName(fullName);
        usuario.setUser(user);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setSaldo(saldo);

        System.out.println(usuario);
        em.persist(usuario);
        em.close();
        System.out.println(usuario);

        return usuario;
//            Usuario usuario = new Usuario(fullName, user, email, password, saldo);
//            String sql = "INSERT INTO usuarios (full_name, user, email, password, creation_date, modification_date) VALUES (?, ?, ?, ?, ?, ?)";
//            Object[] params = {fullName, user, email, password, usuario.getCreationDate(), usuario.getModificationDate()};
//            int registrosIncluidos = DatabaseManager.ejecutarUpdateSQL(sql, params);
//            if(registrosIncluidos>0){
//                // TODO: Actualizar usuario
//                return usuario;
//            }
//            return null;
    }

    public boolean borrarUsuario(UsuariosEntity usuario){
        boolean borradoExitoso = false;
        EntityManager em = emf.createEntityManager();
        UsuariosEntity usuarios = em.find(UsuariosEntity.class, usuario);
        if (usuarios != null) {
            em.remove(usuarios);
        }
        em.close();
        System.out.println(borradoExitoso);
        return borradoExitoso;
        //        String sql = "DELETE FROM usuarios WHERE user LIKE ?";
//        Object[] params = { usuario.getUser() };
//        int registrosEliminados = DatabaseManager.ejecutarUpdateSQL(sql, params);
//        if (registrosEliminados > 0) {
//            borradoExitoso = true;
//        }
    }

    public UsuariosEntity actualizarUsuario(UsuariosEntity usuario){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // BUSCAR NUESTRA ENTITY
        UsuariosEntity usuarios2 = em.find(UsuariosEntity.class, usuario.getId());
        // HACER SETTERS PARA MODIFICAR Y DESPUES ACTUALIZAR
        System.out.println(usuarios2);
        usuarios2.setFullName(usuario.getFullName());
        usuarios2.setUser(usuario.getFullName());
        usuarios2.setEmail(usuario.getEmail());
        usuarios2.setPassword(usuario.getPassword());
        usuarios2.setSaldo(usuario.getSaldo());
        em.merge(usuarios2);
        em.getTransaction().commit();  // Asegúrate de confirmar la transacción
        em.close();
        System.out.println(usuarios2);
        return usuarios2;

        //        String sql = "UPDATE usuarios SET full_name = ?, email = ?, password = ? , modification_date = ? , saldo = ? WHERE user = ?";
//        Object[] params = {usuario.getFullName(), usuario.getEmail(), usuario.getPassword(), new java.util.Date(), usuario.getSaldo(), usuario.getUser()};
//        int registrosActualizados = DatabaseManager.ejecutarUpdateSQL(sql, params);
//        if (registrosActualizados > 0) {
//            return usuario;
//        }
    }

    public UsuariosEntity obtenerUsuario(int userId, String user){
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM UsuariosEntity u WHERE u.id = :idUsuario OR u.user = :user";
        List<UsuariosEntity> usuarios = em.createQuery(jpql, UsuariosEntity.class)
                .setParameter("idUsuario", userId)
                .setParameter("user", user)
                .getResultList();
        em.close();
        if(usuarios!=null && usuarios.size()>0){
            return usuarios.get(0);
        }
        return null;



//        String sql = "SELECT * FROM usuarios WHERE id = ? OR user = ? LIMIT 1";
//        Object[] params = {userId, user};
//        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
//        List<UsuariosEntity> usuarios = procesarResultado(resultado);
//        if(usuarios!=null && usuarios.size()==1){
//            return usuarios.get(0);
//        }
//        return null;
    }

    public List<UsuariosEntity> obtenerUsuarios(){
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM UsuariosEntity u";
        List<UsuariosEntity> usuarios = em.createQuery(jpql, UsuariosEntity.class).getResultList();
        em.close();
        return usuarios;

//        String sql = "SELECT * FROM usuarios";
//        Object[] params = null;
//        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
//        List<Usuario> usuarios = procesarResultado(resultado);
//        return usuarios;
    }

    public UsuariosEntity obtenerUltimoUsuario(){
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM UsuariosEntity u ORDER BY u.id DESC";
        List<UsuariosEntity> usuarios = em.createQuery(jpql, UsuariosEntity.class)
                .setMaxResults(1)
                .getResultList();

        em.close();

        if (usuarios != null && !usuarios.isEmpty()) {
            return usuarios.get(0);
        }
        return null;


//        String sql = "SELECT * FROM usuarios ORDER BY id DESC LIMIT 1";
//        Object[] params = null;
//        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
//        List<Usuario> usuarios = procesarResultado(resultado);
//        if(usuarios!=null && usuarios.size()>0){
//            return usuarios.get(0);
//        }
//        return null;
    }

//    private List<Usuario> procesarResultado(Object[][] resultado){
//        List<Usuario> usuarios = null;
//        if (resultado != null) {
//            usuarios = new ArrayList<>();
//            for (Object[] fila : resultado) {
//                Usuario usuario = new Usuario();
//                usuario.setId((Integer) fila[0]);
//                usuario.setFullName((String) fila[1]);
//                usuario.setUser((String) fila[2]);
//                usuario.setEmail((String) fila[3]);
//                usuario.setPassword((String) fila[4]);
//                usuario.setCreationDate((Date) fila[5]);
//                usuario.setModificationDate((Date) fila[6]);
//                usuario.setSaldo((Integer) fila[7]);
//                usuarios.add(usuario);
//            }
//        } else {
//            System.out.println("El resultado es nulo.");
//        }
//        return usuarios;
//    }

    public UsuariosEntity autenticar(String user, String password){
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM UsuariosEntity u WHERE u.user = :user AND u.password = :password";

        // Configurar y establecer los parámetros
        List<UsuariosEntity> usuarios = em.createQuery(jpql, UsuariosEntity.class)
                .setParameter("user", user)
                .setParameter("password", password)
                .getResultList();

        em.close();

        // Obtener los resultados de la consulta
//        List<UsuariosEntity> myEntityList = query.getResultList();

        if (usuarios != null && usuarios.size() > 0) {
            System.out.println(usuarios.getFirst().getId());
            System.out.println("correcto");
            return usuarios.get(0);

        }
        return null;


        /*String sql = "SELECT * FROM usuarios WHERE user = ? AND password = ?";
        Object[] params = {user, password};
        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
        List<Usuario> usuarios = procesarResultado(resultado);
        if (usuarios!=null && usuarios.size()>0) {
            return usuarios.get(0);
        }*/
    }

//    public boolean borrarUsuariosAusentes(int cantidad){
//        EntityManager em = emf.createEntityManager();
//        String jpql = "DELETE FROM UsuariosEntity u WHERE u.id";
//        int usuariosEliminados = em.createQuery(jpql)
//                .setMaxResults(cantidad)
//                .executeUpdate();
////        boolean borradoExitoso = false;
////        String sql = "DELETE FROM usuarios ORDER BY modification_date ASC LIMIT ?";
////        Object[] params = {cantidad};
////        int usuariosEliminados = DatabaseManager.ejecutarUpdateSQL(sql, params);
////        if (usuariosEliminados > 0) {
////            borradoExitoso = true;
////        }
////        return borradoExitoso;
//    }

}
