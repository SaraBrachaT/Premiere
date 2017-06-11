package JavaPremiereDBSQLSecurityApp;

public class OrderLineData {
  private String partID;
  private int qty;
  private double price;
  
  public OrderLineData( String pID, int q, double p){
	  partID = pID;
	  qty = q;
	  price = p;
	  
  }
    
public String getPartID() {
	return partID;
}

public int getQty() {
	return qty;
}

public double getPrice() {
	return price;
}
  
}