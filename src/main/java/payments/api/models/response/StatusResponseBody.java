package payments.api.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class StatusResponseBody {
    
    public Order order;
    public Map<String, Transaction> transactions;
    
    public static class Order {

        @SerializedName("order_id")
        public String orderId;
        
        @SerializedName("order_description")
        public String orderDescription;
        
        @SerializedName("subscription_id")
        public String subscriptionId;

        public Integer amount;
    
        public String currency;

        @SerializedName("processing_amount")
        public Integer processingAmount;

        @SerializedName("processing_currency")
        public String processingCurrency;

        @SerializedName("marketing_amount")
        public Integer marketingAmount;

        @SerializedName("marketing_currency")
        public String marketingCurrency;

        @SerializedName("refunded_amount")
        public Integer refundedAmount;

        public String status;

        @SerializedName("payment_type")
        public String paymentType;

        @SerializedName("customer_email")
        public String customerEmail;
    
        public String descriptor;

        public String mid;

        @SerializedName("traffic_source")
        public String trafficSource;

    }

    public static class Transaction {
        public String status;
    }
}
