import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class InventoryAllocatorTest {
	private static Map<String, Integer> ordersList;  
	private static List<Map> testData; 

	public InventoryAllocatorTest()
	{
		ordersList = new HashMap<>();
		testData = new ArrayList<>();
	}
	

	@Test
	public  void testWithOneItem() 
	{
		//putting warehouse data
		Warehouse owd = new Warehouse("owd");
		owd.setInventory("apple", 1);
		List<Warehouse> warehouseList = new ArrayList<>();
		warehouseList.add(owd);		
		
		//putting values for order
		ordersList.put("apple",  1);		
			
		//dummy result 
		Map<String, HashMap<String, Integer>> outputMap = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> innerMap=new HashMap<>();
		innerMap.put("apple", 1);
		outputMap.put("owd", innerMap);
		testData.add(outputMap);
		assertEquals(testData,InventoryAllocator.getcheapestShipment(ordersList, warehouseList));
	}
	
	@Test
	public  void testWithMultipeItems() 
	{
		//putting warehouse data
		Warehouse owd = new Warehouse("owd");
		owd.setInventory("apple", 1);
		owd.setInventory("orange", 1);
		List<Warehouse> warehouseList = new ArrayList<>();
		warehouseList.add(owd);		
		
		//putting values for order
		ordersList.put("apple",  1);		
		ordersList.put("orange",  1);	
		//dummy result 
		Map<String, HashMap<String, Integer>> outputMap = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> innerMap=new HashMap<>();
		innerMap.put("apple", 1);
		innerMap.put("orange", 1);
		outputMap.put("owd", innerMap);
		testData.add(outputMap);
		assertEquals(testData,InventoryAllocator.getcheapestShipment(ordersList, warehouseList));
	}
	
	@Test
	public  void testWithoutEnoughItems() 
	{
		//putting warehouse data
		Warehouse owd = new Warehouse("owd");
		owd.setInventory("apple", 1);
		List<Warehouse> warehouseList = new ArrayList<>();
		warehouseList.add(owd);		
		
		//putting values for order
		ordersList.put("apple",  1);		
		ordersList.put("orange",  1);
		assertEquals(testData,InventoryAllocator.getcheapestShipment(ordersList, warehouseList));
	}
	
	//Test for Shipment which don't have enough items from multiple warehouse
	@Test
	public  void test1() 
	{
		//putting warehouse data
		Warehouse owd = new Warehouse("owd");
		Warehouse dm = new Warehouse("dm");
		owd.setInventory("apple", 2);
		dm.setInventory("apple", 5);
		dm.setInventory("orange", 5);
		List<Warehouse> warehouseList = new ArrayList<>();
		warehouseList.add(owd);		
		warehouseList.add(dm);	
		//putting values for order
		ordersList.put("apple",  4);		
		ordersList.put("orange",  6);
		assertEquals(testData,InventoryAllocator.getcheapestShipment(ordersList, warehouseList));
	}
	
	//Test for enough items from multiple warehouse
		@Test
		public  void test2() 
		{
			//putting warehouse data
			Warehouse owd = new Warehouse("owd");
			Warehouse dm = new Warehouse("dm");
			owd.setInventory("apple", 2);
			dm.setInventory("apple", 5);
			dm.setInventory("orange", 5);
			List<Warehouse> warehouseList = new ArrayList<>();
			warehouseList.add(owd);		
			warehouseList.add(dm);	
			//putting values for order
			ordersList.put("apple",  4);		
			ordersList.put("orange",  5);
			Map<String, HashMap<String, Integer>> outputMap1 = new HashMap<String, HashMap<String, Integer>>();
			HashMap<String, Integer> innerMap1=new HashMap<>();
			innerMap1.put("orange", 5);
			innerMap1.put("apple", 2);
			outputMap1.put("dm", innerMap1);
			testData.add(outputMap1);
			Map<String, HashMap<String, Integer>> outputMap = new HashMap<String, HashMap<String, Integer>>();
			HashMap<String, Integer> innerMap=new HashMap<>();
			innerMap.put("apple", 2);
			outputMap.put("owd", innerMap);
			testData.add(outputMap);
			assertEquals(testData,InventoryAllocator.getcheapestShipment(ordersList, warehouseList));
		}
		
		// Test for empty order
		@Test
		public  void test3() 
		{
			//putting warehouse data
			Warehouse owd = new Warehouse("owd");
			Warehouse dm = new Warehouse("dm");
			owd.setInventory("apple", 2);
			dm.setInventory("apple", 5);
			dm.setInventory("orange", 5);
			List<Warehouse> warehouseList = new ArrayList<>();
			warehouseList.add(owd);		
			warehouseList.add(dm);	
			assertEquals(testData,InventoryAllocator.getcheapestShipment(ordersList, warehouseList));
		}

}
