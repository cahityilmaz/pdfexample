package com.capp.pdfexample;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.DocFlavor;
import javax.print.PrintException;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrintQuality;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.ri.common.PrintHelper;

/**
 *
 * @author mucahit.yilmaz
 */
public class Printer {

    public static void print(byte[] data, PrintQuality printQuality) {
        try {
            var doc = new Document();
            doc.setByteArray(data, 0, data.length, null);
            var services = PrintServiceLookup.lookupPrintServices(DocFlavor.SERVICE_FORMATTED.PRINTABLE, null);
            var hashPrintRequestAttributeSet = new HashPrintRequestAttributeSet();
            var service = ServiceUI.printDialog(null, 0, 0, services,
                    PrintServiceLookup.lookupDefaultPrintService(),
                    DocFlavor.SERVICE_FORMATTED.PRINTABLE,
                    hashPrintRequestAttributeSet);
            hashPrintRequestAttributeSet.add(printQuality);

            var printHelper = new PrintHelper(null, doc.getPageTree(), 0, MediaSizeName.ISO_A4, PrintQuality.NORMAL);
            printHelper.setupPrintService(service, hashPrintRequestAttributeSet, false);
            printHelper.print();
            
        } catch (PrintException | PDFException | PDFSecurityException | IOException ex) {
            Logger.getLogger(Printer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
