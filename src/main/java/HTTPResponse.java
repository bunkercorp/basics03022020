public class HTTPTest {
    enum HTTPMethod {
        GET(false),
        POST(true);
        public final boolean acceptsPayload;

        private HTTPMethod(boolean ap) {
            this.acceptsPayload = ap;
        }
    }

    enum ContentType {
        PLAIN_TEXT("text/plain"),
        JSON("application/json");
        public final String headerValue;

        private ContentType(String hv) {
            this.headerValue = hv;
        }
    }

    
}

