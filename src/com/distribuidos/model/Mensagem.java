package com.distribuidos.model;
public class Mensagem {
    private String objectReference;
    private String methodID;
    private String arguments;
    private String requestID;

    public Mensagem(String objectReference, String methodID, String arguments, String requestID) {
        this.objectReference = objectReference;
        this.methodID = methodID;
        this.arguments = arguments;
        this.requestID = requestID;
    }

    public String getObjectReference() { return objectReference; }
    public String getMethodID() { return methodID; }
    public String getArguments() { return arguments; }
    public String getRequestID() { return requestID; }
}
