import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryAllocator {
	
	/*
	 * function that returns the cheapest shipment
	 */ 
	public static List<Map> getcheapestShipment(Map<String, Integer> ordersList, List<Warehouse> warehouseList) 
	{	
		
		List<Map> result = new ArrayList<>(); 
		Map<String, HashMap<String, Integer>> map = new HashMap<String, HashMap<String, Integer>>();  //map for storing warehouseList data
		int warehouseSize=warehouseList.size();  
		
		
		//returns an empty list if no warehouse present
		if(warehouseSize==0 || ordersList.size()==0)
		{			
			return result;
		}
		
		
		//checking ordersList items
		for (String item : ordersList.keySet())
		{			
			Integer itemCount = ordersList.get(item);	
			if(itemCount<=0)
			{	
				return result;
			}
				//checking warehouseList items for ordersList items in warehouse
				for (int i = 0; i < warehouseSize; i++) 
				{	
					Warehouse warehouse = warehouseList.get(i);
					
					//no items in warehouseList inventory 
					if(warehouse.getInventory().size()==0)
					{
						continue;
					}
					String warehouseName = warehouse.getWarehouseName();
					Integer itemQty = warehouse.getItemQuantity(item);
					
					
					//item count
					if (itemQty <= 0) 
					{
						continue;
					}
					
					//if desired count is reached
					if(itemCount<=0)
					{
						break;
					}
					
					//desired count is greater than warehouseList count in warehouse
					else if (itemCount > itemQty) 
					{
							itemCount = itemCount - itemQty;							
							HashMap<String, Integer> temp = map.getOrDefault(warehouseName, new HashMap<>());
							temp.put(item, itemQty);
							map.put(warehouseName, temp);
							
					} 
					//warehouseList count is more or equal 
					else 
					{	
							itemQty = itemCount == itemQty ? itemQty : itemCount;
							itemCount = 0;
							HashMap<String, Integer> temp = map.getOrDefault(warehouseName, new HashMap<>());
							temp.put(item, itemQty);
							map.put(warehouseName, temp);							
							break;
					}
				}
			
			//if ordersList is not fulfilled then return empty list
			if (itemCount != 0) 
			{
				result.clear();
				return result;
			} 
			
		}
		
		
		//if all items are satisfied
		for(String item : map.keySet())
		{
			HashMap<String, HashMap<String, Integer>> shipmentDetails = new HashMap<String, HashMap<String, Integer>>();
			shipmentDetails.put(item, map.get(item));
			result.add(shipmentDetails);			
		}
		
		//return cheapest Shipment
		return result;		
		
	}
	public static void main(String[] args) {
		Warehouse owd = new Warehouse("owd");
		Warehouse dm = new Warehouse("dm");
		owd.setInventory("apple", 2);
		dm.setInventory("apple", 5);
		dm.setInventory("orange", 5);
		List<Warehouse> warehouseList = new ArrayList<>();
		warehouseList.add(owd);	
		warehouseList.add(dm);	
		Map<String, Integer> ordersList = new HashMap<>();
		//adding values for ordersList
		ordersList.put("apple",  4);		
		ordersList.put("orange",  5);	
		List<Map> res = getcheapestShipment(ordersList,warehouseList);
		System.out.println(res);
	}
}
