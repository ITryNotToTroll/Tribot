package scripts.api.inventory;

public interface InventoryListener {
    public void inventoryItemAdded(int id, int count);
    public void inventoryItemRemoved(int id, int count);
}
