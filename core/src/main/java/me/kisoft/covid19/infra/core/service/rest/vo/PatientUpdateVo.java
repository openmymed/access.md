/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.core.service.rest.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author tareq
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientUpdateVo {
    
    private Long patientId;
    private PatientUpdateType type;
    private Long entityId;
    private String value;
}
