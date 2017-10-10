package server.logic.tables;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import server.logic.model.Item;
import utilities.Trace;

public class ItemTable {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<Item> itemList=new ArrayList<Item>();
    private static class ItemListHolder {
        private static final ItemTable INSTANCE = new ItemTable();
    }
    private ItemTable(){
    	//set up the default list with some instances
    	String[] ISBNList=new String[]{"9781442668584","9781442616899","9781442667181","9781611687910"};
    	String[] cnList=new String[]{"1","1","1","1"};
    	for(int i=0;i<ISBNList.length;i++){
			Item deitem=new Item(i,ISBNList[i],cnList[i]);
			itemList.add(deitem);
		}
    	logger.info(String.format("Operation:Initialize ItemTable;ItemTable: %s", itemList));
    };
    public static final ItemTable getInstance() {
        return ItemListHolder.INSTANCE;
    }
	public Object createitem(String string) {
		boolean result=true;
		result=TitleTable.getInstance().lookup(string);
		if(result){
		int flag=0;
		for(int i=0;i<itemList.size();i++){
			if(itemList.get(i).getISBN().equalsIgnoreCase(string)){
				flag=flag+1;
			}else{
				flag=flag+0;
			}
		}
		Item newitem=new Item(itemList.size(),string,String.valueOf(flag+1));
		itemList.add(newitem);
		logger.info(String.format("Operation:Create New Item;Item Info:[%s,%s];State:Success", string,String.valueOf(flag+1)));
		}else{
			result=false;
			logger.info(String.format("Operation:Create New Item;Item Info:[%s,%s];State:Fail;Reason:No such ISBN existed.", string,"N/A"));
		}
		return result;
	}
	public boolean lookup(String string, String string2) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<itemList.size();i++){
			String ISBN=(itemList.get(i)).getISBN();
			String copynumber=(itemList.get(i)).getCopynumber();
			if(ISBN.equalsIgnoreCase(string) && copynumber.equalsIgnoreCase(string2)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			result=false;
		}
		return result;
	}
	public Object delete(String string, String string2) {
		//Since the itemid and copynumber in is automatically assigned to the item,upon its creation.
		//Each item has a unique itemid and copynumber.Even it is deleted,they can not be assigned to other item.
		//To maintain the correctness of the data,here instead delete index from the List.
		//I choose to remove the item's information instead the whole index.
		String result="";
		int index=0;
		int flag=0;
		for(int i=0;i<itemList.size();i++){
			String ISBN=(itemList.get(i)).getISBN();
			String copynumber=(itemList.get(i)).getCopynumber();
			if(ISBN.equalsIgnoreCase(string) && copynumber.equalsIgnoreCase(string2)){
				index=i;
				flag=flag+1;
			}else{
				flag=flag+0;
			}
		}
		if(flag!=0){
			boolean loan=LoanTable.getInstance().checkLoan(string,string2);
			if(loan){
			itemList.get(index).setCopynumber("N/A");
			result="success";
			logger.info(String.format("Operation:Delete Item;Item Info:[%s,%s];State:Success", string,"N/A"));
			}else{
				result="Active Loan Exists";
				logger.info(String.format("Operation:Delete Item;Item Info:[%s,%s];State:Fail;Reason:The item is currently on loan.", string,string2));
			}
		}else{
			result="The Item Does Not Exist";
			logger.info(String.format("Operation:Delete Item;Item Info:[%s,%s];State:Fail;Reason:The Item Does Not Exist.", string,string2));
		}
		return result;
	}
	public void deleteAll(String string) {
		for(int i=0;i<itemList.size();i++){
			if(string.equalsIgnoreCase(itemList.get(i).getISBN())){
				itemList.get(i).setISBN("N/A");
				itemList.get(i).setCopynumber("N/A");
				logger.info(String.format("Operation:Delete Item Due to Title Deletion;ISBN Info:[%s];State:Success", string));
			}
		}
		
	}
	public List<Item> getItemTable() {
		return itemList;
	}
}