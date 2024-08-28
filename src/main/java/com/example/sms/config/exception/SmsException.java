package com.example.sms.config.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Map;

import static org.springframework.core.NestedExceptionUtils.buildMessage;

public class SmsException extends NestedRuntimeException {
    private final int status;
    @Nullable
    private final String reason;

    public SmsException(HttpStatus status) {
        this(status, null);
    }

    public SmsException(HttpStatus status, @Nullable String reason) {
        super("");
        Assert.notNull(status, "HttpStatus is required");
        this.status = status.value();
        this.reason = reason;
    }

    public SmsException(HttpStatus status, @Nullable String reason, @Nullable Throwable cause) {
        super(null, cause);
        Assert.notNull(status, "HttpStatus is required");
        this.status = status.value();
        this.reason = reason;
    }

    public SmsException(int rawStatusCode, @Nullable String reason, @Nullable Throwable cause) {
        super(null, cause);
        this.status = rawStatusCode;
        this.reason = reason;
    }

    public HttpStatus getStatus() {
        return HttpStatus.valueOf(this.status);
    }

    public int getRawStatusCode() {
        return this.status;
    }

    public HttpHeaders getResponseHeaders() {
        Map<String, String> headers = this.getHeaders();
        if (headers.isEmpty()) {
            return HttpHeaders.EMPTY;
        } else {
            HttpHeaders result = new HttpHeaders();
            headers.forEach(result::add);
            return result;
        }
    }

    @Deprecated
    public Map<String, String> getHeaders() {
        return Collections.emptyMap();
    }

    @Nullable
    public String getReason() {
        return this.reason;
    }

    @Override
    public String getMessage() {
        HttpStatus code = HttpStatus.resolve(this.status);
        String msg = (code != null ? code : this.status) + (this.reason != null ? " \"" + this.reason + "\"" : "");
        return buildMessage(msg, this.getCause());
    }
}
