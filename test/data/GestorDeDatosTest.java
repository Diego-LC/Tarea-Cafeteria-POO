package data;

import model.Cafeteria;
import model.Cafe;
import model.IngredientesOpcionales;
import model.RedesSociales;
import model.Tamaño;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestorDeDatosTest {

    private GestorDeDatos gestorDeDatos;
    private Cafeteria cafeteria;

    @BeforeEach
    void setUp() {
        gestorDeDatos = new GestorDeDatos();
        cafeteria = new Cafeteria();
    }

    @Test
    void leerArchivoCafeteria() throws IOException {
        // Create a temporary directory for test files
        Path tempDir = Files.createTempDirectory("test");
        String direccionArchivoCafeteria = tempDir.resolve("cafeteria.txt").toString();
        String direccionArchivoCafes = tempDir.resolve("cafes.txt").toString();

        // Create a sample cafeteria file
        File cafeteriaFile = new File(direccionArchivoCafeteria);
        FileWriter cafeteriaWriter = new FileWriter(cafeteriaFile);
        cafeteriaWriter.write("Cafeteria1,Address1,Facebook,Twitter,instagram");
        cafeteriaWriter.close();

        // Create a sample cafes file
        File cafesFile = new File(direccionArchivoCafes);
        FileWriter cafesWriter = new FileWriter(cafesFile);
        cafesWriter.write("Cafe1,1,2,GRANDE,LECHE");
        cafesWriter.close();

        // Call the method to test
        gestorDeDatos.leerArchivoCafeteria(cafeteria, direccionArchivoCafeteria, direccionArchivoCafes);

        // Assert the values are correctly set
        assertEquals("Cafeteria1", cafeteria.getNombreCafeteria());
        assertEquals("Address1", cafeteria.getDireccion());
        assertEquals(1, cafeteria.getCafes().size());

        // Clean up the temporary files
        cafeteriaFile.delete();
        cafesFile.delete();
        tempDir.toFile().delete();
    }

    @Test
    void leerArchivoCafes() throws IOException {
        // Create a temporary directory for test files
        Path tempDir = Files.createTempDirectory("test");
        String direccionArchivo = tempDir.resolve("cafes.txt").toString();

        // Create a sample cafes file
        File cafesFile = new File(direccionArchivo);
        FileWriter cafesWriter = new FileWriter(cafesFile);
        cafesWriter.write("Cafe1,1,2,GRANDE,LECHE");
        cafesWriter.close();

        // Call the method to test
        gestorDeDatos.leerArchivoCafes(cafeteria, direccionArchivo);

        // Assert the values are correctly set
        assertEquals(1, cafeteria.getCafes().size());
        Cafe cafe = cafeteria.getCafes().get(0);
        assertEquals("Cafe1", cafe.getNombre());
        assertEquals(Tamaño.GRANDE, cafe.getTamaño());
        assertEquals(IngredientesOpcionales.LECHE, cafe.getIngredientesOpcionales());

        // Clean up the temporary file
        cafesFile.delete();
        tempDir.toFile().delete();
    }

    @Test
    void registrarDato() throws IOException {
        // Create a temporary directory for test files
        Path tempDir = Files.createTempDirectory("test");
        String direccionArchivo = tempDir.resolve("datos.txt").toString();

        // Create a sample object to register
        String objeto = "Sample Object";

        // Call the method to test
        boolean result = gestorDeDatos.registrarDato(objeto, direccionArchivo);

        // Assert the result is true
        assertTrue(result);

        // Read the contents of the file
        List<String> lines = Files.readAllLines(Path.of(direccionArchivo));

        // Assert the object is correctly registered
        assertEquals(1, lines.size());
        assertEquals(objeto, lines.get(0));

        // Clean up the temporary file
        Files.delete(Path.of(direccionArchivo));
        tempDir.toFile().delete();
    }
}