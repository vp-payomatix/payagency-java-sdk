package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Response for payment templates query.
 */
public class PaymentTemplatesOutput {
    @JsonProperty("data")
    private List<PaymentTemplate> data;

    // Default constructor
    public PaymentTemplatesOutput() {}

    // Getters and setters
    public List<PaymentTemplate> getData() { return data; }
    public void setData(List<PaymentTemplate> data) { this.data = data; }

    public static class PaymentTemplate {
        @JsonProperty("template_id")
        private String templateId;
        
        @JsonProperty("template_name")
        private String templateName;
        
        @JsonProperty("payment_template_id")
        private String paymentTemplateId;
        
        @JsonProperty("template_screenshot")
        private String templateScreenshot;
        
        @JsonProperty("redirect_url")
        private String redirectUrl;
        
        @JsonProperty("webhook_url")
        private String webhookUrl;

        // Default constructor
        public PaymentTemplate() {}

        // Getters and setters
        public String getTemplateId() { return templateId; }
        public String getTemplateName() { return templateName; }
        public String getPaymentTemplateId() { return paymentTemplateId; }
        public String getTemplateScreenshot() { return templateScreenshot; }
        public String getRedirectUrl() { return redirectUrl; }
        public String getWebhookUrl() { return webhookUrl; }

        public void setTemplateId(String templateId) { this.templateId = templateId; }
        public void setTemplateName(String templateName) { this.templateName = templateName; }
        public void setPaymentTemplateId(String paymentTemplateId) { this.paymentTemplateId = paymentTemplateId; }
        public void setTemplateScreenshot(String templateScreenshot) { this.templateScreenshot = templateScreenshot; }
        public void setRedirectUrl(String redirectUrl) { this.redirectUrl = redirectUrl; }
        public void setWebhookUrl(String webhookUrl) { this.webhookUrl = webhookUrl; }
    }
}
