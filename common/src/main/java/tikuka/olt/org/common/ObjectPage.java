package tikuka.olt.org.common;

import org.springframework.data.domain.Page;

import java.util.List;

public class ObjectPage<T> {

    private List<T> items;
    private Long totalElements;
    private Integer totalPages;

    public ObjectPage(Page<T> page){
        this.items = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }

    public List<T> getItems() {
        return items;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}
