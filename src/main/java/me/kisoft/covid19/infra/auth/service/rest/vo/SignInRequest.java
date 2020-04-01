/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.auth.service.rest.vo;

import lombok.Data;

/**
 *
 * @author tareq
 */
@Data
public class SignInRequest {

  private String username;
  private String password;
}
