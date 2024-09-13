package payments.api.models.request;

import com.google.gson.annotations.SerializedName;

public record StatusRequestPayload(@SerializedName("order_id") String orderId)  { }
