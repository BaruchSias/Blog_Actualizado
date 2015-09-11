
package mx.fing.uach.raw.blog.controllers;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mx.fing.uach.raw.blog.models.Post;
import static spark.Spark.*;

/**
 * Controlador principal de la aplicación
 *
 * @author Rodrigo Arturo Ramos Nájera
 */
public class Home {
    
    public static void main(String[] args) {
        
        try {
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("blogPU");
            EntityManager em = emf.createEntityManager();//nos hace todo el kit(no se cual kit :v tendras que invenstigarle futuro Alejandro)
            
             
          
            Post p = new Post("un post chidote","mi super  post",new Date());
            
            em.getTransaction().begin();
            em.persist(p);
            
            em.getTransaction().commit();
            em.close();
            
            Configuration freeMarkerConfiguration = new Configuration();
            freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(Home.class, "/"));
            StringWriter writer = new StringWriter();
            
            Template homeTemplate = freeMarkerConfiguration.getTemplate("home.ftl");
            Map<String, Object> parametros = new HashMap<>();
            homeTemplate.process(parametros, writer);
            
            get("/", (req, res) -> writer);
        } catch (TemplateException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
