package mx.uv.fei.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//endpoint: punto por el cual se va a acceder a la información.
@CrossOrigin(origins = "*")
@RestController
public class SaludarControlador {

    String nombre;

    //ruta en sustantivo plural

    @GetMapping("/saludos")
    public String saludar() {
        return "Hola Mundo!" + nombre;
    }

    @GetMapping("/despedidas")
    public String adios () {
        return "Adios Mundo!";
    }
    
    @PostMapping("/nombramientos")
    public void nombre () {
        nombre = "YO";
    }

    //renombramientos
    @PutMapping("/nombramientos")
    public void met1() {
        nombre = "Otro nombre";
    }

    //desnombramientos
    @DeleteMapping("/nombramientos")
    public void met2() {
        nombre = null;
    }

    @GetMapping("/saludar/{nombre}")
    public saludador met3(@PathVariable String nombre) {
        return new saludador(nombre);
    }

    @GetMapping("/saludar2/{nombreX}")
    public saludador met4(@PathVariable("nombreX") String nombre) {
        System.out.println(nombre);
        Saludador = new saludador(nombre);
        return Saludador;
    }


    saludador Saludador;

    @PostMapping("/crearSaludador")
    public String crearSaludador(@RequestBody saludador nombre) {
        Saludador = nombre;
        return "Saludaor Creado: " + nombre;
    }

    @GetMapping ("/obtenerSaludador")
    public saludador obtenerSaludador() {
        return Saludador;
    }

    //agregar un metodo que mapee raiz (/)
    @RequestMapping("/")
    public String raiz() {
        String contenido = null;
        contenido = """
                <h1> Menu de opciones </h1>
                <ul>
                    <li> <a href='/saludos'>/saludos</a></li>
                    <li> <a href='/despedidas'>/despedidas</a></li>
                    <li> /nombramientos (P|P|D) </li>
                    <li> <a href='/saludar/default'>/saludar/{nombre}</a></li>
                    <li> <a href='/saludar2/default2'>/saludar2/{nombreX}</a></li>
                    <li> /crearSaludador (Post - json)</li>
                    <li> <a href='/obtenerSaludador'>/obtenerSaludador</a></li>
                    <li> <a href='/query1'>/query1?nombre=valor&paterno=valor2{&materno=valor3}</a></li>
                    <li> <a href='/query2'>/query2?nombre=valor</a></li>
                </ul>
                """;
        return contenido;
    }

    @GetMapping("/query1")
    public String query1(@RequestParam String nombre,
        @RequestParam String paterno,
        @RequestParam(defaultValue = "") String materno) {
        System.out.println(nombre);
        return (nombre + " " + paterno + " " + materno);
    }

    @GetMapping("/query2")
    public String query2(@RequestParam(defaultValue = "default") String nombre) {
        System.out.println(nombre);
        return nombre;
    }
}