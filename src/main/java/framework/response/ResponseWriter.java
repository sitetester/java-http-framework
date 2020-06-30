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

        writeStatusLine();
        writeHeaders();
        writer.println();
        writer.println(response.getContents());
    }

    private void writeStatusLine() {

        // TODO: change 'success' accordingly
        String format = String.format("HTTP/1.1 %s %s", response.getCode(), "Success");
        writer.println(format);
    }

    private void writeHeaders() {

        for (Map.Entry<String, String> entry : response.getHeaders().entrySet()) {
            writer.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
