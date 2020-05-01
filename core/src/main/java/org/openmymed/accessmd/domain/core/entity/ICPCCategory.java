/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.core.entity;

/**
 *
 * @author tareq
 */
public enum ICPCCategory {
    GENERAL_AND_UNSPECIFIED,
    BLOOD_AND_IMMUNITY,
    DIGESTIVE,
    EYE,
    EAR,
    CARDIOVASCULAR,
    MUSCULOSKELETAL,
    NEUROLOGICAL,
    PSYCHOLOGICAL,
    RESPIRATORY,
    SKIN,
    ENDOCRINE_METABOLIC_NUTRITIONAL,
    UROLOGICAL,
    PREGNANCY_CHILDBEARING_FAMILY_PLANNING,
    FEMALE_GENITAL,
    MALE_GENITAL,
    SOCIAL_PROBLEMS,
    PROCESS;

    public static ICPCCategory of(char c) {
        switch (c) {
            case 'A':
                return GENERAL_AND_UNSPECIFIED;
            case 'B':
                return BLOOD_AND_IMMUNITY;
            case 'C':
                throw new IllegalArgumentException("C is not a used category");
            case 'D':
                return DIGESTIVE;
            case 'F':
                return EYE;
            case 'H':
                return EAR;
            case 'K':
                return CARDIOVASCULAR;
            case 'L':
                return MUSCULOSKELETAL;
            case 'N':
                return NEUROLOGICAL;
            case 'P':
                return PSYCHOLOGICAL;
            case 'R':
                return RESPIRATORY;
            case 'S':
                return SKIN;
            case 'T':
                return ENDOCRINE_METABOLIC_NUTRITIONAL;
            case 'U':
                return UROLOGICAL;
            case 'W':
                return PREGNANCY_CHILDBEARING_FAMILY_PLANNING;
            case 'X':
                return FEMALE_GENITAL;
            case 'Y':
                return MALE_GENITAL;
            case 'Z':
                return SOCIAL_PROBLEMS;
            case '-':
                return PROCESS;
            default : 
                throw new IllegalArgumentException("Unknown Code Category :  " + c);
        }
    }

}
