
package com.distribuidos.model;

public class Envelope {
    private String objectReference;
    private String methodID;
    private Object arguments;
    private String requestID;

    public Envelope(String objectReference, String methodID, Object arguments, String requestID) {
        this.objectReference = objectReference;
        this.methodID = methodID;
        this.arguments = arguments;
        this.requestID = requestID;
    }

    public String getObjectReference() { 
        return objectReference; 
    }
    public String getMethodID() { 
        return methodID; 
    }
    
    public Object getArguments() {
        return arguments; 
    }
    
    public String getRequestID() { 
        return requestID; 
    }
}
