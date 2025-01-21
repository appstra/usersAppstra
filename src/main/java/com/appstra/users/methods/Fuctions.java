package com.appstra.users.methods;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class Fuctions {

    // Método para parsear los datos de la respuesta de la API externa
    public List<Map<String, Object>> parseData(String responseGet) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseGet, List.class);
    }

    // Método para obtener una lista de companyId
    public List<Map<String, Object>> extractCompanyIds(List<Map<String, Object>> companyList) {
        List<Map<String, Object>> listCompany = new ArrayList<>();
        for (Map<String, Object> entry : companyList) {
            Map<String, Object> companyData = (Map<String, Object>) entry.get("company");
            if (companyData != null) {
                Map<String, Object> companyIdMap = new HashMap<>();
                companyIdMap.put("companyId", companyData.get("companyId"));
                listCompany.add(companyIdMap);
            }
        }
        return listCompany;
    }
    /**
     * Unifica las listas de empresas y empleados.
     */
    public List<Map<String, Object>> unifyCompanyAndEmployeeData(List<Map<String, Object>> companyList, List<Map<String, Object>> employeeList) {
        List<Map<String, Object>> unifiedList = new ArrayList<>();

        for (Map<String, Object> company : companyList) {
            int companyId = (int) company.get("companyId");
            Map<String, Object> unifiedData = new HashMap<>();

            unifiedData.put("companyId", companyId);
            unifiedData.put("employeeId", null);
            unifiedData.put("personId", null);
            unifiedData.put("employeeRoleId", null);

            for (Map<String, Object> employee : employeeList) {
                if (employee.get("companyId").equals(companyId)) {
                    unifiedData.put("employeeId", employee.get("employeeId"));
                    unifiedData.put("personId", employee.get("personId"));
                    unifiedData.put("employeeRoleId", employee.get("employeeRoleId"));
                }else{
                    unifiedData.put("personId", employee.get("personId"));
                    unifiedData.put("employeeRoleId", employee.get("employeeRoleId"));
                }
            }

            unifiedList.add(unifiedData);
        }

        return unifiedList;
    }
}
