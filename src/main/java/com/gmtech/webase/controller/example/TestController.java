package com.gmtech.webase.controller.example;

import com.gmtech.webase.common.ResponseEntity;
import com.gmtech.webase.controller.response.Employee;
import com.gmtech.webase.exception.ErrorDetail;
import com.gmtech.webase.exception.MyException;
import com.gmtech.webase.util.ResponseEntityUtil;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/test")
public class TestController{
	private static Logger log = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/index")
	  private ResponseEntity sample2() {
	    BigDecimal salaryPerYear = new BigDecimal(1000);
	    BigDecimal bankAccounts = new BigDecimal(123456789);
	    BigDecimal workingHoursPerDay = new BigDecimal(8);
	    // 年収1000万、一日8時間勤務の会社員をインスタンス化
	    Employee employee = new Employee(salaryPerYear, bankAccounts, workingHoursPerDay);
	    System.out.println(employee);
	    log.info("estsete");
	    return ResponseEntityUtil.success(employee);
	  }
	
	@RequestMapping("/test")
    public String get() {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setDetailMessage("詳細なメッセージ");
        throw new MyException("MyException 発生", errorDetail);
    }
	
	@RequestMapping("/fileupload")
    public String fileupload(@RequestParam("file") MultipartFile file) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setDetailMessage("詳細なメッセージ");
        throw new MyException("MyException 発生", errorDetail);
    }
}
