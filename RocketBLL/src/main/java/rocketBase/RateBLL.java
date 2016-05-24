package rocketBase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.formula.functions.*;
import org.hibernate.Session;

import exceptions.RateException;
import rocketDomain.RateDomainModel;
import util.HibernateUtil;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException 
	{
		//tx = session.beginTransaction();
		//ArrayList<RateDomainModel> allRates = new ArrayList<RateDomainModel>();	
		//List rateList = RateDAL.getAllRates(); 
		//Session session = HibernateUtil.getSessionFactory().openSession();
		double scoreRate = 0;
		boolean rateFound = false; 
		/*ArrayList<RateDomainModel> aRates = RateDAL.getAllRates();
		List lstRates = session.createQuery("FROM RateDomainModel ORDER BY MINCREDITSCORE DESC").list();
		for (Iterator iterator = lstRates.iterator(); iterator.hasNext();) {
			RateDomainModel rte = (RateDomainModel) iterator.next();
			allRates.add(rte);
		}*/
		for(RateDomainModel r : RateDAL.getAllRates()){
			if (GivenCreditScore > r.getiMinCreditScore()) {
				scoreRate = r.getdInterestRate();
				rateFound = true;
			}
		}
		if (rateFound == false){
			//System.out.println("Error: Insufficient Credit Score");
			throw new RateException("Error: Insufficient Credit Score");
		}
		
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		//List lstRates = session.createQuery("FROM RateDomainModel ORDER BY MINCREDITSCORE DESC").list();
		
		//TODO - RocketBLL RateBLL.getRate
		//			obviously this should be changed to return the determined rate
		return scoreRate;
		
		
	}
	
	
	//TODO - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r, n, p, f, t);
	}
}
