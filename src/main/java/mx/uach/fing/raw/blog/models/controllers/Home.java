package mx.uach.fing.raw.blog.models.controllers;
import static spark.Spark.*;

/* @author Baruch Sias
  Controlador principal de la aplicación
*/
public class Home {
    public static void main(String[] args) {
        get("/", (req,res) -> "Hola Blog Mío");
    }
    
}
