package JUnit_testings;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import server.logic.tables.FeeTable;
import server.logic.tables.ItemTable;
import server.logic.tables.LoanTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class UserStories {
	
	Test1 use = new Test1();
	
	
	
	@Test
	public void userStory1(){
		
		use.testMonitorSystem();
		
	}
	
	@Test
	public void userStory2(){
		
		use.testCollectFine();
		
	}
	@Test
	public void userStory3(){
		use.testRemoveTitle();
		
		
		use.testRemoveUser();
		use.testRemoveItem();
		
	}
	@Test
	public void userStory4(){
		
		use.addUserTest();
		use.addTitleTest();
		use.addItemTest();
		
	}
	@Test
	public void userStory5(){
		
		use.testBorrowLoanCopy();
		
	}
	@Test
	public void userStory6(){
		
		use.testReturnItem();
		
	}
	
	@Test
	public void userStory7(){
		
		use.testRenewLoan();
	
	}
	@Test
	public void userStory8(){
		TitleTable.getInstance().createtitle("SorrowTitle1", "Author");
		ItemTable.getInstance().createitem("SorrowTitle1");
		

		TitleTable.getInstance().createtitle("SorrowTitl2", "Author");
		ItemTable.getInstance().createitem("SorrowTitle2");

		TitleTable.getInstance().createtitle("SorrowTitle3", "Author");
		ItemTable.getInstance().createitem("SorrowTitle3");
		
		TitleTable.getInstance().createtitle("SorrowTitle4", "Author");
		ItemTable.getInstance().createitem("SorrowTitle4");
		
		TitleTable.getInstance().createtitle("SorrowTitle5", "Author");
		ItemTable.getInstance().createitem("SorrowTitle5");

		TitleTable.getInstance().createtitle("SorrowTitle6", "Author");
		ItemTable.getInstance().createitem("SorrowTitle6");

		TitleTable.getInstance().createtitle("SorrowTitle7", "Author");
		ItemTable.getInstance().createitem("SorrowTitle7");

		TitleTable.getInstance().createtitle("SorrowTitle8", "Author");
		ItemTable.getInstance().createitem("SorrowTitle8");

		TitleTable.getInstance().createtitle("SorrowTitle9", "Author");
		ItemTable.getInstance().createitem("SorrowTitle9");
		
		TitleTable.getInstance().createtitle("SorrowTitle10", "Author");
		ItemTable.getInstance().createitem("SorrowTitle10");
		TitleTable.getInstance().createtitle("SorrowTitle11", "Author");
		ItemTable.getInstance().createitem("SorrowTitle11");
		TitleTable.getInstance().createtitle("SorrowTitle12", "Author");
		ItemTable.getInstance().createitem("SorrowTitle12");
		
		UserTable.getInstance().createuser("Just", "Just");
		UserTable.getInstance().lookup("Just");
		LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Just"), "SorrowTitle1", "1", new Date()) ;
		LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Just"), "SorrowTitle2", "1", new Date()) ;
		LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Just"), "SorrowTitle3", "1", new Date()) ;
		LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Just"), "SorrowTitle4", "1", new Date()) ;
		LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Just"), "SorrowTitle5", "1", new Date()) ;
		LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Just"), "SorrowTitle6", "1", new Date()) ;
		LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Just"), "SorrowTitle7", "1", new Date()) ;
		LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Just"), "SorrowTitle8", "1", new Date()) ;
		LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Just"), "SorrowTitle9", "1", new Date()) ;
		LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Just"), "SorrowTitle10", "1", new Date()) ;
		LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Just"), "SorrowTitle11", "1", new Date()) ;
		//LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Just"), "SorrowTitle12", "1", new Date()) ;
		
	//	use.testBorrowLoanCopy();
		assertEquals("This should pass","The Maximun Number of Items is Reached",LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Just"), "SorrowTitle12", "1", new Date())) ;
		
		
	}
	@Test
	public void userStory9(){
		

		TitleTable.getInstance().createtitle("Somebook", "Author");
		ItemTable.getInstance().createitem("Somebook");
		
		
		UserTable.getInstance().createuser("Maz", "Just");
		LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Maz"), "Somebook", "1", new Date()) ;
		
		UserTable.getInstance().createuser("Mazen", "Just");
		
		assertEquals("This should pass", "The Item is Not Available", LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Mazen"), "Somebook", "1", new Date())) ;
	}
	@Test
	public void userStory11(){
				
		UserTable.getInstance().createuser("Mat", "Just");
		
		TitleTable.getInstance().createtitle("Somebook2", "Author");
		ItemTable.getInstance().createitem("Somebook2");
		
		assertEquals("This should pass", "Copynumber Invalid", LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Mat"), "Somebook2", "2", new Date()));
		
}
	@Test
	public void userStory12(){
		
		UserTable.getInstance().createuser("Maq", "Just");
		
		TitleTable.getInstance().createtitle("Somebook3", "Author");
		ItemTable.getInstance().createitem("Somebook3");
		
		assertEquals("This should pass", "User Invalid", LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Maqd"), "Somebook3", "1", new Date()));
		
	}
	@Test
	public void userStory13(){
		UserTable.getInstance().createuser("Maw", "Just");
		
		TitleTable.getInstance().createtitle("Somebook4", "Author");
		ItemTable.getInstance().createitem("Somebook4");
		
		assertEquals("This should pass", "ISBN Invalid", LoanTable.getInstance().createloan(UserTable.getInstance().lookup("Maw"), "Somebookx", "1", new Date()));
		
	}
	
	@Test
	public void userStory14(){
		assertEquals("This should pass", false, UserTable.getInstance().createuser("Maw", "Just"));
		
	}
	@Test
	public void userStory15(){
		assertEquals("This should pass", false, TitleTable.getInstance().createtitle("Somebook4", "Author"));
		
	}
	
	
	
	
	
}