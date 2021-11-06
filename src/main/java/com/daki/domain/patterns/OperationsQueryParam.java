package com.daki.domain.patterns;

import java.util.Arrays;
import java.util.List;

public interface OperationsQueryParam {

    String OFFSET = "offset";

    String LIMIT = "limit";

    String MATCHER = "matcher";

    String SORT = "sort";

    List<String> OPERATIONS = Arrays.asList(
            OperationsQueryParam.OFFSET,
            OperationsQueryParam.LIMIT,
            OperationsQueryParam.MATCHER,
            OperationsQueryParam.SORT);
}
