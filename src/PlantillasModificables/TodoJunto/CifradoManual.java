package PlantillasModificables.TodoJunto;

import java.io.*;
import java.util.Base64;

public class CifradoManual {

    // 🔹 VARIABLES MODIFICABLES 🔹
    static int desplazamientoCesar = 3; // Modificar para cambiar cifrado César
    static char claveXOR = 'K'; // Modificar para cambiar clave XOR
    static String alfabetoOriginal = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static String alfabetoCifrado   = "QWERTYUIOPLKJHGFDSAZXCVBNMmnbvcxzlkjhgfdsapoiuytrewq";

    // 🔹 CIFRADO DE TEXTO 🔹
    public static String cifrarCesar(String texto) { return cifrarCesar(texto, desplazamientoCesar); }
    public static String descifrarCesar(String texto) { return descifrarCesar(texto, desplazamientoCesar); }
    public static String cifrarROT13(String texto) { return cifrarCesar(texto, 13); }
    public static String cifrarXOR(String texto) { return cifrarXOR(texto, claveXOR); }
    public static String descifrarXOR(String texto) { return descifrarXOR(texto, claveXOR); }
    public static String cifrarSustitucion(String texto) { return cifrarSustitucion(texto, alfabetoOriginal, alfabetoCifrado); }
    public static String descifrarSustitucion(String texto) { return descifrarSustitucion(texto, alfabetoOriginal, alfabetoCifrado); }

    // 🔹 MÉTODOS INTERNOS (NO NECESITAS TOCAR) 🔹
    private static String cifrarCesar(String texto, int desplazamiento) {
        StringBuilder cifrado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                cifrado.append((char) ((c - base + desplazamiento) % 26 + base));
            } else {
                cifrado.append(c);
            }
        }
        return cifrado.toString();
    }

    private static String descifrarCesar(String texto, int desplazamiento) {
        return cifrarCesar(texto, 26 - desplazamiento);
    }

    private static String cifrarXOR(String texto, char clave) {
        StringBuilder cifrado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            cifrado.append((char) (c ^ clave));
        }
        return Base64.getEncoder().encodeToString(cifrado.toString().getBytes());
    }

    private static String descifrarXOR(String textoCifrado, char clave) {
        byte[] bytes = Base64.getDecoder().decode(textoCifrado);
        String texto = new String(bytes);
        return cifrarXOR(texto, clave);
    }

    private static String cifrarSustitucion(String texto, String original, String cifrado) {
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            int index = original.indexOf(c);
            resultado.append(index != -1 ? cifrado.charAt(index) : c);
        }
        return resultado.toString();
    }

    private static String descifrarSustitucion(String texto, String original, String cifrado) {
        return cifrarSustitucion(texto, cifrado, original);
    }

    // 🔹 CIFRADO Y DESCIFRADO DE ARCHIVOS 🔹
    public static void cifrarArchivoXOR(String rutaEntrada, String rutaSalida) throws IOException {
        FileInputStream fis = new FileInputStream(rutaEntrada);
        FileOutputStream fos = new FileOutputStream(rutaSalida);

        int byteLeido;
        while ((byteLeido = fis.read()) != -1) {
            fos.write(byteLeido ^ claveXOR); // Aplicar XOR byte por byte
        }

        fis.close();
        fos.close();
        System.out.println("Archivo cifrado guardado en: " + rutaSalida);
    }

    public static void descifrarArchivoXOR(String rutaEntrada, String rutaSalida) throws IOException {
        cifrarArchivoXOR(rutaEntrada, rutaSalida);
        System.out.println("Archivo descifrado guardado en: " + rutaSalida);
    }
}

/*
#####################################################################
🔹 EXPLICACIÓN DE CADA MÉTODO Y CUÁNDO USARLO 🔹
#####################################################################

1️⃣ **Cifrado César**
   - 🔹 **Cómo funciona:** Mueve cada letra un número fijo de posiciones en el alfabeto.
   - ✅ **Ventajas:** Fácil de implementar, útil para esconder mensajes de forma rápida.
   - ❌ **Desventajas:** Extremadamente fácil de romper con fuerza bruta o análisis de frecuencia.
   - 🏆 **Cuándo usarlo:** Si solo necesitas ocultar texto temporalmente sin seguridad real.

2️⃣ **Cifrado ROT13**
   - 🔹 **Cómo funciona:** Es una versión especial de César con desplazamiento de 13.
   - ✅ **Ventajas:** Reversible sin necesidad de clave (ROT13 de nuevo lo descifra).
   - ❌ **Desventajas:** No proporciona ninguna seguridad real.
   - 🏆 **Cuándo usarlo:** Para ocultar spoilers, correos electrónicos o texto de manera superficial.

3️⃣ **Cifrado XOR**
   - 🔹 **Cómo funciona:** Cada carácter se mezcla con una clave usando la operación XOR.
   - ✅ **Ventajas:** Muy rápido, difícil de descifrar sin la clave correcta.
   - ❌ **Desventajas:** Si la clave es corta, es vulnerable a ataques de repetición.
   - 🏆 **Cuándo usarlo:** Para cifrar archivos o datos simples si la clave es fuerte.

4️⃣ **Cifrado por Sustitución**
   - 🔹 **Cómo funciona:** Reemplaza cada letra con otra según un alfabeto personalizado.
   - ✅ **Ventajas:** Permite crear cifrados personalizados difíciles de reconocer.
   - ❌ **Desventajas:** Si el alfabeto es conocido, se puede descifrar fácilmente.
   - 🏆 **Cuándo usarlo:** Si quieres un cifrado simple pero más seguro que César.

5️⃣ **Cifrado de Archivos con XOR**
   - 🔹 **Cómo funciona:** XOR se aplica a cada byte del archivo, protegiendo su contenido.
   - ✅ **Ventajas:** Funciona con cualquier tipo de archivo sin alterar su estructura.
   - ❌ **Desventajas:** Necesita una clave fuerte para ser seguro.
   - 🏆 **Cuándo usarlo:** Para ocultar archivos sin alterar su formato original.

#####################################################################
📌 **¿Cuál elegir?**
✔ Si quieres algo simple ➝ **César o Sustitución**
✔ Si quieres esconder texto sin seguridad ➝ **ROT13**
✔ Si necesitas un cifrado básico pero seguro ➝ **XOR**
✔ Si quieres cifrar archivos sin perder datos ➝ **XOR para archivos**
#####################################################################
*/