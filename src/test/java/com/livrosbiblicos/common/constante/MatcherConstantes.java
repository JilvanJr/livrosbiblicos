package com.livrosbiblicos.common.constante;

import lombok.NoArgsConstructor;
import org.springframework.test.web.servlet.ResultMatcher;

import static com.livrosbiblicos.common.enums.ParametroEnum.TAG_STATUS_CODE;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@NoArgsConstructor
public class MatcherConstantes {

    public static final ResultMatcher MATCHER_STATUS_IS_OK = status().isOk();
    public static final ResultMatcher MATCHER_STATUS_IS_ACCEPTED = status().isAccepted();
    public static final ResultMatcher MATCHER_STATUS_IS_NO_CONTENT = status().isNoContent();
    public static final ResultMatcher MATCHER_STATUS_IS_BAD_REQUEST = status().isBadRequest();
    public static final ResultMatcher MATCHER_STATUS_IS_NOT_FOUND = status().isNotFound();
    public static final ResultMatcher MATCHER_STATUS_IS_UNPROCESSABLE_ENTITY = status().isUnprocessableEntity();
    public static final ResultMatcher MATCHER_STATUS_IS_BAD_GATEWAY = status().isBadGateway();
    public static final ResultMatcher MATCHER_TAG_STATUS_CODE_IS_BAD_REQUEST = jsonPath(TAG_STATUS_CODE.getParametro(), is(BAD_REQUEST.value()));
    public static final ResultMatcher MATCHER_TAG_STATUS_CODE_IS_NOT_FOUND = jsonPath(TAG_STATUS_CODE.getParametro(), is(NOT_FOUND.value()));
    public static final ResultMatcher MATCHER_TAG_STATUS_CODE_IS_UNPROCESSABLE_ENTITY = jsonPath(TAG_STATUS_CODE.getParametro(), is(UNPROCESSABLE_ENTITY.value()));
    public static final ResultMatcher MATCHER_TAG_STATUS_CODE_IS_BAD_GATEWAY = jsonPath(TAG_STATUS_CODE.getParametro(), is(BAD_GATEWAY.value()));
}