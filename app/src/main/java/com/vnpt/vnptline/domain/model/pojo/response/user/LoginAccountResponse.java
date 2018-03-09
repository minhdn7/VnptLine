package com.vnpt.vnptline.domain.model.pojo.response.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 1/23/2018.
 */

public class LoginAccountResponse {
  @SerializedName("name")
  @Expose
  @Getter @Setter
  private String name;

  @SerializedName("responseCode")
  @Expose
  @Getter @Setter
  private Integer responseCode;

  @SerializedName("responseMessage")
  @Expose
  @Getter @Setter
  private String responseMessage;

  @SerializedName("roles")
  @Expose
  @Getter @Setter
  private List<String> roles = null;

  @SerializedName("tokenId")
  @Expose
  @Getter @Setter
  private String tokenId;

  @SerializedName("userId")
  @Expose
  @Getter @Setter
  private Integer userId;

  @SerializedName("username")
  @Expose
  @Getter @Setter
  private String username;
}
