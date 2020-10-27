package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fdc.mtrg.network.token.ms.error.APIError;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

//import com.fdc.mtrg.network.token.ms.MediaContent;

/**
 * AssetResponseSchema
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetResponse extends APIError {
    @JsonProperty("mediaContents")
    @Valid
    private List<MediaContent> mediaContents = null;

    public List<MediaContent> getMediaContents() {
        return mediaContents;
    }

    public void setMediaContents(List<MediaContent> mediaContents) {
        this.mediaContents = mediaContents;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof AssetResponse)) return false;
        if (!super.equals(o)) return false;
        AssetResponse that = (AssetResponse) o;
        return Objects.equals(getMediaContents(), that.getMediaContents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMediaContents());
    }

    @Override
    public String toString() {
        return "AssetResponse{" +
                "mediaContents=" + mediaContents +
                '}';
    }
}

