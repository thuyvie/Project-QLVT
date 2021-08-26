/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Mun Chan
 */
public class print {
    public print(String chid, Connection connection) throws JRException{
        HashMap<String, Object> parameters = new HashMap();
        parameters.put("chid", chid);
        try{
        JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/report/Bill/Invoicee.jrxml"));
        
        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, connection);
        JasperViewer.viewReport(jp, true);
            try {
                JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(new File(System.getProperty("user.home")+File.separator+"challanreport.pdf")));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(print.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(JRException ex){
        Logger.getLogger(print.class.getName()).log(Level.SEVERE, null,ex);
    }
    }
}
