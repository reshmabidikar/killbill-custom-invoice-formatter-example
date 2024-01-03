package org.killbill.billing.plugin.custominvoiceformatter;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.killbill.billing.callcontext.InternalTenantContext;
import org.killbill.billing.currency.api.CurrencyConversionApi;
import org.killbill.billing.invoice.api.Invoice;
import org.killbill.billing.invoice.api.InvoiceItem;
import org.killbill.billing.invoice.api.formatters.InvoiceItemFormatter;
import org.killbill.billing.invoice.api.formatters.ResourceBundleFactory;
import org.killbill.billing.invoice.template.formatters.DefaultInvoiceFormatter;
import org.killbill.billing.util.template.translation.TranslatorConfig;
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
//    private final Map<String, String> translator;
//    private final Locale locale;

    private final DateTimeFormatter dateFormatter;

    public CustomInvoiceFormatter(final TranslatorConfig config, final Invoice invoice, final Locale locale,
                                  final CurrencyConversionApi currencyConversionApi, final ResourceBundleFactory bundleFactory,
                                  final InternalTenantContext context) {
        super(config, invoice, locale, currencyConversionApi, bundleFactory, context);
        logger.info("Reshma CustomInvoiceFormatter constructor");
        this.invoice = invoice;
        this.dateFormatter = DateTimeFormat.mediumDate().withLocale(locale);
    }

    public String getNewInvoiceMessage() {
        return newInvoiceMessage;
    }

    @Override
    public LocalDate getTargetDate() {
        logger.info("getTargetDate: "+invoice.getTargetDate());
        return invoice.getTargetDate().minusDays(1);
    }

//    public List<InvoiceItem> getInvoiceItems() {
//        final List<InvoiceItem> formatters = new ArrayList<InvoiceItem>();
//        for (final InvoiceItem item : invoice.getInvoiceItems()) {
//            formatters.add(new CustomInvoiceItemFormatter(translator, item, dateFormatter, locale));
//        }
//        return formatters;
//    }




}
