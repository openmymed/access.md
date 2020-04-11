/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.kisoft.covid19.domain.core.enums.ICPCType;

/**
 *
 * @author tareq
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ICPCEntry {
    private String code;
    private String title;
    private ICPCType type;
}