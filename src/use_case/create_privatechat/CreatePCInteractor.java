package use_case.create_privatechat;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class CreatePCInteractor implements CreatePCInputBoundary {
    final CreatePCOutputBoundary createPCPresenter;
    public CreatePCInteractor(CreatePCOutputBoundary createPCPresenter) {
        this.createPCPresenter = createPCPresenter;
    }
    @Override
    public void execute(CreatePCInputData createPCInputData) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(CreatePCInputData.getUrl());
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(CreatePCInputData.getSubject());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        CreatePCOutputData createPCOutputData = new CreatePCOutputData(CreatePCInputData.getUrl(),
                CreatePCInputData.getSubject());
        createPCPresenter.prepareSuccessView(createPCOutputData);
    }
}
