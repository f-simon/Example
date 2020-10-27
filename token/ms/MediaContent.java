package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * MediaContent
 */

public class MediaContent {
  @JsonProperty("type")
  private String type;

  @JsonProperty("data")
  private String data;

  @JsonProperty("height")
  private String height;

  @JsonProperty("width")
  private String width;

  public MediaContent type(String type) {
    this.type = type;
    return this;
  }

  /**
   * What type of media this is. Specified as a MIME type, which will be one of the following supported types   * applicatoin/pdf (for images must be a vector PDF image) * image/png (includes alpha channel) * text/plain  * text/html  __Max Length:32__  
   * @return type
  */
  @NotNull


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public MediaContent data(String data) {
    this.data = data;
    return this;
  }

  /**
   * The data for this item of media. Base64-encoded data, given in the format as specified in ?type?. 
   * @return data
  */
 @NotNull


  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public MediaContent height(String height) {
    this.height = height;
    return this;
  }

  /**
   * For image assets, the height of this image. Specified in pixels.     __Max Length:6__  
   * @return height
  */

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  public MediaContent width(String width) {
    this.width = width;
    return this;
  }

  /**
   * For image assets, the width of this image. Specified in pixels.        __Max Length:6__  
   * @return width
  */

  public String getWidth() {
    return width;
  }

  public void setWidth(String width) {
    this.width = width;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MediaContent mediaContent = (MediaContent) o;
    return Objects.equals(this.type, mediaContent.type) &&
        Objects.equals(this.data, mediaContent.data) &&
        Objects.equals(this.height, mediaContent.height) &&
        Objects.equals(this.width, mediaContent.width);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, data, height, width);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MediaContent {\n");

    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    width: ").append(toIndentedString(width)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

