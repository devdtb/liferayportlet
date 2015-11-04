package dtb.sample.service;

import java.io.Serializable;

import org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings("serial")
public class Product implements Serializable, Cloneable {

    private Long id;

    private String productId = "";
    private String productName = "";
    private String category = "";
    private String size = "";
    private String description = "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public Product clone() throws CloneNotSupportedException {
        try {
            return (Product) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id 
        		+ ", productId=" + productId
        		+ ", productName=" + productName
                + ", category=" + category
                + ", size=" + size
                + ", description=" + description + '}';
    }

}

