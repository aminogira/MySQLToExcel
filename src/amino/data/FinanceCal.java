/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package amino.data;

/**
 *
 * @author User
 */
public class FinanceCal {


//System.out.println(PMT(85000,60,10,12)); (lnamt,noofIns
//    System.out.println("loanamt " +loan_amount);
//    System.out.println("noofprd " +number_of_periods);
//    System.out.println("anl int rate " +annual_interest_rate);
//    System.out.println("pmnt pre year " +payments_per_year);
//    System.out.println("loan amt " +loan_amount);

  public static double PMT(double loan_amount,int number_of_periods,double annual_interest_rate,int payments_per_year){
    annual_interest_rate=annual_interest_rate/100.0;
    //System.out.println(PMT(85000,60,10,12));
    double payment_interest_rate = 1 / (1 + (annual_interest_rate/payments_per_year));
    return ((1 - payment_interest_rate) * loan_amount) / (payment_interest_rate * (1 - Math.pow(payment_interest_rate,number_of_periods)));
  }



}
