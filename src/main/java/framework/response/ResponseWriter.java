package framework.response;

import java.io.PrintWriter;
import java.util.Map;

public class ResponseWriter {

    PrintWriter writer;
    Response response;

    public ResponseWriter(PrintWriter writer, Response response) {

        this.writer = writer;
        this.response = response;
    }

    public void writeResponse() {

        writeStatusLine(writer, response);
        writeHeaders(writer, response);
        writer.println();
        writer.println(response.getContents());
    }

    private void writeStatusLine(PrintWriter writer, Response response) {

        // TODO: change 'success' accordingly
        String format = String.format("HTTP/1.1 %s %s", response.getCode(), "Success");
        writer.println(format);
    }

    private void writeHeaders(PrintWriter writer, Response response) {

        for (Map.Entry<String, String> entry : response.getHeaders().entrySet()) {
            writer.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
