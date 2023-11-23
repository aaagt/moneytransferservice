package aaagt.moneytransferservice.backend.resolver;

import aaagt.moneytransferservice.backend.dto.TransferOperationRequestDto;
import aaagt.moneytransferservice.backend.exception.ErrorInputData;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransferResponseDtoResolver implements HandlerMethodArgumentResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferResponseDtoResolver.class);
    private final ObjectMapper objectMapper;

    public TransferResponseDtoResolver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public static boolean isNotNumeric(String str) {
        return !str.chars()
                .mapToObj(c -> (char) c)
                .allMatch(Character::isDigit);
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(TransferOperationRequestDto.class);
    }

    @Override
    @SuppressWarnings("")
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        List<ErrorInputData> errors = new ArrayList<>();

        final var body = getJsonPayload(webRequest);
        JsonNode json = objectMapper.readTree(body);
        LOGGER.info("amount value " + json.get("amount").get("value").intValue());

        final var cardFromNumber = json.get("cardFromNumber").textValue();
        LOGGER.debug("Value of cardFromNumber: " + cardFromNumber);
        if (isEmpty(cardFromNumber)) {
            errors.add(new ErrorInputData("cardFromNumber is empty", 1));
        } else if (isNotNumeric(cardFromNumber)) {
            errors.add(new ErrorInputData("cardFromNumber contains not ony numbers", 1));
        }

        final var cardFromValidTill = json.get("cardFromValidTill").textValue();
        LOGGER.debug("Value of cardFromValidTill: " + cardFromValidTill);
        if (isEmpty(cardFromValidTill)) {
            errors.add(new ErrorInputData("cardFromValidTill is empty", 1));
        }

        final var cardFromCVV = json.get("cardFromCVV").textValue();
        LOGGER.debug("Value of cardFromCVV: " + cardFromCVV);
        if (isEmpty(cardFromCVV)) {
            errors.add(new ErrorInputData("cardFromCVV is empty", 1));
        }

        final var cardToNumber = json.get("cardToNumber").textValue();
        LOGGER.debug("Value of cardToNumber: " + cardToNumber);
        if (isEmpty(cardToNumber)) {
            errors.add(new ErrorInputData("cardToNumber is empty", 1));
        } else if (isNotNumeric(cardToNumber)) {
            errors.add(new ErrorInputData("cardToNumber contains not ony numbers", 1));
        }

        final var amountJson = json.get("amount");
        final var value = amountJson.get("value").intValue();
        LOGGER.debug("Value of amount value: " + value);
        if (value <= 0) {
            errors.add(new ErrorInputData("amount value is not positive integer", 1));
        }

        final var currency = amountJson.get("currency").textValue();
        if (isEmpty(currency)) {
            errors.add(new ErrorInputData("currency is empty", 1));
        }

        // Сборка всех ошибок в одну согласно спецификации
        if (!errors.isEmpty()) {
            final var message = errors.stream()
                    .map(error -> error.getMessage())
                    .collect(Collectors.joining("; "));
            throw new ErrorInputData(message, 1);
        }

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
