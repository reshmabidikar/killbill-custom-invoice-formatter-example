package org.killbill.billing.plugin.custominvoiceformatter;

import org.killbill.billing.callcontext.InternalTenantContext;
import org.killbill.billing.currency.api.CurrencyConversionApi;
import org.killbill.billing.invoice.api.Invoice;
import org.killbill.billing.invoice.api.formatters.InvoiceFormatter;
import org.killbill.billing.invoice.api.formatters.InvoiceFormatterFactory;
import org.killbill.billing.invoice.api.formatters.ResourceBundleFactory;
import org.killbill.billing.util.callcontext.TenantContext;
import org.killbill.billing.util.template.translation.TranslatorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.Map;

public class CustomInvoiceFormatterFactory implements InvoiceFormatterFactory {

    private static final Logger logger = LoggerFactory.getLogger(CustomInvoiceFormatterFactory.class);

    public CustomInvoiceFormatterFactory() {
        super();
        logger.info("Creating CustomInvoiceFormatterFactory");
    }


    @Override
    public InvoiceFormatter createInvoiceFormatter(TranslatorConfig config, Invoice invoice, Locale locale, CurrencyConversionApi currencyConversionApi, ResourceBundleFactory bundleFactory, InternalTenantContext context) {
        return new CustomInvoiceFormatter(config, invoice, locale, currencyConversionApi, bundleFactory, context);
    }
}
