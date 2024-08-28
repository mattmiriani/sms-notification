package com.example.sms.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Data
public class PageableDTO {
    private Integer pageNumber = 0;

    private Integer pageSize = 20;

    private String sortField;

    private List<OrderDTO> sortFields = new ArrayList<>();

    private Sort.Direction sortDir = Sort.Direction.ASC;

    public Optional<Integer> getPageNumber() {
        return Optional.ofNullable(pageNumber);
    }

    public Optional<Integer> getPageSize() {
        return Optional.ofNullable(pageSize);
    }

    public Optional<String> getSortField() {
        return Optional.ofNullable(sortField);
    }

    public Optional<Sort.Direction> getSortDir() {
        return Optional.ofNullable(this.sortDir);
    }

    public void setSortDir(String sortDir) {
        this.sortDir = isNull(sortDir) ? null : Sort.Direction.fromString(sortDir);
    }

    public Sort getSort() {
        if (getSortDir().isPresent() && getSortField().isPresent()) {
            return Sort.by(getSortDir().get(), getSortField().get());
        }

        return Sort.unsorted();
    }

    public Sort getSortMultipleFields() {
        if (getSortDir().isPresent() && !getSortFields().isEmpty()) {
            return Sort.by(getSortFields().stream().map(OrderDTO::getOrder).toList());
        }

        return getSort();
    }

    public Pageable getPageable() {
        return PageRequest.of(pageNumber, pageSize, getSort());
    }

    public Pageable getPageableSortFields() {
        return PageRequest.of(pageNumber, pageSize, getSortMultipleFields());
    }
}
