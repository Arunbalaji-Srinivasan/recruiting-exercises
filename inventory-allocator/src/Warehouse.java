
import java.util.HashMap;
import java.util.Map;

/*
 * warehouse POJO class for sample data
 */
public class Warehouse {

	private String warehouseName;
	private Map<String, Integer> inventory;

	public Warehouse(String warehouseName) 
	{

		this.warehouseName = warehouseName;
		this.inventory = new HashMap<>();
	}
	
	//returns warehouse name
	public String getWarehouseName() 
	{
		return warehouseName;
	}

	//returns item count in warehouse
	public int getItemQuantity(String itemName) 
	{
		if (this.inventory.containsKey(itemName)) 
		{
			return  this.inventory.get(itemName);
		} 
		else 
		{
			return 0;
		}
	}
	
	//set warehouse name
	public void setWarehouseName(String warehouseName) 
	{
		this.warehouseName = warehouseName;
	}

	//set inventory
	public void setInventory(String name, int quantity) 
	{
		this.inventory.put(name, quantity);
	}
	
	//get inventory for warehouse
	public Map<String, Integer> getInventory() 
	{
		return inventory;
	}

	@Override
	public String toString() 
	{
		return "WareHouse [warehouseName=" + warehouseName + ", inventory=" + inventory + "]";
	}

}
