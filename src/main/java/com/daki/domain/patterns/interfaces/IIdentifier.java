package com.daki.domain.patterns.interfaces;

import java.io.Serializable;

public interface IIdentifier <T extends Number> extends Serializable {

    T getId();

}
