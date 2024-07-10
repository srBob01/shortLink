package ru.arsentiev.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;


@Getter
@AllArgsConstructor
public class PageResponse<T> {
    List<T> content;
    MetaData metaData;

    public static <T> PageResponse<T> of(Page<T> page) {
        var metaData = new MetaData(page.getNumber(), page.getSize(), page.getTotalElements());
        return new PageResponse<>(page.getContent(), metaData);
    }

    @AllArgsConstructor
    public static class MetaData {
        int page;
        int size;
        long totalElements;
    }

}
