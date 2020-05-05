//import javax.net.ssl.HttpsURLConnection;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.List;
//import java.util.Map;
//
//public class HTTP {
//
//    enum HTTPMethod {
//        GET(false),
//        POST(true);
//        public final boolean acceptsPayload;
//
//        private HTTPMethod(boolean ap) {
//            this.acceptsPayload = ap;
//        }
//    }
//
//    enum ContentType {
//        PLAIN_TEXT("text/plain"),
//        JSON("application/json");
//        public final String headerValue;
//
//        private ContentType(String hv) {
//            this.headerValue = hv;
//        }
//
//    }
//
//    static class HTTPResponse {
//        public final int code;
//        public final String body;
//        private final Map<String, List<String>> headers;
//
//        public HTTPResponse(int rc, String rb, Map<String, List<String>> hs) {
//            this.code = rc;
//            this.body = rb;
//            this.headers = hs;
//        }
//
//        @Override
//        public String toString() {
//            return String.format("Code:%d\nBody:\n%s\n-----", code, body);
//        }
//    }
//
//    public static HTTPResponse send(String url, HTTPMethod method, ContentType ct, String body) throws IOException {
//        final String username = Creds.uname; //System.getProperty("hillel username");
//        final String pwd = Creds.pwd; //System.getProperty("hillel pasword");
//        final  String creds = String.format("%s:%s",username,pwd);
//
//        final HttpsURLConnection httpCon = (HttpsURLConnection) new URL(url).openConnection();
//        httpCon.setDoOutput(method.acceptsPayload);
//        final String bodyFinal = body == null ? "" : body;
//        httpCon.setRequestMethod(method.name());
//        httpCon.setRequestProperty("Authorization", String.format("Basic %s", new Base64().encodeToString(creds.getBytes)));
//        if (method.acceptsPayload) {
//            httpCon.setRequestProperty("Content-Type", ct.headerValue);
//            httpCon.setRequestProperty("Content-Length", "" + bodyFinal.length());
//            final OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
//            out.write(bodyFinal);
//            out.close();
//        }
//
//        final int rescode = httpCon.getResponseCode();
//
//        ArrayList<String> result = new ArrayList<String>();
//        BufferedReader bd;
//
//        try {
//            bd = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
//        } catch (Throwable t) {
//            bd = new BufferedReader(new InputStreamReader(httpCon.getErrorStream()));
//        }
//        String line;
//        while ()
//
//}}
