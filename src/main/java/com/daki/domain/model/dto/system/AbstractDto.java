package com.daki.domain.model.dto.system;

import com.daki.domain.patterns.interfaces.IIdentifier;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractDto <T extends Number> implements IIdentifier<T>, Serializable {
}
