package com.j2hb.cException;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
@Builder
public class ErrorDetails {

    private final LocalDateTime timestamp;
    private final String ErrorCount;
    private final List<String> message;
    private final String details;

}
