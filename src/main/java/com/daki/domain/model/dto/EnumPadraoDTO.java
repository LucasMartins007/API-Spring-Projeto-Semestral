package com.daki.domain.model.dto;

import com.daki.domain.model.enums.interfaces.IEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnumPadraoDTO {

    private Object key;

    private Object name;

    private String value;

    public EnumPadraoDTO(IEnum<?> enumPadrao) {
        this.key = enumPadrao.getName();
        this.name = enumPadrao.getKey();
        this.value = enumPadrao.getValue();
    }
}
