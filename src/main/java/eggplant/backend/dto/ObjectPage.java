package eggplant.backend.dto;

import java.util.List;

public class ObjectPage<T> {

    private List<T> items;

    private Integer numberOfItems;

    public ObjectPage(
            List<T> items,
            Integer numberOfItems
    ) {
        this.items = items;
        this.numberOfItems = numberOfItems;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
}
