package server.logic.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import server.logic.model.Fee;
import server.logic.model.Loan;
import utilities.Config;
import utilities.Trace;

public class FeeTable {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<Fee> feeList=new ArrayList<Fee>();
    private static class FeeListHolder {
        private static final FeeTable INSTANCE = new FeeTable();
    }
    private FeeTable(){
    	//set up the default list with some instances
    	Fee fee=new Fee(0,5);
    	feeList.add(fee);
    	Initialization();
    };
    public static final FeeTable getInstance() {
        return FeeListHolder.INSTANCE;
    }
	public boolean lookup(int j) {
		boolean result=true;
		int fee = 0;
		boolean user=FeeTable.getInstance().checkuser(j);
		if(user){
			for(int i=0;i<feeList.size();i++){
				int userid=(feeList.get(i)).getUserid();
				if(userid==j){
					fee=fee+feeList.get(i).getFee();
				}
			}	
		}else{
			fee=0;
		}
		if(fee!=0){
			result=false;
		}
		return result;
	}
	private boolean checkuser(int j) {
		boolean result=true;
		int fee = 0;
		for(int i=0;i<feeList.size();i++){
			int userid=(feeList.get(i)).getUserid();
			if(userid==j){
				fee=fee+1;
			}else{
				fee=fee+0;
			}
		}	
		if(fee==0){
			result=false;
		}
		return result;
	}
	public Object lookupfee(int j) {
		int fee=0;
		boolean user=FeeTable.getInstance().checkuser(j);
		if(user){
		for(int i=0;i<feeList.size();i++){
			int userid=(feeList.get(i)).getUserid();
			if(userid==j){
				fee=fee+feeList.get(i).getFee();
			}
		}
		}else{
			fee=0;
		}
		return fee;
	}
	public void applyfee(int j, long time) {
		int flag=0;
		int index=0;
		for(int i = 0;i<feeList.size();i++){
			int userid=(feeList.get(i)).getUserid();
			if(userid==j){
				flag=flag+1;
				index=i;
			}
		}
		int a=(int) ((time/(Config.STIMULATED_DAY))-Config.OVERDUE);
		if(flag!=0){
			if(a>=0){
				feeList.get(index).setFee(a+feeList.get(index).getFee());
				feeList.get(index).setUserid(j);
				logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", j,a+feeList.get(index).getFee()));
			}else{
				feeList.get(index).setFee(feeList.get(index).getFee());
				feeList.get(index).setUserid(j);
				logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", j,a+feeList.get(index).getFee()));
			}
		}else{
			if(a>=0){
				Fee fee=new Fee(j,a);
				feeList.add(fee);
				logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", j,a));
			}else{
				Fee fee=new Fee(j,0);
				feeList.add(fee);
				logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", j,0));
			}
			
		}
		
		
	}
	public List<Fee> getFeeTable() {
		return feeList;
	}
	public void Initialization(){
    	List<Loan> loanList=LoanTable.getInstance().getLoanTable();
    	for(int i=0;i<loanList.size();i++){
    		applyfee(loanList.get(i).getUserid(), new Date().getTime()-loanList.get(i).getDate().getTime());
    	}
    	logger.info(String.format("Operation:Initialize FeeTable;FeeTable: %s", feeList));
	}
	public Object payfine(int i) {
		String result="DELETE IN PAY FINE";
		boolean oloan= LoanTable.getInstance().looklimit(i);
		int fee=0;
		int index=0;
		boolean user=FeeTable.getInstance().checkuser(i);
		if(user){
			for(int m=0;m<feeList.size();m++){
				if(feeList.get(m).getUserid()==i){
					fee=feeList.get(m).getFee();
					index=m;
				}else{
					fee=0;
				}
			}
		}else{
			fee=0;
		}
		if(oloan==false){
			result="Borrowing Items Exist";
			logger.info(String.format("Operation:Pay Fine;Fee Info:[%d,%d];State:Fail;Reason:Borrowing Items Exist.", i,fee));
		}else{
			feeList.get(index).setUserid(i);
			feeList.get(index).setFee(0);
			result="success";
			logger.info(String.format("Operation:Pay Fine;Fee Info:[%d,%d];State:Success", i,fee));
		}
		//System.out.print(result);
		return result;
	}
	

}
