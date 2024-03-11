package com.masanz.ut2.ejercicio5;

import com.masanz.ut2.ejercicio5.dao.ArticulosDao;
import com.masanz.ut2.ejercicio5.dao.ComprasDao;
import com.masanz.ut2.ejercicio5.dao.UsuariosDao;
import com.masanz.ut2.ejercicio5.database.DatabaseManager;
import com.masanz.ut2.ejercicio5.dto.Articulo;
import com.masanz.ut2.ejercicio5.dto.Compras;
import com.masanz.ut2.ejercicio5.dto.Usuario;
import com.masanz.ut2.ejercicio5.entity.ArticulosEntity;
import com.masanz.ut2.ejercicio5.entity.ComprasEntity;
import com.masanz.ut2.ejercicio5.entity.UsuariosEntity;
import freemarker.template.Configuration;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class ServerMain {

    private static final Logger logger = LogManager.getLogger(ServerMain.class);
    private static int loggedUserId = -1;

    public static void main(String[] args) {

        // Tipos de logger: TRACE, DEBUG, INFO, WARN, ERROR
        logger.info("PODEIS EMPLEAR ESTE METODO PARA SACAR INFORMACION, COMO SI FUESE UN SOUT");
        logger.error("ESTE SIRVE PARA VOLCAR ERRORES, POR EJEMPLO, LAS EXCEPCIONES");

        staticFileLocation("/public");
        port(8080);

        FreeMarkerEngine freeMarker = new FreeMarkerEngine();
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        configuration.setClassForTemplateLoading(ServerMain.class, "/templates");
        freeMarker.setConfiguration(configuration);


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unidadPersistencia");
        EntityManager em = emf.createEntityManager();

        ArticulosDao articulosDao = new ArticulosDao(emf);
        ComprasDao comprasDao = new ComprasDao(emf);
        UsuariosDao usuariosDao = new UsuariosDao(emf);


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "login.ftl");
        }, freeMarker);

        get("/error", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("error", "¡Usuario o contraseña incorrectos!");
            return new ModelAndView(model, "login.ftl");
        }, freeMarker);

        post("/login", (request, response) -> {

            String username = request.queryParams("username");
            String password = request.queryParams("password");

            UsuariosEntity usuario = usuariosDao.autenticar(username, password);

            if (usuario!=null) {
                loggedUserId = usuario.getId();
                response.redirect("/home");
            } else {
                response.redirect("/error");
            }
            return null;
        });

        get("/home", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("userId", loggedUserId);
            return new ModelAndView(model, "home.ftl");
        }, freeMarker);

        get("/articulos", (request, response) -> {
            List<ArticulosEntity> articulos = articulosDao.obtenerArticulosPropietario(loggedUserId);
            Map<String, Object> model = new HashMap<>();
            model.put("mostrarIdExtra", false);
            model.put("articulos", articulos);
            return new ModelAndView(model, "articulos.ftl");
        }, freeMarker);

        get("/articulos/:id", (request, response) -> {
            int userId = Integer.parseInt(request.params(":id"));
            List<ArticulosEntity> articulos = articulosDao.obtenerArticulosPropietario(userId);
            Map<String, Object> model = new HashMap<>();
            model.put("mostrarIdExtra", true);
            model.put("articulos", articulos);
            return new ModelAndView(model, "articulos.ftl");
        }, freeMarker);

        get("/usuarios", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<UsuariosEntity> usuarios = usuariosDao.obtenerUsuarios();
            model.put("usuarios", usuarios);
            return new ModelAndView(model, "usuarios.ftl");
        }, freeMarker);

        get("/usuario/:id", (request, response) -> {
            int userId = Integer.parseInt(request.params(":id"));
            logger.info("BUSCANDO INFORMACION DEL USUARIO CON ID: "+userId);
            UsuariosEntity usuario = usuariosDao.obtenerUsuario(userId, null);
            System.out.println(usuario);
            Map<String, Object> model = new HashMap<>();
            model.put("usuario", usuario);
            return new ModelAndView(model, "usuario.ftl");
        }, freeMarker);

        get("/comprar/:id", (request, response) -> {
            int articuloId = Integer.parseInt(request.params(":id"));
            logger.info("COMPRANDO ARTICULO ID: "+articuloId);
            ArticulosEntity articulo = articulosDao.obtenerArticulo(articuloId);
            int valorArticulo = articulo.getValor();
            UsuariosEntity usuarioComprador = usuariosDao.obtenerUsuario(loggedUserId, null);
            UsuariosEntity usuarioVendedor = usuariosDao.obtenerUsuario(articulo.getIdPropietario(), null);
            if(usuarioComprador.getSaldo()>=valorArticulo && usuarioComprador.getId()!=usuarioVendedor.getId()){
                usuarioComprador.setSaldo(usuarioComprador.getSaldo() - valorArticulo);
                usuarioVendedor.setSaldo(usuarioVendedor.getSaldo() + valorArticulo);
                articulo.setIdPropietario(usuarioComprador.getId());
                usuarioComprador = usuariosDao.actualizarUsuario(usuarioComprador);
                logger.info("Usuario Comprador después de actualizar: " + usuarioComprador);
                usuarioVendedor = usuariosDao.actualizarUsuario(usuarioVendedor);
                logger.info("Usuario Vendedor después de actualizar: " + usuarioVendedor);
                articulo = articulosDao.actualizarArticulo(articulo);
                logger.info("Articulo después de actualizar: " + articulo);

                if(usuarioComprador!=null && usuarioVendedor!=null && articulo!=null){
                    comprasDao.crearCompras(articulo.getId(), usuarioComprador.getId(), usuarioVendedor.getId(),  new Date());
                    //DatabaseManager.persistir();
                } else {
                    DatabaseManager.deshacer();
                } //else {
//                    //DatabaseManager.deshacer();
//                }
                List<ComprasEntity> compras = comprasDao.obtenerCompras();
                for (ComprasEntity compra : compras) {
                    System.out.println(compra);
                }
            }
            response.redirect("/home");
            return null;
        }, freeMarker);

        init();
    }
}