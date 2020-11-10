package com.orangelab.testcase;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.orangelab.util.ExcelUtil;

public class Registerparams {
	@DataProvider(name = "dataMethod")
    public Object[][] dataMethod(){
        List<Map<String, String>> result = ExcelUtil.getExcuteList("D:\\project\\autotest_wolf\\data\\register.xlsx");
        Object[][] files = new Object[result.size()][];
        for(int i=0; i<result.size(); i++){
            files[i] = new Object[]{result.get(i)};
        }
        return files;
    }
}
