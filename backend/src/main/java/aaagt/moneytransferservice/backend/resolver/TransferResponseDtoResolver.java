package aaagt.moneytransferservice.backend.resolver;

import aaagt.moneytransferservice.backend.dto.TransferOperationRequestDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TransferResponseDtoResolver implements HandlerMethodArgumentResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferResponseDtoResolver.class);
    private final ObjectMapper objectMapper;

    public TransferResponseDtoResolver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(TransferOperationRequestDto.class);
    }

    @Override
    @SuppressWarnings("")
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final var body = getJsonPayload(webRequest);
        JsonNode json = objectMapper.readTree(body);
        LOGGER.info("cardFromNumber " + json.get("cardFromNumber").textValue());
        LOGGER.info("amount value " + json.get("amount").get("value").intValue());

        return objectMapper.treeToValue(json, parameter.getParameterType());
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private String getJsonPayload(NativeWebRequest nativeWebRequest) throws IOException {
        HttpServletRequest httpServletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        return StreamUtils.copyToString(httpServletRequest.getInputStream(), StandardCharsets.UTF_8);
    }

}
