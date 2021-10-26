
package com.daki.persistence.pattern;

import com.daki.domain.util.BeanUtil;

/**
 *
 * @author lucas
 */
public class CDIProviderFactory {
    
    private static final String CDIPROVIDERCLASSNAME = "com.smartbr.persistence.CDIUtil";
    
    public static CDIProvider getCDIProvider() {
        return BeanUtil.createInstance(CDIPROVIDERCLASSNAME, Thread.currentThread().getContextClassLoader());
    }
}
