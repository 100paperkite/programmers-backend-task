package project.handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import project.json.JsonMapper;

import java.io.IOException;
import java.io.OutputStream;

public abstract class JsonHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Set headers
        Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", "application/json");

        String response = "";

        // Get response
        try {
            response = JsonMapper.toJson(this.response());
            exchange.sendResponseHeaders(200, response.length());
        }
        catch (Exception e){
            exchange.sendResponseHeaders(500, response.length());
        }
        finally {
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

        }

    }


    public abstract <T> T response() throws Exception;
}
