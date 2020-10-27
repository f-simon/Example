package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class TaskStatusRequest implements Serializable {

    @JsonProperty("responseHost")
    private String responseHost;

    @JsonProperty("requestId")
    private String requestId;

    @JsonProperty("tokenRequestorId")
    private String tokenRequestorId;

    @JsonProperty("taskId")
    private String taskId;

    public String getResponseHost() {
        return responseHost;
    }

    public void setResponseHost(String responseHost) {
        this.responseHost = responseHost;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTokenRequestorId() {
        return tokenRequestorId;
    }

    public void setTokenRequestorId(String tokenRequestorId) {
        this.tokenRequestorId = tokenRequestorId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "Task Status Request{" +
                "responseHost='" + responseHost + '\'' +
                ", requestId='" + requestId + '\'' +
                ", tokenRequestorId='" + tokenRequestorId + '\'' +
                ", taskId='" + taskId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskStatusRequest)) return false;
        TaskStatusRequest that = (TaskStatusRequest) o;
        return  Objects.equals(responseHost, that.responseHost) &&
                Objects.equals(requestId, that.requestId) &&
                Objects.equals(tokenRequestorId, that.tokenRequestorId) &&
                Objects.equals(taskId, that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseHost, requestId, tokenRequestorId, taskId);
    }
}
