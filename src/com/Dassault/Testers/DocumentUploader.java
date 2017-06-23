/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dassault.Testers;

import java.io.File;

import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author NRT4
 */
public class DocumentUploader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        CloseableHttpClient httpclient = HttpClients.custom()
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
                .build();

        String user = "NRT4";
        String pwd = "Nanda@1234";
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY,
                new NTCredentials(user, pwd, "", ""));

        // You may get 401 if you go through a load-balancer.
        // To fix this, go directly to one the sharepoint web server or
        // change the config. See this article :
        // http://blog.crsw.com/2008/10/14/unauthorized-401-1-exception-calling-web-services-in-sharepoint/
        HttpHost target = new HttpHost("dscollaboration.dsone.3ds.com", 80, "http");
        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credsProvider);

        // The authentication is NTLM.
        // To trigger it, we send a minimal http request
        HttpHead request1 = new HttpHead("/");
        CloseableHttpResponse response1 = null;
        try {
            response1 = httpclient.execute(target, request1, context);
            EntityUtils.consume(response1.getEntity());
            System.out.println("1 : " + response1.getStatusLine().getStatusCode());
        } finally {
            if (response1 != null) {
                response1.close();
            }
        }

        // The real request, reuse authentication
        String file = "/sites/DS-WWSupport/ENOVIA-VPLM/AG_Support_Ops/Sandbox/Shared%20Documents/file.txt";
        HttpPut request2 = new HttpPut("/sites/DS-WWSupport/ENOVIA-VPLM/AG_Support_Ops/Sandbox/Shared%20Documents/file.txt");  // target
        request2.setEntity(new FileEntity(new File("file.txt")));// source
        CloseableHttpResponse response2 = null;
        try {
            response2 = httpclient.execute(target, request2, context);
            EntityUtils.consume(response2.getEntity());
            int rc = response2.getStatusLine().getStatusCode();
            String reason = response2.getStatusLine().getReasonPhrase();
            // The possible outcomes :
            //    201 Created
            //        The request has been fulfilled and resulted in a new resource being created
            //    200 OK
            //        Standard response for successful HTTP requests.
            //    others
            //        we have a problem
            if (rc == HttpStatus.SC_CREATED) {
                System.out.println(file + " is copied (new file created)");
            } else if (rc == HttpStatus.SC_OK) {
                System.out.println(file + " is copied (original overwritten)");
            } else {
                throw new Exception("Problem while copying " + file
                        + "  reason " + reason + "  httpcode : " + rc);
            }
        } finally {
            if (response2 != null) {
                response2.close();
            }
        }
        return;
    }

}
