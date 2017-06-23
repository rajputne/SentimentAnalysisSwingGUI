/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dassault.Business;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NRT4
 */
public class ServiceRequestList {

    List srList;

    public List getSrList() {
        return srList;
    }

    public void setSrList(List srList) {
        this.srList = srList;
    }

    public ServiceRequestList() {
        srList = new ArrayList<ServiceRequest>();
    }

}
