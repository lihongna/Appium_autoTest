package com.orangelab.testcase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.dom4j.DocumentException;
import org.testng.annotations.*;
import org.xml.sax.SAXException;

import com.orangelab.service.AppiumServer;
import com.orangelab.util.Device;
import com.orangelab.util.DosCmd;
import com.orangelab.util.ExcelUtil;
import com.orangelab.util.Port;
import com.orangelab.util.XmlUtil;

public class demo{

	//@Parameters({"udid"})
	@Test//(dataProvider = "dataMethod")
    public void testBuy2(){	
		
		XmlUtil.getParamFile();        
    }
	
	//@DataProvider(name = "dataMethod")
    public Object[][] dataMethod(){
        List<Map<String, String>> result = ExcelUtil.getExcuteList("D:\\project\\autotest_wolf\\data\\register.xlsx");
        Object[][] files = new Object[result.size()][];
        for(int i=0; i<result.size(); i++){
            files[i] = new Object[]{result.get(i)};
        }
        return files;
    }
    
    public Object[][] getParam(String uid){
    	List<Map<String, String>> result = ExcelUtil.getExcuteList("D:\\project\\autotest_wolf\\data\\register.xlsx");
        Object[][] files = new Object[result.size()][];
        HashMap<String,String> data = new HashMap<String,String>();
        for(int i=0; i<result.size(); i++){
            files[i] = new Object[]{result.get(i)};
            System.out.println(files[i]);
            
        }
        return files;
    }
}
