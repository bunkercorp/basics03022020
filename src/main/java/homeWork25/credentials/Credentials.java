package homeWork25.credentials;

public enum Credentials {
    URL("https://jira.hillel.it/browse/"),
    EMAIL("oleksandrgolubishko@gmail.com"),
    USER_NAME("Александр Голубишко"),
    PASSWORD("Alex1991");

    private String credential;

    Credentials(String credential) {
        this.credential = credential;
    }

    public String getCredential(){
        return credential;
    }
}
