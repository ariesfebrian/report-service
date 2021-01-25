package id.collect.desk.reportservice.controller;

import id.collect.desk.reportservice.entity.Billing;
import id.collect.desk.reportservice.repo.BillingRepository;
import id.collect.desk.reportservice.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class ReportBillingTrx {

    @Autowired
    private BillingRepository billingRepository;

    @GetMapping("/list")
    public ResponseEntity<?> findAll() {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Map<String, Object> objectMap = new HashMap<>();
        List<Billing> billingList = (List<Billing>) billingRepository.findAll();
        objectMap.put("data", billingList);

        Response response = new Response();
        response.setMessage("Load List Billing Transaction Successfully");
        response.setService(nameofCurrMethod);
        response.setData(objectMap);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/status/{isClose}")
    public ResponseEntity<?> listByStatus(@PathVariable int isClose) {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Map<String, Object> objectMap = new HashMap<>();
        List<Object[]> billingList = billingRepository.listByStatus(isClose);
        objectMap.put("data", billingList);

        Response response = new Response();
        response.setMessage("Load List Billing By Status Successfully");
        response.setService(nameofCurrMethod);
        response.setData(objectMap);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
