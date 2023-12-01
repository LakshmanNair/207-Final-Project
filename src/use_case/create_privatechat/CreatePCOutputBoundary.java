package use_case.create_privatechat;

public interface CreatePCOutputBoundary {
    void prepareSuccessView(CreatePCOutputData createPCOutputData);
    void prepareFailView(String error);
}