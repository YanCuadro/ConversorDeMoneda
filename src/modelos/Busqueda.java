package modelos;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.UnresolvedAddressException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Busqueda {

    public Moneda conversion(String base_code, String target_code, double conversion_rate) throws ConnectException {

        // Instancia y configuración para formatear el double conversion_rate
        // para que no de error cuando el doble tenga muchos números enteros
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat fd = new DecimalFormat("#.#############", symbols);

        // Se crea la instancia de la URI que contiene la dirección de la API
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/d6cfee1ff659dba0f4376102/pair/"+
                base_code+"/"+target_code+"/"+fd.format(conversion_rate));

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // Retornando y convirtiendo el JSON a un objeto Moneda
            return new Gson().fromJson(response.body(), Moneda.class);

        } catch (ConnectException | UnresolvedAddressException e) {
            throw  new ConnectException("No se pudo completar la acción. Por favor revise su conexión a internet.");

        }catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
