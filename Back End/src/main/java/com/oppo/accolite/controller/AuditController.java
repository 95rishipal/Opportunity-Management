package com.oppo.accolite.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import com.oppo.accolite.dao.AuditDaoImp;
import com.oppo.accolite.models.*;

@Controller
public class AuditController {

      @Autowired
      AuditDaoImp auditDaoImp;

      @GetMapping(path = "/audit/getall")
      @ResponseBody
      public List<Audit> getAllAudit() {
            return auditDaoImp.getAllAudit();
      }

      @GetMapping(path = "/audit/search/{col}/{place}", produces = {
            MediaType.APPLICATION_JSON_VALUE
      })
      @ResponseBody
      public List<Audit> searchBy(@PathVariable("col") String col, @PathVariable("place") String place) {
            return auditDaoImp.search(col, place);
      }

}