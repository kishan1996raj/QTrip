package qtriptest;

import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;

public class ReportSingleton {
    static ExtentReports extreports;
    final static String filepath = System.getProperty("user.dir")+File.separator+"reports"+File.separator+"extentreport.html";
    private ReportSingleton(){
    }
    public static synchronized ExtentReports getReport(){
        if(extreports == null){
            extreports = new ExtentReports(filepath,true);
            extreports.loadConfig(new File("/home/crio-user/workspace/kishan1996raj-ME_QTRIP_QA_V2/app/extent_customization_configs.xml"));
        }
        return extreports;
    }
}