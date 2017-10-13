package JUnit_testings;

import static org.junit.Assert.*;
import java.util.Date;

import org.junit.Test;

import server.logic.model.User;
import server.logic.tables.FeeTable;
import server.logic.tables.ItemTable;
import server.logic.tables.LoanTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class Test1 {
	
	final String  user ="lauren";
	final String  pass ="lauren";
	
	
	
	/*All things needed to be tested
	 * AddUser 
	 * 	Tests
	 * 	-Test
	 * 
	 * AddItem
	 * AddTitle
	 * FindItem
	 * FindUser
	 * BorrowLoanCopy
	 * CollectFine
	 * RemoveUser
	 * RemoveItem
	 * RemoveTitle
	 * RenewLoan
	 * ReturnLoanCopy
	 * MoniterSystem


*/
	
	
	@Test
	public void addUserTest(){
		/* In this test we are creating a user
		 * and testing to see if it exists
		 *
		 * by default the UserTable contains 5 elements
		 * therefore when the new User is added its index should be 5
		 */
		
		assertEquals("This should pass",true, UserTable.getInstance().createuser("maab", "123"));
		
		
	//	assertEquals("This should pass",true, UserTable.getInstance().lookup(4) );
		
		assertEquals("This should pass",false, UserTable.getInstance().createuser("zhibo@carleton.ca" ,"zhibo"));
		
		
		
	
		
	}
	
	
	@Test
	public void addTitleTest(){
		//
		
		assertEquals("This should pass",true,TitleTable.getInstance().createtitle("Generic Title", "Author"));
		assertEquals("This should pass",false, TitleTable.getInstance().createtitle("9781442668584" ,"By the grace of God"));
		
	}
	
	@Test
	public void addItemTest(){
		//Title must be created first before Item
		TitleTable.getInstance().createtitle("Title", "Author");
		assertEquals("This should pass",true,ItemTable.getInstance().createitem("Title"));
		
		assertEquals("This should pass",false, ItemTable.getInstance().createitem("RRR" ));
	}
	// These find tests are extra
	@Test
	public void findUser(){
		
		assertEquals("This should pass",true, UserTable.getInstance().lookup(3) );
		
	}
	
	@Test
	public void findTitle(){
		
		assertEquals("This should pass",true, TitleTable.getInstance().lookup("9781442668584") );
		
	}
	
	@Test
	public void testBorrowLoanCopy(){
		
		//Here i am creating 10 items and titles so 
		// so i can get the error "Max items reached
		
		TitleTable.getInstance().createtitle("BorrowTitle1", "Author");
		ItemTable.getInstance().createitem("BorrowTitle1");
		
		TitleTable.getInstance().createtitle("Justs", "Author");
		ItemTable.getInstance().createitem("Justs");
	

		TitleTable.getInstance().createtitle("BorrowTitle2", "Author");
		ItemTable.getInstance().createitem("BorrowTitle2");

		TitleTable.getInstance().createtitle("BorrowTitle3", "Author");
		ItemTable.getInstance().createitem("BorrowTitle3");
		
		TitleTable.getInstance().createtitle("BorrowTitle4", "Author");
		ItemTable.getInstance().createitem("BorrowTitle4");
		
		TitleTable.getInstance().createtitle("BorrowTitle5", "Author");
		ItemTable.getInstance().createitem("BorrowTitle5");

		TitleTable.getInstance().createtitle("BorrowTitle6", "Author");
		ItemTable.getInstance().createitem("BorrowTitle6");

		TitleTable.getInstance().createtitle("BorrowTitle7", "Author");
		ItemTable.getInstance().createitem("BorrowTitle7");

		TitleTable.getInstance().createtitle("BorrowTitle8", "Author");
		ItemTable.getInstance().createitem("BorrowTitle8");

		TitleTable.getInstance().createtitle("BorrowTitle9", "Author");
		ItemTable.getInstance().createitem("BorrowTitle9");
		
		TitleTable.getInstance().createtitle("BorrowTitle10", "Author");
		ItemTable.getInstance().createitem("BorrowTitle10");
		

		TitleTable.getInstance().createtitle("BorrowTitle11", "Author");
		ItemTable.getInstance().createitem("BorrowTitle11");

		TitleTable.getInstance().createtitle("BorrowTitle12", "Author");
		ItemTable.getInstance().createitem("BorrowTitle12");
		TitleTable.getInstance().createtitle("BorrowTitle13", "Author");
		ItemTable.getInstance().createitem("BorrowTitle13");
		TitleTable.getInstance().createtitle("BorrowTitle14", "Author");
		ItemTable.getInstance().createitem("BorrowTitle14");

		TitleTable.getInstance().createtitle("BorrowTitle15", "Author");
		ItemTable.getInstance().createitem("BorrowTitle15");

		LoanTable.getInstance().createloan(2, "BorrowTitle1", "1", new Date()) ;
		LoanTable.getInstance().createloan(2, "BorrowTitle2", "1", new Date()) ;
		LoanTable.getInstance().createloan(2, "BorrowTitle3", "1", new Date()) ;
		LoanTable.getInstance().createloan(2, "BorrowTitle4", "1", new Date()) ;
		LoanTable.getInstance().createloan(2, "BorrowTitle5", "1", new Date()) ;
		LoanTable.getInstance().createloan(2, "BorrowTitle6", "1", new Date()) ;
		LoanTable.getInstance().createloan(2, "BorrowTitle7", "1", new Date()) ;
		LoanTable.getInstance().createloan(2, "BorrowTitle8", "1", new Date()) ;
		LoanTable.getInstance().createloan(2, "BorrowTitle9", "1", new Date()) ;
		LoanTable.getInstance().createloan(2, "BorrowTitle10", "1", new Date()) ;
		LoanTable.getInstance().createloan(2, "BorrowTitle11", "1", new Date()) ;
		LoanTable.getInstance().createloan(2, "BorrowTitle12", "1", new Date()) ;
		
		LoanTable.getInstance().createloan(2, "BorrwTitle13", "1", new Date());

		
		assertEquals("This should pass","The Maximun Number of Items is Reached",LoanTable.getInstance().createloan(2, "BorrowTitle14", "1", new Date())) ;
		
	
		UserTable.getInstance().createuser("May", "May");
		TitleTable.getInstance().createtitle("May B", "Author");
		ItemTable.getInstance().createitem("May B");

		
		assertEquals("This should pass","Outstanding Fee Exists",LoanTable.getInstance().createloan(0, "9781442667181", "1", new Date() ));	

		LoanTable.getInstance().createloan(4, "9781442668584", "1",  new Date());
		assertEquals("This should pass","The Item is Not Available",LoanTable.getInstance().createloan(3, "9781442668584", "1", new Date() ));	
		
		assertEquals("This should pass","User Invalid",LoanTable.getInstance().createloan(900, "9781442616899", "1", new Date() ));	
		assertEquals("This should pass","success",LoanTable.getInstance().createloan(UserTable.getInstance().lookup("May"), "May B", "1", new Date() ));
		assertEquals("This should pass","ISBN Invalid",LoanTable.getInstance().createloan(1, "9781442616892", "1", new Date() ));
		assertEquals("This should pass","Copynumber Invalid",LoanTable.getInstance().createloan(1, "9781442616899", "2", new Date() ));
		
	}
	
	
	@Test
	public void testReturnItem(){
		TitleTable.getInstance().createtitle("ReturnItem", "Author");
		ItemTable.getInstance().createitem("ReturnItem");
	
		LoanTable.getInstance().createloan(3, "ReturnItem", "1", new Date() );
		
		assertEquals("This should pass","success",LoanTable.getInstance().returnItem(3, "ReturnItem", "1", new Date()));
		assertEquals("This should pass","The Loan Does Not Exist",LoanTable.getInstance().returnItem(2, "Title99", "1", new Date()));
		
		
	}
	
	@Test
	public void testCollectFine(){
		
		//Im going to be setting a fine to 
		//a new user so i can get the error message
		// that they must return their book first
		// before paying the fine
		
		TitleTable.getInstance().createtitle("Fine", "Author");
		ItemTable.getInstance().createitem("Fine");
		
		UserTable.getInstance().createuser("Fini", "123");
		
		LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Fini"), "Fine", "1", new Date());
		FeeTable.getInstance().applyfee(1, 1);
		FeeTable.getInstance().getFeeTable().get(1).setFee(5);
		assertEquals("success","Borrowing Items Exist", FeeTable.getInstance().payfine(UserTable.getInstance().lookup("Fini")) );
	
		LoanTable.getInstance().returnItem(0, "9781442668584", "1", new Date()) ;
		FeeTable.getInstance().applyfee(1, 1);
		FeeTable.getInstance().getFeeTable().get(1).setFee(5);
		System.out.print("I AMMM FEE");
		System.out.print(FeeTable.getInstance().getFeeTable().toString());
		
		if ( FeeTable.getInstance().getFeeTable().get(0).getFee() == 0 ) FeeTable.getInstance().getFeeTable().get(0).setFee(5);
		
		assertEquals("sucess","success", FeeTable.getInstance().payfine(0) );
		
	}
	
	@Test
	public void testRenewLoan(){

		
		// 
		TitleTable.getInstance().createtitle("Title1", "Author");
		ItemTable.getInstance().createitem("Title1");
		

		TitleTable.getInstance().createtitle("Title2", "Author");
		ItemTable.getInstance().createitem("Title2");

		TitleTable.getInstance().createtitle("Title3", "Author");
		ItemTable.getInstance().createitem("Title3");
		
		LoanTable.getInstance().createloan(1, "Title1", "1", new Date()) ;
		LoanTable.getInstance().createloan(1, "Title2", "1", new Date()) ;
		LoanTable.getInstance().createloan(1, "Title3", "1", new Date()) ;
		

		
		
		
		
		TitleTable.getInstance().createtitle("Title7", "Author");
		ItemTable.getInstance().createitem("Title7");

		LoanTable.getInstance().createloan(2, "Title7", "1", new Date()) ;
		
		UserTable.getInstance().createuser("Jones", "Jones");
		TitleTable.getInstance().createtitle("JonesBook", "Author");
		ItemTable.getInstance().createitem("JonesBook");
		
		LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Jones"),"JonesBook", "1", new Date() );
		
		
		
		
		assertEquals("this is useless","success", LoanTable.getInstance().renewal(UserTable.getInstance().lookup("Jones"), "JonesBook", "1", new Date())) ;
		assertEquals("this is useless","Renewed Item More Than Once for the Same Loan", LoanTable.getInstance().renewal(UserTable.getInstance().lookup("Jones"), "JonesBook", "1", new Date())) ;

		
		assertEquals("this is useless","The loan does not exist", LoanTable.getInstance().renewal(8, "9781442668584", "1", new Date())) ;


		assertEquals("this is useless","Outstanding Fee Exists", LoanTable.getInstance().renewal(0, "9781442668584", "1", new Date())) ;
		
		
	}
	
	
	
	
	@Test
	public void testRemoveUser( ){
		
		UserTable.getInstance().createuser(user,pass);
		assertEquals("this is useless","success", UserTable.getInstance().delete(UserTable.getInstance().lookup(user)) );
		
		
		UserTable.getInstance().createuser("maab", "123");
		LoanTable.getInstance().createloan(2, "9781442616899", "1", new Date());
		
		UserTable.getInstance().createuser("deleteme", "123");
		
		//UserTable.getInstance().delete(4);
		UserTable.getInstance().createuser("Dan", "Dan");
		
		
		assertEquals("this is useless","success", UserTable.getInstance().delete(UserTable.getInstance().lookup("Dan")) );
		assertEquals("this is useless","The User Does Not Exist", UserTable.getInstance().delete(66) );
		
		if ( FeeTable.getInstance().getFeeTable().get(0).getFee() == 0 ) FeeTable.getInstance().getFeeTable().get(0).setFee(5);
		
		assertEquals("this is useless","Outstanding Fee Exists", UserTable.getInstance().delete(0) );
		assertEquals("this is useless","Active Loan Exists", UserTable.getInstance().delete(2) );
		
		
		
	}
	
	
	@Test
	public void testRemoveItem(){
		TitleTable.getInstance().createtitle("Title1", "Author");
		ItemTable.getInstance().createitem("Title1");
		
		TitleTable.getInstance().createtitle("Remove1", "Author");
		ItemTable.getInstance().createitem("Remove1");
		
		UserTable.getInstance().createuser("oo", "oo");
		LoanTable.getInstance().createloan(UserTable.getInstance().lookup("oo"),"Remove1", "1", new Date());
		
		
		TitleTable.getInstance().createtitle("RemoveItemSucess3", "Author");
		ItemTable.getInstance().createitem("RemoveItemSucess3");
		
		LoanTable.getInstance().createloan(UserTable.getInstance().lookup("oo"), "Title1", "1", new Date());
		assertEquals("this is useless","Active Loan Exists", ItemTable.getInstance().delete("Remove1", "1"));
		assertEquals("this is useless","The Item Does Not Exist", ItemTable.getInstance().delete("RemoveItem2", "1"));
		
		String id = "1";
		
		
		if ( ItemTable.getInstance().lookup( "RemoveItemSucess3", id) == false) id = "2";
		
		
		assertEquals("this is useless","success", ItemTable.getInstance().delete("RemoveItemSucess3", id));
		
	}
	
	@Test
	public void testRemoveTitle(){
		TitleTable.getInstance().createtitle("RemoveTitle", "Author");
		
		
		TitleTable.getInstance().createtitle("Remove4", "Author");
		ItemTable.getInstance().createitem("Remove4");
		
		UserTable.getInstance().createuser("Remove", "34");
		
		LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Remove"), "Remove4", "1", new Date() );
		
		assertEquals("this is useless","Active Loan Exists", TitleTable.getInstance().delete("Remove4"));
		
		
		
		assertEquals("this is useless","success", TitleTable.getInstance().delete("RemoveTitle"));
	
		assertEquals("this is useless","The Title Does Not Exist", TitleTable.getInstance().delete("I dont exist"));
	}
	
	@Test
	public void testMonitorSystem(){
		
		System.out.print(TitleTable.getInstance().monitorSystem());
		String test = TitleTable.getInstance(). monitorSystem();
		for (int i = 0; i < UserTable.getInstance().getUserTable().size() ; i++){
		
			if (test.contains(UserTable.getInstance().getUserTable().get(i).toString())) {}
			else {
				assertEquals("failure",true, false);
				System.out.print("YOU FAILED");
			}
		}
		
		for (int i = 0; i < TitleTable.getInstance().getTitleTable().size() ; i++){
			
			if (test.contains(TitleTable.getInstance().getTitleTable().get(i).toString())) {}
			else {
				assertEquals("failure",true, false);
				System.out.print("YOU FAILED");
			}
		}
		
		
		
	}
	
}
	

