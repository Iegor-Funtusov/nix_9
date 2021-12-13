package ua.com.alevel;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StartWebMain {

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(9000), 0);

            HttpContext context = server.createContext("/", new CustomHttpHandler());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class CustomHttpHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            StringBuilder builder = new StringBuilder();

            builder.append("<!DOCTYPE html>");
            builder.append("<html lang=\"en\">");
            builder.append("<head>");
            builder.append("<meta charset=\"utf-8\">");
            builder.append("<title>Nix</title>");
            builder.append("</head>");
            builder.append("<body>");
            builder.append("<h1>URI: ").append(exchange.getRequestURI()).append("</h1>");


            exchange.setAttribute("attr", new Date());

            StringBuilder html = new StringBuilder();
            try(FileReader fileReader = new FileReader("index.html");
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {

                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();
                    if (line.startsWith("<custom")) {
                        Headers headers = exchange.getRequestHeaders();
                        html.append("<table>");
                        for (String header : headers.keySet()) {
                            html.append("<tr>");
                            html.append("<td>");
                            html.append(header);
                            html.append("</td>");
                            html.append("<td>");
                            html.append(headers.getFirst(header));
                            html.append("</td>");
                            html.append("</tr>");
                        }
                        html.append("<tr>");
                        html.append("<td>");
                        html.append("attr");
                        html.append("</td>");
                        html.append("<td>");
                        html.append(exchange.getAttribute("attr"));
                        html.append("</td>");
                        html.append("</tr>");
                        html.append("</table>");
                        continue;
                    }
                    if (line.startsWith("</custom")) {
                        continue;
                    }
                    html.append(line);
                    System.out.println("line = " + line);

                }
            } catch (Exception e) {
                System.out.println("e = " + e.getMessage());
            }

            Headers headers = exchange.getRequestHeaders();
            builder.append("<table>");
            for (String header : headers.keySet()) {
                builder.append("<tr>");
                builder.append("<td>");
                builder.append(header);
                builder.append("</td>");
                builder.append("<td>");
                builder.append(headers.getFirst(header));
                builder.append("</td>");
                builder.append("</tr>");
            }
            builder.append("</table>");

            builder.append("</body>");
            builder.append("</html>");

            String[] queryParam = null;
            if (exchange.getRequestURI().toString().contains("?")) {
                queryParam = exchange.getRequestURI().getQuery().split("&");
                Map<String, String> map = Stream.of(queryParam)
                        .map(param -> param.split("="))
                        .collect(Collectors.toMap(arg -> arg[0], arg -> arg[1]));
                map.forEach((k, v) -> {
                    System.out.println("k = " + k);
                    System.out.println("v = " + v);
                });
            }

//            byte[] bytes = builder.toString().getBytes();
            byte[] bytes = html.toString().getBytes();
            exchange.sendResponseHeaders(200, bytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }
    }
}
