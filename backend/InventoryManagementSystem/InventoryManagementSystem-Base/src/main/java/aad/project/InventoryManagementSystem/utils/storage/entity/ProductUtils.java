package aad.project.InventoryManagementSystem.utils.storage.entity;

import aad.project.InventoryManagementSystem.storage.entity.Product;
import com.datastax.oss.driver.api.core.cql.Row;

public class ProductUtils {
    public static void mapToProduct(Product product, Row row) {
        product.setProductId(row.getString("productId"));
        product.setName(row.getString("name"));
        product.setDescription(row.getString("description"));
        product.setPrice(row.getDouble("price"));
        product.setQuantity(row.getInt("quantity"));
    }
}
