package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fdc.mtrg.network.token.ms.error.APIError;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskStatusResponse extends APIError {

    @JsonProperty("responseId")
    private String responseId;

    @JsonProperty("responseHost")
    private String responseHost;

    @JsonProperty("status")
    private String status;

    @Override
    public String getResponseId() {
        return responseId;
    }

    @Override
    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    @Override
    public String getResponseHost() {
        return responseHost;
    }

    @Override
    public void setResponseHost(String responseHost) {
        this.responseHost = responseHost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskStatusResponse{" +
                "responseId='" + responseId + '\'' +
                ", responseHost='" + responseHost + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskStatusResponse)) return false;
        if (!super.equals(o)) return false;
        TaskStatusResponse that = (TaskStatusResponse) o;
        return Objects.equals(getResponseId(), that.getResponseId()) &&
                Objects.equals(getResponseHost(), that.getResponseHost()) &&
                Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getResponseId(), getResponseHost(), getStatus());
    }
}
