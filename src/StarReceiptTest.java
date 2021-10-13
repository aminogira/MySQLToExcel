//import jpos.JposConst;
//import jpos.JposException;
//import jpos.POSPrinter;
//import jpos.POSPrinterConst;

//import jpos.util.JposPropertiesConst;

public class StarReceiptTest
{
//    public static void main(String[] args)
//    {
//        /*
//         	If you want to place the jpos.xml file elsewhere on your local file system then uncomment the
//            following line and specify the full path to jpos.xml.
//
//            If you want to place the jpos.xml file on a webserver for access over the internet then uncomment
//            the second System.setProperty line below and specify the full URL to jpos.xml.
//        */
//        System.setProperty(JposPropertiesConst.JPOS_POPULATOR_FILE_PROP_NAME, "jpos.xml");
//        //System.setProperty(JposPropertiesConst.JPOS_POPULATOR_FILE_URL_PROP_NAME, "http://some-where-remote.com/jpos.xml");
//
//        // constants defined for convience sake (could be inlined)
//        String ESC    = ((char) 0x1b) + "";
//        String LF     = ((char) 0x0a) + "";
//        String SPACES = "                                                                      ";
//
//        // instantiate a new jpos.POSPrinter object
//        POSPrinter printer = new POSPrinter();
//
//        try
//        {
//            // open the printer object according to the entry names defined in jpos.xml
//            printer.open("startsp");
//
//            // claim exclsive usage of the printer object
//            printer.claim(1);
//
//            // enable the device for input and output
//            printer.setDeviceEnabled(true);
//
//            // set map mode to metric - all dimensions specified in 1/100mm units
//            printer.setMapMode(POSPrinterConst.PTR_MM_METRIC);  // unit = 1/100 mm - i.e. 1 cm = 10 mm = 10 * 100 units
//
//            do
//            {
//                // poll for printer status
//                //   a javax.swing based application would be best to both poll for status
//                //   AND register for asynchronous StatusUpdateEvent notification
//                //   see the JavaPOS specification for details on this
//
//                // check if the cover is open
//                if (printer.getCoverOpen() == true)
//                {
//                    System.out.println("printer.getCoverOpen() == true");
//
//                    // cover open so do not attempt printing
//                    break;
//                }
//
//                // check if the printer is out of paper
//                if (printer.getRecEmpty() == true)
//                {
//                    System.out.println("printer.getRecEmpty() == true");
//
//                    // the printer is out of paper so do not attempt printing
//                    break;
//                }
//
//                // being a transaction
//                //   transaction mode causes all output to be buffered
//                //   once transaction mode is terminated, the buffered data is
//                //   outputted to the printer in one shot - increased reliability
//                printer.transactionPrint(POSPrinterConst.PTR_S_RECEIPT, POSPrinterConst.PTR_TP_TRANSACTION);
//
//                if (printer.getCapRecBitmap() == true)
//                {
//                    // print an image file
//                    try
//                    {
//                        printer.printBitmap(POSPrinterConst.PTR_S_RECEIPT, "star.gif", POSPrinterConst.PTR_BM_ASIS, POSPrinterConst.PTR_BM_CENTER);
//                    }
//                    catch (JposException e)
//                    {
//                        if (e.getErrorCode () != JposConst.JPOS_E_NOEXIST)
//                        {
//                            // error other than file not exist - propogate it
//                            throw e;
//                        }
//
//                        // image file not found - ignore this error & proceed
//                    }
//                }
//
//                // call printNormal repeatedly to generate out receipt
//                //   the following JavaPOS-POSPrinter control code sequences are used here
//                //   ESC + "|cA"          -> center alignment
//                //   ESC + "|4C"          -> double high double wide character printing
//                //   ESC + "|bC"          -> bold character printing
//                //   ESC + "|uC"          -> underline character printing
//                //   ESC + "|rA"          -> right alignment
//
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|4C" + ESC + "|bC" + "Star Grocer"     + LF);
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" +               "Shizuoka, Japan" + LF);
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|bC" +               "054-555-5555"    + LF);
//
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|uC" + "Qnty Unit Tx Description" + SPACES.substring(0, printer.getRecLineChars() - "Qnty Unit Tx Description".length()) + LF);
//
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT,               "   1  830    Soba Noodles"        + LF);
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT,               "   1  180    Daikon Radish"       + LF);
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT,               "   1  350    Shouyu Soy Sauce"    + LF);
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT,               "   1   80    Negi Green Onions"   + LF);
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT,               "   1  100    Wasabi Horse Radish" + LF);
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT,               "   2  200 Tx Hashi Chop Sticks"   + LF);
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT, LF);
//
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT,               ESC + "|rA" +               "Subtotal:  2160" + LF);
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT,               ESC + "|rA" +               "Tax:         24" + LF);
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT,               ESC + "|rA" + ESC + "|bC" + "Total:     2184" + LF);
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT,               ESC + "|rA" +               "Tender:    2200" + LF);
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT,               ESC + "|rA" + ESC + "|bC" + "Change:      16" + LF);
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT, LF);
//
//                if (printer.getCapRecBarCode() == true)
//                {
//                    // print a Code 3 of 9 barcode with the data "123456789012" encoded
//                    //   the 10 * 100, 60 * 100 parameters below specify the barcode's height and width
//                    //   in the metric map mode (1cm tall, 6cm wide)
//                    printer.printBarCode(POSPrinterConst.PTR_S_RECEIPT, "123456789012", POSPrinterConst.PTR_BCS_Code39, 10 * 100, 60 * 100, POSPrinterConst.PTR_BC_CENTER, POSPrinterConst.PTR_BC_TEXT_BELOW);
//                }
//
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|cA" + ESC + "|4C" + ESC + "|bC" + "Thank you" + LF);
//
//                // the ESC + "|100fP" control code causes the printer to execute a paper cut
//                //   after feeding to the cutter position
//                printer.printNormal(POSPrinterConst.PTR_S_RECEIPT, ESC + "|100fP");
//
//                // terminate the transaction causing all of the above buffered data to be sent to the printer
//                printer.transactionPrint(POSPrinterConst.PTR_S_RECEIPT, POSPrinterConst.PTR_TP_NORMAL);
//
//                // exit our printing loop
//            } while (false);
//        }
//        catch(JposException e)
//        {
//            // display any errors that come up
//            e.printStackTrace();
//        }
//        finally
//        {
//            // close the printer object
//            try
//            {
//                printer.close();
//            }
//            catch (Exception e) {}
//        }
//
//        System.out.println("StarReceiptTest finished.");
//        System.exit(0);
//    }
//
}