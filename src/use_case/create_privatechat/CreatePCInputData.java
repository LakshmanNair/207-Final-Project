package use_case.create_privatechat;

public class CreatePCInputData {
    private static String url;
    private static String subject;
    public CreatePCInputData(String url, String subject) {
        this.url = url;
        this.subject = subject;
    }
    public static String getUrl() {
        return url;
    }
    public static String getSubject() {
        return subject;
    }
}
