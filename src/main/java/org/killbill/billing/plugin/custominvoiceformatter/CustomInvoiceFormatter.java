package org.killbill.billing.plugin.custominvoiceformatter;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.killbill.billing.invoice.api.Invoice;
import org.killbill.billing.invoice.api.InvoiceItem;
import org.killbill.billing.invoice.api.formatters.InvoiceItemFormatter;
import org.killbill.billing.plugin.notification.generator.formatters.DefaultInvoiceFormatter;
import org.killbill.billing.plugin.notification.generator.formatters.DefaultInvoiceItemFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CustomInvoiceFormatter extends DefaultInvoiceFormatter {

    private static final Logger logger = LoggerFactory.getLogger(CustomInvoiceFormatter.class);
    private final String newInvoiceMessage = "Here is your new invoice!!"; //custom field to be added to invoice

    private final Invoice invoice;
    private final Map<String, String> translator;
    private final Locale locale;

    private final DateTimeFormatter dateFormatter;

    public CustomInvoiceFormatter(Map<String, String> translator, Invoice invoice, Locale locale) {
        super(translator, invoice, locale);
        this.invoice = invoice;
        this.translator = translator;
        this.locale = locale;
        this.dateFormatter = DateTimeFormat.mediumDate().withLocale(locale);
        logger.info("Creating CustomInvoiceFormatter");
    }

    public String getNewInvoiceMessage() {
        return newInvoiceMessage;
    }

    @Override
    public LocalDate getTargetDate() {
        logger.info("getTargetDate: "+invoice.getTargetDate());
        return invoice.getTargetDate().minusDays(1);
    }

    public List<InvoiceItem> getInvoiceItems() {
        final List<InvoiceItem> formatters = new ArrayList<InvoiceItem>();
        for (final InvoiceItem item : invoice.getInvoiceItems()) {
            formatters.add(new CustomInvoiceItemFormatter(translator, item, dateFormatter, locale));
        }
        return formatters;
    }




}
