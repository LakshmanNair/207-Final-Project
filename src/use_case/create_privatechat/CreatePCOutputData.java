package use_case.create_privatechat;
import javax.jms.Destination;

public class CreatePCOutputData {
    private String url;
    private String subject;
    public CreatePCOutputData(String url, String subject) {
        this.url = url;
        this.subject = subject;
    }

    public String getUrl() {
        return url;
    }
    public String getSubject() {
        return subject;
    }
}
