package com.jbk.model;

public class Charges 
{
	    private double gst;
	    private double deliveryCharge;
	    
		@Override
		public String toString() {
			return "Charges [gst=" + gst + ", deliveryCharge=" + deliveryCharge + "]";
		}
		public double getGst() {
			return gst;
		}
		public void setGst(double gst) {
			this.gst = gst;
		}
		public double getDeliveryCharge() {
			return deliveryCharge;
		}
		public void setDeliveryCharge(double deliveryCharge) {
			this.deliveryCharge = deliveryCharge;
		}
		public Charges(double gst, double deliveryCharge) {
			super();
			this.gst = gst;
			this.deliveryCharge = deliveryCharge;
		}
		public Charges() {
			super();
			// TODO Auto-generated constructor stub
		}
	
}
