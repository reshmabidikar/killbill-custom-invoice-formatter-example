package org.killbill.billing.plugin.custominvoiceformatter;

import org.joda.time.format.DateTimeFormatter;
import org.killbill.billing.invoice.api.InvoiceItem;
import org.killbill.billing.plugin.notification.generator.formatters.DefaultInvoiceItemFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.Map;

public class CustomInvoiceItemFormatter extends DefaultInvoiceItemFormatter {

    public final InvoiceItem item;
    public final DateTimeFormatter dateFormatter;

    private static final Logger logger = LoggerFactory.getLogger(CustomInvoiceItemFormatter.class);

    public CustomInvoiceItemFormatter(Map<String, String> translator, InvoiceItem item, DateTimeFormatter dateFormatter, Locale locale) {
        super(translator, item, dateFormatter, locale);
        logger.info("Creating CustomInvoiceItemFormatter:");
        this.item = item;
        this.dateFormatter = dateFormatter;
    }

    @Override
    public String getFormattedEndDate() {
        logger.info("getFormattedEndDate:"+item.getEndDate());
        return item.getEndDate() == null ? null : item.getEndDate().minusDays(1).toString(dateFormatter);
    }
}
